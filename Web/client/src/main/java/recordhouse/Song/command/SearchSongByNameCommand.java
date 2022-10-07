package recordhouse.Song.command;

import design.converter.Converter;
import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.ExactMatchRegexCommandMatcher;
import recordhouse.shared.command.String.StringCommand;
import service.Song.SongService;
import service.Song.SongViewModel;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SearchSongByNameCommand extends StringCommand {

    private SongService service;
    private final Converter<SongViewModel, String> printer;
    public SearchSongByNameCommand(SongService service, Converter<SongViewModel, String> printer){
        super("search song", "Search for a song with a keyword");
        this.printer = printer;
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("search song like [a-zA-Z0-9]+"));
        this.service = service;
    }

    @Override
    public String Execute(String input) {
        String[] splitInput = input.split(" ");
        String keyword = splitInput[3];
        List<SongViewModel> songs = Arrays.asList(service.GetByName(keyword));

        StringBuilder stringBuilder = new StringBuilder();
        songs.forEach((s) -> stringBuilder.append(printer.ConvertTo(s)).append("\n"));
        String songsString = stringBuilder.toString();
        return Optional.of(songsString)
                .filter(s -> !s.isEmpty())
                .orElse("No songs to show.\n");
    }
}