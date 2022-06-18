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

	int auswertStatSp1;
	int auswertStatSp2;
	static int hitostay;

	public String ausgabetextS1C; 
	public String ausgabetextS2C; 

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
	
	static boolean wartenAufSpielerClient = false; 
	static boolean wartenAufSpielerServer = false; 





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

		/**Spiel erstellen*/
		Spiel client = new Spiel();
		client.createDeck();
		/**Spieler erstellen*/
		Spieler playerS = new Spieler("playerS", "1234");

		newgame: while(true) {			
			/** Reset*/
			client.DeckSpieler1.clear();
			client.DeckSpieler2.clear();
			client.DeckDealer.clear();

			/**Gesetzter Betrag vom Server empfangen:*/
			try {
				gesetztS = dis.readInt();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//Spiel.wartenZuJettons();
			Spiel.setGesetztSpieler1(gesetztS);
			gesetztS = 0;
			//Bis hier her Wartebildschirm
			System.out.println("c/Server setzt: "+Spiel.getGesetztSpieler1());

			/**Warteschleife um Einsatz zu platzieren*/
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

			/**Karten vom Server empfangen.*/
			//Karte für Spieler 1 empfangen:
			client.DeckSpieler1.add(karteEmpfangen());
			client.DeckSpieler1.add(karteEmpfangen()); 	//2. Karte:
			//Karte für Spieler 2 empfangen:
			client.DeckSpieler2.add(karteEmpfangen());
			client.DeckSpieler2.add(karteEmpfangen()); 	//2. Karte:
			//Karten für Dealer empfangen:
			client.DeckDealer.add(karteEmpfangen());
			client.DeckDealer.add(karteEmpfangen()); 	//2. Karte:

			kartenausgebenS_R1(client);
			kartenwertanzeigen(client);

			/** BlackJack/Überkauft Abfrage empfangen.*/
			try {
				client.winSpieler1 = dis.readBoolean();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				client.winSpieler2 = dis.readBoolean();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				client.winDealer = dis.readBoolean();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				client.loseSpieler1 = dis.readBoolean();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				client.loseSpieler2 = dis.readBoolean();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				client.loseDealer = dis.readBoolean();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


			//Wenn BJ = true, mach neues Spiel (gehe zu newgame)
			while((client.winSpieler1 || client.winSpieler2 || client.winDealer || client.loseSpieler1 || client.loseSpieler2 || client.loseDealer)) {
				/** Auswertestatus der Spieler empfangen*/
				try {
					auswertStatSp1 = dis.readInt();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					auswertStatSp2 = dis.readInt();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				gewinnbenachrichtung();
				continue newgame;
			}

			//Wenn BJ = false, frag nach Hit oder Stay
			newcard: while((!client.winSpieler1 && !client.winSpieler2 && !client.winDealer)) {
				int z = 0; //Zähler Anzahl der Spielzüge.
				boolean hit1 = false;
				boolean hit2 = false;
				z++;
				if (z>3) {
					break newcard;
				}
				/**Spieler 1 Hit/Stay? empfangen"*/
				try {
					hit1 = dis.readBoolean();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				/**Spieler 2 Hit/Stay?*/
				while (!klicks) {
					System.out.println("Warten auf hit oder stay");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} klicks = false;
				if (hitostay == 1) {
					hit1 = true;
				}
				hitostay = 0;
				try {
					dos.writeBoolean(hit1);
				} catch (IOException e) {
					e.printStackTrace(); }
				

				while((!hit1 || !hit2)) {
					/**Auswertestatus der Spieler empfangen*/
					try {
						auswertStatSp1 = dis.readInt();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						auswertStatSp2 = dis.readInt();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					/** Gewinner/Verlierernachricht ausgeben*/
					gewinnbenachrichtung();		
					continue newgame;
				}

				while((hit1 && hit2)) {
					/**Karten vom Server empfangen.*/
					//Karte für Spieler 1 empfangen:
					client.DeckSpieler1.add(karteEmpfangen());
					//Karte für Spieler 2 empfangen:
					client.DeckSpieler2.add(karteEmpfangen());
					//Karten für Dealer empfangen, sofern der Kartenwert unter 17 liegt:
					if (client.wertDealer < 17) {
						client.DeckDealer.add(karteEmpfangen());
					}	
					kartenausgebenS_R234(client, z);
					kartenwertanzeigen(client);
					
					/** BlackJack/Überkauft Abfrage empfangen.*/
					try {
						client.winSpieler1 = dis.readBoolean();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						client.winSpieler2 = dis.readBoolean();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						client.winDealer = dis.readBoolean();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						client.loseSpieler1 = dis.readBoolean();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						client.loseSpieler2 = dis.readBoolean();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						client.loseDealer = dis.readBoolean();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if ((client.winSpieler1 || client.winSpieler2 || client.winDealer || client.winDealer || client.loseSpieler1 || client.loseSpieler2 || client.loseDealer)) {
						System.out.println("Ein spieler Gewinnt oder Überkauft sich");
						break newcard;
					}
					continue newcard;
				}
			
			}
			continue newgame;
		}
	
	}



	void gewinnbenachrichtung ()

	/** Spieler 1*/
	{ if (auswertStatSp1 == 0)
		ausgabetextS1C = "BlackJack";

	if (auswertStatSp1 == 1)
		ausgabetextS1C = "Gewonnen";

	if (auswertStatSp1 == 2)
		ausgabetextS1C = "Verloren";

	/** Spieler 2*/
	if (auswertStatSp2 == 0)
		ausgabetextS2C = "BlackJack";

	if (auswertStatSp2 == 1)
		ausgabetextS2C = "Gewonnen";

	if (auswertStatSp2 == 2)
		ausgabetextS2C = "Verloren";

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

	public void startZuIP() {
		cbo.buttonStartSpielC.setVisible(false);
		cbo.buttonAbbrechenSpiel.setVisible(false);

		//Gemeinsame
		//cbo.ueberschriftC.setVisible(false);
		cbo.buttonZurück.setVisible(true);

		//IPAdressefenster:
		cbo.buttonIPAdresseBestätigen.setVisible(true);
		cbo.labelipadresseC.setVisible(true);
		cbo.ipadresseText.setVisible(true);


	}

	
	public void ipZuAuswahl() {
	
		cbo.buttonStartSpielC.setVisible(false);
		cbo.buttonAbbrechenSpiel.setVisible(false);

		//Gemeinsame
		//cbo.ueberschriftC.setVisible(false);
		cbo.buttonZurück.setVisible(false);
		cbo.buttonZurückZuStart.setVisible(true);

		//IPAdressefenster:
		cbo.buttonIPAdresseBestätigen.setVisible(false);
		cbo.labelipadresseC.setVisible(false);
		cbo.ipadresseText.setVisible(false);
		
		//Auswahlbildschirm
		cbo.buttonLogin.setVisible(true);
		cbo.buttonRegistrieren.setVisible(true);

	}
	public void zurückZuStart() {
		//Startbildschirm
		cbo.buttonStartSpielC.setVisible(true);
		cbo.buttonAbbrechenSpiel.setVisible(true);
		cbo.buttonZurückZuStart.setVisible(false);
		
		cbo.buttonLogin.setVisible(false);
		cbo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		cbo.ueberschrift.setVisible(false);
		cbo.buttonZurück.setVisible(false);
		cbo.logo.setVisible(true);

		//Loginfenster
		cbo.labelBenutzernameC.setVisible(false);
		cbo.labelPasswortC.setVisible(false);
		cbo.buttonstart.setVisible(false);
		cbo.userText.setVisible(false);
		cbo.passwordText.setVisible(false);
		cbo.anmeldetext.setVisible(false);


		//Registrierfenster
		cbo.labelBenutzernameCErstellenC.setVisible(false);
		cbo.labelPasswortC1C.setVisible(false);
		cbo.labelPasswortC2C.setVisible(false);
		cbo.buttonRegistrierenAbschließen.setVisible(false);
		cbo.userRegistText.setVisible(false);
		cbo.passwordText1.setVisible(false);
		cbo.passwordText2.setVisible(false);
		cbo.registrierungtext.setVisible(false);
	}
	
	public void auswahlZuLogin() {
		//Startbildschirm
		cbo.buttonLogin.setVisible(false);
		cbo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		//cbo.ueberschriftC.setVisible(false);
		cbo.buttonZurück.setVisible(true);
		cbo.logo.setVisible(false);
		cbo.buttonZurückZuStart.setVisible(false);

		//Loginfenster
		cbo.labelBenutzernameC.setVisible(true);
		cbo.labelPasswortC.setVisible(true);
		cbo.buttonstart.setVisible(true);
		cbo.userText.setVisible(true);
		cbo.passwordText.setVisible(true);
		cbo.buttonZurück.setVisible(true);
		cbo.anmeldetext.setVisible(true);

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
		//cbo.ueberschriftC.setVisible(false);
		cbo.buttonZurück.setVisible(true);
		cbo.logo.setVisible(false);
		cbo.buttonZurückZuStart.setVisible(false);

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
		cbo.registrierungtext.setVisible(true);

	}

	public void logRegZuAuswahl () {
		//Startbildschirm
		cbo.buttonLogin.setVisible(true);
		cbo.buttonRegistrieren.setVisible(true);

		//Gemeinsame
		//cbo.ueberschriftC.setVisible(false);
		cbo.buttonZurück.setVisible(false);
		cbo.logo.setVisible(true);


		//Loginfenster
		cbo.labelBenutzernameC.setVisible(false);
		cbo.labelPasswortC.setVisible(false);
		cbo.buttonstart.setVisible(false);
		cbo.userText.setVisible(false);
		cbo.passwordText.setVisible(false);
		cbo.anmeldetext.setVisible(false);


		//Registrierfenster
		cbo.labelBenutzernameCErstellenC.setVisible(false);
		cbo.labelPasswortC1C.setVisible(false);
		cbo.labelPasswortC2C.setVisible(false);
		cbo.buttonRegistrierenAbschließen.setVisible(false);
		cbo.userRegistText.setVisible(false);
		cbo.passwordText1.setVisible(false);
		cbo.passwordText2.setVisible(false);
		cbo.registrierungtext.setVisible(false);

	}
	
	

	public void logRegZuEinsatz() {
		cbo.buttonLogin.setVisible(false);
		cbo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		//cbo.ueberschriftC.setVisible(false);
		cbo.buttonZurück.setVisible(false);
		cbo.hintergrundmenu.setVisible(false);
		cbo.panelstart.setVisible(false);


		//Loginfenster
		cbo.labelBenutzernameC.setVisible(false);
		cbo.labelPasswortC.setVisible(false);
		cbo.buttonstart.setVisible(false);
		cbo.userText.setVisible(false);
		cbo.passwordText.setVisible(false);
		cbo.buttonZurück.setVisible(false);
		cbo.anmeldetext.setVisible(false);

		//Registrierfenster
		cbo.labelBenutzernameCErstellenC.setVisible(false);
		cbo.labelPasswortC1C.setVisible(false);
		cbo.labelPasswortC2C.setVisible(false);
		cbo.buttonRegistrierenAbschließen.setVisible(false);
		cbo.userRegistText.setVisible(false);
		cbo.passwordText1.setVisible(false);
		cbo.passwordText2.setVisible(false);
		cbo.registrierungtext.setVisible(false);

		//IPAdressefenster:
		cbo.buttonIPAdresseBestätigen.setVisible(false);
		cbo.labelipadresseC.setVisible(false);
		cbo.ipadresseText.setVisible(false);

		

		//Spielfenster: 
		cbo.menuleiste.setVisible(true);
		cbo.buttonEinsatz.setVisible(true);
		cbo.labelSpieler1C.setVisible(true);
		cbo.labelSpieler2C.setVisible(true);
		cbo.ueberschriftCSpielC.setVisible(false);
		cbo.labelBankC.setVisible(true);
		
		//Kartenfenster
		cbo.kartenfeldS1.setVisible(true);
		cbo.kartenfeldS1g.setVisible(true);
		cbo.kartenfeldS2.setVisible(true);
		cbo.kartenfeldS2g.setVisible(true);
		cbo.kartenfeldbank.setVisible(true);
		cbo.kartenfeldbankg.setVisible(true);
		

	}
	
	public void einsatzZuJetons() {
		cbo.buttonLogin.setVisible(false);
		cbo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		//cbo.ueberschriftC.setVisible(false);
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

	public void kartenausgebenS_R234(Spiel s, int runde){
		String farbek11=null;
		String farbek12=null;
		String farbebank1=null;
		int nummerk11=-1;
		int nummerk12=-1;
		int nummerk1b=-1;

		try {
			farbek11 = s.DeckSpieler1.get(runde).getFarbe();
		}
		catch (Exception e){}
		try {
			farbek12 = s.DeckSpieler2.get(runde).getFarbe();
		}
		catch (Exception e){}
		try {
			farbebank1= s.DeckDealer.get(runde).getFarbe();
		}
		catch (Exception e){}

		try {
			nummerk11 = s.DeckSpieler1.get(s.DeckSpieler1.size()-1).getName();
		}
		catch (Exception e){}

		try {
			nummerk12 = s.DeckSpieler2.get(s.DeckSpieler2.size()-1).getName();
		}
		catch (Exception e){}

		try {
			nummerk1b = s.DeckDealer.get(s.DeckDealer.size()-1).getName();
		}
		catch (Exception e){}

		/**weitere Karte von Spieler1 anzeigen.*/
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
		default:
			break;
		}


		//Karte Spieler2
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
		default:
			break;
		}


		//Karte Bank
		switch (farbebank1) {
		case "pik":
			cbo.karte2Bank.setIcon(cbo.pik[nummerk1b]);
			break;
		case "herz":
			cbo.karte2Bank.setIcon(cbo.herz[nummerk1b]);
			break;
		case "kreuz":
			cbo.karte2Bank.setIcon(cbo.kreuz[nummerk1b]);
			break;
		case "karo":
			cbo.karte2Bank.setIcon(cbo.karo[nummerk1b]);
			break;
		default:
			break;
		} 

		if (runde == 2) {
			cbo.karte1Spieler1.setVisible(true);
			cbo.karte2Spieler1.setVisible(true);
			cbo.karte3Spieler1.setVisible(true);

			cbo.karte1Spieler2.setVisible(true);
			cbo.karte2Spieler2.setVisible(true);
			cbo.karte3Spieler2.setVisible(true);

			cbo.karte1Bank.setVisible(true);
			cbo.karte1Bank.setIcon(cbo.rückseite);
			cbo.karte2Bank.setVisible(true);
			cbo.karte3Bank.setVisible(true);}
		else if (runde == 3) {
			cbo.karte1Spieler1.setVisible(true);
			cbo.karte2Spieler1.setVisible(true);
			cbo.karte3Spieler1.setVisible(true);
			cbo.karte4Spieler1.setVisible(true);

			cbo.karte1Spieler2.setVisible(true);
			cbo.karte2Spieler2.setVisible(true);
			cbo.karte3Spieler2.setVisible(true);
			cbo.karte4Spieler2.setVisible(true);

			cbo.karte1Bank.setVisible(true);
			cbo.karte1Bank.setIcon(cbo.rückseite);
			cbo.karte2Bank.setVisible(true);
			cbo.karte3Bank.setVisible(true);
			cbo.karte4Bank.setVisible(true);}
		else if (runde == 4) {
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
			cbo.karte1Bank.setIcon(cbo.rückseite);
			cbo.karte2Bank.setVisible(true);
			cbo.karte3Bank.setVisible(true);
			cbo.karte4Bank.setVisible(true); 
			cbo.karte5Bank.setVisible(true);}
	}

	public void jetonsZuHitundStay() {
		cbo.buttonLogin.setVisible(false);
		cbo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		//cbo.ueberschriftC.setVisible(false);
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
		cbo.buttonJeton10.setVisible(false);
		cbo.buttonJeton25.setVisible(false);
		cbo.buttonJeton50.setVisible(false);
		cbo.buttonJeton100.setVisible(false);
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
		cbo.buttonHit.setVisible(true);
		cbo.buttonStay.setVisible(true);


		cbo.kartenwertSpieler1.setVisible(true);
		cbo.kartenwertSpieler2.setVisible(true);
		cbo.kartenwertDealer.setVisible(true);
	}

	public void rundeZuAuswerten() {
		cbo.buttonLogin.setVisible(false);
		cbo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		//cbo.ueberschriftC.setVisible(false);
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
		cbo.buttonJeton10.setVisible(false);
		cbo.buttonJeton25.setVisible(false);
		cbo.buttonJeton50.setVisible(false);
		cbo.buttonJeton100.setVisible(false);
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
		cbo.buttonEinsatzbestätigen.setVisible(false);
		cbo.buttonHit.setVisible(false);
		cbo.buttonStay.setVisible(false);
		cbo.kartenwertSpieler1.setVisible(true);
		cbo.kartenwertSpieler2.setVisible(true);
		cbo.kartenwertDealer.setVisible(true);
		cbo.nachrichtS1.setText(ausgabetextS1C);
		cbo.nachrichtS2.setText(ausgabetextS2C);
		cbo.nachrichtS1.setVisible(true);

		cbo.nachrichtS2.setVisible(true);
		cbo.buttonNaechsteRunde.setVisible(true);
	}

	public void auswertenZuEinsatz() {
		cbo.buttonLogin.setVisible(false);
		cbo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		//cbo.ueberschriftC.setVisible(false);
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
		cbo.buttonJeton10.setVisible(false);
		cbo.buttonJeton25.setVisible(false);
		cbo.buttonJeton50.setVisible(false);
		cbo.buttonJeton100.setVisible(false);
		cbo.karte1Spieler1.setVisible(false);
		cbo.karte2Spieler1.setVisible(false);
		cbo.karte3Spieler1.setVisible(false); 
		cbo.karte4Spieler1.setVisible(false); 
		cbo.karte5Spieler1.setVisible(false); 
		cbo.karte1Spieler2.setVisible(false);
		cbo.karte2Spieler2.setVisible(false);
		cbo.karte3Spieler2.setVisible(false);
		cbo.karte4Spieler2.setVisible(false);
		cbo.karte5Spieler2.setVisible(false);
		cbo.karte1Bank.setVisible(false);
		cbo.karte2Bank.setVisible(false);
		cbo.karte3Bank.setVisible(false);
		cbo.karte4Bank.setVisible(false);
		cbo.karte5Bank.setVisible(false);
		cbo.einsatzSpieler1C.setVisible(false);
		cbo.einsatzSpieler1C.setVisible(false);
		//cbo.kontostandSpieler1C.setVisible(true);
		cbo.kontostandSpieler2.setVisible(false); 
		cbo.buttonEinsatzbestätigen.setVisible(false);
		cbo.buttonHit.setVisible(false);
		cbo.buttonStay.setVisible(false);
		cbo.kartenwertSpieler1.setVisible(false);
		cbo.kartenwertSpieler2.setVisible(false);
		cbo.kartenwertDealer.setVisible(false);
		cbo.nachrichtS1.setVisible(false);
		cbo.nachrichtS2.setVisible(false);
		cbo.buttonNaechsteRunde.setVisible(false);
	}
	
	public void warteBildschirm() {
		cbo.wartenAufSpieler.setVisible(true);
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










