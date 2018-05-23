package Chen.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static evan.classes.DBTools.createUserHistory;
import static evan.classes.DBTools.insertusers;

@WebServlet(name = "SignUpServlet")
public class SignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username") ;
        String password = request.getParameter("password") ;
        String id = request.getParameter("id") ;
        String email = request.getParameter("email") ;
        insertusers(email,id,username,password);
        createUserHistory(username);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter printWriter=response.getWriter();
        printWriter.println('T');
        printWriter.flush();
    }
}
