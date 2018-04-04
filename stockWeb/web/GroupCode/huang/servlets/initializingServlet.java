package huang.servlets;

import Chen.Class.DataFetch;
import Chen.Class.StockDailyRecord;
import evan.classes.DBTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class initializingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/9900stockportfolio2?useSSL=true";

    // 数据库的用户名与密码
    static final String USER = "9900stockportfolio";
    static final String PASS = "750300";

    public initializingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        DataFetch monthly_data = new DataFetch();
        //取所有monthly的数据存成Arraylist
        String interval = "15min";
        monthly_data.IntraData("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval="+interval+"&outputsize=full&apikey=BBWCXYKPHWWLCBZ4",interval);
        //访问Data里面的Arraylist，取第一条记录
        StockDailyRecord test = monthly_data.Data.get(0);
        System.out.print(monthly_data.Data.size());
        System.out.print(monthly_data.Symbol+'/');
        System.out.print(test.close+'/');
        System.out.print(test.open+'/');
        System.out.print(test.high+'/');
        System.out.print(test.low+'/');
        System.out.print(test.volume);
        HttpSession session = request.getSession();
        java.lang.String username = request.getParameter("username");
        java.lang.String password = request.getParameter("password");
        try {
            conn = DBTools.getConn();

            if (conn == null) {
                response.getWriter().print("<script> alert(\"Nonexistent User, or incorrect password!\"); </script>");
                request.getRequestDispatcher("/sign_in.jsp").forward(request, response);
            }
            stmt = conn.createStatement();

            String sql;
            sql = "SELECT uname, upasswd FROM users";
            //尚未搭建，username password
            ResultSet rs = stmt.executeQuery(sql);

            System.out.print("1");

            while(rs.next()) {

                java.lang.String real_username = rs.getString("uname");
                java.lang.String real_password = rs.getString("upasswd");

                if (username.compareTo(real_username) == 0 && (password.compareTo(real_password) == 0)) {
                    session.setAttribute("user_id", username);
                    //还需添加更多Attribute到session里：收藏的股票啥的
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
            }

            //没有匹配的用户，或密码错误
            response.getWriter().print("<script> alert(\"Nonexistent User, or incorrect password!\"); </script>");
            request.getRequestDispatcher("sign_in.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}