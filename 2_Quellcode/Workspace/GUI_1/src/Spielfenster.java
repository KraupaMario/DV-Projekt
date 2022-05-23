import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Spielfenster extends JFrame implements ActionListener{
	JFrame frame = new JFrame(); 
	JPanel panel = new JPanel();
	
	//Button 1: Fenster mit Casinotisch,...
	JButton button1 = new JButton ("Fenster mit Casinotisch,...");
	
	
	Spielfenster () {
		
		//Button 1: Fenster mit Casinotisch,...
		button1.setBounds(150,200,500,25);
		button1.setFocusable(false);
		button1.addActionListener(this);
		frame.add(button1); 
		
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
		
	}
	
}

