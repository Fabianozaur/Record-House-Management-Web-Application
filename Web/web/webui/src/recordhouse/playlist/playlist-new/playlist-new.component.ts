import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {PlaylistService} from '../playlist.service';
import {Router} from '@angular/router';
import {NgbCheckBox} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-playlist-new',
  templateUrl: './playlist-new.component.html',
  styleUrls: ['./playlist-new.component.css']
})
export class PlaylistNewComponent implements OnInit {

  playlistGroup = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.pattern('^[A-Za-z]*$')]),
    isPublic: new FormControl('', [Validators.required, Validators.pattern('^[A-Za-z]*$')]),

  });

  constructor(private service: PlaylistService,
              private router: Router) {}

  ngOnInit(): void {
  }

  goToList(): void {
    this.router.navigate(['/playlist']);
  }

  addPlaylist(): void {
    const name = this.playlistGroup.controls.name.value;
    const isPublic = this.playlistGroup.controls.isPublic.value;

    this.service.AddPlaylist(name, isPublic).subscribe();
    this.playlistGroup.reset();
  }

}
