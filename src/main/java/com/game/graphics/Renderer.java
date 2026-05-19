package com.game.graphics;

import org.joml.Matrix4f;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import com.game.objects.GameObject;

public class Renderer {

    private Matrix4f projection;

    private Camera2D camera;

    public Renderer(Matrix4f projection) {
        this.projection = projection;
    }

    public void begin(Camera2D camera) {

        this.camera = camera;

        glClearColor(0, 0, 0, 1f);

        glClear(GL_COLOR_BUFFER_BIT);

    }

    public void render(GameObject obj) {
        SpriteRenderer sprite = obj.getComponent(SpriteRenderer.class);

        if (sprite == null) {
            return;
        }

        Shader shader = sprite.getShader();

        Texture texture = sprite.getTexture();

        Mesh mesh = sprite.getMesh();

        Matrix4f transform = obj.transform.getMatrix();

        drawSprite(mesh, shader, texture, transform);
    }

    public void drawSprite(Mesh mesh, Shader shader, Texture texture, Matrix4f transform) {
        shader.use();

        shader.setMat4("projection", projection);

        shader.setMat4("view", camera.getViewMatrix());

        shader.setMat4("transform", transform);

        texture.bind();

        mesh.render();
    }

    public void setProjection(Matrix4f projection) {
        this.projection = projection;
    }

    public void end() {
    }
}
