package recordhouse.shared.validator;

import design.validation.Validator;
import design.validation.ValidatorException;

import java.util.Optional;

public class StringLengthValidator implements Validator<String> {
    private final int minimumLength;
    private final String message;

    public StringLengthValidator(int minimumLength, String message) {
        this.minimumLength = minimumLength;
        this.message = message;
    }

    @Override
    public void validate(String entity) throws ValidatorException {
        Optional.of(entity.length() < minimumLength)
                .filter(b -> !b)
                .orElseThrow(() -> new ValidatorException(String.format("%s does not match string length validation standard: %s", entity, message)));
    }
}
