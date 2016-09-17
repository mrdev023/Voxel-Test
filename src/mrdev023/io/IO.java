package mrdev023.io;

import java.io.*;
import java.util.zip.*;

import mrdev023.blocks.*;
import mrdev023.math.*;
import mrdev023.world.*;
import mrdev023.world.chunk.*;

public class IO {

	public static void saveChunk(Chunk c,String map) throws IOException{
		long current = System.currentTimeMillis();
		String file = "save" + File.separatorChar + map + File.separatorChar + c.getX() + "_" + c.getY() + "_" + c.getZ() + ".chunk";
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(file))));
		oos.writeObject(c.getBlocks());
		oos.flush();
		oos.close();
		System.out.println("File: " + file + " saved in " + (System.currentTimeMillis() - current) + "ms");
	}
	
	public static Chunk loadChunk(Vector3i c,String map) throws IOException, ClassNotFoundException{
		long current = System.currentTimeMillis();
		String file = "save" + File.separatorChar + map + File.separatorChar + c.getX() + "_" + c.getY() + "_" + c.getZ() + ".chunk";
		FileInputStream fis=new FileInputStream(file);
	    GZIPInputStream gzis=new GZIPInputStream(fis);
	    BufferedInputStream bis = new BufferedInputStream(gzis);
	    ObjectInputStream in=new ObjectInputStream(bis);
		Chunk c1 = new Chunk((int)c.getX(),(int)c.getY(),(int)c.getZ());
		Block[][][] blocks = (Block[][][])in.readObject();
		c1.setBlocks(blocks);
		in.close();
		gzis.close();
		bis.close();
		fis.close();
		System.out.println("File: " + file + " loaded in " + (System.currentTimeMillis() - current) + "ms");
		return c1;
	}
	
	public static boolean chunkIsSaved(Vector3i c,String map){
		String file = "save" + File.separatorChar + map + File.separatorChar + c.getX() + "_" + c.getY() + "_" + c.getZ() + ".chunk";
		File f = new File(file);
		return f.isFile();
	}
	
	public static byte[] compressData(Object data) throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzout = new GZIPOutputStream(out);
		BufferedOutputStream bout = new BufferedOutputStream(gzout);
		ObjectOutputStream oos = new ObjectOutputStream(bout);
		oos.writeObject(data);
		oos.flush();
		oos.close();
		bout.close();
		gzout.close();
		byte[] outData = out.toByteArray();
		out.close();
		return outData;
	}
	
	public static Object decompressData(byte[] data) throws IOException, ClassNotFoundException{
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		GZIPInputStream gzin = new GZIPInputStream(in);
		BufferedInputStream bin = new BufferedInputStream(gzin);
		ObjectInputStream ois = new ObjectInputStream(bin);
		Object obj = ois.readObject();
		ois.close();
		gzin.close();
		bin.close();
		in.close();
		return obj;
	}
	
	
	
}
