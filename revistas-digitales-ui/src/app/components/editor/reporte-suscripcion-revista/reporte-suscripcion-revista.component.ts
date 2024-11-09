import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReportesEditorService } from '../../../services/reportes-editor/reportes-editor.service';
import { Suscripcion } from '../../../entidades/Suscripcion';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-reporte-suscripcion-revista',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './reporte-suscripcion-revista.component.html',
  styleUrls: ['./reporte-suscripcion-revista.component.css'],
})
export class ReporteSuscripcionRevistaComponent implements OnInit {
  suscripciones: Suscripcion[] = [];
  nombreRevista: string = ''; // Almacena el nombre de la revista para filtrar

  constructor(private reportesEditorService: ReportesEditorService) {}

  ngOnInit(): void {
    // Cargar todas las suscripciones al inicio
    this.cargarSuscripciones();
  }

  cargarSuscripciones(): void {
    this.reportesEditorService.obtenerSuscripciones().subscribe(
      (suscripciones) => {
        this.suscripciones = suscripciones;
      },
      (error) => {
        console.error('Error al cargar las suscripciones:', error);
      }
    );
  }

  // Método para filtrar suscripciones por nombre de revista
  filtrarSuscripciones(): void {
    if (this.nombreRevista.trim() === '') {
      // Si no se ha ingresado nombre de revista, recargar todas las suscripciones
      this.cargarSuscripciones();
      return;
    }

    // Llamar al servicio para obtener las suscripciones filtradas por nombre de revista
    this.reportesEditorService.obtenerSuscripcionesPorRevista(this.nombreRevista).subscribe(
      (suscripciones) => {
        this.suscripciones = suscripciones;
      },
      (error) => {
        console.error('Error al obtener suscripciones filtradas:', error);
      }
    );
  }
}