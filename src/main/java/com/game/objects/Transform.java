package com.game.objects;



import org.joml.Matrix4f;
import org.joml.Vector2f;

public class Transform {
    public Vector2f position;

    public Vector2f scale;
    
    public float rotation;

    public Transform(){

        this.position = new Vector2f(1f, 1f);

        this.rotation = 0f;

        this.scale = new Vector2f(1, 1);
    }

    public Transform(float x, float y, float rotation, float scale){

        this.position = new Vector2f(x, y);

        this.rotation = rotation;

        this.scale = new Vector2f(scale, scale);
    }

    public Transform(Vector2f position,  float rotation, Vector2f scale ){

        this.position = position;

        this.rotation = rotation;

        this.scale = scale;
    }
    
    public Matrix4f getMatrix(){

        return new Matrix4f()
        .translate(position.x, position.y, 0f)
        .rotateZ(rotation)
        .scale(scale.x, scale.y, 1f);
    }
}
