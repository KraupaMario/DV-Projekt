import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.*;
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

public class Benutzeroberfläche extends JFrame  {


	//Variablen

	Client spiel;

	static JFrame frame = new JFrame();
	static JPanel panel = new JPanel();





	//Label
	static JLabel labelBenutzername = new JLabel ("Benutzername:");
	static JLabel labelPasswort = new JLabel("Passwort:");
	static JLabel ueberschrift = new JLabel("BLACK JACK"); 
	static JLabel labelBenutzernameErstellen = new JLabel ("Benutzername:");
	static JLabel labelPasswort1 = new JLabel("Passwort:");
	static JLabel labelPasswort2 = new JLabel("Passwort:");
	static JLabel labelipadresse = new JLabel ("IP-Adresse:");
	static JLabel labelport = new JLabel("Port:");





	//Buttons

	static JButton buttonStartSpiel = new JButton ("Spiel starten"); 
	static JButton buttonAbbrechenSpiel = new JButton ("Abbrechen"); 
	static JButton buttonLogin = new JButton("Login");
	static JButton buttonRegistrieren = new JButton("Registrieren");
	static JButton buttonstart = new JButton("Start");
	static JButton buttonRegistrierenAbschließen = new JButton("Abschließen");
	static JButton buttonZurück = new JButton("Zurück");
	static JButton buttonIPAdresseBestätigen = new JButton ("IP Adresse bestätigen");
	static JButton buttonPortBestätigen = new JButton ("Port bestätigen"); 
	



	// TextField
	static JTextField userText = new JTextField(20);
	static JTextField userRegistText = new JTextField(20);
	static JPasswordField passwordText = new JPasswordField(); 
	static JPasswordField passwordText1 = new JPasswordField(); 
	static JPasswordField passwordText2 = new JPasswordField(); 
	static JTextField ipadresseText = new JTextField(20); 
	static JTextField portText = new JTextField(20);




	//Konstruktor Fenster

