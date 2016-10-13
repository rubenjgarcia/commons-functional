package es.rubenjgarcia.commons.functional;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public interface FunctionalExceptions {

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

    static <T> Predicate<T> rethrowPredicate(Predicate_WithExceptions<T> predicate) {
        return t -> {
            try {
                return predicate.test(t);
            } catch (Exception exception) {
                throwAsUnchecked(exception);
                return false;
            }
        };
    }

    static <T> Supplier<T> rethrowSupplier(Supplier_WithExceptions<T> supplier) {
        return () -> {
            try {
                return supplier.get();
            } catch (Exception exception) {
                throwAsUnchecked(exception);
                return null;
            }
        };
    }

    static <E extends Throwable> void throwAsUnchecked(Exception exception) throws E {
        throw (E) exception;
    }

    @FunctionalInterface
    interface Function_WithExceptions<T, R> {
        R apply(T t) throws Exception;
    }

    @FunctionalInterface
    interface Consumer_WithExceptions<T> {
        void accept(T t) throws Exception;
    }

    @FunctionalInterface
    interface Predicate_WithExceptions<T> {
        boolean test(T t) throws Exception;
    }

    @FunctionalInterface
    interface Supplier_WithExceptions<T> {
        T get() throws Exception;
    }
}