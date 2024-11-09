import { Component, OnInit } from '@angular/core';
import { ReportesEditorService } from '../../../services/reportes-editor/reportes-editor.service';
import { CommonModule } from '@angular/common';
import { Revista } from '../../../entidades/Revista';

@Component({
  selector: 'app-reporte-megusta-revista',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './reporte-megusta-revista.component.html',
  styleUrls: ['./reporte-megusta-revista.component.css'],
})
export class ReporteMegustaRevistaComponent implements OnInit {
  revistas: Revista[] = [];

  constructor(private reportesEditorService: ReportesEditorService) {}

  ngOnInit(): void {
    this.obtenerRevistasMasGustadas();
  }

  obtenerRevistasMasGustadas(): void {
    this.reportesEditorService.obtenerTop5RevistasMasGustadas().subscribe(
      (revistas) => {
        this.revistas = revistas;
      },
      (error) => {
        console.error('Error al obtener las revistas más gustadas:', error);
      }
    );
  }
}
