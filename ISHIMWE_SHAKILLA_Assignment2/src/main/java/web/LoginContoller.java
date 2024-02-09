package web;

import java.io.IOException;
import Dao.UserDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginContoller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao loginDao;

    public void init() {
        loginDao = new UserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("Login.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            authenticate(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Login.html"); // Redirect to login page on authentication failure
        }
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (loginDao.validateUser(username, password)) {
            out.println("<html><body>");
            out.println("<h3>Login successful!</h3>");
            out.println("</body></html>");
        } else {
            out.println("<html><body>");
            out.println("<h3>Login failed. Invalid username or password.</h3>");
            out.println("</body></html>");
        }
    }
}
