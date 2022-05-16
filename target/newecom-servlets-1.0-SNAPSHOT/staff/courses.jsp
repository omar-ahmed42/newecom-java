<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Courses</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>
</head>
<body>
<div id="wrapper">
    <div id="list-courses">
        <div class="course-wrapper">
            <div class="course-code"> Code</div>
            <div class="course-name"> Name</div>
        </div>
        <c:forEach items="${courses}" var="course">
            <a class="student-anchor" href="${pageContext.request.contextPath}/student-info?courseCode=${course.courseCode}">
                <div class="course-wrapper">
                    <div class="course-code"> ${course.courseCode} </div>
                    <div class="course-name"> ${course.courseName} </div>
                </div>
            </a>
        </c:forEach>
    </div>
</div>
</body>
</html>
