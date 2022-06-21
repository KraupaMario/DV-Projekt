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

public class BenutzeroberflächeClient extends JFrame  {


	//Variablen

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	Client2 spiel;
	

	 JFrame frame = new JFrame();
	 JPanel panel = new JPanel();
	 
	 //Menubar
	// JMenuBar menu = new JMenuBar(); 
	
	//Label
	 JLabel nachrichtS1C = new JLabel();
	 JLabel nachrichtS2C = new JLabel();
	 JLabel labelBenutzernameC = new JLabel ("Benutzername:");
	 JLabel labelPasswortC = new JLabel("Passwort:");
	 JLabel ueberschriftC = new JLabel("BLACK JACK"); 
	 JLabel ueberschriftCSpielC = new JLabel ("BLACK JACK"); 
	 JLabel unterueberschriftCSpielC = new JLabel ("Casino Las Vegas"); 
	 JLabel labelSpieler1C = new JLabel ("Spieler1");
	 JLabel labelSpieler2C = new JLabel ("Spieler2"); 
	 JLabel labelBankC = new JLabel ("Bank"); 
	 JLabel labelBenutzernameCErstellenC = new JLabel ("Benutzername:");
	 JLabel labelPasswortC1C = new JLabel("Passwort:");
	 JLabel labelPasswortC2C = new JLabel("Passwort:");
	 JLabel labelipadresseC = new JLabel ("IP-Adresse:");
	 JLabel labelportC = new JLabel("Port:");
	 JLabel einsatzSpieler1C = new JLabel ("Einsatz Spieler1:"); 
	 JLabel einsatzSpieler2C = new JLabel ("Einsatz Spieler2:"); 
	 JLabel kontostandSpieler1 = new JLabel ("Kontostand Spieler1: privat!!!"); 
	 JLabel kontostandSpieler2 = new JLabel ("Kontostand Spieler2:");
	 JLabel einsatzausgabeSpieler1C = new JLabel();
	 JLabel einsatzausgabeSpieler2C = new JLabel();
	 JLabel labelipadresseC2 = new JLabel(); 
	 JLabel kartenwertSpieler1 = new JLabel(); 
	 JLabel kartenwertSpieler2 = new JLabel(); 
	 JLabel kartenwertDealer = new JLabel(); 
	 JLabel anmeldetext = new JLabel();
	 JLabel registrierungtext = new JLabel();
	 
	 JLabel wartenAufSpieler = new JLabel("Bitte warten...");
	

	 

	 Icon jeton_10 = new ImageIcon(getClass().getResource("Jeton_10.PNG"));
	 Icon jeton_25 = new ImageIcon(getClass().getResource("Jeton_25.PNG"));
	 Icon jeton_50 = new ImageIcon(getClass().getResource("Jeton_50.PNG")); 
	 Icon jeton_100 = new ImageIcon(getClass().getResource("Jeton_100.PNG"));
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
	 JLabel kartenfeldS1 = new JLabel(hintergrundweiss);
	 JLabel kartenfeldS1g = new JLabel(hintergrundgruen);
     JLabel kartenfeldS2 = new JLabel(hintergrundweiss);
     JLabel kartenfeldS2g = new JLabel(hintergrundgruen);
     JLabel kartenfeldbank = new JLabel(hintergrundweiss);
     JLabel kartenfeldbankg = new JLabel(hintergrundgruen);
     JLabel menuleiste = new JLabel(testkarte);
	 
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
	 
	 JLabel karte1Spieler1 = new JLabel();
	 JLabel karte2Spieler1 = new JLabel();
	 JLabel karte3Spieler1 = new JLabel();
	 JLabel karte4Spieler1 = new JLabel();
	 JLabel karte5Spieler1 = new JLabel();
	 
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

	 JButton buttonStartSpielC = new JButton ("Spiel starten"); 
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

