import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Server implements Runnable{
	private String ip = "localhost";
	private int port = 22222;
	private Scanner scanner = new Scanner(System.in);
	
	private Thread thread;


	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;

	private ServerSocket serverSocket;



	private String[] spaces = new String[9];

	private boolean yourTurn = false; //bin ich an der Reihe?
	private boolean client = true;	// Bin ich der Client?
	private boolean host = false; // Bin ich der Host?
	private boolean accepted = false;	//Bin ich schon mit einem Server verbunden?
	private boolean unableToCommunicateWithOpponent = false; // Verbindung abgebrochen?
	private boolean won = false; //ich habe den Spielzug gewonnen
	private boolean dealerWon = false; // der Dealer hat gewonnen
	
	private int errors = 0;



	private String waitingString = "Warten auf Mitspieler...";
	private String unableToCommunicateWithOpponentString = "Kommunikation mit dem Spieler nicht möglich.";
	private String wonString = "Du hast gewonnen!";
	private String enemyWonString = "Dealer gewinnt!";
	private String tieString = "Unentschieden!";
	
	private int gesetztS;
	private int gesetztC;
	


	public Server() {
		System.out.println("Bitte gib deine IP an: "); 
		ip = JOptionPane.showInputDialog("IP Adresse");
		System.out.println("Bitte gib einen Port an: "); // Port festlegen 8080?
		port = Integer.parseInt(JOptionPane.showInputDialog("Port?"));
		while (port < 1 || port > 65535) {
			System.out.println("Dein Port war ungültig, bitte gib einen neuen ein: ");
			port = Integer.parseInt(JOptionPane.showInputDialog("Port?"));
		}

		if (!verbunden())
			initialisiereServer();
		
		thread = new Thread(this, "BlackJack");
		thread.start();
	}
	
	public void run() {
		while (true) {
			
			//Programmcode welcher im "Thread" ausgeführt wird.
			aktion();
			if (!host && !accepted) {
				wartenAufServer();
			}

		}
	}

	private void wartenAufServer() {
		Socket socket = null;
		try {
			socket = serverSocket.accept();
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			accepted = true;
			System.out.println("CLIENT HAS REQUESTED TO JOIN, AND WE HAVE ACCEPTED");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean verbunden() {
		try {
			socket = new Socket(ip, port);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			accepted = true;
		} catch (IOException e) {
			System.out.println("Meine IP Adresse und Port: " + ip + " : " + port + " | Eröffne einen Server.");
			return false;
		}
		System.out.println("Erfolgreich mit dem Server verbunden.");
		return true;
	}

	private void initialisiereServer() {
		try {
			serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));

		} catch (Exception e) {
			e.printStackTrace();
		}
		yourTurn = true;
		host = true;
		client = false;
	}
	// a = 300; //GUI
	// Klick auf "setzen": bool: gesetzt = true
	
	private void aktion()  {
		//Spiel erstellen
		Spiel server = new Spiel();
		server.createDeck();
		//Spieler erstellen
		Spieler playerS = new Spieler("playerS", "1234");
		//Geld setzen:
		while(ActionHandler2.gesetzt = false) {
			
		}
		gesetztS = ActionHandler2.a;
		
		gesetztS = playerS.geldsetzen();
		server.gesetztSpieler1 = gesetztS;
		//gesetzter Betrag übermitteln.
		try {
			dos.writeInt(gesetztS);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Gesetzter Betrag vom Client empfangen:
		try {
			gesetztC = dis.readInt();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Karte für Spieler 1 ziehen:
		server.DeckSpieler1.add(server.getKarte());
		//Karte für Spieler 2 ziehen:
		server.DeckSpieler2.add(server.getKarte());
		//Karten für Dealer ziehen:
		server.DeckDealer.add(server.getKarte());
		//2. Karte:
		server.DeckSpieler1.add(server.getKarte());
		server.DeckSpieler2.add(server.getKarte());
		server.DeckDealer.add(server.getKarte());
		
		//Gezogene Karten an Client übermitteln:
		String card = server.DeckSpieler1.get(0).getFarbe()+server.DeckSpieler1.get(0).getName() ;
		try {
			dos.writeUTF(card);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		card = server.DeckSpieler1.get(1).getFarbe()+server.DeckSpieler1.get(1).getName() ;
		try {
			dos.writeUTF(card);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//--
		card = server.DeckSpieler2.get(0).getFarbe()+server.DeckSpieler2.get(0).getName() ;
		try {
			dos.writeUTF(card);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		card = server.DeckSpieler2.get(1).getFarbe()+server.DeckSpieler2.get(1).getName() ;
		try {
			dos.writeUTF(card);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//--
		card = server.DeckDealer.get(0).getFarbe()+server.DeckDealer.get(0).getName() ;
		try {
			dos.writeUTF(card);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		card = server.DeckDealer.get(1).getFarbe()+server.DeckDealer.get(1).getName() ;
		try {
			dos.writeUTF(card);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		///---
		
		server.wertSpieler1 = server.wertSpieler1();
		server.wertSpieler2 = server.wertSpieler2();
		try {
			dos.writeInt(server.wertSpieler2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server.wertDealer = server.wertDealer();
		
		server.checkBJSpieler1();
		server.checkBJSpieler2();
		server.checkBJDealer();

		if (server.winSpieler1)
			System.out.println("Spieler 1 hat gewonnen");	//Grafische Ausgabe
		playerS.addMoney(gesetztS*2);
		if (server.loseSpieler1)
			System.out.println("Spieler 1 hat verloren");	//Grafische Ausgabe
		if (server.winDealer)
			System.out.println("Spieler 1 hat verloren");	//Grafische Ausgabe
		gesetztS = 0;
		server.gesetztSpieler1 = 0;
		
		//Ergebnisse vom Check des Client an Client schicken:
		try {
			dos.writeBoolean(server.winSpieler2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dos.writeBoolean(server.loseSpieler2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		
		

		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server neu = new Server();

	}

}


	


