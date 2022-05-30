import javax.swing.JOptionPane;

public class Spieler {

	private String name;
	private String passwort;
	private long kontostand =  5000;
	private long highscore;

	int kartenwert;

	public Spieler(String n, String passwort) {
		//..
		kontostand = 5000;
	}



	static void registrieren() {
		String n = JOptionPane.showInputDialog("Nickname:");
		//Abfrage Doppelt?
		String p = JOptionPane.showInputDialog("Passwort:");
		Spieler name = new Spieler(n, p);
	}


	public int geldsetzen() {
		System.out.println("Wie viel Geld wollen sie setzen? "); // Port festlegen 8080?
		int mony = Integer.parseInt(JOptionPane.showInputDialog("Wie viel Geld wollen sie setzen?"));
		while ((mony < 1 || mony > 500001)&& mony<= kontostand) {
			System.out.println("Sie können nur bis zu 500000 Euro auf einmal setzen! Probieren Sie es nochmal");
			mony = Integer.parseInt(JOptionPane.showInputDialog("mo?")); // überprufen ob er genug auf dem Konto hat??
		}
		this.rmveMoney(mony);
		return mony;
	}
	
	void addMoney(int m) {
		//Geld zum Konto hinzufügen
		
	}
	
	void rmveMoney(int m) {
		kontostand = kontostand - m;
	}



	void hit() {

	}

	void stay() {

	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
