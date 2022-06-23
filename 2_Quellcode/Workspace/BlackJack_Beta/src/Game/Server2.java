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
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Vector;
import java.net.InetAddress;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

//import jdk.internal.misc.FileSystemOption;



public class Server2 implements Runnable {

	private String ip = "localhost";
	private int port = 22222;
	private Scanner scanner = new Scanner(System.in);

	private Thread thread;

	private boolean close = false; //Schließt wenn true

	public String ausgabetextS1; 
	public String ausgabetextS2; 


	private Socket socket;
	private DataOutputStream dos;
	
	private DataInputStream dis;
	

	private ServerSocket serverSocket;

	int auswertStatSp1;
	int auswertStatSp2;

	static boolean wartenAufSpielerClient = false; 
	static boolean wartenAufSpielerServer = false; 


	

	private boolean yourTurn = false; //bin ich an der Reihe?
	private boolean client = true;	// Bin ich der Client?
	private boolean host = false; // Bin ich der Host?
	private boolean accepted = false;	//Bin ich schon mit einem Server verbunden?

	
	int gesetztS; 
	int gesetztC;
	static int  hitostay;

	static boolean klicks = false; 
	public static int swischespeicher;
	int kontomax = 0;
	Spieler aktuellerbenutzer;
	boolean anmelden = false;







	public Server2() {

		InetAddress ia;
		String serverIP;
		try { 
			ia = InetAddress.getLocalHost(); 
			String str = ia.getHostAddress(); 
			System.out.println(str); 
			serverIP =  ia.getHostAddress(); 
			bo.labelIPAdresse.setText(serverIP);
			ip = serverIP;
		} catch (Exception e) { 
			e.printStackTrace(); 
		}

		port = 8080;
		

		if (!verbunden())
			initialisiereServer();



		thread = new Thread(this, "BlackJack");


		thread.start();


	}

