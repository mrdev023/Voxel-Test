package mrdev023.network.server;

import java.io.*;
import java.net.*;
import java.util.*;

import mrdev023.exception.*;
import mrdev023.io.*;
import mrdev023.math.*;
import mrdev023.network.packet.*;
import mrdev023.world.chunk.*;

public class Server {

	public static ServerSocket server;
	public static PacketManager packetManager;
	public static Thread threadPacketManager;
	public static boolean IsRunning = true;

	public static ArrayList<ClientConnection> clientList = new ArrayList<ClientConnection>();

	public static void initServer(){
		try {
			server = new ServerSocket(9999);
			server.setSoTimeout(1000);
			packetManager = new PacketManager();
			(threadPacketManager = new Thread(packetManager)).start();
			while(IsRunning){
				try{
					Socket client = server.accept();
					clientList.add(new ClientConnection(client).start());
					System.out.println("Client:" + client.getInetAddress().getHostAddress() + ":" + client.getPort() + " join game");
				}catch(Exception e){}

				for (ClientConnection cl : clientList) {
					try{
						cl.sendData(new TestPacket());
						if(cl.IsError)throw new LostConnectionException("Data Error");
					} catch (Exception e) {
						cl = null;
						clientList.remove(cl);
						System.out.println("Client:"
								+ cl.getConnection().getInetAddress()
										.getHostAddress() + ":"
								+ cl.getConnection().getPort() + " left game");
					}
				}
				
			}
			for(ClientConnection client : clientList){
				client.destroy();
			}
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public static Chunk getChunk(Vector3i pos){
		try {
			return IO.loadChunk(pos, "multiWorld");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
