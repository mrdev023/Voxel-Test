#version 330

layout (location = 0) in vec3 in_position;

out vec3 position;

void main(void){
	position = gl_Vertex.xyz;
	gl_Position = ftransform();
}