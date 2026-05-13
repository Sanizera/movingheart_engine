package com.meujogo.objects;

import com.meujogo.graphics.Mesh;

public class GameObject {
    
    public Mesh mesh;

    public Transform transform;

    public GameObject(Mesh mesh){
        this.mesh = mesh;

        this.transform = new Transform();
    }
}
