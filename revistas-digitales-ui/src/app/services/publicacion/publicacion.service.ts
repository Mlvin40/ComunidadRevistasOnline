import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RestConstants } from '../rest-constants';
import { UsuariosService } from '../usuarios/usuarios.service';
import { Observable } from 'rxjs';
import { Publicacion } from '../../entidades/Publicacion';

@Injectable({
  providedIn: 'root'
})
export class PublicacionService {



  private apiUrl: string;
  constructor(private http: HttpClient, private restConstants: RestConstants, private usuarioService: UsuariosService) {
    this.apiUrl = `${this.restConstants.getApiURL()}/publicaciones`; // URL del backend para usuarios
  }


  realizarPublicacion(nombreRevista: string, descripcion: string, archivoPDF: File, fechaPublicacion: string): Observable<void> {
    const autor = this.usuarioService.obtenerNombreUsuarioDesdeToken() || 'null';
    
    const formData = new FormData();
    formData.append('autor', autor);
    formData.append('nombreRevista', nombreRevista);
    formData.append('descripcion', descripcion);
    formData.append('archivoPDF', archivoPDF);
    formData.append('fechaPublicacion', fechaPublicacion);
    
    return this.http.post<void>(`${this.apiUrl}/realizarPublicacion`, formData);
  }

  
  obtenerPublicacionesPorRevista(nombreRevista: string): Observable<Publicacion[]> {
    return this.http.get<Publicacion[]>(`${this.apiUrl}/listaPublicaciones?revista=${nombreRevista}`);
  }
  



}
