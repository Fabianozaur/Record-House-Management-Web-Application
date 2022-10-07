package recordhouse.Playlist.Command;

import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.ExactMatchRegexCommandMatcher;
import recordhouse.shared.command.String.StringCommand;
import service.Playlist.PlaylistService;
import service.Playlist.PlaylistViewModel;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FilterPlaylistByPublicityCommand extends StringCommand {
    private final PlaylistService service;
    public FilterPlaylistByPublicityCommand(PlaylistService playlistService)
    {
        super("filterPlaylistByPublicity","This command filters a playlist by isPublic ");
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("^filterPlaylistByPublicity [a-zA-Z]+$"));
        this.service=playlistService;
    }

    @Override
    public String Execute(String input) {
        String[] splitInput=input.split(" ");
        String isPublic=splitInput[1];

        boolean isPub=Boolean.parseBoolean(isPublic);

        List<PlaylistViewModel> playlistStream = Arrays.stream(service.GetAll())
                .filter((s) -> isPub==s.isPublic)
                .collect(Collectors.toList());

        StringBuilder stringBuilder=new StringBuilder();
        playlistStream.forEach((playlist -> stringBuilder.append(playlist).append("\n")));
        String playlists = stringBuilder.toString();

        return Optional.of(playlists)
                .filter(s-> !s.isEmpty())
                .orElse("There are no playlists of that type \n");

    }
}