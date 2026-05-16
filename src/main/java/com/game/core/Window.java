package com.game.core;

import org.joml.Matrix4f;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_E;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Q;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_X;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Z;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_CORE_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glViewport;

import com.game.graphics.Camera2D;
import com.game.graphics.Mesh;
import com.game.graphics.Renderer;
import com.game.graphics.Shader;
import com.game.objects.GameObject;
import com.game.shapes.PrimitiveFactory;

public class Window {

    private int width;
    private int height;

    private String title;

    private long window;

    // tempo
    private float deltaTime = 0f;
    private float lastFrame = 0f;

    // renderização
    private Shader shader;

    private Renderer renderer;

    private Matrix4f projection;

    private Matrix4f view;
    // objetos 

    private GameObject heart;

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

        shader = new Shader("shaders/vertex.glsl", "shaders/fragment.glsl");

        projection = new Matrix4f().ortho(0f, width, height, 0f, -1f, 1f);

        renderer = new Renderer(shader);

        Camera2D camera = new Camera2D();

        view = camera.getViewMatrix();
        

        Mesh heartMesh = PrimitiveFactory.createHeart();

        heart = GameObject.create(heartMesh, shader, 300, 300, 300);

    }

    private void loop() {

        while (!glfwWindowShouldClose(window)) {

            updateTime();

            input();

            render();

            shader.setMat4("projection", projection);

            shader.setMat4("view", view);

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

    private void input() {
        float speed = 1000f;
        if (glfwGetKey(window, GLFW_KEY_W) == GLFW_PRESS) {
            heart.transform.position.y -= speed * deltaTime;
        }
        
        if (glfwGetKey(window, GLFW_KEY_S) == GLFW_PRESS) {
            heart.transform.position.y += speed * deltaTime;
        }
        
        if (glfwGetKey(window, GLFW_KEY_A) == GLFW_PRESS) {
            heart.transform.position.x -= speed * deltaTime;
        }

        if (glfwGetKey(window, GLFW_KEY_D) == GLFW_PRESS) {
            heart.transform.position.x += speed * deltaTime;
        }

        if (glfwGetKey(window, GLFW_KEY_E) == GLFW_PRESS) {
            heart.transform.scale.x += 100f * deltaTime;
        }

        if (glfwGetKey(window, GLFW_KEY_Q) == GLFW_PRESS) {
            heart.transform.scale.y += 100f * deltaTime;
        }
        if (glfwGetKey(window, GLFW_KEY_F) == GLFW_PRESS) {
            heart.transform.scale.x -= 100f* deltaTime;
            heart.transform.scale.y -= 100f* deltaTime;
        }
        if (glfwGetKey(window, GLFW_KEY_Z) == GLFW_PRESS) {
            heart.transform.rotation -= 3f * deltaTime;
        }
        
        if (glfwGetKey(window, GLFW_KEY_X) == GLFW_PRESS) {
            heart.transform.rotation += 3f * deltaTime;
        }
        

    }

    private void render() {
        glClearColor(0f, 0f, 0f, 1f);

        glClear(GL_COLOR_BUFFER_BIT);

        renderer.render(heart);

        glfwSwapBuffers(window);
    }
}
