<%@ page contentType="text/html; charset=UTF-8" language="java"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>
<body>

<h2>Register</h2>

<div class="form-container">

    <form action="${pageContext.request.contextPath}/register" method="post">

        <label>Name</label>
        <input type="text" name="username" required><br><br>

        <label>Email</label>
        <input type="email" name="email" required><br><br>

        <label>Phone Number</label>
        <input type="tel" name="phone" required><br><br>

        <label>Password</label>
        <input type="password" name="password" required><br><br>

        <label>Confirm Password</label>
        <input type="password" name="confirmPassword" required><br><br>

        <button type="submit" class="btn">Sign Up</button><br>

        Already a User? <a href="login.jsp"> Login</a>
    </form>

</div>

</body>
</html>