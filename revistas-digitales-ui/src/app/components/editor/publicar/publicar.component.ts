import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { PublicacionService } from '../../../services/publicacion/publicacion.service';
import { CommonModule } from '@angular/common';
import { AnuncioIndividualComponent } from "../../anunciante/anuncio-individual/anuncio-individual.component";

@Component({
  selector: 'app-publicar',
  standalone: true,
  imports: [CommonModule, FormsModule, AnuncioIndividualComponent],
  templateUrl: './publicar.component.html',
  styleUrls: ['./publicar.component.css']
})
export class PublicarComponent implements OnInit {
  nombreRevista: string = '';
  descripcion: string = '';
  archivoPDF: File | null = null;
  fechaSeleccionada: string = ''; // Campo para la fecha seleccionada
  mensajeErrorArchivo: string = '';
  mensajeExito: string = '';
  rutaActual!: string;

  constructor(private route: ActivatedRoute, private publicacionService: PublicacionService, private router: Router ) {}
  
  ngOnInit(): void {
    this.rutaActual = this.router.url;
    this.nombreRevista = this.route.snapshot.paramMap.get('nombre') || 'Sin nombre';
  }

  onFileSelected(event: Event): void {
    const target = event.target as HTMLInputElement;
    if (target.files && target.files.length > 0) {
      const file = target.files[0];
      
      if (file.type === 'application/pdf') {
        this.archivoPDF = file;
        this.mensajeErrorArchivo = '';
      } else {
        this.archivoPDF = null;
        this.mensajeErrorArchivo = 'Solo se permiten archivos PDF.';
      }
    }
  }

  guardarPublicacion(): void {
    if (this.nombreRevista && this.archivoPDF && this.descripcion && this.fechaSeleccionada) {
      this.publicacionService.realizarPublicacion(this.nombreRevista, this.descripcion, this.archivoPDF, this.fechaSeleccionada)
        .subscribe(
          () => {
            this.mensajeExito = 'Publicación realizada con éxito';
            this.reiniciarFormulario();
          },
          error => {
            this.mensajeExito = '';
            console.error('Error al realizar la publicación', error);
            this.mensajeErrorArchivo = 'Error al realizar la publicación. Intente nuevamente.';
          }
        );
    } else {
      this.mensajeErrorArchivo = 'Falta descripción, archivo PDF, fecha o nombre de la revista.';
    }
  }

  reiniciarFormulario(): void {
    this.descripcion = '';
    this.archivoPDF = null;
    this.fechaSeleccionada = '';
    this.mensajeErrorArchivo = '';
  }
}
