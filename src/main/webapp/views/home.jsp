<%@ page contentType="text/html; charset=UTF-8" language="java"
import="com.techouts.entity.* , jakarta.servlet.http.HttpSession , com.techouts.dao.ProductsDao ,java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home | Ecommerce</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<body>
<%
    HttpSession httpSession = request.getSession(false);
    Users user = (httpSession != null) ? (Users) httpSession.getAttribute("user") : null;
%>
<% if (user != null) { %>
    <h4 style="font-family: 'Times New Roman', serif;"><%= user.getUsername() %>! Welcome to Tech-Outs Shopping</h4>
    <a style="text-decoration:none" href='views/cart.jsp'>MyCart</a><br>
    <a style="text-decoration:none" href='${pageContext.request.contextPath}/logout'>Log out</a>
    <!-- For print the Products --!>
    <h3 style="font-family: 'Times New Roman', serif;">Products in Techouts</h3>
<div class="container my-3">
  <div class="row row-cols-2 row-cols-sm-3 row-cols-md-4 row-cols-lg-5 g-3">
    <%
        ArrayList<Product> products = ProductsDao.getAllProducts();
        if (products != null && !products.isEmpty()) {
            for (Product product : products) {
    %>
      <div class="col">
        <div class="card h-100">
          <div class="card-body p-2">
            <h6 class="card-title mb-1 text-truncate"><%= product.getProductName() %></h6>
            <div class="product-price fw-bold mb-1">$ <%= product.getPrice() %></div>
            <p class="card-text small mb-2" style="display:-webkit-box; -webkit-line-clamp:2; -webkit-box-orient:vertical; overflow:hidden;">
              <%= product.getDescription() %>
            </p>
            <a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/addToCart?id=<%= product.getId() %>">Add to Cart</a>
          </div>
        </div>
      </div>
    <%
            }
        } else {
    %>
      <div class="col-12">
        <h3 class="h6 text-muted m-0">No products available.</h3>
      </div>
    <%
        }
    %>
  </div>
</div>
<% } else { %>
    <h3>Welcome Guest! <a href="<%= request.getContextPath() %>/views/register.jsp">Register</a></h3>
<% } %>
</body>
</html>
