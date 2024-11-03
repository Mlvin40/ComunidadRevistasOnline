import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegistroComponent } from './components/registro/registro.component';
import { PortalComponent } from './components/portal/portal.component';
import { EditorHomeComponent } from './components/editor/editor-home/editor-home.component';
import { CrearRevistaComponent } from './components/editor/crear-revista/crear-revista.component';
import { ConfiguracionUsuarioComponent } from './components/configuracion-usuario/configuracion-usuario.component';
import { CarteraEditorComponent } from './components/editor/cartera-editor/cartera-editor.component';
import { MisRevistasComponent } from './components/editor/mis-revistas/mis-revistas.component';
import { EditarRevistaComponent } from './components/editor/editar-revista/editar-revista.component';
import { PublicarComponent } from './components/editor/publicar/publicar.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'portal', component: PortalComponent },
  { path: '', redirectTo: '/portal', pathMatch: 'full' },// Redirige a portal por defecto
  { path: 'editorHome', component: EditorHomeComponent },
  { path: 'crearRevista', component: CrearRevistaComponent },
  { path: 'configuracionPerfil', component: ConfiguracionUsuarioComponent },
  { path: 'carteraEditor', component: CarteraEditorComponent },
  { path: 'misRevistas', component: MisRevistasComponent },
  { path: 'editarRevista/:nombre', component: EditarRevistaComponent },
  { path: 'publicar/:nombre', component: PublicarComponent },
  
];
