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


public class Client implements Runnable{
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
	


	
	



	public Client() {
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
	
	private void aktion()  {
		//Spiel erstellen
		Spiel client = new Spiel();
		
		//Spieler erstellen
		Spieler playerC = new Spieler("playerC", "1234");
		
		//Gesetzter Betrag vom Server empfangen:
		try {
			gesetztS = dis.readInt();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		//Geld setzen:
		gesetztC = playerC.geldsetzen();
		//gesetzter Betrag übermitteln.
		try {
			dos.writeInt(gesetztC);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//ausgeteilte Karten Spieler 1:
		try {
			System.out.println(dis.readUTF());
			//Switch Case
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(dis.readUTF());
			//Switch Case
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ausgeteilte Karten Spieler 2:
		try {
			System.out.println(dis.readUTF());
			//Switch Case
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(dis.readUTF());
			//Switch Case
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ausgeteilte Karten Dealer:
		try {
			System.out.println(dis.readUTF());
			//Switch Case
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(dis.readUTF());
			//Switch Case
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Wert der Hand von Spieler 2 (kommt vom Server als int)
		try {
			System.out.println(dis.readInt());
			//zum Darstellen im GUI
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Ergebnisse des Checks:
				try {
					System.out.println(dis.readBoolean());
					//win?
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					System.out.println(dis.readBoolean());
					//lose?
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		
		
		
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client neu = new Client();

	}
	
	
	//Grafische Programmierung
	
	
	ActionHandler aHandler = new ActionHandler(this); 
	Benutzeroberfläche bo = new Benutzeroberfläche(this);
	
	public void auswahlZuLogin() {
		//Startbildschirm
		Benutzeroberfläche.buttonLogin.setVisible(false);
		Benutzeroberfläche.buttonRegistrieren.setVisible(false);
		
		//Gemeinsame
		Benutzeroberfläche.ueberschrift.setVisible(true);
		Benutzeroberfläche.buttonZurück.setVisible(true);
		
		//Loginfenster
		Benutzeroberfläche.labelBenutzername.setVisible(true);
		Benutzeroberfläche.labelPasswort.setVisible(true);
		Benutzeroberfläche.buttonstart.setVisible(true);
		Benutzeroberfläche.userText.setVisible(true);
		Benutzeroberfläche.passwordText.setVisible(true);
		Benutzeroberfläche.buttonZurück.setVisible(true);
		
		//Registrierfenster
		Benutzeroberfläche.labelBenutzernameErstellen.setVisible(false);
		Benutzeroberfläche.labelPasswort1.setVisible(false);
		Benutzeroberfläche.labelPasswort2.setVisible(false);
		Benutzeroberfläche.buttonRegistrierenAbschließen.setVisible(false);
		Benutzeroberfläche.userRegistText.setVisible(false);
		Benutzeroberfläche.passwordText1.setVisible(false);
		Benutzeroberfläche.passwordText2.setVisible(false);
		
		
	}
	
	public void auswahlZuRegistrier() {
		//Startbildschirm
		Benutzeroberfläche.buttonLogin.setVisible(false);
		Benutzeroberfläche.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		Benutzeroberfläche.ueberschrift.setVisible(true);
		Benutzeroberfläche.buttonZurück.setVisible(true);

		//Loginfenster
		Benutzeroberfläche.labelBenutzername.setVisible(false);
		Benutzeroberfläche.labelPasswort.setVisible(false);
		Benutzeroberfläche.buttonstart.setVisible(false);
		Benutzeroberfläche.userText.setVisible(false);
		Benutzeroberfläche.passwordText.setVisible(false);

		
		//Registrierfenster
		Benutzeroberfläche.labelBenutzernameErstellen.setVisible(true);
		Benutzeroberfläche.labelPasswort1.setVisible(true);
		Benutzeroberfläche.labelPasswort2.setVisible(true);
		Benutzeroberfläche.buttonRegistrierenAbschließen.setVisible(true);
		Benutzeroberfläche.userRegistText.setVisible(true);
		Benutzeroberfläche.passwordText1.setVisible(true);
		Benutzeroberfläche.passwordText2.setVisible(true);
	}
	
	public void startbildschirm () {
		//Startbildschirm
		Benutzeroberfläche.buttonLogin.setVisible(true);
		Benutzeroberfläche.buttonRegistrieren.setVisible(true);

		//Gemeinsame
		Benutzeroberfläche.ueberschrift.setVisible(false);
		Benutzeroberfläche.buttonZurück.setVisible(false);

		//Loginfenster
		Benutzeroberfläche.labelBenutzername.setVisible(false);
		Benutzeroberfläche.labelPasswort.setVisible(false);
		Benutzeroberfläche.buttonstart.setVisible(false);
		Benutzeroberfläche.userText.setVisible(false);
		Benutzeroberfläche.passwordText.setVisible(false);

		
		//Registrierfenster
		Benutzeroberfläche.labelBenutzernameErstellen.setVisible(false);
		Benutzeroberfläche.labelPasswort1.setVisible(false);
		Benutzeroberfläche.labelPasswort2.setVisible(false);
		Benutzeroberfläche.buttonRegistrierenAbschließen.setVisible(false);
		Benutzeroberfläche.userRegistText.setVisible(false);
		Benutzeroberfläche.passwordText1.setVisible(false);
		Benutzeroberfläche.passwordText2.setVisible(false);
	
	}
	
	
	

}


	


