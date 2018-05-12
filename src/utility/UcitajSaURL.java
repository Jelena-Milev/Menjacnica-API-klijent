package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UcitajSaURL {

	public static String izvrsi(String url) throws IOException {

		URL obj = new URL(url);

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		String content = "";
		boolean proceed = true;

		while(proceed) {
			String line=in.readLine();
			if(line!=null)
				content+=line;
			else proceed=false;		
		}
		in.close();
		return content;
	}
}
