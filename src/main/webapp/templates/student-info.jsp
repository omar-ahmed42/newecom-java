<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students - Info</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>
</head>
<body>
<div id="list-students">
    <div class="student-wrapper">
        <div class="student-id"> ID</div>
        <div class="student-name"> Name</div>
        <div class="student-n-courses"> Academic Year</div>
    </div>
    <c:forEach items="${students}" var="student">
        <div class="student-wrapper">
            <div class="student-wrapper">
                <div class="student-id"> ${student.studentId}</div>
                <div class="student-name">
                    <c:out value="${student.firstName} ${student.lastName}"></c:out>
                </div>
                <div class="student-n-courses">${student.academicYear}</div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
