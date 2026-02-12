<%@ page contentType="text/html; charset=UTF-8" import="com.techouts.entity.*, com.techouts.dao.*" %>
<%
        User user=session.getSession(false).getAttribute("user");
        int productId=Integer.parseInt(request.getParameter(""));
        Product product=ProductDao.getProduct(productId);
        request.setAttribute("product",product);

%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Checkout</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <style>
    body {
      background-color: #f8f9fa;
      display: flex;
      justify-content: center;
      align-items: center;
      min-height: 100vh;
    }

    .checkout-container {
      width: 100%;
      max-width: 600px;
      padding: 20px;
      background: #fff;
      border-radius: 10px;
      box-shadow: 0 5px 20px rgba(0,0,0,0.1);
    }

    .section-box {
      background: #f1f3f5;
      padding: 20px;
      margin-bottom: 20px;
      border-radius: 8px;
    }

    .section-box h4 {
      margin-bottom: 15px;
    }

    .form-label {
      font-weight: 500;
    }

    .btn-primary {
      width: 100%;
    }

    .quantity-input {
      width: 80px;
    }
  </style>
</head>
<body>
<div class="checkout-container">
  <h2 class="mb-4 text-center">Checkout</h2>
  <!-- Item Details Box -->
  <div class="section-box">
  <% Product product=(Product)request.getAttribute("product");
  %>
    <h4>Item Details</h4>
    <p><b><%= product.getProductName() %></b></p>
    <p>Price: $<%= product.getPrice() %></p>

    <div class="mb-3">
      <label class="form-label">Quantity</label>
      <input type="number" id="qtyInput" class="form-control quantity-input" value="<%= qty %>" min="1" onchange="updateTotal()">
    </div>

    <p><b>Total: $<span id="totalAmount"><%= total %></span></b></p>
  </div>

  <!-- Shipping & Payment Box -->
  <div class="section-box">
    <form action="${pageContext.request.contextPath}/place-order" method="post">
      <h4 class="mb-3">Shipping & Payment</h4>

      <div class="mb-3">
        <label class="form-label">Shipping Address</label>
        <input type="text" name="address" class="form-control" required>
      </div>

      <div class="mb-3">
        <label class="form-label">Payment Method</label>
        <select name="paymentMethod" class="form-select" required>
          <option value="">Select</option>
          <option value="COD">Cash on Delivery</option>
          <option value="UPI">UPI</option>
          <option value="Card">Credit/Debit Card</option>
        </select>
      </div>

      <!-- Hidden fields to pass quantity and total -->
      <input type="hidden" name="qty" id="qtyHidden" value="<%= qty %>">
      <input type="hidden" name="confirm" value="true">

      <button type="submit" class="btn btn-primary">Place Order</button>
    </form>
  </div>

  <!-- Continue Shopping -->
  <div class="text-center">
    <a href="dashboard.jsp" class="btn btn-link">Continue Shopping</a>
  </div>

</div>

<script>
  // Initialize total on page load
  updateTotal();
</script>

</body>
</html>
