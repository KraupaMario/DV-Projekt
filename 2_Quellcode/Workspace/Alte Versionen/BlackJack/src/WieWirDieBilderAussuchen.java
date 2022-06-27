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
		
		//Herz-Karten
        case "Herz2":
            System.out.println("Ausgabe: Herz 2"); //Hier Bitmap aufrufen
            break;
        case "Herz3":
            System.out.println("Ausgabe: Herz 3"); //Hier Bitmap aufrufen
            break;
        case "Herz4":
            System.out.println("Ausgabe: Herz 4"); //Hier Bitmap aufrufen
            break;
        case "Herz5":
            System.out.println("Ausgabe: Herz 5"); //Hier Bitmap aufrufen
            break;
        case "Herz6":
            System.out.println("Ausgabe: Herz 6"); //Hier Bitmap aufrufen
            break;
        case "Herz7":
            System.out.println("Ausgabe: Herz 7"); //Hier Bitmap aufrufen
            break;
        case "Herz8":
            System.out.println("Ausgabe: Herz 8"); //Hier Bitmap aufrufen
            break;
        case "Herz9":
            System.out.println("Ausgabe: Herz 9"); //Hier Bitmap aufrufen
            break;
        case "Herz10":
            System.out.println("Ausgabe: Herz 10"); //Hier Bitmap aufrufen
            break;
        case "HerzB":
            System.out.println("Ausgabe: Herz Bube"); //Hier Bitmap aufrufen
            break;
        case "HerzQ":
            System.out.println("Ausgabe: Herz Dame");
            break;
        case "HerzK":
            System.out.println("Ausgabe: Herz Koenig"); //Hier Bitmap aufrufen
            break;
        case "HerzA":
            System.out.println("Ausgabe: Herz Ass"); //Hier Bitmap aufrufen
            break;
            
            //Kreuz-Karten
        case "Kreuz2":
            System.out.println("Ausgabe: Kreuz 2"); //Hier Bitmap aufrufen
            break;
        case "Kreuz3":
            System.out.println("Ausgabe: Kreuz 3"); //Hier Bitmap aufrufen
            break;
        case "Kreuz4":
            System.out.println("Ausgabe: Kreuz 4"); //Hier Bitmap aufrufen
            break;
        case "Kreuz5":
            System.out.println("Ausgabe: Kreuz 5"); //Hier Bitmap aufrufen
            break;
        case "Kreuz6":
            System.out.println("Ausgabe: Kreuz 6"); //Hier Bitmap aufrufen
            break;
        case "Kreuz7":
            System.out.println("Ausgabe: Kreuz 7"); //Hier Bitmap aufrufen
            break;
        case "Kreuz8":
            System.out.println("Ausgabe: Kreuz 8"); //Hier Bitmap aufrufen
            break;
        case "Kreuz9":
            System.out.println("Ausgabe: Kreuz 9"); //Hier Bitmap aufrufen
            break;
        case "Kreuz10":
            System.out.println("Ausgabe: Kreuz 10"); //Hier Bitmap aufrufen
            break;
        case "KreuzB":
            System.out.println("Ausgabe: Kreuz Bube"); //Hier Bitmap aufrufen
            break;
        case "KreuzQ":
            System.out.println("Ausgabe: Kreuz Dame"); //Hier Bitmap aufrufen
            break;
        case "KreuzK":
            System.out.println("Ausgabe: Kreuz Koenig"); //Hier Bitmap aufrufen
            break;
        case "KreuzA":
            System.out.println("Ausgabe: Kreuz Ass"); //Hier Bitmap aufrufen
            break;
            
           //Karo-Karte
        case "Karo2":
            System.out.println("Ausgabe: Karo 2"); //Hier Bitmap aufrufen
            break;
        case "Karo3":
            System.out.println("Ausgabe: Karo 3"); //Hier Bitmap aufrufen
            break;
        case "Karo4":
            System.out.println("Ausgabe: Karo 4"); //Hier Bitmap aufrufen
            break;
        case "Karo5":
            System.out.println("Ausgabe: Karo 5"); //Hier Bitmap aufrufen
            break;
        case "Karo6":
            System.out.println("Ausgabe: Karo 6"); //Hier Bitmap aufrufen
            break;
        case "Karo7":
            System.out.println("Ausgabe: Karo 7"); //Hier Bitmap aufrufen
            break;
        case "Karo8":
            System.out.println("Ausgabe: Karo 8"); //Hier Bitmap aufrufen
            break;
        case "Karo9":
            System.out.println("Ausgabe: Karo 9"); //Hier Bitmap aufrufen
            break;
        case "Karo10":
            System.out.println("Ausgabe: Karo 10"); //Hier Bitmap aufrufen
            break;
        case "KaroB":
            System.out.println("Ausgabe: Karo Bube"); //Hier Bitmap aufrufen
            break;
        case "KaroQ":
            System.out.println("Ausgabe: Karo Dame"); //Hier Bitmap aufrufen
            break;
        case "KaroK":
            System.out.println("Ausgabe: Karo Koenig"); //Hier Bitmap aufrufen
            break;
        case "KaroA":
            System.out.println("Ausgabe: Karo Ass"); //Hier Bitmap aufrufen
            break;
            
            //Pik-Karten
        case "Pik2":
            System.out.println("Ausgabe: Pik 2"); //Hier Bitmap aufrufen
            break;
        case "Pik3":
            System.out.println("Ausgabe: Pik 3"); //Hier Bitmap aufrufen
            break;   
        case "Pik4":
            System.out.println("Ausgabe: Pik 4"); //Hier Bitmap aufrufen
            break;
        case "Pik5":
            System.out.println("Ausgabe: Pik 5"); //Hier Bitmap aufrufen
            break;
        case "Pik6":
            System.out.println("Ausgabe: Pik 6"); //Hier Bitmap aufrufen
            break;
        case "Pik7":
            System.out.println("Ausgabe: Pik 7"); //Hier Bitmap aufrufen
            break;
        case "Pik8":
            System.out.println("Ausgabe: Pik 8"); //Hier Bitmap aufrufen
            break;
        case "Pik9":
            System.out.println("Ausgabe: Pik 9"); //Hier Bitmap aufrufen
            break;
        case "Pik10":
            System.out.println("Ausgabe: Pik 10"); //Hier Bitmap aufrufen
            break;
        case "PikB":
            System.out.println("Ausgabe: Pik Bube"); //Hier Bitmap aufrufen
            break;
        case "PikQ":
            System.out.println("Ausgabe: Pik Dame"); //Hier Bitmap aufrufen
            break;
        case "PikK":
            System.out.println("Ausgabe: Pik Koenig"); //Hier Bitmap aufrufen
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
