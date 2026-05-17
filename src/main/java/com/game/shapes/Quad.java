package com.game.shapes;

public class Quad {
    public float[] verts = {
        //pos               uv
        -0.5f, -0.5f,    0f, 1f,
         0.5f, -0.5f,    1f, 1f,
         0.5f, 0.5f,    1f, 0f,
        -0.5f, 0.5f,    0f, 0f
    };

    public int[] inds = {
        0, 1, 3,
        1, 2, 3
    };
}
