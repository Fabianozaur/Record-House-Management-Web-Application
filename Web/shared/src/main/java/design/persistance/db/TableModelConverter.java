package design.persistance.db;

import design.converter.ReversibleConverter;

public interface TableModelConverter<T> extends ReversibleConverter<T, TableModelOf<T>> {
}
