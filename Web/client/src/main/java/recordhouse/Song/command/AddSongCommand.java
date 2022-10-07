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
public class AddSongCommand extends StringCommand {

    private SongService service;
    public AddSongCommand(SongService service){
        super("add song", "Add a song");
        super.SetCommandMatcher(new ExactMatchRegexCommandMatcher("add song [a-zA-Z]+ [1-9][0-9]* [1-9][0-9]* [0-9]{1,2}/[0-9]{1,2}/[1-9][0-9]{0,3}"));
        this.service = service;
    }

    @Override
    public String Execute(String input) {
        String[] splitInput = input.split(" ");
        String title = splitInput[2];
        int duration = Integer.parseInt(splitInput[3]);
        int likes = Integer.parseInt(splitInput[4]);
        Date publishDate;
        try{
            publishDate = new SimpleDateFormat("dd/mm/yyyy").parse(splitInput[5]);
        }catch(ParseException e) {
            throw new SongCommandException(String.format("Error in add song command, unable to read date: %s", e.getMessage()));
        }
        var model = new SongViewModel();
        model.Title = title;
        model.PublishDate = publishDate;
        model.Likes = likes;
        model.Duration = duration;
        var result = service.Add(model);
        return String.format("Song created with id: %s", result.Id);
    }
}
