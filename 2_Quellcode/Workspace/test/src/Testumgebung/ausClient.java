import java.io.IOException;

/*
		/**Empfängt den Status, ob direkt nach dem austeilen ein Spieler verloren oder gewonnen hat*/
		/**Status für Spieler 1:*/
		try {
			client.winSpieler1 = dis.readBoolean();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			client.loseSpieler1 = dis.readBoolean();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/**Status für Spieler 2:*/
		try {
			client.winSpieler2 = dis.readBoolean();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			client.loseSpieler2 = dis.readBoolean();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/**Status für Dealer*/
		try {
			client.winDealer = dis.readBoolean();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			client.loseDealer = dis.readBoolean();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/**Spieler Status Spieler 1*/
		if(client.winSpieler1 = true) {
			ausgabetextS1C = "Sieger"; 
			System.out.println("Sieger Spieler 1");
			//Stop
		}


		if (client.loseSpieler1=true) {
			ausgabetextS1C = "Verlierer"; 
			System.out.println("Verlierer Spieler 1");
			//Stop
		}


		/**Spieler Status Spieler 2*/
		if(client.winSpieler2 = true) {
			ausgabetextS2C = "Sieger";
			System.out.println("Sieger Spieler 2");
			//Stop
		}


		if (client.loseSpieler2=true) {
			ausgabetextS2C = "Verlierer";
			System.out.println("Verlierer Spieler 2");
			//Stop
		}


		/**Spieler Status Dealer*/
		if(client.winDealer = true) {
			System.out.println("Sieger Spieler Dealer");
			//Stop
		}


		if (client.loseDealer=true) {
			System.out.println("Verlierer Spieler Dealer");
			//Stop
		}




		System.out.println(client.DeckSpieler1.get(0).getFarbe());
		System.out.println(client.DeckSpieler1.get(0).getName());
		System.out.println(client.DeckSpieler1.get(1).getFarbe());
		System.out.println(client.DeckSpieler1.get(1).getName());
		kartenausgebenS_R1(client);//Karten anzeigen
		kartenwertanzeigen(client);

		rundeZuAuswerten();

		thread.stop();
	}*/