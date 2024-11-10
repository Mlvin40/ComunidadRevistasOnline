import { Component } from '@angular/core';
import { Revista } from '../../../entidades/Revista';
import { CommonModule } from '@angular/common';
import { SuscriptorService } from '../../../services/suscriptor/suscriptor.service';
import { Router } from '@angular/router';
import { AnuncioIndividualComponent } from "../../anunciante/anuncio-individual/anuncio-individual.component";

@Component({
  selector: 'app-ver-revistas-disponibles',
  standalone: true,
  imports: [CommonModule, AnuncioIndividualComponent],
  templateUrl: './ver-revistas-disponibles.component.html',
  styleUrl: './ver-revistas-disponibles.component.css'
})
export class VerRevistasDisponiblesComponent {

  revistas: Revista[] = [];
  mensaje: string = '';
  rutaActual!: string;

  constructor(private suscriptorService: SuscriptorService, private router: Router) {}

  ngOnInit(): void {
    this.rutaActual = this.router.url;

    this.suscriptorService.obtenerRevistasDisponibles().subscribe(
      (data) => this.revistas = data,
      (error) => this.mensaje = 'Error al cargar las revistas'
    );
  }
  
  suscribirse(nombreRevista: string, fechaSuscripcion: string): void {
    // Hacer un form data con la fecha de suscripcion y el nombre de la revista
    const revistaData = new FormData();
    revistaData.append('nombreRevista', nombreRevista);
    revistaData.append('fechaSuscripcion', fechaSuscripcion);

    this.suscriptorService.suscribirseRevista(revistaData).subscribe(
      () => this.mensaje = `Te has suscrito a ${nombreRevista} con éxito.`,
      () => this.mensaje = 'Error al suscribirse a la revista.'
    );
  }

  buscarPorCategoria(categoriaBuscar: string): void {
    this.suscriptorService.obtenerRevistasPorCategoria(categoriaBuscar).subscribe(
      (data) => this.revistas = data,
      (error) => this.mensaje = 'Error al cargar las revistas por categoría'
    );
  }  
}
