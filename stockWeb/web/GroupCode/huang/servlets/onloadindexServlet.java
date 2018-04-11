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
        String interval = "10min";

        List<Company> companies = new ArrayList<Company>();
        List<String> symbollist = new ArrayList<String>();
        symbollist.add("MSFT");
        symbollist.add("TURN");
        symbollist.add("JOBS");
        HttpSession session = request.getSession();

        for (String Sym: symbollist) {
            intra_data.IntraData("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+Sym+"&interval=" + interval + "&outputsize=full&apikey=BBWCXYKPHWWLCBZ4", interval);
            //访问Data里面的Arraylist，取第一条记录
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
            //昨日闭盘价
            new_com.setClose(test.close);

            companies.add(new_com);
        }

        session.setAttribute("comp", companies);
    }
}
