import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { ConfiguracionUsuarioService } from '../../../services/configuracion-usuario/configuracion-usuario.service';
import { Usuario } from '../../../entidades/Usuario';
import { TipoUsuario } from '../../../entidades/TipoUsuario';

@Component({
  selector: 'app-configuracion-usuario',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './configuracion-usuario.component.html',
  styleUrls: ['./configuracion-usuario.component.css'],
})
export class ConfiguracionUsuarioComponent implements OnInit {
  nombreUsuario: string | null = '';
  usuario: Usuario | null = null;
  texto: string = '';
  fotoPerfil: File | null = null;
  fotoPerfilUrl: string | null = null;
  rolTexto: string = '';
  mensaje: string = ''; // Variable para mostrar mensajes de éxito o error

  constructor(private configuracionUsuarioService: ConfiguracionUsuarioService, private http: HttpClient) {}

  ngOnInit(): void {
    this.nombreUsuario = this.configuracionUsuarioService.obtenerNombreUsuarioDesdeToken();

    if (this.nombreUsuario) {
      this.configuracionUsuarioService.obtenerDatosUsuario(this.nombreUsuario).subscribe(
        (datosUsuario: Usuario) => {
          this.usuario = datosUsuario;
          this.texto = datosUsuario.texto;
          this.rolTexto = TipoUsuario[datosUsuario.rol];

          // Convertir imagen binaria a Base64 para mostrarla
          if (datosUsuario.fotoPerfil) {
            this.fotoPerfilUrl = `data:image/png;base64,${datosUsuario.fotoPerfil}`;
          }
        },
        (error) => {
          console.error('Error al obtener datos del usuario', error);
        }
      );
    }
  }

  onFileChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      this.fotoPerfil = file;

      // Leer la imagen para previsualización
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.fotoPerfilUrl = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }

  guardarCambios(): void {
    const formData: FormData = new FormData();
    formData.append('nombre', this.nombreUsuario || '');
    formData.append('texto', this.texto);

    if (this.fotoPerfil) {
      formData.append('fileObject', this.fotoPerfil);
    }

    this.configuracionUsuarioService.actualizarPerfil(formData).subscribe(
      (response) => {
        console.log('Perfil actualizado', response);
        if (response && response.message) {
          this.mensaje = response.message; // Asignar el mensaje de éxito
        }
        this.ngOnInit(); // Recargar los datos del usuario después de actualizar
      },
      (error) => {
        console.error('Error al actualizar perfil', error);
        this.mensaje = 'Hubo un error al actualizar tu perfil. Por favor, intenta nuevamente.'; // Mensaje de error
      }
    );
  }
}
