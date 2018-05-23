package Chen.Servlet;

import Chen.Class.RankObject;
import huang.servlets.Company;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static evan.classes.DBTools.getSimilarStock;
import static evan.classes.DBTools.getSimilarStock2;

@WebServlet(name = "SimilarStockServlet")
public class SimilarStockServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ind = request.getParameter("index");
        int index = Integer.parseInt(ind);
        HttpSession session = request.getSession();
        List<Company> companies = (List<Company>)session.getAttribute("user_comp");
        Company comp = companies.get(index);
        List<RankObject> list = getSimilarStock(comp);
        if(list.size()<3){
            list = getSimilarStock2(comp,list);
        }
        String out = "";
        for(int i=0;i<list.size();i++){
            out += list.get(i).getSym() + ","+list.get(i).getName()+"<br>";
        }
        PrintWriter writer = response.getWriter();
        if(out.length()>0){
            writer.print(out.substring(0,out.length()-1));
        }else{
            writer.print(out);
        }
        writer.flush();
    }

}
