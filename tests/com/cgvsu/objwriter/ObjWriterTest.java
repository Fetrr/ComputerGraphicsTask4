package com.cgvsu.objwriter;

import com.cgvsu.math.Vector2f;
import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;
import com.cgvsu.objreader.IncorrectFileException;
import com.cgvsu.objreader.ObjReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObjWriterTest {

    @Test
    public void testSaveFile01() throws IOException, IncorrectFileException {
        Path fileName = Path.of("testModel/Skull.obj");
        String fileContent = Files.readString(fileName);
        Model model = ObjReader.read(fileContent);

        File fileStart = new File(String.valueOf(Path.of("savedModel/testSave01.obj")));
        if(fileStart.exists()){
            fileStart.delete();
        }
        ObjWriter.write("savedModel/testSave01.obj", model);
        File file = new File(String.valueOf(Path.of("savedModel/testSave01.obj")));

        assertTrue(file.exists());
    }
    @Test
    public void testWriteToNonWritableDirectory() throws IOException, IncorrectFileException {
        Path fileName = Path.of("testModel/Skull.obj");
        String fileContent = Files.readString(fileName);
        Model model = ObjReader.read(fileContent);
        String nonWritableDirectory = "/non/writable/directory/testFile.obj";
        Assertions.assertThrows(ObjWriterException.class, () -> {
            ObjWriter.write(nonWritableDirectory, model);
        });
    }
    @Test
    public void testWriteModelWithoutNAndVT() throws IOException, IncorrectFileException {
        Model model = new Model();
        model.vertices.add(new Vector3f(1.0f, 2.0f, 3.0f));
        model.vertices.add(new Vector3f(4.0f, 5.0f, 6.0f));
        model.vertices.add(new Vector3f(7.0f, 8.0f, 9.0f));
        Polygon polygon = new Polygon();
        polygon.setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));

        model.polygons.add(polygon);

        String fileName = "savedModel/testSave02.obj";
        ObjWriter.write(fileName, model);
        Path path = Path.of(fileName);
        String fileContent = Files.readString(path);

        Model testModel = ObjReader.read(fileContent);

        int vertexCount = testModel.vertices.size();
        int textureVertexCount = testModel.textureVertices.size();
        int normalCount = testModel.normals.size();
        int polygonCount = testModel.polygons.size();

        assertTrue(new File(fileName).exists());
        Assertions.assertTrue(fileContent.contains("v 1.0 2.0 3.0"));
        Assertions.assertTrue(fileContent.contains("v 4.0 5.0 6.0"));
        Assertions.assertTrue(fileContent.contains("v 7.0 8.0 9.0"));
        Assertions.assertTrue(fileContent.contains("f 1 2 3"));
        assertEquals(3, vertexCount);
        assertEquals(0, textureVertexCount);
        assertEquals(0, normalCount);
        assertEquals(1, polygonCount);
    }
    @Test
    public void testWriteModelWithNAndVT() throws IOException, IncorrectFileException {
        Model model = new Model();

        model.vertices.add(new Vector3f(1.0f, 2.0f, 3.0f));
        model.vertices.add(new Vector3f(4.0f, 5.0f, 6.0f));
        model.vertices.add(new Vector3f(7.0f, 8.0f, 9.0f));
        model.textureVertices.add(new Vector2f(0.0f, 0.0f));
        model.textureVertices.add(new Vector2f(1.0f, 1.0f));
        model.textureVertices.add(new Vector2f(2.0f, 2.0f));
        model.normals.add(new Vector3f(0.7f, 0.8f, 0.9f));
        model.normals.add(new Vector3f(1.0f, 1.1f, 1.2f));
        model.normals.add(new Vector3f(1.3f, 1.4f, 1.5f));
        Polygon polygon = new Polygon();
        polygon.setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));

        model.polygons.add(polygon);

        String fileName = "savedModel/testSave05.obj";
        ObjWriter.write(fileName, model);
        Path path = Path.of(fileName);
        String fileContent = Files.readString(path);

        Model testModel = ObjReader.read(fileContent);

        int vertexCount = testModel.vertices.size();
        int textureVertexCount = testModel.textureVertices.size();
        int normalCount = testModel.normals.size();
        int polygonCount = testModel.polygons.size();

        assertTrue(new File(fileName).exists());
        Assertions.assertTrue(fileContent.contains("v 1.0 2.0 3.0"));
        Assertions.assertTrue(fileContent.contains("v 4.0 5.0 6.0"));
        Assertions.assertTrue(fileContent.contains("v 7.0 8.0 9.0"));
        Assertions.assertTrue(fileContent.contains("vt 0.0 0.0"));
        Assertions.assertTrue(fileContent.contains("vt 1.0 1.0"));
        Assertions.assertTrue(fileContent.contains("vt 2.0 2.0"));
        Assertions.assertTrue(fileContent.contains("vn 0.7 0.8 0.9"));
        Assertions.assertTrue(fileContent.contains("vn 1.0 1.1 1.2"));
        Assertions.assertTrue(fileContent.contains("vn 1.3 1.4 1.5"));
        Assertions.assertTrue(fileContent.contains("f 1 2 3"));
        assertEquals(3, vertexCount);
        assertEquals(3, textureVertexCount);
        assertEquals(3, normalCount);
        assertEquals(1, polygonCount);
    }
    @Test
    public void testWriteModelWithVTWithoutN() throws IOException, IncorrectFileException {
        Model model = new Model();

        model.vertices.add(new Vector3f(1.0f, 2.0f, 3.0f));
        model.vertices.add(new Vector3f(4.0f, 5.0f, 6.0f));
        model.vertices.add(new Vector3f(7.0f, 8.0f, 9.0f));
        model.textureVertices.add(new Vector2f(0.0f, 0.0f));
        model.textureVertices.add(new Vector2f(1.0f, 1.0f));
        model.textureVertices.add(new Vector2f(2.0f, 2.0f));


        Polygon polygon = new Polygon();
        polygon.setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));
        polygon.setTextureVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));

        model.polygons.add(polygon);

        String fileName = "savedModel/testSave03.obj";
        ObjWriter.write(fileName, model);
        Path path = Path.of(fileName);
        String fileContent = Files.readString(path);

        Model testModel = ObjReader.read(fileContent);

        int vertexCount = testModel.vertices.size();
        int textureVertexCount = testModel.textureVertices.size();
        int normalCount = testModel.normals.size();
        int polygonCount = testModel.polygons.size();

        assertTrue(new File(fileName).exists());

        Assertions.assertTrue(fileContent.contains("v 1.0 2.0 3.0"));
        Assertions.assertTrue(fileContent.contains("v 4.0 5.0 6.0"));
        Assertions.assertTrue(fileContent.contains("v 7.0 8.0 9.0"));
        Assertions.assertTrue(fileContent.contains("vt 0.0 0.0"));
        Assertions.assertTrue(fileContent.contains("vt 1.0 1.0"));
        Assertions.assertTrue(fileContent.contains("vt 2.0 2.0"));
        Assertions.assertTrue(fileContent.contains("f 1/1 2/2 3/3"));

        assertEquals(3, vertexCount);
        assertEquals(3, textureVertexCount);
        assertEquals(0, normalCount);
        assertEquals(1, polygonCount);
    }
    @Test
    public void testWriteModelWithoutVTAndWithN() throws IOException, IncorrectFileException {
        Model model = new Model();

        model.vertices.add(new Vector3f(1.0f, 2.0f, 3.0f));
        model.vertices.add(new Vector3f(4.0f, 5.0f, 6.0f));
        model.vertices.add(new Vector3f(7.0f, 8.0f, 9.0f));
        Vector3f normal1 = new Vector3f(0.7f, 0.8f, 0.9f);
        Vector3f normal2 = new Vector3f(1.0f, 1.1f, 1.2f);
        Vector3f normal3 = new Vector3f(1.3f, 1.4f, 1.5f);
        model.normals.add(normal1);
        model.normals.add(normal2);
        model.normals.add(normal3);

        Polygon polygon = new Polygon();
        polygon.setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));
        polygon.setNormalIndices(new ArrayList<>(Arrays.asList(0, 1, 2)));

        model.polygons.add(polygon);

        String fileName = "savedModel/testSave04.obj";
        ObjWriter.write(fileName, model);
        Path path = Path.of(fileName);
        String fileContent = Files.readString(path);
        Model testModel = ObjReader.read(fileContent);
        int vertexCount = testModel.vertices.size();
        int textureVertexCount = testModel.textureVertices.size();
        int normalCount = testModel.normals.size();
        int polygonCount = testModel.polygons.size();

        assertTrue(new File(fileName).exists());
        Assertions.assertTrue(fileContent.contains("v 1.0 2.0 3.0"));
        Assertions.assertTrue(fileContent.contains("v 4.0 5.0 6.0"));
        Assertions.assertTrue(fileContent.contains("v 7.0 8.0 9.0"));
        Assertions.assertTrue(fileContent.contains("vn 0.7 0.8 0.9"));
        Assertions.assertTrue(fileContent.contains("vn 1.0 1.1 1.2"));
        Assertions.assertTrue(fileContent.contains("vn 1.3 1.4 1.5"));
        Assertions.assertTrue(fileContent.contains("f 1//1 2//2 3//3"));
        assertEquals(3, vertexCount);
        assertEquals(0, textureVertexCount);
        assertEquals(3, normalCount);
        assertEquals(1, polygonCount);
    }
    @Test
    public void testWriteEmptyModel() throws IOException {
        Model model = new Model();
        ObjWriter.write("savedModel/emptyModel.obj", model);
        String fileContent = Files.readString(Path.of("savedModel/emptyModel.obj"));
        Assertions.assertTrue(fileContent.contains(""));
    }



}