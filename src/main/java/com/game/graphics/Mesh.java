package com.game.graphics;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
public class Mesh {

    private int vao;
    private int vbo;
    private int ebo;
    private int indexCount;

    public Mesh(float[] verts, int[] inds) {
        vao = glGenVertexArrays();
        vbo = glGenBuffers();
        ebo = glGenBuffers();

        indexCount = inds.length;

        glBindVertexArray(vao);

        //VBO - Vertex Buffer Object 
        //objeto que armazena as propriedades dos vértices
        glBindBuffer(GL_ARRAY_BUFFER, vbo);

        glBufferData(
                GL_ARRAY_BUFFER,
                verts,
                GL_STATIC_DRAW
        );

        //EBO - Element Buffer Object
        //objeto que armazena a ordem de desenho dos vértices
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);

        glBufferData(
                GL_ELEMENT_ARRAY_BUFFER,
                inds,
                GL_STATIC_DRAW
        );

        glVertexAttribPointer(
                0,
                2,
                GL_FLOAT,
                false,
                5 * Float.BYTES,
                0L
        );

        glEnableVertexAttribArray(0);

        glVertexAttribPointer(
            1,
            3,
            GL_FLOAT,
            false,
            5 * Float.BYTES,
            2L * Float.BYTES
        );

        glEnableVertexAttribArray(1);

    }

    public void render() {
        glBindVertexArray(vao);

        glDrawElements(GL_TRIANGLES, indexCount, GL_UNSIGNED_INT, 0);
        glBindVertexArray(0);
    }
}
