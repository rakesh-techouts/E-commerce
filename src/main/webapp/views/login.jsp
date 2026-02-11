<%@ page contentType="text/html; charset=UTF-8" language="java"  %>
<%@ taglib uri="jakarta.tags.core" prefix="p" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
</head>
<body>

<h2>LogIn</h2>

<div class="form-container">

    <form action="${pageContext.request.contextPath}/login" method="GET">
        <label>Email</label>
        <input type="email" name="email" required><br>

        <label>Password</label>
        <input type="password" name="password" required>

        <button type="submit">Log in</button>

        <p>I dont have Account? </p> <a href="${pageContext.request.contextPath}/views/register.jsp">Register</a>
    </form>
        <c:if test="${not empty errorMessage}">
            <p style="color:red;">${errorMessage}</p>
        </c:if>
</div>

</body>
</html>