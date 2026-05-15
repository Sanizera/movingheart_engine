package com.game.graphics;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUniform1f;
import static org.lwjgl.opengl.GL20.glUniform2f;
import static org.lwjgl.opengl.GL20.glUseProgram;

public class Shader {
    private int programId;

    public Shader(String vertexPath, String fragmentPath){

        String vertexSource = loadFile(vertexPath);

        String fragmentSource = loadFile(fragmentPath);


        // Vertex Shader - sombreador de vértices
        int vertexShader = glCreateShader(GL_VERTEX_SHADER);

        glShaderSource(vertexShader, vertexSource);

        glCompileShader(vertexShader);

        System.out.println(glGetShaderi(vertexShader, GL_COMPILE_STATUS));

        checkCompileErrors(vertexShader, "VERTEX");

        // Fragment Shader - sombreador de fragmentos(espaços)
        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);

        glShaderSource(fragmentShader, fragmentSource);

        glCompileShader(fragmentShader);

        System.out.println(glGetShaderi(fragmentShader, GL_COMPILE_STATUS));

        checkCompileErrors(fragmentShader, "FRAGMENT");

        // Shader Program

        programId = glCreateProgram();
        System.out.println(programId);

        glAttachShader(programId, vertexShader);
        glAttachShader(programId, fragmentShader);

        glLinkProgram(programId);

        checkLinkErrors(programId);

        // higiene
        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);
    }

    private String loadFile(String path){
        try {

            return new String(
                getClass()
                .getClassLoader()
                .getResourceAsStream(path)
                .readAllBytes()
            );

        } catch (IOException e) {

            throw new RuntimeException(
                "Erro ao carregar shader: " + path,
                e
            );

        }
    }

    private void checkCompileErrors(int shader, String type){
        if(glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE){
            System.out.println(type + " SHADER ERROR:");

            System.out.println(glGetShaderInfoLog(shader));
        }
    }

    private void checkLinkErrors(int program){

        if(glGetProgrami(program, GL_LINK_STATUS) == GL_FALSE){
            
            System.out.println(
                "PROGRAM LINK ERROR:"
            );

            System.out.println(
                glGetProgramInfoLog(program)
            );
        }
    }

    public void use(){

        glUseProgram(programId);

    }

    public int getId(){
        return programId;
    }

    public void setVec2(String name, float x, float y){
        int location = glGetUniformLocation(programId, name);

        glUniform2f(location, x, y);

    }
    public void setFloat(String name, float value){
        int location = glGetUniformLocation(programId, name);

        glUniform1f(location, value);

    }
}
