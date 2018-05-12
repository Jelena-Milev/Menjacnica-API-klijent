package gui.kontroler;

import java.awt.EventQueue;

import gui.prozori.GlavniProzor;
import sistemski_kontroler.Menjacnica;

public class GUIKontroler {

	private Menjacnica menjacnica=new Menjacnica();
	private GlavniProzor gp;
	
	public Menjacnica getMenjacnica() {
		return menjacnica;
	}


	public void setMenjacnica(Menjacnica menjacnica) {
		this.menjacnica = menjacnica;
	}


	public GlavniProzor getGp() {
		return gp;
	}


	public void setGp(GlavniProzor gp) {
		this.gp = gp;
	}


	public static void main(String[] args) {
		GUIKontroler guiK=new GUIKontroler();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavniProzor gp = new GlavniProzor(guiK);
					gp.setVisible(true);
					gp.setLocationRelativeTo(null);
					guiK.setGp(gp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