	public BenutzeroberflächeClient(Client2 client2) {

		this.spiel = client2;

		this.setTitle("BlackJack");
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("Karten/casino-chip.png")).getImage());
		this.setSize(1600,1000);
		this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(0,81,0));
		
		auswerteFenster();
		startfenster();
		auswahlfenster();
		anmeldefenster();
		registrierfenster();
		ipadressefenster(); 
		portfenster();
		einsatzfenster(); 
		jetonfenster();
		
		wartefenster(); 
		
		 
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
		buttonStartSpielC.setBounds(80,550,200,50);
		buttonStartSpielC.setFocusable(false);
		buttonStartSpielC.setBorder(null);
		buttonStartSpielC.setBackground(null);
		buttonStartSpielC.setFocusPainted(false);
		buttonStartSpielC.setForeground(Color.white);
		buttonStartSpielC.setFont(new Font("Book Antiqua", Font.PLAIN,30));
		buttonStartSpielC.setContentAreaFilled(false);
		buttonStartSpielC.addActionListener(spiel.cHandler);
		buttonStartSpielC.setVisible(true);
		panelstart.add(buttonStartSpielC);
		
		buttonAbbrechenSpiel.setBounds(300,550,200,50);
		buttonAbbrechenSpiel.setFocusable(false);
		buttonAbbrechenSpiel.setBorder(null);
		buttonAbbrechenSpiel.setBackground(null);
		buttonAbbrechenSpiel.setFocusPainted(false);
		buttonAbbrechenSpiel.setForeground(Color.white);
		buttonAbbrechenSpiel.setFont(new Font("Book Antiqua", Font.PLAIN,30));
		buttonAbbrechenSpiel.setContentAreaFilled(false);
		buttonAbbrechenSpiel.addActionListener(spiel.cHandler);
		buttonAbbrechenSpiel.setVisible(true);
		panelstart.add(buttonAbbrechenSpiel);
		
	}
	
	
	
	//Auswahlfenster

	public void auswahlfenster() {


		//Buttons
		buttonLogin.setBounds(80,550,200,50);
		buttonLogin.setFocusable(false);
		buttonLogin.setBorder(null);
		buttonLogin.setBackground(null);
		buttonLogin.setFocusPainted(false);
		buttonLogin.setForeground(Color.white);
		buttonLogin.setFont(new Font("Book Antiqua", Font.PLAIN,30));
		buttonLogin.setContentAreaFilled(false);
		buttonLogin.addActionListener(spiel.cHandler);
		buttonLogin.setVisible(false);
		panelstart.add(buttonLogin);


		buttonRegistrieren.setBounds(300,550,200,50);
		buttonRegistrieren.setFocusable(false);
		buttonRegistrieren.setBorder(null);
		buttonRegistrieren.setBackground(null);
		buttonRegistrieren.setFocusPainted(false);
		buttonRegistrieren.setForeground(Color.white);
		buttonRegistrieren.setFont(new Font("Book Antiqua", Font.PLAIN,30));
		buttonRegistrieren.setContentAreaFilled(false);
		buttonRegistrieren.addActionListener(spiel.cHandler);
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
		buttonZurueckZuStart.addActionListener(spiel.cHandler);
		buttonZurueckZuStart.setVisible(false);
		panelstart.add(buttonZurueckZuStart);
		

	}

	//Loginfenster


	public void anmeldefenster() {

		//Button 1: Benutzername: 
		labelBenutzernameC.setBounds(100,250,165,25);
		labelBenutzernameC.setFocusable(false);
		labelBenutzernameC.setBorder(null);
		labelBenutzernameC.setBackground(null);
		labelBenutzernameC.setForeground(Color.white);
		labelBenutzernameC.setFont(new Font("Book Antiqua", Font.PLAIN,20));
		labelBenutzernameC.setVisible(false);
		panelstart.add(labelBenutzernameC); 

		//Button 2: Passwort: 
		labelPasswortC.setBounds(100,350,165,25);
		labelPasswortC.setFocusable(false);
		labelPasswortC.setBorder(null);
		labelPasswortC.setBackground(null);
		labelPasswortC.setForeground(Color.white);
		labelPasswortC.setFont(new Font("Book Antiqua", Font.PLAIN,20));
		labelPasswortC.setVisible(false);
		panelstart.add(labelPasswortC);

		
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
		buttonstart.addActionListener(spiel.cHandler);
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
		buttonZurueck.addActionListener(spiel.cHandler);
		buttonZurueck.setVisible(false);
		panelstart.add(buttonZurueck);
		
		// TextField: Benutzername:
		userText.setBounds(300,250,165,25);
		userText.setVisible(false);
		userText.addActionListener(spiel.cHandler);
		panelstart.add(userText);

		//PasswordField: Passwort:
		passwordText.setBounds(300,350,165,25);
		passwordText.setVisible(false);
		passwordText.addActionListener(spiel.cHandler);
		panelstart.add(passwordText);
		 


	}

	
	
	//Registrierungsfenster
	
