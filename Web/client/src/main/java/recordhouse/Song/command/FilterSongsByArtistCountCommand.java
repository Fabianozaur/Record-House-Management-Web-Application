package recordhouse.Song.command;

import design.converter.Converter;
import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.ExactMatchRegexCommandMatcher;
import recordhouse.shared.command.String.StringCommand;
import service.Song.SongService;
import service.Song.SongViewModel;
import service.SongArtist.SongArtistRelationService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class FilterSongsByArtistCountCommand extends StringCommand {
    private final SongService songService;
    private final SongArtistRelationService songArtistService;
    private final Converter<SongViewModel, String> printer;
    public FilterSongsByArtistCountCommand(SongService service, SongArtistRelationService songArtistService, Converter<SongViewModel, String> printer){
        super("filter song:artists", "Filter a song by artist count");
        this.printer = printer;
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("^filter song:artists [>=<] [0-9]+$"));
        this.songService = service;
        this.songArtistService = songArtistService;
    }

    @Override
    public String Execute(String input) {
        String[] splitInput = input.split(" ");
        String condition = splitInput[2];
        String criteria = splitInput[3];
        final long number;
        try{
            number = Long.parseLong(criteria);
        }catch(NumberFormatException e) {
            throw new SongCommandException(String.format("Error in filter song command, unable to read number: %s", e.getMessage()));
        }
        Predicate<Long> conditionPredicate = switch (condition) {
            case ">" -> (n) -> n > number;
            case "=" -> (n) -> n == number;
            case "<" -> (n) -> n < number;
            default -> throw new SongCommandException("Invalid condition");
        };

        Map<String, Long> songArtistCount = songArtistService.GetAllSongArtistCounts()
                .entrySet()
                .stream()
                .filter((kvp) -> conditionPredicate.test(kvp.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        List<SongViewModel> filteredSongs = Arrays.stream(songService.GetAll())
        .filter((s) -> songArtistCount.containsKey(s.Id))
        .collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();
        filteredSongs.forEach(s -> stringBuilder.append(printer.ConvertTo(s)).append('\n'));

        var songsString = stringBuilder.toString();

        return Optional.of(songsString)
                .filter(s -> !s.isEmpty())
                .orElse("No songs to show.\n");
    }

}
