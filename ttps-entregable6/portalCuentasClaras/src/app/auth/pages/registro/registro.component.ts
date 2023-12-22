import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../../services/auth.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styles: [
    `
    .overlay {
      background-color: white; /* Fondo opaco */
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      display: flex;
      align-items: center;
      justify-content: center;
    }

    .example-form {
      background-color: rgba(0, 0, 0, 0.1);
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    .example-form .example-full-width{
      width:100%;
      margin-bottom:15px;
    }

    `
  ]
})
export class RegistroComponent implements OnInit {

  constructor(private authService: AuthenticationService, private router: Router) { }
  ngOnInit(): void {
  }
  errorMessage='';
  username='';
  password='';
  email='';
  name='';
  lastname='';

  onSubmit(){
    this.authService.register(this.username, this.password, this.email, this.name, this.lastname ).subscribe(
      response => {
        // Maneja la respuesta del servidor en caso de éxito
        this.errorMessage = '';  // Reinicia el mensaje de error en caso de éxito
        this.router.navigate(['/auth/login']);
      },
      error => {
        // Maneja el error y muestra un mensaje
        console.log(error);
        this.errorMessage = error.error;
      }
    );
  }

}
