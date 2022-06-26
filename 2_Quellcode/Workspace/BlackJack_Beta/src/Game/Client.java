package Game;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import javax.swing.JOptionPane;



//import jdk.internal.misc.FileSystemOption;



public class Client implements Runnable {

	private String ip ;
	private int port; //Port f¸r TCP/IP Verbindung


	private Thread thread;

	private boolean close = false; //Schlieﬂt wenn true

	private ServerSocket serverSocket;
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;

	int auswertStatSp1;
	int auswertStatSp2;
	static int hitostay;

	public String ausgabetextS1C; 
	public String ausgabetextS2C; 

	private boolean client = true;	// Bin ich der Client? Ist aktuell deaktivert, da Server und Client getrennte Anwendungen sind.

	private boolean accepted = false;	//Bin ich schon mit einem Server verbunden?

	int gesetztS; 
	int gesetztC;
	String nameSp1 = "Mitspieler";
	String nameSp2 = "Ich";
	int kontomax = 0;
	static boolean klicks = false; 
	static boolean spieler1LoggedIn = false;
	static boolean spieler2LoggedIn = false;
	public static int zwischenspeicher;

	//static boolean wartenAufSpielerClient = false; 
	//static boolean wartenAufSpielerServer = false; 







	public Client() {
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

		//falls Manuelle Porteingabe Vorgesehen:
		while (port < 1 || port > 65535) {
			System.out.println("Dein Port war ung¸ltig, bitte gib einen neuen ein: ");
			port = Integer.parseInt(JOptionPane.showInputDialog("Port?"));
		}

		if (!verbunden())
			initialisiereServer();
		thread = new Thread(this, "BlackJack");
		thread.start();
	}

	public void run() {
		while (true) {

			//Programmcode welcher im "Thread" ausgef¸hrt wird.

			if (!client && !accepted) {
				neuerDataStream();
			}

			spielablauf();

			if (close) {
				break;
			}

		}
	}

