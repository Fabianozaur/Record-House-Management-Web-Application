package recordhouse.PlaylistSongRelation.domain;

import design.domain.Relation;

public class PlaylistSongRelation extends Relation<String,String> {
    private String playlistId;
    private String songId;

    public PlaylistSongRelation(String plId,String sId){
        this.playlistId=plId;
        this.songId=sId;
    }

    public PlaylistSongRelation(){}


    public String getPlaylistId()
    {
        return this.playlistId;
    }
    public String getSongId()
    {
        return this.songId;
    }
    public void setSongId(String newSongId){
        this.songId=newSongId;
    }
    public void setPlaylistId(String newPlaylistId){
        this.playlistId=newPlaylistId;
    }
    @Override
    public String toString()
    {
        return "PlaylistSongRelation{"+
                "PlaylistId: {" + playlistId +'}'+ '\n' +
                "SongId: {" + songId +'}'+'\n';
    }

}

