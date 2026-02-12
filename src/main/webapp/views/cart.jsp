<%@ page contentType="text/html; charset=UTF-8" language="java"
import="com.techouts.entity.* , jakarta.servlet.http.HttpSession , com.techouts.dao.* ,java.util.*" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<body>
<%
    HttpSession httpSession = request.getSession(false);
    Users user = (httpSession != null) ? (Users) httpSession.getAttribute("user") : null;
%>
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
    <h4>${user.getUsername()} Your Products are Waiting for you<h4>
    <a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/views/home.jsp">Home</a>
<% if (user != null) { %>
<div class="container-fluid px-0 my-3">
  <div class="row row-cols-2 row-cols-sm-3 row-cols-md-4 row-cols-lg-5 g-0">
    <%
        List<CartItem> cartItems =CartDao.getAllCartItems(user);
        if (cartItems != null && cartItems.size()>0) {
            Double totalPrice=0.0;
            for (CartItem item : cartItems) {
    %>
      <div class="col">
        <div class="card h-100">
          <div class="card-body p-2">
            <h6 class="card-title mb-1 text-truncate"><%= item.getProduct().getProductName() %></h6>
            <div class="product-price fw-bold mb-1">$ <%= item.getProduct().getPrice() %></div>
            <% totalPrice+=(item.getProduct().getPrice()*item.getQuantity());%>
            <p class="card-text small mb-2" style="display:-webkit-box; -webkit-line-clamp:2; -webkit-box-orient:vertical; overflow:hidden;">
              <%= item.getProduct().getDescription() %>
            </p>
            <div class="d-flex align-items-center mb-2 gap-2">
                    <p style="font-family: 'Times New Roman', serif; font-size:1.1rem">Quantity:</p>
                    <form method="post" action="${pageContext.request.contextPath}/updateQuantity" style="display:inline;">
                           <input type="hidden" name="item" value="<%=item.getId()%>">
                           <input type="hidden" name="action" value="decrease">
                           <button type="submit" class="btn btn-danger btn-sm">-</button>
                    </form>
                    <%=item.getQuantity()%>
                    <form method="post" action="${pageContext.request.contextPath}/updateQuantity" style="display:inline;">
                        <input type="hidden" name="item" value="<%=item.getId()%>">
                        <input type="hidden" name="action" value="increase">
                         <button type="submit" class="btn btn-success btn-sm">+</button>
                    </form>
                    <a class="btn btn-danger btn-sm" href="${pageContext.request.contextPath}/removeFromCart?productId=<%=item.getId() %>">Delete</a>
                </div>
          </div>
        </div>
      </div>
         <% }%>
<!-- Floating Total at bottom-right -->
  <div class="position-fixed bottom-0 end-0 m-10">
    <div class=" text-dark border-0 shadow rounded p-3 fw-bold">
      Total Price: $ <%= String.format("%.2f", totalPrice) %>
      <a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/views/checkOut.jsp?msg=PlaceOrder">PlaceOrder</a>
    </div>
  </div>
    <%} else {%>
      <div class="col-12">
        <h3 class="h6 text-muted m-0">No products available.</h3>
      </div>
    <%
        }
    %>
  </div>
</div>
<% } else { %>
    <h3>Welcome Guest! <a href="register.jsp">Register</a></h3>
<% } %>
</body>
</html>
