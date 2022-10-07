package recordhouse.Playlist.spring;

import design.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import recordhouse.shared.validator.RegexStringValidator;

@Configuration
public class PlaylistSpringConfiguration {
    private static final String entityName="playlist";
    @Bean(name = "PlaylistNameValidator")
    public Validator<String> getPlaylistNameValidator()
    {
        return new RegexStringValidator("[a-zA-Z0-9]+", "Name must contain only letters and digits.");
    }
}
