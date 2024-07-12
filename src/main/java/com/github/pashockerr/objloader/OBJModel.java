package com.github.pashockerr.objloader;

public class OBJModel {
    public Float[][] vertices;
    public Float[][] textureCoordinates;
    public Float[][] normals;
    public Integer[][][] faces;

    public OBJModel(Float[][] vertices, Float[][] textureCoordinates, Float[][] normals, Integer[][][] faces) {
        this.vertices = vertices;
        this.textureCoordinates = textureCoordinates;
        this.normals = normals;
        this.faces = faces;
    }
}
