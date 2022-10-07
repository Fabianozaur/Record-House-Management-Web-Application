import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import { faHome } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public faHome = faHome;

  constructor(public route: ActivatedRoute) { }

  ngOnInit(): void {
  }

}
