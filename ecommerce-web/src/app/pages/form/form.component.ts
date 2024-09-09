import { Component } from '@angular/core';
import { AuthenticationRequest, RegistrationRequest } from '../../services/models';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/services';
import { TokenService } from '../../services/token/token.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent {
  isSignUp: boolean = false;
  regRequest: RegistrationRequest = {
    name: '',
    email: '',
    phoneNum: '',
    password: '',
    roleType: ''
  };
  authRequest: AuthenticationRequest = {email: '', password: ''};

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private tokenService: TokenService
  ) {}

  toggleSignUp(value: boolean): void{
    this.isSignUp = value;
  }

  onSignIn(): void {
    this.authService.authenticate({
      body: this.authRequest
    }).subscribe({
      next: (res) => {
        this.tokenService.token = res.data?.access_token as string;
        this.router.navigate(['login'])
      }
    })
  }

  onSignUp(): void {
    this.authService.register({
      body: this.regRequest
    }).subscribe({
      next: () => {
        this.router.navigate(['register']);
      }
    })
  }

  register() {
    this.router.navigate(['register'])
  }

}
