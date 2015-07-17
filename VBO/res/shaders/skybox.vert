#version 330

layout (location = 0) in vec3 in_position;

out vec3 position;
out vec3 rotation;

void main(void){
	rotation = vec3(0,0,0);
	position = gl_Vertex.xyz;
	gl_Position = ftransform();
}