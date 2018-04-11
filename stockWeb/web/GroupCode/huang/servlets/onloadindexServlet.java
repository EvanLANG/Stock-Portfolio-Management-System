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
import java.util.List;

@WebServlet(name = "onloadindexServlet")
public class onloadindexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataFetch intra_data = new DataFetch();
        DataFetch daily_data = new DataFetch();
        //取所有monthly的数据存成Arraylist
        String interval = "15min";

        List<Company> companies = new ArrayList<Company>();
        List<String> symbollist = new ArrayList<String>();
        symbollist.add("MSFT");

        HttpSession session = request.getSession();

        for (String Sym: symbollist) {
            intra_data.IntraData("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+Sym+"&interval=" + interval + "&apikey=BBWCXYKPHWWLCBZ4", interval);
            //访问Data里面的Arraylist，取第一条记录
            if (intra_data.Dataerror){
                continue;
            }
            StockDailyRecord test = intra_data.Data.get(0);
            System.out.println(intra_data.Symbol);
            System.out.println(test.close+"\n");//最新价
            System.out.println(test.TradeDate.toString());//检查下是不是最新时间

            Company new_com = new Company();
            //公司标识
            new_com.setSymbol(intra_data.Symbol);
            //现价
            new_com.setCurrent(test.close);
            //从今日开盘到目前为止累积的交易总量
            new_com.setVolume(intra_data.TotalV);
            //今日开盘价
            new_com.setOpen(intra_data.newest_open);
            //今日开盘到目前为止的最高价和最低价
            new_com.setHigh(intra_data.current_high);
            new_com.setLow(intra_data.current_low);

            daily_data.DailyData("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+Sym+"&apikey=BBWCXYKPHWWLCBZ4");
            StockDailyRecord test1 = daily_data.Data.get(0);
            StockDailyRecord test2 = daily_data.Data.get(1);
            //昨日闭盘价

            float close;
            float change;
            float change_percent;
            boolean up_or_down;
            if (test1.close == test.close && test1.open == intra_data.newest_open) {
                close = test2.close;
            } else {
                close = test1.close;
            }
            new_com.setClose(close);
            up_or_down = !(close > test.close);
            change = test.close - close;
            change_percent = change/close;

            new_com.setChange(change);
            new_com.setChange_percent(change_percent);
            new_com.setSig(up_or_down);

            companies.add(new_com);
        }

        session.setAttribute("comp", companies);
    }
}
