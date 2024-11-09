import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UsuariosService } from '../usuarios/usuarios.service';
import { RestConstants } from '../rest-constants';
import { Revista } from '../../entidades/Revista';
import { Observable } from 'rxjs';
import { RevistaPopular } from '../../entidades/RevistaPopular';

@Injectable({
  providedIn: 'root'
})
export class ReportesAdministradorService {
  
  private apiUrl: string;

  constructor(private http: HttpClient, private restConstants: RestConstants, private usuarioService: UsuariosService) {
  this.apiUrl = `${this.restConstants.getApiURL()}/reportesAdministrador`; // URL del backend
}

obtenerRevistasMasComentadas(): Observable<Revista[]> {
  return this.http.get<Revista[]>(this.apiUrl+"/revistasMasComentadas");
}


obtenerRevistasPopulares(): Observable<RevistaPopular[]> {
  return this.http.get<RevistaPopular[]>(this.apiUrl+"/revistasPopulares");
}

}
