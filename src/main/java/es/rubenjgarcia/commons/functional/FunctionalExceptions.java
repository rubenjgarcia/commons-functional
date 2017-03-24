package es.rubenjgarcia.commons.functional;

import java.util.function.*;

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

    static <T, U, R> BiFunction<T, U, R> rethrowBiFunction(BiFunction_WithExceptions<T, U, R> function) {
        return (t, u) -> {
            try {
                return function.apply(t, u);
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

    static <T, U> BiConsumer<T, U> rethrowBiConsumer(BiConsumer_WithExceptions<T, U> consumer) {
        return (t, u) -> {
            try {
                consumer.accept(t, u);
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

    static <T, U> BiPredicate<T, U> rethrowBiPredicate(BiPredicate_WithExceptions<T, U> predicate) {
        return (t, u) -> {
            try {
                return predicate.test(t, u);
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
    interface BiFunction_WithExceptions<T, U, R> {
        R apply(T t, U u) throws Exception;
    }

    @FunctionalInterface
    interface Consumer_WithExceptions<T> {
        void accept(T t) throws Exception;
    }

    @FunctionalInterface
    interface BiConsumer_WithExceptions<T, U> {
        void accept(T t, U u) throws Exception;
    }

    @FunctionalInterface
    interface Predicate_WithExceptions<T> {
        boolean test(T t) throws Exception;
    }

    @FunctionalInterface
    interface BiPredicate_WithExceptions<T, U> {
        boolean test(T t, U u) throws Exception;
    }

    @FunctionalInterface
    interface Supplier_WithExceptions<T> {
        T get() throws Exception;
    }
}