
public class WieWirDieBilderAussuchen extends Spiel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cards herz2 = new Cards("2", "Herz");
		
		String abf = herz2.farbe + herz2.name;
		
		System.out.println(abf);
		
		switch(abf){
        case "herz2":
            System.out.println("Herz 2"); //Hier Bitmap aufrufen
            break;
        case "herz3":
            System.out.println("Herz 3");
            break;
        case 2:
            System.out.println("i ist zwei");
            break;
        case 3:
            System.out.println("i ist drei");
            break;
        default:
            System.out.println("i liegt nicht zwischen null und drei");
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
