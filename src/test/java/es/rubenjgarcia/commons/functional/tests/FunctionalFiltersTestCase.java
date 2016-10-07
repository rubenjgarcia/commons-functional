package es.rubenjgarcia.commons.functional.tests;

import es.rubenjgarcia.commons.functional.FunctionalFilters;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static es.rubenjgarcia.commons.functional.FunctionalFilters.*;
import static org.junit.Assert.*;

public class FunctionalFiltersTestCase {

    @Test
    public void testAnyOf() {
        boolean anyMatch = Stream.of(1).anyMatch(FunctionalFilters.anyOf(n -> n == 1, n -> n == 1));
        assertTrue(anyMatch);

        anyMatch = Stream.of(1).anyMatch(FunctionalFilters.anyOf(n -> n == 1, n -> n == 3));
        assertTrue(anyMatch);

        anyMatch = Stream.of(1).anyMatch(FunctionalFilters.anyOf(n -> n == 3, n -> n == 1));
        assertTrue(anyMatch);

        anyMatch = Stream.of(1).anyMatch(FunctionalFilters.anyOf(n -> n == 3, n -> n == 4));
        assertFalse(anyMatch);
    }

    @Test
    public void testBothOf() {
        boolean anyMatch = Stream.of(1).anyMatch(allOf(n -> n == 1, n -> n == 1));
        assertTrue(anyMatch);

        anyMatch = Stream.of(1).anyMatch(allOf(n -> n == 1, n -> n == 3));
        assertFalse(anyMatch);

        anyMatch = Stream.of(1).anyMatch(allOf(n -> n == 3, n -> n == 1));
        assertFalse(anyMatch);

        anyMatch = Stream.of(1).anyMatch(allOf(n -> n == 3, n -> n == 4));
        assertFalse(anyMatch);
    }

    @Test
    public void testNoneOf() {
        boolean anyMatch = Stream.of(1).anyMatch(noneOf(n -> n == 1, n -> n == 1));
        assertFalse(anyMatch);

        anyMatch = Stream.of(1).anyMatch(noneOf(n -> n == 1, n -> n == 3));
        assertFalse(anyMatch);

        anyMatch = Stream.of(1).anyMatch(noneOf(n -> n == 3, n -> n == 1));
        assertFalse(anyMatch);

        anyMatch = Stream.of(1).anyMatch(noneOf(n -> n == 3, n -> n == 4));
        assertTrue(anyMatch);
    }

    @Test
    public void testFindFirst() {
        Optional<Integer> found = findFirst(Stream.of(1, 2), n -> n == 1);
        assertTrue(found.isPresent());
        assertEquals(1, found.get().intValue());

        found = findFirst(Stream.of(1, 2), n -> n == 3);
        assertFalse(found.isPresent());
    }

    @Test
    public void testFindFirstNotMatch() {
        Optional<Integer> found = findFirstNotMatch(Stream.of(1, 2), n -> n == 1);
        assertTrue(found.isPresent());
        assertEquals(2, found.get().intValue());

        found = findFirstNotMatch(Stream.of(1, 1), n -> n == 1);
        assertFalse(found.isPresent());
    }

    @Test
    public void testNoneOfPredicates() {
        boolean noneOf = noneOf(Stream.of(1, 2), n -> n == 3, n -> n == 4);
        assertTrue(noneOf);

        noneOf = noneOf(Stream.of(1, 2), n -> n == 1, n -> n == 4);
        assertFalse(noneOf);

        noneOf = noneOf(Stream.of(1, 2), n -> n == 3, n -> n == 1);
        assertFalse(noneOf);
    }

    @Test
    public void testAnyOfPredicates() {
        boolean anyOf = anyOf(Stream.of(1, 2), n -> n == 3, n -> n == 4);
        assertFalse(anyOf);

        anyOf = anyOf(Stream.of(1, 2), n -> n == 1, n -> n == 4);
        assertTrue(anyOf);

        anyOf = anyOf(Stream.of(1, 2), n -> n == 3, n -> n == 1);
        assertTrue(anyOf);
    }

    @Test
    public void testFindLast() {
        Optional<Integer> last = findLast(Stream.of(1, 2, 3), p -> p > 1 && p <= 2);
        assertTrue(last.isPresent());
        assertEquals(2, last.get().intValue());

        last = findLast(Stream.of(1, 2, 3), p -> p > 1 && p < 2);
        assertFalse(last.isPresent());
    }

    @Test
    public void testFindFirstPredicate() {
        Predicate<Integer> p1 = n -> n == 1;
        Predicate<Integer> p2 = n -> n > 1;

        Optional<Predicate<Integer>> first = findFirst(1, Arrays.asList(p1, p2));
        assertTrue(first.isPresent());
        assertEquals(p1, first.get());

        first = findFirst(2, Arrays.asList(p1, p2));
        assertTrue(first.isPresent());
        assertEquals(p2, first.get());

        first = findFirst(0, Arrays.asList(p1, p2));
        assertFalse(first.isPresent());
    }
}
