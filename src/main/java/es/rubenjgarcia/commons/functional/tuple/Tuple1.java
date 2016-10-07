package es.rubenjgarcia.commons.functional.tuple;

public class Tuple1<T1> implements Tuple {
    public final T1 _1;

    Tuple1(T1 _1) {
        this._1 = _1;
    }

    @Override
    public String toString() {
        return "(" + _1 + ")";
    }
}
