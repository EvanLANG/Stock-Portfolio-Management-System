package evan.servlets;

import evan.classes.DBTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static evan.classes.DBTools.*;

@WebServlet(name = "updatefavoServlet")
public class updatefavoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String favo = request.getParameter("favo");
        //favorite stock formate: AA,BB,CC,DD
        String favos = null;
        DBTools db = new DBTools();
        favos = db.getfavorite(email);
        favos = favos + "," + favo;
        db.updatefavo(email,favos);
        //request.setAttribute("email", email);
        //request.getRequestDispatcher("/somepage.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
