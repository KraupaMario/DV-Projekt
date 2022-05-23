
import java.awt.Graphics; 
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Hintergrundbild_1_Seite extends JFrame  {
	
	public void paint (Graphics g) {
		super.paint(g);
		g.drawImage(Toolkit.getDefaultToolkit().getImage("Bilder/Hintergrund_1.png"),0,0,this);
		
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JPanel panel = new JPanel(); //fundamental für ein GUI
		JFrame frame = new JFrame(); //fundamental für ein GUI
		Hintergrundbild_1_Seite hintergrund = new Hintergrundbild_1_Seite(); 
		hintergrund.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hintergrund.setSize(1500,1300);
		
		hintergrund.setLocationRelativeTo(null);
		hintergrund.setResizable(true);
		hintergrund.setTitle("Hintergrund_1");
		hintergrund.add(panel); 
		
		hintergrund.setLayout(null);
		
		JLabel label = new JLabel("User"); 
		label.setBounds (10,20,80,25);
		hintergrund.add(label);
		
		JTextField userText = new JTextField(20); 
		userText.setBounds(400,200,165,25);
		hintergrund.add(userText);
		
		JLabel passwordlabel = new JLabel("Password"); 
		passwordlabel.setBounds(10,50,80,25);
		hintergrund.add(passwordlabel);
		
		JPasswordField passwordText = new JPasswordField(); 
		passwordText.setBounds(400,250,165,25);
		hintergrund.add(passwordText);
		
		
		JButton button1 = new JButton("Benutzername:"); 
		button1.setBounds(150,200,165,25);
		button1.addActionListener(new Benutzername_Passwort());
		hintergrund.add(button1);
		
		JButton button2 = new JButton("Passwort:"); 
		button2.setBounds(150,250,165,25);
		button2.addActionListener(new Benutzername_Passwort());
		hintergrund.add(button2);
		
		JButton button3 = new JButton("Login"); 
		button3.setBounds(400,300,165,25);
		button3.addActionListener(new Benutzername_Passwort());
		hintergrund.add(button3);
		
	
			
		
		
		JLabel success = new JLabel("");
		success.setBounds(150,300,165,25);
		hintergrund.add(success);
		
		
		frame.setVisible(true);
		
		hintergrund.setVisible(true);
		
		

	}
	
	
	}
	
	
		

	

