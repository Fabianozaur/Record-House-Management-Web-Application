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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FindPlaylistShortestLongestCommand extends StringCommand {
    private final PlaylistService playlistService;
    private final PlaylistSongRelationService playlistSongService;

    public FindPlaylistShortestLongestCommand(PlaylistService service,PlaylistSongRelationService relationService)
    {
        super("findLongestShortestPlaylists","find the longest/Shortest songs");
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("^findLongestShortestPlaylists (longest|shortest)$"));
        this.playlistService=service;
        this.playlistSongService=relationService;
    }

    @Override
    public String Execute(String input) {

        String[] splitInput = input.split(" ");
        String condition = splitInput[1];

        String foundPlaylist=switch (condition){
            case "longest" -> playlistSongService.GetAllPlaylistSongCounts()
                    .entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .get()
                    .getKey();
            case "shortest" -> playlistSongService.GetAllPlaylistSongCounts()
                    .entrySet()
                    .stream()
                    .min(Map.Entry.comparingByValue())
                    .get()
                    .getKey();
            default -> throw new PlaylistCommandException("Invalid condition!");
        };
        StringBuilder stringBuilder = new StringBuilder();
        List<PlaylistViewModel> filteredPlaylists = Arrays.stream(playlistService.GetAll())
                .filter((a) -> foundPlaylist.equals(a.Id))
                .collect(Collectors.toList());

        filteredPlaylists.forEach((s) -> stringBuilder.append(s).append("\n"));
        String songsString = stringBuilder.toString();

        return Optional.of(songsString)
                .filter(s -> !s.isEmpty())
                .orElse("No playlists to show!\n");
    }
}
