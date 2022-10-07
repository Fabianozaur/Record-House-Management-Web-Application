package design.converter;

import java.util.function.Function;

// extension to converter interface with conversion from B to A as well
public interface ReversibleConverter<A, B> extends Converter<A, B> {
    // this overload implies using the class data
    A ConvertFrom(B obj);

    // this overload implies giving a conversion method to be used
    default A ConvertFrom(B obj, Function<B, A> converterMethod) {
        return converterMethod.apply(obj);
    }
}
