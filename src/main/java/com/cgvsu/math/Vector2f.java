package com.cgvsu.math;

public class Vector2f {
    private double x;
    private double y;
    private double length = 0;

    public Vector2f() {}

    public Vector2f(double x, double y) {
        this.x = x;
        this.y = y;
        length = Math.sqrt(x * x + y * y);
    }

    public Vector2f(Vector2f v) {
        this.x = v.x;
        this.y = v.y;
        this.length = v.getLength();
    }

    public Vector2f(double[] doubles) {
        if (doubles.length != 2) {
            throw new IllegalArgumentException("doubles должен содержать 2 элемента");
        } else {
            this.x = doubles[0];
            this.y = doubles[1];
            this.length = Math.sqrt((doubles[0] * doubles[0]) + (doubles[1] * doubles[1]));
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getLength() {
        return length;
    }

    public Vector2f add(Vector2f other) {
        return new Vector2f(x + other.getX(), y + other.getY());
    }

    public Vector2f subtract(Vector2f other) {
        return new Vector2f(x - other.getX(), y - other.getY());
    }

    public Vector2f multiplyScalar(double scalar) {
        return new Vector2f(x * scalar, y * scalar);
    }

    public Vector2f divideScalar(double scalar) {
        if (scalar != 0) {
            return new Vector2f(x / scalar, y / scalar);
        }
        throw new IllegalArgumentException("scalar не должен быть = 0");
    }

    public double scalarProduct(Vector2f other) {
        return x * other.getX() + y * other.getY();
    }

    public Vector2f normalize() {
        if (length != 0) {
            return new Vector2f(x / length, y / length);
        }
        return new Vector2f(0, 0);
    }
}

