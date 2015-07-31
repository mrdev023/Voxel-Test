package mrdev023.network.packet.wait;

import mrdev023.network.client.*;
import mrdev023.network.packet.*;
import mrdev023.network.server.*;

public class ChunkWaitPacket extends WaitPacket{

	public ChunkWaitPacket(String id) {
		super("chunkWaitPacket" + (Math.random()*20000));
	}

	public void manageClientPacket(Client client, Packet packet) {
		((ChunkPacket)(((WaitPacket)packet).getPacket())).manageClientPacket(client, packet);
	}

	public void manageServerPacket(ClientConnection client, Packet packet) {
		((ChunkPacket)(((WaitPacket)packet).getPacket())).manageServerPacket(client, packet);
	}

	public void repPacket(ClientConnection client, WaitPacket packet) {
		packet.IsRep = true;
	}

	public void sendPacket(Client client, WaitPacket packet) {
	}

}
