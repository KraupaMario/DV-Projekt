package Game;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

//import jdk.internal.misc.FileSystemOption;



public class Client2 implements Runnable {

	private String ip = "localhost";
	private int port = 22222;
	private Scanner scanner = new Scanner(System.in);

	private Thread thread;
	
	private boolean close = false; //Schließt wenn true


	private Socket socket;
	private DataOutputStream dos;
	private ObjectOutputStream oos;
	private ByteArrayOutputStream baos;
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
	int gesetztS; 
	int gesetztC;
	static boolean klicks = false; 
	public static int swischespeicher;
	
	



	private int errors = 0;



	private String waitingString = "Warten auf Mitspieler...";
	private String unableToCommunicateWithOpponentString = "Kommunikation mit dem Spieler nicht möglich.";
	private String wonString = "Du hast gewonnen!";
	private String enemyWonString = "Dealer gewinnt!";
	private String tieString = "Unentschieden!";

	public Client2() {
		System.out.println("Bitte gib deine IP an: ");
		while (!klicks) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ip = cHandler.ipAdresse;
		System.out.println("Die IP ist: " + ip);
		port = 8080;
		
		while (port < 1 || port > 65535) {
			System.out.println("Dein Port war ungültig, bitte gib einen neuen ein: ");
			port = Integer.parseInt(JOptionPane.showInputDialog("Port?"));
		}

		if (!verbunden())
			initialisiereServer();

		System.out.println("ok1");

		thread = new Thread(this, "BlackJack");

		System.out.println("ok2");
		thread.start();

		System.out.println("ok3");
	}

	public void run() {
		while (true) {

			//Programmcode welcher im "Thread" ausgeführt wird.

			if (!client && !accepted) {
				System.out.println("Jetzt!");
				wartenAufServer();
				System.out.println("JEtzt!");
			}

			aktion();
			
			if (close) {
				break;
			}

		}
	}

