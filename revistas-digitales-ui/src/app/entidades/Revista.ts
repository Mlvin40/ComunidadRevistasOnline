import { Comentario } from "./Comentario";

export interface Revista {
  nombre: string;
  descripcion: string;
  categoria: string;
  fechaCreacion: Date; 
  autor: string;
  costo: number;
  estadoComentar: boolean;
  estadoMeGusta: boolean;
  estadoSuscribirse: boolean;
  likes: number;
  comentarios: Comentario[]; // Arreglo de comentarios
}