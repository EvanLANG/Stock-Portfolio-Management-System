package Chen.Servlet;

import Chen.Class.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static evan.classes.DBTools.AccountExist;
import static evan.classes.DBTools.getFollows;
import static evan.classes.DBTools.getUser;

@WebServlet(name = "SignInServlet")
public class SignInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String username = request.getParameter("username") ;
        String password = request.getParameter("password") ;
        if(AccountExist(username,password)){
            User user = new User();
            user.setUsername(username);
            //user.setPassword(password);
            getUser(user);
            if(user.getFollow()!= null){
                String followcoms = getFollows(user.getFollow());
                user.setFollowcoms(followcoms);
            }
            /*if(user.getStatus()==0){
                response.setHeader("Pragma", "No-cache");
                response.setHeader("Cache-Control", "no-cache");
                response.setDateHeader("Expires", 0);
                PrintWriter writer = response.getWriter();
                writer.print("ban");
                writer.flush();
            }else{*/
            HttpSession session = request.getSession();
            //session.setAttribute("photomap", photosmap);
            //session.setAttribute("friendsmap", friendsmap);
            session.setAttribute("user_id", user);
            //session.setAttribute("postlist", postList);
            //response.sendRedirect("/user.jsp");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            PrintWriter writer = response.getWriter();
            writer.print("true");
            writer.flush();
        }else{
            //request.setAttribute("error","tryagain");
            //RequestDispatcher rd = request.getRequestDispatcher ("/index.jsp") ;
            //rd.forward(request,response);
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            PrintWriter writer = response.getWriter();
            writer.print("false");
            writer.flush();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
