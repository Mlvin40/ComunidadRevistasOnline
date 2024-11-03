import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { catchError, map, Observable, throwError } from 'rxjs';
import { RestConstants } from '../rest-constants'; // Asegúrate de que la ruta sea correcta
import { jwtDecode} from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  private apiUrl: string;

  constructor(private http: HttpClient, private restConstants: RestConstants) {
    this.apiUrl = `${this.restConstants.getApiURL()}/usuarios`; // URL del backend para usuarios
  }

  // Método para registrar un nuevo usuario
  registrarUsuario(usuario: any): Observable<any> {
    console.log('Registrando usuario:', usuario);
    return this.http.post(`${this.apiUrl}/registro`, usuario);
  }

  // Método para iniciar sesión
  loginUsuario(loginData: any): Observable<string> {
    return this.http.post<any>(`${this.apiUrl}/login`, loginData).pipe(
      map(response => {
        const token = response.token;
        if (token) {
          this.guardarToken(token); // Guarda el token en localStorage
          return token; // Devuelve el token
        } else {
          throw new Error('Token no encontrado en la respuesta desde servicio usuarios');
        }
      }),
      catchError(error => {
        console.error('Error en el login:', error);
        return throwError(() => new Error('Error al iniciar sesión, verifique sus credenciales.'));
      })
    );
  }

  // *** Métodos para manejar el token en localStorage ***

  // Guardar el token en localStorage
  guardarToken(token: string): void {
    localStorage.setItem('token', token);
  }

  // Obtener el token desde localStorage
  obtenerToken(): string | null {
    return localStorage.getItem('token');
  }

  // Eliminar el token de localStorage
  eliminarToken(): void {
    localStorage.removeItem('token');
  }

  permisosAdministrador(): boolean {
    return this.tienePermiso('ADMINISTRADOR');
  }

  permisosEditor(): boolean {
    return this.tienePermiso('EDITOR');
  }

  permisosAnunciante(): boolean {
    return this.tienePermiso('ANUNCIANTE');
  }

  permisosSuscriptor(): boolean {
    return this.tienePermiso('SUSCRIPTOR');
  }

  private tienePermiso(rol: string): boolean {
    const token = this.obtenerToken();
    console.log('VIENDOLO DESDE EL SERVICIO');
    console.log('Rol:', rol);
    console.log('Token:', token);
    
    if (token) {
      const decodedToken: any = jwtDecode(token); // Decodifica el token
      console.log('Rol en el token:', decodedToken.rol);
      console.log('Rol esperado:', rol);
      return decodedToken.rol === rol; // Verifica el rol
    }
    console.log('No hay token o no tiene permisos para acceder a esta página');
    return false; // Si no hay token, no tiene permisos de ningún tipo de usuario

  }

  // Método para obtener el nombre de usuario desde el token
  obtenerNombreUsuarioDesdeToken() {
    const token = this.obtenerToken();
    if (!token) {
      console.error('No se encontró el token');
      return null;
    }

    try {
      const decodedToken: { sub?: string } = jwtDecode(token);
      return decodedToken.sub ?? null;
    } catch (error) {
      console.error('Token inválido', error);
      return null;
    }
  }
  obtenerRolDesdeToken() {
    const token = this.obtenerToken();
    if (!token) {
      console.error('No se encontró el token');
      return null;
    }

    try {
      const decodedToken: { rol?: string } = jwtDecode(token);
      return decodedToken.rol ?? null;
    } catch (error) {
      console.error('Token inválido', error);
      return null;
    }
  }

}