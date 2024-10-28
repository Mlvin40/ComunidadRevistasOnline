import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { FormsModule } from '@angular/forms'; 
import { UsuariosService } from '../../services/usuarios/usuarios.service';
import { Router, RouterLink} from '@angular/router';
import { jwtDecode} from 'jwt-decode';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  nombreUsuario: string = '';
  contrasena: string = '';
  mostrarContrasena: boolean = false;
  token: string  = ''; // Almacenar el token

  constructor(private usuariosService: UsuariosService, private router: Router) {}

  onLogin(form: any) {
    if (form.valid) {
      const credenciales = { nombreUsuario: this.nombreUsuario, contrasena: this.contrasena };
      
      this.usuariosService.loginUsuario(credenciales).subscribe({
        next: (token: string) => {
          console.log('Token recibido:', token);
          this.token = token; // Guardamos el token
  
          // Decodificar el token para obtener el rol
          const decodedToken: any = jwtDecode(token);
          const rol = decodedToken.rol; // Asegúrate de que el rol esté en el token
          console.log('Rol del usuario:', rol);
  

          console.log('Token guardado en localStorage:', localStorage.getItem('token'));
          console.log('si se ve este mensaje es que el token se guardo correctamente');
          // Redirigir al usuario según su rol


          this.redirigirSegunRol(rol);
        },
        error: (error) => {
          console.error('Error en el servicio de autenticación TS', error);
          // Muestra un mensaje de error al usuario, si es necesario
        }
      });
    }
  }
  
  redirigirSegunRol(rol: string) {
    switch (rol) {
      case 'EDITOR':
        this.router.navigate(['/editorHome']);
        break;
      case 'ADMINISTRADOR':
        this.router.navigate(['/adminHome']);
        break;
      case 'ANUNCIANTE':
        this.router.navigate(['/anuncianteHome']);
        break;
      case 'SUSCRIPTOR':
        this.router.navigate(['/suscriptorHome']);
        break;
      default:
        console.error('Rol desconocido');
    }
  }
}

