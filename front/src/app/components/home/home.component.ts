import { Component, OnInit } from '@angular/core';
import { Post } from '../../interfaces/models/post.interface';
import { PostService } from '../../services/post.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public posts: Post[] = [];

  constructor(private postService: PostService) {
  }

  public ngOnInit(): void {
    this.postService.getAll().subscribe({
      next: (posts: Post[]) => this.posts = posts
    });
  }

}
