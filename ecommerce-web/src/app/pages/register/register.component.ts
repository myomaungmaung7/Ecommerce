import { Component } from '@angular/core';
import { RegistrationRequest } from '../../services/models';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/services';
import { TokenService } from '../../services/token/token.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  regRequest: RegistrationRequest = {
    name: '',
    email: '',
    phoneNum: '',
    password: ''
  }

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private tokenService: TokenService
  ) {}

  register() {
    this.authService.register({
      body: this.regRequest
    }).subscribe({
      next: () => {
        this.router.navigate(['']);
      }
    })
  }

  login() {
    this.router.navigate([''])
  }
}
