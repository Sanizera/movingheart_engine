package com.game.shapes;

public class Heart {

    public float[] verts = {
    //     x     y           color
        -0.5f, 0.0f,   1.0f, 0.0f, 0f,
        -0.25f, 0.5f,  1.0f, 0.0f, 0f,
         0.0f, 0.0f,   1.0f, 0.0f, 0f,
         0.25f, 0.5f,  1.0f, 0.0f, 0f,
         0.5f, 0.0f,   1.0f, 0.0f, 0f,
         0.0f, -0.75f, 1.0f, 0.0f, 0f,
    };

    public int[]inds = {
        0, 1, 2,
        2, 3, 4,

        0, 2, 5,
        2, 4, 5
    };
}
