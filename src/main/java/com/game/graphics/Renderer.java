package com.game.graphics;

import com.game.objects.GameObject;

public class Renderer {
    
    private Shader shader;

    public Renderer(Shader shader){
        this.shader = shader;
    }

    public void render (GameObject obj){
        shader.use();

        shader.setVec2("offset", obj.transform.x, obj.transform.y);

        shader.setFloat(
            "scale",
            obj.transform.scale
        );

        shader.setFloat(
            "rotation",
            obj.transform.rotation
        );

        obj.mesh.render();
    }
}
