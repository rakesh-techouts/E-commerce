package com.techouts.servlets;

import com.techouts.dao.OrderDao;
import com.techouts.entity.Orders;
import com.techouts.entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@WebServlet("/checkOut")
public class CheckOut extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Users user = (Users) request.getSession().getAttribute("user");
        String address = request.getParameter("address");
        String paymentMethod = request.getParameter("paymentMethod");
        LocalDateTime date = LocalDateTime.now();
        double amount = Double.parseDouble(request.getParameter("totalPrice"));
        if(user!=null){
            OrderDao.createOrder(user.getId(),address,paymentMethod,amount,date);
            response.getWriter().println("Order has been created");
            response.getWriter().println("<br><a href='" + request.getContextPath()+ "/views/home.jsp'><-Continue Shopping</a>");
        }else{
            out.println("Order has not been created");
            out.println("<br><a href='" + request.getContextPath() + "/views/home.jsp'><-Continue Shopping</a>");
        }
    }
}
