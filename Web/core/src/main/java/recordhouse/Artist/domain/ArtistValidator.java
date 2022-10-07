package recordhouse.Artist.domain;


import design.validation.Validator;
import design.validation.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ArtistValidator implements Validator<Artist> {
    private final Validator<String> artistNameValidator;
    private final Validator<String> artistNameLengthValidator;

    @Autowired
    public ArtistValidator(@Qualifier("artistNameValidator") Validator<String> nameValidator,
                           @Qualifier("artistNameLengthValidator") Validator<String> nameLengthValidator){
        this.artistNameValidator = nameValidator;
        this.artistNameLengthValidator = nameLengthValidator;
    }

    @Override
    public void validate(Artist entity) throws ValidatorException {
        this.artistNameValidator.validate(entity.getFirstName());
        this.artistNameValidator.validate(entity.getLastName());
        this.artistNameLengthValidator.validate(entity.getFirstName());
        this.artistNameLengthValidator.validate(entity.getLastName());
    }
}
