import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UsuariosService } from '../../services/usuarios/usuarios.service';
import { setThrowInvalidWriteToSignalError } from '@angular/core/primitives/signals';

@Component({
  selector: 'app-util',
  standalone: true,
  imports: [],
  templateUrl: './util.component.html',
  styleUrl: './util.component.css'
})
export class UtilComponent {
  
constructor(private router: Router, private usuarioService: UsuariosService){ }

  cerrarSesion() {
  console.log('Cerrando sesión');
  this.usuarioService.eliminarToken();
  console.log('Token eliminado:', this.usuarioService.obtenerToken());
  this.router.navigate(['/']);

}
irAConfiguracion() {
  this.router.navigate(['/configuracionPerfil']);
  console.log('Ir a configuración');
}
irAperfil() {
  console.log('Ir a perfil');
}

}
