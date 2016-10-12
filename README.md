# Commons functional

A collection of Functional helpers

## Tuples

From 1 to 10 fields

```
import es.rubenjgarcia.commons.functional.tuple.*;
import static es.rubenjgarcia.commons.functional.tuple.Tuple.tuple;

Tuple2 t2 = tuple("1", "2");
t2.toString(); // (1, 2)
t2.equals(tuple("1", "2")); // true
```

## Stream Utils

### zip

Returns a list of [Tuple2](src/main/java/es/rubenjgarcia/commons/functional/tuple/Tuple2.java) formed from two streams by combining corresponding elements in pairs. If one of the two collections is longer than the other, its remaining elements are ignored.

```
import static es.rubenjgarcia.commons.functional.StreamUtils.zip;

List<Integer> ints = Arrays.asList(1, 2, 3);
List<String> strings = Arrays.asList("a", "b", "c");

List<Tuple2<Integer, String>> tuples = zip(ints.stream(), strings.stream()).collect(Collectors.toList());
tuples.toString(); // [(1, a), (2, b), (3, c)]
```

### zipWithIndex

```
import static es.rubenjgarcia.commons.functional.StreamUtils.zipWithIndex;

List<String> strings = Arrays.asList("1", "2", "3");
List<Tuple2<Long, String>> tuples = zipWithIndex(strings.stream()).collect(Collectors.toList());
```

### unzip

Converts a collection of [Tuple2](src/main/java/es/rubenjgarcia/commons/functional/tuple/Tuple2.java) into a [Tuple2](src/main/java/es/rubenjgarcia/commons/functional/tuple/Tuple2.java) of stream of the first and second half of each pair

```
import es.rubenjgarcia.commons.functional.tuple.*;
import static es.rubenjgarcia.commons.functional.StreamUtils.unzip;

Tuple2<Stream<Integer>, Stream<String>> tupleStream = unzip(tuples);
List<Integer> ints = tupleStream._1.collect(Collectors.toList());
List<String> strings = tupleStream._2.collect(Collectors.toList());
```

## Functional Filters

### anyOf

Return if the value matches any predicate

```
import static es.rubenjgarcia.commons.functional.FunctionalFilters.anyOf;

Predicate<Integer> p = anyOf(n -> n == 1, n -> n == 2);
p.test(1); // true
p.test(3); // false
```

### noneOf

Return if the value doesn't match any of the predicates

```
import static es.rubenjgarcia.commons.functional.FunctionalFilters.noneOf;

Predicate<Integer> p = noneOf(n -> n == 1, n -> n == 2);
p.test(1); // false
p.test(3); // true
```

### allOf

Return if the value matches all the predicates

```
import static es.rubenjgarcia.commons.functional.FunctionalFilters.allOf;

Predicate<Integer> p = allOf(n -> n > 1, n -> n < 3);
p.test(1); // false
p.test(2); // true
```

### findFirst

Find the first element that matchs the predicate

```
import static es.rubenjgarcia.commons.functional.FunctionalFilters.findFirst;

Optional<Integer> found = findFirst(Stream.of(1, 2), n -> n == 1);
found.isPresent(); // true
found.get(); 1
```

### findLast

Finds the last element that matchs the predicate

```
import static es.rubenjgarcia.commons.functional.FunctionalFilters.findLast;

Optional<Integer> last = findLast(Stream.of(1, 2, 3), p -> p > 1 && p <= 2);
last.isPresent(); // true
last.get(); // 2
```

### findFirstNotMatch

Finds first element that doesn't match the predicate

```
import static es.rubenjgarcia.commons.functional.FunctionalFilters.findFirstNotMatch;

Optional<Integer> found = findFirstNotMatch(Stream.of(1, 2), n -> n == 1);
found.isPresent(); // true
found.get(); // 2
```

## FlowStream

### mapIf

Apply a map function to the element of a stream if the element matches the predicate

```
import static es.rubenjgarcia.commons.functional.FlowStream.mapIf;

List<Integer> list = Arrays.asList(1, 1, 1, 1);
List<Object> result = mapIf(list::stream, i -> i == 1, i -> "A")
        .collect(Collectors.toList()); // [A, A, A, A]
```

```
List<Integer> list = Arrays.asList(1, 2, 2, 1);
List<Object> result = mapIf(list::stream, i -> i == 1, i -> "A")
        .collect(Collectors.toList()); // [A, 2, 2, A]
```

### elseIfMap

Apply a map function to the element of a stream if the element matches the predicate after a mapIf code

