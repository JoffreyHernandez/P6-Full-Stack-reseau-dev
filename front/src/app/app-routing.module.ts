import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NewPostComponent } from './components/new-post/new-post.component';
import { PostDetailComponent } from './components/post-detail/post-detail.component';
import { PostComponent } from './components/post/post.component';
import { ProfilComponent } from './components/profil/profil.component';
import { StartComponent } from './components/start/start.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { TopicsComponent } from './components/topics/topics.component';
import { AuthGuard } from './guards/auth.guard';
import { UnauthGuard } from './guards/unauth.guard';

const routes: Routes = [
  { path: '', canActivate: [UnauthGuard], component: StartComponent },
  { path: 'register', canActivate: [UnauthGuard], component: RegisterComponent },
  { path: 'login', canActivate: [UnauthGuard], component: LoginComponent },
  {
    path: 'home', canActivate: [AuthGuard], component: HomeComponent
  },
  {
    path: 'add-post', canActivate: [AuthGuard], component: NewPostComponent
  },
  {
    path: 'post/:id', canActivate: [AuthGuard], component: PostDetailComponent
  },
  {
    path: 'topic', canActivate: [AuthGuard], component: TopicsComponent
  },
  {
    path: 'profil', canActivate: [AuthGuard], component: ProfilComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
