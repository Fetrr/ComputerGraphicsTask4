package com.cgvsu.math;

public class Vector2f {
    private double x;
    private double y;

    public Vector2f() {}

    public Vector2f(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f(Vector2f v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Vector2f(double[] doubles) {
        if (doubles.length != 2) {
            throw new IllegalArgumentException("doubles должен содержать 2 элемента");
        } else {
            this.x = doubles[0];
            this.y = doubles[1];
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    public void add(Vector2f other) {
        x += other.getX();
        y += other.getY();
    }

    public void subtract(Vector2f other) {
        x -= other.getX();
        y -= other.getY();
    }

    public void multiplyScalar(double scalar) {
        x *= scalar;
        y *= scalar;
    }

    public void divideScalar(double scalar) {
        if (scalar == 0) {
            throw new IllegalArgumentException("scalar не должен быть = 0");
        }
        x /= scalar;
        y /= scalar;
    }

    public double scalarProduct(Vector2f other) {
        return x * other.getX() + y * other.getY();
    }

    public void normalize() {
        double length = getLength();
        if (length == 0) {
            throw new IllegalArgumentException("length не должен быть = 0");
        }
        x /= length;
        y /= length;
    }
}

