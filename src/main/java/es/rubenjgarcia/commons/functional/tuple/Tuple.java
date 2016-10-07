package es.rubenjgarcia.commons.functional.tuple;

public interface Tuple {

    static <T1> Tuple1<T1> tuple(T1 _1) {
        return new Tuple1<>(_1);
    }

    static <T1, T2> Tuple2<T1, T2> tuple(T1 _1, T2 _2) {
        return new Tuple2<>(_1, _2);
    }

    static <T1, T2, T3> Tuple3<T1, T2, T3> tuple(T1 _1, T2 _2, T3 _3) {
        return new Tuple3<>(_1, _2, _3);
    }

    static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> tuple(T1 _1, T2 _2, T3 _3, T4 _4) {
        return new Tuple4<>(_1, _2, _3, _4);
    }

    static <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> tuple(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5) {
        return new Tuple5<>(_1, _2, _3, _4, _5);
    }

    static <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> tuple(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6) {
        return new Tuple6<>(_1, _2, _3, _4, _5, _6);
    }

    static <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7> tuple(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7) {
        return new Tuple7<>(_1, _2, _3, _4, _5, _6, _7);
    }

    static <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> tuple(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8) {
        return new Tuple8<>(_1, _2, _3, _4, _5, _6, _7, _8);
    }

    static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> tuple(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9) {
        return new Tuple9<>(_1, _2, _3, _4, _5, _6, _7, _8, _9);
    }

    static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> tuple(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10) {
        return new Tuple10<>(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10);
    }
}
