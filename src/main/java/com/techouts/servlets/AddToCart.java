package com.techouts.servlets;

import com.techouts.dao.CartDao;
import com.techouts.dao.ProductsDao;
import com.techouts.entity.Product;
import com.techouts.entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addToCart")
public class AddToCart extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        Users user = (Users) session.getAttribute("user");
        Product product = ProductsDao.getProduct(Integer.parseInt(request.getParameter("id")));

        if(product != null && user != null) {
            if(CartDao.addProduct(user,product)){
                request.getSession(false).setAttribute("successMessage", "Product added successfully!");
                response.sendRedirect("views/home.jsp?category=All");
            }else{
                request.getSession(false).setAttribute("successMessage", "Product Already exists!");
                response.sendRedirect("views/home.jsp");
            }
        }else{

        }
    }
}
