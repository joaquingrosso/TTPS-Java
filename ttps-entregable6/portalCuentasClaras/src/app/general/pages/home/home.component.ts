import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styles: [ `
    .container{
      margin: 20px;
    }
  `
  ]
})
export class HomeComponent implements OnInit {

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }
  logout() {
    this.authenticationService.logout()
    this.router.navigate(['/auth/login']);
  }
}
