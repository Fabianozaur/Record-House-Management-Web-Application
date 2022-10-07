package recordhouse.PlaylistSong.command;

import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.ExactMatchRegexCommandMatcher;
import recordhouse.shared.command.String.StringCommand;
import service.PlaylistSong.PlaylistSongRelationService;
import service.PlaylistSong.PlaylistSongRelationViewModel;

@Service
public class AssignSongToPlaylistCommand extends StringCommand {
    private final PlaylistSongRelationService service;
    public AssignSongToPlaylistCommand(PlaylistSongRelationService service){
        super("assignSongToPlaylist","This commands assigns a song to a playlist");
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("^assignSongToPlaylist s:([a-zA-Z0-9]|-)+ to p:([a-zA-Z0-9]|-)+$"));
        this.service=service;
    }

    @Override
    public String Execute(String input) {
        String[] splitInput = input.split(" ");
        String songId = splitInput[1].replaceFirst("s:", "");
        String playlistid = splitInput[3].replaceFirst("p:", "");

        var relation = new PlaylistSongRelationViewModel();
        relation.playlistId = playlistid;
        relation.songId = songId;
        service.Add(relation);
        return "";

    }
}
