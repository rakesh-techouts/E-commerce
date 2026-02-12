package com.techouts.servlets;

import com.techouts.dao.CartDao;
import com.techouts.dao.CartItemDao;
import com.techouts.entity.CartItem;
import com.techouts.entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/updateQuantity")
public class UpdateQuantity extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int cartItemId =Integer.parseInt(request.getParameter("item"));
        System.out.println(cartItemId);
        if(action.equalsIgnoreCase("increase")){
            CartItemDao.increaseItem(cartItemId);
            response.sendRedirect("views/cart.jsp");
        }else{
            CartItemDao.decreaseItem(cartItemId);
            response.sendRedirect("views/cart.jsp");
        }
    }
}
