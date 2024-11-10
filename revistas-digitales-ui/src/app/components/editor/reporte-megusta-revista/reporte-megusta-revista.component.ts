import { Component, OnInit } from '@angular/core';
import { ReportesEditorService } from '../../../services/reportes-editor/reportes-editor.service';
import { CommonModule } from '@angular/common';
import { Revista } from '../../../entidades/Revista';
import { Router } from '@angular/router';
import { AnuncioIndividualComponent } from "../../anunciante/anuncio-individual/anuncio-individual.component";
import { EditorHomeComponent } from "../editor-home/editor-home.component";

@Component({
  selector: 'app-reporte-megusta-revista',
  standalone: true,
  imports: [CommonModule, AnuncioIndividualComponent, EditorHomeComponent],
  templateUrl: './reporte-megusta-revista.component.html',
  styleUrls: ['./reporte-megusta-revista.component.css'],
})
export class ReporteMegustaRevistaComponent implements OnInit {
  revistas: Revista[] = [];
  rutaActual!: string;

  constructor(private reportesEditorService: ReportesEditorService, private router: Router) {}

  ngOnInit(): void {
    this.rutaActual = this.router.url;
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
