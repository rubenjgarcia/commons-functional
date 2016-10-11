# commons-functional

Commons functional - A collection of Functional helpers

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

```
import static es.rubenjgarcia.commons.functional.StreamUtils.zip;

List<Integer> ints = Arrays.asList(1, 2, 3);
List<String> strings = Arrays.asList("a", "b", "c");

List<Tuple2<Integer, String>> tuples = zip(ints.stream(), strings.stream()).collect(Collectors.toList());
tuples.toString(); // [(1, a), (2, b), (3, c)]
```

### unzip

```
import es.rubenjgarcia.commons.functional.tuple.*;
import static es.rubenjgarcia.commons.functional.StreamUtils.unzip;

Tuple2<Stream<Integer>, Stream<String>> tupleStream = unzip(tuples);
List<Integer> ints = tupleStream._1.collect(Collectors.toList());
List<String> strings = tupleStream._2.collect(Collectors.toList());
```

## Functional Filters

### anyOf

```
Predicate<Integer> p = anyOf(n -> n == 1, n -> n == 2);
p.test(1); // true
p.test(3); // false
```

### noneOf

```
Predicate<Integer> p = noneOf(n -> n == 1, n -> n == 2);
p.test(1); // false
p.test(3); // true
```

### allOf

```
Predicate<Integer> p = allOf(n -> n > 1, n -> n < 3);
p.test(1); // false
p.test(2); // true
```

## FlowStream

### mapIf

Apply a map function to the element of a stream if the element matches the predicate

```
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
List<Integer> list = Arrays.asList(1, 2, 3, 1);
List<Object> result = mapIf(list::stream, i -> i == 1, i -> "A")
        .elseIfMap(i -> i == 2, i -> "B")
        .collect(Collectors.toList()); // [A, B, 3, A]
```

```
List<Integer> list = Arrays.asList(1, 2, 3, 1);
List<Object> result = mapIf(list::stream, i -> i == 1, i -> "A")
        .elseIfMap(i -> i == 2, i -> "B")
        .elseIfMap(i -> i == 3, i -> "C")
        .collect(Collectors.toList()); // [A, B, C, A]
```

### elseMap

Apply a map function to the elements of a stream that don't match any of the other if or elseif predicates

```
List<Integer> list = Arrays.asList(1, 2, 3, 1);
List<Object> result = mapIf(list::stream, i -> i == 1, i -> "A")
        .elseIfMap(i -> i == 2, i -> "B")
        .elseMap(i -> "C")
        .collect(Collectors.toList()); // [A, B, C, A]
```

```
List<Integer> list = Arrays.asList(1, 2, 3, 1);
List<Object> result = mapIf(list::stream, i -> i == 1, i -> "A")
        .elseMap(i -> "C")
        .collect(Collectors.toList()); // [A, C, C, A]
```

### mapIfAny

Apply a map function to all the elements of the stream if any of them match the predicate

```
List<Integer> list = Arrays.asList(1, 2, 2, 1);
List<Object> result = mapIfAny(list::stream, i -> i == 1, i -> "A")
        .collect(Collectors.toList()); // [A, A, A, A]
```

### elseIfAnyMap

Apply a map function to all the elements of the stream if any of them match the predicate and don't match the other predicates

```
List<Integer> list = Arrays.asList(1, 1, 1, 1);
List<Object> result = mapIfAny(list::stream, i -> i == 2, i -> "A")
        .elseIfAnyMap(i -> i == 1, i -> "B")
        .collect(Collectors.toList()); // [B, B, B, B]
```

```
List<Integer> list = Arrays.asList(1, 2, 3, 1);
List<Object> result = mapIfAny(list::stream, i -> i == 2, i -> "A")
        .elseIfAnyMap(i -> i == 4, i -> "B")
        .elseIfAnyMap(i -> i == 1, i -> "C")
        .collect(Collectors.toList()); // [C, C, C, C]
```

You can combine any of the flows

```
List<Integer> list = Arrays.asList(1, 2, 1, 1);
List<Object> result = mapIf(list::stream, i -> i == 2, i -> "A")
        .elseIfAnyMap(i -> i == 1, i -> "B")
        .elseMap(i -> "C")
        .collect(Collectors.toList()); // [B, A, B, B]
```