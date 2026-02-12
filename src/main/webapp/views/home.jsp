<%@ page contentType="text/html; charset=UTF-8" language="java"
import="com.techouts.entity.* , jakarta.servlet.http.HttpSession , com.techouts.dao.ProductsDao ,java.util.*" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%
    String category = request.getParameter("category");
    List<Product> productsList;
    if (category == null || category.trim().isEmpty() || category.equalsIgnoreCase("All")) {
        productsList = ProductsDao.getAllProducts();
    } else {
        productsList = ProductsDao.getProductsByCategory(category.trim());
    }
    request.setAttribute("products", productsList);
    request.setAttribute("category", category);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home | Ecommerce</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
    .dropdown {
        position: relative;
        display: inline-block;
    }

    /* Button */
    .dropdown-btn {
        padding: 10px 14px;
        background: #eee;
        border: 1px solid #ccc;
        cursor: pointer;
        border-radius: 5px;
    }

    /* Dropdown List Hidden Initially */
    .dropdown-list {
        display: none;
        position: absolute;
        left: 0;
        top: 100%;
        background: white;
        border: 1px solid #ccc;
        width: 180px;
        border-radius: 5px;
        z-index: 100;
    }

    /* Show on Hover */
    .dropdown:hover .dropdown-list {
        display: block;
    }

    /* Items */
    .dropdown-item {
        padding: 10px;
        display: block;
        text-decoration: none;
        color: black;
    }

    .dropdown-item:hover {
        background: #f1f1f1;
    }
       </style>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
    <h1><a style="font-family: 'Times New Roman', serif;" style="text-decoration: none">Tech-Outs Mall</a></h1>
    <a style="font-family: 'Times New Roman', serif;" style="text-decoration: none" href="${pageContext.request.contextPath}/views/cart.jsp" >Mycart</a>
    <a style="font-family: 'Times New Roman', serif;" style="text-decoration: none" href="${pageContext.request.contextPath}/logout">Log out</a>
<div class="dropdown">
    <div class="dropdown-btn">Category</div>
    <div class="dropdown-list">
        <a class="dropdown-item" href="${pageContext.request.contextPath}/views/home.jsp?category=All">All</a>
        <a class="dropdown-item" href="${pageContext.request.contextPath}/views/home.jsp?category=Laptop">Laptops</a>
        <a class="dropdown-item" href="${pageContext.request.contextPath}/views/home.jsp?category=Mobile">Mobiles</a>
        <a class="dropdown-item" href="${pageContext.request.contextPath}/views/home.jsp?category=Buds">Wireless Buds</a>
    </div>
</div>
<%
    HttpSession httpSession = request.getSession(false);
    Users user = (httpSession != null) ? (Users) httpSession.getAttribute("user") : null;
    if (user != null) { %>
    <h4 style="font-family: 'Times New Roman', serif;"><%= user.getUsername() %>! Welcome to Tech-Outs Shopping</h4>
    <!-- For print the Products --!>
    <h4 style="font-family: 'Times New Roman', serif;">Products in Techouts</h4>

    <c:if test="${not empty successMessage and fn:length(fn:trim(successMessage)) > 0}">
        <div id="popup"
             style="
                 position:fixed; top:10px; left:50%; transform:translateX(-50%);
                 background:green; color:white; padding:10px 20px;
                 border-radius:5px; z-index:9999; transition:opacity .5s ease;">
            ${fn:escapeXml(successMessage)}
        </div>
        <script>
            setTimeout(() => {
                const el = document.getElementById('popup');
                el.style.opacity = '0';
                setTimeout(() => el.style.display = 'none', 500);
            }, 2000);
        </script>
    </c:if>

<div class="container-fluid px-0 my-3">
  <div class="row row-cols-2 row-cols-sm-3 row-cols-md-4 row-cols-lg-5 g-0">
    <%
      List<Product> products=(List<Product>) request.getAttribute("products");
      if (products != null && products.size()>0) {
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
            <a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/views/checkOut.jsp?id=<%= product.getId() %>&msg=BuyNow">Buy Now</a>
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
    <% } %>
  </div>
</div>

<% } else { %>
    <h3>Welcome Guest! <a href="${pageContext.request.contextPath}/views/register.jsp">Register</a></h3>
<% } %>
</body>
</html>
