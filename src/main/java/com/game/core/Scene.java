package com.game.core;

public abstract class Scene {
    public abstract void init();

    public abstract void update(float deltaTime);

    public abstract void render();

    public abstract void dispose();
}
