package Testumgebung;

public class SDSD {

	static boolean a = false;
	static boolean hit1 = false;
	static boolean hit2 = false;
	static int z = 0;
	static boolean BJ = false;
	static boolean win = false;

	boolean w = true;

	public static void main(String[] args) {



		System.out.println("Erstellen Spiel usw.");

		newgame: while(!a) {
			System.out.println("reset");
			System.out.println("Einsatz + Erste 2 Karten ausgeben.");
			System.out.println("Prüfen ob BJ..?");

			//Wenn BJ = true, mach neues Spiel (gehe zu newgame)
			while(BJ) {
				System.out.println("BlackJack!");
				System.out.println("Auswerten!");
				continue newgame;}

			//Wenn BJ = false, frag nach Hit oder Stay
			 newcard: while(!BJ) {
				z++;
				if (z>3) {
					break newcard;
				}
				System.out.println("Spieler 1 Hit/Stay?");
				hit1 = true;
				System.out.println("Spieler 2 Hit/Stay?");
				hit2 = true;

				while((!hit1 || !hit2)) {
					System.out.println("Auswerten");
					continue newgame;
				}
				
				while((hit1 && hit2)) {
					System.out.println("Auswerten");
					if (win) {
						System.out.println("Ein spieler Gewinnt oder Überkauft sich");
						break newcard;
					}
					continue newcard;
				}
			}
			 
			 a = true;
			 //continue newgame;
		}


	}

}


