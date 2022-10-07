package recordhouse.Playlist.domain;


import design.domain.BaseEntity;

public class Playlist extends BaseEntity<String> {
    //
    private String PlaylistName;
    private boolean isPublic;

    public Playlist() {

    }

    public Playlist(String playlistId, String playlistName, boolean isPub) {
        this.setId(playlistId);
        PlaylistName = playlistName;
        isPublic = isPub;
    }


    public String getPlaylistName() {
        if (PlaylistName == null)
            return "";
        return PlaylistName;
    }

    public void setPlaylistName(String name) {
        PlaylistName = name;
    }


    public boolean getIsPublic()
    {
        return isPublic;
    }
    public void setIsPublic(boolean isPub)
    {
        isPublic=isPub;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Playlist playlist = (Playlist) obj;

        if(!this.getId().equals(playlist.getId())) return false;
        if (isPublic != playlist.isPublic) return false;
        return PlaylistName.equals(playlist.PlaylistName);


    }

    @Override
    public int hashCode() {
        int result =PlaylistName.hashCode();
        result= 31 * result+(isPublic ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {


        return "Playlist:" +'\n'+
                "PlaylistName: {" + PlaylistName +'}'+ '\n' +
                "Is Public?: {"+isPublic+'}'+'\n'+
                super.toString();
    }

}