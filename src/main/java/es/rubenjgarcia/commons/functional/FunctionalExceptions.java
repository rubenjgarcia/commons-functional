package es.rubenjgarcia.commons.functional;

import java.util.function.Consumer;
import java.util.function.Function;

public interface FunctionalExceptions {

    @FunctionalInterface
    interface Function_WithExceptions<T, R> {
        R apply(T t) throws Exception;
    }

    @FunctionalInterface
    interface Consumer_WithExceptions<T> {
        void accept(T t) throws Exception;
    }

    static <T, R> Function<T, R> rethrowFunction(Function_WithExceptions<T, R> function) {
        return t -> {
            try {
                return function.apply(t);
            } catch (Exception exception) {
                throwAsUnchecked(exception);
                return null;
            }
        };
    }

    static <T> Consumer<T> rethrowConsumer(Consumer_WithExceptions<T> consumer) {
        return t -> {
            try {
                consumer.accept(t);
            } catch (Exception exception) {
                throwAsUnchecked(exception);
            }
        };
    }

    static <E extends Throwable> void throwAsUnchecked(Exception exception) throws E {
        throw (E) exception;
    }
}