import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { AdministradorService } from '../../../services/administrador/administrador.service';
import { CommonModule } from '@angular/common';
import { AnuncioComprado } from '../../../entidades/reporte/AnuncioComprado';

@Component({
  selector: 'app-reporte-anuncios-comprados',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './reporte-anuncios-comprados.component.html',
  styleUrls: ['./reporte-anuncios-comprados.component.css']
})
export class ReporteAnunciosCompradosComponent {
  anuncios: AnuncioComprado[] = [];
  filtroForm: FormGroup;
  mostrarError: boolean = false;

  constructor(private administradorService: AdministradorService, private fb: FormBuilder) {
    this.filtroForm = this.fb.group({
      fechaInicio: [''],
      fechaFin: [''],
      tipoAnuncio: ['']
    });
  }

  generarReporte() {
    const { fechaInicio, fechaFin, tipoAnuncio } = this.filtroForm.value;
      // Verificar si las fechas están vacías
  if (!fechaInicio || !fechaFin) {
    this.mostrarError = true; // Mostrar error si las fechas están vacías
    return;
  }

    this.administradorService.obtenerReporteAnuncios(fechaInicio, fechaFin, tipoAnuncio)
      .subscribe((anuncios) => {
        this.anuncios = anuncios;
        this.mostrarError = false;
      });
  }
}
