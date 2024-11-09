import { Component, OnInit } from '@angular/core';
import { ReportesAdministradorService } from '../../../services/reportes-administrador/reportes-administrador.service';
import { RevistaPopular } from '../../../entidades/RevistaPopular';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-reporte-revistas-populares',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './reporte-revistas-populares.component.html',
  styleUrls: ['./reporte-revistas-populares.component.css']
})
export class ReporteRevistasPopularesComponent implements OnInit {
  revistasPopulares: RevistaPopular[] = [];

  constructor(private reporteAdministradorService: ReportesAdministradorService) {}

  ngOnInit(): void {
    this.reporteAdministradorService.obtenerRevistasPopulares().subscribe((revistas) => {
      console.log('Revistas recibidas:', revistas); // Para depuración
      this.revistasPopulares = revistas;
    });
  }
  
}
