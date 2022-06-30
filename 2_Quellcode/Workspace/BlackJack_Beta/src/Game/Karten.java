package Game;


public class Karten {



	

	private int wert; /**Kartenwert*/
	private String farbe; /**Kartenfarbe*/
	private int name; /**Kartenname*/


/**Kunstroktur Karten
 * 
 * @param w
 * @param f
 * @param n
 */
	public Karten(int w, String f, int n){
		farbe = f;
		wert = w;
		name = n;
	}
/** Get Kartenfarbe
 * 
 * @return this.farbe
 */
	public String getFarbe(){
		return this.farbe;
	}

	/** Get Kartenwert
	 * 
	 * @return this.wert
	 */
	
	public int getWert(){
		return this.wert;
	}

	/** Get Kartenname
	 * 
	 * @return this.name
	 */
	
	public int getName(){
		return this.name;
	}

}





