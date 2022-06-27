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


public class Hintergrund extends JFrame implements ActionListener {

	
	JFrame frame = new JFrame(); 
	JPanel panel = new JPanel();
	JButton button1 = new JButton ("Benutzername:");
	JButton button2 = new JButton("Passwort:");
	JButton button3 = new JButton("Login"); 
	JLabel label = new JLabel("User");
	JTextField userText = new JTextField(20); 
	JLabel passwordlabel = new JLabel("Password"); 
	JPasswordField passwordText = new JPasswordField(); 
	JLabel ueberschrift = new JLabel("BLACK JACK"); 
	JLabel bild ;
	Icon icon1; 
	Icon icon2;
	
	
	
	public Hintergrund() {
		
		
		button1.setBounds(150,200,165,25);
		
		button2.setBounds(150,250,165,25);
		
		button3.setBounds(400,300,165,25);
		
		userText.setBounds(400,200,165,25);
		
		passwordText.setBounds(400,250,165,25);
		
		ueberschrift.setBounds(150,100,1000,100);
		
		Font schriftart = new Font ("Algerian",Font.PLAIN+Font.ITALIC,80);
		ueberschrift.setFont(schriftart);
		
		frame.add(ueberschrift); 
		
		
		
		bild = new JLabel(icon1);
		button1.setFocusable(false);
		button2.setFocusable(false);
		button3.setFocusable(false);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		
		frame.add(button1); 
		frame.add(button2);
		frame.add(button3);
		frame.add(userText);
		frame.add(passwordText);
		frame.add(bild); 
		
		frame.setSize(1600,1000);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color (111111));
		
	icon1 = new ImageIcon(getClass().getResource("C:\\Users\\Jannik\\Documents\\GitHub\\DV-Projekt\\2_Quellcode\\Workspace\\GUI\\Bilder"));
	
	
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()== button3) {
			frame.dispose(); 
			NewWindow myWindow = new NewWindow(); 
		
		}
	}
	/*
	public void paint (Graphics g) {
		super.paint(g);
		g.drawImage(Toolkit.getDefaultToolkit().getImage("Bilder/Hintergrund_1.png"),0,0,this);
		
	}*/
	

}