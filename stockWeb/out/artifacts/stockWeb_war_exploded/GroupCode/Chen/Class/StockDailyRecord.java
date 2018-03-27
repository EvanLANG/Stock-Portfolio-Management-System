package Class;
import net.sf.json.JSONObject;

public class StockDailyRecord {
    private String TradeDate;
    private float open;
    private float high;
    private float low;
    private float close;
    private int volume;
    public void readData(JSONObject value){
        open = Float.parseFloat(value.getString("1. open"));
        high = Float.parseFloat(value.getString("2. high"));
        low = Float.parseFloat(value.getString("3. low"));
        close = Float.parseFloat(value.getString("4. close"));
        volume = Integer.parseInt(value.getString("5. volume"));
    }
    public void GetDate(String date){
        TradeDate = date;
    }
}
