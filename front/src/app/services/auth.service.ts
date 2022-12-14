import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginRequest } from '../interfaces/requests/loginRequest.interface';
import { RegisterRequest } from '../interfaces/requests/registerRequest.interface';
import { LoginResponse } from '../interfaces/responses/loginResponse.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = `api/auth`;

  constructor(private httpClient: HttpClient) {
  }

  public login(loginRequest: LoginRequest): Observable<LoginResponse> {
    return this.httpClient.post<LoginResponse>(`${this.baseUrl}/login`, loginRequest);
  }

  public register(registerRequest: RegisterRequest): Observable<{ message: string }> {
    return this.httpClient.post<{ message: string }>(`${this.baseUrl}/register`, registerRequest);
  }

}
