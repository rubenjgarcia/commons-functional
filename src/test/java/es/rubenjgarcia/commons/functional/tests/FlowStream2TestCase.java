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
            final List<Integer> list = Arrays.asList(1, 2, 2, 1);
            List<Object> result = mapIf(list::stream, i -> i == 1, i -> "A")
                    .collect(Collectors.toList());
            assertEquals("If condition size", 4, result.size());
            assertEquals("If condition equals", "A", result.get(0));
            assertEquals("If condition equals", 2, result.get(1));
            assertEquals("If condition equals", 2, result.get(2));
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
            final List<Integer> list = Arrays.asList(1, 2, 2, 1);
            List<Object> result = mapIf(list::stream, i -> i == 1, i -> "A")
                    .elseMap(i -> "B")
                    .collect(Collectors.toList());
            assertEquals("If condition size", 4, result.size());
            assertEquals("If condition equals", "A", result.get(0));
            assertEquals("If condition equals", "B", result.get(1));
            assertEquals("If condition equals", "B", result.get(2));
            assertEquals("If condition equals", "A", result.get(3));
        }

//
//        strings = mapIf(ints, i -> i == 1, i -> "A")
//                .elseIfMap(i -> i == 2, i -> "B")
//                .collect(Collectors.toList());
//
//        assertEquals("elseIf condition size", 4, strings.size());
//        assertEquals("elseIf condition equals", "A", strings.get(0));
//
//        strings = mapIf(ints, i -> i == 3, i -> "A")
//                .elseIfMap(i -> i == 2, i -> "B")
//                .elseMap(i -> "C")
//                .collect(Collectors.toList());
//
//        assertEquals("else condition size", 4, strings.size());
//        assertEquals("else condition equals", "C", strings.get(0));
//
//        strings = mapIf(ints, i -> i == 1, i -> "A")
//                .elseIfMap(i -> i == 2, i -> "B")
//                .elseMap(i -> "C")
//                .collect(Collectors.toList());
//
//        assertEquals("else condition size", 4, strings.size());
//        assertEquals("else condition equals", "A", strings.get(0));
//
//        strings = mapIf(ints, i -> i == 2, i -> "A")
//                .elseIfMap(i -> i == 1, i -> "B")
//                .elseMap(i -> "C")
//                .collect(Collectors.toList());
//
//        assertEquals("else condition size", 4, strings.size());
//        assertEquals("else condition equals", "B", strings.get(0));
//
//        ints = Arrays.asList(1, 2, 1, 1);
//        strings = mapIf(ints, i -> i == 1, i -> "A").collect(Collectors.toList());
//        assertEquals("If condition size", 0, strings.size());
//
//        strings = mapIf(ints, i -> i == 2, i -> "A")
//                .elseIfMap(i -> i == 1, i -> "B")
//                .collect(Collectors.toList());
//
//        assertEquals("elseIf condition size", 0, strings.size());
//
//        strings = mapIf(ints, i -> i == 3, i -> "A")
//                .elseIfMap(i -> i == 2, i -> "B")
//                .elseMap(i -> "C")
//                .collect(Collectors.toList());
//
//        assertEquals("else condition size", 4, strings.size());
//        assertEquals("else condition equals", "C", strings.get(0));
    }

    @Test
    public void testFlowStreamIfAny() {
//        List<Integer> ints = Arrays.asList(1, 2, 3, 4);
//        List<String> strings = mapIfAny(ints, i -> i == 1, i -> "A").collect(Collectors.toList());
//        assertEquals("IfAny condition size", 1, strings.size());
//        assertEquals("IfAny condition equals", "A", strings.get(0));
//
//        strings = mapIfAny(ints, i -> i == 1, i -> "A")
//                .elseIfAnyMap(i -> i == 2, i -> "B")
//                .collect(Collectors.toList());
//
//        assertEquals("elseIfAny condition size", 2, strings.size());
//        assertEquals("elseIfAny condition equals", "A", strings.get(0));
//        assertEquals("elseIfAny condition equals", "B", strings.get(1));


    }

    @Test
    public void testFlowStreamIfWithAny() {
//        List<Integer> ints = Arrays.asList(1, 2, 3, 4);
//        List<String> strings = mapIfAny(ints, i -> i == 1, i -> "A")
//                .elseMap(i -> "B")
//                .collect(Collectors.toList());
//
//        assertEquals("else condition size", 4, strings.size());
//        assertEquals("else condition equals", "A", strings.get(0));
//        assertEquals("else condition equals", "B", strings.get(1));
//        assertEquals("else condition equals", "B", strings.get(2));
//        assertEquals("else condition equals", "B", strings.get(3));
//
//        strings = mapIfAny(ints, i -> i == 1, i -> "A")
//                .elseIfAnyMap(i -> i == 2, i -> "B")
//                .collect(Collectors.toList());
//
//        assertEquals("else condition size", 2, strings.size());
//        assertEquals("else condition equals", "A", strings.get(0));
//        assertEquals("else condition equals", "B", strings.get(1));
//
//        strings = mapIfAny(ints, i -> i == 1, i -> "A")
//                .elseIfAnyMap(i -> i == 2, i -> "B")
//                .elseMap(i -> "C")
//                .collect(Collectors.toList());
//
//        assertEquals("else condition size", 4, strings.size());
//        assertEquals("else condition equals", "A", strings.get(0));
//        assertEquals("else condition equals", "B", strings.get(1));
//        assertEquals("else condition equals", "C", strings.get(2));
//        assertEquals("else condition equals", "C", strings.get(3));
//
//        strings = mapIf(ints, i -> i == 1, i -> "A")
//                .elseIfAnyMap(i -> i == 2, i -> "B")
//                .elseMap(i -> "C")
//                .collect(Collectors.toList());
//
//        assertEquals("else condition size", 4, strings.size());
//        assertEquals("else condition equals", "C", strings.get(0));
//        assertEquals("else condition equals", "B", strings.get(1));
//        assertEquals("else condition equals", "C", strings.get(2));
//        assertEquals("else condition equals", "C", strings.get(3));
//
//        ints = Arrays.asList(1, 2, 2, 1);
//        strings = mapIf(ints, i -> i == 1, i -> "A")
//                .elseIfAnyMap(i -> i == 2, i -> "B")
//                .elseMap(i -> "C")
//                .collect(Collectors.toList());
//
//        assertEquals("else condition size", 4, strings.size());
//        assertEquals("else condition equals", "A", strings.get(0));
//        assertEquals("else condition equals", "B", strings.get(1));
//        assertEquals("else condition equals", "B", strings.get(2));
//        assertEquals("else condition equals", "A", strings.get(3));
    }
}
