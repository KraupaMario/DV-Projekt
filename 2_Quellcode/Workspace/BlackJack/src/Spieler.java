import javax.swing.JOptionPane;

public class Spieler {
	
	String name;
	String passwort;
	long kontostand =  5000;
	long highscore;
	
	int kartenwert;
	
	Spieler(){
		
	}
	
	static void registrieren() {
		String n = JOptionPane.showInputDialog("Nickname:");
		//Abfrage Doppelt?
		String p = JOptionPane.showInputDialog("Passwort:");
		Spieler name = new Spieler();
	}
	
	
	
	
	void hit() {
		
	}
	
	void stay() {
		
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
