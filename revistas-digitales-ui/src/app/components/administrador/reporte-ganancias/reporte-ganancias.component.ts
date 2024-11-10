import { Component, OnInit } from '@angular/core';
import { AdministradorService } from '../../../services/administrador/administrador.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-reporte-ganancias',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './reporte-ganancias.component.html',
  styleUrls: ['./reporte-ganancias.component.css']
})
export class ReporteGananciasComponent implements OnInit {

  fechaInicio: string = '';
  fechaFin: string = '';
  reporte: any = null;
  mostrarError: boolean = false;

  constructor(private ddministradorService: AdministradorService) { }

  ngOnInit(): void {
  }

  obtenerReporte(): void {
    if (this.fechaInicio === '' || this.fechaFin === '') {
      this.mostrarError = true; // Mostrar el mensaje de error si no se ingresaron las fechas
      return;
    }

    this.ddministradorService.obtenerReporteGanancias(this.fechaInicio, this.fechaFin).subscribe(
      (data) => {
        this.reporte = data;
        this.mostrarError = false;
      },
      (error) => {
        console.error('Error al obtener el reporte', error);
      }
    );
  }
}
