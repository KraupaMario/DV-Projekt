package Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ActionHandler implements ActionListener{

	public static int betragsetzen = 0;
	//boolean klick = false; 

	Server spiel;
	//Client2 spiel1;
	
	//Variablen Login und Registrierung
	
	String benutzername;
	String benutzernameLogin;
	String password;
	String password1; 
	String password2;

	

	
	
	
	
	public ActionHandler(Server spiel) {
		
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
		case "LOS!": 
			spiel.IPZuAuswahl();
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
		case "Start":
		    spiel.setSpielernameServer();
		    if (benutzername.equals("")) {
				JOptionPane.showMessageDialog(null, "Bitte geben Sie Ihren Namen ein.");
				break;} //gehe nur weiter wenn ein Name eingegeben wurde.
		    Server.klicks = true;
		    Server.spieler1LoggedIn = true;
			spiel.logRegZuEinsatz(); 
			break; 
		case "Abschließen": 
			spiel.logRegZuEinsatz(); 
			break;
		case "Zurück zum Start": 
			spiel.zurückZuStart(); 
			break;
		
		/*case "Port bestätigen": 
			spiel.portZuEinsatz(); 
			break; */
		case "Einsatz": 
			
			spiel.einsatzZuJetons(); 
			//Client2.wartenAufSpielerClient = true; 
			
			
			break; 
		case "Jeton10": 
			Server.zwischenspeicher += 10;
			System.out.println("Server sw: "+ Server.zwischenspeicher);
			spiel.jeton10();
			
			break; 
		case "Jeton25":
			Server.zwischenspeicher += 25;
			spiel.jeton25(); 
			
			break; 
		case "Jeton50":
			Server.zwischenspeicher += 50;
			spiel.jeton50(); 
			
			break; 
		case "Jeton100":
			Server.zwischenspeicher += 100;
			spiel.jeton100();
			
			break; 
		case "Einsatz bestätigen": 
			spiel.einsatzAusrechnen();
			//Client2.wartenAufSpielerClient = false; 
			
			Server.klicks = true; 
			
			spiel.jetonsZuHitundStay(); 
			break;
			
		case "Nächste Runde":
			spiel.auswertenZuEinsatz();
			break;
			

		case "Hit":
			Server.hitostay=1;
			Server.klicks = true;
			break;

			
		case "Stay":
			Server.hitostay=2;
			Server.klicks =true;
			break;
			
			
		}
}
	
}
