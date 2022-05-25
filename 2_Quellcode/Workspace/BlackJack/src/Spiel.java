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

	
		
	//	class Spiel{
		    ArrayList<Karten> kartenDeck = new ArrayList<Karten>();
		   
		    	    
		    
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
		    	int random = (int)Math.random()*kartenDeck.size(); //Passt sich den verbleibenden Anzahl an Karten an.
		        Karten gezogeneKarte = kartenDeck.get(random);
		        kartenDeck.remove(random);
		        return gezogeneKarte; //gibt die Gezogene Karte Weiter (Objekt)
		    }
		    
		    public void eigentlichesSpiel(){
		        //...
		        Karten test = getKarte();
		        test.getXCord(); // bekommt wert
		        // ...
		    }
		
		
		}
	
	private void aktion()  {
		String nachricht = "leer";
		if (host && yourTurn) {
			
			/*
			try {
				String nachricht = dis.readUTF();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("nachricht");
			}
			*/
			
			
			System.out.println("Ich bin der Host und bin an der Reihe. Gib eine Nachricht ein.");
			int txt1 = Integer.parseInt(JOptionPane.showInputDialog("Nachricht (Ich bin der Host)"));
			try {
				dos.writeInt(txt1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			yourTurn = false;

		} else if (!host && yourTurn) {
			
			try {
				nachricht = dis.readUTF();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(nachricht);
			
			System.out.println("Ich bin der Client und bin an der Reihe. Gib eine Nachricht ein.");
			int txt2 = Integer.parseInt(JOptionPane.showInputDialog("Nachricht (Ich bin der Client)"));
			try {
				dos.writeInt(txt2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			yourTurn = false;
		}

		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Spiel neu = new Spiel();

	}

}
