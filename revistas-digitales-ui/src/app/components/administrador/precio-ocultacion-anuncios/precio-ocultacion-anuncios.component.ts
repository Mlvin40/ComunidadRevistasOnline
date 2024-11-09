import { Component, OnInit } from '@angular/core';
import { AdministradorService } from '../../../services/administrador/administrador.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-precio-ocultacion-anuncios',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './precio-ocultacion-anuncios.component.html',
  styleUrl: './precio-ocultacion-anuncios.component.css'
})
export class PrecioOcultacionAnunciosComponent implements OnInit{


  precioActual: number = 0;
  nuevoPrecio: number = 0;
  mensaje: string = '';

  constructor(private administradorService: AdministradorService) {}

  ngOnInit(): void {
    this.obtenerCostoOcultacion();
  }

  obtenerCostoOcultacion(): void {
    this.administradorService.obtenerCostoOcultacionRevista().subscribe(
      (precio) => (this.precioActual = precio),
      (error) => (this.mensaje = 'Error al cargar el precio actual.')
    );
  }

  actualizarCostoOcultacion(): void {
    if (this.nuevoPrecio < 0) {
      this.mensaje = 'El precio no puede ser negativo.';
      return;
    }

    this.administradorService.actualizarCostoOcultacionRevista(this.nuevoPrecio).subscribe(
      () => {
        this.mensaje = 'Precio actualizado exitosamente.';
        this.obtenerCostoOcultacion(); // Recargar el precio actual
      },
      (error) => {
        this.mensaje = 'Error al actualizar el precio.';
      }
    );
  }

}
