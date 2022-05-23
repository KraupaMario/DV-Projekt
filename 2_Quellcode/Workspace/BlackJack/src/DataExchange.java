import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.*;
import javax.swing.*;



public class DataExchange implements Serializable {
	InetAddress addre;
	String partner = JOptionPane.showInputDialog(null, "Wer ist Server ?");
	Socket socket = null;
	public static void main(String[] args) { 
		
		
		
	}
	
public void startServer() {
	//Erstellung Socket
	//Hoast und IP rauslessen
	try {
		addre= InetAddress.getByName(partner); //Partner Adresse besorgen
		System.out.println("Ich bin" + InetAddress.getLocalHost());
		System.out.println("Partner: " + addre);
		socket = new Socket(addre, 6000);
		System.out.println("socket = " + socket);
	}
	
	catch (Exception e) {
		System.out.println(e);}
}

public void serverRun() {
	//mit accept verbinden	
	
	}
}

