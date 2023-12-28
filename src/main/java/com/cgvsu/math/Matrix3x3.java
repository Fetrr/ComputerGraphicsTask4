package com.cgvsu.math;

public class Matrix3x3 {
    private final double[][] matrix;

    public Matrix3x3(double[][] matrix) {
        if (matrix.length != 3 ||
                matrix[0].length != 3 || matrix[1].length != 3 || matrix[2].length != 3) {
            throw new IllegalArgumentException("array must be 3x3!");
        }
        this.matrix = matrix;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public Matrix3x3 add(Matrix3x3 other) {
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = this.matrix[i][j] + other.getMatrix()[i][j];
            }
        }
        return new Matrix3x3(result);
    }

    public Matrix3x3 subtract(Matrix3x3 other) {
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = this.matrix[i][j] - other.getMatrix()[i][j];
            }
        }
        return new Matrix3x3(result);
    }

    public Vector3f multiplyVector(Vector3f v) {
        double[] result = new double[3];
        for (int i = 0; i < 3; i++) {
            result[i] = matrix[i][0] * v.getX() + matrix[i][1] * v.getY() + matrix[i][2] * v.getZ();
        }
        return new Vector3f(result);
    }

    public Matrix3x3 multiplyMatrix(Matrix3x3 m) {
        double[][] other = m.getMatrix();
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    result[i][j] += this.matrix[i][k] * other[k][j];
                }
            }
        }
        return new Matrix3x3(result);
    }

    public Matrix3x3 transpose() {
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = this.matrix[j][i];
            }
        }
        return new Matrix3x3(result);
    }

    public static Matrix3x3 setIdentityMatrix() {
        return new Matrix3x3(new double[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        });
    }

    public static Matrix3x3 setNullMatrix() {
        return new Matrix3x3(new double[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        });
    }
    public double getDet() {
        return matrix[0][0] * matrix[1][1] * matrix[2][2] +
                matrix[0][1] * matrix[1][2] * matrix[2][0] +
                matrix[0][2] * matrix[1][0] * matrix[2][1] -
                (matrix[0][2] * matrix[1][1] * matrix[2][0] +
                        matrix[0][0] * matrix[1][2] * matrix[2][1] +
                        matrix[0][1] * matrix[1][0] * matrix[2][2]);
    }
}

