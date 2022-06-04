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
		case "Zur�ck":
			spiel.logRegZuAuswahl();
			break;	
		case "Start": 
			spiel.loginZuIP(); 
			break; 
		case "Abschlie�en": 
			spiel.loginZuIP(); 
		case "IP Adresse best�tigen": 
			spiel.ipZuPort(); 
			
			
			
		}
}
	
}
