package recordhouse.Song.requests.update;

import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.stereotype.Service;
import recordhouse.Song.domain.Song;
import recordhouse.Song.requests.SongRequestError;
import recordhouse.shared.repository.Repository;

@Service
public class UpdateSongRequestHandler implements RequestHandler<UpdateSongRequest> {

    private final Repository<String, Song> songRepository;

    public UpdateSongRequestHandler(Repository<String, Song> songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public RequestResult<UpdateSongRequest> Handle(UpdateSongRequest command) {
        Song song = new Song();
        song.setId(command.Id);
        song.setTitle(command.Title);
        song.setDuration(command.Duration);
        song.setLikes(command.Likes);
        song.setPublishDate(command.PublishDate);
        var result = songRepository.save(song)
                .orElseThrow(() -> new SongRequestError("Failed to update song!"));

        var response = new UpdateSongRequestResult();
        response.Result = result;
        return response;
    }
}
