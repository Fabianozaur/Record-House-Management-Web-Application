package design.domain;

import design.converter.ReversibleConverter;

public interface RelationConverter<R extends Relation<String, String>> extends ReversibleConverter<Composite<String, String>, R> {
}
