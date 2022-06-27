package sdaf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.Vector;
import java.util.ArrayList;

public class Karten {



	//private final String farbe; //Farbe
	//private final String wert; // Wert
	private int index;
	private int wert;
	private String farbe;
	private String name;


	//public static Karten[] deck = new Karten[52];



	public Karten(int w, String f, String n){
		farbe = f;
		wert = w;
		name = n;
	}

	public String getFarbe(){
		return this.farbe;
	}

	public int getWert(){
		return this.wert;
	}

	public String getName(){
		return this.name;
	}

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

/*

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
 */