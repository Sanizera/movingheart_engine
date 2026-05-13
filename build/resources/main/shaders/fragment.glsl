#version 330 core

in vec3 vertexColor;

out vec4 FragColoro;

void main()
{
    FragColor = vec4(vertexColor, 1.0);
}