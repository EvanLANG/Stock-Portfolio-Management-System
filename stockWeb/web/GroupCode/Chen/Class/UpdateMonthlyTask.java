package Chen.Class;

import evan.classes.DBTools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimerTask;

public class UpdateMonthlyTask extends TimerTask {

    @Override
    public void run() {
        Calendar calendar_m = Calendar.getInstance();
        int day = calendar_m.get(Calendar.DAY_OF_MONTH);
        if(day == 1){
            timerTask();
        }
    }

    private void timerTask() {
        System.out.print("定时任务执行");
        ArrayList<String> array = new ArrayList<String>();
        ArrayList<String> namelist = new ArrayList<String>();
        String line = null;
        String path = this.getClass().getClassLoader().getResource("/").toString().substring(6)+"Chen/Class/";
        System.out.print(path);
        try {
            BufferedReader br = new BufferedReader(new FileReader(path+"companylist.txt"));
            while ((line = br.readLine()) != null) {
                array.add(line);
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        //System.out.println(array.get(0));
        DBTools db = new DBTools();
        for (String s : array) {
            String[] parts = s.split("#");
            namelist.add(parts[0].trim());
            //db.insertsymbols(parts[0], parts[1], parts[2],parts[3]);
            //System.out.println(parts[0]);

            //System.out.println(parts[1]);
            //System.out.println(parts[2]);
            //System.out.println(parts[3]);
        }
        for(String s : namelist){
            //System.out.println(s);
            if (!db.validateTableExist(s+"_monthly")) {
                db.createTable(s+"_intraday");
                db.createTable(s+"_daily");
                db.createTable(s+"_monthly");
            }
            DataFetch monthly_data = new DataFetch();
            //DataFetch monthly_data = new DataFetch();
            //DataFetch intraday_data = new DataFetch();
            monthly_data.Dataerror = true;
            //intraday_data.Dataerror = true;
            //monthly_data.Dataerror = true;
            int counts=0;
            while(monthly_data.Dataerror&&counts<3){
                monthly_data.MonthlyData("https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=" + s.trim() + "&apikey=BBWCXYKPHWWLCBZ4");
                if(monthly_data.Data.size()>0){
                    break;
                }
                try {
                    Thread.sleep(2000);
                }catch(InterruptedException e){
                    ;
                }
                counts++;
            }
            /*
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
            */
            if(monthly_data.Data.size()>1) {
                monthly_data.Data.remove(0);
                db.insertStock(monthly_data.Data, s.trim() + "_monthly");
            }
            /*
            for (StockDailyRecord record: monthly_data.Data){
                if(db.insertStock(record,s+"_monthly")==0){
                    break;
                }
            }
            break;//去掉这个break就是存储所有SYMBOL的数据，现在只存一条PIH的*/
        }
        //System.out.print(db.getDaily("pih_daily","2018-04-17"));
    }
}
