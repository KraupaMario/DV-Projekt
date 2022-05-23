package Testumgebung;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;



import javax.swing.JOptionPane;

public class Server {
	
	String m = "Test";

	private Socket socket;

	public Server(int port) {
		try {
			ServerSocket sSocket = new ServerSocket(port);
			//ServerSocket sSocket = new ServerSocket(port, 8, InetAddress.getByName("192.168.178.52"));
			System.out.println("Server is ready. Ich bin " + InetAddress.getLocalHost());
			while (true) {
				//System.out.println("Waiting for client...");
				JOptionPane.showMessageDialog(null, "-S: Waiting for client...");
				socket = sSocket.accept();
				//System.out.println("Connection established. ");
				JOptionPane.showMessageDialog(null, "-S: Connection established. ");
				
				//System.out.println(socket.getInetAddress().toString());
				JOptionPane.showMessageDialog(null, socket.getInetAddress().toString());
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				out.write("-S: Hallo. Ich bin der Server. Verbindung hergestellt");
				out.flush();
				out.close();
				socket.close();
				
				//System.out.println("Connection closed");
				JOptionPane.showMessageDialog(null, "-S: Connection closed. ");
				sSocket.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Server serv = new Server(80);
	}

}