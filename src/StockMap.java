import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Zachary Laborde on 11/2/16.
 */
public class StockMap {
	private Map<String, Double> map;

	public StockMap() {
		map = new LinkedHashMap<>();
	}

	public Double put(Stock stock) {
		return map.put(stock.getSymbol(), stock.getSharePrice());
	}

	public Double get(String name) {
		return map.get(name);
	}

	public Map<String, Double> toMap() {
		return map;
	}
}
