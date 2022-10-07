package recordhouse.Playlist.Command;

import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.ExactMatchRegexCommandMatcher;
import recordhouse.shared.command.String.StringCommand;
import service.Playlist.PlaylistService;
import service.Playlist.PlaylistViewModel;




@Service
public class AddPlaylistCommand extends StringCommand {

    private PlaylistService service;

    public AddPlaylistCommand(PlaylistService playlistService){
        super("AddPlaylist", "If you want to add a playlist. ");
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("^AddPlaylist [a-zA-Z0-9]+ [a-z]+$"));
        this.service=playlistService;
    }

    @Override
    public String Execute(String input) {

        String[] splitInput = input.split(" ");
        String name = splitInput[1];
        String isPublic = splitInput[2];
        boolean is_public=Boolean.parseBoolean(isPublic);


        PlaylistViewModel model =  new PlaylistViewModel();
        model.Name = name;
        model.isPublic = is_public;

        var result=this.service.Add(model);
        return String.format("Playlist created with id: %s.\n",result.Id);

    }
}

