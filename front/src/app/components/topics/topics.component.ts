import { Component, OnInit } from '@angular/core';
import { Topic } from '../../interfaces/models/topic.interface';
import { User } from '../../interfaces/models/user.interface';
import { SessionService } from '../../services/session.service';
import { TopicService } from '../../services/topic.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.css']
})
export class TopicsComponent implements OnInit {

  public topics: Topic[] | null = null;
  public user: User | null = null;

  constructor(
    private sessionService: SessionService,
    private topicService: TopicService,
    private userService: UserService
  ) {
  }

  public ngOnInit(): void {
    this.userService.getById(this.sessionService.loginResponse!.id).subscribe({
      next: (user: User) => this.user = user
    });
    this.refreshTopic();
  }

  public alreadySubscribe(topic: Topic): boolean {
    if (this.user && this.user.topics) {
      return this.user.topics.some(t => t.id === topic.id);
    }
    return false;
  }

  public follow(topic: Topic): void {
    this.topicService.follow(topic.id, this.user!.id).subscribe({
      next: (user: User) => this.user = user
    });
  }

  private refreshTopic(): void {
    this.topicService.getAll().subscribe({
      next: (topics: Topic[]) => this.topics = topics
    });
  }
}
