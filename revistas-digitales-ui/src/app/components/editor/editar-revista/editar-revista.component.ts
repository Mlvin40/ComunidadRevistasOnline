import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms'; // Asegúrate de importar FormsModule
import { RevistaService } from '../../../services/revista/revista.service';
import { RevistaEditable } from '../../../entidades/RevistaEditable';
import { CommonModule } from '@angular/common';
import { Revista } from '../../../entidades/Revista';

@Component({
  selector: 'app-editar-revista',
  standalone: true,
  imports: [FormsModule, CommonModule], // Agregar FormsModule aquí
  templateUrl: './editar-revista.component.html',
  styleUrls: ['./editar-revista.component.css'],
})
export class EditarRevistaComponent implements OnInit {

  revistaEditable: RevistaEditable = { // Inicializar con un objeto vacío
    nombre: '',
    descripcion: '',
    categoria: '',
    estadoComentar: false,
    estadoMeGusta: false,
    estadoSuscribirse: false,
  };

  mensaje: string = ''; // Mensaje de error o éxito

  constructor(
    private revistaService: RevistaService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const nombreRevista = this.route.snapshot.paramMap.get('nombre');
    if (nombreRevista) {
      this.revistaService.obtenerRevistaPorNombre(nombreRevista).subscribe(
        (revista: Revista) => {
          
          //Se toman los datos de la revista y se asignan a la variable revista
          this.revistaEditable = {
            nombre: revista.nombre,
            descripcion: revista.descripcion,
            categoria: revista.categoria,
            estadoComentar: revista.estadoComentar,
            estadoMeGusta: revista.estadoMeGusta,
            estadoSuscribirse: revista.estadoSuscribirse,
          };
        },
        (error) => {
          this.mensaje = error.error?.mensaje || 'No se pudo cargar la revista'; // Captura el mensaje del error
          console.error('Error al obtener la revista:', error);
        }
      );
    }
  }

  guardarCambios(): void {
    this.revistaService.actualizarRevista(this.revistaEditable).subscribe(
      () => this.router.navigate(['/misRevistas']),
      (error) => {
        this.mensaje = error.error.mensaje || 'No se pudo actualizar la revista'; // Captura el mensaje del error
      }
    );
}

}
