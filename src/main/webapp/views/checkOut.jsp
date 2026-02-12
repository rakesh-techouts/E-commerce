<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import = "com.techouts.entity.*,com.techouts.dao.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>chekout Page</title>
</head>
<body>
    <%
        String msg = request.getParameter("msg");
        Users user=(Users)request.getSession(false).getAttribute("user");
        List<CartItem> items=null;
       if ("BuyNow".equalsIgnoreCase(msg)) {
            items=new ArrayList<>();
            int productId=Integer.parseInt(request.getParameter("id"));
            items.add(new CartItem(ProductsDao.getProduct(productId),1));
        }else{
            items=CartDao.getCart(user).getCartItems();
        }
        request.setAttribute("items",items);
    %>
        <h3>Hello <%= user.getUsername()%> Please Check your cart</h3>
        <c:if test="${items.size()>0}">
                <c:set var="total" value="0"/>
                <table>
                    <thead>
                        <tr>
                            <td>Name</td>
                            <td>Price</td>
                            <td>Quantity</td>
                            <td>Subtotal</td>
                        </tr>
                    </thead>
                    <c:forEach var="item" items="${items}">
                    <tbody>
                            <td>${item.getProduct().getProductName()}</td>
                            <td>${item.getProduct().getPrice()}</td>
                            <td>${item.getQuantity()}</td>
                            <td>${item.getProduct().getPrice()*item.getQuantity()}</td>
                            <c:set var="total" value="${total+(item.getProduct().getPrice()*item.getQuantity())}"/>
                    </tbody>
                    </c:forEach>
                </table>
                <form action="${pageContext.request.contextPath}/checkOut" method="post">
                Address: <input type="text" name="address" required>
                   <div class="mb-3">
                          <label class="form-label">Payment Method</label>
                            <select name="paymentMethod" class="form-select" required>
                              <option value="">Select</option>
                               <option value="COD">Cash on Delivery</option>
                               <option value="UPI">UPI</option>
                                <option value="Card">Credit/Debit Card</option>
                                </select>
                      </div>
                <h4>Total Price: ${total}</h4>
                <input type="hidden" name="totalPrice" value="${total}">
                <button type="submit" class="btn btn-primary w-100">Place Order</button>
          </form>
        </c:if>
</body>
</html>