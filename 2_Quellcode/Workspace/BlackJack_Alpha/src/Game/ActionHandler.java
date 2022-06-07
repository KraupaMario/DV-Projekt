package Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener{

	public static  int betragsetzen = 0;

	Server2 spiel;
	
	//Variablen Login und Registrierung
	
	String benutzername;
	String benutzernameLogin;
	char[] password;
	char[] password1; 
	char[] password2;


	
	
	
	
	public ActionHandler(Server2 spiel) {
		
		this.spiel = spiel;
	}
	boolean klick = false; 
	
	
	
	
public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		
	benutzername = spiel.bo.userText.getText();	
	benutzernameLogin = spiel.bo.userRegistText.getText();
	password = spiel.bo.passwordText.getPassword();
	password1 = spiel.bo.passwordText1.getPassword();
	password2 = spiel.bo.passwordText2.getPassword();	
	

	
		

		
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
		case "Zurück":
			spiel.logRegZuAuswahl();
			break;	
		case ("Start"): 
			//if(spiel.passwordPruefen()==true) {
			spiel.loginZuIP(); 
			//}
			break; 
		case "Abschließen": 
			spiel.loginZuIP(); 
			break;
		case "IP Adresse bestätigen": 
			spiel.ipZuPort(); 
			break;
		case "Port bestätigen": 
			spiel.portZuEinsatz(); 
			break; 
		case "Einsatz": 
			spiel.einsatzZuJetons(); 
			break; 
		case "Jeton10": 
			betragsetzen += 10;
			spiel.jeton10();
			
			break; 
		case "Jeton25":
			betragsetzen += 25;
			spiel.jeton25(); 
			
			break; 
		case "Jeton50":
			betragsetzen += 50;
			spiel.jeton50(); 
			
			break; 
		case "Jeton100":
			betragsetzen += 100;
			spiel.jeton100();
			
			break; 
		case "Einsatz bestätigen": 
			spiel.einsatzAusrechnen();
			klick = true; 
			break;
			
			
		 
			
			
			
		}
}
	
}
