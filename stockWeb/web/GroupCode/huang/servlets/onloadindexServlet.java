package huang.servlets;

import Chen.Class.DataFetch;
import Chen.Class.StockDailyRecord;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static evan.classes.DBTools.getDaily;
import static evan.classes.DBTools.getIntraVolumeLowHigh;
import static evan.classes.DBTools.getIntraday;

@WebServlet(name = "onloadindexServlet")
public class onloadindexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取所有monthly的数据存成Arraylist
        String interval = "15min";

        List<Company> companies = new ArrayList<Company>();
        List<String> symbollist = new ArrayList<String>();
        List pricelist = new ArrayList();

        //想要添加什么公司就在这里做处理
        symbollist.add("MSFT");
        symbollist.add("JOBS");
        symbollist.add("TURN");
        //symbollist.add("AABA");
        //symbollist.add("FATE");

        HttpSession session = request.getSession();

        for (String Sym: symbollist) {
            //声明
            DataFetch intra_data = new DataFetch(Sym);
            DataFetch daily_data = new DataFetch(Sym);
            //提取intrading day
            intra_data.Data = getIntraday(Sym);//获取全部数据列表
            StockDailyRecord test = intra_data.Data.get(0);
            String current_day = test.TradeDate.substring(0,10);

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
            if (test1.TradeDate.equals(current_day))     {
                    close = test2.close;
            }else {
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
            String day = first_day.TradeDate.substring(0,10);
            int index = 0;
            List current_price = new ArrayList();
            while(true){
                StockDailyRecord current = intra_data.Data.get(index);
                if(current.TradeDate.substring(0,10).equals(day)){
                    current_price.add(current.close);
                    index++;
                    //System.out.println("index:"+index+",");
                }else{ index--;  break; }
            }
            Collections.reverse(current_price);
            pricelist.add(current_price);
        }

        session.setAttribute("pricelist", pricelist);
        session.setAttribute("comp", companies);
        session.setAttribute("complist", symbollist);
    }
}
