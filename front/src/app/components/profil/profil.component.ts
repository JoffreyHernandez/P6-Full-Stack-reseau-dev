import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../../interfaces/models/user.interface';
import { RegisterRequest } from '../../interfaces/requests/registerRequest.interface';
import { LoginResponse } from '../../interfaces/responses/loginResponse.interface';
import { SessionService } from '../../services/session.service';
import { TopicService } from '../../services/topic.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {

  public form: FormGroup | null = null;
  public update = false;
  public user: User | null = null;

  constructor(private sessionService: SessionService,
              private fb: FormBuilder,
              private userService: UserService,
              private router: Router,
              private topicService: TopicService) {
  }

  public ngOnInit(): void {
    this.userService.getById(this.sessionService.loginResponse!.id).subscribe({
      next: (user: User) => {
        this.user = user;
        this.initForm();
      }
    });
  }

  public async logout(): Promise<void> {
    this.sessionService.logOut();
    await this.router.navigate(['']);
  }

  public unfollow(topicId: number) {
    this.topicService.unfollow(topicId, this.user!.id).subscribe({
      next: (user: User) => this.user = user
    });
  }

  public updateUser(): void {
    const updateUser = { ...this.form?.value, id: this.user!.id } as User;
    this.userService.update(updateUser).subscribe({
      next: (loginResponse: LoginResponse) => {
        this.sessionService.logIn(loginResponse);
        this.userService.getById(loginResponse.id).subscribe({
          next: (user: User) => {
            this.user = user;
            this.initForm();
          }
        });
        this.update = false;
      }
    });
    console.log(updateUser);
  }

  public goUpdate(): void {
    this.update = !this.update;
  }

  private initForm(): void {
    this.form = this.fb.group({
      email: [
        this.user!.email,
        [
          Validators.required,
          Validators.email
        ]
      ],
      username: [
        this.user!.username,
        [
          Validators.required,
          Validators.min(3)
        ]
      ],
      password: [
        '',
        [
          Validators.required,
          Validators.min(3)
        ]
      ]
    });
  }
}
