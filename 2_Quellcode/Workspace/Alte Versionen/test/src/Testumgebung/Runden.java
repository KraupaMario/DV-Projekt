package Testumgebung;

import java.util.ArrayList;


public class Runden {
	
	 

	public static void main(String[] args) {
		ArrayList<String> kartenDeck = new ArrayList();
		
		kartenDeck.add("Hallo");
		kartenDeck.add("Moin");
		
		
		try {
			String str  = kartenDeck.get(0);
			System.out.println(str);
		} catch (Exception e) {
			
		}
		try {
			String str  = kartenDeck.get(1);
			System.out.println(str);
		} catch (Exception e) {
			
		}
		
		try {
			String str  = kartenDeck.get(2);
			System.out.println(str);
		} catch (Exception e) {
			
		}
	
		
		

	}

}
