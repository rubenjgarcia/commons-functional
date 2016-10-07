package es.rubenjgarcia.commons.functional.tests;

import static org.junit.Assert.fail;

public class TestHelper {
    public static <X extends Throwable> Throwable assertThrows(final Class<X> exceptionClass, final Runnable block) {
        try {
            block.run();
        } catch (Throwable ex) {
            if (exceptionClass.isInstance(ex))
                return ex;
        }

        fail("Expected exception not thrown");
        return null;
    }
}
