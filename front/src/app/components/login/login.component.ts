import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from '../../interfaces/requests/loginRequest.interface';
import { LoginResponse } from '../../interfaces/responses/loginResponse.interface';
import { AuthService } from '../../services/auth.service';
import { SessionService } from '../../services/session.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  public onError = false;
  public form = this.fb.group({
    email: [
      '',
      [
        Validators.required,
        Validators.email
      ]
    ],
    password: [
      '',
      [
        Validators.required,
        Validators.min(3)
      ]
    ]
  });

  constructor(private authService: AuthService,
              private fb: FormBuilder,
              private router: Router,
              private sessionService: SessionService) {
  }

  public submit(): void {
    const registerRequest = this.form.value as LoginRequest;
    this.authService.login(registerRequest).subscribe({
      next: (loginResponse: LoginResponse) => {
        this.sessionService.logIn(loginResponse);
        this.router.navigate(['/home']);
      },
      error: (_) => this.onError = true
    });
  }

}
