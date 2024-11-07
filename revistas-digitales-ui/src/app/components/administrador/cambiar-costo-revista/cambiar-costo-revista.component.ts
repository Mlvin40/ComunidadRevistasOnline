import { Component, OnInit } from '@angular/core';
import { AdministradorService } from '../../../services/administrador/administrador.service';
import { Revista } from '../../../entidades/Revista';
import { CommonModule } from '@angular/common'; // Cambiar BrowserModule por CommonModule
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-cambiar-costo-revista',
  standalone: true,
  imports: [CommonModule, FormsModule], // Usa CommonModule en lugar de BrowserModule
  templateUrl: './cambiar-costo-revista.component.html',
  styleUrls: ['./cambiar-costo-revista.component.css'],
})
export class CambiarCostoRevistaComponent implements OnInit {
  revistas: Revista[] = [];
  mensaje: string = '';
  nuevosCostos: { [nombreRevista: string]: number } = {}; // Almacena el nuevo costo por revista

  constructor(private administradorService: AdministradorService) {}

  ngOnInit(): void {
    this.cargarRevistas();
  }

  cargarRevistas(): void {
    this.administradorService.obtenerRevistas().subscribe(
      (revistas) => (this.revistas = revistas),
      (error) => (this.mensaje = 'Error al cargar las revistas.')
    );
  }

  actualizarPrecio(revista: Revista): void {
    const nuevoCosto = this.nuevosCostos[revista.nombre];
  
    if (nuevoCosto == null || nuevoCosto < 0) {
      this.mensaje = 'Debe ingresar un precio válido.';
      return;
    }
    
    this.administradorService.actualizarPrecioRevista(revista.nombre, nuevoCosto).subscribe(
      (response) => {
        this.mensaje = 'Precio actualizado exitosamente.';
        this.cargarRevistas(); // Recargar las revistas para reflejar el nuevo precio
      },
      (error) => {
        this.mensaje = 'Error al actualizar el precio.';
        console.error(error); // Puedes agregar un log para ver más detalles del error
      }
    );
  }
  
}
