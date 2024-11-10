import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RestConstants } from '../rest-constants';
import { UsuariosService } from '../usuarios/usuarios.service';
import { Observable } from 'rxjs';
import { Anuncio } from '../../entidades/Anuncio';

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


  // Método para obtener los anuncios en un arreglo anuncio
  obtenerAnuncios(): Observable<Anuncio[]> {
    return this.http.get<Anuncio[]>(`${this.apiUrl}/anunciosActivos`);
  }


  obtenerAnunciosPorAnunciante(): Observable<Anuncio[]> {
    const nombreAnunciante = this.usuarioService.obtenerNombreUsuarioDesdeToken() || 'null';
    return this.http.get<Anuncio[]>(`${this.apiUrl}/anunciosPorUsuario?nombreAnunciante=${nombreAnunciante}`);
  }

  // Método para obtener un anuncio por su ID
  obtenerAnuncioPorId(id: number): Observable<Anuncio> {
    return this.http.get<Anuncio>(`${this.apiUrl}/anuncioPorId/${id}`);
  }

  // Método para obtener un anuncio random de la base de datos
  obtenerAnuncioRandom(): Observable<Anuncio> {
    return this.http.get<Anuncio>(`${this.apiUrl}/anuncioRandom`);
  }

  actualizarAnuncio(anuncioData: any): Observable<any> {
    return this.http.put<Anuncio>(`${this.apiUrl}/actualizarAnuncio`, anuncioData);
  }

  //Para el reporte de anuncios efectivos
  guardarAnuncioMostrado(formData: FormData): Observable<any> {
    return this.http.post(`${this.apiUrl}/guardarAnuncioEfecivo`, formData);
  }

}
