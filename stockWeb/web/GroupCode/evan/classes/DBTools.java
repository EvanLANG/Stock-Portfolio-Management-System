package evan.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import Chen.Class.StockDailyRecord;
//import java.sql.DatabaseMetaData;
import Chen.Class.StockDailyRecord;

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
        String sql = "insert into" + id + "(timestamp,open,high,low,close,volume) values(?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            //pstmt.setString(1, comp.getId());
            pstmt.setString(1, comp.TradeDate);
            pstmt.setFloat(2, comp.open);
            pstmt.setFloat(3, comp.high);
            pstmt.setFloat(4, comp.low);
            pstmt.setFloat(5, comp.close);
            pstmt.setLong(6, comp.volume);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return i;
    }

    public static int insertsymbols(String symbol, String sname, String ipoyear, String quoat) {
        Connection conn = getConn();
        //String id = null;
        int i = 0;
        String sql = "insert into symbols"+ "(symbol,sname,ipoyear,quoat) values(?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            //pstmt.setString(1, comp.getId());
            pstmt.setString(1, symbol);
            pstmt.setString(2, sname);
            pstmt.setString(3, ipoyear);
            pstmt.setString(4, quoat);

            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return i;
    }
    public boolean validateTableExist(String tableName){
        boolean flag = false ;
        int i = 0;
        Connection conn = getConn();
        String sql = "SELECT COUNT(*) FROM "+ tableName ;
        PreparedStatement pstmt;
        try{
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            flag =  true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return flag;
    }
    public static int createTable(String tablename) {
        Connection conn = getConn();
        int i = 0;
        String id = null;
        id = tablename;
        String sql = "CREATE TABLE "+id+"\n" +
                "(\n" +
                "timestamp VARCHAR(20) not null PRIMARY KEY ,\n" +
                "open      FLOAT ,\n" +
                "high      FLOAT,\n" +
                "low       FLOAT, \n"+
                "close     FLOAT, \n"+
                "volume    INTEGER \n"+
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

}
