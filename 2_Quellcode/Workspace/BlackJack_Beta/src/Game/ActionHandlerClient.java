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
	//String benutzernameLogin;
	//String ipAdresse;
	//char[] password;
	//char[] password1; 
	//char[] password2;


	
	
	
	
	public ActionHandlerClient(Client2 client2) {
		
		this.spiel = client2;
	}
	
	




public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		
	benutzername = spiel.cbo.userText.getText();	
	//benutzernameLogin = spiel.cbo.userRegistText.getText();
	//password = spiel.cbo.passwordText.getPassword();
	//password1 = spiel.cbo.passwordText1.getPassword();
	//password2 = spiel.cbo.passwordText2.getPassword();	
	//ipAdresse = spiel.cbo.ipadresseText.getText();

	
		

		
		switch(command) {
		
		/**
		 * Fall 1: Wenn der Button "Spiel starten" geklickt wird, wird die Methode startZuIP() aufgerufen. 
		 */
		
		case "Spiel starten": 
			spiel.startZuIP();
			break; 
			
		/**
		* Fall 2: Wenn der Button "Abbrechen" geklickt wird, wird das Programm abgebrochen. 
		*/
			
		case "Abbrechen": 
			System.exit(0);
			break; 
			
		/**
		* Fall 3: Wenn der Button "IP Adresse bestätigen" geklickt wird, wird die Methode IPZuAuswahl() aufgerufen. 
		*/
			
		case "IP Adresse bestätigen": 
			spiel.ipZuAuswahl(); 
			Client2.klicks=true;
			break;
		
		/**
		* Fall 4: Wenn der Button "Login" geklickt wird, wird die Methode auswahlZuLogin() aufgerufen. 
		*/
			
		case "Login": 
			spiel.auswahlZuLogin();
			break;
		
		/**
		* Fall 5: Wenn der Button "Registrieren" geklickt wird, wird die Methode auswahlZuRegistrier() aufgerufen. 
		*/
			
		case "Registrieren":
			//spiel.benutzerErstellen();		
			spiel.auswahlZuRegistrier();
			break;
			
		/**
		* Fall 6: Wenn der Button "Zurück" geklickt wird, wird die Methode logRegZuAuswahl() aufgerufen. 
		*/
			
		case "Zurück":
			spiel.logRegZuAuswahl();
			break;	
			
		/**
		* Fall 7: Wenn der Button "Start" geklickt wird, wird die Methode logRegZuEinsatz() aufgerufen. 
		*/
			
		case "Zurück zum Start": 
			spiel.zurückZuStart(); 
			break;
			
		/**
		* Fall 8: Wenn der Button "Abschließen" geklickt wird, wird die Methode logRegZuEinsatz() aufgerufen. 
		*/
			
		case ("Start"): 
			spiel.setspielernameClient();
	        spiel.setspielernameServer();
			spiel.logRegZuEinsatz(); 
			break; 
			
		/**
		* Fall 9: Wenn der Button "Zurück zum Start" geklickt wird, wird die Methode zurückZuStart() aufgerufen. 
		*/
			
		case "Abschließen": 
			spiel.logRegZuEinsatz(); 
			break;
		
		/**
		* Fall 10: Wenn der Button "Einsatz" geklickt wird, wird die Methode einsatzZuJetons() aufgerufen. 
		*/
		
		case "Einsatz":
			spiel.einsatzZuJetons(); 
			break;
			
		/**
		* Fall 11.1: Wenn der Button "Jeton10" geklickt wird, wird die Methode jeton10() aufgerufen. 
		*/
			
		case "Jeton10": 
			Client2.swischespeicher += 10;
			
			spiel.jeton10();
			
			break;
			
		/**
		* Fall 11.2: Wenn der Button "Jeton25" geklickt wird, wird die Methode jeton25() aufgerufen. 
		*/
			
		case "Jeton25":
			Client2.swischespeicher += 25;
			spiel.jeton25(); 
			
			break; 
			
		/**
		* Fall 11.3: Wenn der Button "Jeton50" geklickt wird, wird die Methode jeton50() aufgerufen. 
		*/
			
		case "Jeton50":
			Client2.swischespeicher += 50;
			spiel.jeton50(); 
			
			break; 
			
		/**
		* Fall 11.4: Wenn der Button "Jeton100" geklickt wird, wird die Methode jeton100() aufgerufen. 
		*/
			
		case "Jeton100":
			Client2.swischespeicher += 100;
			spiel.jeton100();
			
			break; 
			
		/**
		* Fall 12: Wenn der Button "Einsatz bestätigen" geklickt wird, wird die Methode einsatzAusrechnen() aufgerufen. 
		*/
			
		case "Einsatz bestätigen": 
			spiel.einsatzAusrechnen();
			//spiel.kartenausgebenS_R1(); 
			
			Client2.klicks = true; 
			spiel.jetonsZuHitundStay(); 
			break;
			
		/**
		* Fall 13: Wenn der Button "Nächste Runde" geklickt wird, wird die Methode auswertenZuEinsatz() aufgerufen. 
		*/
			
		case "Nächste Runde":
			spiel.auswertenZuEinsatz();
			break;
			
		/**
		* Fall 14.1: Wenn der Button "Hit" geklickt wird, wird  hitostay() auf 1 gesetzt. 
		*/
			
		case "Hit":
			Client2.hitostay=1;
			Client2.klicks = true;
			break;

		/**
		* Fall 14.2: Wenn der Button "Stay" geklickt wird, wird hitostay() auf 2 gesetzt. 
		*/
			
		case "Stay":
			Client2.hitostay=2;
			Client2.klicks =true;
			System.out.println("Client sagt Stay!!");
			break;
			
			
		 
			
			
			
		}
}
	
}
