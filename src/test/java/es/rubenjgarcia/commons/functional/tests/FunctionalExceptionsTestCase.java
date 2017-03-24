package es.rubenjgarcia.commons.functional.tests;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;
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
    public void testRethrowBiFunction() {
        assertThrows(UnsupportedEncodingException.class, () -> {
            Map<String, String> map = new HashMap<>();
            map.put("a", "b");
            map.replaceAll(rethrowBiFunction((k, v) -> new String(k.getBytes(), "Foo")));
        });
    }

    @Test
    public void testRethrowConsumer() {
        assertThrows(UnsupportedEncodingException.class, () -> {
            Stream.of("a", "b").forEach(rethrowConsumer(s -> new String(s.getBytes(), "Foo")));
        });
    }

    @Test
    public void testRethrowBiConsumer() {
        assertThrows(UnsupportedEncodingException.class, () -> {
            Map<String, String> map = new HashMap<>();
            map.put("a", "b");
            map.forEach(rethrowBiConsumer((k, v) -> new String(k.getBytes(), "Foo")));
        });
    }

    @Test
    public void testRethrowPredicate() {
        assertThrows(UnsupportedEncodingException.class, () -> {
            Stream.of("a", "b").filter(rethrowPredicate(p -> new String(p.getBytes(), "Foo").equals("F"))).count();
        });
    }

    @Test
    public void testRethrowBiPredicate() {
        assertThrows(UnsupportedEncodingException.class, () -> {
            BiPredicate<String, String> predicate = rethrowBiPredicate((s, e) -> new String(s.getBytes(), e).isEmpty());
            predicate.test("a", "Foo");
        });
    }

    @Test
    public void testRethrowSupplier() {
        assertThrows(UnsupportedEncodingException.class, () -> {
            rethrowSupplier(() -> new String("a".getBytes(), "Foo")).get();
        });
    }
}
