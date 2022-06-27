import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Spielfenster2 extends JFrame implements ActionListener{
	JFrame frame = new JFrame(); 
	JPanel panel = new JPanel();
	
	
	
	
	//Button 1: 10er Jeton: 	
	Icon jeton_10 = new ImageIcon(getClass().getResource("Jeton_10.PNG"));
	JButton button1 = new JButton(jeton_10) ;
	
	Icon jeton_25 = new ImageIcon(getClass().getResource("Jeton_25.PNG"));
	JButton button2 = new JButton(jeton_25);
	
	Icon jeton_50 = new ImageIcon(getClass().getResource("Jeton_50.PNG")); 
	JButton button3 = new JButton(jeton_50); 
	
	Icon jeton_100 = new ImageIcon(getClass().getResource("Jeton_100.PNG")); 
	JButton button4 = new JButton (jeton_100); 
	
	
	
	
	
	Spielfenster2 () {
		
		
		
		button1.setBounds(100,400,150,150);
		button1.setFocusable(false);
		button1.addActionListener(this);
		frame.add(button1);
		
		button2.setBounds(400,400,150,150);
		button2.setFocusable(false);
		button2.addActionListener(this);
		frame.add(button2);
		
		button3.setBounds(700,400,150,150);
		button3.setFocusable(false);
		button3.addActionListener(this);
		frame.add(button3);
		
		button4.setBounds(1000,400,150,150);
		button4.setFocusable(false);
		button4.addActionListener(this);
		frame.add(button4);
		
		
		
		
		 
		//Fenster: 
		
		frame.setSize(1600,1000);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color (111111));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
			
		
	
	}
	
}
