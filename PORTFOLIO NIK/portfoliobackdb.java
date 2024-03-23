import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deptserv1
 */
public class deptserv1 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public deptserv1() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);

        String email = request.getParameter("email");
        String dpwd = request.getParameter("dpwd");
        System.out.println("Username:" + email);
        System.out.println("Password:" + dpwd);
        int did;

        try {
            Connection con = DBConnection.connect();
            PreparedStatement pstmt = con.prepareStatement("select * from department where email=? and dpwd=?");
            pstmt.setString(1, email);
            pstmt.setString(2, dpwd);
            ResultSet rs = pstmt.executeQuery();

            int i = 0;
            while (rs.next()) {
                i = 1;
                did = rs.getInt(1);
                getsetclass.setDid(did);
                System.out.println("Department Login Successfully");
            }
            if (i > 0) {
                response.sendRedirect("departmentdash.html");
            } else {
                response.sendRedirect("departmentlogin.html");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
