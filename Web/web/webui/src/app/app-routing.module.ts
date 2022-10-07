import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PlaylistComponent } from '../recordhouse/playlist/component/playlist.component';
import {HomeComponent} from './components/home/home.component';
import {PlaylistNewComponent} from '../recordhouse/playlist/playlist-new/playlist-new.component';


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  // { path: 'song', component: SongComponent },
  // { path: 'song/add', component: SongNewComponent },
  // { path: 'artist', component: ArtistComponent },
  // { path: 'artist/add', component: ArtistNewComponent },
  { path: 'playlist', component: PlaylistComponent },
  { path: 'playlist/add', component: PlaylistNewComponent },
  // { path: 'recordHouse', component: RecordHouseComponent },
  // { path: 'recordHouse/add', component: RecordHouseNewComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
