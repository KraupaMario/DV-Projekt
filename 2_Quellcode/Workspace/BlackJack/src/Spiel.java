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
	
	int gesetztSpieler1;
	int gesetztSpieler2;

	int wertSpieler1;
	int wertSpieler2;
	int wertDealer;
	
	boolean winSpieler1 = false;
	boolean winSpieler2 = false;
	boolean winDealer = false;
	
	boolean loseSpieler1 = false;
	boolean loseSpieler2 = false;
	boolean loseDealer = false;
	
		
	//	class Spiel Variablen
		    ArrayList<Karten> kartenDeck = new ArrayList<Karten>();
		    ArrayList<Karten> DeckSpieler1 = new ArrayList<Karten>();
		    ArrayList<Karten> DeckSpieler2 = new ArrayList<Karten>();
		    ArrayList<Karten> DeckDealer = new ArrayList<Karten>();
			
		    

	
		   
		    	    
		    
		    public void createDeck(){
		        String[] farben ={"Kreuz","Pik","Herz","Karo"};
		        String[] name ={"Ass","Koenig","Dame","Bube","10","9","8","7","6","5","4","3","2"};
		        int[] wert = {11,10,10,10,10,9,8,7,6,5,4,3,2};
		        for(int y = 0; y < 6; y++) {
		            for(int i=0;i<farben.length;i++) {
		                for(int j=0;j<name.length;j++) {
		                    kartenDeck.add(new Karten(wert[j], name[j],farben[i]));
		                }
		            }
		        }
		        
		    }
		    //public Karten( int w, String f, String n)
		    
		    public Karten getKarte(){
		        //int random = (int)Math.random()*52*6;
		    	int random = (int)Math.random()*(kartenDeck.size()-1); //Passt sich den verbleibenden Anzahl an Karten an. ?????????????????????? -1 oder nicht?????????
		        Karten gezogeneKarte = kartenDeck.get(random);
		        kartenDeck.remove(random);
		        return gezogeneKarte; //gibt die Gezogene Karte Weiter (als Objekt)
		    }
		    
		    public int wertSpieler1(){
				int w=0;
		    	return w;
		        //...Rechnet Wert aus Array List zusammen
		        
		    }
		    
		    // Checkt ob Blackjack oder Überkauft
		    public void checkSpieler1(){
		    	if (wertSpieler1 == 21) 
		    		winSpieler1 = true;
		    	if (wertSpieler1 > 21)
		    		loseSpieler1 = true;
		    }
		    
		    
		 
		
		
		
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Spiel neu = new Spiel();

	}

}
