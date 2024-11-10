import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RevistaService } from '../../../services/revista/revista.service';
import { Revista } from '../../../entidades/Revista';
import { Router } from '@angular/router';
import { EditorHomeComponent } from "../editor-home/editor-home.component";
import { AnuncioIndividualComponent } from "../../anunciante/anuncio-individual/anuncio-individual.component";

@Component({
  selector: 'app-mis-revistas',
  standalone: true,
  imports: [CommonModule, EditorHomeComponent, AnuncioIndividualComponent],
  templateUrl: './mis-revistas.component.html',
  styleUrls: ['./mis-revistas.component.css']
})
export class MisRevistasComponent implements OnInit {
  revistas: Revista[] = [];
  mensaje: string = '';

  rutaActual!: string;
  constructor(private revistaService: RevistaService, private router: Router) {
    
  }

  ngOnInit(): void {
    this.rutaActual = this.router.url;
    this.revistaService.obtenerRevistasPorAutor().subscribe(
      (revistas) => this.revistas = revistas,
      (error) => this.mensaje = 'Error al cargar las revistas'
    );
  }

  editarRevista(nombre: string): void {
    // Redirigir al usuario al componente de edición, pasando el nombre de la revista
    this.router.navigate(['/editarRevista', nombre]);
  }

  realizarPublicacion(nombre: string): void {
    // Redirigir al usuario al componente de publicación, pasando el nombre de la revista
    this.router.navigate(['/publicar', nombre]);
  }
  
  verPublicaciones(nombre: string): void {
    // Redirigir al usuario al componente de visualización de publicaciones, pasando el nombre de la revista
    this.router.navigate(['/verPublicaciones', nombre]);
  }
}
