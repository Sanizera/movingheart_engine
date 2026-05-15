package com.game.graphics;

import com.game.objects.GameObject;

public class Renderer {
    
    private Shader shader;

    public Renderer(Shader shader){
        this.shader = shader;
    }

    public void render (GameObject obj){
        shader.use();

        shader.setMat4("transform", obj.transform.getMatrix());
        

        obj.mesh.render();
    }
}
