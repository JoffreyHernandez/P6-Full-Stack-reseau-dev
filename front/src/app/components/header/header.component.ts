import { Component } from '@angular/core';
import { ActivatedRoute, UrlSegment } from '@angular/router';
import { map, Observable } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  public isOnAuthPage$: Observable<boolean> = this.activedRoute.url.pipe(map((urls: UrlSegment[]) => urls.some((url: UrlSegment) => url.path.includes('login') || url.path.includes('register'))));

  constructor(private activedRoute: ActivatedRoute) {
  }
}
