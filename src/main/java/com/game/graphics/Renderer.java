package com.game.graphics;

import org.joml.Matrix4f;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

public class Renderer {
    
    private Shader shader;

    private Matrix4f projection;

    public Renderer(Shader shader, Matrix4f projection){
        this.shader = shader;

        this.projection = projection;
    }

    public void begin (Camera2D camera){
        glClearColor(0, 0, 0, 1f);

        glClear(GL_COLOR_BUFFER_BIT);

        shader.use();

        shader.setMat4("projection", projection);

        shader.setMat4("view", camera.getViewMatrix());

    }

    public void end(){}
}
