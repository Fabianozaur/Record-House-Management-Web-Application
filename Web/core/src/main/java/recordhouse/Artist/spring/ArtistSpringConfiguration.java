package recordhouse.Artist.spring;

import design.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import recordhouse.shared.validator.RegexStringValidator;
import recordhouse.shared.validator.StringLengthValidator;

@Configuration
public class ArtistSpringConfiguration {
    private static final String entityName = "artist";

    @Bean(name="artistNameValidator")
    public Validator<String> GetArtistNameValidator(){
        return new RegexStringValidator("[a-zA-Z]+", "Artist name must contain only letters!");
    }

    @Bean(name="artistNameLengthValidator")
    public Validator<String> GetArtistNameLengthValidator(){
        return new StringLengthValidator(3, "Artist name must contain more than 3 letters!");
    }
}
