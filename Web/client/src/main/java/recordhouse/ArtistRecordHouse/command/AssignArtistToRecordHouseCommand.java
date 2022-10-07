package recordhouse.ArtistRecordHouse.command;

import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.ExactMatchRegexCommandMatcher;
import recordhouse.shared.command.String.StringCommand;
import service.ArtistRecordHouse.ArtistRecordHouseRelationService;
import service.ArtistRecordHouse.ArtistRecordHouseRelationViewModel;

@Service
public class AssignArtistToRecordHouseCommand extends StringCommand {

    private final ArtistRecordHouseRelationService service;
    public AssignArtistToRecordHouseCommand(ArtistRecordHouseRelationService service) {
        super("assign artist to recordhouse", "Assign an artist to a record house.");
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("^assign a:[a-zA-Z0-9-]+ to r:[a-zA-Z0-9-]+$"));
        this.service = service;
    }

    @Override
    public String Execute(String input) {
        String[] splitInput = input.split(" ");
        String artistId = splitInput[1].replaceFirst("a:", "");
        String recordHouseId = splitInput[3].replaceFirst("r:", "");

        var relation = new ArtistRecordHouseRelationViewModel();
        relation.artistId = artistId;
        relation.recordHouseId = recordHouseId;
        service.Add(relation);
        return "Relation created.";
    }
}