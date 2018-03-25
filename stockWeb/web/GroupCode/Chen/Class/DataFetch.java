package Class;

import java.net.*;
import java.io.*;
import java.util.*;

import net.sf.json.JSONObject;

//你可以直接新声明一个DataFetch对象，
// 然后调用DailyData（URL）这个方法便能得到所有你想要的数据
//全部都存在此对象相应的attribute里面

public class DataFetch {
    private ArrayList<Class.StockDailyRecord> Data;
    private String Symbol;
    private String TimeZone;
    private String Type;
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
    public void DailyData(String ul){
        //For daily data
        String json = GetFromURL(ul);
        Type = "Daily";
        JSONObject jsonObject = JSONObject.fromObject(json);
        String MetaData = jsonObject.getString("Meta Data");
        JSONObject MataDataObj = JSONObject.fromObject(MetaData);
        Symbol = MataDataObj.getString("2. Symbol");
        TimeZone = MataDataObj.getString("5. Time Zone");
        String TimeSeries = jsonObject.getString("Time Series (Daily)");
        JSONObject TimeSeriesObj = JSONObject.fromObject(TimeSeries);
        Iterator iterator = TimeSeriesObj.keys();
        while(iterator.hasNext()){
            String key = (String) iterator.next();
            String value = jsonObject.getString(key);
            JSONObject record = JSONObject.fromObject(value);
            Class.StockDailyRecord recordObj = new Class.StockDailyRecord();
            recordObj.readData(record);
            recordObj.GetDate(key);
            Data.add(recordObj);
        }
    }
}
