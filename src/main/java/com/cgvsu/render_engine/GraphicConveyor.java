package com.cgvsu.render_engine;
import com.cgvsu.math.*;
//import javax.vecmath.*;

public class GraphicConveyor {

    public static Matrix4f rotateScaleTranslate() {
        double[] matrix = new double[]{
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1};
        return new Matrix4f(matrix);
    }

    public static Matrix4f lookAt(Vector3f eye, Vector3f target) {
        return lookAt(eye, target, new Vector3f(0, 1.0, 0));
    }

    public static Matrix4f lookAt(Vector3f eye, Vector3f target, Vector3f up) {
        Vector3f resultX = new Vector3f();
        Vector3f resultY = new Vector3f();
        Vector3f resultZ = new Vector3f();

        resultZ.subtract(target, eye);
        resultX.vectorProduct(up, resultZ);
        resultY.vectorProduct(resultZ, resultX);

        resultX.normalize();
        resultY.normalize();
        resultZ.normalize();

        double[] matrix = new double[]{
                resultX.getX(), resultY.getX(), resultZ.getX(), 0,
                resultX.getY(), resultY.getY(), resultZ.getY(), 0,
                resultX.getZ(), resultY.getZ(), resultZ.getZ(), 0,
                -resultX.scalarProduct(eye), -resultY.scalarProduct(eye), -resultZ.scalarProduct(eye), 1};
        return new Matrix4f(matrix);
    }

    public static Matrix4f perspective(
            final double fov,
            final double aspectRatio,
            final double nearPlane,
            final double farPlane) {
        Matrix4f result = new Matrix4f();
        double tangentMinusOnDegree = (1.0 / (Math.tan(fov * 0.5)));
        result.setCell(0, 0, tangentMinusOnDegree / aspectRatio);
        result.setCell(1, 1, tangentMinusOnDegree);
        result.setCell(2, 2, (farPlane + nearPlane) / (farPlane - nearPlane));
        result.setCell(2, 3, 1.0);
        result.setCell(3, 2, 2 * (nearPlane * farPlane) / (nearPlane - farPlane));
        return result;
    }

    public static Vector3f multiplyMatrix4ByVector3(final Matrix4f matrix4f, final Vector3f vertex) {
        double[][] matrix = matrix4f.getMatrix();
        final double x = (vertex.getX() * matrix[0][0]) + (vertex.getY() * matrix[1][0]) + (vertex.getZ() * matrix[2][0]) + matrix[3][0];
        final double y = (vertex.getX() * matrix[0][1]) + (vertex.getY() * matrix[1][1]) + (vertex.getZ() * matrix[2][1]) + matrix[3][1];
        final double z = (vertex.getX() * matrix[0][2]) + (vertex.getY() * matrix[1][2]) + (vertex.getZ() * matrix[2][2]) + matrix[3][2];
        final double w = (vertex.getX() * matrix[0][3]) + (vertex.getY() * matrix[1][3]) + (vertex.getZ() * matrix[2][3]) + matrix[3][3];
        return new Vector3f(x / w, y / w, z / w);
    }

    public static Point2f vertexToPoint(final Vector3f vertex, final int width, final int height) {
        return new Point2f(vertex.getX() * width + width / 2.0, -vertex.getY() * height + height / 2.0);
    }
}
