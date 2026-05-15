package com.game.objects;

import org.joml.Matrix4f;

public class Transform {
    public float x = 0f;
    public float y = 0f;

    public float scale = 1f;
    
    public float rotation = 0f;

    public Transform(float x, float y, float rotation, float scale ){
        this.x = x;
        this.y = y;

        this.rotation = rotation;

        this.scale = scale;
    }
    public Matrix4f getMatrix(){

        return new Matrix4f()
        .translate(x, y, 0f)
        .rotateZ(rotation)
        .scale(scale);
    }
}
