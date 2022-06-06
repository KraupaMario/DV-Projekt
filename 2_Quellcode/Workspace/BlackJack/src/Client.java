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
import java.util.Vector;

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
	
	//Vector
	Vector<Spieler>player = new Vector<Spieler>();

	public void startZuAuswahl() {
		bo.buttonStartSpiel.setVisible(false);
		bo.buttonAbbrechenSpiel.setVisible(false);

		//Startbildschirm
		bo.buttonLogin.setVisible(true);
		bo.buttonRegistrieren.setVisible(true);

		//Gemeinsame
		bo.ueberschrift.setVisible(true);
		bo.buttonZurück.setVisible(false);

		//Loginfenster
		bo.labelBenutzername.setVisible(false);
		bo.labelPasswort.setVisible(false);
		bo.buttonstart.setVisible(false);
		bo.userText.setVisible(false);
		bo.passwordText.setVisible(false);


		//Registrierfenster
		bo.labelBenutzernameErstellen.setVisible(false);
		bo.labelPasswort1.setVisible(false);
		bo.labelPasswort2.setVisible(false);
		bo.buttonRegistrierenAbschließen.setVisible(false);
		bo.userRegistText.setVisible(false);
		bo.passwordText1.setVisible(false);
		bo.passwordText2.setVisible(false);

	}
	
	public void auswahlZuLogin() {
		//Startbildschirm
		bo.buttonLogin.setVisible(false);
		bo.buttonRegistrieren.setVisible(false);
		
		//Gemeinsame
		bo.ueberschrift.setVisible(true);
		bo.buttonZurück.setVisible(true);
		
		//Loginfenster
		bo.labelBenutzername.setVisible(true);
		bo.labelPasswort.setVisible(true);
		bo.buttonstart.setVisible(true);
		bo.userText.setVisible(true);
		bo.passwordText.setVisible(true);
		bo.buttonZurück.setVisible(true);
		
		//Registrierfenster
		bo.labelBenutzernameErstellen.setVisible(false);
		bo.labelPasswort1.setVisible(false);
		bo.labelPasswort2.setVisible(false);
		bo.buttonRegistrierenAbschließen.setVisible(false);
		bo.userRegistText.setVisible(false);
		bo.passwordText1.setVisible(false);
		bo.passwordText2.setVisible(false);
		
		
	}
	
	public void auswahlZuRegistrier() {
		//Startbildschirm
		bo.buttonLogin.setVisible(false);
		bo.buttonRegistrieren.setVisible(false);
		

		
		
		//Gemeinsame
		bo.ueberschrift.setVisible(true);
		bo.buttonZurück.setVisible(true);

		//Loginfenster
		bo.labelBenutzername.setVisible(false);
		bo.labelPasswort.setVisible(false);
		bo.buttonstart.setVisible(false);
		bo.userText.setVisible(false);
		bo.passwordText.setVisible(false);

		
		//Registrierfenster
		bo.labelBenutzernameErstellen.setVisible(true);
		bo.labelPasswort1.setVisible(true);
		bo.labelPasswort2.setVisible(true);
		bo.buttonRegistrierenAbschließen.setVisible(true);
		bo.userRegistText.setVisible(true);
		bo.passwordText1.setVisible(true);
		bo.passwordText2.setVisible(true);
		
	}
	
	public void logRegZuAuswahl () {
		//Startbildschirm
		bo.buttonLogin.setVisible(true);
		bo.buttonRegistrieren.setVisible(true);

		//Gemeinsame
		bo.ueberschrift.setVisible(true);
		bo.buttonZurück.setVisible(false);

		//Loginfenster
		bo.labelBenutzername.setVisible(false);
		bo.labelPasswort.setVisible(false);
		bo.buttonstart.setVisible(false);
		bo.userText.setVisible(false);
		bo.passwordText.setVisible(false);

		
		//Registrierfenster
		bo.labelBenutzernameErstellen.setVisible(false);
		bo.labelPasswort1.setVisible(false);
		bo.labelPasswort2.setVisible(false);
		bo.buttonRegistrierenAbschließen.setVisible(false);
		bo.userRegistText.setVisible(false);
		bo.passwordText1.setVisible(false);
		bo.passwordText2.setVisible(false);
	
	}
	public void loginZuIP() {
		//Startbildschirm
		bo.buttonLogin.setVisible(false);
		bo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		bo.ueberschrift.setVisible(true);
		bo.buttonZurück.setVisible(false);

		//Loginfenster
		bo.labelBenutzername.setVisible(false);
		bo.labelPasswort.setVisible(false);
		bo.buttonstart.setVisible(false);
		bo.userText.setVisible(false);
		bo.passwordText.setVisible(false);
		bo.buttonZurück.setVisible(false);

		//Registrierfenster
		bo.labelBenutzernameErstellen.setVisible(false);
		bo.labelPasswort1.setVisible(false);
		bo.labelPasswort2.setVisible(false);
		bo.buttonRegistrierenAbschließen.setVisible(false);
		bo.userRegistText.setVisible(false);
		bo.passwordText1.setVisible(false);
		bo.passwordText2.setVisible(false);
		
		//IPAdressefenster:
		bo.buttonIPAdresseBestätigen.setVisible(true);
		bo.labelipadresse.setVisible(true);
		
		
	}
	public void ipZuPort() {
		//Startbildschirm
		bo.buttonLogin.setVisible(false);
		bo.buttonRegistrieren.setVisible(false);

				//Gemeinsame
		bo.ueberschrift.setVisible(true);
		bo.buttonZurück.setVisible(false);

				//Loginfenster
		bo.labelBenutzername.setVisible(false);
		bo.labelPasswort.setVisible(false);
		bo.buttonstart.setVisible(false);
		bo.userText.setVisible(false);
		bo.passwordText.setVisible(false);
		bo.buttonZurück.setVisible(false);

				//Registrierfenster
		bo.labelBenutzernameErstellen.setVisible(false);
		bo.labelPasswort1.setVisible(false);
		bo.labelPasswort2.setVisible(false);
		bo.buttonRegistrierenAbschließen.setVisible(false);
		bo.userRegistText.setVisible(false);
		bo.passwordText1.setVisible(false);
		bo.passwordText2.setVisible(false);
				
				//IPAdressefenster:
		bo.buttonIPAdresseBestätigen.setVisible(false);
		bo.labelipadresse.setVisible(false);
		bo.ipadresseText.setVisible(false);
				
				//Portfenster: 
		bo.buttonPortBestätigen.setVisible(true);
		bo.labelport.setVisible(true);
		bo.portText.setVisible(true);
				
		
	}
	
	public void portZuEinsatz() {
		bo.buttonLogin.setVisible(false);
		bo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		bo.ueberschrift.setVisible(false);
		bo.buttonZurück.setVisible(false);

		//Loginfenster
		bo.labelBenutzername.setVisible(false);
		bo.labelPasswort.setVisible(false);
		bo.buttonstart.setVisible(false);
		bo.userText.setVisible(false);
		bo.passwordText.setVisible(false);
		bo.buttonZurück.setVisible(false);

		//Registrierfenster
		bo.labelBenutzernameErstellen.setVisible(false);
		bo.labelPasswort1.setVisible(false);
		bo.labelPasswort2.setVisible(false);
		bo.buttonRegistrierenAbschließen.setVisible(false);
		bo.userRegistText.setVisible(false);
		bo.passwordText1.setVisible(false);
		bo.passwordText2.setVisible(false);
		
		//IPAdressefenster:
		bo.buttonIPAdresseBestätigen.setVisible(false);
		bo.labelipadresse.setVisible(false);
		bo.ipadresseText.setVisible(false);
		
		//Portfenster: 
		bo.buttonPortBestätigen.setVisible(false);
		bo.labelport.setVisible(false);
		bo.portText.setVisible(false);
		
		//Spielfenster: 
		bo.buttonEinsatz.setVisible(true);
		bo.labelSpieler1.setVisible(true);
		bo.labelSpieler2.setVisible(true);
		bo.ueberschriftSpiel.setVisible(true);
		bo.labelBank.setVisible(true);
		
	}
	public void einsatzZuJetons() {
		bo.buttonLogin.setVisible(false);
		bo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		bo.ueberschrift.setVisible(false);
		bo.buttonZurück.setVisible(false);

		//Loginfenster
		bo.labelBenutzername.setVisible(false);
		bo.labelPasswort.setVisible(false);
		bo.buttonstart.setVisible(false);
		bo.userText.setVisible(false);
		bo.passwordText.setVisible(false);
		bo.buttonZurück.setVisible(false);

		//Registrierfenster
		bo.labelBenutzernameErstellen.setVisible(false);
		bo.labelPasswort1.setVisible(false);
		bo.labelPasswort2.setVisible(false);
		bo.buttonRegistrierenAbschließen.setVisible(false);
		bo.userRegistText.setVisible(false);
		bo.passwordText1.setVisible(false);
		bo.passwordText2.setVisible(false);
		
		//IPAdressefenster:
		bo.buttonIPAdresseBestätigen.setVisible(false);
		bo.labelipadresse.setVisible(false);
		bo.ipadresseText.setVisible(false);
		
		//Portfenster: 
		bo.buttonPortBestätigen.setVisible(false);
		bo.labelport.setVisible(false);
		bo.portText.setVisible(false);
		
		//Spielfenster: 
		bo.buttonEinsatz.setVisible(false);
		bo.buttonJeton10.setVisible(true);
		bo.buttonJeton25.setVisible(true);
		bo.buttonJeton50.setVisible(true);
		bo.buttonJeton100.setVisible(true);
		bo.karte1Spieler1.setVisible(true);
		bo.karte2Spieler1.setVisible(true);
		bo.karte3Spieler1.setVisible(true); 
		bo.karte4Spieler1.setVisible(true); 
		bo.karte5Spieler1.setVisible(true); 
		bo.karte1Spieler2.setVisible(true);
		bo.karte2Spieler2.setVisible(true);
		bo.karte3Spieler2.setVisible(true);
		bo.karte4Spieler2.setVisible(true);
		bo.karte5Spieler2.setVisible(true);
		bo.karte1Bank.setVisible(true);
		bo.karte2Bank.setVisible(true);
		bo.karte3Bank.setVisible(true);
		bo.karte4Bank.setVisible(true);
		bo.karte5Bank.setVisible(true);
		bo.einsatzSpieler1.setVisible(true);
		bo.einsatzSpieler2.setVisible(true);
		bo.kontostandSpieler1.setVisible(true);
		bo.kontostandSpieler2.setVisible(true); 
		//bo.buttonEinsatzbestätigen.setVisible(true);
		
	}
		public void jeton10() {
			int j10 = 10;
			String j11 = Integer.toString(j10);
			bo.einsatzausgabe.setText("Der Einsatz beträgt:" +j11);
			bo.einsatzausgabe.setVisible(true);
			
			System.out.println(j10);
		}
		public void jeton25() {
			int j25 = 25;
			String j26 = Integer.toString(j25);
			
			bo.einsatzausgabe.setText("Der Einsatz beträgt:" +j26);
			bo.einsatzausgabe.setVisible(true);
			
			System.out.println(j25);
		}
		public void  jeton50() {
			int j50 = 50;
			String j51 = Integer.toString(j50);
			bo.einsatzausgabe.setText("Der Einsatz beträgt:" +j51);
			bo.einsatzausgabe.setVisible(true);
			System.out.println(j50);
		}
		public void jeton100() {
			int j100 = 100;
			String j101 = Integer.toString(j100);
			bo.einsatzausgabe.setText("Der Einsatz beträgt:" +j101);
			bo.einsatzausgabe.setVisible(true); 
			System.out.println(j100);
			
		}
		public void einsatzAusrechnen() {
			
		}

		
		
		public void benutzerErstellen() {
			Spieler s = new Spieler(aHandler.benutzernameLogin, aHandler.password1);
			player.addElement(s);			
		}
		
	    public boolean passwordPruefen() {;
	    	 for (int i=0;i<player.size();i++) {
	    		 if (Spieler.passwort.equals(aHandler.password)==true) {
	    			 return true;
	    		 }
	    		 
	    	 } return false;
	    }

}


	


