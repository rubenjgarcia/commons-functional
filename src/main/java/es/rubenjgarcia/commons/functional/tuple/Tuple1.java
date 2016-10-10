package es.rubenjgarcia.commons.functional.tuple;

import java.util.Objects;

public class Tuple1<T1> implements Tuple {
    public final T1 _1;

    Tuple1(T1 _1) {
        this._1 = _1;
    }

    @Override
    public String toString() {
        return "(" + _1 + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple1<?> tuple1 = (Tuple1<?>) o;
        return Objects.equals(_1, tuple1._1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_1);
    }
}
