package design.validation;

public interface Validator<T> {
    // base interface for validator class
    // T - entity to validate
    // If: Entity is Valid - do nothing
    // Otherwise: Throw ValidatorException
    void validate(T entity) throws ValidatorException;
}
