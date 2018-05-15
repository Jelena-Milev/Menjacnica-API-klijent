package sistemske_operacije;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import domenske_klase.Drzava;
import domenske_klase.Konverzija;
import utility.UcitajSaURL;

public class SistemskeOperacije {
	
	public static final String serviceConvert = "/convert";
	public static final String serviceCountries = "/countries";
	public static final String CURRENCY_LAYER_API_URL = "http://free.currencyconverterapi.com/api/v5";
	
	public static void sacuvajKonverziju(String from, String to, double kurs) {
		Konverzija konverzija = new Konverzija();
		konverzija.setIzValute(from);
		konverzija.setuValutu(to);
		konverzija.setKurs(kurs);

		Date now = new Date();
		// 2017-05-15 08:54:44.000525
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss.SSSSSS");
		String date = format.format(now);

		konverzija.setDatumVreme(date);
		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

		JsonArray history=null;

//		objekat Konverzija konverzija -> Json sting
		String obj = gson.toJson(konverzija);
//		Json string -> JsonObject
		JsonObject jsonKon = gson.fromJson(obj, JsonObject.class);

		try (FileReader reader = new FileReader("data/log.json")) {
			history = gson.fromJson(reader, JsonArray.class);			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		try (FileWriter writer = new FileWriter("data/log.json")) {
			if(history==null)
				history=new JsonArray();
			
			history.add(jsonKon);
			writer.write(gson.toJson(history));			

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public static double vratiKurs(String from, String to) throws Exception {
		String url = CURRENCY_LAYER_API_URL + serviceConvert + '?' + "q=" + from + '_' + to;

		try {
			String content = UcitajSaURL.izvrsi(url);
			JsonParser parser = new JsonParser();
			JsonObject con = parser.parse(content).getAsJsonObject();

			JsonObject query = con.get("query").getAsJsonObject();
			if (query.get("count").getAsInt() != 0) {
				JsonObject results = con.get("results").getAsJsonObject();
				JsonObject kurs = results.get(from + '_' + to).getAsJsonObject();
				double k = kurs.get("val").getAsDouble();
				return k;
			} else
				throw new Exception("Ne postoje podaci o konverziji izmedju dve valute");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}
	
	public static ArrayList<Drzava> vratiDrzave() {

		String url = CURRENCY_LAYER_API_URL + serviceCountries;

		try {
			String content = UcitajSaURL.izvrsi(url);

			Gson gson = new GsonBuilder().create();

			JsonObject contentJson = gson.fromJson(content, JsonObject.class);

			JsonObject countriesJson = contentJson.get("results").getAsJsonObject();

			ArrayList<Drzava> list = new ArrayList<Drzava>();
			
			for (Entry<String, JsonElement> entry : countriesJson.entrySet()) {
				Drzava item = gson.fromJson(entry.getValue().getAsJsonObject(), Drzava.class);
				list.add(item);
			}
			return list;

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}
}
