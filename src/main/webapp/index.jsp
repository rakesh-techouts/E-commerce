<%@ page language="java" contentType="text/html; charset=UTF-8 " pageEncoding="UTF-8"
    import="java.util.*,com.techouts.entity.* , com.techouts.dao.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<body>
    <h1 style="font-family: 'Times New Roman', serif;">Welcome to Tech-Outs Shopping</h1>
    <p style="font-family: 'Times New Roman', serif;">Worlds Number Shopping platform</p>
    <a style="text-decoration: none"href="index.jsp">home</a><br>
	<a style="text-decoration: none" href="views/login.jsp"> login</a><br>
	<a style="text-decoration: none" href="views/register.jsp">register</a><br>
</body>

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
            <a class="btn btn-primary btn-sm" href="views/login.jsp">Add to Cart</a>
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
</html>