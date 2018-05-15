package sistemski_kontroler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import domenske_klase.Drzava;
import sistemske_operacije.SistemskeOperacije;
import utility.UcitajSaURL;

public class Menjacnica {
	private ArrayList<Drzava> drzave;
	
	public Menjacnica() {
		drzave = SistemskeOperacije.vratiDrzave();
		Collections.sort(drzave);
	}

	public String ucitajSaURL(String url) throws IOException {
		return UcitajSaURL.izvrsi(url);

	}

	public ArrayList<Drzava> getDrzave() {
		return drzave;
	}
//	Ne sacuva konverzije o kojima nema podataka, da li je to u redu?
//	Jer se exception baci na 30. liniji pre nego se izvrsi sacuvajKonverziju
	public double vratiKurs(String from, String to) throws Exception {
		double kurs = SistemskeOperacije.vratiKurs(from, to);
		SistemskeOperacije.sacuvajKonverziju(from, to, kurs);
		return kurs;
	}

	public void sacuvajLog(String from, String to, double kurs) {
		SistemskeOperacije.sacuvajKonverziju(from, to, kurs);
	}
	
//	Da li ovo da bude u klasi SistemskeOperacije ili ovde?
	public double konvertuj(Drzava from, Drzava to, String iznos) throws Exception {
		Double iznosFrom=Double.parseDouble(iznos);
		double kurs = vratiKurs(from.getCurrencyId(), to.getCurrencyId());		
		return iznosFrom*kurs;		
	}
}
