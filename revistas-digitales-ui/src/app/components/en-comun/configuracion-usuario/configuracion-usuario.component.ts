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
  usuario: Usuario | null = null; // Almacena los datos del usuario
  texto: string = ''; // Para el texto del perfil
  fotoPerfil: File | null = null; // Para almacenar la foto seleccionada
  fotoPerfilUrl: string | null = null; // URL de la foto de perfil para mostrar
  rolTexto: string = ''; // Para almacenar el rol del usuario como texto

  constructor(private configuracionUsuarioService: ConfiguracionUsuarioService, private http: HttpClient) {}

  ngOnInit(): void {
    this.nombreUsuario = this.configuracionUsuarioService.obtenerNombreUsuarioDesdeToken();

    if (this.nombreUsuario) {
      this.configuracionUsuarioService.obtenerDatosUsuario(this.nombreUsuario).subscribe(
        (datosUsuario: Usuario) => {
          this.usuario = datosUsuario;
          this.texto = datosUsuario.texto;
          this.rolTexto = TipoUsuario[datosUsuario.rol]; // Asigna el rol como texto
          
        // Convertir imagen binaria a Base64 para mostrarla
        if (datosUsuario.fotoPerfil) {
          this.fotoPerfilUrl = `data:image/png;base64,${datosUsuario.fotoPerfil}`;
          console.log('Imagen en Base64 lista para mostrar:', this.fotoPerfilUrl);
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
      this.fotoPerfil = file; // Almacena el archivo en la propiedad fotoPerfil
    
      console.log('Imagen seleccionada:', file);

      // Leer la imagen para previsualización
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.fotoPerfilUrl = e.target.result; // Carga la imagen para previsualizar
        console.log('Imagen cargada:', this.fotoPerfilUrl);
      };
      reader.readAsDataURL(file); // Carga el archivo como URL de datos
      
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
          console.log(response.message); // Ahora esto debería mostrar el mensaje del servidor
        }
        this.ngOnInit(); // Recargar los datos del usuario después de actualizar
      },
      (error) => {
        console.error('Error al actualizar perfil', error);
        if (error.error) {
          console.error('Error del servidor:', error.error);
        } else {
          console.error('Error desconocido:', error);
        }
      }
    );
  }
}