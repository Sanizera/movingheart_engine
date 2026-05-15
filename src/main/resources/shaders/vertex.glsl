#version 330 core

layout(location = 0) in vec2 aPos;
layout(location = 1) in vec3 aColor;

out vec3 vertexColor;

uniform vec2 offset;

uniform float scale;

uniform float rotation;


void main()
{
    vec2 scaled = aPos * scale;

    // rotation
    float cosTheta = cos(rotation);
    float sinTheta = sin(rotation);

    vec2 rotated;

    rotated.x =     
        scaled.x * cosTheta - 
        scaled.y * sinTheta;

    rotated.y = 
        scaled.x * sinTheta +
        scaled.y * cosTheta;

    vec2 finalPosition = rotated + offset;

    gl_Position = vec4(finalPosition, 0.0, 1.0);

    vertexColor = aColor;
}