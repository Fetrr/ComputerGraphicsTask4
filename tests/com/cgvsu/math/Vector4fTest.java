package com.cgvsu.math;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;

public class Vector4fTest {

    @Test
    public void testConstructors() {
        Vector4f v = new Vector4f(1, 1, 1, 1);
        assertEquals(1, v.getX());
        assertEquals(1, v.getY());
        assertEquals(1, v.getZ());
        assertEquals(1, v.getW());
        assertEquals(2, v.getLength());

        Vector4f v1 = new Vector4f(1, 1, 1, 1);
        v = new Vector4f(v1);
        assertEquals(1, v.getX());
        assertEquals(1, v.getY());
        assertEquals(1, v.getZ());
        assertEquals(1, v.getW());
        assertEquals(2, v.getLength());

        double[] vecInDouble = {1, 1, 1, 1};
        v = new Vector4f(vecInDouble);
        assertEquals(1, v.getX());
        assertEquals(1, v.getY());
        assertEquals(1, v.getZ());
        assertEquals(1, v.getW());
        assertEquals(2, v.getLength());

        double[] notVecInDouble = {3};
        assertThrows(IllegalArgumentException.class, () -> new Vector4f(notVecInDouble));
    }

    @Test
    public void testVectorAddition() {
        Vector4f v1 = new Vector4f(1, 2, 3, 4);
        Vector4f v2 = new Vector4f(4, 5, 6, 7);
        Vector4f result = v1.add(v2);
        assertEquals(5, result.getX());
        assertEquals(7, result.getY());
        assertEquals(9, result.getZ());
        assertEquals(11, result.getW());
    }

    @Test
    public void testVectorSubtraction() {
        Vector4f v1 = new Vector4f(1, 2, 3, 4);
        Vector4f v2 = new Vector4f(4, 5, 6, 7);
        Vector4f result = v1.subtract(v2);
        assertEquals(-3, result.getX());
        assertEquals(-3, result.getY());
        assertEquals(-3, result.getZ());
        assertEquals(-3, result.getW());
    }

    @Test
    public void testVectorMultiplicationByScalar() {
        Vector4f v = new Vector4f(1, 2, 3, 4);
        Vector4f result = v.multiplyScalar(3);
        assertEquals(3, result.getX());
        assertEquals(6, result.getY());
        assertEquals(9, result.getZ());
        assertEquals(12, result.getW());
    }

    @Test
    public void testVectorDivisionByScalar() {
        Vector4f v = new Vector4f(3, 6, 9, 12);
        Vector4f result = v.divideScalar(3);
        assertEquals(1, result.getX());
        assertEquals(2, result.getY());
        assertEquals(3, result.getZ());
        assertEquals(4, result.getW());

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                v.divideScalar(0);
            }
        });
    }

    @Test
    public void testVectorLength() {
        Vector4f v = new Vector4f(1, 2, 3, 4);
        double length = v.getLength();
        assertEquals(Math.sqrt(30), length);
    }

    @Test
    public void testVectorNormalization() {
        Vector4f v = new Vector4f(1, 2, 3, 4);
        Vector4f normalized = v.normalize();
        double length = normalized.getLength();
        assertEquals(1.0, length, 0.0000001);
    }

    @Test
    public void testVectorScalarProduct() {
        Vector4f v1 = new Vector4f(1, 2, 3, 4);
        Vector4f v2 = new Vector4f(5, 6, 7, 8);
        double dotProduct = v1.scalarProduct(v2);
        assertEquals(70, dotProduct);
    }

    @Test
    public void testVectorVectorProduct() {
        Vector4f v1 = new Vector4f(1, 0, 0, 0);
        Vector4f v2 = new Vector4f(0, 1, 0, 0);
        Vector4f result = v1.vectorProduct(v2);
        assertEquals(0, result.getX());
        assertEquals(0, result.getY());
        assertEquals(1, result.getZ());
        assertEquals(0, result.getW());
    }

    @Test
    public void testHomogeneousCoordinates() {
        Vector4f v1 = new Vector4f(1, 2, 3, 1);
        Vector3f result = v1.toHomogeneousCoordinates();
        assertEquals(1, result.getX());
        assertEquals(2, result.getY());
        assertEquals(3, result.getZ());

        Vector4f v2 = new Vector4f(1, 2, 3, 2);
        result = v2.toHomogeneousCoordinates();
        assertEquals(0.5, result.getX());
        assertEquals(1, result.getY());
        assertEquals(1.5, result.getZ());

        v2 = new Vector4f(2, 4, 6, 2);
        result = v2.toHomogeneousCoordinates();
        assertEquals(1, result.getX());
        assertEquals(2, result.getY());
        assertEquals(3, result.getZ());

        v2 = new Vector4f(2, 4, 6, 0);
        assertThrows(IllegalArgumentException.class, v2::toHomogeneousCoordinates);
    }
}

