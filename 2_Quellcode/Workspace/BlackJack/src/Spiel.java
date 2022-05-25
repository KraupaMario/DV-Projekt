import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
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
	private String unableToCommunicateWithOpponentString = "Kommunikation mit dem Spieler nicht m�glich.";
	private String wonString = "Du hast gewonnen!";
	private String enemyWonString = "Dealer gewinnt!";
	private String tieString = "Unentschieden!";

	
		
		class Spiel{
		    ArrayList<Karte> kartenDeck = new ArrayList<Karte>();
		   
		    	    
		    
		    public void createDeck(){
		        String[] farben ={"Kreuz","Pik","Herz","Karo"};
		        String[] werte ={"Ass","Koenig","Dame","Bube","10","9","8","7","6","5","4","3","2"};
		        for(int y = 0; y < 6; y++){
		            for(int i=0;i<farben.length;i++) {
		                for(int j=0;j<werte.length;j++) {
		                    kartenDeck.add(new Karte(werte[j],farben[i]));
		                }
		            }
		        }
		        
		    }
		    
		    
		    public Karte getKarte(){
		        int random = (int)Math.random()*52*6;
		        Karte test = kartenDeck.get(random);
		        kartenDeck.remove(random);
		        return test;
		    }
		    
		    public void eigentlichesSpiel(){
		        //...
		        Karte test = getKarte();
		        test.getXCord(); // bekommt wert
		        // ...
		    }
		}
	
		
		public void Geldsetzen(int mo) {
			System.out.println("Wie viel Geld wollen sie setzen? "); // Port festlegen 8080?
			mo = Integer.parseInt(JOptionPane.showInputDialog("mo?"));
			while (mo < 1 || mo > 500001) {
				System.out.println("Sie k�nnen nur bis zu 500000 Euro aufeinmal setzen! Probieren Sie es nochmal");
				mo = Integer.parseInt(JOptionPane.showInputDialog("mo?")); // �berprufen ob er genug auf dem Konto hat??
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
