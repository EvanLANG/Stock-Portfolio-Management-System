package evan.classes;


import Chen.Class.DataFetch;
import Chen.Class.StockDailyRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//import evan.classes.DBTools;

public class readTXT {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("web\\resource\\companylist.txt"));
        ArrayList<String> array = new ArrayList<String>();
        ArrayList<String> namelist = new ArrayList<String>();

        String line = null;
        while ((line = br.readLine()) != null) {
            array.add(line);
        }

        br.close();
        DBTools db = new DBTools();
        //注释掉的这一段是用来添加所有公司数据的，等你睡觉前来跑一下这个readTXT就行


       for (String s : array) {
            String[] parts = s.split("#");
            namelist.add(parts[0]);
            //db.insertsymbols(parts[0], parts[1], parts[2],parts[3]);
        }
       // namelist.add("MSFT");
        //namelist.add("JOBS");
        //namelist.add("TURN");



        for(String s : namelist){
            System.out.println(s);
            if (!db.validateTableExist(s+"_intraday")) {
                db.createTable(s+"_intraday");
                db.createTable(s+"_daily");
                db.createTable(s+"_monthly");
            }
            DataFetch daily_data = new DataFetch();
            DataFetch monthly_data = new DataFetch();
            DataFetch intraday_data = new DataFetch();
            daily_data.Dataerror = true;
            intraday_data.Dataerror = true;
            monthly_data.Dataerror = true;
            int counts=0;
            while(daily_data.Dataerror&&counts<1){
                daily_data.DailyData("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + s + "&apikey=BBWCXYKPHWWLCBZ4");
                counts++;
            }
            db.insertStock(daily_data.Data,s.trim()+"_daily");

            counts=0;
            while(intraday_data.Dataerror&&counts<1){
                intraday_data.IntraData("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + s + "&interval=15min&apikey=BBWCXYKPHWWLCBZ4","15min");
                counts++;
            }
            db.insertStock(intraday_data.Data,s.trim()+"_intraday");
            counts=0;
            while(monthly_data.Dataerror&&counts<1){
                monthly_data.MonthlyData("https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=" + s + "&apikey=BBWCXYKPHWWLCBZ4");
                counts++;
            }
            db.insertStock(monthly_data.Data,s.trim()+"_monthly");
        }
        //IMNP
        //System.out.print(db.getDaily("pih_daily","2018-04-17"));
    }
}