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
			spiel.startZuIP();
			break; 
		case "Abbrechen": 
			System.exit(0);
			break; 
		case "IP Adresse best�tigen": 
			spiel.IPZuAuswahl();
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
			spiel.logRegZuEinsatz(); 
			//}
			break; 
		case "Abschlie�en": 
			spiel.logRegZuEinsatz(); 
			break;
		case "Zur�ck zum Start": 
			spiel.zur�ckZuStart(); 
			break;
		
		/*case "Port best�tigen": 
			spiel.portZuEinsatz(); 
			break; */
		case "Einsatz": 
			
			spiel.einsatzZuJetons(); 
			//Client2.wartenAufSpielerClient = true; 
			
			
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
		case "Einsatz best�tigen": 
			spiel.einsatzAusrechnen();
			//Client2.wartenAufSpielerClient = false; 
			
			Server2.klicks = true; 
			
			spiel.jetonsZuHitundStay(); 
			break;
			
		case "N�chste Runde":
			spiel.auswertenZuEinsatz();
			break;
			

		case "Hit":
			Server2.hitostay=1;
			Server2.klicks = true;
			break;

			
		case "Stay":
			Server2.hitostay=2;
			Server2.klicks =true;
			break;
			
			
		}
}
	
}