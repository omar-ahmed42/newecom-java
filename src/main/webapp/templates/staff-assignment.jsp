<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Staff - Assignment</title>
</head>
<body>

<form method="post" action="${pageContext.request.contextPath}/admin/staff-assignment">
    <div>Courses:</div>
    <select id="eligible-courses" name="eligible-courses" multiple required>
        <c:forEach items="${eligibleCourses}" var="course">
            <option value="${course.courseCode}">
                    ${course.courseName}
            </option>
        </c:forEach>
    </select>
    <input id="submit" type="submit" value="submit"/>
</form>


</body>
</html>
