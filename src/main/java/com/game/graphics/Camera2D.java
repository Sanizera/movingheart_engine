package com.game.graphics;

import org.joml.Matrix4f;
import org.joml.Vector2f;

public class Camera2D {

    public Vector2f position = new Vector2f();

    public Matrix4f getViewMatrix() {

        return new Matrix4f()
            .translate(
                    -position.x,
                    -position.y,
                    0f
            );

    }
}
