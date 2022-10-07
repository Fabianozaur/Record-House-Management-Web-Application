package recordhouse.Song.requests.delete;

import design.mediator.RequestHandler;
import design.mediator.RequestResult;
import org.springframework.stereotype.Service;
import recordhouse.Song.domain.Song;
import recordhouse.Song.requests.SongRequestError;
import recordhouse.Song.requests.update.UpdateSongRequestResult;
import recordhouse.shared.repository.Repository;

@Service
public class DeleteSongRequestHandler implements RequestHandler<DeleteSongRequest> {

    private final Repository<String, Song> songRepository;
    public DeleteSongRequestHandler(Repository<String, Song> songRepository){
        this.songRepository = songRepository;
    }


    @Override
    public RequestResult<DeleteSongRequest> Handle(DeleteSongRequest command) {
        var result = songRepository.delete(command.Id)
                .orElseThrow(() -> new SongRequestError("Failed to delete song!"));

        var response = new DeleteSongRequestResult();
        response.Result = result;
        return response;
    }
}
