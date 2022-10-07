package design.domain;

import java.io.Serializable;
import java.util.Optional;

public class Composite<A, B> implements Serializable {
    public final A first;
    public final B second;

    public Composite(A first, B second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return String.format("{ %s, %s }", first, second);
    }

    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + second.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        return Optional.of
                (
                        Optional.of(
                                Optional.ofNullable(o)
                                        .filter(obj -> obj == this)
                                        .isPresent()
                        )
                                .filter(b -> b)
                                .orElse(
                                        Optional.ofNullable(o)
                                                .filter(obj -> obj.getClass() == this.getClass())
                                                .map(obj -> (Composite<A, B>) obj)
                                                .filter(c -> c.first.equals(first) && c.second.equals(second))
                                                .isPresent()
                                )
                )
                .filter(b -> b)
                .orElse(false);
    }
}
