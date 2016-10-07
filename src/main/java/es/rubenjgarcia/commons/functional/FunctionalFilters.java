package es.rubenjgarcia.commons.functional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface FunctionalFilters {

    static <T> Predicate<T> anyOf(Predicate<T> p1, Predicate<T> p2) {
        return anyOf(Arrays.asList(p1, p2)); // TODO Generic array
    }

    static <T> Predicate<T> anyOf(Collection<Predicate<T>> ps) {
        return p -> anyOf(ps.stream(), t -> t.test(p));
    }

    static <T> Predicate<T> allOf(Predicate<T> p1, Predicate<T> p2) {
        return allOf(Arrays.asList(p1, p2)); // TODO Generic array
    }

    static <T> Predicate<T> allOf(Collection<Predicate<T>> ps) {
        return p -> allOf(ps.stream(), t -> t.test(p));
    }

    static <T> Predicate<T> noneOf(Predicate<T> p1, Predicate<T> p2) {
        return p -> !(p1.test(p)) && !(p2.test(p));
    }

    static <T> boolean anyOf(Stream<T> s, Predicate<T>... ps) {
        return anyOf(s, Arrays.asList(ps));
    }
    
    static <T> boolean anyOf(Stream<T> s, Collection<Predicate<T>> ps) {
        return s.anyMatch(t -> ps.stream().anyMatch(p -> p.test(t)));
    }

    static <T> boolean allOf(Stream<T> s, Predicate<T>... ps) {
        return allOf(s, Arrays.asList(ps));
    }

    static <T> boolean allOf(Stream<T> s, Collection<Predicate<T>> ps) {
        return s.allMatch(t -> ps.stream().allMatch(p -> p.test(t)));
    }

    static <T> boolean noneOf(Stream<T> s, Predicate<T>... ps) {
        return noneOf(s, Arrays.asList(ps));
    }
    
    static <T> boolean noneOf(Stream<T> s, Collection<Predicate<T>> ps) {
        return s.noneMatch(t -> ps.stream().anyMatch(p -> p.test(t)));
    }

    static <T> Optional<T> findFirst(Stream<T> s, Predicate<T> p) {
        return s.filter(p).findFirst();
    }

    static <T> Optional<Predicate<T>> findFirst(T o, Collection<Predicate<T>> ps) {
        return ps.stream().filter(p -> p.test(o)).findFirst();
    }

    static <T> Optional<T> findFirstNotMatch(Stream<T> s, Predicate<T> p) {
        return s.filter(t -> !p.test(t)).findFirst();
    }

    static <T> Optional<T> findLast(Stream<T> s, Predicate<T> p) {
        List<T> collect = s.filter(p).collect(Collectors.toList());
        return collect.isEmpty() ? Optional.empty() : Optional.of(collect.get(collect.size() - 1));
    }
}
