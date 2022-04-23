
public class Karten {
	
	
	int anz_ausgespielt;
	int nummer;
	int wert;
	String name;
	
	
	static void austeilen() {
		//Randomzahl 1-52
		//Prüfen ob max 5 ausgespielt
		at1();
		at2();
		
	}
	
	//Info für GUI
	int at1(Karten k) {
		return k.nummer;
	}
	
	//Info für Dealer/Spieler
		int at2(Karten k) {
			return k.wert;
		}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Karten k1 = new Karten();
		k1.name = "Kreuz Bube";
		k1.nummer = 1;
		k1.wert = 10;
		
	}

}
