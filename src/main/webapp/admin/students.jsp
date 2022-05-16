<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List - Students</title>
    <link rel="stylesheet" href="../css/styles.css"/>
</head>
<body>

<div id="list-students">
    <div class="student-wrapper">
        <div class="student-id"> ID </div>
        <div class="student-name"> Name </div>
        <div class="student-n-courses"> Number Of Passed Courses</div>
    </div>

    <c:forEach items="${students}" var="student">
        <a class="student-anchor" href="${pageContext.request.contextPath}/student/page?id=${student.studentId}">
        <div class="student-wrapper">
            <div class="student-id"> ${student.studentId} </div>
            <div class="student-name"> <c:out value="${student.firstName}"></c:out> <c:out value="${student.lastName}"></c:out> </div>
            <div class="student-n-courses"> ${student.completedCoursesByStudentId.size()}</div>
        </div>
        </a>
    </c:forEach>
</div>

</body>
</html>
