package mrdev023.network.packet;

import java.io.*;

import mrdev023.io.*;
import mrdev023.math.*;
import mrdev023.network.client.*;
import mrdev023.network.packet.wait.*;
import mrdev023.network.server.*;
import mrdev023.world.chunk.*;

public class ChunkPacket implements Packet{
	
	public Vector3i position;
	public Chunk chunk = null;
	
	public ChunkPacket(Vector3i position){
		this.position = position;
		this.chunk = null;
	}
	
	public void setChunk(Chunk ch){
		this.chunk = ch;
	}

	public void manageClientPacket(Client client, Packet packet) {
		WaitPacketManager.setWaitPacket(((WaitPacket)packet).getId(), (WaitPacket)packet);
	}

	public void manageServerPacket(ClientConnection client, Packet packet) {
		if((((WaitPacket)packet).getPacket()) != null){
			Chunk ch = (Chunk)((((WaitPacket)packet).getPacket()));
			if(Server.getChunk(ch.getPosition()) == null){
				try {
					IO.saveChunk(ch, "multiWorld");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			try {
				Vector3i pos = ((ChunkPacket)(((WaitPacket)packet).getPacket())).position;
				((WaitPacket)packet).setPacket(Server.getChunk(pos));
				client.sendData(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}

}
