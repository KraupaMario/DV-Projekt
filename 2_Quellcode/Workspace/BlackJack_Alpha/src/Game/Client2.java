package Game;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
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
	private ObjectInputStream ois;
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
	int kontomax = 0;
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
		klicks = false;
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
		 Spiel client = new Spiel();
		client.createDeck();
		//Spieler erstellen
		Spieler playerS = new Spieler("playerS", "1234");
		//Gesetzter Betrag vom Client empfangen:
		try {
			gesetztS = dis.readInt();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Spiel.setGesetztSpieler1(gesetztS);
		gesetztS = 0;
		//Bis hier her Wartebildschirm
		System.out.println("c/Server setzt: "+Spiel.getGesetztSpieler1());
		
		while (!klicks) {
			System.out.println("Warten");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		klicks= false;
		System.out.println("Mein gesetzter Betrag "+gesetztC);
		//Betrag übermitteln
		try {
			dos.writeInt(gesetztC);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Spiel.setGesetztSpieler2(gesetztC);
		gesetztC = 0;
		/*ausgeteilte Karten Spieler 1:
				try {
					System.out.println(dis.readUTF());
					//Switch Case
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
		/*
		try {
			client.DeckSpieler1 = (ArrayList<Karten>) ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		client.DeckSpieler1.clear();
		client.DeckSpieler2.clear();
		client.DeckDealer.clear();
		
		//Karte für Spieler 1 empfangen:
		client.DeckSpieler1.add(karteEmpfangen());
		client.DeckSpieler1.add(karteEmpfangen()); 	//2. Karte:
		//Karte für Spieler 2 empfangen:
		client.DeckSpieler2.add(karteEmpfangen());
		client.DeckSpieler2.add(karteEmpfangen()); 	//2. Karte:
		//Karten für Dealer empfangen:
		client.DeckDealer.add(karteEmpfangen());
		client.DeckDealer.add(karteEmpfangen()); 	//2. Karte:
	
		
		
		
		
	
		
		
		System.out.println(client.DeckSpieler1.get(0).getFarbe());
		System.out.println(client.DeckSpieler1.get(0).getName());
		System.out.println(client.DeckSpieler1.get(1).getFarbe());
		System.out.println(client.DeckSpieler1.get(1).getName());
		kartenausgebenS_R1(client);//Karten anzeigen
		kartenwertanzeigen(client);
		
		thread.stop();
	}
	
	Karten karteEmpfangen() {
		String f = null;
		try {
			f = dis.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    int name=0;
		try {
			name = dis.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int wert=0;
		try {
			wert = dis.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Karten k = new Karten(wert,f,name);
		return k;
	}

boolean abbuchungOK(int m){
		
		if ((swischespeicher)>kontomax) {
			swischespeicher -= m;
			return false; }
		else 
			return true;
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
	public void ipZuEinsatz() {
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
		cbo.bedienfeld.setVisible(true);
		
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
		//cbo.kontostandSpieler1C.setVisible(true);
		cbo.kontostandSpieler2.setVisible(true); 
		cbo.buttonEinsatzbestätigen.setVisible(true);
		
		
		cbo.kartenwertSpieler1.setVisible(true);
		cbo.kartenwertSpieler2.setVisible(true);
		cbo.kartenwertDealer.setVisible(true);
		
	}
	
	
public void kartenwertanzeigen(Spiel s) {
		
		cbo.kartenwertSpieler1.setText("Kartenwert Spieler 1: "+Integer.toString(s.wertSpieler1()));
		cbo.kartenwertSpieler2.setText("Kartenwert Spieler 2: "+Integer.toString(s.wertSpieler2()));
		cbo.kartenwertDealer.setText("Kartenwert Spieler 2: "+Integer.toString(s.wertDealer()));
	
	}
	
		public void jeton10() {
			int j10 = 10;
			
			String j11 = Integer.toString(j10);
			cbo.einsatzausgabeSpieler2C.setText("Der Einsatz beträgt:" +swischespeicher);
			cbo.einsatzausgabeSpieler2C.setVisible(true);
			
			System.out.println(j10);
			System.out.println("Immo:"+swischespeicher);
		}
		public void jeton25() {
			int j25 = 25;
			String j26 = Integer.toString(j25);
			
			cbo.einsatzausgabeSpieler2C.setText("Der Einsatz beträgt:" +swischespeicher);
			cbo.einsatzausgabeSpieler2C.setVisible(true);
			
			System.out.println(j25);
			System.out.println("Immo:"+swischespeicher);
		}
		public void  jeton50() {
			int j50 = 50;
			String j51 = Integer.toString(j50);
			cbo.einsatzausgabeSpieler2C.setText("Der Einsatz beträgt:" +swischespeicher);
			cbo.einsatzausgabeSpieler2C.setVisible(true);
			System.out.println(j50);
			System.out.println("Immo:"+swischespeicher);
		}
		public void jeton100() {
			int j100 = 100;
			String j101 = Integer.toString(j100);
			cbo.einsatzausgabeSpieler2C.setText("Der Einsatz beträgt:" +swischespeicher);
			cbo.einsatzausgabeSpieler2C.setVisible(true); 
			System.out.println(j100);
			System.out.println("Immo:"+swischespeicher);
			
		}
		public void einsatzAusrechnen() {
			//swischespeicher = gesetztS; <---- Da liegt der Mist!
			gesetztC = swischespeicher; 
			swischespeicher = 0;
			einsatzAnzeigenGegenspieler(); 
		}
		
		public void einsatzAnzeigenGegenspieler() {
			cbo.einsatzausgabeSpieler1C.setVisible(true);
			cbo.einsatzausgabeSpieler1C.setText("Der Einsatz beträgt:" + Spiel.getGesetztSpieler1());
			}
		
		
		public void kartenausgebenS_R1(Spiel s){
			
			//String farbek11 = s.DeckSpieler1.get(0).getFarbe();
			String farbek11 = s.DeckSpieler1.get(0).getFarbe();
			String farbek21 = s.DeckSpieler1.get(1).getFarbe();
			String farbek12 = s.DeckSpieler2.get(0).getFarbe();
			String farbek22 = s.DeckSpieler2.get(1).getFarbe();
			String farbebank1= s.DeckDealer.get(0).getFarbe();
			String farbebank2= s.DeckDealer.get(1).getFarbe();
			
			//int nummerk11 = s.DeckSpieler1.get(0).getName();
			int nummerk11 = s.DeckSpieler1.get(0).getName();
			int nummerk21 = s.DeckSpieler1.get(1).getName();
			int nummerk12 = s.DeckSpieler2.get(0).getName();
			int nummerk22 = s.DeckSpieler2.get(1).getName();
			int nummerk1b = s.DeckDealer.get(0).getName();
			int nummerk2b = s.DeckDealer.get(1).getName();
			//Karte1 Spieler1
			switch (farbek11) {
			case "pik":
				cbo.karte1Spieler1.setIcon(cbo.pik[nummerk11]);
				break;
			case "herz":
				cbo.karte1Spieler1.setIcon(cbo.herz[nummerk11]);
				break;
			case "kreuz":
				cbo.karte1Spieler1.setIcon(cbo.kreuz[nummerk11]);
				break;
			case "karo":
				cbo.karte1Spieler1.setIcon(cbo.karo[nummerk11]);
				break;
			}
			
			//Karte2 Spieler1
			switch (farbek21) {
			case "pik":
				cbo.karte2Spieler1.setIcon(cbo.pik[nummerk21]);
				break;
			case "herz":
				cbo.karte2Spieler1.setIcon(cbo.herz[nummerk21]);
				break;
			case "kreuz":
				cbo.karte2Spieler1.setIcon(cbo.kreuz[nummerk21]);
				break;
			case "karo":
				cbo.karte2Spieler1.setIcon(cbo.karo[nummerk21]);
				break;
			}
		
			
			//Karte1 Spieler2
					switch (farbek12) {
					case "pik":
						cbo.karte1Spieler2.setIcon(cbo.pik[nummerk12]);
						break;
					case "herz":
						cbo.karte1Spieler2.setIcon(cbo.herz[nummerk12]);
						break;
					case "kreuz":
						cbo.karte1Spieler2.setIcon(cbo.kreuz[nummerk12]);
						break;
					case "karo":
						cbo.karte1Spieler2.setIcon(cbo.karo[nummerk12]);
						break;
					}
				
			
					//Karte2 Spieler2
					switch (farbek22) {
					case "pik":
						cbo.karte2Spieler2.setIcon(cbo.pik[nummerk22]);
						break;
					case "herz":
						cbo.karte2Spieler2.setIcon(cbo.herz[nummerk22]);
						break;
					case "kreuz":
						cbo.karte2Spieler2.setIcon(cbo.kreuz[nummerk22]);
						break;
					case "karo":
						cbo.karte2Spieler2.setIcon(cbo.karo[nummerk22]);
						break;
					}
				
					//Karte1 Bank
				/*	switch (farbebank1) {
					case "pik":
						cbo.karte1Bank.setIcon(cbo.pik[nummerk1b]);
						break;
					case "herz":
						cbo.karte1Bank.setIcon(cbo.herz[nummerk1b]);
						break;
					case "kreuz":
						cbo.karte1Bank.setIcon(cbo.kreuz[nummerk1b]);
						break;
					case "karo":
						cbo.karte1Bank.setIcon(cbo.karo[nummerk1b]);
						break;
					} */
				
					//Karte2 Bank
					switch (farbebank2) {
					case "pik":
						cbo.karte2Bank.setIcon(cbo.pik[nummerk2b]);
						break;
					case "herz":
						cbo.karte2Bank.setIcon(cbo.herz[nummerk2b]);
						break;
					case "kreuz":
						cbo.karte2Bank.setIcon(cbo.kreuz[nummerk2b]);
						break;
					case "karo":
						cbo.karte2Bank.setIcon(cbo.karo[nummerk2b]);
						break;
					} 
					
			
			cbo.karte1Spieler1.setVisible(true);
			cbo.karte2Spieler1.setVisible(true);
			cbo.karte1Spieler2.setVisible(true);
			cbo.karte2Spieler2.setVisible(true);
			cbo.karte1Bank.setVisible(true);
			cbo.karte1Bank.setIcon(cbo.rückseite);
			cbo.karte2Bank.setVisible(true); 
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


	







