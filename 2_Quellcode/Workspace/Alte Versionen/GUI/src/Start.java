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

public class Start extends JFrame implements ActionListener {
	JFrame frame = new JFrame(); 
	JPanel panel = new JPanel();
	JButton button1 = new JButton ("Spiel starten");
	JButton button2 = new JButton("Abbrechen");
	
	public Start() {
		button1.setBounds(150,200,165,25);
		button2.setBounds(150,250,165,25);
		button1.setFocusable(false);
		button2.setFocusable(false);
		frame.add(button1); 
		frame.add(button2);
		frame.setSize(1000,1000);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color (111111));
	}
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== button1) {
			frame.dispose(); 
			Hintergrund hintergrund = new Hintergrund(); 
	}

}
}
