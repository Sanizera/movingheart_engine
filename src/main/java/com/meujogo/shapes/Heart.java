package com.meujogo.shapes;

import com.meujogo.objects.Transform;

public class Heart {
    public Transform transform = new Transform();

    public float[] verts = {
        -0.5f, 0.0f,   1.0f, 0.0f, 0.0f,
        -0.25f, 0.5f,  1.0f, 0.0f, 0.0f,
         0.0f, 0.0f,   1.0f, 0.0f, 0.0f,
         0.25f, 0.5f,  1.0f, 0.0f, 0.0f,
         0.5f, 0.0f,   1.0f, 0.0f, 0.0f,
         0.0f, -0.75f, 1.0f, 0.0f, 0.0f,
    };

    public int[]inds = {
        0, 1, 2,
        2, 3, 4,

        0, 2, 5,
        2, 4, 5
    };
}
