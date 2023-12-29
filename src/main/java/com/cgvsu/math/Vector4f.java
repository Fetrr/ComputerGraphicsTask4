package com.cgvsu.math;

public class Vector4f {
    private double x;
    private double y;
    private double z;
    private double w;

    public Vector4f() {}

    public Vector4f(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;

    }

    public Vector4f(Vector4f v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.w = v.w;
    }

    public Vector4f(double[] doubles) {
        if (doubles.length != 4) {
            throw new IllegalArgumentException("doubles должен содержать 4 элемента");
        } else {
            this.x = doubles[0];
            this.y = doubles[1];
            this.z = doubles[2];
            this.w = doubles[3];
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
        return Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public double[] getVectorInArray() {
        return new double[]{x, y, z, w};
    }

    public void add(Vector4f other) {
        x += other.getX();
        y += other.getY();
        z += other.getZ();
        w += other.getW();
    }

    public void subtract(Vector4f other) {
        x -= other.getX();
        y -= other.getY();
        z -= other.getZ();
        w -= other.getW();
    }

    public void multiplyScalar(double scalar) {
        x *= scalar;
        y *= scalar;
        z *= scalar;
        w *= scalar;
    }

    public void divideScalar(double scalar) {
        if (scalar == 0) {
            throw new IllegalArgumentException("scalar не должен быть = 0");
        }
        x /= scalar;
        y /= scalar;
        z /= scalar;
        w /= scalar;
    }

    public double scalarProduct(Vector4f other) {
        return x * other.getX() + y * other.getY() + z * other.getZ() + w * other.getW();
    }

    public void vectorProduct(Vector4f other) {
        x = y * other.getZ() - z * other.getY();
        y = z * other.getX() - x * other.getZ();
        z = x * other.getY() - y * other.getX();
        w = other.getW();
    }

    public void normalize() {
        double length = getLength();
        if (length == 0) {
            throw new IllegalArgumentException("len не должен быть = 0");
        }
        x /= length;
        y /= length;
        z /= length;
        w /= length;
    }

    // Приведение 4-х мерного вектора к 3-х мерным однородным координатам
    public Vector3f toHomogeneousCoordinates() {
        if (w != 0) {
            return new Vector3f(x / w, y / w, z / w);
        }
        throw new IllegalArgumentException("w не должно быть = 0");
    }
}


