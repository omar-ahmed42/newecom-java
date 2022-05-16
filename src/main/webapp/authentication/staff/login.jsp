<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Staff - Login</title>
</head>
<body>

<form method="post" action="${pageContext.request.contextPath}/staff/login">
    <label for="user-id">ID: </label>
    <input id="user-id" placeholder="Enter your ID" name="user-id" type="text" required/>
    <label for="user-password">Password: </label>
    <input id="user-password" placeholder="Enter your password" name="user-password" type="password" required/>
    <input type="submit" value="submit" id="submit"/>
</form>
</body>

</html>
