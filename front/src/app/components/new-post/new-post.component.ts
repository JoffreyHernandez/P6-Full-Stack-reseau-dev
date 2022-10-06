import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Post } from '../../interfaces/models/post.interface';
import { Topic } from '../../interfaces/models/topic.interface';
import { LoginRequest } from '../../interfaces/requests/loginRequest.interface';
import { PostService } from '../../services/post.service';
import { SessionService } from '../../services/session.service';
import { TopicService } from '../../services/topic.service';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {

  public form = this.fb.group({
    topic_id: [
      '',
      [
        Validators.required
      ]
    ],
    title: [
      '',
      [
        Validators.required,
        Validators.min(3)
      ]
    ],
    content: [
      '',
      [
        Validators.required,
        Validators.min(3)
      ]
    ]
  });
  public topics: Topic[] | undefined;

  constructor(private postService: PostService,
              private fb: FormBuilder,
              private sessionService: SessionService,
              private topicService: TopicService,
              private router: Router) {
  }

  public ngOnInit() {
    this.topicService.getAll().subscribe({
      next: (topics: Topic[]) => this.topics = topics
    });
  }

  public submit() {
    const post = { ...this.form.value, user_id: this.sessionService.loginResponse?.id } as unknown as Post;
    this.postService.create(post).subscribe({
      next: (_: Post) => this.router.navigate(['/home'])
    });
  }
}
