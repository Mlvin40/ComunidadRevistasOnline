import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { AnuncioService } from '../../../services/anuncio/anuncio.service';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

type TipoAnuncio = 'TEXTO' | 'TEXTO_IMAGEN' | 'VIDEO';
type TipoPrecio = 'texto' | 'textoImagen' | 'video';

@Component({
  selector: 'app-comprar-anuncio',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],
  templateUrl: './comprar-anuncio.component.html',
  styleUrls: ['./comprar-anuncio.component.css']
})
export class ComprarAnuncioComponent implements OnInit {
  precios: Record<TipoPrecio, number> = { texto: 0, textoImagen: 0, video: 0 };
  duracion: number = 1; // duración en días
  totalAPagar: number = 0; // total a pagar por el anuncio
  anuncioForm: FormGroup;

  constructor(private fb: FormBuilder, private anuncioService: AnuncioService) {
    this.anuncioForm = this.fb.group({
      tipoAnuncio: ['', Validators.required],
      contenidoTexto: [''],
      imagen: [null],
      urlVideo: [''],
      duracion: [1, Validators.required], // Agregar campo para la duración
      fechaInicio: [null, Validators.required] // Añadir control para fecha de inicio
    });
  }

  ngOnInit(): void {
    this.anuncioService.obtenerPreciosAnuncios().subscribe((precios) => {
      this.precios = {
        texto: precios.texto || 0,
        textoImagen: precios.textoImagen || 0,
        video: precios.video || 0
      };
      this.calcularTotal(); // Calcular total al iniciar
    });
  }

  // Método para calcular el total a pagar
  calcularTotal(): void {
    const tipo = this.anuncioForm.get('tipoAnuncio')?.value as TipoAnuncio; // Especificar el tipo
    const precioPorDia = this.precios[tipo === 'TEXTO' ? 'texto' : tipo === 'TEXTO_IMAGEN' ? 'textoImagen' : 'video'] || 0; 
    this.totalAPagar = precioPorDia * this.duracion;
  }

  // Método que se llama cuando cambia la duración
  onDuracionChange(): void {
    this.duracion = this.anuncioForm.get('duracion')?.value || 1; // Obtener la duración del formulario
    this.calcularTotal(); // Volver a calcular el total al cambiar la duración
  }

  onTipoAnuncioChange(): void {
    const tipo = this.anuncioForm.get('tipoAnuncio')?.value as TipoAnuncio;

    if (tipo === 'TEXTO') {
      this.anuncioForm.get('contenidoTexto')?.setValidators([Validators.required]);
      this.anuncioForm.get('imagen')?.clearValidators();
      this.anuncioForm.get('urlVideo')?.clearValidators();
    } else if (tipo === 'TEXTO_IMAGEN') {
      this.anuncioForm.get('contenidoTexto')?.setValidators([Validators.required]);
      this.anuncioForm.get('imagen')?.setValidators([Validators.required]);
      this.anuncioForm.get('urlVideo')?.clearValidators();
    } else if (tipo === 'VIDEO') {
      this.anuncioForm.get('contenidoTexto')?.clearValidators();
      this.anuncioForm.get('imagen')?.clearValidators();
      this.anuncioForm.get('urlVideo')?.setValidators([Validators.required]);
    }

    // Actualizar la validez de los controles
    this.anuncioForm.get('contenidoTexto')?.updateValueAndValidity();
    this.anuncioForm.get('imagen')?.updateValueAndValidity();
    this.anuncioForm.get('urlVideo')?.updateValueAndValidity();

    // Calcular el total al cambiar el tipo de anuncio
    this.calcularTotal();
  }

  // Método para manejar la selección de archivos
  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file: File = input.files[0];
      // Verificar que el archivo es una imagen
      if (file.type.startsWith('image/')) {
        this.anuncioForm.patchValue({ imagen: file });
      } else {
        alert('Por favor selecciona un archivo de imagen válido.');
        this.anuncioForm.patchValue({ imagen: null }); // Restablecer el valor si no es válido
      }
    }
  }

  comprarAnuncio(): void {
    if (this.anuncioForm.valid) {
      const anuncioData = {
        ...this.anuncioForm.value,
        duracion: this.duracion,
        fechaInicio: this.anuncioForm.get('fechaInicio')?.value, // Obtener la fecha de inicio
        totalAPagar: this.totalAPagar
      };

      this.anuncioService.comprarAnuncio(anuncioData).subscribe(
        (response) => {
          console.log('Anuncio comprado:', response);
          // Mostrar notificación al usuario
          alert('Anuncio comprado con éxito!');
          
          // Reiniciar el formulario
          this.anuncioForm.reset({
            tipoAnuncio: '',
            contenidoTexto: '',
            imagen: null,
            urlVideo: '',
            duracion: 1,
            fechaInicio: null
          });
          this.totalAPagar = 0; // Reiniciar el total a pagar
        },
        (error) => {
          console.error('Error al comprar el anuncio:', error);
          alert('Hubo un error al comprar el anuncio. Inténtalo de nuevo.'); // Notificación de error
        }
      );
    } else {
      alert('Por favor completa todos los campos requeridos.'); // Notificación si el formulario no es válido
    }
  }

  
  
}
