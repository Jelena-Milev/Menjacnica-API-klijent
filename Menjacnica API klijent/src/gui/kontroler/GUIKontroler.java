package gui.kontroler;

import java.awt.EventQueue;

import gui.prozori.GlavniProzor;
import sistemski_kontroler.Menjacnica;

public class GUIKontroler {

	public static Menjacnica menjacnica=new Menjacnica();
	public static GlavniProzor gp;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gp = new GlavniProzor();
					gp.setVisible(true);
					gp.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
