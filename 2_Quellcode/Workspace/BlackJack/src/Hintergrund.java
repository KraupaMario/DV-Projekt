
	import javax.swing.JFrame; 
	import java.awt.*; 

	public class Hintergrund extends JFrame {
		
		public Hintergrund (String title) {
			super (title); 
			
		}
		
		public void paint (Graphics g) {
			super.paint (g); 
			g.drawImage(Toolkit.getDefaultToolkit().getImage("bild/background.png"),0,0,this); 
			
			
		}

		public static void main(String[] args) {
			Hintergrund hi = new Hintergrund ("Casino"); 
			hi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			hi.setSize(900,400); 
			hi.setVisible(true); 
			

		}

	}


