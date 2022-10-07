package recordhouse.Artist.command;

import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.ExactMatchRegexCommandMatcher;
import recordhouse.shared.command.String.StringCommand;
import service.Artist.ArtistService;
import service.Artist.ArtistViewModel;
import service.SongArtist.SongArtistRelationService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FindArtistWithMaxMinSongCount extends StringCommand {
    private final ArtistService artistService;
    private final SongArtistRelationService songArtistService;

    public FindArtistWithMaxMinSongCount(ArtistService service, SongArtistRelationService songArtistService){
        super("findArtistWithMinMaxNumberOfSongs", "This command finds the artist with the biggest/smallest number of songs.");
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("^findArtistWithMinMaxNumberOfSongs (min|max)$"));
        this.artistService = service;
        this.songArtistService = songArtistService;
    }

    @Override
    public String Execute(String input) {
        String[] splitInput = input.split(" ");
        String condition = splitInput[1];

        String foundArtist = switch (condition) {
            case "min" -> songArtistService.GetAllArtistSongCounts()
                    .entrySet()
                    .stream()
                    .min(Map.Entry.comparingByValue())
                    .get()
                    .getKey();
            case "max" -> songArtistService.GetAllArtistSongCounts()
                    .entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .get()
                    .getKey();
            default -> throw new ArtistCommandException("Invalid condition!");
        };

        List<ArtistViewModel> filteredArtists = Arrays.stream(artistService.GetAll())
                .filter((a) -> foundArtist.equals(a.artistId))
                .collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();
        filteredArtists.forEach(a -> stringBuilder.append(a).append('\n'));

        var artistsString = stringBuilder.toString();

        return Optional.of(artistsString)
                .filter(s -> !s.isEmpty())
                .orElse("No artists to show!\n");

    }
}
