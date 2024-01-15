import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  isLoading = false;

  constructor(private authSrv: AuthService, private router: Router) {}

  registra(form: NgForm) {
    this.isLoading = true;

    try {
      const userDetails = {
        nome: form.value.nome,
        cognome: form.value.cognome,
        eta: form.value.eta,
        email: form.value.email,
        password: form.value.password
      };

      this.authSrv.signup(userDetails).subscribe(
        () => {
          this.router.navigate(['/login']);
          this.isLoading = false;
        },
        (error) => {
          console.error(error.error);
          if (error.error === 'Email format is invalid') {
            alert('Formato email non valido!');
          } else if (error.error === 'Email already exists') {
            alert('Email già in uso!');
          } else if (error.error === 'Password is too short') {
            alert('Password troppo corta!');
          }

          this.isLoading = false;
        }
      );
    } catch (error) {
      console.error(error);
      this.isLoading = false;
    }
  }
}
