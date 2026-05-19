package com.game.core;

import org.joml.Matrix4f;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_CORE_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_PROFILE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetFramebufferSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glViewport;

import com.game.graphics.Renderer;
import com.game.input.Input;
import com.game.scenes.MainScene;

public class Window {

    private int width;
    private int height;

    private String title;

    private long window;

    private Matrix4f projection;

    private Renderer renderer;

    private Scene currentScene;

    private float deltaTime = 0f;
    private float lastFrame = 0f;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void run() {

        init();

        loop();

        destroy();

    }

    private void init() {

        if (!glfwInit()) {
            throw new IllegalStateException(
                    "Falha ao iniciar GLFW"
            );
        }

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);

        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);

        glfwWindowHint(
                GLFW_OPENGL_PROFILE,
                GLFW_OPENGL_CORE_PROFILE
        );

        window = glfwCreateWindow(width, height, title, 0, 0);

        if (window == 0) {
            throw new RuntimeException(
                    "Falha ao criar janela"
            );
        }

        glfwMakeContextCurrent(window);

        glfwSwapInterval(1);

        glfwShowWindow(window);

        GL.createCapabilities();

        glViewport(0, 0, width, height);

        glEnable(GL_BLEND);

        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        
        projection = createProjection(height, width);
        
        glfwSetFramebufferSizeCallback(window, (window, w, h) -> {
            width = w;
            height = h;
            
            glViewport(0, 0, w, h);
            
            projection = createProjection(w, h);
            
            renderer.setProjection(
                projection
            );
        });
        
        renderer = new Renderer(projection);
        
        currentScene = new MainScene(renderer);

        Input.init(window);

        currentScene.init();
    }

    private void loop() {

        while (!glfwWindowShouldClose(window)) {
            updateTime();

            currentScene.update(deltaTime);

            currentScene.render();

            glfwSwapBuffers(window);

            glfwPollEvents();
        }
    }

    private void updateTime() {
        float currentFrame = (float) glfwGetTime();

        deltaTime = currentFrame - lastFrame;

        lastFrame = currentFrame;
    }

    private void destroy() {

        glfwDestroyWindow(window);

        glfwTerminate();
    }

    private Matrix4f createProjection(int height, int width){
        return new Matrix4f().ortho(-width / 2f, width / 2f, height / 2f, -height / 2f, -1f, 1f);
    }
}
