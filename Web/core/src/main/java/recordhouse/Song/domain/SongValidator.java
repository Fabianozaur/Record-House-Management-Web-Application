package recordhouse.Song.domain;

import design.validation.Validator;
import design.validation.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import recordhouse.shared.validator.RegexStringValidator;

import java.util.Optional;

@Service
public class SongValidator implements Validator<Song> {

    private Validator<String> titleValidator;

    @Autowired
    public SongValidator(@Qualifier("titleValidator") Validator<String> titleValidator) {
        this.titleValidator = titleValidator;
    }

    @Override
    public void validate(Song entity) throws ValidatorException {
        Validator<String> titleValidator = new RegexStringValidator("[a-zA-Z0-9]+", "Title must contain only letters and digits.");
        titleValidator.validate(entity.getTitle());
        Optional.ofNullable(entity)
                .filter(e -> e.getLikes() >= 0)
                .orElseThrow(() -> new ValidatorException("Song likes must be a positive number!"));
        Optional.ofNullable(entity)
                .filter(e -> e.getDuration() >= 0)
                .orElseThrow(() -> new ValidatorException("Duration must be a positive number!"));
    }
}
