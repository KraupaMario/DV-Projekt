package Testumgebung;

import javax.swing.JOptionPane;

public class ausprobieren {
	
	int kontostand = 5000;
	
	public int geldsetzen() {
		System.out.println("Wie viel Geld wollen sie setzen? "); // Port festlegen 8080?
		int mony = Integer.parseInt(JOptionPane.showInputDialog("Wie viel Geld wollen sie setzen?"));
		while ((mony < 1 || mony > 500001)) {
			System.out.println("Sie können nur bis zu 500000 Euro auf einmal setzen! Probieren Sie es nochmal");
			mony = Integer.parseInt(JOptionPane.showInputDialog("mo?")); // überprufen ob er genug auf dem Konto hat??
		}
		while (kontostand <= mony) {
			System.out.println("Sie haben maximal "+kontostand+"€ zur Verfügung.");
			mony = Integer.parseInt(JOptionPane.showInputDialog("mo?")); // überprufen ob er genug auf dem Konto hat??
		}
		this.rmveMoney(mony);
		return mony;
	}
	
	void rmveMoney(int m) {
		kontostand = kontostand - m;
	}

	public static void main(String[] args) {
		ausprobieren t = new ausprobieren();
		
		int i;
		
		i = t.geldsetzen();
		System.out.println(i);

	}

}
