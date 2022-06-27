import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Startfenster extends JFrame implements ActionListener {
	JFrame frame = new JFrame(); 
	JPanel panel = new JPanel();
	//Button 1: Spiel starten
	JButton button1 = new JButton ("Spiel starten");
	//Button 2: Abbrechen
	JButton button2 = new JButton("Abbrechen");
	//Überschrift
	JLabel ueberschrift = new JLabel("BLACK JACK");

	public Startfenster() {
		
		//Button 1: Spiel starten
		button1.setBounds(700,350,165,25);
		button1.setFocusable(false);
		button1.addActionListener(this);
		frame.add(button1);

		//Button 2: Abbrechen
		button2.setBounds(700,450,165,25);
		button2.setFocusable(false);
		button2.addActionListener(this);
		frame.add(button2);
		
		//Überschrift: 
		ueberschrift.setBounds(400,100,1000,200);
		Font schriftart = new Font ("Algerian",Font.PLAIN+Font.ITALIC,120);
		ueberschrift.setFont(schriftart);
		frame.add(ueberschrift); 

		//Fenster: 
		frame.setSize(1600,1000);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color (111111));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== button1) {
			frame.dispose(); 
			Anmeldefenster anmeldefenster = new Anmeldefenster(); 
		}
		if (e.getSource()== button2) {
			frame.dispose(); 

		}
	}
}