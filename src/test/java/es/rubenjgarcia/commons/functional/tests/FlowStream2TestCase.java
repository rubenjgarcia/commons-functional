package es.rubenjgarcia.commons.functional.tests;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static es.rubenjgarcia.commons.functional.FlowStream2.mapIf;
import static org.junit.Assert.assertEquals;

public class FlowStream2TestCase {

    @Test
    public void testFlowStreamIf() {
        {
            final List<Integer> list = Arrays.asList(1, 1, 1, 1);
            List<Object> result = mapIf(list::stream, i -> i == 1, i -> "A")
                    .collect(Collectors.toList());
            assertEquals("If condition size", 4, result.size());
            assertEquals("If condition equals", "A", result.get(0));
            assertEquals("If condition equals", "A", result.get(1));
            assertEquals("If condition equals", "A", result.get(2));
            assertEquals("If condition equals", "A", result.get(3));
        }

        {
            final List<Integer> list = Arrays.asList(1, 1, 1, 1);
            List<Object> result = mapIf(list::stream, i -> i == 2, i -> "A")
                    .collect(Collectors.toList());
            assertEquals("If condition size", 4, result.size());
            assertEquals("If condition equals", 1, result.get(0));
            assertEquals("If condition equals", 1, result.get(1));
            assertEquals("If condition equals", 1, result.get(2));
            assertEquals("If condition equals", 1, result.get(3));
        }

        {
            final List<Integer> list = Arrays.asList(1, 2, 3, 1);
            List<Object> result = mapIf(list::stream, i -> i == 1, i -> "A")
                    .collect(Collectors.toList());
            assertEquals("If condition size", 4, result.size());
            assertEquals("If condition equals", "A", result.get(0));
            assertEquals("If condition equals", 2, result.get(1));
            assertEquals("If condition equals", 3, result.get(2));
            assertEquals("If condition equals", "A", result.get(3));
        }

        {
            final List<Integer> list = Arrays.asList(1, 2, 3, 1);
            List<Object> result = mapIf(list::stream, i -> i == 1, i -> "A")
                    .elseIfMap(i -> i == 2, i -> "B")
                    .collect(Collectors.toList());
            assertEquals("If condition size", 4, result.size());
            assertEquals("If condition equals", "A", result.get(0));
            assertEquals("If condition equals", "B", result.get(1));
            assertEquals("If condition equals", 3, result.get(2));
            assertEquals("If condition equals", "A", result.get(3));
        }

        {
            final List<Integer> list = Arrays.asList(1, 2, 3, 1);
            List<Object> result = mapIf(list::stream, i -> i == 1, i -> "A")
                    .elseIfMap(i -> i == 1, i -> "B")
                    .collect(Collectors.toList());
            assertEquals("If condition size", 4, result.size());
            assertEquals("If condition equals", "A", result.get(0));
            assertEquals("If condition equals", 2, result.get(1));
            assertEquals("If condition equals", 3, result.get(2));
            assertEquals("If condition equals", "A", result.get(3));
        }

        {
            final List<Integer> list = Arrays.asList(1, 2, 3, 1);
            List<Object> result = mapIf(list::stream, i -> i == 1, i -> "A")
                    .elseMap(i -> "B")
                    .collect(Collectors.toList());
            assertEquals("If condition size", 4, result.size());
            assertEquals("If condition equals", "A", result.get(0));
            assertEquals("If condition equals", "B", result.get(1));
            assertEquals("If condition equals", "B", result.get(2));
            assertEquals("If condition equals", "A", result.get(3));
        }

        {
            final List<Integer> list = Arrays.asList(1, 2, 3, 1);
            List<Object> result = mapIf(list::stream, i -> i == 1, i -> "A")
                    .elseIfMap(i -> i == 2, i -> "B")
                    .elseMap(i -> "C")
                    .collect(Collectors.toList());
            assertEquals("If condition size", 4, result.size());
            assertEquals("If condition equals", "A", result.get(0));
            assertEquals("If condition equals", "B", result.get(1));
            assertEquals("If condition equals", "C", result.get(2));
            assertEquals("If condition equals", "A", result.get(3));
        }

        {
            final List<Integer> list = Arrays.asList(1, 2, 3, 1);
            List<Object> result = mapIf(list::stream, i -> i == 1, i -> "A")
                    .elseIfMap(i -> i == 1, i -> "B")
                    .elseMap(i -> "C")
                    .collect(Collectors.toList());
            assertEquals("If condition size", 4, result.size());
            assertEquals("If condition equals", "A", result.get(0));
            assertEquals("If condition equals", "C", result.get(1));
            assertEquals("If condition equals", "C", result.get(2));
            assertEquals("If condition equals", "A", result.get(3));
        }

        {
            final List<Integer> list = Arrays.asList(1, 2, 3, 1);
            List<Object> result = mapIf(list::stream, i -> i == 1, i -> "A")
                    .elseIfMap(i -> i == 2, i -> "B")
                    .elseIfMap(i -> i == 3, i -> "C")
                    .elseMap(i -> "D")
                    .collect(Collectors.toList());
            assertEquals("If condition size", 4, result.size());
            assertEquals("If condition equals", "A", result.get(0));
            assertEquals("If condition equals", "B", result.get(1));
            assertEquals("If condition equals", "C", result.get(2));
            assertEquals("If condition equals", "A", result.get(3));
        }

        {
            final List<Integer> list = Arrays.asList(1, 2, 3, 4);
            List<Object> result = mapIf(list::stream, i -> i == 1, i -> "A")
                    .elseIfMap(i -> i == 2, i -> "B")
                    .elseIfMap(i -> i == 3, i -> "C")
                    .elseMap(i -> "D")
                    .collect(Collectors.toList());
            assertEquals("If condition size", 4, result.size());
            assertEquals("If condition equals", "A", result.get(0));
            assertEquals("If condition equals", "B", result.get(1));
            assertEquals("If condition equals", "C", result.get(2));
            assertEquals("If condition equals", "D", result.get(3));
        }

    }
}
