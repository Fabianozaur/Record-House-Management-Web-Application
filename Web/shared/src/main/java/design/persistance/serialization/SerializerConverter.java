package design.persistance.serialization;

import design.converter.ReversibleConverter;

public interface SerializerConverter<T> extends ReversibleConverter<T, SerializableOf<T>> {
}
