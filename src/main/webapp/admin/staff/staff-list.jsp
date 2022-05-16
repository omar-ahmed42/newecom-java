<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Staff - List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>
</head>
<body>
<div id="list-students">
    <div class="student-wrapper">
        <div class="student-id">ID</div>
        <div class="student-name">Name</div>
        <div class="student-n-courses"># Of Taught Courses</div>
    </div>
    <c:forEach items="${allStaff}" var="staff">
    <a class="student-anchor" href="${pageContext.request.contextPath}/staff/page?id=${staff.staffId}">
    <div class="student-wrapper">
            <div class="student-id">${staff.staffId}</div>
            <div class="student-name">
                <c:out value="${staff.firstName} ${staff.lastName}"></c:out>
            </div>
            <div class="student-n-courses">${staff.teachesCoursesByStaffId.size()}</div>
        </div>
    </a>
    </c:forEach>

</div>
</body>
</html>