	public Benutzeroberfläche(Client spiel) {

		this.spiel = spiel;

		this.setTitle("BlackJack");
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("Icon.jpg")).getImage());
		this.setSize(1600,1000);
		this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(0,81,0));

		startfenster();
		auswahlfenster();
		anmeldefenster();
		registrierfenster();
		ipadressefenster(); 
		portfenster();
	}

	//Startbildschirm
	
	public void startfenster() {
		//Buttons: 
		buttonStartSpiel.setBounds(600,550,165,25);
		buttonStartSpiel.setFocusable(false);
		buttonStartSpiel.setBorder(null);
		buttonStartSpiel.setBackground(null);
		buttonStartSpiel.setForeground(Color.white);
		buttonStartSpiel.addActionListener(spiel.aHandler);
		buttonStartSpiel.setVisible(true);
		this.add(buttonStartSpiel);
		
		buttonAbbrechenSpiel.setBounds(800,550,165,25);
		buttonAbbrechenSpiel.setFocusable(false);
		buttonAbbrechenSpiel.setBorder(null);
		buttonAbbrechenSpiel.setBackground(null);
		buttonAbbrechenSpiel.setForeground(Color.white);
		buttonAbbrechenSpiel.addActionListener(spiel.aHandler);
		buttonAbbrechenSpiel.setVisible(true);
		this.add(buttonAbbrechenSpiel);
		
	}
	
	
	//Auswahlfenster

	public void auswahlfenster() {
		
		
	//Buttons
		buttonLogin.setBounds(600,550,165,25);
		buttonLogin.setFocusable(false);
		buttonLogin.setBorder(null);
		buttonLogin.setBackground(null);
		buttonLogin.setForeground(Color.white);
		buttonLogin.addActionListener(spiel.aHandler);
		buttonLogin.setVisible(true);
		this.add(buttonLogin);
		
		
		buttonRegistrieren.setBounds(800,550,165,25);
		buttonRegistrieren.setFocusable(false);
		buttonRegistrieren.setBorder(null);
		buttonRegistrieren.setBackground(null);
		buttonRegistrieren.setForeground(Color.white);
		buttonRegistrieren.addActionListener(spiel.aHandler);
		buttonRegistrieren.setVisible(true);
		this.add(buttonRegistrieren);
		
	}

	
	
	
	
	
	
	//Loginfenster


	public void anmeldefenster() {

		//Button 1: Benutzername: 
		labelBenutzername.setBounds(500,350,165,25);
		labelBenutzername.setFocusable(false);
		labelBenutzername.setBorder(null);
		labelBenutzername.setBackground(null);
		labelBenutzername.setForeground(Color.white);
		labelBenutzername.setVisible(false);
		this.add(labelBenutzername); 

		//Button 2: Passwort: 
		labelPasswort.setBounds(500,450,165,25);
		labelPasswort.setFocusable(false);
		labelPasswort.setBorder(null);
		labelPasswort.setBackground(null);
		labelPasswort.setForeground(Color.white);
		labelPasswort.setVisible(false);
		this.add(labelPasswort);

		//Button 3: Login:
		buttonstart.setBounds(800,550,165,25);
		buttonstart.setFocusable(false);
		buttonstart.setBorder(null);
		buttonstart.setBackground(null);
		buttonstart.setForeground(Color.white);
		buttonstart.addActionListener(spiel.aHandler);
		buttonstart.setVisible(false);
		this.add(buttonstart);


		buttonZurück.setBounds(800,750,165,25);
		buttonZurück.setFocusable(false);
		buttonZurück.setBorder(null);
		buttonZurück.setBackground(null);
		buttonZurück.setForeground(Color.white);
		buttonZurück.addActionListener(spiel.aHandler);
		buttonZurück.setVisible(false);
		this.add(buttonZurück);
		
		// TextField: Benutzername:
		userText.setBounds(800,350,165,25);
		userText.setVisible(false);
		this.add(userText);

		//PasswordField: Passwort:
		passwordText.setBounds(800,450,165,25);
		passwordText.setVisible(false);
		this.add(passwordText);

		//Überschrift: 
		ueberschrift.setBounds(400,100,1000,200);
		Font schriftart = new Font ("Algerian",Font.PLAIN+Font.ITALIC,120);
		ueberschrift.setFont(schriftart);
		ueberschrift.setVisible(true);
		this.add(ueberschrift); 


	}

	
	
	//Registrierungsfenster
	
	public void registrierfenster() {

		
		labelBenutzernameErstellen.setBounds(500,350,165,25);
		labelBenutzernameErstellen.setFocusable(false);
		labelBenutzernameErstellen.setBorder(null);
		labelBenutzernameErstellen.setBackground(null);
		labelBenutzernameErstellen.setForeground(Color.white);
		labelBenutzernameErstellen.setVisible(false);
		this.add(labelBenutzernameErstellen); 

		
		labelPasswort1.setBounds(500,450,165,25);
		labelPasswort1.setFocusable(false);
		labelPasswort1.setBorder(null);
		labelPasswort1.setBackground(null);
		labelPasswort1.setForeground(Color.white);
		labelPasswort1.setVisible(false);
		this.add(labelPasswort1);

		labelPasswort2.setBounds(500,550,165,25);
		labelPasswort2.setFocusable(false);
		labelPasswort2.setBorder(null);
		labelPasswort2.setBackground(null);
		labelPasswort2.setForeground(Color.white);
		labelPasswort2.setVisible(false);
		this.add(labelPasswort2);
		
		
		
		buttonRegistrierenAbschließen.setBounds(800,650,165,25);
		buttonRegistrierenAbschließen.setFocusable(false);
		buttonRegistrierenAbschließen.setBorder(null);
		buttonRegistrierenAbschließen.setBackground(null);
		buttonRegistrierenAbschließen.setForeground(Color.white);
		buttonRegistrierenAbschließen.addActionListener(spiel.aHandler);
		buttonRegistrierenAbschließen.setVisible(false);
		this.add(buttonRegistrierenAbschließen);

		// TextField: Benutzername:
		userRegistText.setBounds(800,350,165,25);
		userRegistText.setVisible(false);
		this.add(userRegistText);

		//PasswordField:
		passwordText1.setBounds(800,450,165,25);
		passwordText1.setVisible(false);
		this.add(passwordText1);

		passwordText2.setBounds(800,550,165,25);
		passwordText2.setVisible(false);
		this.add(passwordText2);
	}
		
		//ipadressefenster: 
		
		public void ipadressefenster() {
			
			
			//Labels:
			labelipadresse.setBounds(500,350,165,25);
			labelipadresse.setFocusable(false);
			labelipadresse.setBorder(null);
			labelipadresse.setBackground(null);
			labelipadresse.setForeground(Color.white);
			labelipadresse.setVisible(false);
			this.add(labelipadresse);
			
			// TextField: Benutzername:
			ipadresseText.setBounds(800,350,165,25);
			ipadresseText.setVisible(false);
			this.add(ipadresseText);

			//Buttons:	
			buttonIPAdresseBestätigen.setBounds(800,550,165,25);
			buttonIPAdresseBestätigen.setFocusable(false);
			buttonIPAdresseBestätigen.setBorder(null);
			buttonIPAdresseBestätigen.setBackground(null);
			buttonIPAdresseBestätigen.setForeground(Color.white);
			buttonIPAdresseBestätigen.addActionListener(spiel.aHandler);
			buttonIPAdresseBestätigen.setVisible(true);
			this.add(buttonIPAdresseBestätigen);
		}

		public void portfenster() {
			
			//Labels:
			labelport.setBounds(500,350,165,25);
			labelport.setFocusable(false);
			labelport.setBorder(null);
			labelport.setBackground(null);
			labelport.setForeground(Color.white);
			labelport.setVisible(false);
			this.add(labelport);
			
			// TextField: Benutzername:
			portText.setBounds(800,350,165,25);
			portText.setVisible(false);
			this.add(portText);

			//Buttons:	
			buttonPortBestätigen.setBounds(800,550,165,25);
			buttonPortBestätigen.setFocusable(false);
			buttonPortBestätigen.setBorder(null);
			buttonPortBestätigen.setBackground(null);
			buttonPortBestätigen.setForeground(Color.white);
			buttonPortBestätigen.addActionListener(spiel.aHandler);
			buttonPortBestätigen.setVisible(true);
			this.add(buttonPortBestätigen);




		
		
		//Überschrift: 
		ueberschrift.setBounds(400,100,1000,200);
		Font schriftart = new Font ("Algerian",Font.PLAIN+Font.ITALIC,120);
		ueberschrift.setFont(schriftart);
		ueberschrift.setVisible(false);
		this.add(ueberschrift); 


	}
	
	
	
	
	
	
	
	
	//Spielbildschirm 
	
	
	
	
	
	
}
