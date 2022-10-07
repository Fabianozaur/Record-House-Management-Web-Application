package recordhouse.Playlist.domain;

import design.validation.Validator;
import design.validation.ValidatorException;
import org.springframework.stereotype.Service;

@Service
public class PlaylistValidator implements Validator<Playlist> {
    @Override
    public void validate(Playlist entity) throws ValidatorException {
    }
}