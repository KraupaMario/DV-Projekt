package Testumgebung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String ip = JOptionPane.showInputDialog("IP");
		int port = Integer.parseInt(JOptionPane.showInputDialog("Port"));
		try {
			Socket connection = new Socket(ip,port);
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = new String();
			while((line = in.readLine()) != null){
				System.out.println(line+"TEst");
				JOptionPane.showMessageDialog(null, line);
			}
			//System.out.println("Closing down connection...");
			JOptionPane.showMessageDialog(null, "-C: Connection closing. ");
			in.close();
			connection.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}