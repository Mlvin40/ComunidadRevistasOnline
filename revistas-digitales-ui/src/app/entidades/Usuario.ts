import { TipoUsuario } from "./TipoUsuario";

export interface Usuario {
    
    nombreUsuario: string,
    contrasena: string,
    texto: string
    fotoPerfil: Blob | null; // Permitir que fotoPerfil sea null
    rol: TipoUsuario,
    fechaCreacion: Date

}