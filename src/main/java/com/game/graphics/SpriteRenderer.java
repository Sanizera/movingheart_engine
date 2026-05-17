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

    @Override
    public void render() {
        shader.use();

        shader.setMat4("transform", gameObject.transform.getMatrix());

        texture.bind();

        mesh.render();
   
    }
}
