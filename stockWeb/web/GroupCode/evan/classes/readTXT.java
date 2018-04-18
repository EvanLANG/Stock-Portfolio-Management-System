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
        //System.out.println(array.get(0));
        DBTools db = new DBTools();
        for (String s : array) {
            String[] parts = s.split("#");
            namelist.add(parts[0]);
            //db.insertsymbols(parts[0], parts[1], parts[2],parts[3]);
            //System.out.println(parts[0]);

            //System.out.println(parts[1]);
            //System.out.println(parts[2]);
            //System.out.println(parts[3]);
        }
        for(String s : namelist){
            //System.out.println(s);
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
            while(daily_data.Dataerror){
                daily_data.DailyData("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + s + "&apikey=BBWCXYKPHWWLCBZ4");
            }
            while(intraday_data.Dataerror){
                intraday_data.IntraData("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + s + "&interval=15min&apikey=BBWCXYKPHWWLCBZ4","15min");
            }
            System.out.print(intraday_data.Data.get(0).TradeDate);
            while(monthly_data.Dataerror){
                monthly_data.MonthlyData("https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=" + s + "&apikey=BBWCXYKPHWWLCBZ4");
            }
            for (StockDailyRecord record: intraday_data.Data){
                if(db.insertStock(record,s+"_intraday")==0){
                    break;
                }
            }
            for (StockDailyRecord record: daily_data.Data){
                if(db.insertStock(record,s+"_daily")==0){
                    break;
                }
            }
            for (StockDailyRecord record: monthly_data.Data){
                if(db.insertStock(record,s+"_monthly")==0){
                    break;
                }
            }
            break;//去掉这个break就是存储所有SYMBOL的数据，现在只存一条PIH的
        }
        //System.out.print(db.getDaily("pih_daily","2018-04-17"));
    }
}