package recordhouse.shared.validator;

import design.validation.Validator;
import design.validation.ValidatorException;

import java.util.Optional;
import java.util.regex.Pattern;

public class RegexStringValidator implements Validator<String> {

    private String regex;
    private String message;

    public RegexStringValidator(String regex, String message) {
        this.regex = regex;
        this.message = message;
    }

    @Override
    public void validate(String entity) throws ValidatorException {
        Optional.of(Pattern.matches(regex, entity))
                .filter(b -> b)
                .orElseThrow(() -> new ValidatorException(String.format("%s does not match string validation standard: %s", entity, message)));
    }
}
