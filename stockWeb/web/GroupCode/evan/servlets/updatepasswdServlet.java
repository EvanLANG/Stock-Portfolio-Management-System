package evan.servlets;

import evan.classes.DBTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "updatepasswdServlet")
public class updatepasswdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String passwd = request.getParameter("passwd");
        if(email == null || passwd == null){
            request.getRequestDispatcher("/updatepasswd.jsp").forward(request,response);
            return ;
        }
        DBTools db = new DBTools();
        db.updatepasswd(email,passwd);
        request.setAttribute("email", email);
        request.getRequestDispatcher("/updatepasswdSuccess.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
