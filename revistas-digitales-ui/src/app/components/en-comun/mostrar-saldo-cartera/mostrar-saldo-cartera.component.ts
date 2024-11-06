import { Component, OnInit } from '@angular/core';
import { CarteraService } from '../../../services/cartera/cartera.service';

@Component({
  selector: 'app-mostrar-saldo-cartera',
  standalone: true,
  imports: [],
  templateUrl: './mostrar-saldo-cartera.component.html',
  styleUrl: './mostrar-saldo-cartera.component.css'
})
export class MostrarSaldoCarteraComponent implements OnInit {

  saldoCartera: number = 0;
  
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

}
