import { Component, OnInit } from '@angular/core';
import { ReporteEfectividadAnuncio } from '../../../entidades/reporte/ReporteEfectividadAnuncio';
import { AdministradorService } from '../../../services/administrador/administrador.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-efectividad-anuncio',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './efectividad-anuncio.component.html',
  styleUrls: ['./efectividad-anuncio.component.css']
})
export class EfectividadAnuncioComponent implements OnInit {

  reportes: ReporteEfectividadAnuncio[] = [];
  fechaInicio: string = '';
  fechaFin: string = '';

  constructor(private administradorService: AdministradorService) {}

  ngOnInit(): void {
    // Establecer fechas predeterminadas a la fecha actual
    const fechaHoy = new Date();
    this.fechaInicio = fechaHoy.toISOString().split('T')[0]; // Formato YYYY-MM-DD
    this.fechaFin = fechaHoy.toISOString().split('T')[0]; // Formato YYYY-MM-DD
  }

  // Método para obtener el reporte de efectividad
  obtenerReporte(): void {
    if (!this.fechaInicio || !this.fechaFin) {
      console.error("Las fechas son obligatorias");
      return;
    }
    this.administradorService.obtenerReporteEfectividad(this.fechaInicio, this.fechaFin)
      .subscribe(
        (data) => {
          this.reportes = data;
        },
        (error) => {
          console.error('Error obteniendo el reporte de efectividad', error);
        }
      );
  }
}
