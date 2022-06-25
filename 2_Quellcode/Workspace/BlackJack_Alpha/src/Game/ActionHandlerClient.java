package Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ActionHandlerClient implements ActionListener{

	public static int betragsetzen = 0;
	//boolean klick = false; 

	Client spiel;
	//Client spiel1;
	
	//Variablen Login und Registrierung
	
	String benutzername;
	String benutzernameLogin;
	String ipAdresse;
	char[] password;
	char[] password1; 
	char[] password2;


	
	
	
	
	public ActionHandlerClient(Client Client) {
		
		this.spiel = Client;
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
			spiel.startZuIP();
			break; 
		case "Abbrechen": 
			System.exit(0);
			break; 
		case "IP Adresse bestätigen": 
			spiel.ipZuAuswahl(); 
			Client.klicks=true;
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
		case "Zurück zum Start": 
			spiel.zurückZuStart(); 
			break;
		case ("Start"):
			spiel.setSpielernameClient();
			if (benutzername.equals("")) {
				JOptionPane.showMessageDialog(null, "Bitte geben Sie Ihren Namen ein.");
				break;} //gehe nur weiter wenn ein Name eingegeben wurde.
			 Client.klicks = true;
			 Client.spieler2LoggedIn = true;
			spiel.logRegZuEinsatz(); 
			//}
			break; 
		case "Abschließen": 
			spiel.logRegZuEinsatz(); 
			break;
		/*case "Port bestätigen": 
			spiel.portZuEinsatz(); 
			break; */
		case "Einsatz":
			spiel.einsatzZuJetons(); 
			break; 
		case "Jeton10": 
			Client.zwischenspeicher += 10;
			
			spiel.jeton10();
			
			break; 
		case "Jeton25":
			Client.zwischenspeicher += 25;
			spiel.jeton25(); 
			
			break; 
		case "Jeton50":
			Client.zwischenspeicher += 50;
			spiel.jeton50(); 
			
			break; 
		case "Jeton100":
			Client.zwischenspeicher += 100;
			spiel.jeton100();
			
			break; 
		case "Einsatz bestätigen": 
			spiel.einsatzAusrechnen();
			//spiel.kartenausgebenS_R1(); 
			
			Client.klicks = true; 
			spiel.jetonsZuHitundStay(); 
			break;
			
		case "Nächste Runde":
			spiel.auswertenZuEinsatz();
			break;
			
		case "Hit":
			Client.hitostay=1;
			Client.klicks = true;
			break;

			
		case "Stay":
			Client.hitostay=2;
			Client.klicks =true;
			System.out.println("Client sagt Stay!!");
			break;
			
			
		 
			
			
			
		}
}
	
}
