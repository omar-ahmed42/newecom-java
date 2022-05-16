<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students - Creation</title>
    <link rel="stylesheet" href="../../css/styles.css"/>

</head>
<body>
<c:if test="${success == true}">
    <% request.removeAttribute("success");%>
    <div><h1>SUCCESS</h1></div>
</c:if>
<c:if test="${failure == true}">
    <% request.removeAttribute("failure");%>
    <div><h1>FAILURE</h1></div>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/admin/student-creation-action">
    <label for="first-name-input">First Name: </label>
    <input id="first-name-input" name="first-name-input" type="text" required/>
    <br>
    <label for="last-name-input">Last Name: </label>
    <input id="last-name-input" name="last-name-input" type="text" required/>
    <br>
    <label for="dob-input">Date of birth: </label>
    <input id="dob-input" name="dob" type="date" required/>
    <br>
    <select id="academic-year-input" name="academic-year" required>
        <option value="FIRST">First</option>
        <option value="SECOND">Second</option>
        <option value="THIRD">Third</option>
        <option value="FOURTH">Fourth</option>
    </select>
    <input id="submit" type="submit" value="submit"/>
</form>
</body>
</html>
