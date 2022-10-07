package recordhouse.Artist.command;

import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.ExactMatchRegexCommandMatcher;
import recordhouse.shared.command.String.StringCommand;
import service.Artist.ArtistService;
import service.Artist.ArtistViewModel;
import service.Song.SongViewModel;
import service.SongArtist.SongArtistRelationService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class FilterArtistBySongCountCommand extends StringCommand {
    private final ArtistService artistService;
    private final SongArtistRelationService songArtistService;

    public FilterArtistBySongCountCommand(ArtistService service, SongArtistRelationService songArtistService){
        super("filterArtistByNumberOfSongs", "This command filters an artist by song count.");
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("^filterArtistByNumberOfSongs [>=<] [0-9]+$"));
        this.artistService = service;
        this.songArtistService = songArtistService;
    }

    @Override
    public String Execute(String input) {
        String[] splitInput = input.split(" ");
        String condition = splitInput[1];
        String criteria = splitInput[2];
        final long number;

        try{
            number = Long.parseLong(criteria);
        }catch(NumberFormatException e) {
            throw new ArtistCommandException(String.format("Error in filter artist command, unable to read number: %s", e.getMessage()));
        }

        Predicate<Long> conditionPredicate = switch (condition) {
            case ">" -> (n) -> n > number;
            case "=" -> (n) -> n == number;
            case "<" -> (n) -> n < number;
            default -> throw new ArtistCommandException("Invalid condition!");
        };

        Map<String, Long> artistSongCount = songArtistService.GetAllArtistSongCounts()
                .entrySet()
                .stream()
                .filter((kvp) -> conditionPredicate.test(kvp.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        List<ArtistViewModel> filteredArtists = Arrays.stream(artistService.GetAll())
                .filter((a) -> artistSongCount.containsKey(a.artistId))
                .collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();
        filteredArtists.forEach(a -> stringBuilder.append(a).append('\n'));

        var artistsString = stringBuilder.toString();

        return Optional.of(artistsString)
                .filter(s -> !s.isEmpty())
                .orElse("No artists to show!\n");
    }
}
