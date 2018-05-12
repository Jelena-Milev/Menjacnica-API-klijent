package sistemske_operacije;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import domenske_klase.Drzava;
import utility.UcitajSaURL;

public class SOVratiDrzave {

	public static final String service = "/countries";
	public static final String CURRENCY_LAYER_API_URL = "http://free.currencyconverterapi.com/api/v5";

	public static ArrayList<Drzava> izvrsi() {

		String url = CURRENCY_LAYER_API_URL + service;

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
//			LinkedList<String> drzave=new LinkedList<String>();
//			for (int i = 0; i < list.size(); i++) {
//				drzave.add(list.get(i).getName());
//			}
			return list;

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

}
