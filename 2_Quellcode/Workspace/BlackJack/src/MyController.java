import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyController implements ActionListener {

	private DemoFrame window;                      //referenz um auf Window zugriff zu erhalten

	public void startGUI() {
		window = new DemoFrame(this);
		window.setVisible(true);
	}


	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();    //Aufruf welcher Button angeklickt wurde (Beschriftung des Buttons)

		if (command.equals("OK")) { 
			//window.showMessage("SEND");
			System.out.println("OK");
		}
		else if(command.equals("Close")) {
			//window.showMessage("CLOSE");
			System.out.println("Close");
		}

	}

}
