import { Component, OnInit } from '@angular/core';
import {faSearch, faSort, faTrash } from '@fortawesome/free-solid-svg-icons';
import {PlaylistService} from '../playlist.service';
import {Playlist} from '../playlist.interface';

@Component({
  selector: 'app-playlist-list',
  templateUrl: './playlist-list.component.html',
  styleUrls: ['./playlist-list.component.css']
})
export class PlaylistListComponent implements OnInit {

  public Playlists: Array<Playlist> = [];
  public faTrash = faTrash;
  public faSort = faSort;
  public faSearch = faSearch;
  nameSort = 'asc';

  constructor(private service: PlaylistService) { }

  ngOnInit(): void {
    this.getPlaylists();
  }

  getPlaylists(): void {
    this.service.GetPlaylists()
      .subscribe((r) => {
        this.Playlists = r;
        console.log(r);
      });
  }

  buttonClick(id: string): void {
    this.service.DeletePlaylist(id).subscribe(() => {
      this.getPlaylists();
    });
  }


  sortByName(): void{
    this.service.GetPlaylists()
      .subscribe(
        Playlists => {
          this.Playlists = Playlists.sort((a1, a2) => a1.PlaylistName?.localeCompare(a2.PlaylistName));
          if (this.nameSort === 'desc') {
            this.nameSort = 'asc';
            this.Playlists.reverse();
          }
          else {
            this.nameSort = 'desc';
          }
        },
        error => console.log(error),
      );
  }

  filterPlaylist(name: string): void{
    this.service.GetPlaylists()
      .subscribe(
        playlists => {
          this.Playlists = playlists.filter(playlist =>
            playlist.PlaylistName.startsWith(name));
        },
        error => console.log(error),
      );
  }
}
