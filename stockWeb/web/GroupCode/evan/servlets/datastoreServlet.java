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
// this servlet just for modified test purpose right now.
@WebServlet(name= "datastoreServlet")
public class datastoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String timestamp = request.getParameter("timestamp");
        String open = request.getParameter("open");
        String high = request.getParameter("high");
        String low = request.getParameter("low");
        String close = request.getParameter("close");
        String volume = request.getParameter("volume");
        try{
            System.out.println(id);
            response.setHeader("Content-Type", "text/html; charset=UTF-8");
            Writer out=response.getWriter();
            out.write(timestamp);
            out.write(open);
            out.write(high);
            out.write(low);
            out.write(close);
            out.write(volume);
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
