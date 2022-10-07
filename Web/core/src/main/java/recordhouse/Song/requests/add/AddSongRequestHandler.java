package recordhouse.Song.requests.add;

import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recordhouse.Song.domain.Song;
import recordhouse.Song.requests.SongRequestError;
import recordhouse.shared.repository.Repository;

@Service
public class AddSongRequestHandler implements RequestHandler<AddSongRequest> {

    private final Repository<String, Song> songRepository;

    public AddSongRequestHandler(Repository<String, Song> songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public RequestResult<AddSongRequest> Handle(AddSongRequest command) {
        Song song = new Song();
        song.setTitle(command.Title);
        song.setDuration(command.Duration);
        song.setLikes(command.Likes);
        song.setPublishDate(command.PublishDate);
        var result = songRepository.save(song)
                .orElseThrow(() -> new SongRequestError("Failed to create song!"));

        var response = new AddSongRequestResult();
        response.Result = result;
        return response;
    }
}
