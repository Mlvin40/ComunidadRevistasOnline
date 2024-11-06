import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RestConstants } from '../rest-constants';
import { UsuariosService } from '../usuarios/usuarios.service';
import { Revista } from '../../entidades/Revista';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SuscriptorService {

  private apiUrl: string;

  constructor(private http: HttpClient, private restConstants: RestConstants, private usuarioService: UsuariosService) {
  this.apiUrl = `${this.restConstants.getApiURL()}/suscriptorFunciones`; // URL del backend para usuarios
}


// Este metodo sirve para cargar todas las revistas disponibles en la base de datos y es usado por el suscriptor
  obtenerRevistasDisponibles(): Observable<Revista[]> {
    const params = new HttpParams().set('usuario', this.usuarioService.obtenerNombreUsuarioDesdeToken() || 'Null');
    return this.http.get<Revista[]>(`${this.apiUrl}/revistasDisponibles`, { params });
  }


  suscribirseRevista(revistaData: FormData): Observable<any> {

    revistaData.append('usuario', this.usuarioService.obtenerNombreUsuarioDesdeToken() || 'Null');
    return this.http.post<any>(`${this.apiUrl}/suscribirseRevista`, revistaData);
  }

  obtenerRevistasSuscriptor(): Observable<Revista[]> {
    const params = new HttpParams().set('usuario', this.usuarioService.obtenerNombreUsuarioDesdeToken() || 'Null');
    return this.http.get<Revista[]>(`${this.apiUrl}/revistasSuscriptor`, { params });
  }

  //Metodo para dar like a una revista suscrita
  darLikeRevista(revistaData: FormData): Observable<any> {
      revistaData.append('nombreSuscriptor', this.usuarioService.obtenerNombreUsuarioDesdeToken() || 'Null');
      return this.http.post<any>(`${this.apiUrl}/darLike`, revistaData);
    }

    //Metodo para dar like a una revista suscrita
    comentarRevista(revistaData: FormData): Observable<any> {
      revistaData.append('nombreSuscriptor', this.usuarioService.obtenerNombreUsuarioDesdeToken() || 'Null');
      return this.http.post<any>(`${this.apiUrl}/comentarRevista`, revistaData);
    }

}
