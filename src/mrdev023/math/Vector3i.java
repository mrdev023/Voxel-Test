package mrdev023.math;


public class Vector3i {
	
	public int x, y, z;
	
	public Vector3i() {
		this(0, 0, 0);
	}
	
	public Vector3i(Vector3i v) {
		this(v.x, v.y, v.z);
	}
	
	public Vector3i(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int length() {
		return (int) Math.sqrt(x * x + y * y + z * z);
	}
	
	public Vector3i normalize() {
		x /= length();
		y /= length();
		z /= length();
		
		return this;
	}
	
	public Vector3i add(Vector3i vec) {
		x += vec.getX();
		y += vec.getY();
		z += vec.getZ();
		
		return this;
	}
	
	public Vector3i check(){
		int max = Math.max(Math.max(x, y),z);
		int min = Math.min(Math.min(x, y),z);
		
		int absMax = Math.abs(max - 1);
		int absMin = Math.abs(min);
		
		int v = 0;
		
		if(absMax>absMin)v=min;
		else v=max;
		
		int rv = 1;
		
		if(v<0.5f)rv=-1;
		
		return new Vector3i(v == x ? rv : 0,v == y ? rv : 0,v == z ? rv : 0);
	}
	
	public Vector3i copy(){
		return new Vector3i(this);
	}
	
	public Vector3i sub(Vector3i vec) {
		x -= vec.getX();
		y -= vec.getY();
		z -= vec.getZ();
		
		return this;
	}
	
	public void set(Vector3i vec){
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
	}
	
	public Vector3i mul(Vector3i vec) {
		x *= vec.getX();
		y *= vec.getY();
		z *= vec.getZ();
		
		return this;
	}
	
	public Vector3i div(Vector3i vec) {
		x /= vec.getX();
		y /= vec.getY();
		z /= vec.getZ();
		
		return this;
	}
	
	
	public Vector3i add(int v) {
		x += v;
		y += v;
		z += v;
		
		return this;
	}
	
	public Vector3i add(int x,int y, int z) {
		this.x += x;
		this.y += y;
		this.z += z;
		
		return this;
	}
	
	public Vector3i sub(int v) {
		x -= v;
		y -= v;
		z -= v;
		
		return this;
	}
	
	public Vector3i mul(int v) {
		x *= v;
		y *= v;
		z *= v;
		
		return this;
	}
	
	public Vector3i div(int v) {
		x /= v;
		y /= v;
		z /= v;
		
		return this;
	}
	
	// ---- X
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public Vector3i addX(int v) {
		x += v;
		return this;
	}
	public Vector3i subX(int v) {
		x -= v;
		return this;
	}
	
	
	// ----- Y
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Vector3i addY(int v) {
		y += v;
		return this;
	}
	public Vector3i subY(int v) {
		y -= v;
		return this;
	}
	
	
	// ----- Z
	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	public Vector3i addZ(int v) {
		z += v;
		return this;
	}
	public Vector3i subZ(int v) {
		z -= v;
		return this;
	}
	
	public String toString(){
		return x + " " + y + " " + z;
	}
	
	public static int distance(Vector3i a,Vector3i b){
		return (int)Math.sqrt((Math.pow(b.getX()-a.getX(), 2))+(Math.pow(b.getY()-a.getY(), 2))+(Math.pow(b.getZ()-a.getZ(), 2)));
	}
	
}
