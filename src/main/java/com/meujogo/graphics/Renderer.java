package com.meujogo.graphics;

import com.meujogo.objects.GameObject;

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

        obj.mesh.render();
    }
}
