package es.rubenjgarcia.commons.functional.tests;

import es.rubenjgarcia.commons.functional.tuple.Tuple2;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static es.rubenjgarcia.commons.functional.StreamUtils.unzip;
import static es.rubenjgarcia.commons.functional.StreamUtils.zip;
import static es.rubenjgarcia.commons.functional.tuple.Tuple.tuple;
import static org.junit.Assert.assertEquals;

public class StreamUtilsTestCase {

    @Test
    public void testZip() {
        {
            List<Integer> ints = Arrays.asList(1, 2, 3);
            List<String> strings = Arrays.asList("1", "2", "3");

            List<Tuple2<Integer, String>> tuples = zip(ints.stream(), strings.stream()).collect(Collectors.toList());
            assertEquals(3, tuples.size());
            System.out.println(tuples);
            assertEquals(tuple(1, "1"), tuples.get(0));
            assertEquals(tuple(2, "2"), tuples.get(1));
            assertEquals(tuple(3, "3"), tuples.get(2));
        }

        {
            List<Integer> ints = Arrays.asList(1, 2, 3);
            List<String> strings = Arrays.asList("1", "2");

            List<Tuple2<Integer, String>> tuples = zip(ints.stream(), strings.stream()).collect(Collectors.toList());
            assertEquals(2, tuples.size());
            assertEquals(tuple(1, "1"), tuples.get(0));
            assertEquals(tuple(2, "2"), tuples.get(1));
        }

        {
            List<Integer> ints = Arrays.asList(1, 2);
            List<String> strings = Arrays.asList("1", "2", "3");

            List<Tuple2<Integer, String>> tuples = zip(ints.stream(), strings.stream()).collect(Collectors.toList());
            assertEquals(2, tuples.size());
            assertEquals(tuple(1, "1"), tuples.get(0));
            assertEquals(tuple(2, "2"), tuples.get(1));
        }
    }

    @Test
    public void testUnzip() {
        {
            List<Integer> ints = Arrays.asList(1, 2, 3);
            List<String> strings = Arrays.asList("1", "2", "3");

            Stream<Tuple2<Integer, String>> zip = zip(ints.stream(), strings.stream());
            Tuple2<Stream<Integer>, Stream<String>> unzip = unzip(zip);
            List<Integer> intsUnzip = unzip._1.collect(Collectors.toList());
            List<String> stringsUnzip = unzip._2.collect(Collectors.toList());

            assertEquals(3, intsUnzip.size());
            assertEquals(1, (int) intsUnzip.get(0));
            assertEquals(2, (int) intsUnzip.get(1));
            assertEquals(3, (int) intsUnzip.get(2));

            assertEquals(3, stringsUnzip.size());
            assertEquals("1", stringsUnzip.get(0));
            assertEquals("2", stringsUnzip.get(1));
            assertEquals("3", stringsUnzip.get(2));
        }

        {
            List<Integer> ints = Arrays.asList(1, 2, 3);
            List<String> strings = Arrays.asList("1", "2");

            Stream<Tuple2<Integer, String>> zip = zip(ints.stream(), strings.stream());
            Tuple2<Stream<Integer>, Stream<String>> unzip = unzip(zip);
            List<Integer> intsUnzip = unzip._1.collect(Collectors.toList());
            List<String> stringsUnzip = unzip._2.collect(Collectors.toList());

            assertEquals(2, intsUnzip.size());
            assertEquals(1, (int) intsUnzip.get(0));
            assertEquals(2, (int) intsUnzip.get(1));

            assertEquals(2, stringsUnzip.size());
            assertEquals("1", stringsUnzip.get(0));
            assertEquals("2", stringsUnzip.get(1));
        }

        {
            List<Integer> ints = Arrays.asList(1, 2);
            List<String> strings = Arrays.asList("1", "2", "3");

            Stream<Tuple2<Integer, String>> zip = zip(ints.stream(), strings.stream());
            Tuple2<Stream<Integer>, Stream<String>> unzip = unzip(zip);
            List<Integer> intsUnzip = unzip._1.collect(Collectors.toList());
            List<String> stringsUnzip = unzip._2.collect(Collectors.toList());

            assertEquals(2, intsUnzip.size());
            assertEquals(1, (int) intsUnzip.get(0));
            assertEquals(2, (int) intsUnzip.get(1));

            assertEquals(2, stringsUnzip.size());
            assertEquals("1", stringsUnzip.get(0));
            assertEquals("2", stringsUnzip.get(1));
        }
    }
}
