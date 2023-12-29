package com.cgvsu.math;

public class Vector3f {
    public static final double eps = 1e-7f;
    private double x;
    private double y;
    private double z;
    
    public Vector3f() {}

    public Vector3f(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f(Vector3f v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public Vector3f(double[] doubles) {
        if (doubles.length != 3) {
            throw new IllegalArgumentException("doubles должен содержать 3 элемента");
        } else {
            this.x = doubles[0];
            this.y = doubles[1];
            this.z = doubles[2];
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

    public double getLength() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public void add(Vector3f other) {
        x += other.getX();
        y += other.getY();
        z += other.getZ();
    }

    public void subtract(Vector3f other) {
        x -= other.getX();
        y -= other.getY();
        z -= other.getZ();
    }
    public void subtract(Vector3f v1, Vector3f v2) {
        x = v1.getX() - v2.getX();
        y = v1.getY() - v2.getY();
        z = v1.getZ() - v2.getZ();
    }

    public Vector3f multiplyScalar(double scalar) {
        return new Vector3f(x * scalar, y * scalar, z * scalar);
    }

    public Vector3f divideScalar(double scalar) {
        if (scalar != 0) {
            return new Vector3f(x / scalar, y / scalar, z / scalar);
        }
        throw new IllegalArgumentException("scalar не должен быть = 0");
    }

    public double scalarProduct(Vector3f other) {
        return x * other.getX() + y * other.getY() + z * other.getZ();
    }
    
    public Vector3f vectorProduct(Vector3f other) {
        return new Vector3f(
                y * other.getZ() - z * other.getY(),
                z * other.getX() - x * other.getZ(),
                x * other.getY() - y * other.getX()
        );
    }
    
    public void vectorProduct(Vector3f v1, Vector3f v2) {
        x = v1.getY() * v2.getZ() - v1.getZ() * v2.getY();
        y = v1.getZ() * v2.getX() - v1.getX() * v2.getZ();
        z = v1.getX() * v2.getY() - v1.getY() * v2.getX();
    }

    public void normalize() {
        double len = getLength();
        if (len != 0) {
            x /= len;
            y /= len;
            z /= len;
        } else {
            throw new IllegalArgumentException("len не должен быть = 0");
        }
    }
    
    public boolean equals(Vector3f other) {
        return Math.abs(x - other.x) < eps && Math.abs(y - other.y) < eps && Math.abs(z - other.z) < eps;
    }
}
