package com.techouts.servlets;

import com.techouts.HibernateUtil;
import com.techouts.dao.UserDao;
import com.techouts.entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class Login extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Users user;
        try{
            user = UserDao.getUserByEmailAndPassword(email, password);
            if(user != null && user.getEmail().equals(email) && user.getPassword().equals(password)) {
                request.getSession(false).setAttribute("user", user);
                request.getSession(false).setMaxInactiveInterval(30*60);
                request.getRequestDispatcher("views/home.jsp").forward(request, response);
            }else{
                out.println("<h3>Invalid UserName or Password</h3>");
                out.println("<a href='index.jsp'>Home</a><br>");
                out.println("<a href='views/login.jsp'>Login</a>");
            }
        }catch (Exception e) {
            out.println("<h3> "+e.getMessage()+" Error</h3>");
            out.println("<a href='index.jsp'>Home</a><br>");
            out.println("<a href='views/login.jsp'>Login</a>");
        }
    }
}
