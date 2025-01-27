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
	//String benutzernameLogin;
	String ipAdresse;
	//char[] password;
	//char[] password1; 
	//char[] password2;






	public ActionHandlerClient(Client Client) {

		this.spiel = Client;
	}






	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();


		benutzername = spiel.cbo.userText.getText();	
		//benutzernameLogin = spiel.cbo.userRegistText.getText();
		//password = spiel.cbo.passwordText.getPassword();
		//password1 = spiel.cbo.passwordText1.getPassword();
		//password2 = spiel.cbo.passwordText2.getPassword();	
		ipAdresse = spiel.cbo.ipadresseText.getText();





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
			 * Fall 3: Wenn der Button "IP Adresse best�tigen" geklickt wird, wird die Methode IPZuAuswahl() aufgerufen. 
			 * Client.klicks wird auf true gesetzt. 
			 */

		case "IP Adresse best�tigen": 
			spiel.ipZuAuswahl(); 
			Client.klicks=true;
			break;

			/**
			 * Fall 4: Wenn der Button "Login" geklickt wird, wird die Methode auswahlZuLogin() aufgerufen. 
			 */

		case "Login": 
			spiel.auswahlZuLogin();
			break;

			/**
			 * Fall 5: Wenn der Button "Registrieren" geklickt wird, wird die Methode auswahlZuRegistrier() aufgerufen. Dies ist ein Fall f�r eine m�gliche Erweiterung des Programms. 
			 */

		case "Registrieren":
			//spiel.benutzerErstellen();		
			spiel.auswahlZuRegistrier();
			break;

			/**
			 * Fall 6: Wenn der Button "Zur�ck" geklickt wird, wird die Methode logRegZuAuswahl() aufgerufen. 
			 */

		case "Zur�ck":
			spiel.zurueckZuStart();
			break;	
			
			/**
			 * Fall 7: Wenn der Button "Zur�ck zum Start" geklickt wird, wird die Methode zur�ckZuStart() aufgerufen. 
			 */

			

		case "Zur�ck zum Start": 
			spiel.zurueckZuStart(); 
			break;

			/**
			* Fall 8: Wenn der Button "Start" geklickt wird, wird die Methode logRegZuEinsatz() aufgerufen.
			* Des Weiteren wird die Methode setSpielernameServer() aufgerufen. Nur wenn der Spieler einen Namen eingegeben hat, geht das Spiel weiter.
			* Client.klicks wird auf true gesetzt.
			* Client.spieler1LoggedIn wird auf true gesetzt. 
			*/	

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
		/**
		 * Fall 9: Wenn der Button "Abschlie�en" geklickt wird, wird die Methode logRegZuEinsatz() aufgerufen. 
		 */	

		case "Abschlie�en": 
			spiel.logRegZuEinsatz(); 
			break;

			/**
			 * Fall 10: Wenn der Button "Einsatz" geklickt wird, wird die Methode einsatzZuJetons() aufgerufen. 
			 */

		case "Einsatz":
			spiel.einsatzZuJetons(); 
			break;

			/**
			* Fall 11.1: Wenn der Button "Jeton10" geklickt wird, wird die Methode jeton10() aufgerufen und der Zwischenspeicher wird um 10 erh�ht. 
			*/


		case "Jeton10": 
			Client.zwischenspeicher += 10;

			spiel.jeton10();

			break;

			/**
			* Fall 11.2: Wenn der Button "Jeton25" geklickt wird, wird die Methode jeton25() aufgerufen und der Zwischenspeicher wird um 25 erh�ht. 
			*/

		case "Jeton25":
			Client.zwischenspeicher += 25;
			spiel.jeton25(); 

			break; 

			/**
			* Fall 11.3: Wenn der Button "Jeton50" geklickt wird, wird die Methode jeton50() aufgerufen und der Zwischenspeicher wird um 50 erh�ht. 
			*/

		case "Jeton50":
			Client.zwischenspeicher += 50;
			spiel.jeton50(); 

			break; 

			/**
			* Fall 11.4: Wenn der Button "Jeton100" geklickt wird, wird die Methode jeton100() aufgerufen und der Zwischenspeicher wird um 100 erh�ht. 
			*/

		case "Jeton100":
			Client.zwischenspeicher += 100;
			spiel.jeton100();

			break; 

			/**
			* Fall 12: Wenn der Button "Einsatz best�tigen" geklickt wird, wird die Methode einsatzAusrechnen() aufgerufen.
			* Au�erdem wird die Methode jetonsZuHitundStay aufgerufen.  
			* Client.klicks wird auf true gesetzt.
			*/

		case "Einsatz best�tigen": 
			spiel.einsatzAusrechnen();
			//spiel.kartenausgebenS_R1(); 

			Client.klicks = true; 
			spiel.jetonsZuHitundStay(); 
			break;

			/**
			* Fall 13: Wenn der Button "N�chste Runde" geklickt wird, wird die Methode auswertenZuEinsatz() aufgerufen. 
			*/

		case "N�chste Runde":
			spiel.auswertenZuEinsatz();
			break;

			/**
			* Fall 14.1: Wenn der Button "Hit" geklickt wird, wird  Client.hitostay auf 1 gesetzt.
			* Client.klicks wird auf true gesetzt.  
			*/

		case "Hit":
			Client.hitostay=1;
			Client.klicks = true;
			break;

			/**
			* Fall 14.2: Wenn der Button "Stay" geklickt wird, wird Client.hitostay auf 2 gesetzt.
			* Client.klicks wird auf true gesetzt.  
			*/

		case "Stay":
			Client.hitostay=2;
			Client.klicks =true;
			System.out.println("Client sagt Stay!!");
			break;






		}
	}

}
