package com.game.input;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.glfwGetKey;

public class Input{

    private static long window;

    public static void init(long win){
        window = win;
    }

    public static boolean isKeyDown(int key){

        return glfwGetKey(window, key) == GLFW_PRESS;
    }
}