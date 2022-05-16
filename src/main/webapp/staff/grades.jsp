
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Grade assignment</title>
    <link rel="stylesheet" href="../css/styles.css"/></head>
<body>
<center><h1>Assign a grade to a student</h1></center>
<form method="post">
    <label for="student-id-input">ID: </label>
    <input type="text" id="student-id-input" name="completed-course-id" required/>
    <label for="student-grade-input">Grade:</label>
    <input type="text" id="student-grade-input" name="completed-course-grade" required/>
    <input type="submit" value="submit" id="submit"/>
</form>
</body>
</html>
