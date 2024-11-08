import { Component, OnInit } from '@angular/core';
import { AdministradorService } from '../../../services/administrador/administrador.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-costo-global-revista',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './costo-global-revista.component.html',
  styleUrls: ['./costo-global-revista.component.css'],
})
export class CostoGlobalRevistaComponent implements OnInit {
  precioActual: number = 0;
  nuevoPrecio: number = 0;
  mensaje: string = '';

  constructor(private administradorService: AdministradorService) {}

  ngOnInit(): void {
    this.cargarCostoGlobal();
  }

  cargarCostoGlobal(): void {
    this.administradorService.obtenerCostoGlobalRevista().subscribe(
      (precio) => (this.precioActual = precio),
      (error) => (this.mensaje = 'Error al cargar el precio actual.')
    );
  }

  actualizarCostoGlobal(): void {
    if (this.nuevoPrecio < 0) {
      this.mensaje = 'El precio no puede ser negativo.';
      return;
    }

    this.administradorService.actualizarCostoGlobalRevista(this.nuevoPrecio).subscribe(
      () => {
        this.mensaje = 'Precio actualizado exitosamente.';
        this.cargarCostoGlobal(); // Recargar el precio actual
      },
      (error) => {
        this.mensaje = 'Error al actualizar el precio.';
      }
    );
  }
}
