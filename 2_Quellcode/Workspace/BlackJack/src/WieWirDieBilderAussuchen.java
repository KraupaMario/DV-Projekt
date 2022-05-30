import java.util.ArrayList;

public class WieWirDieBilderAussuchen extends Spiel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Cards> kartenDeck = new ArrayList<Cards>();
		kartenDeck.add(new Cards("2", "Herz"));
		kartenDeck.add(new Cards("Q", "Herz"));
		
		
		String abfr = kartenDeck.get(1).farbe + kartenDeck.get(1).name;
		
		
		//System.out.println(abfr);
		
		switch(abfr){
        case "Herz2":
            System.out.println("Ausgabe: Herz 2"); //Hier Bitmap aufrufen
            break;
        case "HerzQ":
            System.out.println("Ausgabe: Herz Dame");
            break;
        default:
            System.out.println("Fehler");
            break;
        } 

	}

}

class Cards{
	String name;
	String farbe;
	Cards(String n, String f){
		name = n;
		farbe = f;
	}
}
