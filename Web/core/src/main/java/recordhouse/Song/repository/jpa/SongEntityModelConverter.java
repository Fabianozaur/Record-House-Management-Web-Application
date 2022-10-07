package recordhouse.Song.repository.jpa;

import design.persistance.jpa.EntityModelConverter;
import design.persistance.jpa.EntityModelOf;
import org.springframework.stereotype.Service;
import recordhouse.Song.domain.Song;
import recordhouse.Song.domain.SongEntityModel;

@Service
public class SongEntityModelConverter implements EntityModelConverter<Song> {
    @Override
    public Song ConvertFrom(EntityModelOf<Song> obj) {
        SongEntityModel model = (SongEntityModel) obj;
        Song song = new Song();
        song.setId(model.Id);
        song.setTitle(model.Title);
        song.setPublishDate(model.PublishDate);
        song.setLikes(model.Likes);
        song.setDuration(model.Duration);
        return song;
    }

    @Override
    public EntityModelOf<Song> ConvertTo(Song obj) {
        SongEntityModel model = new SongEntityModel();
        model.Id = obj.getId();
        model.Title = obj.getTitle();
        model.PublishDate = obj.getPublishDate();
        model.Duration = obj.getDuration();
        model.Likes = obj.getLikes();
        return model;
    }
}
