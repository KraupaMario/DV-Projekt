import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Spielfenster extends JFrame implements ActionListener{
	JFrame frame = new JFrame(); 
	JPanel panel = new JPanel();
	
	
	//Button 10: Einsatz 
	JButton button10 = new JButton("Einsatz");
	
	//Button20: Halten
	JButton button20 = new JButton("Halten");
	
	//Button30: Karte
	JButton button30 = new JButton("Karte");
	
	Icon hintergrundtisch = new ImageIcon(getClass().getResource("Hintergrundtisch.PNG"));
	JLabel label1 = new JLabel(hintergrundtisch) ;
	
	 	
		Icon jeton_10 = new ImageIcon(getClass().getResource("Jeton_10.PNG"));
		//Button 1: 10er Jeton: 
		JButton button1 = new JButton(jeton_10) ;
		//Button 100: 10er Jeton: 
		JButton button100 = new JButton(jeton_10);
		
		Icon jeton_25 = new ImageIcon(getClass().getResource("Jeton_25.PNG"));
		//Button 2: 25er Jeton: 
		JButton button2 = new JButton(jeton_25);
		//Button 250: 25er Jeton: 
		JButton button250 = new JButton(jeton_25);
		
		Icon jeton_50 = new ImageIcon(getClass().getResource("Jeton_50.PNG")); 
		//Button 3: 50er Jeton: 
		JButton button3 = new JButton(jeton_50); 
		//Button 500: 50er Jeton: 
		JButton button500 = new JButton(jeton_50);
		
		Icon jeton_100 = new ImageIcon(getClass().getResource("Jeton_100.PNG")); 
		//Button 4: 100er Jeton: 
		JButton button4 = new JButton (jeton_100);
		//Button 1000: 100er Jeton: 
		JButton button1000 = new JButton (jeton_100); 
	
	
	
	
	Spielfenster () {
		
		//Button 10: Einsatz 
		button10.setBounds(0,700,1600,100);
		button10.setFocusable(false);
		frame.add(button10);
		button10.addActionListener(this);
		
		//Button 1: 10er Jeton 
		button1.setBounds(100,700,100,100);
		button1.setFocusable(false);
		button1.addActionListener(this);
		frame.add(button1);
		
		//Button 2: 25er Jeton
		button2.setBounds(400,700,100,100);
		button2.setFocusable(false);
		button2.addActionListener(this);
		frame.add(button2);
		
		//Button 3: 50er Jeton
		button3.setBounds(700,700,100,100);
		button3.setFocusable(false);
		button3.addActionListener(this);
		frame.add(button3);
		
		//Button 4: 100er Jeton
		button4.setBounds(1100,700,100,100);
		button4.setFocusable(false);
		button4.addActionListener(this);
		frame.add(button4);
		
		
		//Button 100: 10er Jeton
		button100.setBounds(1100,400,100,100);
		button100.setFocusable(false);
		button100.addActionListener(this);
		button100.setVisible(false);
		frame.add(button100);
		
		//Button 250: 25er Jeton
		button250.setBounds(1100,400,100,100);
		button250.setFocusable(false);
		button250.addActionListener(this);
		button250.setVisible(false);
		frame.add(button250);
		
		//Button 500: 50er Jeton
		button500.setBounds(1100,400,100,100);
		button500.setFocusable(false);
		button500.addActionListener(this);
		button500.setVisible(false);
		frame.add(button500);
		
		//Button 1000: 100er Jeton
		button1000.setBounds(1100,400,100,100);
		button1000.setFocusable(false);
		button1000.addActionListener(this);
		button1000.setVisible(false);
		frame.add(button1000);
		
		//Button 20: Halten
		
		button20.setBounds(0,700,800,100);
		button20.setFocusable(false);
		frame.add(button20);
		button20.addActionListener(this);
		button20.setVisible(false);
		
		
		//Button 4: Karte
		
		button30.setBounds(800,700,1600,100);
		button30.setFocusable(false);
		frame.add(button30);
		button30.addActionListener(this);
		button30.setVisible(false);
		
		
		// Label 1: Hintergrund
		label1.setBounds(300,-100,1000,800);
		label1.setFocusable(false);
		
		frame.add(label1);
		
		
		
		
		
		
		 
		//Fenster: 
		
		frame.setSize(1600,1000);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setBackground(new Color (111111));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== button10) {
			button10.setVisible(false);
			
		
	}
		if(e.getSource()== button1) {
			button100.setVisible(true);
			button1.setVisible(false);
			button2.setVisible(false);
			button3.setVisible(false);
			button4.setVisible(false);
			button20.setVisible(true);
			button30.setVisible(true);
		}
		else if(e.getSource()== button2) {
			button250.setVisible(true);
			button1.setVisible(false);
			button2.setVisible(false);
			button3.setVisible(false);
			button4.setVisible(false);
			button20.setVisible(true);
			button30.setVisible(true);
			
		}
		else if(e.getSource()== button3) {
			button500.setVisible(true);
			button1.setVisible(false);
			button2.setVisible(false);
			button3.setVisible(false);
			button4.setVisible(false);
			button20.setVisible(true);
			button30.setVisible(true);
		}
		else if(e.getSource()== button4) {
			button1000.setVisible(true);
			button1.setVisible(false);
			button2.setVisible(false);
			button3.setVisible(false);
			button4.setVisible(false);
			button20.setVisible(true);
			button30.setVisible(true);
		}
		
	}
	
}

