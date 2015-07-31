package mrdev023.network.server;

import java.util.*;
import java.util.Map.Entry;

import mrdev023.network.*;
import mrdev023.network.packet.*;
import mrdev023.network.server.*;

public class PacketManager implements Runnable{
	
	public HashMap<Packet,ClientConnection> packetList = new HashMap<Packet,ClientConnection>();
	
	public PacketManager(){
		
	}

	public void run() {
		while(Server.IsRunning){
			for (Entry<Packet, ClientConnection> e : packetList.entrySet()) {
				Packet packet = e.getKey();
			    ClientConnection client = e.getValue();
			    packet.manageServerPacket(client, packet);
			}
		}
	}

	public void addPacket(ClientConnection client,Packet packet){
		packetList.put(packet, client);
	}
	
}
