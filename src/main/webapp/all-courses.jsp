<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All - Courses</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>
</head>
<body>

<center><h1>All Courses</h1></center>
<div id="list-courses">
<div class="course-wrapper">
    <div class="course-code">Code</div>
    <div class="course-name">Name</div>
</div>
<c:forEach items="${allCourses}" var="course">
    <div class="course-wrapper">
        <div class="course-code">${course.courseCode}</div>
        <div class="course-name">${course.courseName}</div>
    </div>
</c:forEach>
</div>
</body>
</html>
