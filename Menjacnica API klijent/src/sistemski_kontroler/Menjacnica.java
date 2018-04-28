package sistemski_kontroler;

import java.io.IOException;
import java.util.LinkedList;

import domenske_klase.Drzava;
import sistemske_operacije.SOSacuvajKonverziju;
import sistemske_operacije.SOUcitajSaURL;
import sistemske_operacije.SOVratiDrzave;
import sistemske_operacije.SOVratiKurs;

public class Menjacnica {

	public String ucitajSaURL(String url) throws IOException {
		return SOUcitajSaURL.izvrsi(url);

	}

	public LinkedList<Drzava> vratiDrzave() {
		return SOVratiDrzave.izvrsi();
	}

	public double vratiKurs(String from, String to) {
		return SOVratiKurs.izvrsi(from, to);
	}

	public void sacuvajLog(String from, String to, double kurs) {
		SOSacuvajKonverziju.izvrsi(from, to, kurs);
	}

}
