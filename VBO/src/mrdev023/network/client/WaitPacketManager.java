package mrdev023.network.client;

import java.io.*;
import java.util.*;

import mrdev023.network.packet.wait.*;

public class WaitPacketManager{

	public static HashMap<String,WaitPacket> waitPacketList = new HashMap<String,WaitPacket>();
	
	public static void sendWaitPacket(WaitPacket packet,Client client) throws IOException{
		packet.sendPacket(client, packet);
		client.sendData(packet);
		waitPacketList.put(packet.getId(), packet);
	}
	
	public static boolean isRep(String id){
		return waitPacketList.get(id).getIsRep();
	}
	
	public static void setWaitPacket(String id,WaitPacket packet){
		waitPacketList.replace(id, packet);
	}
}
