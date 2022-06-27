import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
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

import java.awt.Font;


public class Anmeldefenster extends JFrame implements ActionListener {


	JFrame frame = new JFrame(); 
	JPanel panel = new JPanel();
	//Button 1: Benutzername
	JButton button1 = new JButton ("Benutzername:");
	//Button 2: Passwort
	JButton button2 = new JButton("Passwort:");
	//Button 3: Login
	JButton button3 = new JButton("Login");
	// TextField: Benutzername:
	JTextField userText = new JTextField(20);  
	//PasswordField: Passwort:
	JPasswordField passwordText = new JPasswordField(); 
	//Überschrift
	JLabel ueberschrift = new JLabel("BLACK JACK"); 




	public Anmeldefenster() {

		//Button 1: Benutzername: 
		button1.setBounds(500,350,165,25);
		button1.setFocusable(false);
		button1.addActionListener(this);
		frame.add(button1); 

		//Button 2: Passwort: 
		button2.setBounds(500,450,165,25);
		button2.setFocusable(false);
		button2.addActionListener(this);
		frame.add(button2);

		//Button 3: Login:
		button3.setBounds(800,550,165,25);
		button3.setFocusable(false);
		button3.addActionListener(this);
		frame.add(button3);

		// TextField: Benutzername:
		userText.setBounds(800,350,165,25);
		frame.add(userText);

		//PasswordField: Passwort:
		passwordText.setBounds(800,450,165,25);
		frame.add(passwordText);

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
		// TODO Auto-generated method stub
		if (e.getSource()== button3) {
			frame.dispose(); 
			Spielfenster spielfenster = new Spielfenster(); 

		}
	}



}