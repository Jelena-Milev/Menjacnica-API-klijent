package sistemski_kontroler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import domenske_klase.Drzava;
import sistemske_operacije.SOSacuvajKonverziju;
import sistemske_operacije.SOVratiDrzave;
import sistemske_operacije.SOVratiKurs;
import utility.UcitajSaURL;

public class Menjacnica {
	private ArrayList<Drzava> drzave;
	
	public Menjacnica() {
		this.drzave = this.vratiDrzaveSaURL();
	}	
	
	public String ucitajSaURL(String url) throws IOException {
		return UcitajSaURL.izvrsi(url);

	}

	public ArrayList<Drzava> vratiDrzaveSaURL() {
		return SOVratiDrzave.izvrsi();
	}
	
	public ArrayList<Drzava> vratiDrzave() {
		return this.drzave;
	}


	public double vratiKurs(String from, String to) throws Exception {
		return SOVratiKurs.izvrsi(from, to);
	}

	public void sacuvajLog(String from, String to, double kurs) {
		SOSacuvajKonverziju.izvrsi(from, to, kurs);
	}

}
