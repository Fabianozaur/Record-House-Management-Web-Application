package recordhouse.Song.converter;

import design.converter.ReversibleConverter;
import org.springframework.stereotype.Service;
import recordhouse.Song.domain.Song;
import service.Song.SongViewModel;

@Service
public class SongViewModelConverter implements ReversibleConverter<Song, SongViewModel> {
    @Override
    public Song ConvertFrom(SongViewModel obj) {
        var song = new Song();
        song.setId(obj.Id);
        song.setTitle(obj.Title);
        song.setDuration(obj.Duration);
        song.setLikes(obj.Likes);
        song.setPublishDate(obj.PublishDate);
        return song;
    }

    @Override
    public SongViewModel ConvertTo(Song obj) {
        var model = new SongViewModel();
        model.Id = obj.getId();
        model.Title = obj.getTitle();
        model.Duration = obj.getDuration();
        model.Likes = obj.getLikes();
        model.PublishDate = obj.getPublishDate();

        return model;
    }
}
