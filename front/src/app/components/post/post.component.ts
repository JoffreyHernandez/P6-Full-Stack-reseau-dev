import { Component, Input } from '@angular/core';
import { Post } from '../../interfaces/models/post.interface';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent {
  @Input()
  public post: Post | undefined;
  public open = false;
}
