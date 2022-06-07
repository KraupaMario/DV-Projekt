package test;

import javax.swing.JOptionPane;

public class Threeed implements Runnable {
	static boolean count=false;
	
	Threeed(){
		thread = new Thread(this, "BlackJack");
		thread.start();
	}
	
	void wartenBisKlick(){
		while (!ActionDings.klick) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	}
	
	private Thread thread;

	private void aktion()  {
		System.out.println("Thread gestartet...");
		wartenBisKlick();
		System.out.println("1.Warteschleife beendet!");
		ActionDings.klick = false;
		count=true;
		wartenBisKlick();
		System.out.println("2.Warteschleife beendet!");
		thread.stop();
	}


	public void run() {
		
				aktion();	
		}
	

	public static void main(String[] args) {
		Threeed neu = new Threeed();
		
		if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
		        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
		    // yes option
			ActionDings.klick = true;
		} else {
		    // no option
			ActionDings.klick = true;
			
		}
		if (count=true) {
			if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
			        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			    // yes option
				ActionDings.klick = true;
			} else {
			    // no option
				ActionDings.klick = true;
				
			}
		}
		// TODO Auto-generated method stub

	}

}

class ActionDings{
	static boolean klick = false;
}
