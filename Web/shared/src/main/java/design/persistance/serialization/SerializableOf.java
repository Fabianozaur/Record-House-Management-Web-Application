package design.persistance.serialization;

import java.io.Serializable;

public interface SerializableOf<T> extends Serializable {
    // A Serializable Object has:
    // Only serializable fields
    // Meaning, primitive data types: int, long, byte, char
    // or 'String' or 'Date'
    // And Only public fields
}
