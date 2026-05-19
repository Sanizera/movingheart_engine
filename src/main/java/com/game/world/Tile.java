package com.game.world;

import com.game.graphics.Texture;

public class Tile{
    
    public Texture texture;
    public int tileSize = 64;
    public Tile(Texture texture){
        this.texture = texture;
    }
}
