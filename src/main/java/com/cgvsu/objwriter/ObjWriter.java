package com.cgvsu.objwriter;

import com.cgvsu.math.Vector2f;
import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ObjWriter {
    private static final String OBJ_VERTEX_TOKEN = "v";
    private static final String OBJ_TEXTURE_TOKEN = "vt";
    private static final String OBJ_NORMAL_TOKEN = "vn";
    private static final String OBJ_FACE_TOKEN = "f";
    public static void write(String fileName, Model model){
        if (model == null){
            throw new IllegalArgumentException("Неверная модель для записи.");
        }
        File file = new File(fileName);
        try {
            if (file.createNewFile()){
                System.out.println("Файл создан!");
            }
        }catch (IOException e){
            throw new ObjWriterException("Ошибка создания файла:" + fileName + e.getMessage());
        }

        try (PrintWriter pw = new PrintWriter(file)){
            writeV(pw, model.vertices);
            writeTV(pw, model.textureVertices);
            writeN(pw, model.normals);
            writeP(pw, model.polygons);
        }catch (IOException e){
            throw new ObjWriterException(e.getMessage());
        }
    }
    protected static void writeV(PrintWriter pw, List<Vector3f> vertices) throws IOException{
        for (Vector3f v: vertices) {
            pw.println(OBJ_VERTEX_TOKEN + " " + v.getX() + " " + v.getY() + " " + v.getZ());
        }
        pw.println();
    }

    protected static void writeTV(PrintWriter pw, List<Vector2f> textureVertices) throws IOException{
        for (Vector2f v: textureVertices) {
            pw.println(OBJ_TEXTURE_TOKEN + " " + v.getX() + " " + v.getY());
        }
        pw.println();
    }

    protected static void writeN(PrintWriter pw, List<Vector3f> normals) throws IOException{
        for (Vector3f n: normals) {
            pw.println(OBJ_NORMAL_TOKEN + " " + n.getX() + " " + n.getY() + " " + n.getZ());
        }
        pw.println();
    }

    protected static void writeP(PrintWriter pw, List<Polygon> polygons) throws IOException{
        for (Polygon polygon : polygons) {
            StringBuilder objStr = new StringBuilder(OBJ_FACE_TOKEN + " ");
            for (int i = 0; i < polygon.getVertexIndices().size(); i++) {
                if (!polygon.getTextureVertexIndices().isEmpty()) {
                    objStr.append(polygon.getVertexIndices().get(i) + 1).append("/").append(polygon.getTextureVertexIndices().get(i) + 1);
                } else {
                    objStr.append(polygon.getVertexIndices().get(i) + 1);
                }
                if (!polygon.getNormalIndices().isEmpty()) {
                    if (polygon.getTextureVertexIndices().isEmpty()) {
                        objStr.append("/");
                    }
                    objStr.append("/").append(polygon.getNormalIndices().get(i) + 1);
                }
                objStr.append(" ");
            }
            pw.println(objStr);
        }
            pw.println();
        }
    }