	private void neuerDataStream() {
		Socket socket = null;
		try {
			socket = serverSocket.accept();
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			accepted = true;
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
			//System.out.println("Meine IP Adresse und Port: " + ip + " : " + port + " | Erˆffne einen Server.");
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

		client = false;
	}

	private void spielablauf()  {

		/**Spiel erstellen*/
		Spiel client = new Spiel();
		/**Spieler erstellen*/
		Spieler playerC = new Spieler("Spieler2", "0000");
		
		while (!klicks) {
			System.out.println("Warten auf LogIn Name.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} klicks = false;
		
		/**LogIn Best‰tigung an Server senden.*/
		try {
			dos.writeBoolean(spieler2LoggedIn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			spieler1LoggedIn = dis.readBoolean();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/**Log In Routine im Server + Client beendet. Spieler 1 und Spieler 2 haben ihren Benutzernamen eingegeben:*/
		while (!spieler1LoggedIn & !spieler2LoggedIn) {
			System.out.println("Warten auf Login der Spieler");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/**Benutzername vom Server empfangen.*/
		try {
			String nameMitSpieler = dis.readUTF();
			setSpielernameServer(nameMitSpieler);
			nameSp1 = nameMitSpieler;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/**Benutzername an Server verschicken.*/
		try {
			dos.writeUTF(cHandler.benutzername);
			nameSp2 = cHandler.benutzername;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		newgame: while(true) {			
			/** Reset*/
			client.DeckSpieler1.clear();
			client.DeckSpieler2.clear();
			client.DeckDealer.clear();
			client.wertSpieler1 = 0;
			client.wertSpieler2 = 0;
			client.wertSpieler2 = 0;
			int z = 2; //Z‰hler. Z‰hlt Anzahl der Spielz¸ge. (max 3 Karten aufnehmen)

			/**Aktuellen Kontostand abfragen:*/
			kontomax = playerC.getKontostand();
			kontostandanzeigen(client, playerC);

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
			//Betrag ¸bermitteln
			try {
				dos.writeInt(gesetztC);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Spiel.setGesetztSpieler2(gesetztC);
			gesetztC = 0;
			playerC.abbuchen(Spiel.getGesetztSpieler2());
			kontostandanzeigen(client, playerC);

			/**Karten vom Server empfangen.*/
			//Karte f¸r Spieler 1 empfangen:
			client.DeckSpieler1.add(karteEmpfangen());
			client.DeckSpieler1.add(karteEmpfangen()); 	//2. Karte
			//Karte f¸r Spieler 2 empfangen:
			client.DeckSpieler2.add(karteEmpfangen());
			client.DeckSpieler2.add(karteEmpfangen()); 	//2. Karte
			//Karten f¸r Dealer empfangen:
			client.DeckDealer.add(karteEmpfangen());
			client.DeckDealer.add(karteEmpfangen()); 	//2. Karte

			kartenausgebenS_R1(client);
			kartenwertanzeigen(client);

			/** BlackJack/‹berkauft Abfrage empfangen.*/
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


			/**Wenn BlackJack oder Spieler ‹berkauft, werte aus und mach ein neue Runde (gehe zu newgame)*/
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
				gewinnbenachrichtung(client,playerC);

				rundeZuAuswerten();
				try {
					Thread.sleep(15000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				auswertenZuEinsatz();

				continue newgame;
			}

			//Wenn kein BlackJack und nicht ‹berkauft, frag nach Hit oder Stay
			newcard: while((!client.winSpieler1 && !client.winSpieler2 && !client.winDealer || client.loseSpieler1 || client.loseSpieler2 || client.loseDealer)) {

				boolean hit1 = false;
				boolean hit2 = false;

				if (z>5) {
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
					hit2 = true;
				}
				hitostay = 0;
				/**Spieler 2 Hit/Stay? verschicken*/
				try {
					dos.writeBoolean(hit2);
				} catch (IOException e) {
					e.printStackTrace(); }

				/* Wenn kein Spieler eine Karte aufnehmen mˆchte*/
				while((!hit1 && !hit2)) {

					/**Karten f¸r Dealer empfangen, sofern der Kartenwert unter 17 liegt*/
					if (client.wertDealer() < 17) {
						client.DeckDealer.add(karteEmpfangen());
					}
					kartenwertanzeigen(client);
					kartenausgebenS_R234(client, z);

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
					gewinnbenachrichtung(client,playerC);	

					rundeZuAuswerten();
					try {
						Thread.sleep(15000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					auswertenZuEinsatz();

					continue newgame;
				}

				/* Wenn beide oder ein Spieler eine Karte aufnehmen mˆchten*/
				while((hit1 || hit2)) {
					/**Karte f¸r Spieler 1 empfangen*/
					if (hit1) {
						client.DeckSpieler1.add(karteEmpfangen());
					}
					//Karte f¸r Spieler 2 empfangen:
					if (hit2) {
						client.DeckSpieler2.add(karteEmpfangen());
					}
					/**Karten f¸r Dealer empfangen, sofern der Kartenwert unter 17 liegt*/
					if (client.wertDealer() < 17) {
						client.DeckDealer.add(karteEmpfangen());
					}	
					kartenwertanzeigen(client);
					kartenausgebenS_R234(client, z);

					if (hit1 && hit2) {
						/** BlackJack/‹berkauft Abfrage empfangen.*/
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
						/* Wenn ein Spieler BlackJack oder ‹berkauft*/
						if ((client.winSpieler1 || client.winSpieler2 || client.winDealer || client.winDealer || client.loseSpieler1 || client.loseSpieler2 || client.loseDealer)) {
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
							gewinnbenachrichtung(client,playerC);

							rundeZuAuswerten();
							try {
								Thread.sleep(15000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							auswertenZuEinsatz();
							continue newgame;
						}
					}
					if ((hit1 && !hit2) || (!hit1 && hit2)) {
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
						gewinnbenachrichtung(client,playerC);
						rundeZuAuswerten();
						try {
							Thread.sleep(15000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						auswertenZuEinsatz();
						continue newgame;
					}

					z++;
					continue newcard;
				}

			}
			continue newgame;
		}

	}



	void gewinnbenachrichtung (Spiel s, Spieler p) {

		/** Spieler 1*/
		if (auswertStatSp1 == 0) {
			ausgabetextS1C = "BlackJack";
			System.out.println("Blackjack");}

		if (auswertStatSp1 == 1) {
			ausgabetextS1C = "Gewonnen";
			System.out.println("gew");}

		if (auswertStatSp1 == 2) {
			ausgabetextS1C = "Verloren";
			System.out.println("verl");}

		if (auswertStatSp1 == 3) {
			ausgabetextS1C = "Unentschieden";
			System.out.println("unent");}

		/** Spieler 2*/
		if (auswertStatSp2 == 0)
			ausgabetextS2C = "BlackJack";
		System.out.println("Blackjack");

		if (auswertStatSp2 == 1)
			ausgabetextS2C = "Gewonnen";
		System.out.println("geww");

		if (auswertStatSp2 == 2)
			ausgabetextS2C = "Verloren";
		System.out.println("verl");

		if (auswertStatSp2 == 3)
			ausgabetextS1C = "Unentschieden";
		System.out.println("Unent");

		/** 1. Karte von Dealer aufdecken*/
		dealerKarteAufdecken(s);

		/**ggfs. Einsatz auf Konto des Spielers buchen.*/
		p.einzahlen(Spiel.getGesetztSpieler2(), auswertStatSp2);

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

	/** Pr¸ft ob gen¸gend Geld vorhanden*/
	boolean abbuchungOK(int m){

		if ((zwischenspeicher)>kontomax) {
			zwischenspeicher -= m;
			return false; }
		else 
			return true;
	}


	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client gameclient = new Client();

	}
	//Grafische Programmierung


	ActionHandlerClient cHandler = new ActionHandlerClient(this); 
	Benutzeroberfl‰cheClient cbo = new Benutzeroberfl‰cheClient(this);

	//Vector
	Vector<Spieler>player = new Vector<Spieler>();

	public void startZuIP() {
		cbo.buttonStartSpielC.setVisible(false);
		cbo.buttonAbbrechenSpiel.setVisible(false);

		//Gemeinsame
		//cbo.ueberschriftC.setVisible(false);
		cbo.buttonZurueck.setVisible(true);

		//IPAdressefenster:
		cbo.buttonIPAdresseBestaetigen.setVisible(true);
		cbo.labelipadresseC.setVisible(true);
		cbo.ipadresseText.setVisible(true);
		cbo.ipAdressetext.setVisible(true);

	}


	public void ipZuAuswahl() {

		cbo.buttonStartSpielC.setVisible(false);
		cbo.buttonAbbrechenSpiel.setVisible(false);

		//Gemeinsame
		//cbo.ueberschriftC.setVisible(false);
		cbo.buttonZurueck.setVisible(false);
		cbo.buttonZurueckZuStart.setVisible(true);

		//IPAdressefenster:
		cbo.buttonIPAdresseBestaetigen.setVisible(false);
		cbo.labelipadresseC.setVisible(false);
		cbo.ipadresseText.setVisible(false);

		//Auswahlbildschirm
		cbo.buttonLogin.setVisible(true);
		cbo.buttonRegistrieren.setVisible(false);
		cbo.ipAdressetext.setVisible(false);

	}
	public void zur¸ckZuStart() {
		//Startbildschirm
		cbo.buttonStartSpielC.setVisible(true);
		cbo.buttonAbbrechenSpiel.setVisible(true);
		cbo.buttonZurueckZuStart.setVisible(false);

		cbo.buttonLogin.setVisible(false);
		cbo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		//cbo.ueberschrift.setVisible(false);
		cbo.buttonZurueck.setVisible(false);
		cbo.logo.setVisible(true);

		//Loginfenster
		cbo.labelBenutzernameC.setVisible(false);
		cbo.labelPasswortC.setVisible(false);
		cbo.buttonstart.setVisible(false);
		cbo.userText.setVisible(false);
		cbo.passwordText.setVisible(false);
		cbo.anmeldetext.setVisible(false);
		cbo.ipAdressetext.setVisible(false);


		//Registrierfenster
		cbo.labelBenutzernameCErstellenC.setVisible(false);
		cbo.labelPasswortC1C.setVisible(false);
		cbo.labelPasswortC2C.setVisible(false);
		cbo.buttonRegistrierenAbschlieﬂen.setVisible(false);
		cbo.userRegistText.setVisible(false);
		cbo.passwordText1.setVisible(false);
		cbo.passwordText2.setVisible(false);
		cbo.ipAdressetext.setVisible(false);
	}

	public void auswahlZuLogin() {
		//Startbildschirm
		cbo.buttonLogin.setVisible(false);
		cbo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		//cbo.ueberschriftC.setVisible(false);
		cbo.buttonZurueck.setVisible(true);
		cbo.logo.setVisible(false);
		cbo.buttonZurueckZuStart.setVisible(false);

		//Loginfenster
		cbo.labelBenutzernameC.setVisible(true);
		cbo.labelPasswortC.setVisible(false);
		cbo.buttonstart.setVisible(true);
		cbo.userText.setVisible(true);
		cbo.passwordText.setVisible(false);
		cbo.buttonZurueck.setVisible(true);
		cbo.anmeldetext.setVisible(true);

		//Registrierfenster
		cbo.labelBenutzernameCErstellenC.setVisible(false);
		cbo.labelPasswortC1C.setVisible(false);
		cbo.labelPasswortC2C.setVisible(false);
		cbo.buttonRegistrierenAbschlieﬂen.setVisible(false);
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
		cbo.buttonZurueck.setVisible(true);
		cbo.logo.setVisible(false);
		cbo.buttonZurueckZuStart.setVisible(false);

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
		cbo.buttonRegistrierenAbschlieﬂen.setVisible(true);
		cbo.userRegistText.setVisible(true);
		cbo.passwordText1.setVisible(true);
		cbo.passwordText2.setVisible(true);
		cbo.ipAdressetext.setVisible(false);

	}

	public void logRegZuAuswahl () {
		//Startbildschirm
		cbo.buttonLogin.setVisible(true);
		cbo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		//cbo.ueberschriftC.setVisible(false);
		cbo.buttonZurueck.setVisible(false);
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
		cbo.buttonRegistrierenAbschlieﬂen.setVisible(false);
		cbo.userRegistText.setVisible(false);
		cbo.passwordText1.setVisible(false);
		cbo.passwordText2.setVisible(false);
		cbo.ipAdressetext.setVisible(false);

	}



	public void logRegZuEinsatz() {
		cbo.buttonLogin.setVisible(false);
		cbo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		cbo.ueberschriftCSpielC.setVisible(true);
		cbo.unterueberschriftCSpielC.setVisible(true);
		cbo.buttonZurueck.setVisible(false);
		cbo.hintergrundmenu.setVisible(false);
		cbo.panelstart.setVisible(false);


		//Loginfenster
		cbo.labelBenutzernameC.setVisible(false);
		cbo.labelPasswortC.setVisible(false);
		cbo.buttonstart.setVisible(false);
		cbo.userText.setVisible(false);
		cbo.passwordText.setVisible(false);
		cbo.buttonZurueck.setVisible(false);
		cbo.anmeldetext.setVisible(false);

		//Registrierfenster
		cbo.labelBenutzernameCErstellenC.setVisible(false);
		cbo.labelPasswortC1C.setVisible(false);
		cbo.labelPasswortC2C.setVisible(false);
		cbo.buttonRegistrierenAbschlieﬂen.setVisible(false);
		cbo.userRegistText.setVisible(false);
		cbo.passwordText1.setVisible(false);
		cbo.passwordText2.setVisible(false);


		//IPAdressefenster:
		cbo.buttonIPAdresseBestaetigen.setVisible(false);
		cbo.labelipadresseC.setVisible(false);
		cbo.ipadresseText.setVisible(false);



		//Spielfenster: 
		cbo.menuleiste.setVisible(true);
		cbo.buttonEinsatz.setVisible(true);
		cbo.labelSpieler1C.setVisible(true);
		cbo.labelSpieler2C.setVisible(true);
		//cbo.ueberschriftCSpielC.setVisible(true);
		cbo.labelBankC.setVisible(true);

		//Kartenfenster
		cbo.kartenfeldS1.setVisible(true);
		cbo.kartenfeldS1g.setVisible(true);
		cbo.kartenfeldS2.setVisible(true);
		cbo.kartenfeldS2g.setVisible(true);
		cbo.kartenfeldbank.setVisible(true);
		cbo.kartenfeldbankg.setVisible(true);

		cbo.einsatzSpieler1C.setVisible(true);
		cbo.einsatzSpieler2C.setVisible(true);
		cbo.kontostandSpieler1.setVisible(true);
		cbo.kontostandSpieler2.setVisible(true); 


	}

	public void einsatzZuJetons() {
		cbo.buttonLogin.setVisible(false);
		cbo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		//cbo.ueberschriftC.setVisible(false);
		cbo.buttonZurueck.setVisible(false);

		//Loginfenster
		cbo.labelBenutzernameC.setVisible(false);
		cbo.labelPasswortC.setVisible(false);
		cbo.buttonstart.setVisible(false);
		cbo.userText.setVisible(false);
		cbo.passwordText.setVisible(false);
		cbo.buttonZurueck.setVisible(false);

		//Registrierfenster
		cbo.labelBenutzernameCErstellenC.setVisible(false);
		cbo.labelPasswortC1C.setVisible(false);
		cbo.labelPasswortC2C.setVisible(false);
		cbo.buttonRegistrierenAbschlieﬂen.setVisible(false);
		cbo.userRegistText.setVisible(false);
		cbo.passwordText1.setVisible(false);
		cbo.passwordText2.setVisible(false);

		//IPAdressefenster:
		cbo.buttonIPAdresseBestaetigen.setVisible(false);
		cbo.labelipadresseC.setVisible(false);
		cbo.ipadresseText.setVisible(false);

		//Portfenster: 
		cbo.buttonPortBest‰tigen.setVisible(false);
		cbo.labelportC.setVisible(false);
		cbo.portText.setVisible(false);

		//Spielfenster: 
		cbo.buttonEinsatz.setVisible(false);
		cbo.buttonJeton10.setVisible(true);
		cbo.buttonJeton25.setVisible(true);
		cbo.buttonJeton50.setVisible(true);
		cbo.buttonJeton100.setVisible(true);

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


		//cbo.kontostandSpieler1C.setVisible(true);
		cbo.kontostandSpieler2.setVisible(true); 
		cbo.buttonEinsatzbestaetigen.setVisible(true);



		cbo.kartenwertSpieler1.setVisible(true);
		cbo.kartenwertSpieler2.setVisible(true);
		cbo.kartenwertDealer.setVisible(false);

	}


	public void kontostandanzeigen (Spiel s, Spieler p) {
		cbo.kontostandSpieler2.setText(("Dein Kontostand: "+ Integer.toString(p.getKontostand())+"$"));
	}



	public void kartenwertanzeigen(Spiel s) {

		cbo.kartenwertSpieler1.setText("Kartenwert von "+nameSp1+" :"+Integer.toString(s.wertSpieler1()));
		cbo.kartenwertSpieler2.setText("Dein Kartenwert : "+Integer.toString(s.wertSpieler2()));
		//cbo.kartenwertDealer.setText("Kartenwert Dealer: "+Integer.toString(s.wertDealer()));

	}

	public void jeton10() {
		int j10 = 10;
		if(abbuchungOK(10)) {
			cbo.einsatzausgabeSpieler2C.setText("Dein Einsatz betr‰gt: " +zwischenspeicher+"$");
			cbo.einsatzausgabeSpieler2C.setVisible(true);

			System.out.println(j10);
			System.out.println("Immo:"+zwischenspeicher);}
		else {
			JOptionPane.showMessageDialog(null, "Maximaler Geldbetrag erreicht.");
		}
	}
	public void jeton25() {
		int j25 = 25;
		if(abbuchungOK(25)) {
			cbo.einsatzausgabeSpieler2C.setText("Dein Einsatz betr‰gt: " +zwischenspeicher+"$");
			cbo.einsatzausgabeSpieler2C.setVisible(true);

			System.out.println(j25);
			System.out.println("Immo:"+zwischenspeicher);}
		else {
			JOptionPane.showMessageDialog(null, "Maximaler Geldbetrag erreicht.");
		}
	}
	public void  jeton50() {
		int j50 = 50;
		if(abbuchungOK(50)) {
			cbo.einsatzausgabeSpieler2C.setText("Dein Einsatz betr‰gt: " +zwischenspeicher+"$");
			cbo.einsatzausgabeSpieler2C.setVisible(true);

			System.out.println(j50);
			System.out.println("Immo:"+zwischenspeicher);}
		else {
			JOptionPane.showMessageDialog(null, "Maximaler Geldbetrag erreicht.");
		}
	}
	public void jeton100() {
		int j100 = 100;
		if(abbuchungOK(100)) {
			cbo.einsatzausgabeSpieler2C.setText("Dein Einsatz betr‰gt: " +zwischenspeicher+"$");
			cbo.einsatzausgabeSpieler2C.setVisible(true); 
			System.out.println(j100);
			System.out.println("Immo:"+zwischenspeicher);}
		else {
			JOptionPane.showMessageDialog(null, "Maximaler Geldbetrag erreicht.");
		}

	}


	public void einsatzAusrechnen() {
		gesetztC = zwischenspeicher; 
		zwischenspeicher = 0;
		einsatzAnzeigenGegenspieler(); 
	}

	public void einsatzAnzeigenGegenspieler() {
		cbo.einsatzausgabeSpieler1C.setVisible(true);
		cbo.einsatzausgabeSpieler1C.setText(nameSp1+" setzt: " + Spiel.getGesetztSpieler1()+"$");
	}


	public void kartenausgebenS_R1(Spiel s){

		cbo.karte3Spieler1.setIcon(null);
		cbo.karte3Spieler2.setIcon(null);
		cbo.karte3Bank.setIcon(null);
		cbo.karte4Spieler1.setIcon(null);
		cbo.karte4Spieler2.setIcon(null);
		cbo.karte4Bank.setIcon(null);
		cbo.karte5Spieler1.setIcon(null);
		cbo.karte5Spieler2.setIcon(null);
		cbo.karte5Bank.setIcon(null);

		//String farbek11 = s.DeckSpieler1.get(0).getFarbe();
		String farbek11 = s.DeckSpieler1.get(0).getFarbe();
		String farbek21 = s.DeckSpieler1.get(1).getFarbe();
		String farbek12 = s.DeckSpieler2.get(0).getFarbe();
		String farbek22 = s.DeckSpieler2.get(1).getFarbe();
		String farbebank2= s.DeckDealer.get(1).getFarbe();

		//int nummerk11 = s.DeckSpieler1.get(0).getName();
		int nummerk11 = s.DeckSpieler1.get(0).getName();
		int nummerk21 = s.DeckSpieler1.get(1).getName();
		int nummerk12 = s.DeckSpieler2.get(0).getName();
		int nummerk22 = s.DeckSpieler2.get(1).getName();
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
		cbo.karte3Spieler1.setVisible(false);
		cbo.karte4Spieler1.setVisible(false);
		cbo.karte5Spieler1.setVisible(false);
		cbo.karte1Spieler2.setVisible(true);
		cbo.karte2Spieler2.setVisible(true);
		cbo.karte3Spieler2.setVisible(false);
		cbo.karte4Spieler2.setVisible(false);
		cbo.karte5Spieler2.setVisible(false);
		cbo.karte1Bank.setVisible(true);
		cbo.karte1Bank.setIcon(cbo.rueckseite);
		cbo.karte2Bank.setVisible(true); 
		cbo.karte3Bank.setVisible(false); 
		cbo.karte4Bank.setVisible(false); 
		cbo.karte5Bank.setVisible(false); 
	}

	public void kartenausgebenS_R234(Spiel s, int runde){
		String farbek11= "null";
		String farbek12= "null";
		String farbebank1= "null";
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



		if (runde == 2) {
			/**weitere Karte von Spieler1 anzeigen.*/
			switch (farbek11) {
			case "pik":
				cbo.karte3Spieler1.setIcon(cbo.pik[nummerk11]);
				break;
			case "herz":
				cbo.karte3Spieler1.setIcon(cbo.herz[nummerk11]);
				break;
			case "kreuz":
				cbo.karte3Spieler1.setIcon(cbo.kreuz[nummerk11]);
				break;
			case "karo":
				cbo.karte3Spieler1.setIcon(cbo.karo[nummerk11]);
				break;
			case "null":
				break;
			}


			//Karte Spieler2
			switch (farbek12) {
			case "pik":
				cbo.karte3Spieler2.setIcon(cbo.pik[nummerk12]);
				break;
			case "herz":
				cbo.karte3Spieler2.setIcon(cbo.herz[nummerk12]);
				break;
			case "kreuz":
				cbo.karte3Spieler2.setIcon(cbo.kreuz[nummerk12]);
				break;
			case "karo":
				cbo.karte3Spieler2.setIcon(cbo.karo[nummerk12]);
				break;
			case "null":
				break;
			}


			//Karte Bank
			switch (farbebank1) {
			case "pik":
				cbo.karte3Bank.setIcon(cbo.pik[nummerk1b]);
				break;
			case "herz":
				cbo.karte3Bank.setIcon(cbo.herz[nummerk1b]);
				break;
			case "kreuz":
				cbo.karte3Bank.setIcon(cbo.kreuz[nummerk1b]);
				break;
			case "karo":
				cbo.karte3Bank.setIcon(cbo.karo[nummerk1b]);
				break;
			case "null":
				break;
			} 
			cbo.karte1Spieler1.setVisible(true);
			cbo.karte2Spieler1.setVisible(true);
			cbo.karte3Spieler1.setVisible(true);

			cbo.karte1Spieler2.setVisible(true);
			cbo.karte2Spieler2.setVisible(true);
			cbo.karte3Spieler2.setVisible(true);

			cbo.karte1Bank.setVisible(true);
			cbo.karte1Bank.setIcon(cbo.rueckseite);
			cbo.karte2Bank.setVisible(true);
			cbo.karte3Bank.setVisible(true);}
		else if (runde == 3) {
			/**weitere Karte von Spieler1 anzeigen.*/
			switch (farbek11) {
			case "pik":
				cbo.karte4Spieler1.setIcon(cbo.pik[nummerk11]);
				break;
			case "herz":
				cbo.karte4Spieler1.setIcon(cbo.herz[nummerk11]);
				break;
			case "kreuz":
				cbo.karte4Spieler1.setIcon(cbo.kreuz[nummerk11]);
				break;
			case "karo":
				cbo.karte4Spieler1.setIcon(cbo.karo[nummerk11]);
				break;
			case "null":
				break;
			}


			//Karte Spieler2
			switch (farbek12) {
			case "pik":
				cbo.karte4Spieler2.setIcon(cbo.pik[nummerk12]);
				break;
			case "herz":
				cbo.karte4Spieler2.setIcon(cbo.herz[nummerk12]);
				break;
			case "kreuz":
				cbo.karte4Spieler2.setIcon(cbo.kreuz[nummerk12]);
				break;
			case "karo":
				cbo.karte4Spieler2.setIcon(cbo.karo[nummerk12]);
				break;
			case "null":
				break;
			}


			//Karte Bank
			switch (farbebank1) {
			case "pik":
				cbo.karte4Bank.setIcon(cbo.pik[nummerk1b]);
				break;
			case "herz":
				cbo.karte4Bank.setIcon(cbo.herz[nummerk1b]);
				break;
			case "kreuz":
				cbo.karte4Bank.setIcon(cbo.kreuz[nummerk1b]);
				break;
			case "karo":
				cbo.karte4Bank.setIcon(cbo.karo[nummerk1b]);
				break;
			case "null":
				break;
			} 

			cbo.karte1Spieler1.setVisible(true);
			cbo.karte2Spieler1.setVisible(true);
			cbo.karte3Spieler1.setVisible(true);
			cbo.karte4Spieler1.setVisible(true);

			cbo.karte1Spieler2.setVisible(true);
			cbo.karte2Spieler2.setVisible(true);
			cbo.karte3Spieler2.setVisible(true);
			cbo.karte4Spieler2.setVisible(true);

			cbo.karte1Bank.setVisible(true);
			cbo.karte1Bank.setIcon(cbo.rueckseite);
			cbo.karte2Bank.setVisible(true);
			cbo.karte3Bank.setVisible(true);
			cbo.karte4Bank.setVisible(true);}
		else if (runde == 4) {

			switch (farbek11) {
			case "pik":
				cbo.karte5Spieler1.setIcon(cbo.pik[nummerk11]);
				break;
			case "herz":
				cbo.karte5Spieler1.setIcon(cbo.herz[nummerk11]);
				break;
			case "kreuz":
				cbo.karte5Spieler1.setIcon(cbo.kreuz[nummerk11]);
				break;
			case "karo":
				cbo.karte5Spieler1.setIcon(cbo.karo[nummerk11]);
				break;
			case "null":
				break;
			}


			//Karte Spieler2
			switch (farbek12) {
			case "pik":
				cbo.karte5Spieler2.setIcon(cbo.pik[nummerk12]);
				break;
			case "herz":
				cbo.karte5Spieler2.setIcon(cbo.herz[nummerk12]);
				break;
			case "kreuz":
				cbo.karte5Spieler2.setIcon(cbo.kreuz[nummerk12]);
				break;
			case "karo":
				cbo.karte5Spieler2.setIcon(cbo.karo[nummerk12]);
				break;
			case "null":
				break;
			}


			//Karte Bank
			switch (farbebank1) {
			case "pik":
				cbo.karte5Bank.setIcon(cbo.pik[nummerk1b]);
				break;
			case "herz":
				cbo.karte5Bank.setIcon(cbo.herz[nummerk1b]);
				break;
			case "kreuz":
				cbo.karte5Bank.setIcon(cbo.kreuz[nummerk1b]);
				break;
			case "karo":
				cbo.karte5Bank.setIcon(cbo.karo[nummerk1b]);
				break;
			case "null":
				break;
			} 
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
			cbo.karte1Bank.setIcon(cbo.rueckseite);
			cbo.karte2Bank.setVisible(true);
			cbo.karte3Bank.setVisible(true);
			cbo.karte4Bank.setVisible(true); 
			cbo.karte5Bank.setVisible(true);}
	}

	public void dealerKarteAufdecken (Spiel s) {
		String farbebank1= s.DeckDealer.get(0).getFarbe();
		int nummerk1b = s.DeckDealer.get(0).getName();
		//Karte1 Bank ausw‰hlen.
		switch (farbebank1) {
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
		}
		cbo.karte1Bank.setVisible(true);
	}

	public void jetonsZuHitundStay() {
		cbo.buttonLogin.setVisible(false);
		cbo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		//cbo.ueberschriftC.setVisible(false);
		cbo.buttonZurueck.setVisible(false);

		//Loginfenster
		cbo.labelBenutzernameC.setVisible(false);
		cbo.labelPasswortC.setVisible(false);
		cbo.buttonstart.setVisible(false);
		cbo.userText.setVisible(false);
		cbo.passwordText.setVisible(false);
		cbo.buttonZurueck.setVisible(false);

		//Registrierfenster
		cbo.labelBenutzernameCErstellenC.setVisible(false);
		cbo.labelPasswortC1C.setVisible(false);
		cbo.labelPasswortC2C.setVisible(false);
		cbo.buttonRegistrierenAbschlieﬂen.setVisible(false);
		cbo.userRegistText.setVisible(false);
		cbo.passwordText1.setVisible(false);
		cbo.passwordText2.setVisible(false);

		//IPAdressefenster:
		cbo.buttonIPAdresseBestaetigen.setVisible(false);
		cbo.labelipadresseC.setVisible(false);
		cbo.ipadresseText.setVisible(false);

		//Portfenster: 
		cbo.buttonPortBest‰tigen.setVisible(false);
		cbo.labelportC.setVisible(false);
		cbo.portText.setVisible(false);

		//Spielfenster: 
		cbo.buttonEinsatz.setVisible(false);
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
		cbo.einsatzSpieler1C.setVisible(true);
		cbo.einsatzSpieler2C.setVisible(true);
		//cbo.kontostandSpieler1C.setVisible(true);
		cbo.kontostandSpieler2.setVisible(true); 
		cbo.buttonEinsatzbestaetigen.setVisible(false);
		cbo.buttonHit.setVisible(true);
		cbo.buttonStay.setVisible(true);


		cbo.kartenwertSpieler1.setVisible(true);
		cbo.kartenwertSpieler2.setVisible(true);
		cbo.kartenwertDealer.setVisible(false);
	}

	public void rundeZuAuswerten() {
		cbo.buttonLogin.setVisible(false);
		cbo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		//cbo.ueberschriftC.setVisible(false);
		cbo.buttonZurueck.setVisible(false);

		//Loginfenster
		cbo.labelBenutzernameC.setVisible(false);
		cbo.labelPasswortC.setVisible(false);
		cbo.buttonstart.setVisible(false);
		cbo.userText.setVisible(false);
		cbo.passwordText.setVisible(false);
		cbo.buttonZurueck.setVisible(false);

		//Registrierfenster
		cbo.labelBenutzernameCErstellenC.setVisible(false);
		cbo.labelPasswortC1C.setVisible(false);
		cbo.labelPasswortC2C.setVisible(false);
		cbo.buttonRegistrierenAbschlieﬂen.setVisible(false);
		cbo.userRegistText.setVisible(false);
		cbo.passwordText1.setVisible(false);
		cbo.passwordText2.setVisible(false);

		//IPAdressefenster:
		cbo.buttonIPAdresseBestaetigen.setVisible(false);
		cbo.labelipadresseC.setVisible(false);
		cbo.ipadresseText.setVisible(false);

		//Portfenster: 
		cbo.buttonPortBest‰tigen.setVisible(false);
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
		cbo.einsatzSpieler1C.setVisible(false);
		cbo.einsatzSpieler2C.setVisible(false);
		cbo.kontostandSpieler1.setVisible(true);
		cbo.kontostandSpieler2.setVisible(true); 
		cbo.buttonEinsatzbestaetigen.setVisible(false);
		cbo.buttonHit.setVisible(false);
		cbo.buttonStay.setVisible(false);
		cbo.kartenwertSpieler1.setVisible(true);
		cbo.kartenwertSpieler2.setVisible(true);
		cbo.kartenwertDealer.setVisible(false);
		cbo.nachrichtS1C.setText(ausgabetextS1C);
		cbo.nachrichtS2C.setText(ausgabetextS2C);
		System.out.println("Hier sollte angzeigt werden: "+ausgabetextS1C);
		cbo.nachrichtS1C.setVisible(true);
		cbo.nachrichtS2C.setVisible(true);
		cbo.buttonNaechsteRunde.setVisible(false);
	}

	public void auswertenZuEinsatz() {
		cbo.buttonLogin.setVisible(false);
		cbo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		//cbo.ueberschriftC.setVisible(false);
		cbo.buttonZurueck.setVisible(false);

		//Loginfenster
		cbo.labelBenutzernameC.setVisible(false);
		cbo.labelPasswortC.setVisible(false);
		cbo.buttonstart.setVisible(false);
		cbo.userText.setVisible(false);
		cbo.passwordText.setVisible(false);
		cbo.buttonZurueck.setVisible(false);

		//Registrierfenster
		cbo.labelBenutzernameCErstellenC.setVisible(false);
		cbo.labelPasswortC1C.setVisible(false);
		cbo.labelPasswortC2C.setVisible(false);
		cbo.buttonRegistrierenAbschlieﬂen.setVisible(false);
		cbo.userRegistText.setVisible(false);
		cbo.passwordText1.setVisible(false);
		cbo.passwordText2.setVisible(false);

		//IPAdressefenster:
		cbo.buttonIPAdresseBestaetigen.setVisible(false);
		cbo.labelipadresseC.setVisible(false);
		cbo.ipadresseText.setVisible(false);

		//Portfenster: 
		cbo.buttonPortBest‰tigen.setVisible(false);
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
		cbo.kontostandSpieler1.setVisible(true);
		cbo.kontostandSpieler2.setVisible(true); 
		cbo.buttonEinsatzbestaetigen.setVisible(false);
		cbo.buttonHit.setVisible(false);
		cbo.buttonStay.setVisible(false);
		cbo.kartenwertSpieler1.setVisible(false);
		cbo.kartenwertSpieler2.setVisible(false);
		cbo.kartenwertDealer.setVisible(false);
		cbo.nachrichtS1C.setVisible(false);
		cbo.nachrichtS2C.setVisible(false);
		cbo.buttonNaechsteRunde.setVisible(false);
	}


	public void setSpielernameClient() {
		cbo.labelSpieler2C.setText(cHandler.benutzername);
	}

	public void setSpielernameServer(String str) {
		cbo.labelSpieler1C.setText(str);
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