	private void wartenAufServer() {
		Socket socket = null;
		try {
			System.out.println("Jetzt2y");
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
		Spiel server = new Spiel();
		server.createDeck();
		//Spieler erstellen
		Spieler playerS = new Spieler("playerS", "1234");
		//Gesetzter Betrag vom Client empfangen:
		try {
			gesetztS = dis.readInt();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Bis hier her Wartebildschirm
		System.out.println("c/Server setzt: "+gesetztS);
		
		while (!klicks) {
			System.out.println("Warten");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Mein gesetzter Betrag"+gesetztS);
		//Betrag übermitteln
		try {
			dos.writeInt(gesetztC);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thread.stop();
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client2 neu = new Client2();

	}
	//Grafische Programmierung
	
	
	ActionHandlerClient cHandler = new ActionHandlerClient(this); 
	BenutzeroberflächeClient cbo = new BenutzeroberflächeClient(this);
	
	//Vector
	Vector<Spieler>player = new Vector<Spieler>();

	public void startZuAuswahl() {
		cbo.buttonStartSpielC.setVisible(false);
		cbo.buttonAbbrechenSpiel.setVisible(false);

		//Startbildschirm
		cbo.buttonLogin.setVisible(true);
		cbo.buttonRegistrieren.setVisible(true);

		//Gemeinsame
		cbo.ueberschriftC.setVisible(true);
		cbo.buttonZurück.setVisible(false);

		//Loginfenster
		cbo.labelBenutzernameC.setVisible(false);
		cbo.labelPasswortC.setVisible(false);
		cbo.buttonstart.setVisible(false);
		cbo.userText.setVisible(false);
		cbo.passwordText.setVisible(false);


		//Registrierfenster
		cbo.labelBenutzernameCErstellenC.setVisible(false);
		cbo.labelPasswortC1C.setVisible(false);
		cbo.labelPasswortC2C.setVisible(false);
		cbo.buttonRegistrierenAbschließen.setVisible(false);
		cbo.userRegistText.setVisible(false);
		cbo.passwordText1.setVisible(false);
		cbo.passwordText2.setVisible(false);

	}
	
	public void auswahlZuLogin() {
		//Startbildschirm
		cbo.buttonLogin.setVisible(false);
		cbo.buttonRegistrieren.setVisible(false);
		
		//Gemeinsame
		cbo.ueberschriftC.setVisible(true);
		cbo.buttonZurück.setVisible(true);
		
		//Loginfenster
		cbo.labelBenutzernameC.setVisible(true);
		cbo.labelPasswortC.setVisible(true);
		cbo.buttonstart.setVisible(true);
		cbo.userText.setVisible(true);
		cbo.passwordText.setVisible(true);
		cbo.buttonZurück.setVisible(true);
		
		//Registrierfenster
		cbo.labelBenutzernameCErstellenC.setVisible(false);
		cbo.labelPasswortC1C.setVisible(false);
		cbo.labelPasswortC2C.setVisible(false);
		cbo.buttonRegistrierenAbschließen.setVisible(false);
		cbo.userRegistText.setVisible(false);
		cbo.passwordText1.setVisible(false);
		cbo.passwordText2.setVisible(false);
		
		
	}
	
	public void auswahlZuRegistrier() {
		//Startbildschirm
		cbo.buttonLogin.setVisible(false);
		cbo.buttonRegistrieren.setVisible(false);
		

		
		
		//Gemeinsame
		cbo.ueberschriftC.setVisible(true);
		cbo.buttonZurück.setVisible(true);

		//Loginfenster
		cbo.labelBenutzernameC.setVisible(false);
		cbo.labelPasswortC.setVisible(false);
		cbo.buttonstart.setVisible(false);
		cbo.userText.setVisible(false);
		cbo.passwordText.setVisible(false);

		
		//Registrierfenster
		cbo.labelBenutzernameCErstellenC.setVisible(true);
		cbo.labelPasswortC1C.setVisible(true);
		cbo.labelPasswortC2C.setVisible(true);
		cbo.buttonRegistrierenAbschließen.setVisible(true);
		cbo.userRegistText.setVisible(true);
		cbo.passwordText1.setVisible(true);
		cbo.passwordText2.setVisible(true);
		
	}
	
	public void logRegZuAuswahl () {
		//Startbildschirm
		cbo.buttonLogin.setVisible(true);
		cbo.buttonRegistrieren.setVisible(true);

		//Gemeinsame
		cbo.ueberschriftC.setVisible(true);
		cbo.buttonZurück.setVisible(false);

		//Loginfenster
		cbo.labelBenutzernameC.setVisible(false);
		cbo.labelPasswortC.setVisible(false);
		cbo.buttonstart.setVisible(false);
		cbo.userText.setVisible(false);
		cbo.passwordText.setVisible(false);

		
		//Registrierfenster
		cbo.labelBenutzernameCErstellenC.setVisible(false);
		cbo.labelPasswortC1C.setVisible(false);
		cbo.labelPasswortC2C.setVisible(false);
		cbo.buttonRegistrierenAbschließen.setVisible(false);
		cbo.userRegistText.setVisible(false);
		cbo.passwordText1.setVisible(false);
		cbo.passwordText2.setVisible(false);
	
	}
	public void loginZuIP() {
		//Startbildschirm
		cbo.buttonLogin.setVisible(false);
		cbo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		cbo.ueberschriftC.setVisible(true);
		cbo.buttonZurück.setVisible(false);

		//Loginfenster
		cbo.labelBenutzernameC.setVisible(false);
		cbo.labelPasswortC.setVisible(false);
		cbo.buttonstart.setVisible(false);
		cbo.userText.setVisible(false);
		cbo.passwordText.setVisible(false);
		cbo.buttonZurück.setVisible(false);

		//Registrierfenster
		cbo.labelBenutzernameCErstellenC.setVisible(false);
		cbo.labelPasswortC1C.setVisible(false);
		cbo.labelPasswortC2C.setVisible(false);
		cbo.buttonRegistrierenAbschließen.setVisible(false);
		cbo.userRegistText.setVisible(false);
		cbo.passwordText1.setVisible(false);
		cbo.passwordText2.setVisible(false);
		
		//IPAdressefenster:
		cbo.buttonIPAdresseBestätigen.setVisible(true);
		cbo.labelipadresseC.setVisible(true);
		cbo.ipadresseText.setVisible(true);
		
		
		
		//System.out.println(j10);
		
		
	}
	public void ipZuPort() {
		//Startbildschirm
		cbo.buttonLogin.setVisible(false);
		cbo.buttonRegistrieren.setVisible(false);

				//Gemeinsame
		cbo.ueberschriftC.setVisible(true);
		cbo.buttonZurück.setVisible(false);

				//Loginfenster
		cbo.labelBenutzernameC.setVisible(false);
		cbo.labelPasswortC.setVisible(false);
		cbo.buttonstart.setVisible(false);
		cbo.userText.setVisible(false);
		cbo.passwordText.setVisible(false);
		cbo.buttonZurück.setVisible(false);

				//Registrierfenster
		cbo.labelBenutzernameCErstellenC.setVisible(false);
		cbo.labelPasswortC1C.setVisible(false);
		cbo.labelPasswortC2C.setVisible(false);
		cbo.buttonRegistrierenAbschließen.setVisible(false);
		cbo.userRegistText.setVisible(false);
		cbo.passwordText1.setVisible(false);
		cbo.passwordText2.setVisible(false);
				
				//IPAdressefenster:
		cbo.buttonIPAdresseBestätigen.setVisible(false);
		cbo.labelipadresseC.setVisible(false);
		cbo.ipadresseText.setVisible(false);
				
				//Portfenster: 
		cbo.buttonPortBestätigen.setVisible(true);
		cbo.labelportC.setVisible(true);
		cbo.portText.setVisible(true);
				
		
	}
	
	public void portZuEinsatz() {
		cbo.buttonLogin.setVisible(false);
		cbo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		cbo.ueberschriftC.setVisible(false);
		cbo.buttonZurück.setVisible(false);

		//Loginfenster
		cbo.labelBenutzernameC.setVisible(false);
		cbo.labelPasswortC.setVisible(false);
		cbo.buttonstart.setVisible(false);
		cbo.userText.setVisible(false);
		cbo.passwordText.setVisible(false);
		cbo.buttonZurück.setVisible(false);

		//Registrierfenster
		cbo.labelBenutzernameCErstellenC.setVisible(false);
		cbo.labelPasswortC1C.setVisible(false);
		cbo.labelPasswortC2C.setVisible(false);
		cbo.buttonRegistrierenAbschließen.setVisible(false);
		cbo.userRegistText.setVisible(false);
		cbo.passwordText1.setVisible(false);
		cbo.passwordText2.setVisible(false);
		
		//IPAdressefenster:
		cbo.buttonIPAdresseBestätigen.setVisible(false);
		cbo.labelipadresseC.setVisible(false);
		cbo.ipadresseText.setVisible(false);
		
		//Portfenster: 
		cbo.buttonPortBestätigen.setVisible(false);
		cbo.labelportC.setVisible(false);
		cbo.portText.setVisible(false);
		
		//Spielfenster: 
		cbo.buttonEinsatz.setVisible(true);
		cbo.labelSpieler1C.setVisible(true);
		cbo.labelSpieler2C.setVisible(true);
		cbo.ueberschriftCSpielC.setVisible(true);
		cbo.labelBankC.setVisible(true);
		
	}
	public void einsatzZuJetons() {
		cbo.buttonLogin.setVisible(false);
		cbo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		cbo.ueberschriftC.setVisible(false);
		cbo.buttonZurück.setVisible(false);

		//Loginfenster
		cbo.labelBenutzernameC.setVisible(false);
		cbo.labelPasswortC.setVisible(false);
		cbo.buttonstart.setVisible(false);
		cbo.userText.setVisible(false);
		cbo.passwordText.setVisible(false);
		cbo.buttonZurück.setVisible(false);

		//Registrierfenster
		cbo.labelBenutzernameCErstellenC.setVisible(false);
		cbo.labelPasswortC1C.setVisible(false);
		cbo.labelPasswortC2C.setVisible(false);
		cbo.buttonRegistrierenAbschließen.setVisible(false);
		cbo.userRegistText.setVisible(false);
		cbo.passwordText1.setVisible(false);
		cbo.passwordText2.setVisible(false);
		
		//IPAdressefenster:
		cbo.buttonIPAdresseBestätigen.setVisible(false);
		cbo.labelipadresseC.setVisible(false);
		cbo.ipadresseText.setVisible(false);
		
		//Portfenster: 
		cbo.buttonPortBestätigen.setVisible(false);
		cbo.labelportC.setVisible(false);
		cbo.portText.setVisible(false);
		
		//Spielfenster: 
		cbo.buttonEinsatz.setVisible(false);
		cbo.buttonJeton10.setVisible(true);
		cbo.buttonJeton25.setVisible(true);
		cbo.buttonJeton50.setVisible(true);
		cbo.buttonJeton100.setVisible(true);
		cbo.karte1Spieler1.setVisible(true);
		cbo.karte2Spieler1.setVisible(true);
		cbo.karte3Spieler1.setVisible(true); 
		cbo.karte4Spieler1.setVisible(true); 
		cbo.karte5Spieler1.setVisible(true); 
		cbo.karte1Spieler2.setVisible(true);
		cbo.karte2Spieler2.setVisible(true);
		cbo.karte3Spieler2.setVisible(true);
		cbo.karte4Spieler2.setVisible(true);
		cbo.karte5Spieler2.setVisible(true);
		cbo.karte1Bank.setVisible(true);
		cbo.karte2Bank.setVisible(true);
		cbo.karte3Bank.setVisible(true);
		cbo.karte4Bank.setVisible(true);
		cbo.karte5Bank.setVisible(true);
		cbo.einsatzSpieler1C.setVisible(true);
		cbo.einsatzSpieler2C.setVisible(true);
		cbo.kontostandSpieler1C.setVisible(true);
		cbo.kontostandSpieler2.setVisible(true); 
		cbo.buttonEinsatzbestätigen.setVisible(true);
		
	}
		public void jeton10() {
			int j10 = 10;
			
			String j11 = Integer.toString(j10);
			cbo.einsatzausgabe.setText("Der Einsatz beträgt:" +swischespeicher);
			cbo.einsatzausgabe.setVisible(true);
			
			System.out.println(j10);
			System.out.println("Immo:"+swischespeicher);
		}
		public void jeton25() {
			int j25 = 25;
			String j26 = Integer.toString(j25);
			
			cbo.einsatzausgabe.setText("Der Einsatz beträgt:" +swischespeicher);
			cbo.einsatzausgabe.setVisible(true);
			
			System.out.println(j25);
			System.out.println("Immo:"+swischespeicher);
		}
		public void  jeton50() {
			int j50 = 50;
			String j51 = Integer.toString(j50);
			cbo.einsatzausgabe.setText("Der Einsatz beträgt:" +swischespeicher);
			cbo.einsatzausgabe.setVisible(true);
			System.out.println(j50);
			System.out.println("Immo:"+swischespeicher);
		}
		public void jeton100() {
			int j100 = 100;
			String j101 = Integer.toString(j100);
			cbo.einsatzausgabe.setText("Der Einsatz beträgt:" +swischespeicher);
			cbo.einsatzausgabe.setVisible(true); 
			System.out.println(j100);
			System.out.println("Immo:"+swischespeicher);
			
		}
		public void einsatzAusrechnen() {
			//swischespeicher = gesetztS; <---- Da liegt der Mist!
			gesetztS = swischespeicher;
			swischespeicher = 0;
		}

		
		
		public void benutzerErstellen() {
			//Spieler s = new Spieler(aHandler.benutzernameLogin, aHandler.password1);
			//player.addElement(s);			
		}
		/*
	    public boolean passwordPruefen() {;
	    	 for (int i=0;i<player.size();i++) {
	    		 if (Spieler.passwort.equals(aHandler.password)==true) {
	    			 return true;
	    		 }
	    		 
	    	 } return false;
	    }
*/
}


	







