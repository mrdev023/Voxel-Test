package mrdev023.network.client;

import java.util.*;
import java.util.Map.Entry;

import mrdev023.network.*;
import mrdev023.network.packet.*;

public class PacketManager implements Runnable{
	
	public ArrayList<Packet> packetList = new ArrayList<Packet>();
	
	public Client client;
	
	public PacketManager(Client client){
		this.client = client;
	}

	public void run() {
		while(client.IsRunning){
			for (Packet e : packetList) {
			    e.manageClientPacket(client, e);
			}
		}
	}

	public void addPacket(Packet packet){
		packetList.add(packet);
	}
	
}
