package reseau;

import java.io.IOException;
import java.net.DatagramSocket;

public class ServeurUDP extends ClientServeurUDP implements Runnable{
	private String msgIn;
	private Thread proc;
	
	public ServeurUDP(int portEcoute) throws IOException
	{
		this.portCorrespondant = portEcoute;
		this.socket = new DatagramSocket(this.portCorrespondant);
		this.proc = new Thread(this);
	}
	
	public String getMsgIn()
	{
		return this.msgIn;
	}
	
	public Thread getProc(){
		return this.proc;
	}
	
	public void run(){
		while(true){
			try{
				msgIn=recevoirMessage();
				System.out.println(msgIn+"\n");
			}
			catch (IOException e){
				System.out.println("Erreur");
			}
		}
	}
}
