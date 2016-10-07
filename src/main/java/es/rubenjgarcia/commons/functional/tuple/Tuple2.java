package es.rubenjgarcia.commons.functional.tuple;

public class Tuple2<T1, T2> {
    public final T1 _1;
    public final T2 _2;

    Tuple2(T1 _1, T2 _2) {
        this._1 = _1;
        this._2 = _2;
    }

    @Override
    public String toString() {
        return "(" + _1 + ", " + _2 + ")";
    }
}
