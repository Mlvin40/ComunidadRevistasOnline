import { Component, OnInit } from '@angular/core';
import { AnuncioService } from '../../../services/anuncio/anuncio.service';
import { Anuncio } from '../../../entidades/Anuncio';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';


@Component({
  selector: 'app-ver-anuncios',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './ver-anuncios.component.html',
  styleUrls: ['./ver-anuncios.component.css']
})
export class VerAnunciosComponent implements OnInit {
  anuncios: Anuncio[] = [];

  constructor(private anuncioService: AnuncioService, private router: Router) {}

  ngOnInit(): void {
    this.anuncioService.obtenerAnunciosPorAnunciante().subscribe(
      (data) => {
        this.anuncios = data;
      },
      (error) => {
        console.error('Error al obtener los anuncios:', error);
      }
    );
  }

  //Por medio del id se cargara un anuncio desde la base de datos
  editarAnuncio(anuncio: Anuncio): void {
    this.router.navigate(['/editarAnuncio', anuncio.idAnuncio]);
}

}

