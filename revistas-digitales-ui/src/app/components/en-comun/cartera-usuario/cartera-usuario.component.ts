import { Component, OnInit } from '@angular/core';
import { CarteraService } from '../../../services/cartera/cartera.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-cartera-usuario',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './cartera-usuario.component.html',
  styleUrl: './cartera-usuario.component.css'
})
export class CarteraUsuarioComponent implements OnInit {
  saldoCartera: number = 0;
  cantidadRecarga: number | null = null;
  mensaje: string = ''; 

  constructor(private carteraService: CarteraService) {}

  ngOnInit(): void {
    this.obtenerSaldo();
  }

  obtenerSaldo(): void {
    this.carteraService.obtenerSaldo().subscribe(
      (saldo) => {
        this.saldoCartera = saldo;
      },
      (error) => {
        console.error('Error al obtener el saldo', error);
      }
    );
  }
  
  recargarCartera(): void {
    if (this.cantidadRecarga) {
      this.carteraService.recargarSaldo(this.cantidadRecarga).subscribe(
        response => {
          console.log('Cartera recargada exitosamente:', response);
          this.mensaje = 'Cartera recargada exitosamente';
          this.obtenerSaldo();
          this.cantidadRecarga = null; // Limpiar el campo de cantidad
        },
        error => {
          console.error('Error al recargar la cartera:', error);
          this.mensaje = 'Error al recargar la cartera. Intente nuevamente.';
        }
      );
    }
  }

}
