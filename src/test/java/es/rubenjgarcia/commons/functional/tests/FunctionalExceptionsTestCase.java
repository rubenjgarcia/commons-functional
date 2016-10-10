package es.rubenjgarcia.commons.functional.tests;

import org.junit.Test;

import java.util.stream.Stream;

import static es.rubenjgarcia.commons.functional.FunctionalExceptions.rethrowConsumer;
import static es.rubenjgarcia.commons.functional.FunctionalExceptions.rethrowFunction;
import static es.rubenjgarcia.commons.functional.FunctionalExceptions.rethrowPredicate;
import static es.rubenjgarcia.commons.functional.tests.TestHelper.assertThrows;

public class FunctionalExceptionsTestCase {

    @Test
    public void testRethrowFunction() {
        assertThrows(NullPointerException.class, () -> {
            Stream.of(null, 1).map(rethrowFunction(Object::toString)).findFirst();
        });
    }

    @Test
    public void testRethrowConsumer() {
        assertThrows(NullPointerException.class, () -> {
            Stream.of(null, 1).forEach(rethrowConsumer(Object::toString));
        });
    }

    @Test
    public void testRethrowPredicate() {
        assertThrows(NullPointerException.class, () -> {
            Stream.of(null, 1).filter(rethrowPredicate(p -> p.toString() == null)).count();
        });
    }
}
