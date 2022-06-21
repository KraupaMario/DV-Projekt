package Game;
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



public class Spiel {

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

	private static int gesetztSpieler1;
	private static int gesetztSpieler2;

	int wertSpieler1;
	int wertSpieler2;
	int wertDealer;

	boolean winSpieler1 = false;
	boolean winSpieler2 = false;
	boolean winDealer = false;

	boolean loseSpieler1 = false;
	boolean loseSpieler2 = false;
	boolean loseDealer = false;

	boolean pattSpieler1 = false;
	boolean pattSpieler2 = false;


	//	class Spiel Variablen
	ArrayList<Karten> kartenDeck = new ArrayList<Karten>();

	ArrayList<Karten> DeckSpieler1 = new ArrayList<Karten>();
	ArrayList<Karten> DeckSpieler2 = new ArrayList<Karten>();
	ArrayList<Karten> DeckDealer = new ArrayList<Karten>();






	public void createDeck(){
		String[] farben ={"kreuz","pik","herz","karo"};
		int[] name ={1,13,12,11,10,9,8,7,6,5,4,3,2};
		int[] wert = {11,10,10,10,10,9,8,7,6,5,4,3,2};
		for(int y = 0; y < 6; y++) {
			for(int i=0;i<farben.length;i++) {
				for(int j=0;j<name.length;j++) {
					kartenDeck.add(new Karten(wert[j],farben[i],name[j]));
				}
			}
		}

	}



	//public Karten( int w, String f, String n)

	public Karten karteManuell(int n) {
		Karten gezogeneKarte = kartenDeck.get(n);
		kartenDeck.remove(n);
		//wertgez = gezogeneKarte.getWert();
		return gezogeneKarte;
	}

	public Karten getKarte(){
		//int random = (int)Math.random()*52*6;
		int random = (int)(Math.random()*(kartenDeck.size()-1)); //Passt sich den verbleibenden Anzahl an Karten an. ?????????????????????? -1 oder nicht?????????
		Karten gezogeneKarte = kartenDeck.get(random);
		kartenDeck.remove(random);
		//wertgez = gezogeneKarte.getWert();
		return gezogeneKarte; //gibt die Gezogene Karte Weiter (als Objekt)
	}




	public void austeilenKarteSp1 () { //gezogene Karte wird über getKarte als Rückgabewert in KartenArraylist von jedem spieler
		DeckSpieler1.add(getKarte());

	}
	public void austeilenKarteSp2 () {
		DeckSpieler2.add(getKarte());

	}

	public void austeilenKarteDealer () {
		DeckDealer.add(getKarte());

	}

	public int wertSpieler1() {		//aktueller Karten wert für jeden Spieler
		int wert = 0;
		for (int i = 0; i<DeckSpieler1.size();i++) {
			int j = DeckSpieler1.get(i).getWert();
			wert = wert + j;
		}System.out.println("S1:" + wert);
		return wert;
	}

	public int wertSpieler2() {
		int wert = 0;
		for (int i = 0; i<DeckSpieler2.size();i++) {
			int j = DeckSpieler2.get(i).getWert();
			wert = wert + j;

		}System.out.println("S2:" + wert);
		return wert;
	}

	public int wertDealer() {
		int wert = 0;
		for (int i = 0; i<DeckDealer.size();i++) {
			int j = DeckDealer.get(i).getWert();
			wert = wert + j;

		}System.out.println("SD:" + wert);
		return wert;
	}

	// Checkt ob Blackjack oder Überkauft
	/** checkBJSpieler 1 muss immer als erstes aufgerufen werden!*/
	public int checkBJSpieler1(){
		winSpieler1 = false;
		winSpieler2 = false;
		winDealer = false;

		loseSpieler1 = false;
		loseSpieler2 = false;
		loseDealer = false;

		pattSpieler1 = false;
		pattSpieler2 = false;
		if (wertSpieler1() == 21) {
			System.out.println("Hier true setzen!");
			winSpieler1 = true;		      		
			return 0;
		}
		if (wertSpieler1() > 21) {
			loseSpieler1 = true;
			System.out.println("PLanlos");
			return -1;
		}
		else return -1;

	} 



	public void checkBJSpieler2(){
		if (wertSpieler2() == 21) 
			winSpieler2 = true;
		if (wertSpieler2 ()> 21)
			loseSpieler2 = true;
	}


	public void checkBJDealer(){
		if (wertDealer() == 21) 
			winDealer = true;
		if (wertDealer() > 21)
			loseDealer = true;
	}

	/**Auswerten Methoden*/
	/** 1 = Sieg Spieler, 2 = Sieg Dealer (Spieler verliert), 3 = unentschieden*/
	public int auswertenS1() {
		winSpieler1 = false;
		winSpieler2 = false;
		winDealer = false;

		loseSpieler1 = false;
		loseSpieler2 = false;
		loseDealer = false;

		pattSpieler1 = false;
		pattSpieler2 = false;
		
		if ((wertSpieler1() > wertDealer())&& wertSpieler1()<22 && wertDealer() <22) {
			winSpieler1 = true;
			loseDealer = true;
			return 1;}
		else if (wertSpieler1()<22 && wertDealer() >21) {
			winSpieler1 = true;  
			loseDealer = true;
			return 1;
		}
		else if ((wertSpieler1() < wertDealer())&& wertSpieler1()<22 && wertDealer() <22) {
			winDealer = true;
			loseSpieler1 = true;
			return 2;
		}
		else if (wertSpieler1()>22 && wertDealer() <22) {
			winDealer = true;
			loseSpieler1 = true;
			return 2;
		}
		else if (wertSpieler1()>22 && wertDealer() >22) {
			winDealer = true;
			loseSpieler1 = true;
			return 2;
		}
		else if (wertSpieler1() == wertDealer())
		{
			pattSpieler1 = true;
			return 3;
		}
		return -1;

	}

	public int auswertenS2() {
		
		
		winSpieler1 = false;
		winSpieler2 = false;
		winDealer = false;

		loseSpieler1 = false;
		loseSpieler2 = false;
		loseDealer = false;

		pattSpieler1 = false;
		pattSpieler2 = false;
		
		
				
		if ((wertSpieler2() > wertDealer())&& wertSpieler2()<22 && wertDealer() <22) {
			winSpieler2 = true;
			loseDealer = true;
			return 1;}
		else if (wertSpieler2()<22 && wertDealer() >21) {
			winSpieler2 = true;  
			loseDealer = true;
			return 1;
		}
		else if ((wertSpieler2() < wertDealer())&& wertSpieler2()<22 && wertDealer() <22) {
			winDealer = true;
			loseSpieler2 = true;
			return 2;
		}
		else if (wertSpieler2()>22 && wertDealer() <22) {
			winDealer = true;
			loseSpieler2 = true;
			return 2;
		}
		else if (wertSpieler2()>22 && wertDealer() >22) {
			
			winDealer = true;
			loseSpieler2 = true;
			return 2;
		}
		else if (wertSpieler2() == wertDealer())
		{
			pattSpieler2 = true;
			return 3;
		}
		return -1;


	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub


	}




	public static int getGesetztSpieler2() {
		return gesetztSpieler2;
	}




	public static void setGesetztSpieler2(int gesetztSpieler2) {
		Spiel.gesetztSpieler2 = gesetztSpieler2;
	}




	public static int getGesetztSpieler1() {
		return gesetztSpieler1;
	}




	public static void setGesetztSpieler1(int gesetztSpieler1) {
		Spiel.gesetztSpieler1 = gesetztSpieler1;
	}

}
