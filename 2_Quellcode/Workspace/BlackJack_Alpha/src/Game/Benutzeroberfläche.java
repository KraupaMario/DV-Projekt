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
	 JLabel unterueberschriftSpiel = new JLabel ("Casino Las Vegas"); 
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
	 JLabel kontostandSpieler2 = new JLabel ("Kontostand Spieler2: privat!!!");
	 JLabel einsatzausgabeSpieler1 = new JLabel();
	 JLabel einsatzausgabeSpieler2 = new JLabel();
	 JLabel labelIPAdresse = new JLabel(); 
	 JLabel kartenwertSpieler1 = new JLabel(); 
	 JLabel kartenwertSpieler2 = new JLabel(); 
	 JLabel kartenwertDealer = new JLabel();
	 JLabel nachrichtS1 = new JLabel();
	 JLabel nachrichtS2 = new JLabel();
     JLabel anmeldetext = new JLabel();
     JLabel registrierungtext = new JLabel();
     JLabel IPText = new JLabel();
      
     
	 

	 Icon jeton_10 = new ImageIcon(getClass().getResource("Jeton_10.PNG"));
	 Icon jeton_25 = new ImageIcon(getClass().getResource("Jeton_25.PNG"));
	 Icon jeton_50 = new ImageIcon(getClass().getResource("Jeton_50.PNG")); 
	 Icon jeton_100 = new ImageIcon(getClass().getResource("Jeton_100.PNG"));
	 Icon karte =  new ImageIcon(getClass().getClassLoader().getResource("Karten/front.jpg"));
	 Icon testkarte = new ImageIcon(getClass().getResource("testkarte.PNG"));
	 Icon rueckseite =  new ImageIcon(getClass().getClassLoader().getResource("Karten/front.jpg"));
	 Icon lasvegas = new ImageIcon(getClass().getClassLoader().getResource("Karten/lasvegas.jpg"));
	 Icon blackjack = new ImageIcon(getClass().getClassLoader().getResource("Karten/blackjacklogo.png"));
	 Icon hintergrundtisch = new ImageIcon(getClass().getClassLoader().getResource("Karten/hintergrundtisch.jpg"));
	 Icon hintergrundweiss = new ImageIcon(getClass().getClassLoader().getResource("Karten/hintergrundweiß.png"));
	 Icon hintergrundgruen = new ImageIcon(getClass().getClassLoader().getResource("Karten/hintergrundgrün.png"));
	 
	 JLabel panelstart = new JLabel(hintergrundtisch);
	 JLabel hintergrundmenu = new JLabel(lasvegas);
	 JLabel bedienfeld = new JLabel();
	 JLabel logo = new JLabel(blackjack);
	 JLabel menuleiste = new JLabel(testkarte); 
	 JLabel kartenfeldS1 = new JLabel(hintergrundweiss);
	 JLabel kartenfeldS1g = new JLabel(hintergrundgruen);
     JLabel kartenfeldS2 = new JLabel(hintergrundweiss);
     JLabel kartenfeldS2g = new JLabel(hintergrundgruen);
     JLabel kartenfeldbank = new JLabel(hintergrundweiss);
     JLabel kartenfeldbankg = new JLabel(hintergrundgruen);
	 

	
	 //PIK
	 Icon pik[] = new ImageIcon[14];{
		 for(int num=1; num<14; num++) {
				pik[num]  = new ImageIcon(getClass().getClassLoader().getResource("Karten/"+num+"Pik.png"));
			}
	 }
	 //Herz
	 Icon herz[] = new ImageIcon[14];{
		 for(int num=1; num<14; num++) {
				herz[num]  = new ImageIcon(getClass().getClassLoader().getResource("Karten/"+num+"Herz.png"));
			}
	 }
	 //Kreuz
	 Icon kreuz[] = new ImageIcon[14];{
		 for(int num=1; num<14; num++) {
				kreuz[num]  = new ImageIcon(getClass().getClassLoader().getResource("Karten/"+num+"Kreuz.png"));
			}
	 }
	 //Karo
	 Icon karo[] = new ImageIcon[14];{
		 for(int num=1; num<14; num++) {
				karo[num]  = new ImageIcon(getClass().getClassLoader().getResource("Karten/"+num+"Karo.png"));
			}
	 }
	 
		
		
		
		
	 JLabel karte5Spieler1 = new JLabel();
	 JLabel karte4Spieler1 = new JLabel();
	 JLabel karte3Spieler1 = new JLabel();
	 JLabel karte2Spieler1 = new JLabel();
	 JLabel karte1Spieler1 = new JLabel();
	 
	 JLabel karte1Spieler2 = new JLabel();
	 JLabel karte2Spieler2 = new JLabel();
	 JLabel karte3Spieler2 = new JLabel();
	 JLabel karte4Spieler2 = new JLabel();
	 JLabel karte5Spieler2 = new JLabel();
	 
	 JLabel karte1Bank = new JLabel(); 
	 JLabel karte2Bank = new JLabel(); 
	 JLabel karte3Bank = new JLabel(); 
	 JLabel karte4Bank = new JLabel(); 
	 JLabel karte5Bank = new JLabel(); 


	 

		


	//Buttons

	 JButton buttonStartSpiel = new JButton ("Spiel starten"); 
	 JButton buttonAbbrechenSpiel = new JButton ("Abbrechen"); 
	 JButton buttonLogin = new JButton("Login");
	 JButton buttonRegistrieren = new JButton("Registrieren");
	 JButton buttonstart = new JButton("Start");
	 JButton buttonRegistrierenAbschließen = new JButton("Abschließen");
	 JButton buttonZurueck = new JButton("Zurück");
	 JButton buttonZurueckZuStart = new JButton("Zurück zum Start");
	 JButton buttonIPAdresseBestaetigen = new JButton ("IP Adresse bestätigen");
	 JButton buttonPortBestätigen = new JButton ("Port bestätigen"); 
	 JButton buttonEinsatz = new JButton ("Einsatz");
	 JButton buttonJeton10 = new JButton("Jeton10",jeton_10);
	 JButton buttonJeton25 = new JButton("Jeton25", jeton_25);
	 JButton buttonJeton50 = new JButton ("Jeton50", jeton_50);
	 JButton buttonJeton100 = new JButton("Jeton100", jeton_100);
	 JButton buttonEinsatzbestaetigen = new JButton("Einsatz bestätigen");
	 JButton buttonHit = new JButton ("Hit"); 
	 JButton buttonStay = new JButton ("Stay"); 
	 JButton buttonNaechsteRunde = new JButton ("Nächste Runde");
	
	 
	 
	 
	 


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
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("Karten/casino-chip.png")).getImage());
		this.setSize(1600,1000);
		this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(0,81,0));

		startfenster();
		auswerteFenster();
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
		
		//hintergrundbild
		
		hintergrundmenu.setBounds(0,0,2000,1300);
		hintergrundmenu.setFocusable(false);
		hintergrundmenu.setBorder(null);
		hintergrundmenu.setBackground(null);
		hintergrundmenu.setForeground(Color.white);
		hintergrundmenu.setVisible(true);
		this.add(hintergrundmenu); 

		
		
		panelstart.setBounds(450,100,600,700);
		panelstart.setFocusable(false);
		panelstart.setBorder(null);
		panelstart.setForeground(Color.white);
		panelstart.setBackground(null);
		panelstart.setVisible(true);
		hintergrundmenu.add(panelstart); 
		
		logo.setBounds(50,10,500,500);
		logo.setFocusable(false);
		logo.setBorder(null);
		logo.setBackground(Color.white);
		logo.setForeground(Color.white);
		logo.setVisible(true);
		panelstart.add(logo); 
		
		//Buttons: 
		buttonStartSpiel.setBounds(80,550,200,50);
		buttonStartSpiel.setFocusable(false);
		buttonStartSpiel.setBorder(null);
		buttonStartSpiel.setBackground(Color.gray);
		buttonStartSpiel.setFocusPainted(false);
		buttonStartSpiel.setForeground(Color.white);
		buttonStartSpiel.setFont(new Font("Book Antiqua", Font.PLAIN,30));
		buttonStartSpiel.setContentAreaFilled(false);
		buttonStartSpiel.addActionListener(spiel.aHandler);
		buttonStartSpiel.setVisible(true);
		panelstart.add(buttonStartSpiel);
		
		buttonAbbrechenSpiel.setBounds(300,550,200,50);
		buttonAbbrechenSpiel.setFocusable(false);
		buttonAbbrechenSpiel.setBorder(null);
		buttonAbbrechenSpiel.setBackground(Color.gray);
		buttonAbbrechenSpiel.setFocusPainted(false);
		buttonAbbrechenSpiel.setForeground(Color.white);
		buttonAbbrechenSpiel.setFont(new Font("Book Antiqua", Font.PLAIN,30));
		buttonAbbrechenSpiel.setContentAreaFilled(false);
		buttonAbbrechenSpiel.addActionListener(spiel.aHandler);
		buttonAbbrechenSpiel.setVisible(true);
		panelstart.add(buttonAbbrechenSpiel);
		
	}
	
	
	//Auswahlfenster

	public void auswahlfenster() {
		
		
	//Buttons
		buttonLogin.setBounds(80,550,200,50);
		buttonLogin.setFocusable(false);
		buttonLogin.setBorder(null);
		buttonLogin.setBackground(Color.gray);
		buttonLogin.setFocusPainted(false);
		buttonLogin.setForeground(Color.white);
		buttonLogin.setFont(new Font("Book Antiqua", Font.PLAIN,30));
		buttonLogin.setContentAreaFilled(false);
		buttonLogin.addActionListener(spiel.aHandler);
		buttonLogin.setVisible(false);
		panelstart.add(buttonLogin);
		
		
		buttonRegistrieren.setBounds(300,550,200,50);
		buttonRegistrieren.setFocusable(false);
		buttonRegistrieren.setBorder(null);
		buttonRegistrieren.setBackground(Color.gray);
		buttonRegistrieren.setFocusPainted(false);
		buttonRegistrieren.setForeground(Color.white);
		buttonRegistrieren.setFont(new Font("Book Antiqua", Font.PLAIN,30));
		buttonRegistrieren.setContentAreaFilled(false);
		buttonRegistrieren.addActionListener(spiel.aHandler);
		buttonRegistrieren.setVisible(false);
		panelstart.add(buttonRegistrieren);
		
		
		buttonZurueckZuStart.setBounds(165,625,250,50);
		buttonZurueckZuStart.setFocusable(false);
		buttonZurueckZuStart.setBorder(null);
		buttonZurueckZuStart.setBackground(Color.gray);
		buttonZurueckZuStart.setFocusPainted(false);
		buttonZurueckZuStart.setForeground(Color.white);
		buttonZurueckZuStart.setFont(new Font("Book Antiqua", Font.PLAIN,30));
		buttonZurueckZuStart.setContentAreaFilled(false);
		buttonZurueckZuStart.addActionListener(spiel.aHandler);
		buttonZurueckZuStart.setVisible(false);
		panelstart.add(buttonZurueckZuStart);
		
	}

	//Loginfenster


	public void anmeldefenster() {

		//Button 1: Benutzername: 
		labelBenutzername.setBounds(100,250,165,25);
		labelBenutzername.setFocusable(false);
		labelBenutzername.setBorder(null);
		labelBenutzername.setBackground(null);
		labelBenutzername.setForeground(Color.white);
		labelBenutzername.setFont(new Font("Book Antiqua", Font.PLAIN,20));
		labelBenutzername.setVisible(false);
		panelstart.add(labelBenutzername); 

		//Button 2: Passwort: 
		labelPasswort.setBounds(100,350,165,25);
		labelPasswort.setFocusable(false);
		labelPasswort.setBorder(null);
		labelPasswort.setBackground(null);
		labelPasswort.setForeground(Color.white);
		labelPasswort.setFont(new Font("Book Antiqua", Font.PLAIN,20));
		labelPasswort.setVisible(false);
		panelstart.add(labelPasswort);

		
		anmeldetext.setBounds(100,150,500,25);
		anmeldetext.setFocusable(false);
		anmeldetext.setBorder(null);
		anmeldetext.setBackground(null);
		anmeldetext.setForeground(Color.white);
		anmeldetext.setFont(new Font("Book Antiqua", Font.PLAIN,15));
		anmeldetext.setVisible(false);
		anmeldetext.setText("Bitte geben Sie Ihren Benutzernamen und Ihr Passwort ein!");
		panelstart.add(anmeldetext);
		
		//Button 3: Login:
		buttonstart.setBounds(100,610,150,50);
		buttonstart.setFocusable(false);
		buttonstart.setBorder(null);
		buttonstart.setBackground(Color.gray);
		buttonstart.setFocusPainted(false);
		buttonstart.setForeground(Color.white);
		buttonstart.setFont(new Font("Book Antiqua", Font.PLAIN,25));
		buttonstart.setContentAreaFilled(false);
		buttonstart.addActionListener(spiel.aHandler);
		buttonstart.setVisible(false);
		panelstart.add(buttonstart);


		buttonZurueck.setBounds(380,610,150,50);
		buttonZurueck.setFocusable(false);
		buttonZurueck.setBorder(null);
		buttonZurueck.setBackground(Color.gray);
		buttonZurueck.setFocusPainted(false);
		buttonZurueck.setForeground(Color.white);
		buttonZurueck.setFont(new Font("Book Antiqua", Font.PLAIN,25));
		buttonZurueck.setContentAreaFilled(false);
		buttonZurueck.addActionListener(spiel.aHandler);
		buttonZurueck.setVisible(false);
		panelstart.add(buttonZurueck);
		
		// TextField: Benutzername:
		userText.setBounds(300,250,165,25);
		userText.setVisible(false);
		userText.addActionListener(spiel.aHandler);
		panelstart.add(userText);

		//PasswordField: Passwort:
		passwordText.setBounds(300,350,165,25);
		passwordText.setVisible(false);
		passwordText.addActionListener(spiel.aHandler);
		panelstart.add(passwordText);
		
		
		
		

		//Überschrift: 
		ueberschrift.setBounds(400,100,1000,200);
		Font schriftart = new Font ("Algerian",Font.PLAIN+Font.ITALIC,120);
		ueberschrift.setFont(schriftart);
		ueberschrift.setVisible(true);
		this.add(ueberschrift); 


	}

	
	
	//Registrierungsfenster
	
	public void registrierfenster() {

		
		labelBenutzernameErstellen.setBounds(100,250,165,25);
		labelBenutzernameErstellen.setFocusable(false);
		labelBenutzernameErstellen.setBorder(null);
		labelBenutzernameErstellen.setBackground(null);
		labelBenutzernameErstellen.setForeground(Color.white);
		labelBenutzernameErstellen.setFont(new Font("Book Antiqua", Font.PLAIN,20));
		labelBenutzernameErstellen.setVisible(false);
		panelstart.add(labelBenutzernameErstellen); 

		
		labelPasswort1.setBounds(100,350,165,25);
		labelPasswort1.setFocusable(false);
		labelPasswort1.setBorder(null);
		labelPasswort1.setBackground(null);
		labelPasswort1.setForeground(Color.white);
		labelPasswort1.setFont(new Font("Book Antiqua", Font.PLAIN,20));
		labelPasswort1.setVisible(false);
		panelstart.add(labelPasswort1);

		labelPasswort2.setBounds(100,450,165,25);
		labelPasswort2.setFocusable(false);
		labelPasswort2.setBorder(null);
		labelPasswort2.setBackground(null);
		labelPasswort2.setForeground(Color.white);
		labelPasswort2.setFont(new Font("Book Antiqua", Font.PLAIN,20));
		labelPasswort2.setVisible(false);
		panelstart.add(labelPasswort2);
		
		registrierungtext.setBounds(100,150,500,25);
		registrierungtext.setFocusable(false);
		registrierungtext.setBorder(null);
		registrierungtext.setBackground(null);
		registrierungtext.setForeground(Color.white);
		registrierungtext.setFont(new Font("Book Antiqua", Font.PLAIN,15));
		registrierungtext.setVisible(false);
		registrierungtext.setText("Bitte wählen Sie einen Benutzernamen und ein Passwort!");
		panelstart.add(registrierungtext);
		
		
		buttonRegistrierenAbschließen.setBounds(100,610,150,50);
		buttonRegistrierenAbschließen.setFocusable(false);
		buttonRegistrierenAbschließen.setBorder(null);
		buttonRegistrierenAbschließen.setBackground(Color.gray);
		buttonRegistrierenAbschließen.setFocusPainted(false);
		buttonRegistrierenAbschließen.setForeground(Color.white);
		buttonRegistrierenAbschließen.setFont(new Font("Book Antiqua", Font.PLAIN,25));
		buttonRegistrierenAbschließen.setContentAreaFilled(false);
		buttonRegistrierenAbschließen.addActionListener(spiel.aHandler);
		buttonRegistrierenAbschließen.setVisible(false);
		panelstart.add(buttonRegistrierenAbschließen);

		// TextField: Benutzername:
		userRegistText.setBounds(300,250,165,25);
		userRegistText.setVisible(false);
		userRegistText.addActionListener(spiel.aHandler);
		panelstart.add(userRegistText);

		//PasswordField:
		passwordText1.setBounds(300,350,165,25);
		passwordText1.setVisible(false);
		passwordText1.addActionListener(spiel.aHandler);
		panelstart.add(passwordText1);

		passwordText2.setBounds(300,450,165,25);
		passwordText2.setVisible(false);
		passwordText2.addActionListener(spiel.aHandler);
		panelstart.add(passwordText2);
	}
		
		//ipadressefenster: 
		
		public void ipadressefenster() {
			
			
			//Labels:
			labelipadresse.setBounds(50,550,165,25);
			labelipadresse.setFocusable(false);
			labelipadresse.setBorder(null);
			labelipadresse.setBackground(null);
			labelipadresse.setForeground(Color.white);
			labelipadresse.setVisible(false);
			labelipadresse.setFont(new Font("Book Antiqua", Font.PLAIN,25));
			panelstart.add(labelipadresse);
			
			labelIPAdresse.setBounds(250,550,200,25);
			labelIPAdresse.setFocusable(false);
			labelIPAdresse.setBorder(null);
			labelIPAdresse.setBackground(null);
			labelIPAdresse.setForeground(Color.white);
			labelIPAdresse.setVisible(false);
			labelIPAdresse.setFont(new Font("Book Antiqua", Font.PLAIN,25));
			panelstart.add(labelIPAdresse);
			
			// TextField: Benutzername:
			ipadresseText.setBounds(800,550,165,25);
			ipadresseText.setVisible(false);
			ipadresseText.setFont(new Font("Book Antiqua", Font.PLAIN,25));
			panelstart.add(ipadresseText);

			//Buttons:	
			
			buttonIPAdresseBestaetigen.setBounds(50,610,250,50);
			buttonIPAdresseBestaetigen.setFocusable(false);
			buttonIPAdresseBestaetigen.setBorder(null);
			buttonIPAdresseBestaetigen.setBackground(Color.gray);
			buttonIPAdresseBestaetigen.setFocusPainted(false);
			buttonIPAdresseBestaetigen.setForeground(Color.white);
			buttonIPAdresseBestaetigen.setFont(new Font("Book Antiqua", Font.PLAIN,25));
			buttonIPAdresseBestaetigen.setContentAreaFilled(false);
			buttonIPAdresseBestaetigen.addActionListener(spiel.aHandler);
			buttonIPAdresseBestaetigen.setVisible(false);
			panelstart.add(buttonIPAdresseBestaetigen);
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
			buttonPortBestätigen.setBackground(Color.gray);
			buttonPortBestätigen.setForeground(Color.white);
			buttonPortBestätigen.addActionListener(spiel.aHandler);
			buttonPortBestätigen.setVisible(false);
			this.add(buttonPortBestätigen);




	 


	}

	
	//Spielfenster
		
	
		
		public void einsatzfenster() {
			
		menuleiste.setBounds(0,700,1600,350);
		menuleiste.setVisible(false);
		this.add(menuleiste);
			
		
		//Buttons:	
		buttonEinsatz.setBounds(500,10,600,100);
		buttonEinsatz.setFocusable(false);
		buttonEinsatz.setBorder(null);
		buttonEinsatz.setBackground(null);
		buttonEinsatz.setForeground(Color.white);
		buttonEinsatz.setContentAreaFilled(false);
		buttonEinsatz.setFont(new Font("Calibri", Font.PLAIN,30));
		buttonEinsatz.addActionListener(spiel.aHandler);
		buttonEinsatz.setVisible(false);
		menuleiste.add(buttonEinsatz);
		
		//Bedienfeld: 
		bedienfeld.setBounds(500,500,1600,200);
		bedienfeld.setForeground(Color.gray); 
		bedienfeld.setVisible(false);
		this.add(bedienfeld);
		
		
		//Überschrift: 
		ueberschriftSpiel.setBounds(525,300,1000,200);
		Font schriftart = new Font ("Algerian",Font.PLAIN+Font.ITALIC,80);
		ueberschriftSpiel.setForeground(Color.yellow);
		ueberschriftSpiel.setFont(schriftart);
		ueberschriftSpiel.setVisible(false);
		this.add(ueberschriftSpiel); 

		//Überschrift: 
		unterueberschriftSpiel.setBounds(650,350,1000,200);
		Font schriftart4 = new Font ("Algerian",Font.PLAIN+Font.ITALIC,25);
		unterueberschriftSpiel.setForeground(Color.yellow);
		unterueberschriftSpiel.setFont(schriftart4);
		unterueberschriftSpiel.setVisible(false);
		this.add(unterueberschriftSpiel); 

		//Überschrift: 
		labelSpieler1.setBounds(30,200,1000,200);
		Font schriftart1 = new Font("Algerian",Font.PLAIN+Font.ITALIC,40);
		labelSpieler1.setForeground(Color.yellow); 
		labelSpieler1.setFont(schriftart1);
		labelSpieler1.setVisible(false);
		this.add(labelSpieler1); 
		
		//Überschrift: 
		labelSpieler2.setBounds(1300,200,1000,200);
		Font schriftart2 = new Font("Algerian",Font.PLAIN+Font.ITALIC,40);
		labelSpieler2.setForeground(Color.yellow); 
		labelSpieler2.setFont(schriftart2);
		labelSpieler2.setVisible(false);
		this.add(labelSpieler2);

		//Überschrift: 
		labelBank.setBounds(725,1,1000,100);
		Font schriftart3 = new Font("Algerian",Font.PLAIN+Font.ITALIC,40);
		labelBank.setForeground(Color.yellow); 
		labelBank.setFont(schriftart3);
		labelBank.setVisible(false);
		this.add(labelBank);
		
		//Karten: 
		
		karte5Spieler1.setBounds(500,350,150,213);
		karte5Spieler1.setFocusable(false);
		karte5Spieler1.setBorder(null);
		karte5Spieler1.setVisible(false);
		kartenfeldS1g.add(karte5Spieler1);
		
		karte4Spieler1.setBounds(400,350,150,213);
		karte4Spieler1.setFocusable(false);
		karte4Spieler1.setBorder(null);
		karte4Spieler1.setVisible(false);
		kartenfeldS1g.add(karte4Spieler1);
		
		
		karte3Spieler1.setBounds(300,350,150,213);
		karte3Spieler1.setFocusable(false);
		karte3Spieler1.setBorder(null);
		karte3Spieler1.setVisible(false);
		kartenfeldS1g.add(karte3Spieler1);
		
		karte2Spieler1.setBounds(200,350,150,213);
		karte2Spieler1.setFocusable(false);
		karte2Spieler1.setBorder(null);
		karte2Spieler1.setVisible(false);
		kartenfeldS1g.add(karte2Spieler1);
		
		karte1Spieler1.setBounds(100,350,150,213);
		karte1Spieler1.setFocusable(false);
		karte1Spieler1.setBorder(null);
		karte1Spieler1.setVisible(false);
		kartenfeldS1g.add(karte1Spieler1); 
		
		karte5Spieler2.setBounds(1400,350,150,213);
		karte5Spieler2.setFocusable(false);
		karte5Spieler2.setBorder(null);
		karte5Spieler2.setVisible(false);
		kartenfeldS2g.add(karte5Spieler2);
		

		karte4Spieler2.setBounds(1300,350,150,213);
		karte4Spieler2.setFocusable(false);
		karte4Spieler2.setBorder(null);
		karte4Spieler2.setVisible(false);
		kartenfeldS2g.add(karte4Spieler2);
		
		karte3Spieler2.setBounds(1200,350,150,213);
		karte3Spieler2.setFocusable(false);
		karte3Spieler2.setBorder(null);
		karte3Spieler2.setVisible(false);
		kartenfeldS2g.add(karte3Spieler2);
		
		karte2Spieler2.setBounds(1100,350,150,213);
		karte2Spieler2.setFocusable(false);
		karte2Spieler2.setBorder(null);
		karte2Spieler2.setVisible(false);
		kartenfeldS2g.add(karte2Spieler2);
		
		
		karte1Spieler2.setBounds(1000,350,150,213);
		karte1Spieler2.setFocusable(false);
		karte1Spieler2.setBorder(null);
		karte1Spieler2.setVisible(false);
		kartenfeldS2g.add(karte1Spieler2);
		
		karte5Bank.setBounds(900,75,150,213);
		karte5Bank.setFocusable(false);
		karte5Bank.setBorder(null);
		karte5Bank.setVisible(false);
		kartenfeldbankg.add(karte5Bank);

		
		karte4Bank.setBounds(800,75,150,213);
		karte4Bank.setFocusable(false);
		karte4Bank.setBorder(null);
		karte4Bank.setVisible(false);
		kartenfeldbankg.add(karte4Bank);
		
		karte3Bank.setBounds(700,75,150,213);
		karte3Bank.setFocusable(false);
		karte3Bank.setBorder(null);
		karte3Bank.setVisible(false);
		kartenfeldbankg.add(karte3Bank);
		
		karte2Bank.setBounds(600,75,150,213);
		karte2Bank.setFocusable(false);
		karte2Bank.setBorder(null);
		karte2Bank.setVisible(false);
		kartenfeldbankg.add(karte2Bank);
		
		karte1Bank.setBounds(500,75,150,213);
		karte1Bank.setFocusable(false);
		karte1Bank.setBorder(null);
		karte1Bank.setVisible(false);
		kartenfeldbankg.add(karte1Bank);
		
		//Kartenfelder
		
		kartenfeldS1.setBounds(30,430,600,263);
		kartenfeldS1.setFocusable(false);
		kartenfeldS1.setBorder(null);
		kartenfeldS1.setForeground(Color.white);
		kartenfeldS1.setBackground(null);
		kartenfeldS1.setVisible(true);
		this.add(kartenfeldS1); 
		
		kartenfeldS1g.setBounds(10,10,580,243);
		kartenfeldS1g.setFocusable(false);
		kartenfeldS1g.setBorder(null);
		kartenfeldS1g.setForeground(Color.white);
		kartenfeldS1g.setBackground(null);
		kartenfeldS1g.setVisible(true);
		kartenfeldS1.add(kartenfeldS1g); 
		
		kartenfeldS2.setBounds(900,430,600,263);
		kartenfeldS2.setFocusable(false);
		kartenfeldS2.setBorder(null);
		kartenfeldS2.setForeground(Color.white);
		kartenfeldS2.setBackground(null);
		kartenfeldS2.setVisible(true);
		this.add(kartenfeldS2); 
		
		kartenfeldS2g.setBounds(10,10,580,243);
		kartenfeldS2g.setFocusable(false);
		kartenfeldS2g.setBorder(null);
		kartenfeldS2g.setForeground(Color.white);
		kartenfeldS2g.setBackground(null);
		kartenfeldS2g.setVisible(true);
		kartenfeldS2.add(kartenfeldS2g); 
		
		kartenfeldbank.setBounds(475,75,600,263);
		kartenfeldbank.setFocusable(false);
		kartenfeldbank.setBorder(null);
		kartenfeldbank.setForeground(Color.white);
		kartenfeldbank.setBackground(null);
		kartenfeldbank.setVisible(true);
		this.add(kartenfeldbank); 
		
		kartenfeldbankg.setBounds(10,10,580,243);
		kartenfeldbankg.setFocusable(false);
		kartenfeldbankg.setBorder(null);
		kartenfeldbankg.setForeground(Color.white);
		kartenfeldbankg.setBackground(null);
		kartenfeldbankg.setVisible(true);
		kartenfeldbank.add(kartenfeldbankg); 
		
		
		}
		
		public void jetonfenster() {
			buttonJeton10.setBounds(250,0,115,100);
			buttonJeton10.setFocusable(false);
			buttonJeton10.setBorder(null);
			buttonJeton10.setBackground(null);
			buttonJeton10.setForeground(Color.black);
			buttonJeton10.addActionListener(spiel.aHandler);
			buttonJeton10.setContentAreaFilled(false);
			buttonJeton10.setVisible(false);
			menuleiste.add(buttonJeton10);
			
			buttonJeton25.setBounds(500,0,115,100);
			buttonJeton25.setFocusable(false);
			buttonJeton25.setBorder(null);
			buttonJeton25.setBackground(null);
			buttonJeton25.setForeground(Color.black);
			buttonJeton25.setContentAreaFilled(false);
			buttonJeton25.addActionListener(spiel.aHandler);
			buttonJeton25.setVisible(false);
			menuleiste.add(buttonJeton25);
			
			buttonJeton50.setBounds(1000,0,115,100);
			buttonJeton50.setFocusable(false);
			buttonJeton50.setBorder(null);
			buttonJeton50.setBackground(null);
			buttonJeton50.setContentAreaFilled(false);
			buttonJeton50.setForeground(Color.black);
			buttonJeton50.addActionListener(spiel.aHandler);
			buttonJeton50.setVisible(false);
			menuleiste.add(buttonJeton50);
			
			buttonJeton100.setBounds(1250,0,115,100);
			buttonJeton100.setFocusable(false);
			buttonJeton100.setBorder(null);
			buttonJeton100.setBackground(null);
			buttonJeton100.setContentAreaFilled(false);
			buttonJeton100.setForeground(Color.black);
			buttonJeton100.addActionListener(spiel.aHandler);
			buttonJeton100.setVisible(false);
			menuleiste.add(buttonJeton100);
			
			buttonEinsatzbestaetigen.setBounds(550,0,500,100);
			buttonEinsatzbestaetigen.setFocusable(false);
			buttonEinsatzbestaetigen.setBorder(null);
			buttonEinsatzbestaetigen.setBackground(null);
			buttonEinsatzbestaetigen.setForeground(Color.white);
			buttonEinsatzbestaetigen.addActionListener(spiel.aHandler);
			buttonEinsatzbestaetigen.setFont(new Font("Calibri", Font.PLAIN,30));
			buttonEinsatzbestaetigen.setContentAreaFilled(false);
			buttonEinsatzbestaetigen.setVisible(false);
			menuleiste.add(buttonEinsatzbestaetigen);
			
			einsatzausgabeSpieler1.setBounds(10,650,165,25);
			einsatzausgabeSpieler1.setFocusable(false);
			einsatzausgabeSpieler1.setBorder(null);
			einsatzausgabeSpieler1.setBackground(null);
			einsatzausgabeSpieler1.setForeground(Color.white);
			einsatzausgabeSpieler1.setVisible(false);
			this.add(einsatzausgabeSpieler1);
			
			
			einsatzausgabeSpieler2.setBounds(1350,650,165,25);
			einsatzausgabeSpieler2.setFocusable(false);
			einsatzausgabeSpieler2.setBorder(null);
			einsatzausgabeSpieler2.setBackground(null);
			einsatzausgabeSpieler2.setForeground(Color.white);
			einsatzausgabeSpieler2.setVisible(false);
			this.add(einsatzausgabeSpieler2);
			
			einsatzSpieler1.setBounds(25,350,165,25);
			einsatzSpieler1.setFocusable(false);
			einsatzSpieler1.setBorder(null);
			einsatzSpieler1.setBackground(null);
			einsatzSpieler1.setForeground(Color.white);
			einsatzSpieler1.setVisible(false);
			this.add(einsatzSpieler1);
			
			einsatzSpieler2.setBounds(1300,350,165,25);
			einsatzSpieler2.setFocusable(false);
			einsatzSpieler2.setBorder(null);
			einsatzSpieler2.setBackground(null);
			einsatzSpieler2.setForeground(Color.white);
			einsatzSpieler2.setVisible(false);
			this.add(einsatzSpieler2);
			
			kontostandSpieler1.setBounds(25,325,165,25);
			kontostandSpieler1.setFocusable(false);
			kontostandSpieler1.setBorder(null);
			kontostandSpieler1.setBackground(null);
			kontostandSpieler1.setForeground(Color.white);
			kontostandSpieler1.setVisible(false);
			this.add(kontostandSpieler1);
			
			kontostandSpieler2.setBounds(1300,325,165,25);
			kontostandSpieler2.setFocusable(false);
			kontostandSpieler2.setBorder(null);
			kontostandSpieler2.setBackground(null);
			kontostandSpieler2.setForeground(Color.white);
			kontostandSpieler2.setVisible(false);
			this.add(kontostandSpieler2);
			
			kartenwertSpieler1.setBounds(10,50,165,25);
			kartenwertSpieler1.setFocusable(false);
			kartenwertSpieler1.setBorder(null);
			kartenwertSpieler1.setBackground(null);
			kartenwertSpieler1.setForeground(Color.white);
			kartenwertSpieler1.setVisible(false);
			this.add(kartenwertSpieler1);
			

			kartenwertSpieler2.setBounds(1350,50,165,25);
			kartenwertSpieler2.setFocusable(false);
			kartenwertSpieler2.setBorder(null);
			kartenwertSpieler2.setBackground(null);
			kartenwertSpieler2.setForeground(Color.white);
			kartenwertSpieler2.setVisible(false);
			this.add(kartenwertSpieler2);
			

			kartenwertDealer.setBounds(500,10,165,25);
			kartenwertDealer.setFocusable(false);
			kartenwertDealer.setBorder(null);
			kartenwertDealer.setBackground(null);
			kartenwertDealer.setForeground(Color.white);
			kartenwertDealer.setVisible(false);
			this.add(kartenwertDealer);
			
			buttonHit.setBounds(400,0,200,100);
			buttonHit.setFocusable(false);
			buttonHit.setBorder(null);
			buttonHit.setBackground(null);
			buttonHit.setForeground(Color.white);
			buttonHit.addActionListener(spiel.aHandler);
			buttonHit.setFont(new Font("Calibri", Font.PLAIN,30));
			buttonHit.setContentAreaFilled(false);
			buttonHit.setVisible(false);
			menuleiste.add(buttonHit);
			
			buttonStay.setBounds(1000,0,200,100);
			buttonStay.setFocusable(false);
			buttonStay.setBorder(null);
			buttonStay.setBackground(null);
			buttonStay.setForeground(Color.white);
			buttonStay.addActionListener(spiel.aHandler);
			buttonStay.setFont(new Font("Calibri", Font.PLAIN,30));
			buttonStay.setContentAreaFilled(false);
			buttonStay.setVisible(false);
			menuleiste.add(buttonStay);
			
			
		}
		
		public void auswerteFenster() {
			buttonNaechsteRunde.setBounds(650,650,200,50);
			buttonNaechsteRunde.setFocusable(false);
			buttonNaechsteRunde.setBorder(null);
			buttonNaechsteRunde.setBackground(null);
			buttonNaechsteRunde.setForeground(Color.white);
			buttonNaechsteRunde.addActionListener(spiel.aHandler);
			buttonNaechsteRunde.setVisible(false);
			this.add(buttonNaechsteRunde);
			
			nachrichtS1.setBounds(200,400,1000,100);
			nachrichtS1.setForeground(Color.red); 
			nachrichtS1.setVisible(false);
			this.add(nachrichtS1);
			
			nachrichtS2.setBounds(1100,400,1000,100);
			nachrichtS2.setForeground(Color.red); 
			nachrichtS2.setVisible(false);
			this.add(nachrichtS2);
		}
	
	
	
	
	
	
}
