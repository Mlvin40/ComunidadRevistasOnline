import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RestConstants } from '../rest-constants';
import { UsuariosService } from '../usuarios/usuarios.service';
import { Observable } from 'rxjs';
import { Comentario } from '../../entidades/Comentario';
import { Suscripcion } from '../../entidades/Suscripcion';
import { Revista } from '../../entidades/Revista';

@Injectable({
  providedIn: 'root'
})
export class ReportesEditorService {
  
  private apiUrl: string;

  constructor(private http: HttpClient, private restConstants: RestConstants, private usuarioService: UsuariosService) {
  this.apiUrl = `${this.restConstants.getApiURL()}/reportesEditor`; // URL del backend para usuarios
}

  obtenerComentarios(): Observable<Comentario[]> {
    return this.http.get<Comentario[]>(this.apiUrl+"/obtenerComentarios");
  }

  // Método para obtener los comentarios filtrados por nombre de revista
  obtenerComentariosPorRevista(nombreRevista: string): Observable<Comentario[]> {
    return this.http.get<Comentario[]>(`${this.apiUrl}/obtenerComentariosFiltro`, {
      params: { nombreRevista: nombreRevista },
    });
  }

  obtenerSuscripciones(): Observable<Suscripcion[]> {
    return this.http.get<Suscripcion[]>(`${this.apiUrl}/obtenerSuscripciones`);
  }


  obtenerSuscripcionesPorRevista(nombreRevista: string): Observable<Suscripcion[]> {
    return this.http.get<Suscripcion[]>(`${this.apiUrl}/obtenerSuscripcionesFiltro`, {
      params: { nombreRevista: nombreRevista },
    });
  }

  obtenerTop5RevistasMasGustadas(): Observable<Revista[]> {
    return this.http.get<Revista[]>(`${this.apiUrl}/revistasMasGustadas`);
  }
  
}
