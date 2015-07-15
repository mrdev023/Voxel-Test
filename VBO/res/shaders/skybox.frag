#version 330
out vec4 fragColor;
uniform samplerCube cubeMap;

in vec3 position;


void main(void){
 fragColor = texture(cubeMap, position);
}