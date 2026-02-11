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
            if (password.equals(confirmPassword) && password.length() > 6 ) {
                try (Session session = HibernateUtil.getSession()) {
                    Transaction transaction = session.beginTransaction();
                        Users user = new Users();
                        user.setUsername(username);
                        user.setPhone(phone);
                        user.setPassword(password);
                        user.setEmail(email);
                        Cart cart = new Cart();
                        cart.setUser(user);
                        user.setCart(cart);
                        session.persist(user);
                        transaction.commit();
                        request.getSession(false).setAttribute("user", user);
                        request.getSession(false).setMaxInactiveInterval(30 * 60);
                        request.getRequestDispatcher("views/home.jsp").forward(request, response);
                    }catch (Exception e){
                        request.setAttribute("errorMessage",e.getMessage());
                        request.getRequestDispatcher("views/register.jsp").forward(request, response);
                    }
            }else {
                if(password.equals(confirmPassword))
                    request.setAttribute("errorMessage","Your password is Not same as Confirm password! Please try again.");
                else
                    request.setAttribute("errorMessage","Your password is too short! Please try again.");
                request.getRequestDispatcher("views/register.jsp").forward(request, response);
            }
        }else{
            request.setAttribute("errorMessage","User Already Exists! Please try again.");
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
        }
    }
}
