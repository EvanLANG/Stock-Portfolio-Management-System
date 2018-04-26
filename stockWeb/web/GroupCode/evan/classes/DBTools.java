package evan.classes;

import java.sql.*;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import Chen.Class.StockDailyRecord;
//import java.sql.DatabaseMetaData;
import Chen.Class.DataFetch;
import Chen.Class.StockDailyRecord;
import Chen.Class.User;
import huang.servlets.Company;
//import org.postgresql.util.PSQLException;

public class DBTools {
    public static Connection getConn() {
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/9900stockportfolio?useSSL=true";
        String username = "postgres";
        String password = "750300";
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

    public static int insertStock(StockDailyRecord comp, String symbol) {
        Connection conn = getConn();
        String id = null;
        id = symbol;
        int i = 0;
        String sql = "insert into " + id + "(timestamp,open,high,low,close,volume) values(?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            //pstmt.setString(1, comp.getId());
            pstmt.setString(1, comp.TradeDate);
            pstmt.setFloat(2, comp.open);
            pstmt.setFloat(3, comp.high);
            pstmt.setFloat(4, comp.low);
            pstmt.setFloat(5, comp.close);
            pstmt.setString(6, String.valueOf(comp.volume));
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static int insertsymbols(String symbol, String sname, String ipoyear, String quote) {
        Connection conn = getConn();
        //String id = null;
        int i = 0;
        String sql = "insert into symbols" + "(symbol,sname,ipoyear,quote) values(?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            //pstmt.setString(1, comp.getId());
            pstmt.setString(1, symbol);
            pstmt.setString(2, sname);
            pstmt.setString(3, ipoyear);
            pstmt.setString(4, quote);

            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return i;
    }
    public static ArrayList<StockDailyRecord> getDaily(String symbol) {
        Connection conn = getConn();
        ArrayList<StockDailyRecord> result = new ArrayList<StockDailyRecord>();
        String sql = "select * from "+symbol+"_daily";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result.add(readData(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static ArrayList<StockDailyRecord> getIntraday(String symbol) {
        Connection conn = getConn();
        ArrayList<StockDailyRecord> result = new ArrayList<StockDailyRecord>();
        String sql = "select * from "+symbol+"_intraday";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result.add(readData(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    private static StockDailyRecord readData(ResultSet rs){
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
        while(true){
            StockDailyRecord current = Data.get(index);
            if(current.TradeDate.substring(0,10).equals(day)){
                data.TotalV += current.volume;
                if(current.high>data.current_high){
                    data.current_high = current.high;
                }
                if(current.low < data.current_low){
                    data.current_low = current.low;
                }
                index++;
                //System.out.println("index:"+index+",");
            }else{
                data.newest_open = Data.get(index-1).open;
                break;
            }
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
    public static int insertusers(String email, String uid, String uname, String upasswd, String risklevel, String favo, String birthdate, String gender) {
        Connection conn = getConn();
        //String id = null;
        int i = 0;
        String sql = "insert into users" + "(email,uid,uname,upasswd,risklevel,favo,birthdate,gender) values(?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, uid);
            pstmt.setString(3, uname);
            pstmt.setString(4, upasswd);
            pstmt.setString(5, risklevel);
            pstmt.setString(6, favo);
            pstmt.setString(7, birthdate);
            pstmt.setString(8, gender);

            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

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
        }
        return i;
    }
    public static String getfavorite(String email){
        String favos= null;
        Connection conn = getConn();
        String sql = "select favo from users where email = '" + email+"'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                favos= rs.getString("favo");
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favos;
    }
    public static int updatefavo(String email,String favos) {
        Connection conn = getConn();
        int i = 0;
        String sql = "update users set favo = '"+favos+"' where email='"+email+"'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
        }
        return result;
    }
    public static void getUser(User user) {
        Connection conn = getConn();
        String theUsername = user.getUsername();
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
                user.setFollow(rs.getString("follow"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
