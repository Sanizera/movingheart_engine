package com.game.shapes;

import com.game.graphics.Mesh;

public class PrimitiveFactory {

    public static Mesh createHeart() {
        float[] verts = {
            //     x     y           color
            -0.5f, 0.0f, 1.0f, 0.0f, 0f,
            -0.25f, -0.5f, 1.0f, 0.0f, 0f,
            0.0f, 0.0f, 1.0f, 0.0f, 0f,
            0.25f, -0.5f, 1.0f, 0.0f, 0f,
            0.5f, 0.0f, 1.0f, 0.0f, 0f,
            0.0f, 0.75f, 1.0f, 0.0f, 0f,};

        int[] inds = {
            0, 1, 2,
            2, 3, 4,
            0, 2, 5,
            2, 4, 5
        };
        return new Mesh(verts, inds);
    }

    public static Mesh createSquare() {
        float[] verts = {
            -0.5f, -0.5f, 1f, 0f, 0f,
            0.5f, -0.5f, 1f, 0f, 0f,
            0.5f, 0.5f, 1f, 0f, 0f,
            -0.5f, 0.5f, 1f, 0f, 0f
        };

        int[] inds = {
            0, 1, 3,
            1, 2, 3
        };

        return new Mesh(verts, inds);
    }

    public static Mesh createTriangle() {
        float[] verts = {
            -0.5f, -0.5f, 1f, 0f, 0f,
            0.5f, -0.5f, 1f, 0f, 0f,
            0.0f, 0.5f, 1f, 0f, 0f
        };

        int[] inds = {
            0, 1, 2
        };

        return new Mesh(verts, inds);
    }

    public static Mesh createQuad() {
        Quad quad = new Quad();


        return new Mesh(quad.verts, quad.inds);
    }
}
