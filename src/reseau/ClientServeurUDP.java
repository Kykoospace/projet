package reseau;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public abstract class ClientServeurUDP {

	protected DatagramSocket socket;
	protected InetAddress adresseIpCorrespondant;
	protected int portCorrespondant;
	
	
	
	public InetAddress getAdresseIpCorrespondant() {
		return adresseIpCorrespondant;
	}



	public void setAdresseIpCorrespondant(InetAddress adresseIpCorrespondant) {
		this.adresseIpCorrespondant = adresseIpCorrespondant;
	}



	public int getPortCorrespondant() {
		return portCorrespondant;
	}



	public void setPortCorrespondant(int portCorrespondant) {
		this.portCorrespondant = portCorrespondant;
	}

	public void envoyerMessage(String mesg) throws IOException
	{
		
		
		DatagramPacket packet = new DatagramPacket(mesg.getBytes(), mesg.length(), this.adresseIpCorrespondant, getPortCorrespondant());
		this.socket.send(packet);
	}
	
	public String recevoirMessage() throws IOException
	{
		byte buffer[] = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		this.socket.receive(packet);
		System.out.println(packet.getAddress());
		
		String mesg = new String(packet.getData()).trim();
		
		String reponse= "ok";
		DatagramPacket packet2 = new DatagramPacket(reponse.getBytes(), reponse.length(), packet.getAddress(), packet.getPort());
		this.socket.send(packet2);
		
		
		return (mesg);
	}
	
	
}
