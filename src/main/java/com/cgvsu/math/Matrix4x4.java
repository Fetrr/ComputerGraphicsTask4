package com.cgvsu.math;

public class Matrix4x4 {
    private double[][] matrix;

    public Matrix4x4(double[][] matrix) {
        if (matrix.length != 4 ||
                matrix[0].length != 4 || matrix[1].length != 4 || matrix[2].length != 4 || matrix[3].length != 4) {
            throw new IllegalArgumentException("array must be 4x4!");
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public Matrix4x4 add(Matrix4x4 other) {
        double[][] result = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = this.matrix[i][j] + other.getMatrix()[i][j];
            }
        }
        return new Matrix4x4(result);
    }

    public Matrix4x4 subtract(Matrix4x4 other) {
        double[][] result = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = this.matrix[i][j] - other.getMatrix()[i][j];
            }
        }
        return new Matrix4x4(result);
    }

    public Vector4f multiplyVector(Vector4f v) {
        double[] result = new double[4];
        for (int i = 0; i < 4; i++) {
            result[i] = matrix[i][0] * v.getX() + matrix[i][1] * v.getY() +
                    matrix[i][2] * v.getZ() + matrix[i][3] * v.getW();
        }
        return new Vector4f(result);
    }

    public Matrix4x4 multiplyMatrix(Matrix4x4 other) {
        double[][] m = other.getMatrix();
        double[][] result = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    result[i][j] += matrix[i][k] * m[k][j];
                }
            }
        }
        return new Matrix4x4(result);
    }

    public Matrix4x4 transpose() {
        double[][] result = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = this.matrix[j][i];
            }
        }
        return new Matrix4x4(result);
    }

    public static Matrix4x4 setIdentityMatrix() {
        double[][] identity = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}};
        return new Matrix4x4(identity);
    }

    public static Matrix4x4 setNullMatrix() {
        double[][] nullMatrix = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};
        return new Matrix4x4(nullMatrix);
    }

//    public double getDet() {
//        return
//    }
}
