package com.game.graphics;
import com.game.objects.Component;
public class SpriteRenderer extends Component {
    private Mesh mesh;

    private Shader shader;

    private Texture texture;

    public SpriteRenderer(Mesh mesh, Shader shader, Texture texture){
        this.mesh = mesh;

        this.shader = shader;

        this.texture = texture;
    }


    public Shader getShader(){
        return shader;
    }

    public Mesh getMesh(){
        return mesh;
    }

    public Texture getTexture(){
        return texture;
    }
}
