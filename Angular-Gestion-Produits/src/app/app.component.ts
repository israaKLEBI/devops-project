import { Component, OnInit } from '@angular/core';
import { AuthService } from './services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent implements OnInit {
  title = 'Gestion de produits';

  constructor(public authService: AuthService, private router: Router) {}

  ngOnInit() {
    this.authService.loadToken();

    if (!this.authService.isUserLoggedIn()) {
      this.router.navigate(['/login']);
    }  else {
      this.router.navigate(['/']);
    }
  }

  onLogout() {
    this.authService.logout();
  }
}
