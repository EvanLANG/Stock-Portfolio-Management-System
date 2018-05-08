package huang.servlets;

import Chen.Class.DataFetch;
import Chen.Class.RankObject;
import Chen.Class.StockDailyRecord;
import evan.classes.DBTools;
import org.apache.commons.collections.list.AbstractLinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import static evan.classes.DBTools.*;

@WebServlet(name = "rankServlet")
public class rankServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;

        //取所有monthly的数据存成Arraylist
        String interval = "15min";
        HttpSession session = request.getSession();

//follows rank
        List<RankObject> followrank = getFollowsRank();
        session.setAttribute("followlist", followrank);

        List<RankObject> valuesrank = getValuesRank();
        session.setAttribute("valuelist", valuesrank);

//Rise and Fall rank
        List<RankObject> rfrank = getRFRank();
        session.setAttribute("rflist", rfrank);
        request.getRequestDispatcher("/rank_page.jsp").forward(request, response);
    }
}
