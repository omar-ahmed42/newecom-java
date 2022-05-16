<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course Creation</title>
    <link rel="stylesheet" href="../../css/styles.css"/>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/admin/course/creation">
    <label for="course-code-input">Course Code: </label>
    <input type="text" id="course-code-input" name="course-code" required/>
    <br><br>
    <label for="course-name-input">Course Name: </label>
    <input type="text" id="course-name-input" name="course-name" required/>
    <br><br>
    <label for="prerequisite-input">Prerequisite Academic Year: </label>
    <select id="prerequisite-input" name="academic-year" required>
        <option value="FIRST">First</option>
        <option value="SECOND">Second</option>
        <option value="THIRD">Third</option>
        <option value="FOURTH">Fourth</option>
    </select>
    <br><br>
    <input type="submit" value="submit" id="submit"/>
</form>

</body>
</html>
