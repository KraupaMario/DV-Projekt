package Game;
import javax.swing.ImageIcon;

public class Spielkarten {
	 static ImageIcon front = new ImageIcon();
	static ImageIcon pik[] = new ImageIcon[14];
	static ImageIcon herz[] = new ImageIcon[14];
	static ImageIcon kreuz[] = new ImageIcon[14];
	static ImageIcon karo[] = new ImageIcon[14];

	public Spielkarten() {
		
		front = new ImageIcon(getClass().getClassLoader().getResource("Karten/front.jpg"));

		// Pik
		for(int num=1; num<14; num++) {
			pik[num]  = new ImageIcon(getClass().getClassLoader().getResource("Karten/"+num+"Pik.png"));
		}
		
		// Herz
		for(int num=1; num<14; num++) {
			herz[num]  = new ImageIcon(getClass().getClassLoader().getResource("Karten/"+num+"Herz.png"));
		}
		
		// Kreuz
		for(int num=1; num<14; num++) {
			kreuz[num]  = new ImageIcon(getClass().getClassLoader().getResource("Karten/"+num+"Kreuz.png"));
		}
		
		// Karo
		for(int num=1; num<14; num++) {
			karo[num]  = new ImageIcon(getClass().getClassLoader().getResource("Karten/"+num+"Karo.png"));
		}	
		
	}
}


