import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReportesEditorService } from '../../../services/reportes-editor/reportes-editor.service';
import { Comentario } from '../../../entidades/Comentario';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AnuncioIndividualComponent } from "../../anunciante/anuncio-individual/anuncio-individual.component";

@Component({
  selector: 'app-reporte-comentarios-revista',
  standalone: true,
  imports: [CommonModule, FormsModule, AnuncioIndividualComponent],
  templateUrl: './reporte-comentarios-revista.component.html',
  styleUrls: ['./reporte-comentarios-revista.component.css'],
})
export class ReporteComentariosRevistaComponent implements OnInit {
  comentarios: Comentario[] = [];
  nombreRevista: string = ''; // Para almacenar el nombre de la revista a filtrar
  rutaActual!: string;

  constructor(private reportesEditorService: ReportesEditorService, private router: Router) {}

  ngOnInit(): void {
    this.rutaActual = this.router.url;
    this.cargarComentarios();
  }

  cargarComentarios(): void {
    this.reportesEditorService.obtenerComentarios().subscribe(
      (comentarios) => {
        this.comentarios = comentarios;
      },
      (error) => {
        console.error('Error al cargar los comentarios:', error);
      }
    );
  }

  
  // Método para filtrar comentarios por nombre de revista
  filtrarComentarios(): void {
    if (this.nombreRevista.trim() === '') {
      // Si no se ha ingresado nombre de revista, recargar todos los comentarios
      this.cargarComentarios();
      return;
    }

    this.reportesEditorService.obtenerComentariosPorRevista(this.nombreRevista).subscribe(
      (comentarios) => {
        this.comentarios = comentarios;
      },
      (error) => {
        console.error('Error al obtener comentarios filtrados:', error);
      }
    );
  }
}
