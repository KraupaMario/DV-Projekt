package Game;
import javax.swing.JOptionPane;

public class Spieler {

	private String name; //Vorbereitung für Erweiterung
    private String passwort; //Vorbereitung für Erweiterung
	private int kontostand;
	private long highscore; //Vorbereitung für Erweiterung

	int kartenwert;

	public Spieler(String n, String passwort) {
		//..Name und Passwort im Konstruktor dienen als Vorbereitung für eine Erweiterung mit einer Benutzerdatenbank die Benutzerkonten speichert.
		kontostand = 500;
	}



	/*static void registrieren() {
		String n = JOptionPane.showInputDialog("Nickname:");
		//Abfrage Doppelt?
		String p = JOptionPane.showInputDialog("Passwort:");
		Spieler name = new Spieler(n, p);
	}  */
	
	
	/**
	 * Kontostand abfragen 
	 * @return den Betrag des Kontostands
	 */
	public int getKontostand() {
		return kontostand;
	}
	
	/**Abbuchung von Spielgeld vom Spielerkonto.*/
	/**@param Abzubuchender Betrag.*/
	public void abbuchen(int g) {
		kontostand = kontostand - g;
	}
	
	/**@param Gesetzter Betrag des Spielers (mny) sowie Gewinnstatus.*/
	void einzahlen(int mny, int gewinnstatus) {
		//Geld zum Konto hinzufÃ¼gen oder abziehen
			if (gewinnstatus == 1 /*Gewonnen*/) {
				kontostand = kontostand + mny*2;
			}
			else if (gewinnstatus == 0 /*BlackJack*/) {
				kontostand = (int)(kontostand - mny*2.5);
			}
			else if (gewinnstatus == 3 /*Patt/Unentschieden*/) {
				kontostand = kontostand + mny;
			}

		}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



	

}
