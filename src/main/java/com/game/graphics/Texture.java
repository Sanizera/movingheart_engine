package com.game.graphics;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

public class Texture {
    private int id;

    public Texture(String path){

        id = glGenTextures();
        
        glBindTexture(GL_TEXTURE_2D, id);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        try(MemoryStack stack = MemoryStack.stackPush()){

            IntBuffer w = stack.mallocInt(1);

            IntBuffer h = stack.mallocInt(1);

            IntBuffer channels = stack.mallocInt(1);

            STBImage.stbi_set_flip_vertically_on_load(true);

            ByteBuffer image = STBImage.stbi_load(path, w, h, channels, 4);

            if (image == null){
                throw new RuntimeException("Erro ao carregar textura");
            }

            glTexImage2D(
                GL_TEXTURE_2D,
                0,
                GL_RGBA,
                w.get(),
                h.get(),
                0,
                GL_RGBA,
                GL_UNSIGNED_BYTE,
                image
            );

            STBImage.stbi_image_free(image);

        }
    }
    public void bind(){
        glBindTexture(GL_TEXTURE_2D, id);
     
    }
}   
