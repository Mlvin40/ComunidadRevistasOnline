import { Injectable } from '@angular/core';
import { Anuncio } from '../../../entidades/Anuncio';

@Injectable({
  providedIn: 'root'
})
export class AnuncioTemporalService {
  
  private anuncioSeleccionado: Anuncio | null = null;

  setAnuncio(anuncio: Anuncio) {
    this.anuncioSeleccionado = anuncio;
  }

  getAnuncio(): Anuncio | null {
    return this.anuncioSeleccionado;
  }

  clearAnuncio() {
    this.anuncioSeleccionado = null;
  }
}
