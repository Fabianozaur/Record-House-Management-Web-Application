package recordhouse.Song;

import design.converter.Converter;
import org.springframework.stereotype.Service;
import service.Song.SongViewModel;

@Service
public class SongViewModelPrinter implements Converter<SongViewModel, String> {
    @Override
    public String ConvertTo(SongViewModel obj) {
        return String.format("Song { " +
                "Id: %s " +
                "Title: %s " +
                "Publish Date: %s " +
                "Likes: %d " +
                "Duration: %d "+
                "}",
                obj.Id,
                obj.Title,
                obj.PublishDate,
                obj.Likes,
                obj.Duration
        );
    }
}
