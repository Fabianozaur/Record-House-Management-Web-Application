import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Playlist} from './playlist.interface';
import {EnvironmentService} from '../environment/environment.service';
@Injectable({
  providedIn: 'root'
})
export class PlaylistService {

  private playlistEndpoint = '/playlist';

  constructor(private environment: EnvironmentService, private http: HttpClient) { }

  private get url(): string {
    return this.environment.apiUrl + this.playlistEndpoint;
  }

  public GetPlaylists(): Observable<Array<Playlist>> {
    return this.http.get<Array<Playlist>>(
      this.url
    );
  }

  public DeletePlaylist(id: string): Observable<any>{
    return this.http.delete(this.url, {params: { id }});
  }

  public AddPlaylist(Name: string, isPublic: boolean): Observable<Playlist> {
    return this.http.post<any>(this.url, { Name, isPublic });
  }
}
