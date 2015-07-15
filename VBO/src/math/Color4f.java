package math;

public class Color4f {

	private float r,g,b,a;
	
	public Color4f(float r,float g,float b,float a){
		this.r = r;
		this.b = b;
		this.g = g;
		this.a = a;
	}
	
	public Color4f(float r,float g,float b){
		this.r = r;
		this.b = b;
		this.g = g;
		this.a = 1;
	}
	
	public Color4f(Color4f c){
		this.r = c.getR();
		this.b = c.getG();
		this.g = c.getB();
		this.a = c.getA();
	}
	
	public static Color4f interpolate(Color4f ca, Color4f cb, float t){
		float r = ca.r + (cb.r - ca.r) * t;
		float g = ca.g + (cb.g - ca.g) * t;
		float b = ca.b + (cb.b - ca.b) * t;
		float a = ca.a + (cb.a - ca.a) * t;
		return new Color4f(r,g,b,a);
	}
	
	public Color4f add(Color4f c){
		r += c.getR();
		g += c.getG();
		b += c.getB();
		a += c.getA();
		return this;
	}

	public Color4f sub(Color4f c){
		r -= c.getR();
		g -= c.getG();
		b -= c.getB();
		a -= c.getA();
		return this;
	}
	
	public Color4f mul(float c){
		r *= c;
		g *= c;
		b *= c;
		a *= c;
		return this;
	}
	
	public Color4f copy(){
		return new Color4f(r,g,b,a);
	}
	
	public void div(Color4f c){
		r /= c.getR();
		g /= c.getG();
		b /= c.getB();
		a /= c.getA();
	}
	
	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public float getG() {
		return g;
	}

	public void setG(float g) {
		this.g = g;
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = b;
	}

	public float getA() {
		return a;
	}

	public void setA(float a) {
		this.a = a;
	}
	
	
	
}
