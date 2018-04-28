package sistemske_operacije;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SOVratiKurs {

	public static final String service = "/convert";
	public static final String CURRENCY_LAYER_API_URL = "http://free.currencyconverterapi.com/api/v3";

	public static double izvrsi(String from, String to) {
		String url = CURRENCY_LAYER_API_URL + service + '?' + "q=" + from + '_' + to;

		try {
			String content = SOUcitajSaURL.izvrsi(url);
			JsonParser parser = new JsonParser();
			JsonObject con = parser.parse(content).getAsJsonObject();

			JsonObject query = con.get("query").getAsJsonObject();
			if (query.get("count").getAsInt() != 0) {
				JsonObject results = con.get("results").getAsJsonObject();
				JsonObject kurs = results.get(from + '_' + to).getAsJsonObject();
				double k = kurs.get("val").getAsDouble();
				return k;
			} else
				throw new RuntimeException("Ne postoje podaci o konverziji izmedju dve valute");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}
}
