package game;

import static org.lwjgl.opengl.GL11.*;
import world.*;
import blocks.*;
import main.*;
import math.*;

public class Camera {

	private static Vector3f position = new Vector3f(0, 4, 0);
	private static Vector2f rotation = new Vector2f(0, 120);
	private static PlayerRayCast playerRaycast;
	private static final float HEIGHT = (float) (1.5f - (Block.getSize() / 2.0f));
	public static boolean gravity = true;
	public static float gravityFactor = 0;
	public static boolean grounded = false;
	public static boolean noClip = false;

	public static void initCamera() {
		playerRaycast = new PlayerRayCast();
	}

	public static void renderCamera() {
		if (position.getY() < (int)((new Noise(Main.getGame().getWorld().seed, Main
				.getGame().getWorld().octave,
				Main.getGame().getWorld().amplitude).getNoise(position.getX(),
				position.getZ())) + 1)) {
			
			position.setY((int)((new Noise(Main.getGame().getWorld().seed, Main
					.getGame().getWorld().octave,
					Main.getGame().getWorld().amplitude).getNoise(position.getX(),
					position.getZ())) + 2));
		}
		glLoadIdentity();
		glRotatef(rotation.getX(), 1, 0, 0);
		glRotatef(rotation.getY(), 0, 1, 0);
		glTranslatef(-position.getX(), -(position.getY() + getHeight()),
				-position.getZ());
	}

	public static void move(float xa, float ya, float za) {
		gravityFactor += World.GRAVITY * 0.01f;
		if (grounded)
			gravityFactor = 0;
		ya -= gravityFactor;
		int xStep = (int) Math.abs(xa * 100);
		for (int i = 0; i < xStep; i++) {
			if (!isColliding(xa / xStep, 0, 0) || noClip) {
				position.x += xa / xStep;
			} else {
				xa = 0;
			}
		}
		int yStep = (int) Math.abs(ya * 100);
		for (int i = 0; i < yStep; i++) {
			if (!isColliding(0, ya / yStep, 0) || noClip) {
				position.y += ya / yStep;
			} else {
				ya = 0;
			}
		}
		int zStep = (int) Math.abs(za * 100);
		for (int i = 0; i < zStep; i++) {
			if (!isColliding(0, 0, za / zStep) || noClip) {
				position.z += za / zStep;
			} else {
				za = 0;
			}
		}
	}

	public static Vector3f getDirection() {
		Vector3f r = new Vector3f();

		float cosY = (float) Math.cos(Math.toRadians(rotation.getY() - 90));
		float sinY = (float) Math.sin(Math.toRadians(rotation.getY() - 90));
		float cosP = (float) Math.cos(Math.toRadians(-rotation.getX()));
		float sinP = (float) Math.sin(Math.toRadians(-rotation.getX()));

		r.setX(cosY * cosP);
		r.setY(sinP);
		r.setZ(sinY * cosP);

		r.normalize();

		return r;
	}

	public static Vector3f getPosition() {
		return position;
	}

	public static void setPosition(Vector3f position) {
		Camera.position = position;
	}

	public static Vector2f getRotation() {
		return rotation;
	}

	public static void setRotation(Vector2f rotation) {
		Camera.rotation = rotation;
	}

	public static float getHeight() {
		return HEIGHT;
	}

	public static PlayerRayCast getPlayerRaycast() {
		return playerRaycast;
	}

	public static void setPlayerRaycast(PlayerRayCast playerRaycast) {
		Camera.playerRaycast = playerRaycast;
	}

	public static boolean isColliding(float xa, float ya, float za) {
		World world = Main.getGame().getWorld();
		float r = 0.3f;

		boolean nx = false, ny = false, nz = false;

		int x0 = (int) (position.x + xa - r);
		int x1 = (int) (position.x + xa + r);

		int y0 = (int) (position.y + ya - r);
		int y1 = (int) (position.y + ya + r);

		int z0 = (int) (position.z + za - r);
		int z1 = (int) (position.z + za + r);

		int xmid = (int) (position.x + xa);
		int yb = (int) (position.y + ya - r - 0.01f);
		int zmid = (int) (position.z + za);

		if (xmid < 0)
			nx = true;
		else
			nx = false;
		if (yb < 0)
			ny = true;
		else
			ny = false;
		if (zmid < 0)
			nz = true;
		else
			nz = false;
		if (world.getBlock(xmid, yb, zmid, nx, ny, nz) != null) {
			grounded = true;
		} else {
			grounded = false;
		}

		if (x0 < 0)
			nx = true;
		else
			nx = false;
		if (y0 < 0)
			ny = true;
		else
			ny = false;
		if (z0 < 0)
			nz = true;
		else
			nz = false;
		if (world.getBlock(x0, y0, z0, nx, ny, nz) != null)
			return true;

		if (x1 < 0)
			nx = true;
		else
			nx = false;
		if (y0 < 0)
			ny = true;
		else
			ny = false;
		if (z0 < 0)
			nz = true;
		else
			nz = false;
		if (world.getBlock(x1, y0, z0, nx, ny, nz) != null)
			return true;

		if (x1 < 0)
			nx = true;
		else
			nx = false;
		if (y1 < 0)
			ny = true;
		else
			ny = false;
		if (z0 < 0)
			nz = true;
		else
			nz = false;
		if (world.getBlock(x1, y1, z0, nx, ny, nz) != null)
			return true;

		if (x0 < 0)
			nx = true;
		else
			nx = false;
		if (y1 < 0)
			ny = true;
		else
			ny = false;
		if (z0 < 0)
			nz = true;
		else
			nz = false;
		if (world.getBlock(x0, y1, z0, nx, ny, nz) != null)
			return true;

		if (x0 < 0)
			nx = true;
		else
			nx = false;
		if (y0 < 0)
			ny = true;
		else
			ny = false;
		if (z1 < 0)
			nz = true;
		else
			nz = false;
		if (world.getBlock(x0, y0, z1, nx, ny, nz) != null)
			return true;

		if (x1 < 0)
			nx = true;
		else
			nx = false;
		if (y0 < 0)
			ny = true;
		else
			ny = false;
		if (z1 < 0)
			nz = true;
		else
			nz = false;
		if (world.getBlock(x1, y0, z1, nx, ny, nz) != null)
			return true;

		if (x1 < 0)
			nx = true;
		else
			nx = false;
		if (y1 < 0)
			ny = true;
		else
			ny = false;
		if (z1 < 0)
			nz = true;
		else
			nz = false;
		if (world.getBlock(x1, y1, z1, nx, ny, nz) != null)
			return true;

		if (x0 < 0)
			nx = true;
		else
			nx = false;
		if (y1 < 0)
			ny = true;
		else
			ny = false;
		if (z1 < 0)
			nz = true;
		else
			nz = false;
		if (world.getBlock(x0, y1, z1, nx, ny, nz) != null)
			return true;

		// if(world.getBlock(x0, y0+1, z0) != null) return true;
		// if(world.getBlock(x1, y0+1, z0) != null) return true;
		// if(world.getBlock(x1, y1+1, z0) != null) return true;
		// if(world.getBlock(x0, y1+1, z0) != null) return true;
		//
		// if(world.getBlock(x0, y0+1, z1) != null) return true;
		// if(world.getBlock(x1, y0+1, z1) != null) return true;
		// if(world.getBlock(x1, y1+1, z1) != null) return true;
		// if(world.getBlock(x0, y1+1, z1) != null) return true;

		return false;
	}

}
