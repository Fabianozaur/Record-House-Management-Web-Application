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
import java.util.function.Function;

@Service
public class FindSongWithCriteriaCommand extends StringCommand {

    private SongService service;
    private final Converter<SongViewModel, String> printer;
    public FindSongWithCriteriaCommand(SongService service, Converter<SongViewModel, String> printer){
        super("find most song", "Find the most something song.");
        this.printer = printer;
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("find song (liked|recent|oldest|longest|shortest)"));
        this.service = service;
    }

    @Override
    public String Execute(String input) {
        String[] splitInput = input.split(" ");
        String keyword = splitInput[2];

        List<SongViewModel> songs = Arrays.asList(service.GetAll());
        SongViewModel mostSong = GetMapFunction(keyword).apply(songs);
        return String.format("Most %s song is:\n" +
               printer.ConvertTo(mostSong), keyword);
    }

    private Function<List<SongViewModel>, SongViewModel> GetMapFunction(String keyword){
        return switch (keyword) {
            case "liked" -> (list) -> list.stream().reduce(
                    list.stream().findFirst()
                            .orElseThrow(() -> new SongCommandException("Attempt to filter empty song collection.")),
                    (s1, s2) -> Optional.of(s1)
                            .filter((s) -> s.Likes > s2.Likes)
                            .orElse(s2)
            );
            case "recent" -> (list) -> list.stream().reduce(
                    list.stream().findFirst()
                            .orElseThrow(() -> new SongCommandException("Attempt to filter empty song collection.")),
                    (s1, s2) -> Optional.of(s1)
                            .filter((s) -> s.PublishDate.compareTo(s2.PublishDate) < 0)
                            .orElse(s2)
            );
            case "oldest" -> (list) -> list.stream().reduce(
                    list.stream().findFirst()
                            .orElseThrow(() -> new SongCommandException("Attempt to filter empty song collection.")),
                    (s1, s2) -> Optional.of(s1)
                            .filter((s) -> s.PublishDate.compareTo(s2.PublishDate) > 0)
                            .orElse(s2)
            );
            case "longest" -> (list) -> list.stream().reduce(
                    list.stream().findFirst()
                            .orElseThrow(() -> new SongCommandException("Attempt to filter empty song collection.")),
                    (s1, s2) -> Optional.of(s1)
                            .filter((s) -> s.Duration > s2.Duration)
                            .orElse(s2)
            );
            case "shortest" -> (list) -> list.stream().reduce(
                    list.stream().findFirst()
                            .orElseThrow(() -> new SongCommandException("Attempt to filter empty song collection.")),
                    (s1, s2) -> Optional.of(s1)
                            .filter((s) -> s.Duration < s2.Duration)
                            .orElse(s2)
            );
            default -> throw new SongCommandException("Invalid keyword");
        };
    }
}