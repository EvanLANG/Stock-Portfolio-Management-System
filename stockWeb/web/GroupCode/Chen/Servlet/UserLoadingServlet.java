package Chen.Servlet;

import Chen.Class.DataFetch;
import Chen.Class.StockDailyRecord;
import Chen.Class.User;
import huang.servlets.Company;

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

import static evan.classes.DBTools.*;

@WebServlet(name = "UserLoadingServlet")
public class UserLoadingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String interval = "15min";
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user_id");
        getUser(user);
        if(user.getFollow()!= null){
            String followcoms = getFollows(user.getFollow());
            user.setFollowcoms(followcoms);
        }
        List<Company> companies = new ArrayList<Company>();
        String[] symbollist = user.getFollow();
        String[] snamelist = user.getFollowcoms().split("#");
        List pricelist = new ArrayList();
        List mpricelist = new ArrayList();
        List datelist = new ArrayList();
        List mdatelist = new ArrayList();
        if(symbollist == null){
            session.setAttribute("user_pricelist", new ArrayList());
            session.setAttribute("user_comp", new ArrayList());
            session.setAttribute("user_complist", new ArrayList());
            session.setAttribute("where", "user");
        }else {
            for (int i=0;i<symbollist.length;i++) {
                //声明
                String Sym = symbollist[i];
                String name = snamelist[i];
                DataFetch intra_data = new DataFetch(Sym);
                DataFetch daily_data = new DataFetch(Sym);
                DataFetch monthly_data = new DataFetch(Sym);
                //提取intrading day
                intra_data.Data = getIntraday(Sym);//获取全部数据列表
                StockDailyRecord test = intra_data.Data.get(0);
                String current_day = test.TradeDate.substring(0, 10);

                Company new_com = getIntraVolumeLowHigh(intra_data);//获取处理过的数据
                if(new_com == null){
                    continue;
                }
                new_com.setCurrent(test.close);
                new_com.setSymbol(Sym);
                new_com.setComname(name);
                //提取daily
                daily_data.Data = getDaily(Sym);//获取全部数据列表
                StockDailyRecord test1 = daily_data.Data.get(0);
                StockDailyRecord test2 = daily_data.Data.get(1);
                //System.out.println(test1.TradeDate + ","+test1.close);
                //System.out.println(test2.TradeDate + ","+test2.close);

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

                new_com.setClose(close);
                up_or_down = !(close > test.close);
                change = test.close - close;
                change_percent = change / close;

                new_com.setChange(change);
                new_com.setChange_percent(change_percent);
                new_com.setSig(up_or_down);


                companies.add(getSymbols(new_com));


                //当天所有价格（15min）
                StockDailyRecord first_day = intra_data.Data.get(0);
                String day = first_day.TradeDate.substring(0, 10);
                int index = 0;
                List current_price = new ArrayList();
                List current_date = new ArrayList();
                while (true) {
                    StockDailyRecord current = intra_data.Data.get(index);
                    if (current.TradeDate.substring(0, 10).equals(day)) {
                        current_price.add(current.close);
                        current_date.add(current.TradeDate);
                        index++;
                        //System.out.println("index:"+index+",");
                    } else {
                        current_price.add(current.close);
                        current_date.add(current.TradeDate);
                        break;
                    }
                }
                Collections.reverse(current_price);
                Collections.reverse(current_date);
                String c_date =  org.apache.commons.lang.StringUtils.join(current_date.toArray(),",");
                pricelist.add(current_price);
                datelist.add(c_date);
                List monthly_price = new ArrayList();
                List monthly_date = new ArrayList();
                if(monthly_data.Data.size()>=2) {
                    for (StockDailyRecord current : monthly_data.Data) {
                        monthly_price.add(current.close);
                        monthly_date.add(current.TradeDate);
                    }
                }
                Collections.reverse(monthly_price);
                Collections.reverse(monthly_date);
                String c_mdate =  org.apache.commons.lang.StringUtils.join(monthly_date.toArray(),",");
                mpricelist.add(monthly_price);
                mdatelist.add(c_mdate);
            }
            session.setAttribute("user_mdatelist", mdatelist);
            session.setAttribute("user_datelist", datelist);
            session.setAttribute("user_mpricelist", mpricelist);
            session.setAttribute("user_pricelist", pricelist);
            session.setAttribute("user_comp", companies);
            session.setAttribute("user_complist", symbollist);
            session.setAttribute("where", "user");
        }
    }
}
