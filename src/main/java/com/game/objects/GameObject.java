package com.game.objects;

import com.game.graphics.Mesh;

public class GameObject {
    
    public Mesh mesh;

    public Transform transform;

    public GameObject(Mesh mesh){
        this.mesh = mesh;

        this.transform = new Transform();
    }
}
