package com.game.objects;

import org.joml.Matrix4f;

public class Transform {
    public float x = 0f;
    public float y = 0f;

    public float scale = 1f;
    
    public float rotation = 0f;

    public Matrix4f getMatrix(){

        return new Matrix4f()
        .translate(x, y, 0f)
        .rotateZ(rotation)
        .scale(scale);
    }
}
