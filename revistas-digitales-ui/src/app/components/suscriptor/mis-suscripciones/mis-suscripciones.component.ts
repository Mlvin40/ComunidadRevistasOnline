import { Component, OnInit } from '@angular/core';
import { Revista } from '../../../entidades/Revista';
import { SuscriptorService } from '../../../services/suscriptor/suscriptor.service';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AnuncioIndividualComponent } from "../../anunciante/anuncio-individual/anuncio-individual.component";
import { SuscriptorHomeComponent } from "../suscriptor-home/suscriptor-home.component";

@Component({
  selector: 'app-mis-suscripciones',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule, AnuncioIndividualComponent, SuscriptorHomeComponent],
  templateUrl: './mis-suscripciones.component.html',
  styleUrls: ['./mis-suscripciones.component.css']
})
export class MisSuscripcionesComponent implements OnInit {
  revistas: Revista[] = [];
  mensaje: string = '';
  comentarioText: { [revistaNombre: string]: string } = {};  // Cambié a un objeto con clave por revista
  rutaActual!: string; 

  constructor(private suscriptorService: SuscriptorService, private router: Router) { }

  ngOnInit(): void {
    this.obtenerRevistas();
    this.rutaActual = this.router.url;
  }

  obtenerRevistas(): void {
    this.suscriptorService.obtenerRevistasSuscriptor().subscribe(
      (revistas: Revista[]) => {
        this.revistas = revistas;
        // Inicializar el objeto comentarioText con claves por revista
        this.revistas.forEach(revista => {
          if (!this.comentarioText[revista.nombre]) {
            this.comentarioText[revista.nombre] = '';
          }
        });
      },
      (error) => {
        this.mensaje = 'Hubo un error al cargar tus suscripciones.';
      }
    );
  }

  verPublicaciones(nombre: string): void {
    this.router.navigate(['/verPublicaciones', nombre]);
  }

  darLike(nombreRevista: string): void {
    const revistaData = new FormData();
    revistaData.append('nombreRevista', nombreRevista);
    this.suscriptorService.darLikeRevista(revistaData).subscribe(
      (response) => {
        console.log('Like registrado con éxito:', response);
        this.obtenerRevistas();
      },
      (error) => {
        console.error('Error al dar like:', error);
        this.mensaje = 'Hubo un error al registrar el like.';
      }
    );
  }

  comentar(nombreRevista: string): void {
    const textoComentario = this.comentarioText[nombreRevista];
    const revistaData = new FormData();
    revistaData.append('nombreRevista', nombreRevista);
    revistaData.append('textoComentario', textoComentario);

    this.suscriptorService.comentarRevista(revistaData).subscribe(
      (response) => {
        console.log('Comentario agregado:', response);
        this.obtenerRevistas();
        this.comentarioText[nombreRevista] = '';  // Limpiar el comentario después de agregarlo
      },
      (error) => {
        console.error('Error al agregar comentario:', error);
        this.mensaje = 'Hubo un error al agregar el comentario.';
      }
    );
  }
}
