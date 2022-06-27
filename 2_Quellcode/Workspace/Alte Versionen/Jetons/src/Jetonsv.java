import java.awt.FlowLayout; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JOptionPane; 

public class Jetonsv extends JFrame{
	 private JButton bLinks, bRechts, bMitte, bUmdrehen; 
	 
	 
	 Jetonsv() {
		 super("Buttons zu verkaufen!");
		 setLayout(new FlowLayout()); 
		 
		 bLinks = new JButton("links"); 
		 bRechts = new JButton("rechts"); 
		 bMitte = new JButton("mitte"); 
		 add(bLinks); 
		 add(bMitte); 
		 add(bRechts); 
		
		 Icon normal = new ImageIcon(getClass().getResource("normal.png"));
		 Icon hover = new ImageIcon(getClass().getResource("hover.png"));
		 bUmdrehen = new JButton("umdrehen", normal); 
		 bUmdrehen.setRolloverIcon(hover);
		 add(bUmdrehen);
		 
		 
		 DerHandler handler = new DerHandler(); 
		 bLinks.addActionListener(handler);
		 bRechts.addActionListener(handler);
		 bMitte.addActionListener(handler);
		 bUmdrehen.addActionListener(handler);
	 }
class DerHandler implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(event.getSource()==bLinks)
			JOptionPane.showMessageDialog(null,"gehe nach links!"); 
		else if (event.getSource()==bRechts)
			JOptionPane.showMessageDialog(null,"gehe nach rechts!"); 
		else if (event.getSource()==bMitte)
			JOptionPane.showMessageDialog(null,"gehe weiter gerade aus!"); 
		else if (event.getSource()==bUmdrehen)
			JOptionPane.showMessageDialog(null,"drehe um!"); 
	
	}
	
}


}
