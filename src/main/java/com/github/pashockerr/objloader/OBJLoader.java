package com.github.pashockerr.objloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class OBJLoader {
    /**
     * Loads OBJ model from file stream to OBJModel object. Model SHOULD contain vertices coordinates, normals and texture coordinates.
     * Model should consist ONLY of triangles or quads.
     * @param mS OBJ model file input stream.
     * @return Loaded model.
     * @throws IOException When can't read file.
     */
    public static OBJModel loadModel(InputStream mS) throws IOException {
        var bR = new BufferedReader(new InputStreamReader(mS));
        String line;

        ArrayList<Float[]> vertices = new ArrayList<>();
        ArrayList<Float[]> textureCoordinates = new ArrayList<>();
        ArrayList<Float[]> normals = new ArrayList<>();
        ArrayList<Integer[][]> faces = new ArrayList<>();

        while((line = bR.readLine()) != null){
            String[] components = line.split(" ");
            String identifier = components[0];
            switch (identifier) {
                case "v" -> {
                    Float[] data = Arrays.stream(components, 1, 4).map(Float::parseFloat).toArray(Float[]::new);
                    vertices.add(data);
                }
                case "vt" -> {
                    Float[] data = Arrays.stream(components, 1, 3).map(Float::parseFloat).toArray(Float[]::new);
                    textureCoordinates.add(data);
                }
                case "vn" -> {
                    Float[] data = Arrays.stream(components, 1, 4).map(Float::parseFloat).toArray(Float[]::new);
                    normals.add(data);
                }
                case "f" -> {
                    String[] fV = Arrays.stream(components, 1, components.length).toArray(String[]::new);
                    ArrayList<Integer[]> vS = new ArrayList<>();
                    for (String v : fV) {
                        Integer[] indices = Arrays.stream(v.split("/")).map((String n) ->{
                            var nn = Integer.parseInt(n);
                            return nn - 1;
                        }).toArray(Integer[]::new);
                        vS.add(indices);
                    }
                    faces.add(vS.toArray(Integer[][]::new));
                }
            }
        }

        return new OBJModel(
                vertices.toArray(Float[][]::new),
                textureCoordinates.toArray(Float[][]::new),
                normals.toArray(Float[][]::new),
                faces.toArray(Integer[][][]::new));
    }
}
