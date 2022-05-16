<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>List - Courses</title>
    <link rel="stylesheet" href="../css/styles.css"/>
</head>
<body>
<center><h1>Current Courses</h1></center>
<div id="list-courses">
    <div class="course-wrapper">
        <div class="course-name"> Code </div>
    </div>
    <c:forEach items="${registeredCoursesForStudent}" var="course">
    <div class="course-wrapper">
        <div class="course-name"> ${course.courseCode} </div>
    </div>
    </c:forEach>
</div>

<center><h1>Past Courses</h1></center>
<div id="list-past-courses">
    <div class="course-wrapper">
        <div class="course-code"> Code </div>
        <div class="course-name"> Grade </div>
    </div>
    <c:forEach items="${completedCoursesForStudent}" var="course">
    <div class="course-wrapper">
        <div class="course-code"> ${course.courseCode} </div>
        <div class="course-name">${course.grade}</div>
    </div>
    </c:forEach>
</div>
</body>
</html>
