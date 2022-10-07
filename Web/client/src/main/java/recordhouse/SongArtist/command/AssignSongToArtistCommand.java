package recordhouse.SongArtist.command;

import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.StringCommand;
import service.SongArtist.SongArtistRelationService;
import service.SongArtist.SongArtistRelationViewModel;
import shared.channel.console.ConsoleChannel;
import spring.ApplicationContext;

@Service
public class AssignSongToArtistCommand extends StringCommand {
    private final SongArtistRelationService service;

    public AssignSongToArtistCommand(SongArtistRelationService service) {
        super("assignSongToArtist", "This command assigns a song to an artist.");
        this.service = service;
    }

    @Override
    public String Execute(String input) {
        ConsoleChannel stream = ApplicationContext.getInstance(ConsoleChannel.class);

        stream.writeLine("Song id:");
        String songId = stream.readLine();
        stream.writeLine("Artist id:");
        String artistId = stream.readLine();

        var relation = new SongArtistRelationViewModel();
        relation.songId = songId;
        relation.artistId = artistId;
        service.Add(relation);
        return "";
    }
}
