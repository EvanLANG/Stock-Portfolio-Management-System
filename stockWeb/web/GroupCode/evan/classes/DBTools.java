package evan.classes;

import java.math.BigDecimal;
import java.sql.*;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
//import Chen.Class.StockDailyRecord;
//import java.sql.DatabaseMetaData;
import Chen.Class.DataFetch;
import Chen.Class.RankObject;
import Chen.Class.StockDailyRecord;
import Chen.Class.User;
import Chen.Comparator.RiseNFallComparator;
import Chen.Comparator.ValueComparator;
import huang.servlets.Company;

import static java.lang.Float.isInfinite;
//import org.postgresql.util.PSQLException;

public class DBTools {
    public static Connection getConn() {
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/9900stockportfolio?useSSL=true";
        String username = "postgres";
        String password = "921616";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static int insertStock(ArrayList<StockDailyRecord> list, String symbol) {
        Connection conn = getConn();
        String id = null;
        id = symbol;
        int i = 0;
        String sql = "insert into " + id + "(timestamp,open,high,low,close,volume) values(?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            for(StockDailyRecord comp: list){
                //pstmt.setString(1, comp.getId());
                pstmt.setString(1, comp.TradeDate);
                pstmt.setFloat(2, comp.open);
                pstmt.setFloat(3, comp.high);
                pstmt.setFloat(4, comp.low);
                pstmt.setFloat(5, comp.close);
                pstmt.setString(6, String.valueOf(comp.volume));
                i = pstmt.executeUpdate();
                if(i == 0){
                    break;
                }
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            try{
                conn.close();
            }catch(SQLException E){; }
        }
        return i;
    }

