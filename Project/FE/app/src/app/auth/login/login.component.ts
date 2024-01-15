import { Component } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from '../auth.service';
import { RoleService } from '../role.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  isLoading = false;
  userRole$ = new BehaviorSubject<string>('');

  constructor(private authSrv: AuthService, private roleSrv: RoleService, private router: Router){}

  access(form: NgForm) {
    this.isLoading = true;

    this.authSrv.login(form.value).subscribe(
      (response: any) => {
        const token = response.token;
        if (token) {
          localStorage.setItem('token', token);

          // Ora che abbiamo il token, otteniamo i dettagli dell'utente
          this.authSrv.getUserDetails().subscribe(
            (user) => {
              console.log('Dettagli utente:', user);
              const userRole = user?.ruolo?.nome;
              const username = user?.username;
              const id = user?.id;
              const foto = user?.foto;
              localStorage.setItem('userRole', userRole);
              localStorage.setItem('username', username);
              localStorage.setItem('id', id);
              console.log(id);
              console.log('Ruolo dell\'utente:', userRole);

              this.roleSrv.setUserRole(userRole);
              this.userRole$.next(userRole);

              if (userRole === 'ADMIN') {
                this.router.navigate(['/admin']);
              } else if (userRole === 'USER') {
                this.router.navigate(['/user']);
              } else {
                console.error('Ruolo non gestito:', userRole);
              }

              this.isLoading = false;
            },
            (error) => {
              console.error('Errore nel recuperare i dettagli dell\'utente:', error);
              this.isLoading = false;
            }
          );
        } else {
          console.error('Il server non ha restituito un token.');
          this.isLoading = false;
        }
      },
      (error) => {
        console.error(error);

        if (error.error === 'Incorrect password') {
          alert('Occhio, pirata! Hai sbagliato password!');
        } else if (error.error === 'Cannot find user') {
          alert('Registrati!');
        } else {
          alert('Errore sconosciuto durante il login. Controlla la console per i dettagli.');
        }

        this.isLoading = false;
      }
    );
  }
}
