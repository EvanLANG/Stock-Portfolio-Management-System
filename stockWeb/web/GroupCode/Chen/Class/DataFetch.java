package Chen.Class;

import java.net.*;
import java.io.*;
import java.util.*;
import Chen.Class.StockDailyRecord;
import net.sf.json.JSONObject;

//你可以直接新声明一个DataFetch对象，
// 然后调用DailyData（URL）这个方法便能得到所有你想要的数据
//全部都存在此对象相应的attribute里面

public class DataFetch {
    public ArrayList<StockDailyRecord> Data = new ArrayList<StockDailyRecord>();
    public String Symbol;
    public String TimeZone;
    public String Type;
    private String GetFromURL(String ul)
    {
        //this function aims to get the String from URL;
        String urlString = "";
        try
        {
            URL url = new URL(ul);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection connection;
            if(urlConnection instanceof HttpURLConnection)
            {
                connection = (HttpURLConnection) urlConnection;
            }
            else
            {
                System.out.println("please enter the URL.");
                return null;
            }
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String current;
            while((current = in.readLine()) != null)
            {
                urlString += current;
            }
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("Json string got.");
        return urlString;
    }
    public void IntraData(String ul,String interval) {
        //For daily data
        String json = GetFromURL(ul);
        Type = "Daily";
        JSONObject jsonObject = JSONObject.fromObject(json);
        String MetaData = jsonObject.getString("Meta Data");
        JSONObject MetaDataObj = JSONObject.fromObject(MetaData);
        Symbol = MetaDataObj.getString("2. Symbol");
        TimeZone = MetaDataObj.getString("6. Time Zone");
        String TimeSeries = jsonObject.getString("Time Series ("+interval+")");
        JSONObject TimeSeriesObj = JSONObject.fromObject(TimeSeries);
        //迭代器可选择
        TJsonInterator(TimeSeriesObj);
    }
    //取单条数据
    private void TJsonInterator(JSONObject obj){
        Iterator iterator = obj.keys();
        String key = (String) iterator.next();
        String value = obj.getString(key);
        JSONObject record = JSONObject.fromObject(value);
        StockDailyRecord recordObj = new StockDailyRecord();
        recordObj.readData(record);
        recordObj.GetDate(key);
        try{
            Data.add(recordObj);
        }catch(Exception e){
            e.printStackTrace(); }
    }
    //取全部数据
    private void JsonInterator(JSONObject obj){
        Iterator iterator = obj.keys();
        while(iterator.hasNext()){
            String key = (String) iterator.next();
            String value = obj.getString(key);
            JSONObject record = JSONObject.fromObject(value);
            StockDailyRecord recordObj = new StockDailyRecord();
            recordObj.readData(record);
            recordObj.GetDate(key);
            try{
                Data.add(recordObj);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public void DailyData(String ul) {
        //For daily data
        String json = GetFromURL(ul);
        Type = "Daily";
        JSONObject jsonObject = JSONObject.fromObject(json);
        String MetaData = jsonObject.getString("Meta Data");
        JSONObject MetaDataObj = JSONObject.fromObject(MetaData);
        Symbol = MetaDataObj.getString("2. Symbol");
        try {
            TimeZone = MetaDataObj.getString("5. Time Zone");
        }catch(Exception e){
        }
        String TimeSeries = jsonObject.getString("Time Series (Daily)");
        JSONObject TimeSeriesObj = JSONObject.fromObject(TimeSeries);
        JsonInterator(TimeSeriesObj);
    }
    public void WeeklyData(String ul){
        //For daily data
        String json = GetFromURL(ul);
        Type = "Weekly";
        JSONObject jsonObject = JSONObject.fromObject(json);
        String MetaData = jsonObject.getString("Meta Data");
        JSONObject MetaDataObj = JSONObject.fromObject(MetaData);
        Symbol = MetaDataObj.getString("2. Symbol");
        TimeZone = MetaDataObj.getString("4. Time Zone");
        String TimeSeries = jsonObject.getString("Weekly Time Series");
        JSONObject TimeSeriesObj = JSONObject.fromObject(TimeSeries);
        JsonInterator(TimeSeriesObj);
    }
    public void MonthlyData(String ul){
        //For daily data
        String json = GetFromURL(ul);
        Type = "Monthly";
        JSONObject jsonObject = JSONObject.fromObject(json);
        String MetaData = jsonObject.getString("Meta Data");
        JSONObject MetaDataObj = JSONObject.fromObject(MetaData);
        Symbol = MetaDataObj.getString("2. Symbol");
        TimeZone = MetaDataObj.getString("4. Time Zone");
        String TimeSeries = jsonObject.getString("Monthly Time Series");
        JSONObject TimeSeriesObj = JSONObject.fromObject(TimeSeries);
        JsonInterator(TimeSeriesObj);
    }
}