	public void run() {
		while (true) {

			//Programmcode welcher im "Thread" ausgeführt wird.

			if (!client && !accepted) {
				
				wartenAufServer();
			
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
			
			socket = serverSocket.accept();
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			accepted = true;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/** Erstellung Socket mit Verbindung*/
	private boolean verbunden() {
		try {
			socket = new Socket(ip, port);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			accepted = true;
		} catch (IOException e) {
			
			return false;
		}
		
		return true;
	}
	/** Server initialisieren*/
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
	/** Spielablauf*/
	private void aktion()  {

		/**Erstellen Spiel*/
		Spiel server = new Spiel();
		/**erstellen Karten Deck*/
		server.createDeck();
		Spielkarten Server = new Spielkarten();

		Spieler playerS = new Spieler ("jhg","76554");
		kontomax = playerS.getKontostand();


		newgame: while(true) {
			/**Reset Array-Lists, Variabeln (Wert-Abfragen), Rundenzähler*/

			server.DeckSpieler1.clear();
			server.DeckSpieler2.clear();
			server.DeckDealer.clear();
			server.wertSpieler1 =0;
			server.wertSpieler2 =0;
			server.wertDealer =0;
			int z=2;

			/** Warteschleife Einsatz setzen */

			while (!klicks) {
			
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} klicks = false;
			/**Gesetzter Beitrag auf Screen anzeigen von Spieler 1 (Server)*/
			Spiel.setGesetztSpieler1(gesetztS);
			gesetztS = 0;
			playerS.abbuchen(Spiel.getGesetztSpieler1());

			/**Gesetzt-Beitrag übermitteln von Spieler 1 (Server) an Client*/
			try {
				dos.writeInt(Spiel.getGesetztSpieler1());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/**Gesetzter Betrag vom Spieler 2 (Client) empfangen:*/
			try {
				gesetztC = dis.readInt();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/**Gesetzter Beitrag auf Screen anzeigen von Spieler 2 (Client)*/
			Spiel.setGesetztSpieler2(gesetztC);
			gesetztC = 0;
			einsatzAnzeigenGegenspieler();

			/** Ziehen der ersten 2 Karten*/
			//1.Karte
			/**Karte für Spieler 1 ziehen*/
			server.austeilenKarteSp1();

			/**Karte für Spieler 2 ziehen:*/
			server.austeilenKarteSp2();

			/**Karten für Dealer ziehen:*/
			server.austeilenKarteDealer();
			//2. Karte:
			/**Karte für Spieler 1 ziehen*/
			server.austeilenKarteSp1();
			/**Karte für Spieler 2 ziehen:*/
			server.austeilenKarteSp2();
			/**Karten für Dealer ziehen:*/
			server.austeilenKarteDealer();

			/**Karten verschicken an SPieler 2 (Client)*/
			/**Karten verschicken Spieler 1 (server) */

			for (int i = 0;i<server.DeckSpieler1.size();i++) {
				try {
					dos.writeUTF(server.DeckSpieler1.get(i).getFarbe());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					dos.writeInt(server.DeckSpieler1.get(i).getName());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					dos.writeInt(server.DeckSpieler1 .get(i).getWert());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}

			/**Karte verschicken Spieler 2 (Server)*/

			for (int i = 0;i<server.DeckSpieler2.size();i++) {
				try {
					dos.writeUTF(server.DeckSpieler2.get(i).getFarbe());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					dos.writeInt(server.DeckSpieler2.get(i).getName());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					dos.writeInt(server.DeckSpieler2 .get(i).getWert());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}


			/**Karte verschicken Dealer*/

			for (int i = 0;i<server.DeckDealer.size();i++) {
				try {
					dos.writeUTF(server.DeckDealer.get(i).getFarbe());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					dos.writeInt(server.DeckDealer.get(i).getName());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					dos.writeInt(server.DeckDealer .get(i).getWert());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}

			/** Karten und Spielerwert anzeigen auf Screen*/
			kartenausgebenS_R1(server);
			kartenwertanzeigen(server);


			/**BlackJack und Überkauft-Abfrage*/
			server.checkBJSpieler1();
			server.checkBJSpieler2();
			server.checkBJDealer();


			/** verschicken Status Spieler win?*/
			try {
				dos.writeBoolean(server.winSpieler1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();  }  

			try {
				dos.writeBoolean(server.winSpieler2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();  }  

			try {
				dos.writeBoolean(server.winDealer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();  }  

			/** verschicken Status Spieler lose....*/


			/** verschicken Status Spieler lose?*/
			try {
				dos.writeBoolean(server.loseSpieler1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();  }  

			try {
				dos.writeBoolean(server.loseSpieler2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();  }  

			try {
				dos.writeBoolean(server.loseDealer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();  } 

			/**Wenn BJ = true oder Spieler überkauft,werte aus und mach neues Spiel (gehe zu newgame)*/
			while(server.winSpieler1 || server.winSpieler2 || server.winDealer||server.loseSpieler1||server.loseSpieler2||server.loseDealer) {

				/**auswerten*/
				auswerten(server);
				/**verschicken Auswerte Status Spieler 1*/
				try {
					dos.writeInt(auswertStatSp1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();  }  

				/**verschicken Auswerte Status Spieler 2:*/

				try {
					dos.writeInt(auswertStatSp2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(); } 

				/** Auswerte Status auf Screen anzeigen*/
				gewinnbenachrichtung(server);
				rundeZuAuswerten();
				try {
					Thread.sleep(15000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				auswertenZuEinsatz();

				continue newgame;}

			/**Wenn BJ = false oder nicht überkauft, frag nach Hit oder Stay*/
			newcard: while(!server.winSpieler1 && !server.winSpieler2 && !server.winDealer&& !server.loseSpieler1 && !server.loseSpieler2 && !server.loseDealer) {


				boolean hit1=false;
				boolean hit2=false;
				if (z>5) {
					break newcard;
				}

				/** Wählen hit oder stay*/
				while (!klicks) {
					
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
				hitostay =0;
				/** verschicken hit1*/
				try {
					dos.writeBoolean(hit1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();  }

				/**Spieler 2 Hit/Stay? empfangen"*/
				try {
					hit2 = dis.readBoolean(); 
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				/**wenn kein Spieler eine Karte aufnehmen möchte*/
				while((!hit1 && !hit2)) {

					/**Dealer Karte wenn ja werte der Karte an CLient schicken*/

					if (server.wertDealer()<17) {
						server.austeilenKarteDealer();
						int s = server.DeckDealer.size()-1;
						try {
							dos.writeUTF(server.DeckDealer.get(s).getFarbe());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							dos.writeInt(server.DeckDealer.get(s).getName());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							dos.writeInt(server.DeckDealer .get(s).getWert());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	

					}
					/** Karten und Spielerwert anzeigen auf Screen*/
					kartenwertanzeigen(server);
					kartenausgebenS_R234(server, z); //Karte anzeigen.

					/**BlackJack und Überkauft Abfrage*/
					server.checkBJSpieler1();
					server.checkBJSpieler2();
					server.checkBJDealer();

					/**Auswerten*/
					auswerten(server);
					/**verschicken Auswerte Status Spieler 1*/
					try {
						dos.writeInt(auswertStatSp1);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();  }  

					/**verschicken Auswerte Status Spieler 2:*/

					try {
						dos.writeInt(auswertStatSp2);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace(); } 

					/** Auswerte Status auf Screen anzeigen*/
					gewinnbenachrichtung(server);
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

				/** wenn einer oder beide hit drücken*/
				while((hit1 || hit2)) {

					/** Wenn Spieler 2 eine Karte will*/
					if (hit1) {server.austeilenKarteSp1();

					/**Karte verschicken Spieler 1*/

					int i = server.DeckSpieler1.size()-1;
					try {
						dos.writeUTF(server.DeckSpieler1.get(i).getFarbe());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						dos.writeInt(server.DeckSpieler1.get(i).getName());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						dos.writeInt(server.DeckSpieler1 .get(i).getWert());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					}
					/** Wenn Spieler 2 eine Karte will*/
					if (hit2) {
						server.austeilenKarteSp2();
						/**Karte verschicken Spieler 1*/
						int j = server.DeckSpieler2.size()-1;
						try {
							dos.writeUTF(server.DeckSpieler2.get(j).getFarbe());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							dos.writeInt(server.DeckSpieler2.get(j).getName());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							dos.writeInt(server.DeckSpieler2 .get(j).getWert());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
					}


					/**Dealer Karte wenn ja werte der Karte an CLient schicken*/

					if (server.wertDealer()<17) {
						server.austeilenKarteDealer();
						int s = server.DeckDealer.size()-1;
						try {
							dos.writeUTF(server.DeckDealer.get(s).getFarbe());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							dos.writeInt(server.DeckDealer.get(s).getName());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							dos.writeInt(server.DeckDealer .get(s).getWert());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	

					}
					/** Spielerwert anzeigen und Karte ausgeben*/
					kartenwertanzeigen(server);
					kartenausgebenS_R234(server, z);

					/** Wenn beide Spieler eine Karte  wollen*/
					if (hit1 && hit2) {
						/**BlackJack und Überkauft Abfrage*/
						server.checkBJSpieler1();
						server.checkBJSpieler2();
						server.checkBJDealer();

						/** verschicken Status Spieler win?*/
						try {
							dos.writeBoolean(server.winSpieler1);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();  }  

						try {
							dos.writeBoolean(server.winSpieler2);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();  }  

						try {
							dos.writeBoolean(server.winDealer);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();  }  

						/** verschike Status Spieler lose....*/

						try {
							dos.writeBoolean(server.loseSpieler1);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();  }  

						try {
							dos.writeBoolean(server.loseSpieler2);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();  }  

						try {
							dos.writeBoolean(server.loseDealer);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();  } 


						/** wenn ein Spieler BJ hat oder sich überkauft dann auswerten*/

						if(server.winSpieler1 || server.winSpieler2 || server.winDealer||server.loseSpieler1||server.loseSpieler2||server.loseDealer) {
							/**Auswerten*/
							auswerten(server);
							/**verschicken Status Spieler 1*/
							try {
								dos.writeInt(auswertStatSp1);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();  }  

							/**verschicken Status Spieler 2:*/
							try {
								dos.writeInt(auswertStatSp2);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace(); }

							/**Auswertung anzeigen*/
							gewinnbenachrichtung(server);


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
					/** wenn einer eine Karte will und der andere nicht mehr dann soll die Runde noch zu ende geführt werden*/
					if ((hit1 && !hit2) || (!hit1 && hit2)) {
						/** Auswerten*/
						auswerten(server);
						/**verschicken Status Spieler 1*/
						try {
							dos.writeInt(auswertStatSp1);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();  }  

						/**verschicken Status Spieler 2:*/

						try {
							dos.writeInt(auswertStatSp2);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace(); } 
						/** Auswerten Status auf Screen anzeigen*/
						gewinnbenachrichtung(server);
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

					z++; //Zähler, Anzahl der Spielzüge
					continue newcard;

				}

			}

			continue newgame;

		}
	}




	/**Auswerten der Spieler + Dealer*/
	void auswerten(Spiel server) {

		/**Spieler Status Spieler 1*/
		auswertStatSp1=server.auswertenS1();
		auswertStatSp2=server.auswertenS2();

	}

	/**Gewninnbenachrichtiung von Spiel s*/
	void gewinnbenachrichtung (Spiel s){ 

		/** Spieler 1*/
		if (auswertStatSp1 == 0)
			ausgabetextS1 = "BlackJack";

		if (auswertStatSp1 == 1)
			ausgabetextS1 = "Gewonnen";

		if (auswertStatSp1 == 2)
			ausgabetextS1 = "Verloren";

		if (auswertStatSp1 == 3)
			ausgabetextS1 = "Unentschieden";

		/** Spieler 2*/
		if (auswertStatSp2 == 0)
			ausgabetextS2 = "BlackJack";

		if (auswertStatSp2 == 1)
			ausgabetextS2 = "Gewonnen";

		if (auswertStatSp2 == 2)
			ausgabetextS2 = "Verloren";

		if (auswertStatSp2 == 3)
			ausgabetextS2 = "Unentschieden";

		/** 1.Karte Dealer umdrehen*/
		dealerKarteAufdecken(s);


	}
	/** abbuchen Konto*/
	boolean abbuchungOK(int m){

		if ((swischespeicher)>kontomax) {
			swischespeicher -= m;
			return false; }
		else 
			return true;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server2 gameh = new Server2();

	}
	//Grafische Programmierung


	ActionHandler aHandler = new ActionHandler(this); 
	Benutzeroberfläche bo = new Benutzeroberfläche(this);
	Spielkarten sk = new Spielkarten();


	public void startZuIP() {
		bo.buttonStartSpiel.setVisible(false);
		bo.buttonAbbrechenSpiel.setVisible(false);

		//Gemeinsame
		bo.ueberschrift.setVisible(false);
		bo.buttonZurueck.setVisible(true);

		//IPAdressefenster:
		bo.buttonIPAdresseBestaetigen.setVisible(true);
		bo.labelipadresse.setVisible(true);
		bo.labelIPAdresse.setVisible(true);
	}


	public void IPZuAuswahl() {
		//Startbildschirm
		bo.buttonLogin.setVisible(true);
		bo.buttonRegistrieren.setVisible(true);
		bo.buttonZurueckZuStart.setVisible(true);

		//Gemeinsame
		bo.ueberschrift.setVisible(false);
		bo.buttonZurueck.setVisible(false);


		//IPAdressefenster:
		bo.buttonIPAdresseBestaetigen.setVisible(false);
		bo.labelipadresse.setVisible(false);
		bo.labelIPAdresse.setVisible(false);
	}



	public void auswahlZuLogin() {
		//Startbildschirm
		bo.buttonLogin.setVisible(false);
		bo.buttonRegistrieren.setVisible(false);
		bo.buttonZurueckZuStart.setVisible(false);

		//Gemeinsame
		bo.ueberschrift.setVisible(false);
		bo.buttonZurueck.setVisible(true);
		bo.logo.setVisible(false);

		//Loginfenster
		bo.labelBenutzername.setVisible(true);
		bo.labelPasswort.setVisible(true);
		bo.buttonstart.setVisible(true);
		bo.userText.setVisible(true);
		bo.passwordText.setVisible(true);
		bo.buttonZurueck.setVisible(true);
		bo.anmeldetext.setVisible(true);

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
		bo.buttonZurueckZuStart.setVisible(false);


		//Gemeinsame
		bo.ueberschrift.setVisible(false);
		bo.buttonZurueck.setVisible(true);
		bo.logo.setVisible(false);

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
		bo.registrierungtext.setVisible(true);

	}

	public void logRegZuAuswahl () {
		//Startbildschirm
		bo.buttonLogin.setVisible(true);
		bo.buttonRegistrieren.setVisible(true);

		//Gemeinsame
		bo.ueberschrift.setVisible(true);
		bo.buttonZurueck.setVisible(false);
		bo.logo.setVisible(true);

		//Loginfenster
		bo.labelBenutzername.setVisible(false);
		bo.labelPasswort.setVisible(false);
		bo.buttonstart.setVisible(false);
		bo.userText.setVisible(false);
		bo.passwordText.setVisible(false);
		bo.anmeldetext.setVisible(false);


		//Registrierfenster
		bo.labelBenutzernameErstellen.setVisible(false);
		bo.labelPasswort1.setVisible(false);
		bo.labelPasswort2.setVisible(false);
		bo.buttonRegistrierenAbschließen.setVisible(false);
		bo.userRegistText.setVisible(false);
		bo.passwordText1.setVisible(false);
		bo.passwordText2.setVisible(false);
		bo.registrierungtext.setVisible(false);
	}

	public void zurückZuStart() {
		//Startbildschirm
		bo.buttonStartSpiel.setVisible(true);
		bo.buttonAbbrechenSpiel.setVisible(true);
		bo.buttonZurueckZuStart.setVisible(false);

		bo.buttonLogin.setVisible(false);
		bo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		bo.ueberschrift.setVisible(false);
		bo.buttonZurueck.setVisible(false);
		bo.logo.setVisible(true);

		//Loginfenster
		bo.labelBenutzername.setVisible(false);
		bo.labelPasswort.setVisible(false);
		bo.buttonstart.setVisible(false);
		bo.userText.setVisible(false);
		bo.passwordText.setVisible(false);
		bo.anmeldetext.setVisible(false);


		//Registrierfenster
		bo.labelBenutzernameErstellen.setVisible(false);
		bo.labelPasswort1.setVisible(false);
		bo.labelPasswort2.setVisible(false);
		bo.buttonRegistrierenAbschließen.setVisible(false);
		bo.userRegistText.setVisible(false);
		bo.passwordText1.setVisible(false);
		bo.passwordText2.setVisible(false);
		bo.registrierungtext.setVisible(false);
	}


	public void logRegZuEinsatz() {
		bo.buttonLogin.setVisible(false);
		bo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		bo.ueberschrift.setVisible(false);
		bo.buttonZurueck.setVisible(false);
		bo.hintergrundmenu.setVisible(false);
		bo.panelstart.setVisible(false);

		//Loginfenster
		bo.labelBenutzername.setVisible(false);
		bo.labelPasswort.setVisible(false);
		bo.buttonstart.setVisible(false);
		bo.userText.setVisible(false);
		bo.passwordText.setVisible(false);
		bo.buttonZurueck.setVisible(false);
		bo.anmeldetext.setVisible(false);

		//Registrierfenster
		bo.labelBenutzernameErstellen.setVisible(false);
		bo.labelPasswort1.setVisible(false);
		bo.labelPasswort2.setVisible(false);
		bo.buttonRegistrierenAbschließen.setVisible(false);
		bo.userRegistText.setVisible(false);
		bo.passwordText1.setVisible(false);
		bo.passwordText2.setVisible(false);
		bo.registrierungtext.setVisible(false);

		//IPAdressefenster:
		bo.buttonIPAdresseBestaetigen.setVisible(false);
		bo.labelipadresse.setVisible(false);
		bo.ipadresseText.setVisible(false);

		//Portfenster: 
		bo.buttonPortBestätigen.setVisible(false);
		bo.labelport.setVisible(false);
		bo.portText.setVisible(false);

		//Spielfenster:
		bo.menuleiste.setVisible(true);
		bo.buttonEinsatz.setVisible(true);
		bo.labelSpieler1.setVisible(true);
		bo.labelSpieler2.setVisible(true);
		bo.ueberschriftSpiel.setVisible(true);
		bo.unterueberschriftSpiel.setVisible(true);
		bo.labelBank.setVisible(true);
		bo.bedienfeld.setVisible(true);
		bo.labelipadresse.setVisible(false);
		bo.labelIPAdresse.setVisible(false);

		//Kartenfenster
		bo.kartenfeldS1.setVisible(true);
		bo.kartenfeldS1g.setVisible(true);
		bo.kartenfeldS2.setVisible(true);
		bo.kartenfeldS2g.setVisible(true);
		bo.kartenfeldbank.setVisible(true);
		bo.kartenfeldbankg.setVisible(true);

		bo.einsatzSpieler1.setVisible(true);
		bo.einsatzSpieler2.setVisible(true);

		bo.kontostandSpieler1.setVisible(true);
		bo.kontostandSpieler2.setVisible(true);





	}

	public void einsatzZuJetons() {

		bo.buttonLogin.setVisible(false);
		bo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		bo.ueberschrift.setVisible(false);
		bo.buttonZurueck.setVisible(false);

		//Loginfenster
		bo.labelBenutzername.setVisible(false);
		bo.labelPasswort.setVisible(false);
		bo.buttonstart.setVisible(false);
		bo.userText.setVisible(false);
		bo.passwordText.setVisible(false);
		bo.buttonZurueck.setVisible(false);

		//Registrierfenster
		bo.labelBenutzernameErstellen.setVisible(false);
		bo.labelPasswort1.setVisible(false);
		bo.labelPasswort2.setVisible(false);
		bo.buttonRegistrierenAbschließen.setVisible(false);
		bo.userRegistText.setVisible(false);
		bo.passwordText1.setVisible(false);
		bo.passwordText2.setVisible(false);

		//IPAdressefenster:
		bo.buttonIPAdresseBestaetigen.setVisible(false);
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
		//bo.einsatzSpieler1.setVisible(true);
		//bo.einsatzSpieler2.setVisible(true);
		//bo.kontostandSpieler1.setVisible(true);
		//bo.kontostandSpieler2.setVisible(true); 
		bo.buttonEinsatzbestaetigen.setVisible(true);  

		bo.kartenwertSpieler1.setVisible(true);
		bo.kartenwertSpieler2.setVisible(true);
		bo.kartenwertDealer.setVisible(true);

		bo.karte1Spieler1.setVisible(false);
		bo.karte2Spieler1.setVisible(false);
		bo.karte3Spieler1.setVisible(false); 
		bo.karte4Spieler1.setVisible(false); 
		bo.karte5Spieler1.setVisible(false); 
		bo.karte1Spieler2.setVisible(false);
		bo.karte2Spieler2.setVisible(false);
		bo.karte3Spieler2.setVisible(false);
		bo.karte4Spieler2.setVisible(false);
		bo.karte5Spieler2.setVisible(false);
		bo.karte1Bank.setVisible(false);
		bo.karte2Bank.setVisible(false);
		bo.karte3Bank.setVisible(false);
		bo.karte4Bank.setVisible(false);
		bo.karte5Bank.setVisible(false);



	}


	public void kartenwertanzeigen(Spiel s) {

		bo.kartenwertSpieler1.setText("Kartenwert Spieler 1: "+Integer.toString(s.wertSpieler1()));
		bo.kartenwertSpieler2.setText("Kartenwert Spieler 2: "+Integer.toString(s.wertSpieler2()));
		bo.kartenwertDealer.setText("Kartenwert Spieler 2: "+Integer.toString(s.wertDealer()));

	}

	/*
		public void kontostandanzeigen (Spiel s) {
			bo.kontostandSpieler1.setText(("Kontostand beträgt"+ Integer.toString(playerS.getKontostand());
			bo.kontostandSpieler2.setText(("Kontostand beträgt" + Integer.toString(playerC.getKontostand());
		}
	 */





	public void jeton10() {
		int j10 = 10;
		if(abbuchungOK(10)) {
			String j11 = Integer.toString(j10);
			bo.einsatzausgabeSpieler1.setText("Der Einsatz beträgt:" +swischespeicher);
			bo.einsatzausgabeSpieler1.setVisible(true);

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

			bo.einsatzausgabeSpieler1.setText("Der Einsatz beträgt:" +swischespeicher);
			bo.einsatzausgabeSpieler1.setVisible(true);

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
			bo.einsatzausgabeSpieler1.setText("Der Einsatz beträgt:" +swischespeicher);
			bo.einsatzausgabeSpieler1.setVisible(true);

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
			bo.einsatzausgabeSpieler1.setText("Der Einsatz beträgt:" +swischespeicher);
			bo.einsatzausgabeSpieler1.setVisible(true); 
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

	public void einsatzAnzeigenGegenspieler() {
		bo.einsatzausgabeSpieler2.setVisible(true);

		bo.einsatzausgabeSpieler2.setText("Der Einsatz beträgt:" + Spiel.getGesetztSpieler2());
	}


	//Kartenauswahl



	public void kartenausgebenS_R1(Spiel s){

		bo.karte3Spieler1.setIcon(null);
		bo.karte3Spieler2.setIcon(null);
		bo.karte3Bank.setIcon(null);
		bo.karte4Spieler1.setIcon(null);
		bo.karte4Spieler2.setIcon(null);
		bo.karte4Bank.setIcon(null);
		bo.karte5Spieler1.setIcon(null);
		bo.karte5Spieler2.setIcon(null);
		bo.karte5Bank.setIcon(null);

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
			bo.karte1Spieler1.setIcon(bo.pik[nummerk11]);
			break;
		case "herz":
			bo.karte1Spieler1.setIcon(bo.herz[nummerk11]);
			break;
		case "kreuz":
			bo.karte1Spieler1.setIcon(bo.kreuz[nummerk11]);
			break;
		case "karo":
			bo.karte1Spieler1.setIcon(bo.karo[nummerk11]);
			break;
		}

		//Karte2 Spieler1
		switch (farbek21) {
		case "pik":
			bo.karte2Spieler1.setIcon(bo.pik[nummerk21]);
			break;
		case "herz":
			bo.karte2Spieler1.setIcon(bo.herz[nummerk21]);
			break;
		case "kreuz":
			bo.karte2Spieler1.setIcon(bo.kreuz[nummerk21]);
			break;
		case "karo":
			bo.karte2Spieler1.setIcon(bo.karo[nummerk21]);
			break;
		}


		//Karte1 Spieler2
		switch (farbek12) {
		case "pik":
			bo.karte1Spieler2.setIcon(bo.pik[nummerk12]);
			break;
		case "herz":
			bo.karte1Spieler2.setIcon(bo.herz[nummerk12]);
			break;
		case "kreuz":
			bo.karte1Spieler2.setIcon(bo.kreuz[nummerk12]);
			break;
		case "karo":
			bo.karte1Spieler2.setIcon(bo.karo[nummerk12]);
			break;
		}


		//Karte2 Spieler2
		switch (farbek22) {
		case "pik":
			bo.karte2Spieler2.setIcon(bo.pik[nummerk22]);
			break;
		case "herz":
			bo.karte2Spieler2.setIcon(bo.herz[nummerk22]);
			break;
		case "kreuz":
			bo.karte2Spieler2.setIcon(bo.kreuz[nummerk22]);
			break;
		case "karo":
			bo.karte2Spieler2.setIcon(bo.karo[nummerk22]);
			break;
		}

		//Karte1 Bank
		/*	switch (farbebank1) {
				case "pik":
					bo.karte1Bank.setIcon(bo.pik[nummerk1b]);
					break;
				case "herz":
					bo.karte1Bank.setIcon(bo.herz[nummerk1b]);
					break;
				case "kreuz":
					bo.karte1Bank.setIcon(bo.kreuz[nummerk1b]);
					break;
				case "karo":
					bo.karte1Bank.setIcon(bo.karo[nummerk1b]);
					break;
				} */

		//Karte2 Bank
		switch (farbebank2) {
		case "pik":
			bo.karte2Bank.setIcon(bo.pik[nummerk2b]);
			break;
		case "herz":
			bo.karte2Bank.setIcon(bo.herz[nummerk2b]);
			break;
		case "kreuz":
			bo.karte2Bank.setIcon(bo.kreuz[nummerk2b]);
			break;
		case "karo":
			bo.karte2Bank.setIcon(bo.karo[nummerk2b]);
			break;
		} 


		bo.karte1Spieler1.setVisible(true);
		bo.karte2Spieler1.setVisible(true);
		bo.karte3Spieler1.setVisible(false);
		bo.karte4Spieler1.setVisible(false);
		bo.karte5Spieler1.setVisible(false);
		bo.karte1Spieler2.setVisible(true);
		bo.karte2Spieler2.setVisible(true);
		bo.karte3Spieler2.setVisible(false);
		bo.karte4Spieler2.setVisible(false);
		bo.karte5Spieler2.setVisible(false);
		bo.karte1Bank.setVisible(true);
		bo.karte1Bank.setIcon(bo.rueckseite);
		bo.karte2Bank.setVisible(true); 
		bo.karte3Bank.setVisible(false); 
		bo.karte4Bank.setVisible(false); 
		bo.karte5Bank.setVisible(false); 
	}

	public void kartenausgebenS_R234(Spiel s, int runde){
		String farbek11="null"; //soll bewusst kein null sein sondern nur ein Text...
		String farbek12="null";
		String farbebank1="null";
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
				bo.karte3Spieler1.setIcon(bo.pik[nummerk11]);
				break;
			case "herz":
				bo.karte3Spieler1.setIcon(bo.herz[nummerk11]);
				break;
			case "kreuz":
				bo.karte3Spieler1.setIcon(bo.kreuz[nummerk11]);
				break;
			case "karo":
				bo.karte3Spieler1.setIcon(bo.karo[nummerk11]);
				break;
			case "null":
				break;
			}


			//Karte Spieler2
			switch (farbek12) {
			case "pik":
				bo.karte3Spieler2.setIcon(bo.pik[nummerk12]);
				break;
			case "herz":
				bo.karte3Spieler2.setIcon(bo.herz[nummerk12]);
				break;
			case "kreuz":
				bo.karte3Spieler2.setIcon(bo.kreuz[nummerk12]);
				break;
			case "karo":
				bo.karte3Spieler2.setIcon(bo.karo[nummerk12]);
				break;
			case "null":
				break;
			}


			//Karte Bank
			switch (farbebank1) {
			case "pik":
				bo.karte3Bank.setIcon(bo.pik[nummerk1b]);
				break;
			case "herz":
				bo.karte3Bank.setIcon(bo.herz[nummerk1b]);
				break;
			case "kreuz":
				bo.karte3Bank.setIcon(bo.kreuz[nummerk1b]);
				break;
			case "karo":
				bo.karte3Bank.setIcon(bo.karo[nummerk1b]);
				break;
			case "null":
				break;
			} 
			bo.karte1Spieler1.setVisible(true);
			bo.karte2Spieler1.setVisible(true);
			bo.karte3Spieler1.setVisible(true);

			bo.karte1Spieler2.setVisible(true);
			bo.karte2Spieler2.setVisible(true);
			bo.karte3Spieler2.setVisible(true);

			bo.karte1Bank.setVisible(true);
			bo.karte1Bank.setIcon(bo.rueckseite);
			bo.karte2Bank.setVisible(true);
			bo.karte3Bank.setVisible(true);}
		else if (runde == 3) {
			/**weitere Karte von Spieler1 anzeigen.*/
			switch (farbek11) {
			case "pik":
				bo.karte4Spieler1.setIcon(bo.pik[nummerk11]);
				break;
			case "herz":
				bo.karte4Spieler1.setIcon(bo.herz[nummerk11]);
				break;
			case "kreuz":
				bo.karte4Spieler1.setIcon(bo.kreuz[nummerk11]);
				break;
			case "karo":
				bo.karte4Spieler1.setIcon(bo.karo[nummerk11]);
				break;
			case "null":
				break;
			}


			//Karte Spieler2
			switch (farbek12) {
			case "pik":
				bo.karte4Spieler2.setIcon(bo.pik[nummerk12]);
				break;
			case "herz":
				bo.karte4Spieler2.setIcon(bo.herz[nummerk12]);
				break;
			case "kreuz":
				bo.karte4Spieler2.setIcon(bo.kreuz[nummerk12]);
				break;
			case "karo":
				bo.karte4Spieler2.setIcon(bo.karo[nummerk12]);
				break;
			case "null":
				break;
			}


			//Karte Bank
			switch (farbebank1) {
			case "pik":
				bo.karte4Bank.setIcon(bo.pik[nummerk1b]);
				break;
			case "herz":
				bo.karte4Bank.setIcon(bo.herz[nummerk1b]);
				break;
			case "kreuz":
				bo.karte4Bank.setIcon(bo.kreuz[nummerk1b]);
				break;
			case "karo":
				bo.karte4Bank.setIcon(bo.karo[nummerk1b]);
				break;
			case "null":
				break;
			} 

			bo.karte1Spieler1.setVisible(true);
			bo.karte2Spieler1.setVisible(true);
			bo.karte3Spieler1.setVisible(true);
			bo.karte4Spieler1.setVisible(true);

			bo.karte1Spieler2.setVisible(true);
			bo.karte2Spieler2.setVisible(true);
			bo.karte3Spieler2.setVisible(true);
			bo.karte4Spieler2.setVisible(true);

			bo.karte1Bank.setVisible(true);
			bo.karte1Bank.setIcon(bo.rueckseite);
			bo.karte2Bank.setVisible(true);
			bo.karte3Bank.setVisible(true);
			bo.karte4Bank.setVisible(true);}
		else if (runde == 4) {

			switch (farbek11) {
			case "pik":
				bo.karte5Spieler1.setIcon(bo.pik[nummerk11]);
				break;
			case "herz":
				bo.karte5Spieler1.setIcon(bo.herz[nummerk11]);
				break;
			case "kreuz":
				bo.karte5Spieler1.setIcon(bo.kreuz[nummerk11]);
				break;
			case "karo":
				bo.karte5Spieler1.setIcon(bo.karo[nummerk11]);
				break;
			case "null":
				break;
			}


			//Karte Spieler2
			switch (farbek12) {
			case "pik":
				bo.karte5Spieler2.setIcon(bo.pik[nummerk12]);
				break;
			case "herz":
				bo.karte5Spieler2.setIcon(bo.herz[nummerk12]);
				break;
			case "kreuz":
				bo.karte5Spieler2.setIcon(bo.kreuz[nummerk12]);
				break;
			case "karo":
				bo.karte5Spieler2.setIcon(bo.karo[nummerk12]);
				break;
			case "null":
				break;
			}


			//Karte Bank
			switch (farbebank1) {
			case "pik":
				bo.karte5Bank.setIcon(bo.pik[nummerk1b]);
				break;
			case "herz":
				bo.karte5Bank.setIcon(bo.herz[nummerk1b]);
				break;
			case "kreuz":
				bo.karte5Bank.setIcon(bo.kreuz[nummerk1b]);
				break;
			case "karo":
				bo.karte5Bank.setIcon(bo.karo[nummerk1b]);
				break;
			case "null":
				break;
			} 
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
			bo.karte1Bank.setIcon(bo.rueckseite);
			bo.karte2Bank.setVisible(true);
			bo.karte3Bank.setVisible(true);
			bo.karte4Bank.setVisible(true); 
			bo.karte5Bank.setVisible(true);}



	}

	public void dealerKarteAufdecken(Spiel s)
	{/**Karte drehen Dealer*/
		String farbebank1= s.DeckDealer.get(0).getFarbe();
		int nummerk1b = s.DeckDealer.get(0).getName();

		//Karte1 Bank
		switch (farbebank1) {
		case "pik":
			bo.karte1Bank.setIcon(bo.pik[nummerk1b]);
			break;
		case "herz":
			bo.karte1Bank.setIcon(bo.herz[nummerk1b]);
			break;
		case "kreuz":
			bo.karte1Bank.setIcon(bo.kreuz[nummerk1b]);
			break;
		case "karo":
			bo.karte1Bank.setIcon(bo.karo[nummerk1b]);
			break;


		}
		bo.karte1Bank.setVisible(true);
	}


	public void jetonsZuHitundStay() {
		bo.buttonLogin.setVisible(false);
		bo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		bo.ueberschrift.setVisible(false);
		bo.buttonZurueck.setVisible(false);

		//Loginfenster
		bo.labelBenutzername.setVisible(false);
		bo.labelPasswort.setVisible(false);
		bo.buttonstart.setVisible(false);
		bo.userText.setVisible(false);
		bo.passwordText.setVisible(false);
		bo.buttonZurueck.setVisible(false);

		//Registrierfenster
		bo.labelBenutzernameErstellen.setVisible(false);
		bo.labelPasswort1.setVisible(false);
		bo.labelPasswort2.setVisible(false);
		bo.buttonRegistrierenAbschließen.setVisible(false);
		bo.userRegistText.setVisible(false);
		bo.passwordText1.setVisible(false);
		bo.passwordText2.setVisible(false);

		//IPAdressefenster:
		bo.buttonIPAdresseBestaetigen.setVisible(false);
		bo.labelipadresse.setVisible(false);
		bo.ipadresseText.setVisible(false);

		//Portfenster: 
		bo.buttonPortBestätigen.setVisible(false);
		bo.labelport.setVisible(false);
		bo.portText.setVisible(false);
		//Spielfenster: 
		bo.buttonEinsatz.setVisible(false);
		bo.buttonJeton10.setVisible(false);
		bo.buttonJeton25.setVisible(false);
		bo.buttonJeton50.setVisible(false);
		bo.buttonJeton100.setVisible(false);
		bo.karte1Spieler1.setVisible(false);
		bo.karte2Spieler1.setVisible(false);
		bo.karte3Spieler1.setVisible(false); 
		bo.karte4Spieler1.setVisible(false); 
		bo.karte5Spieler1.setVisible(false); 
		bo.karte1Spieler2.setVisible(false);
		bo.karte2Spieler2.setVisible(false);
		bo.karte3Spieler2.setVisible(false);
		bo.karte4Spieler2.setVisible(false);
		bo.karte5Spieler2.setVisible(false);
		bo.karte1Bank.setVisible(false);
		bo.karte2Bank.setVisible(false);
		bo.karte3Bank.setVisible(false);
		bo.karte4Bank.setVisible(false);
		bo.karte5Bank.setVisible(false);
		bo.einsatzSpieler1.setVisible(true);
		bo.einsatzSpieler2.setVisible(true);
		//bo.kontostandSpieler1C.setVisible(true);
		bo.kontostandSpieler1.setVisible(true); 
		bo.buttonEinsatzbestaetigen.setVisible(false);
		bo.buttonHit.setVisible(true);
		bo.buttonStay.setVisible(true);


		bo.kartenwertSpieler1.setVisible(true);
		bo.kartenwertSpieler2.setVisible(true);
		bo.kartenwertDealer.setVisible(true);
	}

	public void rundeZuAuswerten() {
		bo.buttonLogin.setVisible(false);
		bo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		bo.ueberschrift.setVisible(false);
		bo.buttonZurueck.setVisible(false);

		//Loginfenster
		bo.labelBenutzername.setVisible(false);
		bo.labelPasswort.setVisible(false);
		bo.buttonstart.setVisible(false);
		bo.userText.setVisible(false);
		bo.passwordText.setVisible(false);
		bo.buttonZurueck.setVisible(false);

		//Registrierfenster
		bo.labelBenutzernameErstellen.setVisible(false);
		bo.labelPasswort1.setVisible(false);
		bo.labelPasswort2.setVisible(false);
		bo.buttonRegistrierenAbschließen.setVisible(false);
		bo.userRegistText.setVisible(false);
		bo.passwordText1.setVisible(false);
		bo.passwordText2.setVisible(false);

		//IPAdressefenster:
		bo.buttonIPAdresseBestaetigen.setVisible(false);
		bo.labelipadresse.setVisible(false);
		bo.ipadresseText.setVisible(false);

		//Portfenster: 
		bo.buttonPortBestätigen.setVisible(false);
		bo.labelport.setVisible(false);
		bo.portText.setVisible(false);
		//Spielfenster: 
		bo.buttonEinsatz.setVisible(false);
		bo.buttonJeton10.setVisible(false);
		bo.buttonJeton25.setVisible(false);
		bo.buttonJeton50.setVisible(false);
		bo.buttonJeton100.setVisible(false);
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
		//bo.kontostandSpieler1C.setVisible(true);
		bo.kontostandSpieler1.setVisible(true); 
		bo.buttonEinsatzbestaetigen.setVisible(false);
		bo.buttonHit.setVisible(false);
		bo.buttonStay.setVisible(false);
		bo.kartenwertSpieler1.setVisible(true);
		bo.kartenwertSpieler2.setVisible(true);
		bo.kartenwertDealer.setVisible(true);
		bo.nachrichtS1.setText(ausgabetextS1);
		bo.nachrichtS2.setText(ausgabetextS2);
		bo.nachrichtS1.setVisible(true);
		bo.nachrichtS2.setVisible(true);
		bo.buttonNaechsteRunde.setVisible(true);
	}

	public void auswertenZuEinsatz() {
		bo.buttonLogin.setVisible(false);
		bo.buttonRegistrieren.setVisible(false);

		//Gemeinsame
		bo.ueberschrift.setVisible(false);
		bo.buttonZurueck.setVisible(false);

		//Loginfenster
		bo.labelBenutzername.setVisible(false);
		bo.labelPasswort.setVisible(false);
		bo.buttonstart.setVisible(false);
		bo.userText.setVisible(false);
		bo.passwordText.setVisible(false);
		bo.buttonZurueck.setVisible(false);

		//Registrierfenster
		bo.labelBenutzernameErstellen.setVisible(false);
		bo.labelPasswort1.setVisible(false);
		bo.labelPasswort2.setVisible(false);
		bo.buttonRegistrierenAbschließen.setVisible(false);
		bo.userRegistText.setVisible(false);
		bo.passwordText1.setVisible(false);
		bo.passwordText2.setVisible(false);

		//IPAdressefenster:
		bo.buttonIPAdresseBestaetigen.setVisible(false);
		bo.labelipadresse.setVisible(false);
		bo.ipadresseText.setVisible(false);

		//Portfenster: 
		bo.buttonPortBestätigen.setVisible(false);
		bo.labelport.setVisible(false);
		bo.portText.setVisible(false);
		//Spielfenster: 
		bo.buttonEinsatz.setVisible(true);
		bo.buttonJeton10.setVisible(false);
		bo.buttonJeton25.setVisible(false);
		bo.buttonJeton50.setVisible(false);
		bo.buttonJeton100.setVisible(false);
		bo.karte1Spieler1.setVisible(false);
		bo.karte2Spieler1.setVisible(false);
		bo.karte3Spieler1.setVisible(false); 
		bo.karte4Spieler1.setVisible(false); 
		bo.karte5Spieler1.setVisible(false); 
		bo.karte1Spieler2.setVisible(false);
		bo.karte2Spieler2.setVisible(false);
		bo.karte3Spieler2.setVisible(false);
		bo.karte4Spieler2.setVisible(false);
		bo.karte5Spieler2.setVisible(false);
		bo.karte1Bank.setVisible(false);
		bo.karte2Bank.setVisible(false);
		bo.karte3Bank.setVisible(false);
		bo.karte4Bank.setVisible(false);
		bo.karte5Bank.setVisible(false);
		bo.einsatzSpieler1.setVisible(false);
		bo.einsatzSpieler2.setVisible(false);
		//bo.kontostandSpieler1C.setVisible(true);
		bo.kontostandSpieler1.setVisible(false); 
		bo.buttonEinsatzbestaetigen.setVisible(false);
		bo.buttonHit.setVisible(false);
		bo.buttonStay.setVisible(false);
		bo.kartenwertSpieler1.setVisible(false);
		bo.kartenwertSpieler2.setVisible(false);
		bo.kartenwertDealer.setVisible(false);
		bo.nachrichtS1.setVisible(false);
		bo.nachrichtS2.setVisible(false);
		bo.buttonNaechsteRunde.setVisible(false);
	}



	/*
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
		} */
}












