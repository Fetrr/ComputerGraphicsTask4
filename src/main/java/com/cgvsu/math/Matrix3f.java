package com.cgvsu.math;

public class Matrix3f {
    private double[][] matrix;

    public Matrix3f() {
        matrix = new double[3][3];
    }

    public Matrix3f(double[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("array must not be null!");
        }
        if (matrix.length != 3 ||
                matrix[0].length != 3 || matrix[1].length != 3 || matrix[2].length != 3) {
            throw new IllegalArgumentException("array must be 3x3!");
        }
        this.matrix = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    public Matrix3f(double[] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("array must not be null!");
        }
        if (matrix.length!= 9) {
            throw new IllegalArgumentException("array must be 3x3!");
        }
        this.matrix = new double[4][4];
        int i = 0, j = 0, iter = 1;
        while (iter <= 9) {
            this.matrix[i][j] = matrix[iter - 1];
            iter += 1;
            j += 1;
            if (iter % 3 == 0) {
                i += 1;
                j = 0;
            }
        }
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public Matrix3f add(Matrix3f other) {
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = this.matrix[i][j] + other.getMatrix()[i][j];
            }
        }
        return new Matrix3f(result);
    }

    public Matrix3f subtract(Matrix3f other) {
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = this.matrix[i][j] - other.getMatrix()[i][j];
            }
        }
        return new Matrix3f(result);
    }

    public Vector3f multiplyVector(Vector3f v) {
        double[] result = new double[3];
        for (int i = 0; i < 3; i++) {
            result[i] = matrix[i][0] * v.getX() + matrix[i][1] * v.getY() + matrix[i][2] * v.getZ();
        }
        return new Vector3f(result);
    }

    public Matrix3f multiplyMatrix(Matrix3f m) {
        double[][] other = m.getMatrix();
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    result[i][j] += this.matrix[i][k] * other[k][j];
                }
            }
        }
        return new Matrix3f(result);
    }

    public Matrix3f transpose() {
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = this.matrix[j][i];
            }
        }
        return new Matrix3f(result);
    }

    public static Matrix3f setIdentityMatrix() {
        return new Matrix3f(new double[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        });
    }

    public static Matrix3f setNullMatrix() {
        return new Matrix3f(new double[][]{
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

