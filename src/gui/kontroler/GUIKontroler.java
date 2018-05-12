package gui.kontroler;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JComboBox;

import domenske_klase.Drzava;
import gui.prozori.GlavniProzor;
import sistemski_kontroler.Menjacnica;

public class GUIKontroler {

	private Menjacnica menjacnica;//=new Menjacnica();
	private GlavniProzor gp;
	
	
	public GUIKontroler(Menjacnica menjacnica) {
		this.menjacnica = menjacnica;
	}


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
		GUIKontroler guiK=new GUIKontroler(new Menjacnica());
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
	
	
	public void popuniComboBox(JComboBox<Drzava> comboBox) {
		ArrayList<Drzava> d=menjacnica.vratiDrzave();
		for (int i = 0; i < d.size(); i++) {
			comboBox.addItem(d.get(i));
		}		
	}
	
}
