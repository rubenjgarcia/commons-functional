package es.rubenjgarcia.commons.functional.tests;

import es.rubenjgarcia.commons.functional.tuple.*;
import org.junit.Test;

import static es.rubenjgarcia.commons.functional.tuple.Tuple.tuple;
import static org.junit.Assert.assertEquals;

public class TupleTestCase {

    @Test
    public void testTuple1() {
        Tuple1 t1 = tuple("1");
        assertEquals("1", t1._1);
        assertEquals("(1)", t1.toString());
        assertEquals(tuple("1"), t1);
    }

    @Test
    public void testTuple2() {
        Tuple2 t2 = tuple("1", "2");
        assertEquals("1", t2._1);
        assertEquals("2", t2._2);
        assertEquals("(1, 2)", t2.toString());
        assertEquals(tuple("1", "2"), t2);
    }

    @Test
    public void testTuple3() {
        Tuple3 t3 = tuple("1", "2", "3");
        assertEquals("1", t3._1);
        assertEquals("2", t3._2);
        assertEquals("3", t3._3);
        assertEquals("(1, 2, 3)", t3.toString());
        assertEquals(tuple("1", "2", "3"), t3);
    }

    @Test
    public void testTuple4() {
        Tuple4 t4 = tuple("1", "2", "3", "4");
        assertEquals("1", t4._1);
        assertEquals("2", t4._2);
        assertEquals("3", t4._3);
        assertEquals("4", t4._4);
        assertEquals("(1, 2, 3, 4)", t4.toString());
        assertEquals(tuple("1", "2", "3", "4"), t4);
    }

    @Test
    public void testTuple5() {
        Tuple5 t5 = tuple("1", "2", "3", "4", "5");
        assertEquals("1", t5._1);
        assertEquals("2", t5._2);
        assertEquals("3", t5._3);
        assertEquals("4", t5._4);
        assertEquals("5", t5._5);
        assertEquals("(1, 2, 3, 4, 5)", t5.toString());
        assertEquals(tuple("1", "2", "3", "4", "5"), t5);
    }

    @Test
    public void testTuple6() {
        Tuple6 t6 = tuple("1", "2", "3", "4", "5", "6");
        assertEquals("1", t6._1);
        assertEquals("2", t6._2);
        assertEquals("3", t6._3);
        assertEquals("4", t6._4);
        assertEquals("5", t6._5);
        assertEquals("6", t6._6);
        assertEquals("(1, 2, 3, 4, 5, 6)", t6.toString());
        assertEquals(tuple("1", "2", "3", "4", "5", "6"), t6);
    }

    @Test
    public void testTuple7() {
        Tuple7 t7 = tuple("1", "2", "3", "4", "5", "6", "7");
        assertEquals("1", t7._1);
        assertEquals("2", t7._2);
        assertEquals("3", t7._3);
        assertEquals("4", t7._4);
        assertEquals("5", t7._5);
        assertEquals("6", t7._6);
        assertEquals("7", t7._7);
        assertEquals("(1, 2, 3, 4, 5, 6, 7)", t7.toString());
        assertEquals(tuple("1", "2", "3", "4", "5", "6", "7"), t7);
    }

    @Test
    public void testTuple8() {
        Tuple8 t8 = tuple("1", "2", "3", "4", "5", "6", "7", "8");
        assertEquals("1", t8._1);
        assertEquals("2", t8._2);
        assertEquals("3", t8._3);
        assertEquals("4", t8._4);
        assertEquals("5", t8._5);
        assertEquals("6", t8._6);
        assertEquals("7", t8._7);
        assertEquals("8", t8._8);
        assertEquals("(1, 2, 3, 4, 5, 6, 7, 8)", t8.toString());
        assertEquals(tuple("1", "2", "3", "4", "5", "6", "7", "8"), t8);
    }

    @Test
    public void testTuple9() {
        Tuple9 t9 = tuple("1", "2", "3", "4", "5", "6", "7", "8", "9");
        assertEquals("1", t9._1);
        assertEquals("2", t9._2);
        assertEquals("3", t9._3);
        assertEquals("4", t9._4);
        assertEquals("5", t9._5);
        assertEquals("6", t9._6);
        assertEquals("7", t9._7);
        assertEquals("8", t9._8);
        assertEquals("9", t9._9);
        assertEquals("(1, 2, 3, 4, 5, 6, 7, 8, 9)", t9.toString());
        assertEquals(tuple("1", "2", "3", "4", "5", "6", "7", "8", "9"), t9);
    }

    @Test
    public void testTuple10() {
        Tuple10 t10 = tuple("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        assertEquals("1", t10._1);
        assertEquals("2", t10._2);
        assertEquals("3", t10._3);
        assertEquals("4", t10._4);
        assertEquals("5", t10._5);
        assertEquals("6", t10._6);
        assertEquals("7", t10._7);
        assertEquals("8", t10._8);
        assertEquals("9", t10._9);
        assertEquals("10", t10._10);
        assertEquals("(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)", t10.toString());
        assertEquals(tuple("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"), t10);
    }
}
