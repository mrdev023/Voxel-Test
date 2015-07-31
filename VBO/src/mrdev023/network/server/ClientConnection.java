package mrdev023.network.server;

import java.io.*;
import java.net.*;
import java.util.*;

import mrdev023.network.packet.*;
import mrdev023.network.packet.wait.*;

public class ClientConnection {

	private Socket connection;
	private ObjectOutputStream out;
	private ClientReader in;
	private Thread th;
	public boolean IsRunning = true;
	public boolean IsError = false;
	
	public ClientConnection(Socket connection) throws IOException{
		this.connection = connection;
		out = new ObjectOutputStream(connection.getOutputStream());
		in = new ClientReader(this);
	}
	
	public ClientConnection start(){
		th = new Thread(in);
		th.start();
		return this;
	}

	public void sendData(Packet packet) throws IOException{
		out.writeObject(packet);
		out.flush();
	}
	
	public Socket getConnection() {
		return connection;
	}

	public void setConnection(Socket connection) {
		this.connection = connection;
	}

	public void destroy(){
		th.stop();
		try {
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		connection = null;
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out = null;
		try {
			in.in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		in = null;
		IsRunning = false;
	}
	
	public ObjectOutputStream getOut() {
		return out;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

	public ClientReader getIn() {
		return in;
	}

	public void setIn(ClientReader in) {
		this.in = in;
	}

	public Thread getTh() {
		return th;
	}

	public void setTh(Thread th) {
		this.th = th;
	}
	
	
}

class ClientReader implements Runnable{
	
	public ClientConnection client;
	public ObjectInputStream in;
	
	public ClientReader(ClientConnection client) throws IOException{
		this.client = client;
		in = new ObjectInputStream(client.getConnection().getInputStream());
	}

	public void run() {
		while(client.IsRunning){
			try {
				Object packet = in.readObject();
				if(packet != null){
					if(packet instanceof WaitPacket){
						manageWaitPacket((WaitPacket)packet);			
					}else if(packet instanceof Packet){
						if(packet != null){
							Server.packetManager.addPacket(client, (Packet)packet);
						}	
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
	
	public void manageWaitPacket(WaitPacket packet){
		packet.repPacket(client,packet);
	}
	
}