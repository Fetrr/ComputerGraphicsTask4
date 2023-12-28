package com.cgvsu.math;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;

public class Vector3fTest {

    @Test
    public void testConstructors() {
        Vector3f v = new Vector3f(3, 4, 0);
        assertEquals(3, v.getX());
        assertEquals(4, v.getY());
        assertEquals(0, v.getZ());
        assertEquals(5, v.getLength());

        Vector3f v1 = new Vector3f(3, 4, 0);
        v = new Vector3f(v1);
        assertEquals(3, v.getX());
        assertEquals(4, v.getY());
        assertEquals(0, v.getZ());
        assertEquals(5, v.getLength());

        double[] vecInDouble = {3, 4, 0};
        v = new Vector3f(vecInDouble);
        assertEquals(3, v.getX());
        assertEquals(4, v.getY());
        assertEquals(0, v.getZ());
        assertEquals(5, v.getLength());

        double[] notVecInDouble = {3};
        assertThrows(IllegalArgumentException.class, () -> new Vector3f(notVecInDouble));
    }

    @Test
    public void testVectorAddition() {
        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = new Vector3f(4, 5, 6);
        Vector3f result = v1.add(v2);
        assertEquals(5, result.getX());
        assertEquals(7, result.getY());
        assertEquals(9, result.getZ());
    }

    @Test
    public void testVectorSubtraction() {
        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = new Vector3f(4, 5, 6);
        Vector3f result = v1.subtract(v2);
        assertEquals(-3, result.getX());
        assertEquals(-3, result.getY());
        assertEquals(-3, result.getZ());
    }

    @Test
    public void testVectorMultiplicationByScalar() {
        Vector3f v = new Vector3f(1, 2, 3);
        Vector3f result = v.multiplyScalar(3);
        assertEquals(3, result.getX());
        assertEquals(6, result.getY());
        assertEquals(9, result.getZ());
    }

    @Test
    public void testVectorDivisionByScalar() {
        Vector3f v = new Vector3f(3, 6, 9);
        Vector3f result = v.divideScalar(3);
        assertEquals(1, result.getX());
        assertEquals(2, result.getY());
        assertEquals(3, result.getZ());

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                v.divideScalar(0);
            }
        });
    }

    @Test
    public void testVectorLength() {
        Vector3f v = new Vector3f(1, 2, 3);
        double length = v.getLength();
        assertEquals(Math.sqrt(14), length);
    }

    @Test
    public void testVectorNormalization() {
        Vector3f v = new Vector3f(1, 2, 3);
        Vector3f normalized = v.normalize();
        double length = normalized.getLength();
        assertEquals(1, length);
    }

    @Test
    public void testVectorScalarProduct() {
        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = new Vector3f(4, 5, 6);
        double dotProduct = v1.scalarProduct(v2);
        assertEquals(32, dotProduct);
    }

    @Test
    public void testVectorVectorProduct() {
        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = new Vector3f(4, 5, 6);
        Vector3f result = v1.vectorProduct(v2);
        assertEquals(-3, result.getX());
        assertEquals(6, result.getY());
        assertEquals(-3, result.getZ());
    }
}

