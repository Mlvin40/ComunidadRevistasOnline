import { Component } from '@angular/core';
import { LoginComponent } from "./components/login/login.component";
import { PortalComponent } from "./components/portal/portal.component";
import { Router, RouterOutlet, RouterLink } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html', // Mantén esto para usar el archivo de plantilla
  styleUrls: ['./app.component.css'],
  imports: [LoginComponent, PortalComponent,RouterLink, RouterOutlet]
})
export class AppComponent {
  title = 'revistas-digitales-ui';

}
