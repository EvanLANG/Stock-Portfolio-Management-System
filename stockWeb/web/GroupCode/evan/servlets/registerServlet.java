package evan.servlets;

import evan.classes.DBTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registerServlet")
public class registerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String uid = request.getParameter("uid");
        String uname = request.getParameter("uname");
        String upasswd = request.getParameter("upasswd");
        String risklevel = request.getParameter("risklevel");
        String favo = "";
        String birthdate = request.getParameter("birthdate");
        String gender = request.getParameter("gender");
        //String userExist = null;
        if(email == null || uname == null){
            request.getRequestDispatcher("/index.jsp").forward(request,response);
            return ;
        }
        DBTools db = new DBTools();
        db.insertusers(email,uid,uname,upasswd);
        request.setAttribute("uname", uname);
        request.setAttribute("email", email);
        request.getRequestDispatcher("/registerSuccess.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
