package com.cgvsu.math;

public class Vector4f {
    private double x;
    private double y;
    private double z;
    private double w;
    private double length = 0;

    public Vector4f() {}

    public Vector4f(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        length = Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public Vector4f(Vector4f v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.w = v.w;
        this.length = v.getLength();
    }

    public Vector4f(double[] doubles) {
        if (doubles.length != 4) {
            throw new IllegalArgumentException("doubles должен содержать 4 элемента");
        } else {
            this.x = doubles[0];
            this.y = doubles[1];
            this.z = doubles[2];
            this.w = doubles[3];
            this.length = Math.sqrt((doubles[0] * doubles[0]) + (doubles[1] * doubles[1]) +
                    (doubles[2] * doubles[2]) + (doubles[3] * doubles[3]));
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getW() {
        return w;
    }

    public double getLength() {
        return length;
    }

    public double[] getVectorInArray() {
        return new double[]{x, y, z, w};
    }

    public Vector4f add(Vector4f other) {
        return new Vector4f(x + other.getX(), y + other.getY(), z + other.getZ(), w + other.getW());
    }

    public Vector4f subtract(Vector4f other) {
        return new Vector4f(x - other.getX(), y - other.getY(), z - other.getZ(), w - other.getW());
    }

    public Vector4f multiplyScalar(double scalar) {
        return new Vector4f(x * scalar, y * scalar, z * scalar, w * scalar);
    }

    public Vector4f divideScalar(double scalar) {
        if (scalar != 0) {
            return new Vector4f(x / scalar, y / scalar, z / scalar, w / scalar);
        }
        throw new IllegalArgumentException("scalar не должен быть = 0");
    }

    public double scalarProduct(Vector4f other) {
        return x * other.getX() + y * other.getY() + z * other.getZ() + w * other.getW();
    }

    public Vector4f vectorProduct(Vector4f other) {
        double newX = y * other.getZ() - z * other.getY();
        double newY = z * other.getX() - x * other.getZ();
        double newZ = x * other.getY() - y * other.getX();
        return new Vector4f(newX, newY, newZ, other.getW());
    }

    public Vector4f normalize() {
        if (length != 0) {
            return new Vector4f(x / length, y / length, z / length, w / length);
        }
        return new Vector4f(0, 0, 0, 0);
    }

    // Приведение 4-х мерного вектора к 3-х мерным однородным координатам
    public Vector3f toHomogeneousCoordinates() {
        if (w != 0) {
            return new Vector3f(x / w, y / w, z / w);
        }
        throw new IllegalArgumentException("w не должно быть = 0");
    }
}


