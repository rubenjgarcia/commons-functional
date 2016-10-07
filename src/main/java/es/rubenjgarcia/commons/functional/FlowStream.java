package es.rubenjgarcia.commons.functional;

import es.rubenjgarcia.commons.functional.tuple.Tuple2;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static es.rubenjgarcia.commons.functional.tuple.Tuple.tuple;

public class FlowStream<S, C> implements Stream<S> {

    private final Stream<S> s;
    private Collection<C> c;
    private boolean allMatch;
    private boolean all;
    private List<Tuple2<Predicate<C>, Function<C, S>>> predicateFunctions;

    private FlowStream(Stream<S> s, Collection<C> c, boolean all, Predicate<C> p, Function<C, S> f) {
        this.s = s;
        this.c = c;
        this.all = all;
        this.predicateFunctions = Collections.singletonList(tuple(p, f));
    }

    private FlowStream(Stream<S> s, Collection<C> c, boolean all, List<Tuple2<Predicate<C>, Function<C, S>>> predicateFunctions) {
        this.s = s;
        this.c = c;
        this.all = all;
        this.predicateFunctions = predicateFunctions;
    }

    public static <C, S> FlowStream<S, C> mapIf(Collection<C> c, Predicate<C> p, Function<C, S> mapper) {
        return getScFlowStream(c, p, mapper, true);
    }

    public static <C, S> FlowStream<S, C> mapIfAny(Collection<C> c, Predicate<C> p, Function<C, S> mapper) {
        return getScFlowStream(c, p, mapper, false);
    }

    private static <C, S> FlowStream<S, C> getScFlowStream(Collection<C> c, Predicate<C> p, Function<C, S> mapper, boolean all) {
        if (all && c.stream().allMatch(p)) {
            FlowStream<S, C> rFlowStream = new FlowStream<>(c.stream().filter(p).map(mapper), c, all, p, mapper);
            rFlowStream.allMatch = true;
            return rFlowStream;
        } else if (!all) {
            return new FlowStream<>(c.stream().filter(p).map(mapper), c, all, p, mapper);
        } else {
            return new FlowStream<>(Stream.empty(), c, all, p, mapper);
        }
    }

    public FlowStream<S, C> elseIfMap(Predicate<C> p, Function<C, S> mapper) {
        if (allMatch) {
            return this;
        }

        return getScFlowStream(this.c, p, mapper, true);
    }

    public FlowStream<S, C> elseIfAnyMap(Predicate<C> p, Function<C, S> mapper) {
        if (allMatch) {
            return this;
        }

        if (all) {
            return new FlowStream<>(Stream.concat(this.s, getScFlowStream(this.c, p, mapper, false).s), this.c, false, p, mapper);
        } else {
            Stream<S> sStream = this.c.stream().map(o -> FunctionalFilters.findFirst(predicateFunctions.stream(), pf -> pf._1.test(o))
                    .map(pf -> pf._2.apply(o))
                    .orElseGet(() -> {
                        if (p.test(o)) {
                            return mapper.apply(o);
                        } else {
                            return null;
                        }
                    })).filter(o -> o != null);

            List<Tuple2<Predicate<C>, Function<C, S>>> predicateFunctions = new ArrayList<>(this.predicateFunctions);
            predicateFunctions.add(tuple(p, mapper));
            return new FlowStream<>(sStream, this.c, false, predicateFunctions);
        }
    }

    public Stream<S> elseMap(Function<C, S> mapper) {
        if (allMatch) {
            return this.s;
        }

        if (all) {
            return this.c.stream().map(mapper);
        } else {
            return this.c.stream()
                    .map(o -> FunctionalFilters.findFirst(predicateFunctions.stream(), pf -> pf._1.test(o))
                    .map(pf -> pf._2.apply(o))
                    .orElseGet(() -> mapper.apply(o)));
        }
    }

    @Override
    public Stream<S> filter(Predicate<? super S> predicate) {
        return s.filter(predicate);
    }

    @Override
    public <R> Stream<R> map(Function<? super S, ? extends R> mapper) {
        return s.map(mapper);
    }

