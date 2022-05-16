<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Staff - Creation</title>
    <link rel="stylesheet" href="../../css/styles.css"/>

</head>
<body>

<form method="post" action="${pageContext.request.contextPath}/admin/staff-creation">
    <label for="first-name-input">First Name: </label>
    <input id="first-name-input" name="first-name" type="text" required/>
    <br>
    <label for="last-name-input">Last Name: </label>
    <input id="last-name-input" name="last-name" type="text" required/>
    <br>
    <label for="dob-input">Date of birth: </label>
    <input id="dob-input" name="dob" type="date" required/>
    <br>
    <input id="submit" type="submit" value="submit"/>
</form>

</body>
</html>
