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

	Client2 spiel;
	

	 JFrame frame = new JFrame();
	 JPanel panel = new JPanel();
	 
	 //Menubar
	 JMenuBar menu = new JMenuBar(); 
	 
	
	





	//Label
	 JLabel labelBenutzernameC = new JLabel ("Benutzername:");
	 JLabel labelPasswortC = new JLabel("Passwort:");
	// JLabel ueberschriftC = new JLabel("BLACK JACK"); 
	 JLabel ueberschriftCSpielC = new JLabel ("BLACK JACK"); 
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
	 //JLabel kontostandSpieler1C = new JLabel ("Kontostand Spieler1:"); 
	 JLabel kontostandSpieler2 = new JLabel ("Kontostand Spieler2:");
	 JLabel einsatzausgabeSpieler1C = new JLabel();
	 JLabel einsatzausgabeSpieler2C = new JLabel();
	 JLabel labelipadresseC2 = new JLabel(); 
	 JLabel kartenwertSpieler1 = new JLabel(); 
	 JLabel kartenwertSpieler2 = new JLabel(); 
	 JLabel kartenwertDealer = new JLabel(); 
	 JLabel nachrichtS1 = new JLabel();
	 JLabel nachrichtS2 = new JLabel();
	 JLabel anmeldetext = new JLabel();
	 JLabel registrierungtext = new JLabel();
	 JLabel ueberschrift = new JLabel("BLACK JACK"); 
	

	 

	 Icon jeton_10 = new ImageIcon(getClass().getResource("Jeton_10.PNG"));
	 Icon jeton_25 = new ImageIcon(getClass().getResource("Jeton_25.PNG"));
	 Icon jeton_50 = new ImageIcon(getClass().getResource("Jeton_50.PNG")); 
	 Icon jeton_100 = new ImageIcon(getClass().getResource("Jeton_100.PNG"));
	 Icon testkarte = new ImageIcon(getClass().getResource("testkarte.PNG"));
	 Icon rückseite =  new ImageIcon(getClass().getClassLoader().getResource("Karten/front.jpg"));
	 Icon lasvegas = new ImageIcon(getClass().getClassLoader().getResource("Karten/lasvegas.jpg"));
	 Icon blackjack = new ImageIcon(getClass().getClassLoader().getResource("Karten/blackjacklogo.png"));
	 Icon hintergrundtisch = new ImageIcon(getClass().getClassLoader().getResource("Karten/hintergrundtisch.jpg"));
	 
	 JLabel panelstart = new JLabel(hintergrundtisch);
	 JLabel hintergrundmenu = new JLabel(lasvegas);
	 JLabel bedienfeld = new JLabel();
	 JLabel logo = new JLabel(blackjack);
	 
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
	 JButton buttonZurück = new JButton("Zurück");
	 JButton buttonZurückZuStart = new JButton("Zurück zum Start");
	 JButton buttonIPAdresseBestätigen = new JButton ("IP Adresse bestätigen");
	 JButton buttonPortBestätigen = new JButton ("Port bestätigen"); 
	 JButton buttonEinsatz = new JButton ("Einsatz");
	 JButton buttonJeton10 = new JButton("Jeton10",jeton_10);
	 JButton buttonJeton25 = new JButton("Jeton25", jeton_25);
	 JButton buttonJeton50 = new JButton ("Jeton50", jeton_50);
	 JButton buttonJeton100 = new JButton("Jeton100", jeton_100);
	 JButton buttonEinsatzbestätigen = new JButton("Einsatz bestätigen");
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

		startfenster();
		auswahlfenster();
		anmeldefenster();
		registrierfenster();
		ipadressefenster(); 
		portfenster();
		einsatzfenster(); 
		jetonfenster();
		auswerteFenster();
		
		 
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
		

		buttonZurückZuStart.setBounds(165,625,250,50);
		buttonZurückZuStart.setFocusable(false);
		buttonZurückZuStart.setBorder(null);
		buttonZurückZuStart.setBackground(Color.gray);
		buttonZurückZuStart.setFocusPainted(false);
		buttonZurückZuStart.setForeground(Color.white);
		buttonZurückZuStart.setFont(new Font("Book Antiqua", Font.PLAIN,30));
		buttonZurückZuStart.setContentAreaFilled(false);
		buttonZurückZuStart.addActionListener(spiel.cHandler);
		buttonZurückZuStart.setVisible(false);
		panelstart.add(buttonZurückZuStart);
		

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


		buttonZurück.setBounds(380,610,150,50);
		buttonZurück.setFocusable(false);
		buttonZurück.setBorder(null);
		buttonZurück.setBackground(Color.gray);
		buttonZurück.setFocusPainted(false);
		buttonZurück.setForeground(Color.white);
		buttonZurück.setFont(new Font("Book Antiqua", Font.PLAIN,25));
		buttonZurück.setContentAreaFilled(false);
		buttonZurück.addActionListener(spiel.cHandler);
		buttonZurück.setVisible(false);
		panelstart.add(buttonZurück);
		
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
		
		

		//Überschrift: 
		ueberschrift.setBounds(400,100,1000,200);
		Font schriftart = new Font ("Algerian",Font.PLAIN+Font.ITALIC,120);
		ueberschrift.setFont(schriftart);
		ueberschrift.setVisible(true);
		this.add(ueberschrift); 


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
			buttonIPAdresseBestätigen.setBounds(50,610,250,50);
			buttonIPAdresseBestätigen.setFocusable(false);
			buttonIPAdresseBestätigen.setBorder(null);
			buttonIPAdresseBestätigen.setBackground(Color.gray);
			buttonIPAdresseBestätigen.setFocusPainted(false);
			buttonIPAdresseBestätigen.setForeground(Color.white);
			buttonIPAdresseBestätigen.setFont(new Font("Book Antiqua", Font.PLAIN,25));
			buttonIPAdresseBestätigen.setContentAreaFilled(false);
			buttonIPAdresseBestätigen.addActionListener(spiel.cHandler);
			buttonIPAdresseBestätigen.setVisible(false);
			panelstart.add(buttonIPAdresseBestätigen);
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
			
		
		//Buttons:	
		buttonEinsatz.setBounds(0,700,1600,100);
		buttonEinsatz.setFocusable(false);
		buttonEinsatz.setBorder(null);
		buttonEinsatz.setBackground(null);
		buttonEinsatz.setForeground(Color.white);
		buttonEinsatz.addActionListener(spiel.cHandler);
		buttonEinsatz.setVisible(false);
		this.add(buttonEinsatz);
		
		
		//Bedienfeld: 
		bedienfeld.setBounds(500,500,1600,200);
		bedienfeld.setForeground(Color.black); 
		bedienfeld.setVisible(false);
		this.add(bedienfeld);
		
		//Überschrift: 
		ueberschriftCSpielC.setBounds(500,250,1000,200);
		Font schriftart = new Font ("Algerian",Font.PLAIN+Font.ITALIC,80);
		ueberschriftCSpielC.setForeground(Color.yellow);
		ueberschriftCSpielC.setFont(schriftart);
		ueberschriftCSpielC.setVisible(false);
		this.add(ueberschriftCSpielC); 
		
		//Überschrift: 
		labelSpieler1C.setBounds(200,500,1000,200);
		Font schriftart1 = new Font("Algerian",Font.PLAIN+Font.ITALIC,40);
		labelSpieler1C.setForeground(Color.yellow); 
		labelSpieler1C.setFont(schriftart1);
		labelSpieler1C.setVisible(false);
		this.add(labelSpieler1C); 
		
		//Überschrift: 
		labelSpieler2C.setBounds(1100,500,1000,200);
		Font schriftart2 = new Font("Algerian",Font.PLAIN+Font.ITALIC,40);
		labelSpieler2C.setForeground(Color.yellow); 
		labelSpieler2C.setFont(schriftart2);
		labelSpieler2C.setVisible(false);
		this.add(labelSpieler2C);

		//Überschrift: 
		labelBankC.setBounds(600,10,1000,100);
		Font schriftart3 = new Font("Algerian",Font.PLAIN+Font.ITALIC,40);
		labelBankC.setForeground(Color.yellow); 
		labelBankC.setFont(schriftart3);
		labelBankC.setVisible(false);
		this.add(labelBankC);
		
		//Karten: 
		karte5Spieler1.setBounds(500,350,150,213);
		karte5Spieler1.setFocusable(false);
		karte5Spieler1.setBorder(null);
		karte5Spieler1.setVisible(false);
		this.add(karte5Spieler1);
		
		karte4Spieler1.setBounds(400,350,150,213);
		karte4Spieler1.setFocusable(false);
		karte4Spieler1.setBorder(null);
		karte4Spieler1.setVisible(false);
		this.add(karte4Spieler1);
		
		
		karte3Spieler1.setBounds(300,350,150,213);
		karte3Spieler1.setFocusable(false);
		karte3Spieler1.setBorder(null);
		karte3Spieler1.setVisible(false);
		this.add(karte3Spieler1);
		
		karte2Spieler1.setBounds(200,350,150,213);
		karte2Spieler1.setFocusable(false);
		karte2Spieler1.setBorder(null);
		karte2Spieler1.setVisible(false);
		this.add(karte2Spieler1);
		
		karte1Spieler1.setBounds(100,350,150,213);
		karte1Spieler1.setFocusable(false);
		karte1Spieler1.setBorder(null);
		karte1Spieler1.setVisible(false);
		this.add(karte1Spieler1); 
		
		karte5Spieler2.setBounds(1400,350,150,213);
		karte5Spieler2.setFocusable(false);
		karte5Spieler2.setBorder(null);
		karte5Spieler2.setVisible(false);
		this.add(karte5Spieler2);
		

		karte4Spieler2.setBounds(1300,350,150,213);
		karte4Spieler2.setFocusable(false);
		karte4Spieler2.setBorder(null);
		karte4Spieler2.setVisible(false);
		this.add(karte4Spieler2);
		
		karte3Spieler2.setBounds(1200,350,150,213);
		karte3Spieler2.setFocusable(false);
		karte3Spieler2.setBorder(null);
		karte3Spieler2.setVisible(false);
		this.add(karte3Spieler2);
		
		karte2Spieler2.setBounds(1100,350,150,213);
		karte2Spieler2.setFocusable(false);
		karte2Spieler2.setBorder(null);
		karte2Spieler2.setVisible(false);
		this.add(karte2Spieler2);
		
		
		karte1Spieler2.setBounds(1000,350,150,213);
		karte1Spieler2.setFocusable(false);
		karte1Spieler2.setBorder(null);
		karte1Spieler2.setVisible(false);
		this.add(karte1Spieler2);
		
		karte5Bank.setBounds(900,75,150,213);
		karte5Bank.setFocusable(false);
		karte5Bank.setBorder(null);
		karte5Bank.setVisible(false);
		this.add(karte5Bank);

		
		karte4Bank.setBounds(800,75,150,213);
		karte4Bank.setFocusable(false);
		karte4Bank.setBorder(null);
		karte4Bank.setVisible(false);
		this.add(karte4Bank);
		
		karte3Bank.setBounds(700,75,150,213);
		karte3Bank.setFocusable(false);
		karte3Bank.setBorder(null);
		karte3Bank.setVisible(false);
		this.add(karte3Bank);
		
		karte2Bank.setBounds(600,75,150,213);
		karte2Bank.setFocusable(false);
		karte2Bank.setBorder(null);
		karte2Bank.setVisible(false);
		this.add(karte2Bank);
		
		karte1Bank.setBounds(500,75,150,213);
		karte1Bank.setFocusable(false);
		karte1Bank.setBorder(null);
		karte1Bank.setVisible(false);
		this.add(karte1Bank);

		
		}
		
		public void jetonfenster() {
			buttonJeton10.setBounds(350,700,100,100);
			buttonJeton10.setFocusable(false);
			buttonJeton10.setBorder(null);
			buttonJeton10.setBackground(null);
			buttonJeton10.setForeground(Color.white);
			buttonJeton10.addActionListener(spiel.cHandler);
			buttonJeton10.setVisible(false);
			this.add(buttonJeton10);
			
			buttonJeton25.setBounds(600,700,100,100);
			buttonJeton25.setFocusable(false);
			buttonJeton25.setBorder(null);
			buttonJeton25.setBackground(null);
			buttonJeton25.setForeground(Color.white);
			buttonJeton25.addActionListener(spiel.cHandler);
			buttonJeton25.setVisible(false);
			this.add(buttonJeton25);
			
			buttonJeton50.setBounds(850,700,100,100);
			buttonJeton50.setFocusable(false);
			buttonJeton50.setBorder(null);
			buttonJeton50.setBackground(null);
			buttonJeton50.setForeground(Color.white);
			buttonJeton50.addActionListener(spiel.cHandler);
			buttonJeton50.setVisible(false);
			this.add(buttonJeton50);
			
			buttonJeton100.setBounds(1100,700,100,100);
			buttonJeton100.setFocusable(false);
			buttonJeton100.setBorder(null);
			buttonJeton100.setBackground(null);
			buttonJeton100.setForeground(Color.white);
			buttonJeton100.addActionListener(spiel.cHandler);
			buttonJeton100.setVisible(false);
			this.add(buttonJeton100);
			
			buttonEinsatzbestätigen.setBounds(650,650,200,50);
			buttonEinsatzbestätigen.setFocusable(false);
			buttonEinsatzbestätigen.setBorder(null);
			buttonEinsatzbestätigen.setBackground(null);
			buttonEinsatzbestätigen.setForeground(Color.white);
			buttonEinsatzbestätigen.addActionListener(spiel.cHandler);
			buttonEinsatzbestätigen.setVisible(false);
			this.add(buttonEinsatzbestätigen);
			
			
			einsatzausgabeSpieler1C.setBounds(10,650,165,25);
			einsatzausgabeSpieler1C.setFocusable(false);
			einsatzausgabeSpieler1C.setBorder(null);
			einsatzausgabeSpieler1C.setBackground(null);
			einsatzausgabeSpieler1C.setForeground(Color.white);
			einsatzausgabeSpieler1C.setVisible(false);
			this.add(einsatzausgabeSpieler1C);
			
			
			einsatzausgabeSpieler2C.setBounds(1350,650,165,25);
			einsatzausgabeSpieler2C.setFocusable(false);
			einsatzausgabeSpieler2C.setBorder(null);
			einsatzausgabeSpieler2C.setBackground(null);
			einsatzausgabeSpieler2C.setForeground(Color.white);
			einsatzausgabeSpieler2C.setVisible(false);
			this.add(einsatzausgabeSpieler2C);
			
			einsatzSpieler1C.setBounds(10,600,165,25);
			einsatzSpieler1C.setFocusable(false);
			einsatzSpieler1C.setBorder(null);
			einsatzSpieler1C.setBackground(null);
			einsatzSpieler1C.setForeground(Color.white);
			einsatzSpieler1C.setVisible(false);
			this.add(einsatzSpieler1C);
			
			einsatzSpieler2C.setBounds(1350,600,165,25);
			einsatzSpieler2C.setFocusable(false);
			einsatzSpieler2C.setBorder(null);
			einsatzSpieler2C.setBackground(null);
			einsatzSpieler2C.setForeground(Color.white);
			einsatzSpieler2C.setVisible(false);
			this.add(einsatzSpieler2C);
			
			/*kontostandSpieler1C.setBounds(10,10,165,25);
			kontostandSpieler1C.setFocusable(false);
			kontostandSpieler1C.setBorder(null);
			kontostandSpieler1C.setBackground(null);
			kontostandSpieler1C.setForeground(Color.white);
			kontostandSpieler1C.setVisible(false);
			this.add(kontostandSpieler1C);*/
			
			kontostandSpieler2.setBounds(10,10,165,25);
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
			
			buttonHit.setBounds(400,700,200,100);
			buttonHit.setFocusable(false);
			buttonHit.setBorder(null);
			buttonHit.setBackground(null);
			buttonHit.setForeground(Color.white);
			buttonHit.addActionListener(spiel.cHandler);
			buttonHit.setVisible(false);
			this.add(buttonHit);
			
			buttonStay.setBounds(1000,700,200,100);
			buttonStay.setFocusable(false);
			buttonStay.setBorder(null);
			buttonStay.setBackground(null);
			buttonStay.setForeground(Color.white);
			buttonStay.addActionListener(spiel.cHandler);
			buttonStay.setVisible(false);
			this.add(buttonStay);
			
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
