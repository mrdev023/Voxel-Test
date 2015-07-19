package math;


public class Vector3f {
	
	public float x, y, z;
	
	public Vector3f() {
		this(0, 0, 0);
	}
	
	public Vector3f(Vector3f v) {
		this(v.x, v.y, v.z);
	}
	
	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float length() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}
	
	public Vector3f normalize() {
		x /= length();
		y /= length();
		z /= length();
		
		return this;
	}
	
	public Vector3f add(Vector3f vec) {
		x += vec.getX();
		y += vec.getY();
		z += vec.getZ();
		
		return this;
	}
	
	public Vector3f check(){
		float max = Math.max(Math.max(x, y),z);
		float min = Math.min(Math.min(x, y),z);
		
		float absMax = Math.abs(max - 1);
		float absMin = Math.abs(min);
		
		float v = 0;
		
		if(absMax>absMin)v=min;
		else v=max;
		
		int rv = 1;
		
		if(v<0.5f)rv=-1;
		
		return new Vector3f(v == x ? rv : 0,v == y ? rv : 0,v == z ? rv : 0);
	}
	
	public Vector3f copy(){
		return new Vector3f(this);
	}
	
	public Vector3f sub(Vector3f vec) {
		x -= vec.getX();
		y -= vec.getY();
		z -= vec.getZ();
		
		return this;
	}
	
	public void set(Vector3f vec){
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
	}
	
	public Vector3f mul(Vector3f vec) {
		x *= vec.getX();
		y *= vec.getY();
		z *= vec.getZ();
		
		return this;
	}
	
	public Vector3f div(Vector3f vec) {
		x /= vec.getX();
		y /= vec.getY();
		z /= vec.getZ();
		
		return this;
	}
	
	
	public Vector3f add(float v) {
		x += v;
		y += v;
		z += v;
		
		return this;
	}
	
	public Vector3f add(float x,float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
		
		return this;
	}
	
	public Vector3f sub(float v) {
		x -= v;
		y -= v;
		z -= v;
		
		return this;
	}
	
	public Vector3f mul(float v) {
		x *= v;
		y *= v;
		z *= v;
		
		return this;
	}
	
	public Vector3f div(float v) {
		x /= v;
		y /= v;
		z /= v;
		
		return this;
	}
	
	// ---- X
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}
	
	public Vector3f addX(float v) {
		x += v;
		return this;
	}
	public Vector3f subX(float v) {
		x -= v;
		return this;
	}
	
	
	// ----- Y
	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public Vector3f addY(float v) {
		y += v;
		return this;
	}
	public Vector3f subY(float v) {
		y -= v;
		return this;
	}
	
	
	// ----- Z
	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
	public Vector3f addZ(float v) {
		z += v;
		return this;
	}
	public Vector3f subZ(float v) {
		z -= v;
		return this;
	}
	
	public String toString(){
		return x + " " + y + " " + z;
	}
	
	public static float distance(Vector3f a,Vector3f b){
		return (float)Math.sqrt((Math.pow(b.getX()-a.getX(), 2))+(Math.pow(b.getY()-a.getY(), 2))+(Math.pow(b.getZ()-a.getZ(), 2)));
	}
	
}
