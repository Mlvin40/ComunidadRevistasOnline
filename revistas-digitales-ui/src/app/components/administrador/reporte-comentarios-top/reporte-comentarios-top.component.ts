import { Component, OnInit } from '@angular/core';
import { ReportesAdministradorService } from '../../../services/reportes-administrador/reportes-administrador.service';
import { CommonModule } from '@angular/common';
import { Revista } from '../../../entidades/Revista';

@Component({
  selector: 'app-reporte-comentarios-top',
  standalone: true,
  templateUrl: './reporte-comentarios-top.component.html',
  styleUrls: ['./reporte-comentarios-top.component.css'],
  imports: [CommonModule]
})
export class ReporteComentariosTopComponent implements OnInit {
  revistas: Revista[] = [];

  constructor(private reporteAdministradorService: ReportesAdministradorService) {}

  ngOnInit(): void {
    this.reporteAdministradorService.obtenerRevistasMasComentadas().subscribe(
      (data) => {
        this.revistas = data;
      },
      (error) => {
        console.error('Error al obtener las revistas más comentadas:', error);
      }
    );
  }
}
