import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from '../interfaces/models/post.interface';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private baseUrl = `api/post`;

  constructor(private httpClient: HttpClient) {
  }

  public getAll(): Observable<Post[]> {
    return this.httpClient.get<Post[]>(`${this.baseUrl}`);
  }

  public getById(id: number): Observable<Post> {
    return this.httpClient.get<Post>(`${this.baseUrl}/${id}`);
  }

  public create(post: Post): Observable<Post> {
    return this.httpClient.post<Post>(`${this.baseUrl}`,post);
  }
}
