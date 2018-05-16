package huang.servlets;

import Chen.Class.DataFetch;
import Chen.Class.RankObject;
import Chen.Class.StockDailyRecord;
import Chen.Class.User;
import evan.classes.DBTools;
import org.apache.commons.collections.list.AbstractLinkedList;

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
import java.util.regex.Pattern;

import static evan.classes.DBTools.*;

@WebServlet(name = "rankServlet")
public class rankServlet extends HttpServlet {
    private void SessionFunction(HttpSession session, List<RankObject> rank, String num,String type){
        List<Company> companies = new ArrayList<Company>();
        List<String> symbollist = new ArrayList<String>();
        List pricelist = new ArrayList();
        List mpricelist = new ArrayList();
        User user = (User)session.getAttribute("user_id");
        for (RankObject obj : rank) {
            String sym = obj.getSym();
            symbollist.add(sym);
        }

        int x=0;
        for (String Sym : symbollist) {
            //声明
            DataFetch intra_data = new DataFetch(Sym);
            DataFetch daily_data = new DataFetch(Sym);
            DataFetch monthly_data = new DataFetch(Sym);
            //提取intrading day
            intra_data.Data = getIntraday(Sym);//获取全部数据列表

            if (intra_data.Data.isEmpty()) {
                continue;
            }
            StockDailyRecord test = intra_data.Data.get(0);

            String current_day = test.TradeDate.substring(0, 10);

            Company new_com = getIntraVolumeLowHigh(intra_data);//获取处理过的数据
            if(new_com == null){
                continue;
            }
            new_com.setCurrent(test.close);
            new_com.setSymbol(Sym);
            //提取daily
            daily_data.Data = getDaily(Sym);//获取全部数据列表
            if (daily_data.Data.isEmpty()) {
                continue;
            }
            StockDailyRecord test1 = daily_data.Data.get(0);
            StockDailyRecord test2 = daily_data.Data.get(1);
            monthly_data.Data = getMonthly(Sym);
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

            x++;
            new_com.setRank_index(x);
            if(type.equals("F")) {
                new_com.setRank_follows(rank.get(x - 1).getFollowNum());
            }else if(type.equals("RF")){
                new_com.setRank_RF(rank.get(x - 1).getRiseAndFall());
            }else{
                new_com.setRank_value(rank.get(x - 1).getValue());
            }

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
                    current_price.add(current.close);
                    break;
                }
            }
            Collections.reverse(current_price);
            pricelist.add(current_price);
            List monthly_price = new ArrayList();
            if(monthly_data.Data.size()>=2) {
                for (StockDailyRecord current : monthly_data.Data) {
                    monthly_price.add(current.close);
                }
            }
            Collections.reverse(monthly_price);
            mpricelist.add(monthly_price);
        }
        session.setAttribute("mpricelist"+num, mpricelist.subList(0,10));
        session.setAttribute("pricelist"+num, pricelist.subList(0,10));
        session.setAttribute("comp"+num, companies.subList(0,10));
        session.setAttribute("complist"+num, symbollist.subList(0,10));

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        HttpSession session = request.getSession();
        if(session.getAttribute("user_id")==null){
            request.getRequestDispatcher("/rank_page.jsp").forward(request, response);
        }else{
            User user = (User)session.getAttribute("user_id");
            getUser(user);
            //取所有monthly的数据存成Arraylist
            String interval = "15min";


//follows rank
            List<RankObject> rank1 = getFollowsRank();
            SessionFunction(session, rank1,"1","F");

//Rise and Fall rank
            //List<RankObject> rank2 = getRFRank();
            //SessionFunction(session, rank2,"2","RF");

//Value rank
            //rank = getValuesRank();
            //SessionFunction(session, rank,"3","V");

            session.setAttribute("where","rank");

            request.getRequestDispatcher("/rank_page.jsp").forward(request, response);
        }
    }
}
