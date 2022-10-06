import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../interfaces/models/user.interface';
import { LoginResponse } from '../interfaces/responses/loginResponse.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = `api/user`;

  constructor(private httpClient: HttpClient) {
  }

  public getById(user_id: number): Observable<User> {
    return this.httpClient.get<User>(`${this.baseUrl}/${user_id}`);

  }

  public update(updateUser: User): Observable<LoginResponse> {
    return this.httpClient.put<LoginResponse>(`${this.baseUrl}`, updateUser);
  }
}
