package com.game.scenes;

import org.joml.Vector2f;

import com.game.core.Scene;
import com.game.graphics.Camera2D;
import com.game.graphics.Mesh;
import com.game.graphics.Renderer;
import com.game.graphics.Shader;
import com.game.graphics.SpriteRenderer;
import com.game.graphics.Texture;
import com.game.input.Input;
import com.game.objects.GameObject;
import com.game.objects.components.PlayerController;
import com.game.shapes.PrimitiveFactory;
import com.game.world.Tile;
import com.game.world.TileMap;

public class MainScene extends Scene{
//  textures
    private Texture grassTex;
    private Texture playerTex;
//  render
    public Renderer renderer;
    private Mesh quad;
    private Camera2D camera;
    public Shader shader;

//  environment
    private TileMap map;

//  objects
    private GameObject player;

//input
    private Input input;

    public MainScene(Renderer renderer){
        this.renderer = renderer;
    }
    @Override
    public void init() {
        quad = PrimitiveFactory.createQuad();
        
        shader = new Shader( "shaders/vertex.glsl", "shaders/fragment.glsl" );

        grassTex = new Texture("grassTile.png");
        Tile grass = new Tile(grassTex);


        camera = new Camera2D();

        int mapWidth = 50;
        int mapHeight = 50;
        
        map = new TileMap(mapWidth, mapHeight, grass.tileSize, quad, shader);
        
        for(int y = 0; y < mapHeight; y++){
            for(int x = 0; x < mapWidth; x++){
                map.setTile(x, y, grass);
            }   
        }

        playerTex = new Texture("joaquim.png");
        player = new GameObject();
        player.addComponent(new PlayerController());
        player.addComponent(new SpriteRenderer(quad, shader, playerTex));
        
        player.transform.scale = new Vector2f(256f, 256f);

        
    }

    @Override
    public void update(float deltaTime) {
        player.update(deltaTime);
        camera.position.lerp(player.transform.position, 5f*deltaTime); 
    }

    @Override
    public void render() {
        renderer.begin(camera);

        map.renderMap(renderer);

        renderer.render(player);

        renderer.end();
    }

    @Override
    public void dispose() {
    

    }
    public Shader getShader(){
        return shader;
    }
    public Renderer getRenderer(){
        return renderer;
    }
}