```
import static es.rubenjgarcia.commons.functional.FlowStream.mapIf;

List<Integer> list = Arrays.asList(1, 2, 3, 1);
List<Object> result = mapIf(list::stream, i -> i == 1, i -> "A")
        .elseIfMap(i -> i == 2, i -> "B")
        .collect(Collectors.toList()); // [A, B, 3, A]
```

```
import static es.rubenjgarcia.commons.functional.FlowStream.mapIf;

List<Integer> list = Arrays.asList(1, 2, 3, 1);
List<Object> result = mapIf(list::stream, i -> i == 1, i -> "A")
        .elseIfMap(i -> i == 2, i -> "B")
        .elseIfMap(i -> i == 3, i -> "C")
        .collect(Collectors.toList()); // [A, B, C, A]
```

### elseMap

Apply a map function to the elements of a stream that don't match any of the other if or elseif predicates

```
import static es.rubenjgarcia.commons.functional.FlowStream.mapIf;

List<Integer> list = Arrays.asList(1, 2, 3, 1);
List<Object> result = mapIf(list::stream, i -> i == 1, i -> "A")
        .elseIfMap(i -> i == 2, i -> "B")
        .elseMap(i -> "C")
        .collect(Collectors.toList()); // [A, B, C, A]
```

```
import static es.rubenjgarcia.commons.functional.FlowStream.mapIf;

List<Integer> list = Arrays.asList(1, 2, 3, 1);
List<Object> result = mapIf(list::stream, i -> i == 1, i -> "A")
        .elseMap(i -> "C")
        .collect(Collectors.toList()); // [A, C, C, A]
```

### mapIfAny

Apply a map function to all the elements of the stream if any of them match the predicate

```
import static es.rubenjgarcia.commons.functional.FlowStream.mapIfAny;

List<Integer> list = Arrays.asList(1, 2, 2, 1);
List<Object> result = mapIfAny(list::stream, i -> i == 1, i -> "A")
        .collect(Collectors.toList()); // [A, A, A, A]
```

### elseIfAnyMap

Apply a map function to all the elements of the stream if any of them match the predicate and don't match the other predicates

```
import static es.rubenjgarcia.commons.functional.FlowStream.mapIfAny;

List<Integer> list = Arrays.asList(1, 1, 1, 1);
List<Object> result = mapIfAny(list::stream, i -> i == 2, i -> "A")
        .elseIfAnyMap(i -> i == 1, i -> "B")
        .collect(Collectors.toList()); // [B, B, B, B]
```

```
import static es.rubenjgarcia.commons.functional.FlowStream.mapIfAny;

List<Integer> list = Arrays.asList(1, 2, 3, 1);
List<Object> result = mapIfAny(list::stream, i -> i == 2, i -> "A")
        .elseIfAnyMap(i -> i == 4, i -> "B")
        .elseIfAnyMap(i -> i == 1, i -> "C")
        .collect(Collectors.toList()); // [C, C, C, C]
```

You can combine any of the flows

```
import static es.rubenjgarcia.commons.functional.FlowStream.mapIf;

List<Integer> list = Arrays.asList(1, 2, 1, 1);
List<Object> result = mapIf(list::stream, i -> i == 2, i -> "A")
        .elseIfAnyMap(i -> i == 1, i -> "B")
        .elseMap(i -> "C")
        .collect(Collectors.toList()); // [B, A, B, B]
```

## Exceptions 

You can use `rethrowFunction`, `rethrowConsumer` or `rethrowPredicate` to deal with [Function](https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html), [Consumer](https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html) or [Predicate](https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html) that throws any [Exception](https://docs.oracle.com/javase/8/docs/api/java/lang/Exception.html) 

```
import static es.rubenjgarcia.commons.functional.FunctionalExceptions.rethrowFunction;

Stream.of("a", "b").map(rethrowFunction(s -> new String(s.getBytes(), "Foo")));
```

```
import static es.rubenjgarcia.commons.functional.FunctionalExceptions.rethrowConsumer;

Stream.of("a", "b").forEach(rethrowConsumer(s -> new String(s.getBytes(), "Foo")));
```

```
import static es.rubenjgarcia.commons.functional.FunctionalExceptions.rethrowPredicate;

Stream.of("a", "b").filter(rethrowPredicate(s -> new String(s.getBytes(), "Foo") == null));
```

# Installation

Just add to your pom.xml

```
<dependency>
    <groupId>es.rubenjgarcia</groupId>
    <artifactId>commons-functional</artifactId>
    <version>0.2.0-SNAPSHOT</version>
</dependency>
```