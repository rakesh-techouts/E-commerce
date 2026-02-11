package com.techouts.servlets;

import com.techouts.dao.CartDao;
import com.techouts.entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/removeFromCart")
public class RemoveFromCart extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int productId = Integer.parseInt(request.getParameter("productId"));
        Users user = (Users) request.getSession().getAttribute("user");
        if(CartDao.removeProductFromCart(user,productId)){
            request.getSession(false).setAttribute("successMessage", "Product removed successfully!");
            request.getRequestDispatcher("views/cart.jsp").forward(request, response);
        }
    }
}
