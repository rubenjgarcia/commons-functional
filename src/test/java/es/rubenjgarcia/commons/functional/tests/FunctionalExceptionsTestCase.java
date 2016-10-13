package es.rubenjgarcia.commons.functional.tests;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.stream.Stream;

import static es.rubenjgarcia.commons.functional.FunctionalExceptions.*;
import static es.rubenjgarcia.commons.functional.tests.TestHelper.assertThrows;

@SuppressWarnings("InjectedReferences")
public class FunctionalExceptionsTestCase {

    @Test
    public void testRethrowFunction() {
        assertThrows(UnsupportedEncodingException.class, () -> {
            Stream.of("a", "b").map(rethrowFunction(s -> new String(s.getBytes(), "Foo"))).findFirst();
        });
    }

    @Test
    public void testRethrowConsumer() {
        assertThrows(UnsupportedEncodingException.class, () -> {
            Stream.of("a", "b").forEach(rethrowConsumer(s -> new String(s.getBytes(), "Foo")));
        });
    }

    @Test
    public void testRethrowPredicate() {
        assertThrows(UnsupportedEncodingException.class, () -> {
            Stream.of("a", "b").filter(rethrowPredicate(p -> new String(p.getBytes(), "Foo") == null)).count();
        });
    }

    @Test
    public void testRethrowSupplier() {
        assertThrows(UnsupportedEncodingException.class, () -> {
            rethrowSupplier(() -> new String("a".getBytes(), "Foo")).get();
        });
    }
}
