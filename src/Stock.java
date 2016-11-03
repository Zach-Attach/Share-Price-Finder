import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Zachary Laborde on 11/2/16.
 */
public class Stock {

	private String symbol;
	private double sharePrice;

	public Stock(String sym) {
		try {
			sharePrice = findPrice(sym);
			symbol = sym;
		} catch (IOException i) {
		}
	}

	public double getSharePrice() {
		return sharePrice;
	}

	public String getSymbol() {
		return symbol;
	}

	public String toString() {
		return String.format("Trading symbol: %s \t\tShare price: $%.2f", symbol, sharePrice);
	}

	public double findPrice(String name) throws IOException {
		URL url = new URL(String.format("https://www.google.com/finance?q=NYSE:%s", name));

		HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(urlConnect.getInputStream()));

		String line = null;

		while ((line = in.readLine()) != null) {

			if (line.contains("<span id=\"ref_") && line.contains("_l\">")) {

				line = line.substring(line.indexOf(">") + 1);
				line = line.substring(0, line.indexOf("<"));
				return Double.parseDouble(line);
				//break;
			}
		}

		System.out.println("Error: stock not listed in the NYSE");
		return 0;
	}
}
