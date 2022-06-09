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
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Vector;
import java.net.InetAddress;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

//import jdk.internal.misc.FileSystemOption;



public class Server2 implements Runnable {

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
	int kontomax = 0;
Spieler aktuellerbenutzer;
boolean anmelden = false;




	private int errors = 0;



	private String waitingString = "Warten auf Mitspieler...";
	private String unableToCommunicateWithOpponentString = "Kommunikation mit dem Spieler nicht möglich.";
	private String wonString = "Du hast gewonnen!";
	private String enemyWonString = "Dealer gewinnt!";
	private String tieString = "Unentschieden!";

	public Server2() {
		System.out.println("Bitte gib deine IP an: ");
		ip = JOptionPane.showInputDialog("IP Adresse");
		System.out.println("Bitte gib einen Port an: ");
		port = Integer.parseInt(JOptionPane.showInputDialog("Port?"));
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
		while (!anmelden) {
			System.out.println("Warten");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		Spieler playerS = aktuellerbenutzer;
		kontomax = playerS.getKontostand();
		while (!klicks) {
			System.out.println("Warten");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		server.gesetztSpieler1 = gesetztS;
		System.out.println("s/Mein gesetzter Betrag"+gesetztS);
		try {
			dos.writeInt(gesetztS);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Betrag übermitteln
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
		System.out.println("s/Der Client hat soviel gesetzt: "+gesetztC);
		thread.stop();
	}

	boolean abbuchungOK(int m){
		boolean r = false;
		if ((swischespeicher+m)>kontomax)
			return false;
		else return true;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server2 neu = new Server2();

	}
	//Grafische Programmierung


	ActionHandler aHandler = new ActionHandler(this); 
	Benutzeroberfläche bo = new Benutzeroberfläche(this);



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
<<<<<<< Updated upstream


		String serverIP = "192.168.178.53";
		bo.labelIPAdresse.setText(serverIP);
=======
		
		  InetAddress ia;
		
		try { 
		 ia = InetAddress.getLocalHost(); 
			String str = ia.getHostAddress(); 
			System.out.println(str); 
			String serverIP =  ia.getHostAddress(); 
			bo.labelIPAdresse.setText(serverIP);
			} catch (Exception e) { 
			e.printStackTrace(); 
			}
	
>>>>>>> Stashed changes
		bo.labelIPAdresse.setVisible(true);

		//System.out.println(j10);


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
		bo.buttonEinsatzbestätigen.setVisible(true);

	}
	public void jeton10() {
		int j10 = 10;
		if(abbuchungOK(10)) {
			String j11 = Integer.toString(j10);
			bo.einsatzausgabe.setText("Der Einsatz beträgt:" +swischespeicher);
			bo.einsatzausgabe.setVisible(true);

			System.out.println(j10);
			System.out.println("Immo:"+swischespeicher);}
		else {
			JOptionPane.showMessageDialog(null, "Maximale Menge erreicht.");
		}
	}
	public void jeton25() {
		int j25 = 25;
		if(abbuchungOK(25)) {
			String j26 = Integer.toString(j25);

			bo.einsatzausgabe.setText("Der Einsatz beträgt:" +swischespeicher);
			bo.einsatzausgabe.setVisible(true);

			System.out.println(j25);
			System.out.println("Immo:"+swischespeicher);}
		else {
			JOptionPane.showMessageDialog(null, "Maximale Menge erreicht.");
		}
	}
	public void  jeton50() {
		int j50 = 50;
		if(abbuchungOK(50)) {
			String j51 = Integer.toString(j50);
			bo.einsatzausgabe.setText("Der Einsatz beträgt:" +swischespeicher);
			bo.einsatzausgabe.setVisible(true);
			System.out.println(j50);
			System.out.println("Immo:"+swischespeicher);}
		else {
			JOptionPane.showMessageDialog(null, "Maximale Menge erreicht.");
		}
	}
	public void jeton100() {
		int j100 = 100;
		if(abbuchungOK(100)) {
			String j101 = Integer.toString(j100);
			bo.einsatzausgabe.setText("Der Einsatz beträgt:" +swischespeicher);
			bo.einsatzausgabe.setVisible(true); 
			System.out.println(j100);
			System.out.println("Immo:"+swischespeicher);}
		else {
			JOptionPane.showMessageDialog(null, "Maximale Menge erreicht.");
		}

	}
	public void einsatzAusrechnen() {
		//swischespeicher = gesetztS; <---- Da liegt der Mist!
		gesetztS = swischespeicher;
		swischespeicher = 0;
	}


	//Vector
	private Vector<Spieler>players = new Vector<Spieler>();


	public boolean logIn() {
		int logIn = namePruefen();
		if(logIn >= 0) {
			if (passwordPruefen(logIn)) {
				
				return true;
			}
			else 
				return false;
		}
		else return false;
		
		
	}


	public void benutzerErstellen() {
		if (aHandler.password1.equals(aHandler.password2))
		{ 
			Spieler s = new Spieler(aHandler.benutzernameLogin, aHandler.password1);
				players.addElement(s);
		}
		else {
			System.out.println("Passwort nicht gleich");
			JOptionPane.showMessageDialog(null, "Bitte wiederholen Passwort nicht gleich");
		}

	}


	public boolean passwordPruefen(int i) {

		if (players.elementAt(i).getpasswort().equals(aHandler.password)==true)

			return true;

		else 
			return false;
	}



	public int namePruefen() {
		boolean gefunden = false;
		int j=-1;
		for (int i=0;i<players.size();i++) {
			if (players.elementAt(i).getname().equals(aHandler.benutzernameLogin)==true)
				j = i;	
			gefunden = true;
		}

		if (gefunden) {
			return j;
			
		}
		else {
			JOptionPane.showMessageDialog(null, "Name nicht gefunden");
			return -1;
		} 
	}}











