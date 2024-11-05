import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegistroComponent } from './components/registro/registro.component';
import { PortalComponent } from './components/portal/portal.component';
import { EditorHomeComponent } from './components/editor/editor-home/editor-home.component';
import { CrearRevistaComponent } from './components/editor/crear-revista/crear-revista.component';
import { ConfiguracionUsuarioComponent } from './components/en-comun/configuracion-usuario/configuracion-usuario.component';
import { MisRevistasComponent } from './components/editor/mis-revistas/mis-revistas.component';
import { EditarRevistaComponent } from './components/editor/editar-revista/editar-revista.component';
import { PublicarComponent } from './components/editor/publicar/publicar.component';
import { VerPublicacionesComponent } from './components/en-comun/ver-publicaciones/ver-publicaciones.component';
import { AnuncianteHomeComponent } from './components/anunciante/anunciante-home/anunciante-home.component';
import { CarteraUsuarioComponent } from './components/en-comun/cartera-usuario/cartera-usuario.component';
import { PerfilUsuarioComponent } from './components/en-comun/perfil-usuario/perfil-usuario.component';
import { ComprarAnuncioComponent } from './components/anunciante/comprar-anuncio/comprar-anuncio.component';
import { AnuncioComponent } from './components/anunciante/anuncio/anuncio.component';
import { VerAnunciosComponent } from './components/anunciante/ver-anuncios/ver-anuncios.component';
import { EditarAnuncioComponent } from './components/anunciante/editar-anuncio/editar-anuncio.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'portal', component: PortalComponent },
  { path: '', redirectTo: '/portal', pathMatch: 'full' },// Redirige a portal por defecto
  { path: 'editorHome', component: EditorHomeComponent },
  { path: 'crearRevista', component: CrearRevistaComponent },
  { path: 'configuracionPerfil', component: ConfiguracionUsuarioComponent },
  { path: 'carteraUsuario', component: CarteraUsuarioComponent },
  { path: 'perfilUsuario/:nombreUsuario', component: PerfilUsuarioComponent },

  // Para el usuario editor
  { path: 'misRevistas', component: MisRevistasComponent },
  { path: 'editarRevista/:nombre', component: EditarRevistaComponent },
  { path: 'publicar/:nombre', component: PublicarComponent },
  { path: 'verPublicaciones/:nombre', component: VerPublicacionesComponent },


  // Rutas del anunciante
  { path : 'anuncianteHome', component: AnuncianteHomeComponent },
  { path : 'comprarAnuncio', component: ComprarAnuncioComponent },
  { path : 'mostrarAnuncio', component: AnuncioComponent },
  { path : 'verMisAnuncios', component: VerAnunciosComponent },
  { path : 'editarAnuncio/:id', component: EditarAnuncioComponent }
  

];
