package recordhouse.Song.spring;

import design.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import recordhouse.shared.validator.RegexStringValidator;

@Configuration
public class SongSpringConfiguration {

    private static final String entityName = "song";

    @Bean(name = "titleValidator")
    public Validator<String> GetTitleValidator() {
        return new RegexStringValidator("[a-zA-Z0-9]+", "Title must contain only letters and digits.");
    }
}
