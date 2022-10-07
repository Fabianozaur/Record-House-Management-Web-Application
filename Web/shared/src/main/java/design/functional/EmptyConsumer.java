package design.functional;

import java.util.function.Consumer;

public class EmptyConsumer<T> implements Consumer<T> {
    @Override
    public void accept(T t) {
    }

    @Override
    public Consumer<T> andThen(Consumer<? super T> after) {
        return new EmptyConsumer<T>();
    }
}
