package com.game.objects.components;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;

import com.game.input.Input;
import com.game.objects.Component;

public class PlayerController extends Component {

    @Override
    public void update(float deltaTime){
        float speed = 300f;

        if(Input.isKeyDown(GLFW_KEY_W)){
            gameObject.transform.position.y-= speed * deltaTime;
        }

        if(Input.isKeyDown(GLFW_KEY_S)){
            gameObject.transform.position.y+= speed * deltaTime;
        }

        if(Input.isKeyDown(GLFW_KEY_A)){
            gameObject.transform.position.x -= speed * deltaTime;
        }

        if(Input.isKeyDown(GLFW_KEY_D)){
            gameObject.transform.position.x += speed * deltaTime;
        }

        // if(Input.isKeyDown(GLFW_KEY_SPACE)){
        //     gameObject.transform.position.y -= speed * deltaTime;
        // }

    }
    
}
