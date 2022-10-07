package recordhouse.Song.command;

import design.converter.Converter;
import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.StringCommand;
import service.Song.SongService;
import service.Song.SongViewModel;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ShowSongsCommand extends StringCommand {

    private SongService service;
    private final Converter<SongViewModel, String> printer;
    public ShowSongsCommand(SongService service, Converter<SongViewModel, String> printer){
        super("show songs", "Show the songs.");
        this.service = service;
        this.printer = printer;
    }

    @Override
    public String Execute(String input) {
        List<SongViewModel> songs = Arrays.asList(service.GetAll());
        StringBuilder stringBuilder = new StringBuilder();
        songs.forEach((s) -> stringBuilder.append(printer.ConvertTo(s)).append("\n"));
        String songsString = stringBuilder.toString();
        return Optional.of(songsString)
                .filter(s -> !s.isEmpty())
                .orElse("No songs to show.\n");
    }
}
