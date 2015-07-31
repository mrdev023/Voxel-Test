package mrdev023.network.packet.wait;

import mrdev023.network.client.*;
import mrdev023.network.packet.*;
import mrdev023.network.server.*;

public abstract class WaitPacket implements Packet{

	protected boolean IsRep = false;
	protected String id;
	protected Object packet;
	
	public WaitPacket(String id){
		this.id = id;
	}
	
	public String getId(){
		return this.id;
	}
	
	public boolean getIsRep(){
		return this.IsRep;
	}
	
	public Object getPacket(){
		return this.packet;
	}
	
	public void setPacket(Object obj){
		this.packet = obj;
	}
	
	public abstract void repPacket(ClientConnection client,WaitPacket packet);
	public abstract void sendPacket(Client client,WaitPacket packet);
	
}