    public static int insertsymbols(String symbol, String sname, String ipoyear,String sector,String industry, String quote) {
        Connection conn = getConn();
        //String id = null;
        int i = 0;
        String sql = "insert into symbols" + "(symbol,sname,ipoyear,quote,sector,industry) values(?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            //pstmt.setString(1, comp.getId());
            pstmt.setString(1, symbol);
            pstmt.setString(2, sname);
            pstmt.setString(3, ipoyear);
            pstmt.setString(4, quote);
            pstmt.setString(5, sector);
            pstmt.setString(6, industry);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                conn.close();
            }catch(SQLException E){; }
        }
        return i;
    }

    public boolean validateTableExist(String tableName) {
        boolean flag = false;
        int i = 0;
        Connection conn = getConn();
        String sql = "SELECT COUNT(*) FROM " + tableName;
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            pstmt.close();
            conn.close();
            flag = true;
        } catch (SQLException e) {
            try{
                conn.close();
            }catch(SQLException E){; }
        }
        return flag;
    }

    public static int createTable(String tablename) {
        Connection conn = getConn();
        int i = 0;
        String id = null;
        id = tablename;
        String sql = "CREATE TABLE " + id + "\n" +
                "(\n" +
                "timestamp VARCHAR(20) not null PRIMARY KEY ,\n" +
                "open      FLOAT ,\n" +
                "high      FLOAT,\n" +
                "low       FLOAT, \n" +
                "close     FLOAT, \n" +
                "volume    VARCHAR(50) \n" +
                ")";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            try{
                conn.close();
            }catch(SQLException E){; }
        }
        return i;
    }
    public static int createUserHistory(String username) {
        Connection conn = getConn();
        int i = 0;
        String id = null;
        id = username+"_history";
        String sql = "CREATE TABLE " + id + "\n" +
                "(\n" +
                "timestamp VARCHAR(20) not null PRIMARY KEY ,\n" +
                "keyword    VARCHAR(300) \n" +
                ")";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            try{
                conn.close();
            }catch(SQLException E){; }
        }
        return i;
    }
    public static int inserthistory(String username, String keyword, String date) {
        Connection conn = getConn();
        //String id = null;
        int i = 0;
        String sql = "insert into "+username+"_history" + "(timestamp,keyword) values(?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            //pstmt.setString(1, comp.getId());
            pstmt.setString(1, date);
            pstmt.setString(2, keyword);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                conn.close();
            }catch(SQLException E){; }
        }
        return i;
    }
    public static ArrayList<StockDailyRecord> getDaily(String symbol) {
        Connection conn = getConn();
        ArrayList<StockDailyRecord> result = new ArrayList<StockDailyRecord>();
        String sql = "select * from "+symbol+"_daily order by timestamp DESC";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result.add(readData(rs));
            }
            conn.close();
        } catch (SQLException e) {
            try{
                conn.close();
            }catch(SQLException E){; }
        }
        return result;
    }

    public static ArrayList<StockDailyRecord> getMonthly(String symbol) {
        Connection conn = getConn();
        ArrayList<StockDailyRecord> result = new ArrayList<StockDailyRecord>();
        String sql = "select * from "+symbol+"_monthly order by timestamp DESC";
        Calendar now = Calendar.getInstance();
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int year = now.get(Calendar.YEAR);
            int new_year = 0;
            while (rs.next()) {
                new_year = Integer.parseInt(rs.getString("timestamp").substring(0,4));
                if(year-new_year>1){
                    break;
                }
                result.add(readData(rs));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                conn.close();
            }catch(SQLException E){; }
        }
        return result;
    }
    public static ArrayList<StockDailyRecord> getIntraday(String symbol) {
        Connection conn = getConn();
        ArrayList<StockDailyRecord> result = new ArrayList<StockDailyRecord>();
        String sql = "select * from "+symbol+"_intraday order by timestamp DESC";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result.add(readData(rs));
            }
            conn.close();
        } catch (SQLException e) {
            try{
                conn.close();
            }catch(SQLException E){; }
        }
        return result;
    }
    public static StockDailyRecord readData(ResultSet rs){
        StockDailyRecord record = new StockDailyRecord();
        try {
            record.open = rs.getFloat("open");
            record.close = rs.getFloat("close");
            record.high = rs.getFloat("high");
            record.low = rs.getFloat("low");
            record.volume = rs.getLong("volume");
            record.TradeDate = rs.getString("timestamp");
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return record;
    }
    public static Company getIntraVolumeLowHigh(DataFetch data){
        ArrayList<StockDailyRecord> Data = data.Data;
        Company com = new Company();
        StockDailyRecord first_day = Data.get(0);
        String day = first_day.TradeDate.substring(0,10);
        int index = 0;
        try {
            while (true) {
                StockDailyRecord current = Data.get(index);
                if (current.TradeDate.substring(0, 10).equals(day)) {
                    data.TotalV += current.volume;
                    if (current.high > data.current_high) {
                        data.current_high = current.high;
                    }
                    if (current.low < data.current_low) {
                        data.current_low = current.low;
                    }
                    index++;
                    //System.out.println("index:"+index+",");
                } else {
                    data.newest_open = Data.get(index - 1).open;
                    break;
                }
            }
        }catch(Exception e){
            return null;
        }
        com.setSymbol(data.Symbol);
        //从今日开盘到目前为止累积的交易总量
        com.setVolume(data.TotalV);
        //今日开盘价
        com.setOpen(data.newest_open);
        //今日开盘到目前为止的最高价和最低价
        com.setHigh(data.current_high);
        com.setLow(data.current_low);
        return com;
    }

    public static int insertusers(String email, String uid, String uname, String upasswd) {
        Connection conn = getConn();
        //String id = null;
        int i = 0;
        String sql = "insert into users" + "(email,uid,uname,upasswd) values(?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, uid);
            pstmt.setString(3, uname);
            pstmt.setString(4, upasswd);
            //pstmt.setString(5, risklevel);
            //pstmt.setString(6, favo);
            //pstmt.setString(7, birthdate);
            //pstmt.setString(8, gender);

            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                conn.close();
            }catch(SQLException E){; }

        }
        return i;
    }
    public static int updatepasswd(String email,String passwd) {
        Connection conn = getConn();
        int i = 0;
        String sql = "update users set upasswd = '"+passwd+"' where email='"+email+"'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                conn.close();
            }catch(SQLException E){; }
        }
        return i;
    }
    public static String getfavorite(String email){
        String favos= null;
        Connection conn = getConn();
        String sql = "select follow from users where email = '" + email+"'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                favos= rs.getString("follow");
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                conn.close();
            }catch(SQLException E){; }
        }
        return favos;
    }
    public static int updatefavo(User user,String sym) {
        String favos = "";
        if(user.getFollowString().length() == 0){
            favos = user.getFollowString()+sym;
        }else{
            favos = user.getFollowString()+"#"+sym;
        }
        user.setFollow(favos);
        Connection conn = getConn();
        int i = 0;
        String sql1 = "update users set follow = '"+favos+"' where email='"+user.getEmail()+"'";
        String sql2 = "update symbols set follows = follows+1 where symbol='"+sym+"'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql1);
            i = pstmt.executeUpdate();
            pstmt = (PreparedStatement) conn.prepareStatement(sql2);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                conn.close();
            }catch(SQLException E){; }
        }
        return i;
    }
    public static int cancelfavo(User user,String sym) {
        String favos = "";
        if(user.getFollowString().contains("#")) {
            favos = user.getFollowString().replace("#" + sym, "");
            favos = favos.replace(sym + "#", "");
            user.setFollow(favos);
        }else{
            favos = user.getFollowString().replace(sym, "");
            user.setFollow(favos);
        }
        Connection conn = getConn();
        int i = 0;
        String sql1 = "update users set follow = '"+favos+"' where email='"+user.getEmail()+"'";
        String sql2 = "update symbols set follows = follows-1 where symbol='"+sym+"'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql1);
            i = pstmt.executeUpdate();
            pstmt = (PreparedStatement) conn.prepareStatement(sql2);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                conn.close();
            }catch(SQLException E){; }
        }
        return i;
    }
    public static Boolean AccountExist(String username,String password){
        Connection conn = getConn();
        String sql = "select * from users where uname = '"+ username+"' and upasswd = '"+ password+"'";
        PreparedStatement pstmt;
        boolean result;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                result = true;
            }else{
                result = false;
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
            try{
                conn.close();
            }catch(SQLException E){; }
        }
        return result;
    }
    public static void getUser(User user) {
        Connection conn = getConn();
        String theUsername;
        if(user.getUsername()==null){
            theUsername = "";
        } else{
            theUsername =user.getUsername();
        }
        String sql = "select * from users where uname = '" + theUsername+"'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            //DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user.setId(rs.getString("uname"));
                //user.setGender(rs.getString("gender"));
                //Timestamp date = rs.getTimestamp("dateofbirth");
                //String dateStr = sdf.format(date);
                //user.setDateofbirth(dateStr.substring(0,10));
                if(rs.getString("follow") == null){
                    user.setFollow("");
                }else{
                    user.setFollow(rs.getString("follow"));
                }
                user.setEmail(rs.getString("email"));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                conn.close();
            }catch(SQLException E){; }
        }
    }
    public static int insertsubscribe(String email) {
        Connection conn = getConn();
        //String id = null;
        int i = 0;
        String sql = "insert into subscribe" + "(email) values(?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            //pstmt.setString(1, comp.getId());
            pstmt.setString(1, email);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                conn.close();
            }catch(SQLException E){; }
        }
        return i;
    }
    public static String getFollows(String[] list) {
        Connection conn = getConn();
        String output = "";
        PreparedStatement pstmt;
        try {
            if (list != null) {
                for (String sym : list) {
                    String sql = "select sname from symbols where symbol ='" + sym + "'";
                    pstmt = (PreparedStatement) conn.prepareStatement(sql);
                    //DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        String sname = rs.getString("sname");
                        sname = sname.replace(",", "");
                        sname = sname.replace(".com", "");
                        String[] tuple = sname.split(" ");
                        String finals = "";
                        int len = 0;
                        if (tuple.length > 1) {
                            len = tuple.length - 1;
                        } else {
                            len = tuple.length;
                        }
                        for (int i = 0; i < len; i++) {
                            finals += tuple[i] + " ";
                        }
                        output += finals.substring(0, finals.length() - 1).trim() + "#";
                    }
                }
            }
            conn.close();
        } catch (SQLException e) {
            try {
                conn.close();
            } catch (SQLException E) {
                System.out.println('1');
            }
        }
        if (output == ""){
            return output;
        }else
        {
            return output.substring(0, output.length() - 1);
        }
    }
    public static ArrayList<String> getSymbols(){
        Connection conn = getConn();
        ArrayList<String> list = new ArrayList<String>();
        String sql = "select symbol from symbols";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            //DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            conn.close();
        } catch (SQLException e) {
            try{
                conn.close();
            }catch(SQLException E){; }
        }
        return list;
    }
    public static List<RankObject> getFollowsRank(){
        Connection conn = getConn();
        ArrayList<RankObject> list = new ArrayList<RankObject>();
        String sql = "select * from symbols order by follows DESC";
        PreparedStatement pstmt;
        int count = 30;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            //DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()&&count > 0) {
                RankObject object = new RankObject();
                object.setSym(rs.getString("symbol"));
                object.setSector(rs.getString("sector"));
                object.setIndustry(rs.getString("industry"));
                object.setFollowNum(rs.getInt("follows"));
                list.add(object);
                count--;
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            try{
                conn.close();
            }catch(SQLException E){; }
        }
        return list.subList(0,20);
    }
    public static List<RankObject> getValuesRank(){
        Connection conn = getConn();
        List<RankObject> list = new ArrayList<RankObject>();
        ArrayList<String> namelist = getSymbols();
        String sql="";
        PreparedStatement pstmt = null;
        try {
            for(String symbol:namelist) {
                sql = "select * from " + symbol + "_daily order by timestamp DESC";
                pstmt = (PreparedStatement) conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                RankObject object = new RankObject();
                object.setSym(symbol);
                while (rs.next()) {
                    object.setValue(rs.getFloat("close"));
                    break;
                }
                list.add(object);
            }
            pstmt.close();
            conn.close();
        }catch (SQLException e) {
            try{
                conn.close();
            }catch(SQLException E){; }
        }
        Collections.sort(list, new ValueComparator());
        return list.subList(0,20);
    }
    public static List<RankObject> getRFRank(){
        Connection conn = getConn();
        List<RankObject> list = new ArrayList<RankObject>();
        ArrayList<String> namelist = getSymbols();
        //String sql = "insert into " + id + "(timestamp,open,high,low,close,volume) values(?,?,?,?,?,?)";
        PreparedStatement pstmt = null;
        try {
            for(int i=0;i<namelist.size();i++) {
                System.out.println(i);
                String symbol = namelist.get(i);
                try {
                    pstmt = (PreparedStatement) conn.prepareStatement("select close from "+symbol+"_monthly order by timestamp DESC");
                    ResultSet rs = pstmt.executeQuery();
                    RankObject object = new RankObject();
                    object.setSym(symbol);
                    float open = 0;
                    float close = 0;
                    int count = 0;
                    while (rs.next()) {
                        if(count==0){
                            close = rs.getFloat("close");
                            count++;
                        }else{
                            open = rs.getFloat("close");
                            break;
                        }
                    }
                    object.setRiseAndFall((close-open)/open);
                    if(isInfinite(object.getRiseAndFall())){
                        continue;
                    }
                    list.add(object);
                }catch(SQLException E){
                    continue;
                }
            }
            pstmt.close();
            conn.close();
        }catch (SQLException e) {
            try{
                pstmt.close();
                conn.close();
            }catch(SQLException E){; }
        }
        Collections.sort(list, new RiseNFallComparator());
        System.out.println(list.size());
        return list.subList(0,20);
    }
    public static Company getSymbols(Company comp){
        Connection conn = getConn();
        String sql = "select sector,industry from symbols where symbol ='"+comp.getSymbol()+"'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            //DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                comp.setSector(rs.getString(1));
                comp.setIndustry(rs.getString(2));
            }
            conn.close();
        } catch (SQLException e) {
            try{
                conn.close();
            }catch(SQLException E){; }
        }
        return comp;
    }
    public static List<RankObject> getSimilarStock(Company comp){
        Connection conn = getConn();
        ArrayList<RankObject> list = new ArrayList<RankObject>();
        String sql = "select * from symbols order by follows DESC";
        PreparedStatement pstmt;
        int count = 30;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            //DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()&&count > 0) {
                RankObject object = new RankObject();
                String newsym = rs.getString("symbol");
                String newsector = rs.getString("sector");
                String newsname = rs.getString("sname");
                String newindustry = rs.getString("industry");
                if(!(newsym.equals(comp.getSymbol()))&&newsector.equals(comp.getSector())&&newindustry.equals(comp.getIndustry())) {
                    object.setSym(newsym);
                    object.setSector(newsector);
                    object.setName(newsname);
                    object.setIndustry(newindustry);
                    object.setFollowNum(rs.getInt("follows"));
                    list.add(object);
                    count--;
                }
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            try{
                conn.close();
            }catch(SQLException E){; }
        }
        return list.subList(0,Math.min(3,list.size()));
    }
    public static List<RankObject> getSimilarStock2(Company comp,List<RankObject> list){
        Connection conn = getConn();
        String sql = "select * from symbols order by follows DESC";
        PreparedStatement pstmt;
        int count = 30;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            //DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()&&count > 0) {
                RankObject object = new RankObject();
                String newsym = rs.getString("symbol");
                String newsname = rs.getString("sname");
                String newsector = rs.getString("sector");
                String newindustry = rs.getString("industry");
                if(!(newsym.equals(comp.getSymbol()))&&newsector.equals(comp.getSector())) {
                    object.setSym(newsym);
                    object.setSector(newsector);
                    object.setName(newsname);
                    object.setIndustry(newindustry);
                    object.setFollowNum(rs.getInt("follows"));
                    list.add(object);
                    count--;
                }
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            try{
                conn.close();
            }catch(SQLException E){; }
        }
        return list.subList(0,Math.min(6,list.size()));
    }

}
