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
		
		case "Login": 
			spiel.auswahlZuLogin();
			break;
		case "Registrieren":
			spiel.auswahlZuRegistrier();
			break;
		case "Zurück":
			spiel.startbildschirm();
			break;	
			
			
		}
}
	
}
