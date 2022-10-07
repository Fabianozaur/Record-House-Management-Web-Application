package design.persistance.jpa;

import design.converter.ReversibleConverter;

public interface EntityModelConverter<T> extends ReversibleConverter<T, EntityModelOf<T>> {
}
