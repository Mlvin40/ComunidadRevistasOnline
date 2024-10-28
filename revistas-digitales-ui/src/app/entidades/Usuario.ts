import { TipoUsuario } from "./TipoUsuario";

export interface Usuario {
    
    nombreUsuario: string,
    contrasena: string,
    texto: string
    fotoPerfil: any
    rol: TipoUsuario,
    fechaCreacion: Date

}