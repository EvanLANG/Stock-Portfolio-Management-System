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
        DataFetch monthly_data = new DataFetch();
        //取所有monthly的数据存成Arraylist
        String interval = "15min";

        List<Company> companies = new ArrayList<Company>();
        List<String> symbollist = new ArrayList<String>();
        symbollist.add("MSFT");
        symbollist.add("TURN");
        symbollist.add("JOBS");
        HttpSession session = request.getSession();

        for (String Sym: symbollist) {
            monthly_data.IntraData("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+Sym+"&interval=" + interval + "&outputsize=full&apikey=BBWCXYKPHWWLCBZ4", interval);
            //访问Data里面的Arraylist，取第一条记录
            StockDailyRecord test = monthly_data.Data.get(0);
            System.out.println(monthly_data.Symbol);
            System.out.println(test.close);
            System.out.println(test.open);
            System.out.println(test.high);
            System.out.println(test.low);
            System.out.println(test.volume);

            Company new_com = new Company();
            new_com.setSymbol(monthly_data.Symbol);
            new_com.setHigh(test.high);
            new_com.setLow(test.low);
            new_com.setOpen(test.open);
            new_com.setClose(test.close);
            new_com.setVolume(test.volume);
            companies.add(new_com);
        }

        session.setAttribute("comp", companies);
    }
}
