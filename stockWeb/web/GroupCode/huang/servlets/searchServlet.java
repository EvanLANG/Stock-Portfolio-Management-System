package huang.servlets;

import Chen.Class.DataFetch;
import Chen.Class.StockDailyRecord;
import evan.classes.DBTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.*;

import static evan.classes.DBTools.getDaily;
import static evan.classes.DBTools.getIntraVolumeLowHigh;
import static evan.classes.DBTools.getIntraday;

@WebServlet(name = "searchServlet")
public class searchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/9900stockportfolio2?useSSL=true";

    // 数据库的用户名与密码
    static final String USER = "9900stockportfolio";
    static final String PASS = "750300";

    public searchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection conn = null;
        Statement stmt = null;

        //取所有monthly的数据存成Arraylist
        String interval = "15min";

        List<Company> companies = new ArrayList<Company>();
        List<String> symbollist = new ArrayList<String>();
        List pricelist = new ArrayList();

        //想要添加什么公司就在这里做处理
        java.lang.String keyword = request.getParameter("kw");
        if (keyword == "") {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        try {
            conn = DBTools.getConn();

            if (conn == null) {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            stmt = conn.createStatement();

            String sql;
            sql = "SELECT symbol, sname FROM symbols";
            //尚未搭建，username password
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                java.lang.String symbol = rs.getString("symbol");

                boolean y = Pattern.compile(keyword,Pattern.CASE_INSENSITIVE).matcher(symbol).find();
                if (y) {
                    symbollist.add(symbol);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println(symbollist);

        HttpSession session = request.getSession();

        for (String Sym : symbollist) {
            //声明
            DataFetch intra_data = new DataFetch(Sym);
            DataFetch daily_data = new DataFetch(Sym);
            //提取intrading day
            intra_data.Data = getIntraday(Sym);//获取全部数据列表
            StockDailyRecord test = intra_data.Data.get(0);
            String current_day = test.TradeDate.substring(0, 10);

            Company new_com = getIntraVolumeLowHigh(intra_data);//获取处理过的数据
            new_com.setCurrent(test.close);
            new_com.setSymbol(Sym);
            //提取daily
            daily_data.Data = getDaily(Sym);//获取全部数据列表
            StockDailyRecord test1 = daily_data.Data.get(0);
            StockDailyRecord test2 = daily_data.Data.get(1);
            //System.out.println(test1.TradeDate + ","+test1.close);
            //System.out.println(test2.TradeDate + ","+test2.close);


            float close;
            //昨日闭盘价
            if (test1.TradeDate.equals(current_day)) {
                close = test2.close;
            } else {
                close = test1.close;
            }

            float change;
            float change_percent;
            boolean up_or_down;

            new_com.setClose(close);
            up_or_down = !(close > test.close);
            change = test.close - close;
            change_percent = change / close;

            new_com.setChange(change);
            new_com.setChange_percent(change_percent);
            new_com.setSig(up_or_down);

            companies.add(new_com);


            //当天所有价格（15min）
            StockDailyRecord first_day = intra_data.Data.get(0);
            String day = first_day.TradeDate.substring(0, 10);
            int index = 0;
            List current_price = new ArrayList();
            while (true) {
                StockDailyRecord current = intra_data.Data.get(index);
                if (current.TradeDate.substring(0, 10).equals(day)) {
                    current_price.add(current.close);
                    index++;
                    //System.out.println("index:"+index+",");
                } else {
                    index--;
                    break;
                }
            }
            Collections.reverse(current_price);
            pricelist.add(current_price);
        }

        session.setAttribute("pricelist", pricelist);
        session.setAttribute("comp", companies);
        session.setAttribute("complist", symbollist);

        request.getRequestDispatcher("/search_result.jsp").forward(request, response);
    }
}
