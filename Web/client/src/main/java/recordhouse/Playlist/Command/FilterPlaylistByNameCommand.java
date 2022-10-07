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
public class FilterPlaylistByNameCommand extends StringCommand {
    private final PlaylistService service;
    public FilterPlaylistByNameCommand(PlaylistService playlistService)
    {
        super("filterPlaylistByName","This command filters a playlist by name ");
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("^filterPlaylistByName [a-zA-Z0-9]+$"));
        this.service=playlistService;
    }

    @Override
    public String Execute(String input) {
        String[] splitInput=input.split(" ");
        String name=splitInput[1];

        List<PlaylistViewModel> playlistStream = Arrays.stream(service.GetAll())
                .filter((s) -> Pattern.matches(name, s.Name))
                .collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();
        playlistStream.forEach((playlist -> stringBuilder.append(playlist).append("\n")));
        String playlist = stringBuilder.toString();

        return Optional.of(playlist)
                .filter(s-> !s.isEmpty())
                .orElse("There are not playlist with that name \n");
    }
}