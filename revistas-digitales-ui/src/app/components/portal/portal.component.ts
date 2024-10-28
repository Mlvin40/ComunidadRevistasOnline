import { Component } from '@angular/core';
import { Router, RouterOutlet, RouterLink } from '@angular/router';

@Component({
  selector: 'app-portal',
  standalone: true,
  imports: [RouterLink, RouterOutlet],
  templateUrl: './portal.component.html',
  styleUrls: ['./portal.component.css']
})
export class PortalComponent {
  title = 'revistas-digitales-ui';

  constructor(private router: Router) {}

  goToLogin() {
    this.router.navigate(['/login']); // Redirige al componente de login
  }

  goToRegister() {
    this.router.navigate(['/registro']); // Redirige al componente de registro
  }
}