package design.converter;

import java.util.function.Function;

// general interface from converting from A to B
public interface Converter<A, B> {
    // this overload implies using the class data
    B ConvertTo(A obj);

    // this overload implies giving a conversion method to be used
    // default implementation just applies converterMethod to an A object
    default B ConvertTo(A obj, Function<A, B> converterMethod) {
        return converterMethod.apply(obj);
    }
}