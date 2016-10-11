package es.rubenjgarcia.commons.functional;

import es.rubenjgarcia.commons.functional.tuple.Tuple2;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class FlowStream2<F, T> implements Stream<T> {

    private final Supplier<Stream<F>> supplier;
    private final List<Predicate<F>> predicates;
    private final Stream<Tuple2<F, T>> zip;
    private final Supplier<Stream<T>> stream;

    private FlowStream2(Supplier<Stream<F>> supplier, Stream<T> result, Predicate<F> predicate) {
        this(supplier, result, new ArrayList<>());
        this.predicates.add(predicate);
    }

    private FlowStream2(Supplier<Stream<F>> supplier, Stream<T> result, List<Predicate<F>> predicates) {
        this.supplier = supplier;
        this.zip = StreamUtils.zip(supplier.get(), result);
        this.stream = () -> zip.map(t -> t._2);
        this.predicates = predicates;
    }

    private FlowStream2(Supplier<Stream<F>> supplier, Stream<T> result, List<Predicate<F>> predicates, Predicate<F> predicate) {
        this(supplier, result, new ArrayList<>(predicates));
        this.predicates.add(predicate);
    }

    public static <F, T> FlowStream2<F, Object> mapIf(Supplier<Stream<F>> s, Predicate<F> p, Function<F, T> mapper) {
        Stream<Object> result = s.get().map(e -> p.test(e) ? mapper.apply(e) : e);
        return new FlowStream2(s, result, p);
    }

    public FlowStream2<F, Object> elseIfMap(Predicate<F> p, Function<F, T> mapper) {
        Stream<Object> result = this.zip.map(t -> FunctionalFilters.noneOf(this.predicates).test(t._1) ? (p.test(t._1) ? mapper.apply(t._1) : t._2) : t._2);
        return new FlowStream2(this.supplier, result, this.predicates, p);
    }

    public Stream<T> elseMap(Function<F, T> mapper) {
        return this.zip.map(t -> FunctionalFilters.noneOf(this.predicates).test(t._1) ? mapper.apply(t._1) : t._2);
    }

    @Override
    public Stream<T> filter(Predicate<? super T> predicate) {
        return stream.get().filter(predicate);
    }

    @Override
    public <R> Stream<R> map(Function<? super T, ? extends R> mapper) {
        return stream.get().map(mapper);
    }

    @Override
    public IntStream mapToInt(ToIntFunction<? super T> mapper) {
        return stream.get().mapToInt(mapper);
    }

    @Override
    public LongStream mapToLong(ToLongFunction<? super T> mapper) {
        return stream.get().mapToLong(mapper);
    }

    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
        return stream.get().mapToDouble(mapper);
    }

    @Override
    public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
        return stream.get().flatMap(mapper);
    }

    @Override
    public IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
        return stream.get().flatMapToInt(mapper);
    }

    @Override
    public LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
        return stream.get().flatMapToLong(mapper);
    }

    @Override
    public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {
        return stream.get().flatMapToDouble(mapper);
    }

    @Override
    public Stream<T> distinct() {
        return stream.get().distinct();
    }

    @Override
    public Stream<T> sorted() {
        return stream.get().sorted();
    }

    @Override
    public Stream<T> sorted(Comparator<? super T> comparator) {
        return stream.get().sorted(comparator);
    }

    @Override
    public Stream<T> peek(Consumer<? super T> action) {
        return stream.get().peek(action);
    }

    @Override
    public Stream<T> limit(long maxSize) {
        return stream.get().limit(maxSize);
    }

    @Override
    public Stream<T> skip(long n) {
        return stream.get().skip(n);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        stream.get().forEach(action);
    }

    @Override
    public void forEachOrdered(Consumer<? super T> action) {
        stream.get().forEachOrdered(action);
    }

    @Override
    public Object[] toArray() {
        return stream.get().toArray();
    }

    @Override
    public <A> A[] toArray(IntFunction<A[]> generator) {
        return stream.get().toArray(generator);
    }

    @Override
    public T reduce(T identity, BinaryOperator<T> accumulator) {
        return stream.get().reduce(identity, accumulator);
    }

    @Override
    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        return stream.get().reduce(accumulator);
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
        return stream.get().reduce(identity, accumulator, combiner);
    }

    @Override
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
        return stream.get().collect(supplier, accumulator, combiner);
    }

    @Override
    public <R, A> R collect(Collector<? super T, A, R> collector) {
        return stream.get().collect(collector);
    }

    @Override
    public Optional<T> min(Comparator<? super T> comparator) {
        return stream.get().min(comparator);
    }

    @Override
    public Optional<T> max(Comparator<? super T> comparator) {
        return stream.get().max(comparator);
    }

    @Override
    public long count() {
        return stream.get().count();
    }

    @Override
    public boolean anyMatch(Predicate<? super T> predicate) {
        return stream.get().anyMatch(predicate);
    }

    @Override
    public boolean allMatch(Predicate<? super T> predicate) {
        return stream.get().allMatch(predicate);
    }

    @Override
    public boolean noneMatch(Predicate<? super T> predicate) {
        return stream.get().noneMatch(predicate);
    }

    @Override
    public Optional<T> findFirst() {
        return stream.get().findFirst();
    }

    @Override
    public Optional<T> findAny() {
        return stream.get().findAny();
    }

    @Override
    public Iterator<T> iterator() {
        return stream.get().iterator();
    }

    @Override
    public Spliterator<T> spliterator() {
        return stream.get().spliterator();
    }

    @Override
    public boolean isParallel() {
        return stream.get().isParallel();
    }

    @Override
    public Stream<T> sequential() {
        return stream.get().sequential();
    }

    @Override
    public Stream<T> parallel() {
        return stream.get().parallel();
    }

    @Override
    public Stream<T> unordered() {
        return stream.get().unordered();
    }

    @Override
    public Stream<T> onClose(Runnable closeHandler) {
        return stream.get().onClose(closeHandler);
    }

    @Override
    public void close() {
        stream.get().close();
    }
}