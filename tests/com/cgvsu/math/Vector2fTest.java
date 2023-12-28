package com.cgvsu.math;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;


public class Vector2fTest {

    @Test
    public void testConstructors() {
        Vector2f v = new Vector2f(3, 4);
        assertEquals(3, v.getX());
        assertEquals(4, v.getY());
        assertEquals(5, v.getLength());

        Vector2f v1 = new Vector2f(3, 4);
        v = new Vector2f(v1);
        assertEquals(3, v.getX());
        assertEquals(4, v.getY());
        assertEquals(5, v.getLength());

        double[] vecInDouble = {3, 4};
        v = new Vector2f(vecInDouble);
        assertEquals(3, v.getX());
        assertEquals(4, v.getY());
        assertEquals(5, v.getLength());

        double[] notVecInDouble = {3};
        assertThrows(IllegalArgumentException.class, () -> new Vector2f(notVecInDouble));
    }

    @Test
    public void testVectorAddition() {
        Vector2f v1 = new Vector2f(1, 2);
        Vector2f v2 = new Vector2f(3, 4);
        Vector2f result = v1.add(v2);
        assertEquals(4, result.getX());
        assertEquals(6, result.getY());
    }

    @Test
    public void testVectorSubtraction() {
        Vector2f v1 = new Vector2f(1, 2);
        Vector2f v2 = new Vector2f(3, 4);
        Vector2f result = v1.subtract(v2);
        assertEquals(-2, result.getX());
        assertEquals(-2, result.getY());
    }

    @Test
    public void testVectorMultiplicationByScalar() {
        Vector2f v1 = new Vector2f(1, 2);
        Vector2f result = v1.multiplyScalar(2);
        assertEquals(2, result.getX());
        assertEquals(4, result.getY());
    }

    @Test
    public void testVectorDivisionByScalar() {
        Vector2f v = new Vector2f(1, 2);
        Vector2f result = v.divideScalar(2);
        assertEquals(0.5, result.getX());
        assertEquals(1, result.getY());

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                v.divideScalar(0);
            }
        });
    }

    @Test
    public void testVectorLength() {
        Vector2f v1 = new Vector2f(3, 4);
        assertEquals(5, v1.getLength());
    }

    @Test
    public void testVectorNormalization() {
        Vector2f v1 = new Vector2f(3, 4);
        Vector2f result = v1.normalize();
        assertEquals(0.6, result.getX(), 0.01);
        assertEquals(0.8, result.getY(), 0.01);
    }

    @Test
    public void testVectorScalarProduct() {
        Vector2f v1 = new Vector2f(1, 2);
        Vector2f v2 = new Vector2f(3, 4);
        double result = v1.scalarProduct(v2);
        assertEquals(11, result);
    }

    @Test
    public void testAdditionInDouble() {
        Vector2f v1 = new Vector2f(0.1, 0);
        Vector2f v2 = new Vector2f(0, 0);
        for(int i = 0; i < 10; i++) {
            v2 = v2.add(v1);
        }
        Vector2f result = v2;
        assertEquals(1, result.getX(), 0.01);
        assertEquals(0, result.getY());
    }
}


