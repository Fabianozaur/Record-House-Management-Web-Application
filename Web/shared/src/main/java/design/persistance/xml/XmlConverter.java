package design.persistance.xml;

import design.converter.ReversibleConverter;

public interface XmlConverter<T> extends ReversibleConverter<T, XmlOf<T>> {
}
