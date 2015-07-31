package mrdev023.network.client;

import java.io.*;
import java.net.*;

import mrdev023.network.packet.*;
import mrdev023.network.packet.wait.*;

public class Client {
	
	private String ip;
	private short port;
	private Socket connection;
	public boolean IsError = false;
	public boolean IsRunning = true;
	private ObjectOutputStream out;
	private PacketManager paketManager;
	private Thread threadPacketManager;

	public Client(String ip,short port)throws Exception{
		this.ip = ip;
		this.port = port;
		this.connection = new Socket(ip, port);
		this.out = new ObjectOutputStream(connection.getOutputStream());
		this.paketManager = new PacketManager(this);
		this.threadPacketManager = new Thread(this.paketManager);
		this.threadPacketManager.start();
	}
	
	public void sendData(Packet packet) throws IOException{
		out.writeObject(packet);
		out.flush();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public short getPort() {
		return port;
	}

	public void setPort(short port) {
		this.port = port;
	}

	public Socket getConnection() {
		return connection;
	}

	public void setConnection(Socket connection) {
		this.connection = connection;
	}

	public boolean isIsError() {
		return IsError;
	}

	public void setIsError(boolean isError) {
		IsError = isError;
	}

	public boolean isIsRunning() {
		return IsRunning;
	}

	public void setIsRunning(boolean isRunning) {
		IsRunning = isRunning;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

	public PacketManager getPaketManager() {
		return paketManager;
	}

	public void setPaketManager(PacketManager paketManager) {
		this.paketManager = paketManager;
	}

	public Thread getThreadPacketManager() {
		return threadPacketManager;
	}

	public void setThreadPacketManager(Thread threadPacketManager) {
		this.threadPacketManager = threadPacketManager;
	}
	
	
	
}
class ClientReader implements Runnable{
	
	private Client client;
	private ObjectInputStream in;
	
	public ClientReader(Client client){
		this.client = client;
	}

	public void run() {
		while(client.IsRunning){
			try {
				Object packet = in.readObject();
				if(packet != null){
					if(packet instanceof WaitPacket){
						WaitPacketManager.setWaitPacket(((WaitPacket)packet).getId(), (WaitPacket)packet);
					}else if(packet instanceof Packet){
						client.getPaketManager().addPacket((Packet)packet);						
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				client.IsError = true;
				e.printStackTrace();
			}
		}
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public void setIn(ObjectInputStream in) {
		this.in = in;
	}
	
	
	
}