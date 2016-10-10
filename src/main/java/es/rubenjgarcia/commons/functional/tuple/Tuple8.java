package es.rubenjgarcia.commons.functional.tuple;

import java.util.Objects;

public class Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> {
    public final T1 _1;
    public final T2 _2;
    public final T3 _3;
    public final T4 _4;
    public final T5 _5;
    public final T6 _6;
    public final T7 _7;
    public final T8 _8;

    Tuple8(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8) {
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
        this._4 = _4;
        this._5 = _5;
        this._6 = _6;
        this._7 = _7;
        this._8 = _8;
    }

    @Override
    public String toString() {
        return "(" + _1 + ", " + _2 + ", " + _3 + ", " + _4 + ", " + _5 + ", " + _6 + ", " + _7 + ", " + _8 + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple8<?, ?, ?, ?, ?, ?, ?, ?> tuple8 = (Tuple8<?, ?, ?, ?, ?, ?, ?, ?>) o;
        return Objects.equals(_1, tuple8._1) &&
                Objects.equals(_2, tuple8._2) &&
                Objects.equals(_3, tuple8._3) &&
                Objects.equals(_4, tuple8._4) &&
                Objects.equals(_5, tuple8._5) &&
                Objects.equals(_6, tuple8._6) &&
                Objects.equals(_7, tuple8._7) &&
                Objects.equals(_8, tuple8._8);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_1, _2, _3, _4, _5, _6, _7, _8);
    }
}
