package evan.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
//import java.sql.Timestamp;
import java.lang.String;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import Class.DataFetch;
import Class.StockDailyRecord;

// this servlet just for modified test purpose right now.
@WebServlet(name= "datastoreServlet")
public class datastoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        DataFetch monthly_data = new DataFetch();
        //取所有monthly的数据存成Arraylist
        monthly_data.MonthlyData("https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=MSFT&apikey=BBWCXYKPHWWLCBZ4");
        //访问Data里面的Arraylist，取第一条记录
        StockDailyRecord test = monthly_data.Data.get(1);
        //String id = request.getParameter("id");
        //String timestamp = request.getParameter("timestamp");
        //String open = request.getParameter("open");
        //String high = request.getParameter("high");
        //String low = request.getParameter("low");
        //String close = request.getParameter("close");
        //String volume = request.getParameter("volume");
        try{
            //Symbol就是公司名
            System.out.println(monthly_data.Symbol);
            response.setHeader("Content-Type", "text/html; charset=UTF-8");
            Writer out=response.getWriter();
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            out.write(sdf.format(test.TradeDate));
            out.write(Float.toString(test.open));
            out.write(Float.toString(test.close));
            out.write(Float.toString(test.high));
            out.write(Float.toString(test.low));
            out.write(test.volume);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
