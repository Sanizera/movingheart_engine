package com.game.world;

import org.joml.Matrix4f;

import com.game.graphics.Mesh;
import com.game.graphics.Renderer;
import com.game.graphics.Shader;

public class TileMap {

    private Tile[][] tiles;

    private int width;

    private int height;

    private int tileSize;

    private Mesh quad;

    private Shader shader;

    public TileMap(int width, int height, int tileSize, Mesh quad, Shader shader) {
        this.width = width;

        this.height = height;

        this.tileSize = tileSize;

        this.quad = quad;

        this.shader = shader;

        tiles = new Tile[height][width];
    }

    public void setTile(int x, int y, Tile tile) {
        tiles[y][x] = tile;
    }

    public void renderMap(Renderer renderer) {
        float mapWidth = width * tileSize;

        float mapHeight = height * tileSize;
        
        float startX = -mapWidth / 2f + tileSize / 2f;

        float startY = -mapHeight / 2f + tileSize / 2f;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                Tile tile = tiles[y][x];

                if (tile == null) {
                    continue;
                }

                float worldX = startX + x * tileSize;

                float worldY = startY + y * tileSize;

                Matrix4f transform = new Matrix4f()
                        .translate(worldX, worldY, 0f)
                        .scale(tileSize);

                renderer.drawSprite(quad, shader, tile.texture, transform);
            }
        }
    }

}
