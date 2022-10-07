package recordhouse.ArtistRecordHouse.command;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.StringCommand;
import service.ArtistRecordHouse.ArtistRecordHouseRelationService;
import service.ArtistRecordHouse.ArtistRecordHouseRelationViewModel;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PrintAllArtistRecordHouseRelationsCommand extends StringCommand {
    private final ArtistRecordHouseRelationService service;

    public PrintAllArtistRecordHouseRelationsCommand(ArtistRecordHouseRelationService service) {
        super("printAllArtistRecordHouseRelations", "This command prints all song-artist relations.");
        this.service = service;
    }

    @Override
    public String Execute(String input) {
        List<ArtistRecordHouseRelationViewModel> artists = Arrays.asList(service.GetAll());
        StringBuilder stringBuilder = new StringBuilder();
        artists.forEach((artist) -> stringBuilder.append(artist).append("\n"));
        String artistsString = stringBuilder.toString();

        return Optional.of(artistsString)
                .filter(s -> !s.isEmpty())
                .orElse("There are no artist-record house relations present!\n");
    }
}
