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
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
public class SimpleFilterSongsCommand extends StringCommand {

    private static final String commandRegex = String.format(
            "^filter song %s %s %s$",
            SongFilterPredicateFabricator.attributesRegex,
            SongFilterPredicateFabricator.conditionsRegex,
            SongFilterPredicateFabricator.criteriaRegex
    );


    private final SongService service;
    private final SongFilterPredicateFabricator filterFabricator;
    private final Converter<SongViewModel, String> printer;
    public SimpleFilterSongsCommand(SongService service, SongFilterPredicateFabricator filterFabricator, Converter<SongViewModel, String> printer){
        super("filter song", "Filter a song by duration, likes or publishdate");
        this.printer = printer;
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher(commandRegex));
        this.service = service;
        this.filterFabricator = filterFabricator;
    }

    @Override
    public String Execute(String input) {
        String[] splitInput = input.split(" ");
        String attribute = splitInput[2];
        String condition = splitInput[3];
        String criteria = splitInput[4];
        filterFabricator.ValidateInput(attribute, condition, criteria);

        Predicate<SongViewModel> filter = filterFabricator.FabricatePredicate(attribute, condition, criteria);
        List<SongViewModel> filteredSongs = Arrays.stream(service.GetAll())
                .filter(filter)
                .collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();
        filteredSongs.forEach((s) -> stringBuilder.append(printer.ConvertTo(s)).append("\n"));
        String songsString = stringBuilder.toString();
        return Optional.of(songsString)
                .filter(s -> !s.isEmpty())
                .orElse("No songs to show.\n");
    }
}
