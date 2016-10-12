package es.rubenjgarcia.commons.functional.tests;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.stream.Stream;

import static es.rubenjgarcia.commons.functional.FunctionalExceptions.rethrowConsumer;
import static es.rubenjgarcia.commons.functional.FunctionalExceptions.rethrowFunction;
import static es.rubenjgarcia.commons.functional.FunctionalExceptions.rethrowPredicate;
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
}
