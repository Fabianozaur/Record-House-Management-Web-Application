package recordhouse.Song.command;

import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.ExactMatchRegexCommandMatcher;
import recordhouse.shared.command.String.StringCommand;
import service.Song.SongService;
import service.Song.SongViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UpdateSongCommand extends StringCommand {

    private SongService service;
    public UpdateSongCommand(SongService service){
        super("update song", "Update a song");
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("update song ([a-zA-Z0-9]|-)+ [a-zA-Z]+ [1-9][0-9]* [1-9][0-9]* [0-9]{1,2}/[0-9]{1,2}/[1-9][0-9]{0,3}"));
        this.service = service;
    }

    @Override
    public String Execute(String input) {
        String[] splitInput = input.split(" ");
        String id = splitInput[2];
        String title = splitInput[3];
        int duration = Integer.parseInt(splitInput[4]);
        int likes = Integer.parseInt(splitInput[5]);
        Date publishDate;
        try{
            publishDate = new SimpleDateFormat("dd/mm/yyyy").parse(splitInput[6]);
        }catch(ParseException e) {
            throw new SongCommandException(String.format("Error in update song command, unable to read date: %s", e.getMessage()));
        }
        var model = new SongViewModel();
        model.Id = id;
        model.Title = title;
        model.PublishDate = publishDate;
        model.Duration = duration;
        model.Likes = likes;
        service.Update(model);
        return "";

    }
}
