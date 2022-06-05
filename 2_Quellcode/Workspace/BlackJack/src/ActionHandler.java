import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener{

	Client spiel;
	
	public ActionHandler(Client spiel) {
		
		this.spiel = spiel;
		
	}
	
	
	
	
public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		 
		
		
		
		
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
			spiel.auswahlZuRegistrier();
			break;
		case "Zurück":
			spiel.logRegZuAuswahl();
			break;	
		case ("Start"): 
			spiel.loginZuIP(); 
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
			spiel.jeton10(); 
			break; 
		case "Jeton25": 
			spiel.jeton25(); 
			break; 
		case "Jeton50": 
			spiel.jeton50(); 
			break; 
		case "Jeton100":
			spiel.jeton100();
			break; 
		case "Einsatz bestätigen": 
			spiel.einsatzAusrechnen(); 
			break;
			
			
		 
			
			
			
		}
}
	
}
