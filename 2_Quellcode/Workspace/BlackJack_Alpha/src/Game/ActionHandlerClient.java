package Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandlerClient implements ActionListener{

	public static int betragsetzen = 0;
	//boolean klick = false; 

	Client2 spiel;
	//Client2 spiel1;
	
	//Variablen Login und Registrierung
	
	String benutzername;
	String benutzernameLogin;
	String ipAdresse;
	char[] password;
	char[] password1; 
	char[] password2;


	
	
	
	
	public ActionHandlerClient(Client2 client2) {
		
		this.spiel = client2;
	}
	
	




public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		
	benutzername = spiel.cbo.userText.getText();	
	benutzernameLogin = spiel.cbo.userRegistText.getText();
	password = spiel.cbo.passwordText.getPassword();
	password1 = spiel.cbo.passwordText1.getPassword();
	password2 = spiel.cbo.passwordText2.getPassword();	
	ipAdresse = spiel.cbo.ipadresseText.getText();

	
		

		
		switch(command) {
		
		case "Spiel starten": 
			spiel.startZuAuswahl();
			break; 
		case "Abbrechen": 
			System.exit(0);
			break; 
		case "Login": 
			spiel.auswahlZuLogin();
			break;
		case "Registrieren":
			//spiel.benutzerErstellen();		
			spiel.auswahlZuRegistrier();
			break;
		case "Zur�ck":
			spiel.logRegZuAuswahl();
			break;	
		case ("Start"): 
			//if(spiel.passwordPruefen()==true) {
			spiel.loginZuIP(); 
			//}
			break; 
		case "Abschlie�en": 
			spiel.loginZuIP(); 
			break;
		case "IP Adresse best�tigen": 
			spiel.ipZuPort(); 
			Client2.klicks=true;
			break;
		case "Port best�tigen": 
			spiel.portZuEinsatz(); 
			break; 
		case "Einsatz": 
			spiel.einsatzZuJetons(); 
			break; 
		case "Jeton10": 
			Server2.swischespeicher += 10;
			
			spiel.jeton10();
			
			break; 
		case "Jeton25":
			Server2.swischespeicher += 25;
			spiel.jeton25(); 
			
			break; 
		case "Jeton50":
			Server2.swischespeicher += 50;
			spiel.jeton50(); 
			
			break; 
		case "Jeton100":
			Server2.swischespeicher += 100;
			spiel.jeton100();
			
			break; 
		case "Einsatz best�tigen": 
			spiel.einsatzAusrechnen();
			Server2.klicks = true; 
			break;
			
			
		 
			
			
			
		}
}
	
}
