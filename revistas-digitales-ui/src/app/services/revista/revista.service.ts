import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RestConstants } from '../rest-constants';
import { UsuariosService } from '../usuarios/usuarios.service';
import { Revista } from '../../entidades/Revista';
import { RevistaEditable } from '../../entidades/RevistaEditable';

@Injectable({
  providedIn: 'root',
})
export class RevistaService {

  private apiUrl: string;

  constructor(private http: HttpClient, private restConstants: RestConstants, private usuarioService: UsuariosService) {
  this.apiUrl = `${this.restConstants.getApiURL()}/revistas`; // URL del backend para usuarios
}


crearRevista(revistaData: FormData): Observable<any> {
  return this.http.post<any>(`${this.apiUrl}/crearRevista`, revistaData);
}


obtenerRevistasPorAutor(): Observable<Revista[]> {
  // guardar el nombre del autor en el token
  const autorActual = this.usuarioService.obtenerNombreUsuarioDesdeToken();
  return this.http.get<Revista[]>(`${this.apiUrl}/obtenerRevistasPorAutor?autor=${autorActual}`);
}


obtenerRevistaPorNombre(nombre: string): Observable<Revista> {
  return this.http.get<Revista>(`${this.apiUrl}/obtenerRevistaPorNombre?nombre=${nombre}`);
}

actualizarRevista(revista: RevistaEditable): Observable<void> {
  return this.http.put<void>(`${this.apiUrl}/actualizarRevista`, revista);
}

}
