#version 330 core

layout(location = 0) in vec2 aPos;
layout(location = 1) in vec3 aColor;

out vec3 vertexColor;

uniform vec2 offset;

uniform float scale;

void main()
{
    vec2 position = (aPos * scale) + offset;

    gl_Position = vec4(position, 0.0, 1.0);

    vertexColor = aColor;
}