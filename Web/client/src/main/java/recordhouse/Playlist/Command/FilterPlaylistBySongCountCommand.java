package recordhouse.Playlist.Command;


import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.ExactMatchRegexCommandMatcher;
import recordhouse.shared.command.String.StringCommand;
import service.Playlist.PlaylistService;
import service.Playlist.PlaylistViewModel;
import service.PlaylistSong.PlaylistSongRelationService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FilterPlaylistBySongCountCommand extends StringCommand {
    private final PlaylistService playlistService;
    private final PlaylistSongRelationService playlistSongService;

    public FilterPlaylistBySongCountCommand(PlaylistService service,PlaylistSongRelationService playlistSongService)
    {
        super("filterPlaylistByNumberOfSongs","This command will filter a playlist by songs count ");
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("^filterPlaylistByNumberOfSongs [>=<] [0-9]+$"));
        this.playlistService=service;
        this.playlistSongService=playlistSongService;
    }

    @Override
    public String Execute(String input) {
        String[] splitInput = input.split(" ");
        String condition = splitInput[1];
        String criteria = splitInput[2];
        final int number;

        try{
            number = Integer.parseInt(criteria);
        }catch(NumberFormatException e) {
            throw new PlaylistCommandException(String.format("Error in filter playlist command, unable to read number: %s", e.getMessage()));
        }

        Predicate<Long> conditionPredicate=(switch (condition){
            case ">" -> (n) -> n > number;
            case "<" -> (n) -> n < number;
            case "=" -> (n) -> n == number;
            default -> throw new RuntimeException("Invalid operator for condition");
        });

        StringBuilder stringBuilder=new StringBuilder();
        Map<String,Long> playlistSongCount=playlistSongService.GetAllPlaylistSongCounts()
                .entrySet()
                .stream()
                .filter((kvp) -> conditionPredicate.test(kvp.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));

        List<PlaylistViewModel> filteredPlaylists = Arrays.stream(playlistService.GetAll())
                .filter((a) -> playlistSongCount.containsKey(a.Id))
                .collect(Collectors.toList());        filteredPlaylists.forEach((p)-> stringBuilder.append(p).append("\n"));
        String playlistString=stringBuilder.toString();
        return Optional.of(playlistString.toString())
                .filter(s->!s.isEmpty())
                .orElse("No Plalylists to show\n");
    }
}
