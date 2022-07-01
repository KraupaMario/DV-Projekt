package Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ActionHandler implements ActionListener{

	public static int betragsetzen = 0;
	//boolean klick = false; 

	Server spiel;
	//Client2 spiel1;

	/**
	 * String benutzername. 
	 */

	String benutzername;
	//String benutzernameLogin;
	//String password;
	//String password1; 
	//String password2;







	public ActionHandler(Server spiel) {

		this.spiel = spiel;
	}





	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();


		benutzername = spiel.bo.userText.getText();	
		//benutzernameLogin = spiel.bo.userRegistText.getText();
		//password = spiel.bo.passwordText.getText();
		//password1 = spiel.bo.passwordText1.getText();
		//password2 = spiel.bo.passwordText2.getText();	






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
			spiel.IPZuAuswahl();
			break;

		/**
		* Fall 4: Wenn der Button "Login" geklickt wird, wird die Methode auswahlZuLogin() aufgerufen. 
		*/

		case "Login": 
			spiel.auswahlZuLogin();
			break;

		/**
		* Fall 5: Wenn der Button "Registrieren" geklickt wird, wird die Methode auswahlZuRegistrier() aufgerufen. Dies ist ein Fall für eine mögliche Erweiterung des Programms.
		*/	

		case "Registrieren":
			//spiel.benutzerErstellen();		
			spiel.auswahlZuRegistrier();
			break;

		/**
		* Fall 6: Wenn der Button "Zurück" geklickt wird, wird die Methode logRegZuAuswahl() aufgerufen. 
		*/

		case "Zurück":
			spiel.zurueckZuStart();
			break;

		/**
		* Fall 7: Wenn der Button "Start" geklickt wird, wird die Methode logRegZuEinsatz() aufgerufen.
		* Des Weiteren wird die Methode.setSpielernameServer() aufgerufen. Nur wenn der Spieler einen Namen eingegeben hat, geht das Spiel weiter.
		* Server.klicks wird auf true gesetzt.
		* Server.spieler1LoggedIn wird auf true gesetzt. 
		*/

		case ("Start"): 
			spiel.setSpielernameServer();
			if (benutzername.equals("")) {
			JOptionPane.showMessageDialog(null, "Bitte geben Sie Ihren Namen ein.");
			break;} //gehe nur weiter wenn ein Name eingegeben wurde.
			Server.klicks = true;
			Server.spieler1LoggedIn = true;
			spiel.logRegZuEinsatz(); 
		break; 

		/**
		 * Fall 8: Wenn der Button "Abschließen" geklickt wird, wird die Methode logRegZuEinsatz() aufgerufen. 
		 */	

		case "Abschließen": 
			spiel.logRegZuEinsatz(); 
			break;

		/**
		* Fall 9: Wenn der Button "Zurück zum Start" geklickt wird, wird die Methode zurückZuStart() aufgerufen. 
		*/

		case "Zurück zum Start": 
			spiel.zurueckZuStart(); 
			break;

		/**
		* Fall 10: Wenn der Button "Einsatz" geklickt wird, wird die Methode einsatzZuJetons() aufgerufen. 
		*/


		case "Einsatz": 

			spiel.einsatzZuJetons(); 
			//Client2.wartenAufSpielerClient = true; 


			break;

		/**
		* Fall 11.1: Wenn der Button "Jeton10" geklickt wird, wird die Methode jeton10() aufgerufen und der Zwischenspeicher wird um 10 erhöht. 
		*/


		case "Jeton10": 
			Server.zwischenspeicher += 10;
			System.out.println("Server sw: "+ Server.zwischenspeicher);
			spiel.jeton10();

			break; 

		/**
		* Fall 11.2: Wenn der Button "Jeton25" geklickt wird, wird die Methode jeton25() aufgerufen und der Zwischenspeicher wird um 25 erhöht. 
		*/

		case "Jeton25":
			Server.zwischenspeicher += 25;
			spiel.jeton25(); 

			break; 

		/**
		* Fall 11.3: Wenn der Button "Jeton50" geklickt wird, wird die Methode jeton50() aufgerufen und der Zwischenspeicher wird um 50 erhöht. 
		*/

		case "Jeton50":
			Server.zwischenspeicher += 50;
			spiel.jeton50(); 

			break; 

		/**
		* Fall 11.4: Wenn der Button "Jeton100" geklickt wird, wird die Methode jeton100() aufgerufen und der Zwischenspeicher wird um 100 erhöht. 
		*/

		case "Jeton100":
			Server.zwischenspeicher += 100;
			spiel.jeton100();

			break;

		/**
		* Fall 12: Wenn der Button "Einsatz bestätigen" geklickt wird, wird die Methode einsatzAusrechnen() aufgerufen.
		* Außerdem wird die Methode jetonsZuHitundStay aufgerufen.  
		* Server.klicks wird auf true gesetzt.
		*/

		case "Einsatz bestätigen": 
			spiel.einsatzAusrechnen();
			//Client2.wartenAufSpielerClient = false; 

			Server.klicks = true; 

			spiel.jetonsZuHitundStay(); 
			break;

		/**
		* Fall 13: Wenn der Button "Nächste Runde" geklickt wird, wird die Methode auswertenZuEinsatz() aufgerufen. 
		*/

		case "Nächste Runde":
			spiel.auswertenZuEinsatz();
			break;

		/**
		* Fall 14.1: Wenn der Button "Hit" geklickt wird, wird  Server.hitostay auf 1 gesetzt.
		* Server.klicks wird auf true gesetzt.  
		*/


		case "Hit":
			Server.hitostay=1;
			Server.klicks = true;
			break;

		/**
		* Fall 14.2: Wenn der Button "Stay" geklickt wird, wird Server.hitostay auf 2 gesetzt.
		* Server.klicks wird auf true gesetzt.  
		*/


		case "Stay":
			Server.hitostay=2;
			Server.klicks =true;
			break;


		}
	}

}
