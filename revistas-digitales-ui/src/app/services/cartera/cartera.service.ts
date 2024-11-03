import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RestConstants } from '../rest-constants';
import { catchError, Observable, throwError } from 'rxjs';
import { UsuariosService } from '../usuarios/usuarios.service';

@Injectable({
  providedIn: 'root'
})
export class CarteraService {

  private apiUrl: string;
  
  constructor(private http: HttpClient, private restConstants: RestConstants, private usuarioService: UsuariosService) {
    this.apiUrl = `${this.restConstants.getApiURL()}/carteras`; // URL del backend para usuarios
  }

  obtenerSaldo(): Observable<number> {
    const nombreUsuario = this.usuarioService.obtenerNombreUsuarioDesdeToken();
    return this.http.get<number>(`${this.apiUrl}/${nombreUsuario}`); // Asegúrate de que esta URL sea correcta.
}

recargarSaldo(cantidadRecarga: number): Observable<any> {
  const nombreUsuario = this.usuarioService.obtenerNombreUsuarioDesdeToken();
  const rol = this.usuarioService.obtenerRolDesdeToken();

  const params = new HttpParams().set('cantidadRecarga', cantidadRecarga.toString());

  return this.http.post(`${this.apiUrl}/${nombreUsuario}/${rol}`, params.toString(), {
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
  }).pipe(
    catchError(error => {
      // Manejo del error
      console.error('Error al recargar la cartera', error);
      return throwError('Error en la recarga de cartera, intente nuevamente.');
    })
  );
}

}
