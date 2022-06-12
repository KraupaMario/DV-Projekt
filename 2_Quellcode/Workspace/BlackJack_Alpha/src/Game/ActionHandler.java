package Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener{

	public static int betragsetzen = 0;
	//boolean klick = false; 

	Server2 spiel;
	//Client2 spiel1;
	
	//Variablen Login und Registrierung
	
	String benutzername;
	String benutzernameLogin;
	String password;
	String password1; 
	String password2;


	
	
	
	
	public ActionHandler(Server2 spiel) {
		
		this.spiel = spiel;
	}
	
	
	
	
	
public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		
	benutzername = spiel.bo.userText.getText();	
	benutzernameLogin = spiel.bo.userRegistText.getText();
	password = spiel.bo.passwordText.getText();
	password1 = spiel.bo.passwordText1.getText();
	password2 = spiel.bo.passwordText2.getText();	
	

	
		

		
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
			spiel.ipZuEinsatz(); 
			break;
		/*case "Port bestätigen": 
			spiel.portZuEinsatz(); 
			break; */
		case "Einsatz": 
			
			spiel.einsatzZuJetons(); 
			
			
			break; 
		case "Jeton10": 
			Server2.swischespeicher += 10;
			System.out.println("Server2 sw: "+ Server2.swischespeicher);
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
		case "Einsatz bestätigen": 
			spiel.einsatzAusrechnen();
			
			Server2.klicks = true; 
			break;
			
			
		 
			
			
			
		}
}
	
}
