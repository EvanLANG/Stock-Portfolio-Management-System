package evan.servlets;

import Chen.Class.User;
import evan.classes.DBTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static evan.classes.DBTools.*;

@WebServlet(name = "updatefavoServlet")
public class updatefavoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sym = request.getParameter("symbol");
        String type = request.getParameter("type");
        //favorite stock formate: AA,BB,CC,DD
        System.out.println(sym);
        System.out.println(type);

        HttpSession session = request.getSession();

        User user = (User)session.getAttribute("user_id");
        DBTools db = new DBTools();
        if(type.equals("f")) {
            db.updatefavo(user, sym);
        }else{
            db.cancelfavo(user, sym);
        }
        String name = user.getUsername();
        user = new User();
        user.setUsername(name);
        getUser(user);
        if(user.getFollow()!= null){
            String followcoms = getFollows(user.getFollow());
            user.setFollowcoms(followcoms);
        }
        if(user.getFollowString()!= null){
            String followcoms = getFollows(user.getFollow());
            user.setFollowcoms(followcoms);
        }
        session.setAttribute("user_id",user);
        PrintWriter writer = response.getWriter();
        writer.print("success.");
        writer.flush();
        //request.setAttribute("email", email);
        //request.getRequestDispatcher("/somepage.jsp").forward(request,response);
        session.setAttribute("where", "updated");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
