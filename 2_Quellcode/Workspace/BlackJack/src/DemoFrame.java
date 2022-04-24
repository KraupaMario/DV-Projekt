import javax.swing.*;
import java.awt.*;

public class DemoFrame extends JFrame {
	
	public DemoFrame() {
		setTitle("Layout demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		//Label 
		JLabel titleLabel = new JLabel ("Passwort-Fenster");                //Erzeugen eines Textfeldes "Label"
		titleLabel.setHorizontalAlignment(JLabel.CENTER);                   //Positionieren des Textes in der Mitte des Labels
		titleLabel.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));   //Abstand um den Text erzeugen Oben 20 Pixel, links 0 Pixel, unten 20 Pixel, rechts 0 Pixel
		
		//Main Panel 
		JPanel mainPanel = new JPanel();                                    //Erzeugen des Panels im Zentrum vom Layout 
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		
		mainPanel.setLayout(new GridLayout(2,1));                           //main Panel als GridLayout (Gitter) erzeugen mit 2 Zeilen und 1 Spalte  
		mainPanel.add(p1);                                                  //JPannel 1 dem mainPanel hinzuf�gen
		mainPanel.add(p2);                                                  //JPannel 2 dem mainPanel hinzuf�gen
		
		JLabel l1 = new JLabel ("Login:");                                  //Erstellen eines Labels mit dem Text "Login:"
		JTextField tf1 = new JTextField(12);                                //Erzeugen eines Textfeldes mit einer L�nge von 12 Zeichen
		p1.add(l1);                                                         //Hinzuf�gen des Labels zum JPanel p1
		p1.add(tf1);                                                        //Hinzuf�gen des Textfeldes zum JPanel p1
		
		JLabel l2 = new JLabel ("Passwort");                                //Erstellen eines Labels mit dem Text "Passwort"
		JTextField tf2 = new JTextField(12);                                //Erzeugen eines Textfeldes mit einer L�nge von 12 Zeichen
		p2.add(l2);                                                         //Hinzuf�gen des Labels zum JPanel p2
		p2.add(tf2);                                                        //Hinzuf�gen des Textfeldes zum JPanel p2
		
		
		//Button Panel
		JPanel buttonPanel = new JPanel();                                  //Erzeugen der Panels unten mit den Buttons vom Layout
		
		JButton okButton = new JButton("OK");                               //Erstellen eines Button mit dem Text "OK"
		JButton closeButton= new JButton ("Close");                         //Erstellen eines Button mit dem Text "Close"
		
		buttonPanel.add(okButton);                                          //Hinzuf�gen des "OK" Buttons zum Panel "buttonPanel"
		buttonPanel.add(closeButton);                                       //Hinzuf�gen des "Close" Buttons zum Panel "buttonPanel"
		
		
		
	
		//Top-Level Layout (Layout des Frames)
		add(titleLabel, BorderLayout.NORTH);                                //hinzuf�gen des Titel Labels im Oberen (North) teil des Borderlayouts
		add(mainPanel, BorderLayout.CENTER);                                //hinzuf�gen des "mainPanels" in der Mitte (Center) des Borderlayouts
		add(buttonPanel, BorderLayout.SOUTH);                               //hinzuf�gen des buttonPanels im unteren (South) teil des Borderlayouts
		setSize(300,200);                                                   //Den Frame auf eine bestimmte gr��e Festlegen (L�nge, H�he)
		//pack();                                                             //anpassen des Fensters auf die passende gr��e 
	}

}
