import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { LoginResponse } from '../interfaces/responses/loginResponse.interface';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  public isLogged = false;
  public loginResponse: LoginResponse | undefined;

  private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);

  public $isLogged(): Observable<boolean> {
    return this.isLoggedSubject.asObservable();
  }

  public logIn(loginResponse: LoginResponse): void {
    localStorage.setItem('token', loginResponse.token);
    this.loginResponse = loginResponse;
    this.isLogged = true;
    this.next();
  }

  public logOut(): void {
    localStorage.removeItem('token');
    this.loginResponse = undefined;
    this.isLogged = false;
    this.next();
  }

  private next(): void {
    this.isLoggedSubject.next(this.isLogged);
  }
}
