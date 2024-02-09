package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.User;
import Dao.UserDao;



@WebServlet("/register")
public class usercontroller extends HttpServlet {

   
	private static final long serialVersionUID = 1L;
	private Dao.UserDao userDao ;

    public void init() {
        userDao = new Dao.UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        

        // Create a new Student object
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
       

        // Register the user using the DAO
        userDao.saveUser(user);
        // Set response content type
        response.setContentType("text/html");

        // Write registration success message directly to response
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h2>Registration Successful</h2>");
        response.getWriter().println("<p>Thank you for registering, " + firstName + " " + lastName + "!</p>");
        response.getWriter().println("</body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to doPost method
        doPost(request, response);
    }

}
