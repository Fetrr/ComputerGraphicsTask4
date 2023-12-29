package com.cgvsu.math;

public class Vector3f {
    private double x;
    private double y;
    private double z;
    private double length = 0;
    
    public Vector3f() {}

    public Vector3f(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        length = Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3f(Vector3f v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.length = v.getLength();
    }

    public Vector3f(double[] doubles) {
        if (doubles.length != 3) {
            throw new IllegalArgumentException("doubles должен содержать 3 элемента");
        } else {
            this.x = doubles[0];
            this.y = doubles[1];
            this.z = doubles[2];
            this.length = Math.sqrt((doubles[0] * doubles[0]) + (doubles[1] * doubles[1]) + (doubles[2] * doubles[2]));
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
        return length;
    }

    public Vector3f add(Vector3f other) {
        return new Vector3f(x + other.getX(), y + other.getY(), z + other.getZ());
    }

    public Vector3f subtract(Vector3f other) {
        return new Vector3f(x - other.getX(), y - other.getY(), z - other.getZ());
    }
    public Vector3f subtract(Vector3f v1, Vector3f v2) {
        return new Vector3f(v1.getX() - v2.getX(), v1.getY() - v2.getY(), v1.getZ() - v2.getZ());
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
    
    public Vector3f vectorProduct(Vector3f v1, Vector3f v2) {
        return new Vector3f(
                v1.getY() * v2.getZ() - v1.getZ() * v2.getY(),
                v1.getZ() * v2.getX() - v1.getX() * v2.getZ(),
                v1.getX() * v2.getY() - v1.getY() * v2.getX()
        );
    }

    public Vector3f normalize() {
        double len = getLength();
        if (len != 0) {
            return new Vector3f(x / len, y / len, z / len);
        }
        return new Vector3f(0, 0, 0);
    }
    
    public boolean equals(Vector3f other) {
        // todo: желательно, чтобы это была глобальная константа
        final float eps = 1e-7f;
        return Math.abs(x - other.x) < eps && Math.abs(y - other.y) < eps && Math.abs(z - other.z) < eps;
    }
}
