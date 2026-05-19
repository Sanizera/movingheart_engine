package com.game.core;

import org.joml.Matrix4f;
import org.joml.Vector2f;
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

import com.game.graphics.Camera2D;
import com.game.graphics.Mesh;
import com.game.graphics.Renderer;
import com.game.graphics.Shader;
import com.game.graphics.SpriteRenderer;
import com.game.graphics.Texture;
import com.game.objects.GameObject;
import com.game.objects.Input;
import com.game.objects.PlayerController;
import com.game.shapes.PrimitiveFactory;
import com.game.world.Tile;
import com.game.world.TileMap;

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

    private Camera2D camera;

    private Matrix4f projection;

    private Texture playerTexture;

    private Texture tileTexture;
    // objetos 
    private Tile grass;

    private GameObject player;

    private TileMap map;

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

        
        shader = new Shader("shaders/vertex.glsl", "shaders/fragment.glsl");
        
        projection = new Matrix4f().ortho(-width/2f , width/2f , height/2f , -height/2f , -1f , 1f);
        
        renderer = new Renderer(shader, projection);

        glfwSetFramebufferSizeCallback(window, (window, w, h) -> {
            width = w;
            height = h;
            
            glViewport(0, 0, w, h);

            projection = new Matrix4f()
            .ortho(-w/2f, w/2f, h/2f, -h/2f, -1f, 1f);

            renderer.setProjection(
                projection
            );
        });

        playerTexture = new Texture("src/main/resources/textures/heart.png");

        tileTexture = new Texture("src/main/resources/textures/grassTile.png");

        camera = new Camera2D();

        Input.init(window);

        Mesh quad = PrimitiveFactory.createQuad();

        grass = new Tile(tileTexture);

        int mapWidth = 200;

        int mapHeight = 200;

        map = new TileMap(mapWidth, mapHeight, 64, quad, shader);

        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                map.setTile(x, y, grass);
            }
        }   

        player = new GameObject();

        player.transform.scale = new Vector2f(64f, 64f);

        player.addComponent(
                new SpriteRenderer(quad, shader, playerTexture)
        );
        player.addComponent(new PlayerController());

    }

    private void loop() {

        while (!glfwWindowShouldClose(window)) {
            updateTime();

            renderer.begin(camera);

            map.renderMap();
            
            player.update(deltaTime);

            camera.position.lerp(player.transform.position, 6f * deltaTime); 

            player.render();

            renderer.end();

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

}
