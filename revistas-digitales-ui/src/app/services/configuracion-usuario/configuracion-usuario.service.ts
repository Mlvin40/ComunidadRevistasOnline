import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RestConstants } from '../rest-constants';
import { jwtDecode } from 'jwt-decode';
import { UsuariosService } from '../usuarios/usuarios.service';
import { Usuario } from '../../entidades/Usuario';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfiguracionUsuarioService {

  private apiUrl: string;

  constructor(private http: HttpClient, private restConstants: RestConstants, private usuarioService: UsuariosService) {
    this.apiUrl = `${this.restConstants.getApiURL()}/configuracion-usuario`; // URL del backend para usuarios
  }
  obtenerNombreUsuarioDesdeToken(): string | null {
    const token: string | null = this.usuarioService.obtenerToken();
    if (!token) {
      console.error("No se encontró el token");
      return null; 
    }

    try {
      const decodedToken: { sub?: string } = jwtDecode(token);
      return decodedToken.sub ?? null; // Retorna el nombre de usuario o null si no existe
    } catch (error) {
      console.error("Token inválido", error);
      return null;
    }
  }
  
  obtenerDatosUsuario(nombreUsuario: string): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.apiUrl}/obtener-datos-usuario`);
  }
  
}
