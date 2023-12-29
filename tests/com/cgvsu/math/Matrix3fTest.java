package com.cgvsu.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Matrix3fTest {

    @Test
    public void testMatrixAddition() {
        Matrix3f matrix1 = new Matrix3f(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Matrix3f matrix2 = new Matrix3f(new double[][]{{9, 8, 7}, {6, 5, 4}, {3, 2, 1}});
        Matrix3f result = matrix1.add(matrix2);

        double[][] expected = {{10, 10, 10}, {10, 10, 10}, {10, 10, 10}};
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    public void testMatrixSubtraction() {
        Matrix3f matrix1 = new Matrix3f(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Matrix3f matrix2 = new Matrix3f(new double[][]{{9, 8, 7}, {6, 5, 4}, {3, 2, 1}});
        Matrix3f result = matrix1.subtract(matrix2);

        double[][] expected = {{-8, -6, -4}, {-2, 0, 2}, {4, 6, 8}};
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    public void testMatrixMultiplication() {
        Matrix3f matrix1 = new Matrix3f(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});
        Matrix3f matrix2 = new Matrix3f(new double[][]{
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}});
        Matrix3f result = matrix1.multiplyMatrix(matrix2);

        double[][] expected = {
                {30, 24, 18},
                {84, 69, 54},
                {138, 114, 90}};
        assertArrayEquals(expected, result.getMatrix());
    }

    @Test
    public void testVectorMultiplication() {
        Matrix3f matrix = new Matrix3f(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});
        Vector3f v = new Vector3f(1, 2, 3);
        Vector3f result = matrix.multiplyVector(v);

        double[] expected = new double[]{14, 32, 50};
        assertArrayEquals(expected, new double[]{result.getX(), result.getY(), result.getZ()});
    }

    @Test
    public void testMatrixTranspose() {
        Matrix3f matrix = new Matrix3f(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});
        Matrix3f result = matrix.transpose();
        assertArrayEquals(new double[][] {
                {1, 4, 7},
                {2, 5, 8},
                {3, 6, 9}}, result.getMatrix());
    }

    @Test
    public void testIdentityMatrix() {
        Matrix3f identity = Matrix3f.setIdentityMatrix();
        assertArrayEquals(new double[][] {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}}, identity.getMatrix());
    }

    @Test
    public void testInvalidMatrixInitialization() {
        assertThrows(IllegalArgumentException.class, () -> {
            double[][] invalidMatrixData = {{1, 2, 3}, {4, 5, 6}};
            new Matrix3f(invalidMatrixData);
        });
    }

    @Test
    public void testNullMatrix() {
        Matrix3f nullMatrix = Matrix3f.setNullMatrix();
        assertArrayEquals(new double[][] {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}}, nullMatrix.getMatrix());
    }

    @Test
    public void testMatrixDeterminant() {
        Matrix3f matrix = new Matrix3f(new double[][]{
                {1, 2, 3},
                {4, 3, 6},
                {7, 8, 9}});
        assertEquals(24, matrix.getDet());

        matrix = new Matrix3f(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 90}});
        assertEquals(-243, matrix.getDet());

        matrix = new Matrix3f(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});
        assertEquals(0, matrix.getDet());
    }
}

