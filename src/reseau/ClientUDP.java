package reseau;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.StringTokenizer;

public class ClientUDP extends ClientServeurUDP{
	public ClientUDP (String adresseDest, int portDest) throws IOException
	{
		byte[] addr = new byte[4];
		int i=0;
		StringTokenizer tk = new StringTokenizer(adresseDest, ".");
		
		while(tk.hasMoreTokens()){
			addr[i] = (byte)Integer.parseInt(tk.nextToken());
			i++;
		}
		this.adresseIpCorrespondant = InetAddress.getByAddress(addr);
		this.portCorrespondant = portDest;
		this.socket = new DatagramSocket();
	}
	
	public String getHostName(){
		return adresseIpCorrespondant.getCanonicalHostName();
	}
}
