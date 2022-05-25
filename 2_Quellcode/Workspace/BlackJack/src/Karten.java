import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.Vector;
import java.util.ArrayList;

public class Karten {


	private int anz_ausgespielt;
	private int nummer;
	
	private String blatt;
	private String name;
	
	 private final String farbe; //Farbe
	 private final String wert; // Wert
	//public static Karten[] deck = new Karten[52];


   
    public void Karte(String farbe, String wert){
        this.farbe = farbe;
        this.wert = wert;
    }

    public String getXCord(){
        return this.farbe;
    }

    public String getYCord(){
        return this.wert;
    }





	//public Karten(int nr, String b, String n, int w) {
	//	nummer = nr;
	//	blatt= b ;
	//	wert = w;
		//name = n;
	//}

//	static void austeilen() {
		//Randomzahl 1-52
		//Prüfen ob max 5 ausgespielt
		//at1();
		//at2();

	}

	//Info für GUI
	int at1(Karten k) {
		return k.nummer;
	}

	//Info für Dealer/Spieler
	int at2(Karten k) {
		return k.wert;
	}


	
	//static void lesen(String dateiname) {
		//try {
		//	BufferedReader datei = new BufferedReader(new FileReader(dateiname));
		//	while (true) {
			//	String str;

			//	try {

					//str = datei.readLine();
					
				//	if (str==null) break;
				//}
				//catch (Exception e) {
				//	e.printStackTrace();	
				//} 

			//}
		//}
		//catch (Exception e) {
		//	e.printStackTrace();{

		//	}
		//}
	//}
	
	//public static void getLineNumber(int num, String file){
	//	try {
	//		LineNumberReader reader = new LineNumberReader (new FileReader(file) );
	//		for(int i = 0; i<num-1; i++)
	//			reader.readLine();
	//		String[]inp = reader.readLine().split("\t");
		//	for(int i=0;i<52;i++){
		//	    deck[i] = new Karten(Integer.parseInt(inp[0]),inp[1], inp[2], Integer.parseInt(inp[3]));
		//	}

			/*System.out.println(deck[0]);
			System.out.println(deck[1]);
			System.out.println(deck[2]);
			System.out.println(deck[3]);*/
	//	}
	//	catch (Exception e) {
	//		e.printStackTrace();}
		

	//}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector<Karten> zahlen = new Vector<Karten>();
		//lesen("Karten.txt");
		
		for (int i=2; i<54; i++) {
			getLineNumber(i, "Karten.txt");
			
		}

       System.out.println(deck[13].wert);

	}

}
