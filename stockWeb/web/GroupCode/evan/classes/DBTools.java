package evan.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import evan.classes.company;


public class DBTools {
    public static Connection getConn() {
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/9900stockportfolio2?useSSL=true";
        String username = "postgres";
        String password = "123456";
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
    public static int insertStock(Class.StockDailyRecord comp,String symbol) {
        Connection conn = getConn();
        String id = null;
        id = symbol;
        int i = 0;
        String sql = "insert into" + id + "(timestamp,open,high,low,close,volume) values(?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            //pstmt.setString(1, comp.getId());
            pstmt.setTimestamp(1, comp.TradeDate);
            pstmt.setFloat(2, comp.open);
            pstmt.setFloat(3, comp.high);
            pstmt.setFloat(4, comp.low);
            pstmt.setFloat(5, comp.close);
            pstmt.setInt(6, comp.volume);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
