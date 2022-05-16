<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Courses</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
<center><h1>Courses</h1></center>
<div id="list-courses">
    <div class="course-wrapper">
        <div class="course-code"><b>Course Code</b></div>
        <div class="course-name"><b>Course Name</b></div>
    </div>
    <c:forEach items="${courses}" var="course">
        <div class="course-wrapper">
            <div class="course-code"> ${course.courseCode} </div>
            <div class="course-name"> ${course.courseName} </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
