package Game;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.InetAddress;
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

	Server2 spiel;
	

	 JFrame frame = new JFrame();
	 JPanel panel = new JPanel();
	
	





	//Label
	 JLabel labelBenutzername = new JLabel ("Benutzername:");
	 JLabel labelPasswort = new JLabel("Passwort:");
	 JLabel ueberschrift = new JLabel("BLACK JACK"); 
	 JLabel ueberschriftSpiel = new JLabel ("BLACK JACK"); 
	 JLabel labelSpieler1 = new JLabel ("Spieler1");
	 JLabel labelSpieler2 = new JLabel ("Spieler2"); 
	 JLabel labelBank = new JLabel ("Bank"); 
	 JLabel labelBenutzernameErstellen = new JLabel ("Benutzername:");
	 JLabel labelPasswort1 = new JLabel("Passwort:");
	 JLabel labelPasswort2 = new JLabel("Passwort:");
	 JLabel labelipadresse = new JLabel ("IP-Adresse:");
	 JLabel labelport = new JLabel("Port:");
	 JLabel einsatzSpieler1 = new JLabel ("Einsatz Spieler1:"); 
	 JLabel einsatzSpieler2 = new JLabel ("Einsatz Spieler2:"); 
	 JLabel kontostandSpieler1 = new JLabel ("Kontostand Spieler1:"); 
	 JLabel kontostandSpieler2 = new JLabel ("Kontostand Spieler2:");
	 JLabel einsatzausgabe = new JLabel();
	 JLabel labelIPAdresse = new JLabel(); 
	 
	 

	 

	 Icon jeton_10 = new ImageIcon(getClass().getResource("Jeton_10.PNG"));
	 Icon jeton_25 = new ImageIcon(getClass().getResource("Jeton_25.PNG"));
	 Icon jeton_50 = new ImageIcon(getClass().getResource("Jeton_50.PNG")); 
	 Icon jeton_100 = new ImageIcon(getClass().getResource("Jeton_100.PNG"));
	 Icon testkarte =  new ImageIcon(getClass().getClassLoader().getResource("Karten/13Karo.png"));
	 
	 JLabel karte1Spieler1 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Karten/5Kreuz.png")));
	 JLabel karte2Spieler1 = new JLabel(testkarte);
	 JLabel karte3Spieler1 = new JLabel(testkarte);
	 JLabel karte4Spieler1 = new JLabel(testkarte);
	 JLabel karte5Spieler1 = new JLabel(testkarte);
	 
	 JLabel karte1Spieler2 = new JLabel(testkarte);
	 JLabel karte2Spieler2 = new JLabel(testkarte);
	 JLabel karte3Spieler2 = new JLabel(testkarte);
	 JLabel karte4Spieler2 = new JLabel(testkarte);
	 JLabel karte5Spieler2 = new JLabel(testkarte);
	 
	 JLabel karte1Bank = new JLabel(testkarte); 
	 JLabel karte2Bank = new JLabel(testkarte); 
	 JLabel karte3Bank = new JLabel(testkarte); 
	 JLabel karte4Bank = new JLabel(testkarte); 
	 JLabel karte5Bank = new JLabel(testkarte); 


	//Buttons

	 JButton buttonStartSpiel = new JButton ("Spiel starten"); 
	 JButton buttonAbbrechenSpiel = new JButton ("Abbrechen"); 
	 JButton buttonLogin = new JButton("Login");
	 JButton buttonRegistrieren = new JButton("Registrieren");
	 JButton buttonstart = new JButton("Start");
	 JButton buttonRegistrierenAbschließen = new JButton("Abschließen");
	 JButton buttonZurück = new JButton("Zurück");
	 JButton buttonIPAdresseBestätigen = new JButton ("IP Adresse bestätigen");
	 JButton buttonPortBestätigen = new JButton ("Port bestätigen"); 
	 JButton buttonEinsatz = new JButton ("Einsatz");
	 JButton buttonJeton10 = new JButton("Jeton10",jeton_10);
	 JButton buttonJeton25 = new JButton("Jeton25", jeton_25);
	 JButton buttonJeton50 = new JButton ("Jeton50", jeton_50);
	 JButton buttonJeton100 = new JButton("Jeton100", jeton_100);
	 JButton buttonEinsatzbestätigen = new JButton("Einsatz bestätigen");
	
	 
	 
	 
	 


	// TextField
	public JTextField userText = new JTextField(20);
	public JTextField userRegistText = new JTextField(20);
	public JPasswordField passwordText = new JPasswordField(); 
	public JPasswordField passwordText1 = new JPasswordField(); 
	public JPasswordField passwordText2 = new JPasswordField(); 
	public JTextField ipadresseText = new JTextField(20); 
	public JTextField portText = new JTextField(20);
	
	 //Icons
	
	




	//Konstruktor Fenster

	public Benutzeroberfläche(Server2 server2) {

		this.spiel = server2;

		this.setTitle("BlackJack");
		//this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("Icon.jpg")).getImage());
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
		einsatzfenster(); 
		jetonfenster();
		 
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
		userText.addActionListener(spiel.aHandler);
		this.add(userText);

		//PasswordField: Passwort:
		passwordText.setBounds(800,450,165,25);
		passwordText.setVisible(false);
		passwordText.addActionListener(spiel.aHandler);
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
		userRegistText.addActionListener(spiel.aHandler);
		this.add(userRegistText);

		//PasswordField:
		passwordText1.setBounds(800,450,165,25);
		passwordText1.setVisible(false);
		passwordText1.addActionListener(spiel.aHandler);
		this.add(passwordText1);

		passwordText2.setBounds(800,550,165,25);
		passwordText2.setVisible(false);
		passwordText2.addActionListener(spiel.aHandler);
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
			
			labelIPAdresse.setBounds(700,350,165,25);
			labelIPAdresse.setFocusable(false);
			labelIPAdresse.setBorder(null);
			labelIPAdresse.setBackground(null);
			labelIPAdresse.setForeground(Color.white);
			labelIPAdresse.setVisible(false);
			this.add(labelIPAdresse);
			
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
			buttonIPAdresseBestätigen.setVisible(false);
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
			buttonPortBestätigen.setVisible(false);
			this.add(buttonPortBestätigen);




	 


	}

	
	//Spielfenster
		
	
		
		public void einsatzfenster() {
			
		
		//Buttons:	
		buttonEinsatz.setBounds(0,700,1600,100);
		buttonEinsatz.setFocusable(false);
		buttonEinsatz.setBorder(null);
		buttonEinsatz.setBackground(null);
		buttonEinsatz.setForeground(Color.white);
		buttonEinsatz.addActionListener(spiel.aHandler);
		buttonEinsatz.setVisible(false);
		this.add(buttonEinsatz);
		
		//Überschrift: 
		ueberschriftSpiel.setBounds(500,250,1000,200);
		Font schriftart = new Font ("Algerian",Font.PLAIN+Font.ITALIC,80);
		ueberschriftSpiel.setForeground(Color.yellow);
		ueberschriftSpiel.setFont(schriftart);
		ueberschriftSpiel.setVisible(false);
		this.add(ueberschriftSpiel); 
		
		//Überschrift: 
		labelSpieler1.setBounds(200,500,1000,200);
		Font schriftart1 = new Font("Algerian",Font.PLAIN+Font.ITALIC,40);
		labelSpieler1.setForeground(Color.yellow); 
		labelSpieler1.setFont(schriftart1);
		labelSpieler1.setVisible(false);
		this.add(labelSpieler1); 
		
		//Überschrift: 
		labelSpieler2.setBounds(1100,500,1000,200);
		Font schriftart2 = new Font("Algerian",Font.PLAIN+Font.ITALIC,40);
		labelSpieler2.setForeground(Color.yellow); 
		labelSpieler2.setFont(schriftart2);
		labelSpieler2.setVisible(false);
		this.add(labelSpieler2);

		//Überschrift: 
		labelBank.setBounds(600,10,1000,100);
		Font schriftart3 = new Font("Algerian",Font.PLAIN+Font.ITALIC,40);
		labelBank.setForeground(Color.yellow); 
		labelBank.setFont(schriftart3);
		labelBank.setVisible(false);
		this.add(labelBank);
		
		//Karten: 
		karte1Spieler1.setBounds(1000,425,60,200);
		karte1Spieler1.setFocusable(false);
		karte1Spieler1.setBorder(null);
		karte1Spieler1.setVisible(false);
		this.add(karte1Spieler1); 
		
		karte2Spieler1.setBounds(1100,425,60,200);
		karte2Spieler1.setFocusable(false);
		karte2Spieler1.setBorder(null);
		karte2Spieler1.setVisible(false);
		this.add(karte2Spieler1);
		
		karte3Spieler1.setBounds(1200,425,60,200);
		karte3Spieler1.setFocusable(false);
		karte3Spieler1.setBorder(null);
		karte3Spieler1.setVisible(false);
		this.add(karte3Spieler1);
		
		karte4Spieler1.setBounds(1300,425,60,200);
		karte4Spieler1.setFocusable(false);
		karte4Spieler1.setBorder(null);
		karte4Spieler1.setVisible(false);
		this.add(karte4Spieler1);
		
		karte5Spieler1.setBounds(1400,425,60,200);
		karte5Spieler1.setFocusable(false);
		karte5Spieler1.setBorder(null);
		karte5Spieler1.setVisible(false);
		this.add(karte5Spieler1);
		
		karte1Spieler2.setBounds(100,425,60,200);
		karte1Spieler2.setFocusable(false);
		karte1Spieler2.setBorder(null);
		karte1Spieler2.setVisible(false);
		this.add(karte1Spieler2);
		
		karte2Spieler2.setBounds(200,425,60,200);
		karte2Spieler2.setFocusable(false);
		karte2Spieler2.setBorder(null);
		karte2Spieler2.setVisible(false);
		this.add(karte2Spieler2);
		
		karte3Spieler2.setBounds(300,425,60,200);
		karte3Spieler2.setFocusable(false);
		karte3Spieler2.setBorder(null);
		karte3Spieler2.setVisible(false);
		this.add(karte3Spieler2);
		
		karte4Spieler2.setBounds(400,425,60,200);
		karte4Spieler2.setFocusable(false);
		karte4Spieler2.setBorder(null);
		karte4Spieler2.setVisible(false);
		this.add(karte4Spieler2);
		
		karte5Spieler2.setBounds(500,425,60,200);
		karte5Spieler2.setFocusable(false);
		karte5Spieler2.setBorder(null);
		karte5Spieler2.setVisible(false);
		this.add(karte5Spieler2);
		
		karte1Bank.setBounds(500,100,60,200);
		karte1Bank.setFocusable(false);
		karte1Bank.setBorder(null);
		karte1Bank.setVisible(false);
		this.add(karte1Bank);
		
		karte2Bank.setBounds(600,100,60,200);
		karte2Bank.setFocusable(false);
		karte2Bank.setBorder(null);
		karte2Bank.setVisible(false);
		this.add(karte2Bank);
		
		karte3Bank.setBounds(700,100,60,200);
		karte3Bank.setFocusable(false);
		karte3Bank.setBorder(null);
		karte3Bank.setVisible(false);
		this.add(karte3Bank);
		
		karte4Bank.setBounds(800,100,60,200);
		karte4Bank.setFocusable(false);
		karte4Bank.setBorder(null);
		karte4Bank.setVisible(false);
		this.add(karte4Bank);
		
		karte5Bank.setBounds(900,100,60,200);
		karte5Bank.setFocusable(false);
		karte5Bank.setBorder(null);
		karte5Bank.setVisible(false);
		this.add(karte5Bank);
		
		
		
		}
		
		public void jetonfenster() {
			buttonJeton10.setBounds(100,700,100,100);
			buttonJeton10.setFocusable(false);
			buttonJeton10.setBorder(null);
			buttonJeton10.setBackground(null);
			buttonJeton10.setForeground(Color.white);
			buttonJeton10.addActionListener(spiel.aHandler);
			buttonJeton10.setVisible(false);
			this.add(buttonJeton10);
			
			buttonJeton25.setBounds(400,700,100,100);
			buttonJeton25.setFocusable(false);
			buttonJeton25.setBorder(null);
			buttonJeton25.setBackground(null);
			buttonJeton25.setForeground(Color.white);
			buttonJeton25.addActionListener(spiel.aHandler);
			buttonJeton25.setVisible(false);
			this.add(buttonJeton25);
			
			buttonJeton50.setBounds(700,700,100,100);
			buttonJeton50.setFocusable(false);
			buttonJeton50.setBorder(null);
			buttonJeton50.setBackground(null);
			buttonJeton50.setForeground(Color.white);
			buttonJeton50.addActionListener(spiel.aHandler);
			buttonJeton50.setVisible(false);
			this.add(buttonJeton50);
			
			buttonJeton100.setBounds(1100,700,100,100);
			buttonJeton100.setFocusable(false);
			buttonJeton100.setBorder(null);
			buttonJeton100.setBackground(null);
			buttonJeton100.setForeground(Color.white);
			buttonJeton100.addActionListener(spiel.aHandler);
			buttonJeton100.setVisible(false);
			this.add(buttonJeton100);
			
			buttonEinsatzbestätigen.setBounds(700,400,200,50);
			buttonEinsatzbestätigen.setFocusable(false);
			buttonEinsatzbestätigen.setBorder(null);
			buttonEinsatzbestätigen.setBackground(null);
			buttonEinsatzbestätigen.setForeground(Color.white);
			buttonEinsatzbestätigen.addActionListener(spiel.aHandler);
			buttonEinsatzbestätigen.setVisible(false);
			this.add(buttonEinsatzbestätigen);
			
			einsatzausgabe.setBounds(1350,650,165,25);
			einsatzausgabe.setFocusable(false);
			einsatzausgabe.setBorder(null);
			einsatzausgabe.setBackground(null);
			einsatzausgabe.setForeground(Color.white);
			einsatzausgabe.setVisible(false);
			this.add(einsatzausgabe);
			
			einsatzSpieler1.setBounds(10,600,165,25);
			einsatzSpieler1.setFocusable(false);
			einsatzSpieler1.setBorder(null);
			einsatzSpieler1.setBackground(null);
			einsatzSpieler1.setForeground(Color.white);
			einsatzSpieler1.setVisible(false);
			this.add(einsatzSpieler1);
			
			einsatzSpieler2.setBounds(1350,600,165,25);
			einsatzSpieler2.setFocusable(false);
			einsatzSpieler2.setBorder(null);
			einsatzSpieler2.setBackground(null);
			einsatzSpieler2.setForeground(Color.white);
			einsatzSpieler2.setVisible(false);
			this.add(einsatzSpieler2);
			
			kontostandSpieler1.setBounds(10,10,165,25);
			kontostandSpieler1.setFocusable(false);
			kontostandSpieler1.setBorder(null);
			kontostandSpieler1.setBackground(null);
			kontostandSpieler1.setForeground(Color.white);
			kontostandSpieler1.setVisible(false);
			this.add(kontostandSpieler1);
			
			kontostandSpieler2.setBounds(1350,10,165,25);
			kontostandSpieler2.setFocusable(false);
			kontostandSpieler2.setBorder(null);
			kontostandSpieler2.setBackground(null);
			kontostandSpieler2.setForeground(Color.white);
			kontostandSpieler2.setVisible(false);
			this.add(kontostandSpieler2);
			
		}
		
	
	
	
	
	
	
}
