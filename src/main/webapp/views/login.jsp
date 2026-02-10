<%@ page contentType="text/html; charset=UTF-8" language="java"  %>
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

        <p>I dont have Account? </p> <a href="register.jsp"> register</a>
    </form>
</div>

</body>
</html>