package com.techouts.servlets;

import com.techouts.HibernateUtil;
import com.techouts.entity.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.techouts.entity.Users;
import com.techouts.dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String confirmPassword = request.getParameter("confirmPassword");
        long phone = Long.parseLong(request.getParameter("phone"));
        if (email!=null && !(UserDao.isEmailExists(email))) {
            if (password.equals(confirmPassword) && password.length() > 6 && confirmPassword.length() > 6) {
                try (Session session = HibernateUtil.getSession()) {
                    Transaction transaction = session.beginTransaction();
                        Users user = new Users();
                        user.setUsername(username);
                        user.setPhone(phone);
                        user.setPassword(password);
                        user.setEmail(email);
                        Cart cart = new Cart();
                        cart.setUser(user);
                        session.persist(user);
                        session.persist(cart);
                        transaction.commit();
                        request.getSession(false).setAttribute("user", user);
                        request.getSession(false).setMaxInactiveInterval(30 * 60);
                        request.getRequestDispatcher("views/home.jsp").forward(request, response);
                    }catch (Exception e){
                        out.println(e.getMessage());
                        out.println("<a href='index.jsp'>Home</a><br>");
                        out.println("<a href='views/register.jsp'>Register</a>");
                    }
            }else {
                out.println("<h3> Your password is too short! or Not same as Confirm password! Please try again.</h3>");
                out.println("<a href='index.jsp'>Home</a><br>");
                out.println("<a href='views/register.jsp'>Register</a>");
            }
        }else{
            out.println("<h3>User Already Exists! Please try again.</h3>");
            out.println("<a href='index.jsp'>Home</a><br>");
            out.println("<a href='views/register.jsp'>Register</a>");
        }
    }
}
