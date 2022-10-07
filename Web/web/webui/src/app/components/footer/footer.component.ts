import { Component, OnInit } from '@angular/core';
import { faPlayCircle, faRandom, faStepBackward, faStepForward,  faVolumeUp} from '@fortawesome/free-solid-svg-icons';
import {faRetweet} from '@fortawesome/free-solid-svg-icons/faRetweet';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome/fontawesome.module';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  public faRandom = faRandom;
  public faStepBackward = faStepBackward;
  public faPlayCircle = faPlayCircle;
  public faStepForward = faStepForward;
  public faRetweet = faRetweet;
  public faVolumeUp = faVolumeUp;

  constructor() { }

  ngOnInit(): void {
  }

}
