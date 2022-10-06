import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Topic } from '../interfaces/models/topic.interface';
import { User } from '../interfaces/models/user.interface';

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  private baseUrl = `api/topic`;

  constructor(private httpClient: HttpClient) {
  }

  public getAll(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(`${this.baseUrl}`);
  }

  public getById(topic_id: number): Observable<Topic> {
    return this.httpClient.get<Topic>(`${this.baseUrl}/${topic_id}`);
  }

  public follow(topic_id: number, user_id: number): Observable<User> {
    return this.httpClient.put<User>(`${this.baseUrl}/${topic_id}/follow/${user_id}`, null);
  }

  public unfollow(topic_id: number, user_id: number): Observable<User> {
    return this.httpClient.put<User>(`${this.baseUrl}/${topic_id}/unfollow/${user_id}`, null);
  }
}