public void registrierfenster() {

		
	labelBenutzernameCErstellenC.setBounds(100,250,165,25);
	labelBenutzernameCErstellenC.setFocusable(false);
	labelBenutzernameCErstellenC.setBorder(null);
	labelBenutzernameCErstellenC.setBackground(null);
	labelBenutzernameCErstellenC.setForeground(Color.white);
	labelBenutzernameCErstellenC.setFont(new Font("Book Antiqua", Font.PLAIN,20));
	labelBenutzernameCErstellenC.setVisible(false);
	panelstart.add(labelBenutzernameCErstellenC); 

		
	labelPasswortC1C.setBounds(100,350,165,25);
	labelPasswortC1C.setFocusable(false);
	labelPasswortC1C.setBorder(null);
	labelPasswortC1C.setBackground(null);
	labelPasswortC1C.setForeground(Color.white);
	labelPasswortC1C.setFont(new Font("Book Antiqua", Font.PLAIN,20));
	labelPasswortC1C.setVisible(false);
	panelstart.add(labelPasswortC1C);

	labelPasswortC2C.setBounds(100,450,165,25);
	labelPasswortC2C.setFocusable(false);
	labelPasswortC2C.setBorder(null);
	labelPasswortC2C.setBackground(null);
	labelPasswortC2C.setForeground(Color.white);
	labelPasswortC2C.setFont(new Font("Book Antiqua", Font.PLAIN,20));
	labelPasswortC2C.setVisible(false);
	panelstart.add(labelPasswortC2C);
		
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
		buttonRegistrierenAbschließen.addActionListener(spiel.cHandler);
		buttonRegistrierenAbschließen.setVisible(false);
		panelstart.add(buttonRegistrierenAbschließen);

		// TextField: Benutzername:
		userRegistText.setBounds(300,250,165,25);
		userRegistText.setVisible(false);
		userRegistText.addActionListener(spiel.cHandler);
		panelstart.add(userRegistText);

		//PasswordField:
		passwordText1.setBounds(300,350,165,25);
		passwordText1.setVisible(false);
		passwordText1.addActionListener(spiel.cHandler);
		panelstart.add(passwordText1);

		passwordText2.setBounds(300,450,165,25);
		passwordText2.setVisible(false);
		passwordText2.addActionListener(spiel.cHandler);
		panelstart.add(passwordText2);
	}
		
		//ipadressefenster: 
		
		public void ipadressefenster() {
			
			
			//Labels:
			labelipadresseC.setBounds(50,550,165,25);
			labelipadresseC.setFocusable(false);
			labelipadresseC.setBorder(null);
			labelipadresseC.setBackground(null);
			labelipadresseC.setForeground(Color.white);
			labelipadresseC.setFont(new Font("Book Antiqua", Font.PLAIN,25));
			labelipadresseC.setVisible(false);
			panelstart.add(labelipadresseC);
			
			labelipadresseC.setBounds(50,550,165,25);
			labelipadresseC.setFocusable(false);
			labelipadresseC.setBorder(null);
			labelipadresseC.setBackground(null);
			labelipadresseC.setForeground(Color.white);
			labelipadresseC.setFont(new Font("Book Antiqua", Font.PLAIN,25));
			labelipadresseC.setVisible(false);
			panelstart.add(labelipadresseC);
			
			// TextField: Benutzername:
			ipadresseText.setBounds(250,550,165,25);
			ipadresseText.setVisible(false);
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
			buttonIPAdresseBestaetigen.addActionListener(spiel.cHandler);
			buttonIPAdresseBestaetigen.setVisible(false);
			panelstart.add(buttonIPAdresseBestaetigen);
		}

		public void portfenster() {
			
			//Labels:
			labelportC.setBounds(500,350,165,25);
			labelportC.setFocusable(false);
			labelportC.setBorder(null);
			labelportC.setBackground(null);
			labelportC.setForeground(Color.white);
			labelportC.setVisible(false);
			this.add(labelportC);
			
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
			buttonPortBestätigen.addActionListener(spiel.cHandler);
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
		buttonEinsatz.addActionListener(spiel.cHandler);
		buttonEinsatz.setVisible(false);
		menuleiste.add(buttonEinsatz);
		
		
		//Bedienfeld: 
		bedienfeld.setBounds(500,500,1600,200);
		bedienfeld.setForeground(Color.black); 
		bedienfeld.setVisible(false);
		this.add(bedienfeld);
		
		//Überschrift: 
		ueberschriftCSpielC.setBounds(525,300,1000,200);
		Font schriftart = new Font ("Algerian",Font.PLAIN+Font.ITALIC,80);
		ueberschriftCSpielC.setForeground(Color.yellow);
		ueberschriftCSpielC.setFont(schriftart);
		ueberschriftCSpielC.setVisible(false);
		this.add(ueberschriftCSpielC); 

		//Überschrift: 
		unterueberschriftCSpielC.setBounds(650,350,1000,200);
		Font schriftart4 = new Font ("Algerian",Font.PLAIN+Font.ITALIC,25);
		unterueberschriftCSpielC.setForeground(Color.yellow);
		unterueberschriftCSpielC.setFont(schriftart4);
		unterueberschriftCSpielC.setVisible(false);
		this.add(unterueberschriftCSpielC);

		//Überschrift: 
		labelSpieler1C.setBounds(30,200,1000,200);
		Font schriftart1 = new Font("Algerian",Font.PLAIN+Font.ITALIC,40);
		labelSpieler1C.setForeground(Color.yellow); 
		labelSpieler1C.setFont(schriftart1);
		labelSpieler1C.setVisible(false);
		this.add(labelSpieler1C); 
		
		//Überschrift: 
		labelSpieler2C.setBounds(1300,200,1000,200);
		Font schriftart2 = new Font("Algerian",Font.PLAIN+Font.ITALIC,40);
		labelSpieler2C.setForeground(Color.yellow); 
		labelSpieler2C.setFont(schriftart2);
		labelSpieler2C.setVisible(false);
		this.add(labelSpieler2C);

		//Überschrift: 
		labelBankC.setBounds(725,1,1000,100);
		Font schriftart3 = new Font("Algerian",Font.PLAIN+Font.ITALIC,40);
		labelBankC.setForeground(Color.yellow); 
		labelBankC.setFont(schriftart3);
		labelBankC.setVisible(false);
		this.add(labelBankC);
		
	//Karten: 
		
		karte5Spieler1.setBounds(200,0,150,213);
		karte5Spieler1.setFocusable(false);
		karte5Spieler1.setBorder(null);
		karte5Spieler1.setVisible(false);
		kartenfeldS1g.add(karte5Spieler1);
		
		karte4Spieler1.setBounds(150,0,150,213);
		karte4Spieler1.setFocusable(false);
		karte4Spieler1.setBorder(null);
		karte4Spieler1.setVisible(false);
		kartenfeldS1g.add(karte4Spieler1);
		
		
		karte3Spieler1.setBounds(100,0,150,213);
		karte3Spieler1.setFocusable(false);
		karte3Spieler1.setBorder(null);
		karte3Spieler1.setVisible(false);
		kartenfeldS1g.add(karte3Spieler1);
		
		karte2Spieler1.setBounds(50,0,150,213);
		karte2Spieler1.setFocusable(false);
		karte2Spieler1.setBorder(null);
		karte2Spieler1.setVisible(false);
		kartenfeldS1g.add(karte2Spieler1);
		
		karte1Spieler1.setBounds(0,0,150,213);
		karte1Spieler1.setFocusable(false);
		karte1Spieler1.setBorder(null);
		karte1Spieler1.setVisible(false);
		kartenfeldS1g.add(karte1Spieler1); 
		
		karte5Spieler2.setBounds(200,0,150,213);
		karte5Spieler2.setFocusable(false);
		karte5Spieler2.setBorder(null);
		karte5Spieler2.setVisible(false);
		kartenfeldS2g.add(karte5Spieler2);
		

		karte4Spieler2.setBounds(150,0,150,213);
		karte4Spieler2.setFocusable(false);
		karte4Spieler2.setBorder(null);
		karte4Spieler2.setVisible(false);
		kartenfeldS2g.add(karte4Spieler2);
		
		karte3Spieler2.setBounds(100,0,150,213);
		karte3Spieler2.setFocusable(false);
		karte3Spieler2.setBorder(null);
		karte3Spieler2.setVisible(false);
		kartenfeldS2g.add(karte3Spieler2);
		
		karte2Spieler2.setBounds(50,0,150,213);
		karte2Spieler2.setFocusable(false);
		karte2Spieler2.setBorder(null);
		karte2Spieler2.setVisible(false);
		kartenfeldS2g.add(karte2Spieler2);
		
		
		karte1Spieler2.setBounds(0,0,150,213);
		karte1Spieler2.setFocusable(false);
		karte1Spieler2.setBorder(null);
		karte1Spieler2.setVisible(false);
		kartenfeldS2g.add(karte1Spieler2);
		
		karte5Bank.setBounds(200,0,150,213);
		karte5Bank.setFocusable(false);
		karte5Bank.setBorder(null);
		karte5Bank.setVisible(false);
		kartenfeldbankg.add(karte5Bank);

		
		karte4Bank.setBounds(150,0,150,213);
		karte4Bank.setFocusable(false);
		karte4Bank.setBorder(null);
		karte4Bank.setVisible(false);
		kartenfeldbankg.add(karte4Bank);
		
		karte3Bank.setBounds(100,0,150,213);
		karte3Bank.setFocusable(false);
		karte3Bank.setBorder(null);
		karte3Bank.setVisible(false);
		kartenfeldbankg.add(karte3Bank);
		
		karte2Bank.setBounds(50,0,150,213);
		karte2Bank.setFocusable(false);
		karte2Bank.setBorder(null);
		karte2Bank.setVisible(false);
		kartenfeldbankg.add(karte2Bank);
		
		karte1Bank.setBounds(0,0,150,213);
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
			buttonJeton10.addActionListener(spiel.cHandler);
			buttonJeton10.setContentAreaFilled(false);
			buttonJeton10.setVisible(false);
			menuleiste.add(buttonJeton10);
			
			buttonJeton25.setBounds(500,0,115,100);
			buttonJeton25.setFocusable(false);
			buttonJeton25.setBorder(null);
			buttonJeton25.setBackground(null);
			buttonJeton25.setForeground(Color.black);
			buttonJeton25.addActionListener(spiel.cHandler);
			buttonJeton25.setContentAreaFilled(false);
			buttonJeton25.setVisible(false);
			menuleiste.add(buttonJeton25);
			
			buttonJeton50.setBounds(1000,0,115,100);
			buttonJeton50.setFocusable(false);
			buttonJeton50.setBorder(null);
			buttonJeton50.setBackground(null);
			buttonJeton50.setForeground(Color.black);
			buttonJeton50.addActionListener(spiel.cHandler);
			buttonJeton50.setContentAreaFilled(false);
			buttonJeton50.setVisible(false);
			menuleiste.add(buttonJeton50);
			
			buttonJeton100.setBounds(1250,0,115,100);
			buttonJeton100.setFocusable(false);
			buttonJeton100.setBorder(null);
			buttonJeton100.setBackground(null);
			buttonJeton100.setForeground(Color.black);
			buttonJeton100.addActionListener(spiel.cHandler);
			buttonJeton100.setContentAreaFilled(false);
			buttonJeton100.setVisible(false);
			menuleiste.add(buttonJeton100);
			
			buttonEinsatzbestaetigen.setBounds(550,0,500,100);
			buttonEinsatzbestaetigen.setFocusable(false);
			buttonEinsatzbestaetigen.setBorder(null);
			buttonEinsatzbestaetigen.setBackground(null);
			buttonEinsatzbestaetigen.setForeground(Color.white);
			buttonEinsatzbestaetigen.addActionListener(spiel.cHandler);
			buttonEinsatzbestaetigen.setFont(new Font("Calibri", Font.PLAIN,30));
			buttonEinsatzbestaetigen.setContentAreaFilled(false);
			buttonEinsatzbestaetigen.setVisible(false);
			menuleiste.add(buttonEinsatzbestaetigen);
			
			
			einsatzausgabeSpieler1C.setBounds(25,375,165,25);
			einsatzausgabeSpieler1C.setFocusable(false);
			einsatzausgabeSpieler1C.setBorder(null);
			einsatzausgabeSpieler1C.setBackground(null);
			einsatzausgabeSpieler1C.setForeground(Color.white);
			einsatzausgabeSpieler1C.setVisible(false);
			this.add(einsatzausgabeSpieler1C);
			
			
			einsatzausgabeSpieler2C.setBounds(1300,375,165,25);
			einsatzausgabeSpieler2C.setFocusable(false);
			einsatzausgabeSpieler2C.setBorder(null);
			einsatzausgabeSpieler2C.setBackground(null);
			einsatzausgabeSpieler2C.setForeground(Color.white);
			einsatzausgabeSpieler2C.setVisible(false);
			this.add(einsatzausgabeSpieler2C);
			
			einsatzSpieler1C.setBounds(25,350,165,25);
			einsatzSpieler1C.setFocusable(false);
			einsatzSpieler1C.setBorder(null);
			einsatzSpieler1C.setBackground(null);
			einsatzSpieler1C.setForeground(Color.white);
			einsatzSpieler1C.setVisible(false);
			this.add(einsatzSpieler1C);
			
			einsatzSpieler2C.setBounds(1300,350,165,25);
			einsatzSpieler2C.setFocusable(false);
			einsatzSpieler2C.setBorder(null);
			einsatzSpieler2C.setBackground(null);
			einsatzSpieler2C.setForeground(Color.white);
			einsatzSpieler2C.setVisible(false);
			this.add(einsatzSpieler2C);
			
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
			buttonHit.addActionListener(spiel.cHandler);
			buttonHit.setFont(new Font("Calibri", Font.PLAIN,30));
			buttonHit.setContentAreaFilled(false);
			buttonHit.setVisible(false);
			menuleiste.add(buttonHit);
			
			buttonStay.setBounds(1000,0,200,100);
			buttonStay.setFocusable(false);
			buttonStay.setBorder(null);
			buttonStay.setBackground(null);
			buttonStay.setForeground(Color.white);
			buttonStay.addActionListener(spiel.cHandler);
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
			buttonNaechsteRunde.addActionListener(spiel.cHandler);
			buttonNaechsteRunde.setVisible(false);
			this.add(buttonNaechsteRunde);
			
			
			nachrichtS1C.setBounds(200,500,1000,100);
			nachrichtS1C.setForeground(Color.red); 
			nachrichtS1C.setFont(new Font("Calibri", Font.PLAIN,40));
			nachrichtS1C.setVisible(false);
			this.add(nachrichtS1C);
			
			
			nachrichtS2C.setBounds(1100,500,1000,100);
			nachrichtS2C.setForeground(Color.red); 
			nachrichtS2C.setFont(new Font("Calibri", Font.PLAIN,40));
			nachrichtS2C.setVisible(false);
			this.add(nachrichtS2C);
		}
		
		public void wartefenster() {
			wartenAufSpieler.setBounds(600,400,200,100);
			wartenAufSpieler.setForeground(Color.red); 
			wartenAufSpieler.setVisible(false);
			this.add(wartenAufSpieler);
		}
	
		
	
	
	
	
	
	
}
