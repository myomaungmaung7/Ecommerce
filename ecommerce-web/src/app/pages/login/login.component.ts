import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/services';
import { TokenService } from '../../services/token/token.service';
import { AuthenticationRequest } from '../../services/models';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  authRequest: AuthenticationRequest = {email: '', password: ''};
  errorMsg: Array<string> = [];

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private tokenService: TokenService
  ) {}

  login() {
    this.errorMsg = [];
    this.authService.authenticate({
      body: this.authRequest
    }).subscribe({
      next: (res) => {
        this.tokenService.token = res.data?.access_token as string;
        this.router.navigate(['register']);
      },
      error: (err) => {
        console.log(err);
        if (err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else {
          this.errorMsg.push(err.error.errorMsg);
        }
      }
    });
  }

  // login() {
  //   this.errorMsg = [];
  //   this.authService.authenticate({ body: this.authRequest }).pipe(
  //     tap((res) => {
  //       // Handle successful authentication
  //       if (res.data?.access_token) {
  //         this.tokenService.token = res.data.access_token;
  //         this.router.navigate(['register']);
  //       } else {
  //         // Handle missing access token in response
  //         this.errorMsg.push('Authentication failed. No access token received.');
  //       }
  //     }),
  //     catchError((err) => {
  //       // Handle errors
  //       console.log(err);
  //       if (err.error?.validationErrors) {
  //         this.errorMsg = err.error.validationErrors;
  //       } else if (err.error?.errorMsg) {
  //         this.errorMsg.push(err.error.errorMsg);
  //       } else {
  //         this.errorMsg.push('An unknown error occurred.');
  //       }
  //       return throwError(err);
  //     })
  //   ).subscribe();
  // }

  register() {
    this.router.navigate(['register'])
  }
}
