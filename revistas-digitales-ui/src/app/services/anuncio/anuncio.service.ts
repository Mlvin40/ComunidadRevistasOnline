import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RestConstants } from '../rest-constants';
import { UsuariosService } from '../usuarios/usuarios.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AnuncioService {

  private apiUrl: string;
  
  constructor(private http: HttpClient, private restConstants: RestConstants, private usuarioService: UsuariosService) {
    this.apiUrl = `${this.restConstants.getApiURL()}/anuncios`; // URL del backend para usuarios
  }

  // Este es para que el usuario anunciante pueda comprar anuncios
  obtenerPreciosAnuncios() {
    return this.http.get<{ texto: number; textoImagen: number; video: number }>(`${this.apiUrl}/preciosAnuncio`);
  }

    // Método para comprar un anuncio
    comprarAnuncio(anuncioData: any): Observable<any> {
      const formData = new FormData();

      formData.append('nombreAnunciante', this.usuarioService.obtenerNombreUsuarioDesdeToken() || 'null');
      formData.append('tipoAnuncio', anuncioData.tipoAnuncio);
      formData.append('contenidoTexto', anuncioData.contenidoTexto);
      if (anuncioData.imagen) {
        formData.append('imagen', anuncioData.imagen);
      }
      formData.append('urlVideo', anuncioData.urlVideo);
      formData.append('duracion', anuncioData.duracion.toString());
      formData.append('fechaInicio', anuncioData.fechaInicio);
      formData.append('totalAPagar', anuncioData.totalAPagar.toString());
  
      return this.http.post<any>(`${this.apiUrl}/comprarAnuncio`, formData);
    }

  obtenerAnuncios() {
    throw new Error('Method not implemented.');
  }
}
