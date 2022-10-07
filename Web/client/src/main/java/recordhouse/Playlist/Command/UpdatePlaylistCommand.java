package recordhouse.Playlist.Command;


import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.ExactMatchRegexCommandMatcher;
import recordhouse.shared.command.String.StringCommand;
import service.Playlist.PlaylistService;
import service.Playlist.PlaylistViewModel;

import java.util.Optional;

@Service
public class UpdatePlaylistCommand extends StringCommand {
    private PlaylistService service;

    public UpdatePlaylistCommand(PlaylistService playlistService){
        super("UpdatePlaylist", "If you want to update a playlist.");
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("^UpdatePlaylist ([a-zA-Z0-9]|-)+ [a-zA-Z0-9]+ [a-z]+"));
        this.service=playlistService;

    }

    @Override
    public String Execute(String input) {

        String[] splitInput = input.split(" ");
        String id=splitInput[1];
        String name = splitInput[2];
        String isPublic = splitInput[3];
        Optional.of(isPublic).filter(s -> s.toLowerCase().equals("true") || s.toLowerCase().equals("false")).orElseThrow(() -> new PlaylistCommandException("You should've entered a bool value"));
        boolean is_public=Boolean.parseBoolean(isPublic);

        var model =  new PlaylistViewModel();
        model.Id = id;
        model.Name = name;
        model.isPublic=is_public;

        this.service.Update(model);

        return "Playlist updated\n";
    }
}
