package huang.servlets;

import Chen.Class.DataFetch;
import Chen.Class.RankObject;
import Chen.Class.StockDailyRecord;
import Chen.Class.User;
import Chen.Comparator.SimilarityComparator;
import evan.classes.DBTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.regex.*;
import info.debatty.java.stringsimilarity.*;



import static evan.classes.DBTools.*;

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
    private void SearchFunction(HttpSession session,List<String> symbollist,List<String> snamelist,List<String> urllist){
        List<Company> companies = new ArrayList<Company>();
        List<String> recordllist = new ArrayList<String>();
        List pricelist = new ArrayList();
        User user = (User)session.getAttribute("user_id");
        DBTools db = new DBTools();
        Connection conn = getConn();
        PreparedStatement pstmt=null;
        HashMap<String,DataFetch> intra_map = new HashMap<String, DataFetch>();
        HashMap<String,DataFetch> daily_map = new HashMap<String,DataFetch>();
        try {
            for (String Sym : symbollist) {
                DataFetch intra_data = new DataFetch(Sym);
                DataFetch daily_data = new DataFetch(Sym);
                ArrayList<StockDailyRecord> result = new ArrayList<StockDailyRecord>();
                String sql = "select * from " + Sym + "_intraday order by timestamp DESC";
                pstmt = (PreparedStatement) conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    result.add(readData(rs));
                }
                intra_data.Data = result;
                intra_map.put(Sym,intra_data);
                result = new ArrayList<StockDailyRecord>();
                sql = "select * from "+Sym+"_daily order by timestamp DESC";
                pstmt = (PreparedStatement)conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    result.add(readData(rs));
                }
                daily_data.Data = result;
                daily_map.put(Sym,daily_data);
            }
            pstmt.close();
            conn.close();
        }catch (SQLException e) {
            try{
                conn.close();
            }catch(SQLException E){; }
        }

        for (int i=0;i < symbollist.size();i++) {
            //声明
            String Sym = symbollist.get(i);
            String sname = snamelist.get(i);
            String url = urllist.get(i);
            DataFetch intra_data = new DataFetch(Sym);
            DataFetch daily_data = new DataFetch(Sym);
            //提取intrading day
            intra_data.Data = null;
            if(intra_map.get(Sym)!=null&&intra_map.get(Sym).Data.size()!=0){
                intra_data.Data = intra_map.get(Sym).Data;//获取全部数据列表
            }else{continue;}
            StockDailyRecord test = intra_data.Data.get(0);

            String current_day = test.TradeDate.substring(0, 10);

            Company new_com = getIntraVolumeLowHigh(intra_data);//获取处理过的数据
            new_com.setCurrent(test.close);
            new_com.setSymbol(Sym);
            new_com.setComname(sname);
            new_com.setUrl(url);
            //提取daily
            daily_data.Data = null;//获取全部数据列表
            if(daily_map.get(Sym)!=null&&daily_map.get(Sym).Data.size()!=0){
                daily_data.Data = daily_map.get(Sym).Data;//获取全部数据列表
            }else {continue;}
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
            if(user!=null) {
                String favo = user.getFollowString();
                if(favo!=null&&favo.contains(new_com.getSymbol())){
                    new_com.setFollowed(1);
                }else{new_com.setFollowed(0);}
            }else{
                new_com.setFollowed(0);
            }
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
        session.setAttribute("where", "search");

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
        List<String> recordllist = new ArrayList<String>();
        List<String> symbollist = new ArrayList<String>();
        List<String> snamelist = new ArrayList<String>();
        List<String> urllist = new ArrayList<String>();
        List<RankObject> simlist= new ArrayList<RankObject>();
        List pricelist = new ArrayList();

        //想要添加什么公司就在这里做处理
        java.lang.String keyword = request.getParameter("kw").toLowerCase();
        if (!keyword.equals("")) {
            try {
                conn = getConn();

                if (conn == null) {
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
                String sql;
                sql = "SELECT symbol, sname, quote FROM symbols";
                //尚未搭建，username password
                PreparedStatement pstmt;
                pstmt = (PreparedStatement)conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    java.lang.String symbol = rs.getString("symbol");
                    String sname = rs.getString("sname");
                    String url  = rs.getString("quote");
                    JaroWinkler jw = new JaroWinkler();
                    //boolean y = symbol.contains(keyword);
                    //boolean m = sname.contains(keyword);
                    double s1 = jw.similarity(keyword, symbol.toLowerCase());
                    double s2 = jw.similarity(keyword, sname.toLowerCase());
                    //boolean y = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE).matcher(symbol).find();
                    //boolean m = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE).matcher(sname).find();
                    if (s1>0.7||s2>0.7) {
                        double s = Math.max(s1,s2);
                        //symbollist.add(symbol);
                        //snamelist.add(sname);
                        RankObject simobject = new RankObject();
                        simobject.setSym(symbol);
                        simobject.setName(sname);
                        simobject.setSim(s);
                        simlist.add(simobject);
                        urllist.add(url);
                    }
                }
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                try{
                    conn.close();
                }catch(SQLException E){; }
            }
        }
        Collections.sort(simlist, new SimilarityComparator());
        for(int i=0;i<simlist.size();i++){
            symbollist.add(simlist.get(i).getSym());
            snamelist.add(simlist.get(i).getName());
        }
        HttpSession session = request.getSession();
        if (symbollist.size()>0) { SearchFunction(session,symbollist,snamelist,urllist); }
        else {session.setAttribute("comp",null);}
        response.sendRedirect("/search_result.jsp");

    }
}
