package Chen.Class;
import net.sf.json.JSONObject;

import java.math.BigInteger;
import java.sql.Timestamp;

public class StockDailyRecord {
    public String TradeDate;
    public float open;
    public float high;
    public float low;
    public float close;
    public long volume;
    public void readData(JSONObject value){
        open = Float.parseFloat(value.getString("1. open"));
        high = Float.parseFloat(value.getString("2. high"));
        low = Float.parseFloat(value.getString("3. low"));
        close = Float.parseFloat(value.getString("4. close"));
        volume = Long.parseLong(value.getString("5. volume"));
    }
    public void GetDate(String date){
        //Timestamp ts = new Timestamp(System.currentTimeMillis());
        String tsStr = date;
        TradeDate = tsStr;
    }
}
