


	import javax.swing.JButton;

	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.JPasswordField;
	import javax.swing.JTextField;
	import java.awt.event.ActionEvent; 
	import java.awt.event.ActionListener;
	import java.awt.Graphics; 
	import java.awt.Toolkit; 

	public class Benutzername_Passwort implements ActionListener{ //GUI: Graphical User Interface


		public static void main(String[] args) {

			JPanel panel = new JPanel(); //fundamental für ein GUI
			JFrame frame = new JFrame(); //fundamental für ein GUI
			frame.setSize(700,700);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			frame.add(panel); 
			
			panel.setLayout(null);
			
			JLabel label = new JLabel("User"); 
			label.setBounds (10,20,80,25);
			panel.add(label);
			
			JTextField userText = new JTextField(20); 
			userText.setBounds(100,20,165,25);
			panel.add(userText);
			
			JLabel passwordlabel = new JLabel("Password"); 
			passwordlabel.setBounds(10,50,80,25);
			panel.add(passwordlabel);
			
			JPasswordField passwordText = new JPasswordField(); 
			passwordText.setBounds(100,50,165,25);
			panel.add(passwordText);
			
			
			JButton button = new JButton("Login"); 
			button.setBounds(10,80,80,25);
			button.addActionListener(new Benutzername_Passwort());
			panel.add(button);
			
			JLabel success = new JLabel("");
			success.setBounds(10,110,300,25);
			panel.add(success);
			
			
			frame.setVisible(true);

		}
		public void actionPerformed(ActionEvent e) {
			System.out.println("Button clicked");
		}

	}
