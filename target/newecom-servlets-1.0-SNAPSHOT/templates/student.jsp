<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student</title>
    <link rel="stylesheet" href="../css/styles.css"/>
</head>
<body>
<center><h1>Registered Courses</h1></center>
<div id="list-courses">
    <div class="course-wrapper">
        <div class="course-name"> Code</div>
    </div>
    <c:forEach items="${registeredCourses}" var="course">
        <div id="registered-courses" class="course-wrapper">
            <div class="course-name">${course.courseCode}</div>
        </div>
    </c:forEach>
</div>

<center><h1>Passed Courses</h1></center>
<div id="list-past-courses">
    <div class="course-wrapper">
        <div class="course-code"> Code</div>
        <div class="course-name"> Grade</div>
    </div>
    <c:forEach items="${completedCourses}" var="course">
        <div id="passed-courses" class="course-wrapper">
            <c:if test="${course.grade > 50}">
                <div class="course-code">${course.courseCode}</div>
                <div class="course-name">${course.grade}</div>
            </c:if>
        </div>
    </c:forEach>
</div>
</body>
</html>
