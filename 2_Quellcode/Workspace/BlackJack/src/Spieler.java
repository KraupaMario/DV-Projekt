import javax.swing.JOptionPane;

public class Spieler {

	private String name;
    char[] passwort;
	private long kontostand =  5000;
	private long highscore;

	int kartenwert;
	int Gewinnstatus;

	public Spieler(String n, char[] passwort) {
		//..
		kontostand = 5000;
	}



	/*static void registrieren() {
		String n = JOptionPane.showInputDialog("Nickname:");
		//Abfrage Doppelt?
		String p = JOptionPane.showInputDialog("Passwort:");
		Spieler name = new Spieler(n, p);
	}  */


	public int geldsetzen() {
		System.out.println("Wie viel Geld wollen sie setzen? "); // Port festlegen 8080?
		int mony = Integer.parseInt(JOptionPane.showInputDialog("Wie viel Geld wollen sie setzen?"));
		while ((mony < 1 || mony > 500001)&& mony<= kontostand) {
			System.out.println("Sie k�nnen nur bis zu 500000 Euro auf einmal setzen! Probieren Sie es nochmal");
			mony = Integer.parseInt(JOptionPane.showInputDialog("mo?")); // �berprufen ob er genug auf dem Konto hat??
		}
		this.rmveMoney(mony);
		return mony;
	}
	
	void changeKontostand(mony) {
		//Geld zum Konto hinzufügen oder abziehen
			if (Gewinnstatus == 1) {
				// wie kann man denn den "Gewinnstatus aus der Klasse Spiel herausziehen?"
				kontostand = kontostand + mony*2.5;
			}
			else if (Gewinnstatus == 0) {
				kontostand = kontostand - mony;
			}

		}


	void hit() {

	}

	void stay() {

	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
