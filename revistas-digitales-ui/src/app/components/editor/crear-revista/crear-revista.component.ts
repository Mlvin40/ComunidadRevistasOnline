import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { UsuariosService } from '../../../services/usuarios/usuarios.service';
import { RevistaService } from '../../../services/revista/revista.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-crear-revista',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './crear-revista.component.html',
  styleUrls: ['./crear-revista.component.css']
})
export class CrearRevistaComponent implements OnInit {
  revistaForm: FormGroup;
  mensaje: string = '';

  constructor(
    private fb: FormBuilder,
    private revistaService: RevistaService,
    private usuarioService: UsuariosService,
    private router: Router
  ) {
    if(!this.usuarioService.permisosEditor()){
      //redirigir al login
      this.router.navigate(['/login']);
    }

    // Este es el formulario para crear una revista
    this.revistaForm = this.fb.group({
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
      categoria: ['', Validators.required],
      fechaCreacion: [new Date(), Validators.required],
      autor: [{ value: '', disabled: true }],
    });
  }

  ngOnInit(): void {
    const autor = this.usuarioService.obtenerNombreUsuarioDesdeToken();
    this.revistaForm.patchValue({ autor });
  }

  crearRevista(): void {
    if (this.revistaForm.valid) {
      const revistaData = new FormData();
      
      // Agregar los datos al FormData
      revistaData.append('nombre', this.revistaForm.get('nombre')?.value);
      revistaData.append('descripcion', this.revistaForm.get('descripcion')?.value);
      revistaData.append('categoria', this.revistaForm.get('categoria')?.value);
      revistaData.append('fechaCreacion', this.revistaForm.get('fechaCreacion')?.value);
      revistaData.append('autor', this.revistaForm.get('autor')?.value);
  
      this.revistaService.crearRevista(revistaData).subscribe(
        response => {
          console.log('Revista creada exitosamente:', response);
          this.mensaje = 'Revista creada exitosamente'; // Mensaje de éxito
          this.revistaForm.reset();
          const autor = this.usuarioService.obtenerNombreUsuarioDesdeToken();
          this.revistaForm.patchValue({ autor });
        },
        error => {
          console.error('Error al crear revista:', error);
          this.mensaje = 'Ha ocurrido un error al crear la revista'; // Mensaje de error
        }
      );
    } else {
      this.mensaje = 'Por favor completa todos los campos requeridos.'; // Mensaje de validación
    }
  }
}
