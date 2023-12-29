package com.cgvsu.math;

public class Matrix4f {
    private double[][] matrix;

    public Matrix4f() {
        matrix = new double[4][4];
    }

    public Matrix4f(double[][] matrix) {
        if (matrix.length != 4 ||
                matrix[0].length != 4 || matrix[1].length != 4 || matrix[2].length != 4 || matrix[3].length != 4) {
            throw new IllegalArgumentException("array must be 4x4!");
        }
        this.matrix = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    public Matrix4f(double[] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("array must not be null!");
        }
        if (matrix.length!= 16) {
            throw new IllegalArgumentException("array must be 4x4!");
        }
        this.matrix = new double[4][4];
        int i = 0, j = 0, iter = 1;
        while (iter <= 16) {
            this.matrix[i][j] = matrix[iter - 1];
            iter += 1;
            j += 1;
            if ((iter - 1) % 4 == 0) {
                i += 1;
                j = 0;
            }
        }
    }

    public void setCell(int x, int y, double value) {
        matrix[y][x] = value;
    }

    public double[][] getMatrix() {
        return matrix;
    }
    public double[] getMatrixInArray() {
        double[] result = new double[4 * 4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i * 4 + j] = matrix[i][j];
            }
        }
        return result;
    }

    public void add(Matrix4f other) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.matrix[i][j] += other.getMatrix()[i][j];
            }
        }
    }

    public void subtract(Matrix4f other) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.matrix[i][j] -= other.getMatrix()[i][j];
            }
        }
    }

    public Vector4f multiplyVector(Vector4f v) {
        double[] result = new double[4];
        for (int i = 0; i < 4; i++) {
            result[i] = matrix[i][0] * v.getX() + matrix[i][1] * v.getY() +
                    matrix[i][2] * v.getZ() + matrix[i][3] * v.getW();
        }
        return new Vector4f(result);
    }

    public void multiplyMatrix(Matrix4f other) {
        double[][] m = other.getMatrix();
        double[][] result = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    result[i][j] += matrix[i][k] * m[k][j];
                }
            }
        }
        this.matrix = result;
    }

    public void transpose() {
        double[][] result = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = this.matrix[j][i];
            }
        }
        this.matrix = result;
    }

    public static Matrix4f setIdentityMatrix() {
        double[][] identity = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}};
        return new Matrix4f(identity);
    }

    public static Matrix4f setNullMatrix() {
        double[][] nullMatrix = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};
        return new Matrix4f(nullMatrix);
    }

//    public double getDet() {
//        return
//    }
}
