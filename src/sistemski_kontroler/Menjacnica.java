package sistemski_kontroler;

import java.io.IOException;
import java.util.ArrayList;

import domenske_klase.Drzava;
import sistemske_operacije.SistemskeOperacije;
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
		return SistemskeOperacije.vratiDrzave();
	}
	
	public ArrayList<Drzava> vratiDrzave() {
		return this.drzave;
	}


	public double vratiKurs(String from, String to) throws Exception {
		return SistemskeOperacije.vratiKurs(from, to);
	}

	public void sacuvajLog(String from, String to, double kurs) {
		SistemskeOperacije.sacuvajKonverziju(from, to, kurs);
	}

}
