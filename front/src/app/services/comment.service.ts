import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Comment } from '../interfaces/models/comment.interface';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private baseUrl = `api/comment`;

  constructor(private httpClient: HttpClient) {
  }

  public create(comment: Comment): Observable<Comment> {
    return this.httpClient.post<Comment>(`${this.baseUrl}`, comment);
  }

  public getByPostId(postId: number): Observable<Comment[]> {
    return this.httpClient.get<Comment[]>(`${this.baseUrl}/post/${postId}`);
  }
}
