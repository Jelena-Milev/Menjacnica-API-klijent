package sistemske_operacije;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import domenske_klase.Konverzija;

public class SOSacuvajKonverziju {

	public static void izvrsi(String from, String to, double kurs) {
		Konverzija konverzija = new Konverzija();
		konverzija.setIzValute(from);
		konverzija.setuValutu(to);
		konverzija.setKurs(kurs);

		Date now = new Date();
		// 2017-05-15 08:54:44.000525
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss.SSSSSS");
		String date = format.format(now);

		konverzija.setDatumVreme(date);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		JsonArray history=null;

//		objekat konverzija -> Json sting
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

}
