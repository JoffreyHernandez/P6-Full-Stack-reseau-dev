import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Comment } from '../../interfaces/models/comment.interface';
import { Post } from '../../interfaces/models/post.interface';
import { Topic } from '../../interfaces/models/topic.interface';
import { User } from '../../interfaces/models/user.interface';
import { CommentService } from '../../services/comment.service';
import { PostService } from '../../services/post.service';
import { SessionService } from '../../services/session.service';
import { TopicService } from '../../services/topic.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css']
})
export class PostDetailComponent implements OnInit {
  private postId: number = +this.route.snapshot.paramMap.get('id')!;
  public post: Post | null = null;
  public topic: Topic | null = null;
  public comments: Comment[] | null = null;
  public user: User | null = null;
  public form: FormGroup | null = null;

  constructor(private route: ActivatedRoute,
              private commentService: CommentService,
              private userService: UserService,
              private sessionService: SessionService,
              private topicService: TopicService,
              private fb: FormBuilder,
              private postService: PostService) {
  }

  public ngOnInit(): void {
    this.refreshComment();
    this.initForm();
    this.postService.getById(this.postId).subscribe({
      next: (post: Post) => {
        this.post = post;
        if (post.topic_id) {
          this.topicService.getById(post.topic_id).subscribe({
            next: (topic: Topic) => this.topic = topic
          });
        }
        if (post.user_id) {
          this.userService.getById(post.user_id).subscribe({
            next: (user: User) => this.user = user
          });
        }
      }
    });
  }

  public addComment(): void {
    const comment = {
      comment: this.form!.value.comment,
      user_id: this.sessionService.loginResponse!.id,
      post_id: this.postId
    } as Comment;
    this.commentService.create(comment).subscribe({
      next: (_: Comment) => this.refreshComment()
    });
  }

  private refreshComment(): void {
    this.commentService.getByPostId(this.postId).subscribe({
      next: (comments: Comment[]) => {
        this.comments = comments;
        this.initForm();
      }
    });
  }

  private initForm(): void {
    this.form = this.fb.group({
      comment: [
        '',
        [
          Validators.required
        ]
      ]
    });
  }
}
