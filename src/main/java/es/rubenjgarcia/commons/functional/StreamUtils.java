package es.rubenjgarcia.commons.functional;

import es.rubenjgarcia.commons.functional.tuple.Tuple2;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static es.rubenjgarcia.commons.functional.tuple.Tuple.tuple;

public interface StreamUtils {

    static <A, B> Stream<Tuple2<A, B>> zip(Stream<A> sa, Stream<B> sb) {
        Spliterator<? extends A> aSpliterator = sa.spliterator();
        Spliterator<? extends B> bSpliterator = sb.spliterator();

        Iterator<A> itA = Spliterators.iterator(aSpliterator);
        Iterator<B> itB = Spliterators.iterator(bSpliterator);
        Iterator<Tuple2<A, B>> cIterator = new Iterator<Tuple2<A, B>>() {
            @Override
            public boolean hasNext() {
                return itA.hasNext() && itB.hasNext();
            }

            @Override
            public Tuple2<A, B> next() {
                return tuple(itA.next(), itB.next());
            }
        };

        // Zipping looses DISTINCT and SORTED characteristics
        int characteristics = aSpliterator.characteristics() & bSpliterator.characteristics() &
                ~(Spliterator.DISTINCT | Spliterator.SORTED);
        long zipSize = ((characteristics & Spliterator.SIZED) != 0)
                ? Math.min(aSpliterator.getExactSizeIfKnown(), bSpliterator.getExactSizeIfKnown())
                : -1;

        Spliterator<Tuple2<A, B>> split = Spliterators.spliterator(cIterator, zipSize, characteristics);
        return (sa.isParallel() || sb.isParallel())
                ? StreamSupport.stream(split, true)
                : StreamSupport.stream(split, false);
    }

    static <A, B> Tuple2<Stream<A>, Stream<B>> unzip(Stream<Tuple2<A, B>> stream) {
        List<Tuple2<A, B>> tuples = stream.collect(Collectors.toList());

        Stream<A> aStream = tuples.stream().map(t -> t._1);
        Stream<B> bStream = tuples.stream().map(t -> t._2);
        return tuple(aStream, bStream);
    }
}