    @Override
    public IntStream mapToInt(ToIntFunction<? super S> mapper) {
        return s.mapToInt(mapper);
    }

    @Override
    public LongStream mapToLong(ToLongFunction<? super S> mapper) {
        return s.mapToLong(mapper);
    }

    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super S> mapper) {
        return s.mapToDouble(mapper);
    }

    @Override
    public <R> Stream<R> flatMap(Function<? super S, ? extends Stream<? extends R>> mapper) {
        return s.flatMap(mapper);
    }

    @Override
    public IntStream flatMapToInt(Function<? super S, ? extends IntStream> mapper) {
        return s.flatMapToInt(mapper);
    }

    @Override
    public LongStream flatMapToLong(Function<? super S, ? extends LongStream> mapper) {
        return s.flatMapToLong(mapper);
    }

    @Override
    public DoubleStream flatMapToDouble(Function<? super S, ? extends DoubleStream> mapper) {
        return s.flatMapToDouble(mapper);
    }

    @Override
    public Stream<S> distinct() {
        return s.distinct();
    }

    @Override
    public Stream<S> sorted() {
        return s.sorted();
    }

    @Override
    public Stream<S> sorted(Comparator<? super S> comparator) {
        return s.sorted(comparator);
    }

    @Override
    public Stream<S> peek(Consumer<? super S> action) {
        return s.peek(action);
    }

    @Override
    public Stream<S> limit(long maxSize) {
        return s.limit(maxSize);
    }

    @Override
    public Stream<S> skip(long n) {
        return s.skip(n);
    }

    @Override
    public void forEach(Consumer<? super S> action) {
        s.forEach(action);
    }

    @Override
    public void forEachOrdered(Consumer<? super S> action) {
        s.forEachOrdered(action);
    }

    @Override
    public Object[] toArray() {
        return s.toArray();
    }

    @Override
    public <A> A[] toArray(IntFunction<A[]> generator) {
        return s.toArray(generator);
    }

    @Override
    public S reduce(S identity, BinaryOperator<S> accumulator) {
        return s.reduce(identity, accumulator);
    }

    @Override
    public Optional<S> reduce(BinaryOperator<S> accumulator) {
        return s.reduce(accumulator);
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super S, U> accumulator, BinaryOperator<U> combiner) {
        return s.reduce(identity, accumulator, combiner);
    }

    @Override
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super S> accumulator, BiConsumer<R, R> combiner) {
        return s.collect(supplier, accumulator, combiner);
    }

    @Override
    public <R, A> R collect(Collector<? super S, A, R> collector) {
        return s.collect(collector);
    }

    @Override
    public Optional<S> min(Comparator<? super S> comparator) {
        return s.min(comparator);
    }

    @Override
    public Optional<S> max(Comparator<? super S> comparator) {
        return s.max(comparator);
    }

    @Override
    public long count() {
        return s.count();
    }

    @Override
    public boolean anyMatch(Predicate<? super S> predicate) {
        return s.anyMatch(predicate);
    }

    @Override
    public boolean allMatch(Predicate<? super S> predicate) {
        return s.allMatch(predicate);
    }

    @Override
    public boolean noneMatch(Predicate<? super S> predicate) {
        return s.noneMatch(predicate);
    }

    @Override
    public Optional<S> findFirst() {
        return s.findFirst();
    }

    @Override
    public Optional<S> findAny() {
        return s.findAny();
    }

    @Override
    public Iterator<S> iterator() {
        return s.iterator();
    }

    @Override
    public Spliterator<S> spliterator() {
        return s.spliterator();
    }

    @Override
    public boolean isParallel() {
        return s.isParallel();
    }

    @Override
    public Stream<S> sequential() {
        return s.sequential();
    }

    @Override
    public Stream<S> parallel() {
        return s.parallel();
    }

    @Override
    public Stream<S> unordered() {
        return s.unordered();
    }

    @Override
    public Stream<S> onClose(Runnable closeHandler) {
        return s.onClose(closeHandler);
    }

    @Override
    public void close() {
        s.close();
    }
}