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
	
public void startServer(int port ) {
	//Erstellung Socket
	//Hoast und IP rauslessen
	try {
		addre= InetAddress.getByName(partner); //Partner Adresse besorgen
	
		System.out.println("Partner: " + addre);
		ServerSocket Ssocket = new ServerSocket (port);
		System.out.println("Server ist da. Ich bin" + InetAddress.getLocalHost());
		while (true) {
			JOptionPane.showMessageDialog(null, "-S: Waiting for client...");
			socket = Ssocket.accept();
			JOptionPane.showMessageDialog(null, "-S: Connection successfull. ");
			JOptionPane.showMessageDialog(null, socket.getInetAddress().toString());
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); //BSP. TEXT
			out.write("-S: Hallo. Ich bin der Server. Verbindung hergestellt"); //BSP TEXT
			out.flush();
			out.close();
			socket.close();
			
			//System.out.println("Connection closed");
			JOptionPane.showMessageDialog(null, "-S: Connection closed. ");
			Ssocket.close();
		}
	}
	
	catch (Exception e) {
		System.out.println(e);}
}

public void serverRun() {
	//mit accept verbinden	
	
	}
}





