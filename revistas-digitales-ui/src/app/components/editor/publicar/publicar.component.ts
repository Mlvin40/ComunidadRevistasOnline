import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PublicacionService } from '../../../services/publicacion/publicacion.service';
import { CommonModule } from '@angular/common'; // Importa CommonModule

@Component({
  selector: 'app-publicar',
  standalone: true,
  imports: [CommonModule, FormsModule], // Asegúrate de incluir CommonModule aquí
  templateUrl: './publicar.component.html',
  styleUrls: ['./publicar.component.css']
})
export class PublicarComponent implements OnInit {
  nombreRevista: string = '';
  descripcion: string = '';
  archivoPDF: File | null = null;
  mensajeErrorArchivo: string = '';
  mensajeExito: string = ''; // Variable para el mensaje de éxito

  constructor(private route: ActivatedRoute, private publicacionService: PublicacionService) {}
  
  ngOnInit(): void {
    this.nombreRevista = this.route.snapshot.paramMap.get('nombre') || 'Sin nombre';
  }

  onFileSelected(event: Event): void {
    const target = event.target as HTMLInputElement;
    if (target.files && target.files.length > 0) {
      const file = target.files[0];
      
      // Verificar si el archivo es PDF
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
    if (this.nombreRevista && this.archivoPDF && this.descripcion) {
      this.publicacionService.realizarPublicacion(this.nombreRevista, this.descripcion, this.archivoPDF)
        .subscribe(
          () => {
            this.mensajeExito = 'Publicación realizada con éxito'; // Mensaje de éxito
            this.reiniciarFormulario(); // Reiniciar los campos del formulario
          },
          error => {
            this.mensajeExito = ''; // Limpiar mensaje de éxito
            console.error('Error al realizar la publicación', error);
            this.mensajeErrorArchivo = 'Error al realizar la publicación. Intente nuevamente.'; 
          }
        );
    } else {
      this.mensajeErrorArchivo = 'Falta descripción, archivo PDF o nombre de la revista.';
    }
  }

  reiniciarFormulario(): void {
    this.descripcion = '';
    this.archivoPDF = null;
    this.mensajeErrorArchivo = '';
  }
}
