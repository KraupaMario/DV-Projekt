package Game;
import javax.swing.ImageIcon;

public class Spielkarten {
	ImageIcon front = new ImageIcon();
	ImageIcon pik[] = new ImageIcon[14];
	ImageIcon herz[] = new ImageIcon[14];
	ImageIcon kreuz[] = new ImageIcon[14];
	ImageIcon karo[] = new ImageIcon[14];

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


