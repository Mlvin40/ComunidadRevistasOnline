import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { FormsModule } from '@angular/forms'; 
import { UsuariosService } from '../../services/usuarios/usuarios.service';
import { Router } from '@angular/router';
import { Usuario} from '../../entidades/Usuario';
import { TipoUsuario } from '../../entidades/TipoUsuario';

@Component({
  selector: 'app-registro',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent {
  
  // Constructor con la inyección de dependencias
  constructor(private usuariosService: UsuariosService, private router: Router) {}

  // Método para manejar el envío del formulario
  onRegister(form: any) {
    if (form.valid) {
        // Obtener datos del formulario
        const formValue = form.value; 

        // Mapear los datos del formulario al objeto Usuario
        const usuario: Usuario = {
            nombreUsuario: formValue.nombreUsuario,
            contrasena: formValue.contrasena,
            texto: '', 
            fotoPerfil: null, 
            rol: TipoUsuario[formValue.rol as keyof typeof TipoUsuario], // Convertir el rol a TipoUsuario
            fechaCreacion: new Date() // esta fecha no se utiliza 
        };

    // Llamar al servicio para registrar el usuario
    this.usuariosService.registrarUsuario(usuario).subscribe(response => {
      console.log('Usuario registrado:', response);
      // Redirigir a la vista principal despues de un registro exitoso
      this.router.navigate(['/portal']);
    }, error => {
      console.error('Error al registrar el usuario:', error);
    });

    } else {
        console.log('El formulario es inválido');
    }
  }
}
