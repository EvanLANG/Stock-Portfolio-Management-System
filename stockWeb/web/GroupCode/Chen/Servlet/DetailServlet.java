package Chen.Servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DetailServlet")
public class DetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String symbol = request.getParameter("sym");
        String index = request.getParameter("index");
        String flag =  request.getParameter("flag");
        int index_int = Integer.parseInt(index);
        request.setAttribute("flag",flag);
        request.setAttribute("sym",symbol);
        request.setAttribute("index",index_int);
        RequestDispatcher rd = request.getRequestDispatcher("tanchuang.jsp");
        rd.forward(request,response);
    }
}
