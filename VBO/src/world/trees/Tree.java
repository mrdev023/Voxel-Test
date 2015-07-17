package world.trees;

import world.*;
import blocks.*;


public class Tree {

	public static void addOak(World world, int x, int y, int z) {
		int treeHeight = 9;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				for (int k = 0; k < 9; k++) {
					float ii = i - 4.5f;
					float jj = j - 4.5f;
					float kk = k - 4.5f;
					float l = (float) Math.sqrt(ii * ii + jj * jj + kk * kk);

					if (l < 4.5f) {
						world.addBlock(x + (int) ii, y + (int) jj + treeHeight,
								z + (int) kk, Block.LEAF);
					}
				}
			}
		}
		for (int i = 0; i < treeHeight; i++) {
			world.addBlock(x, y + i, z, Block.OAK_WOOD);
		}

	}

	public static void addFir(World world, int x, int y, int z) {
		int treeHeight = 13;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 13; j++) {
				for (int k = 0; k < 9; k++) {
					float ii = i - 4f;
					float jj = j - 3f;
					float kk = k - 4f;
					float l = (float) Math.sqrt(ii * ii + kk * kk);
					float size = 1;
					
					size -= (float)j /13.0f;
					
					if(l < 4.5f * size){
						world.addBlock(x + (int) ii, y + (int) jj + 7, z
								+ (int) kk, Block.FIR_LEAF);
					}
				}
			}
		}
		for (int i = 0; i < treeHeight; i++) {
			world.addBlock(x, y + i, z, Block.FIR_WOOD);
		}

	}
}
