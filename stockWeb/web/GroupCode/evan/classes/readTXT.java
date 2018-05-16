package evan.classes;


import Chen.Class.DataFetch;
import Chen.Class.StockDailyRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static evan.classes.DBTools.getConn;
//import evan.classes.DBTools;

public class readTXT {
    private static void insert(String s,String type,Connection conn,HashMap<String,ArrayList<StockDailyRecord>> map){
        int i = 0;
        String sql = "insert into " + s + type + " (timestamp,open,high,low,close,volume) values(?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
            for (int j = 0; j < map.get(s).size(); j++) {
                try {
                    StockDailyRecord comp = map.get(s).get(j);
                    if (type == "_monthly" && j == 0) {
                        continue;
                    }
                    //pstmt.setString(1, comp.getId());
                    pstmt.setString(1, comp.TradeDate);
                    pstmt.setFloat(2, comp.open);
                    pstmt.setFloat(3, comp.high);
                    pstmt.setFloat(4, comp.low);
                    pstmt.setFloat(5, comp.close);
                    pstmt.setString(6, String.valueOf(comp.volume));
                    i = pstmt.executeUpdate();
                    if (i == 0) {
                        break;
                    }
                } catch (SQLException e) { }
            }
            pstmt.close();
        }
        catch(SQLException e){
            ;
        }
    }
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


        HashMap<String,ArrayList<StockDailyRecord>> intra_map = new HashMap<String, ArrayList<StockDailyRecord>>();
        HashMap<String,ArrayList<StockDailyRecord>> daily_map = new HashMap<String, ArrayList<StockDailyRecord>>();
        HashMap<String,ArrayList<StockDailyRecord>> monthly_map = new HashMap<String, ArrayList<StockDailyRecord>>();


        int count = 0;
        for(String s : namelist){
            System.out.print(s+" ");
            /*if (!db.validateTableExist(s+"_intraday")) {
                db.createTable(s+"_intraday");
                db.createTable(s+"_daily");
                db.createTable(s+"_monthly");
            }*/
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
            daily_map.put(s,daily_data.Data);
            //db.insertStock(daily_data.Data,s.trim()+"_daily");

            counts=0;
            while(intraday_data.Dataerror&&counts<1){
                intraday_data.IntraData("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + s + "&interval=15min&apikey=BBWCXYKPHWWLCBZ4","15min");
                counts++;
            }
            intra_map.put(s,intraday_data.Data);
            //db.insertStock(intraday_data.Data,s.trim()+"_intraday");
            counts=0;
            while(monthly_data.Dataerror&&counts<1){
                monthly_data.MonthlyData("https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=" + s + "&apikey=BBWCXYKPHWWLCBZ4");
                counts++;
            }
            monthly_map.put(s,monthly_data.Data);
            //db.insertStock(monthly_data.Data,s.trim()+"_monthly");
        }
        Connection conn = getConn();
        try {
            for (int i = 0; i < namelist.size(); i++) {
                String s = namelist.get(i);
                System.out.println(s);

                if (intra_map.get(s)!=null&&intra_map.get(s).size() > 2) {
                    insert(s,"_intraday",conn,intra_map);
                }
                if (intra_map.get(s)!=null&&daily_map.get(s).size() > 2) {
                    insert(s,"_daily",conn,daily_map);
                }
                if (intra_map.get(s)!=null&&monthly_map.get(s).size() > 2) {
                    insert(s,"_monthly",conn,monthly_map);
                }
            }
            conn.close();
        }catch (SQLException e) {
            try{
                conn.close();
            }catch(SQLException E){; }
        }
        //IMNP
        //System.out.print(db.getDaily("pih_daily","2018-04-17"));
    }
}