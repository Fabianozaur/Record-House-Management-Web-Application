import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {PlaylistComponent} from './playlist/component/playlist.component';
import {PlaylistListComponent} from './playlist/playlist-list/playlist-list.component';
import {PlaylistNewComponent} from './playlist/playlist-new/playlist-new.component';
import {NgModule} from '@angular/core';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import { SongListComponent } from './song/song-list/song-list.component';
import { SongNewComponent } from './song/song-new/song-new.component';
import {SongComponent} from './song/component/song.component';
import { ArtistComponent } from './artist/component/artist.component';
import { ArtistListComponent } from './artist/artist-list/artist-list.component';
import { ArtistNewComponent } from './artist/artist-new/artist-new.component';
import { RecordhouseComponent } from './recordhouse/component/recordhouse.component';
import { RecordhouseListComponent } from './recordhouse/recordhouse-list/recordhouse-list.component';
import { RecordhouseNewComponent } from './recordhouse/recordhouse-new/recordhouse-new.component';

@NgModule({
  declarations: [
    // SongComponent, SongListComponent, SongDetailsComponent, SongNewComponent,
    // tslint:disable-next-line:max-line-length
    PlaylistComponent, PlaylistListComponent, PlaylistNewComponent, SongComponent, SongListComponent, SongNewComponent, ArtistComponent, ArtistListComponent, ArtistNewComponent, RecordhouseComponent, RecordhouseListComponent, RecordhouseNewComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    FontAwesomeModule
  ]
})
export class RecordhouseModule { }
