package com.game.objects;

import com.game.graphics.Mesh;
import com.game.graphics.Shader;

public class GameObject {
    public Mesh mesh;
    public Shader shader;
    public Transform transform;

    public static GameObject create(
        Mesh mesh,
        Shader shader,
        float x,
        float  y,
        float scale
    ){
        return new GameObject(mesh , shader, new Transform(x, y, 0f , scale));
    }


    public GameObject(Mesh mesh, Shader shader, Transform transform){
        this.mesh = mesh;
        this.shader = shader;
        this.transform = transform;
    }
}
