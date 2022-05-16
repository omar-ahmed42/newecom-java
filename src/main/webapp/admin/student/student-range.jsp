<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students - Range</title>
</head>
<body>

<h4>Range</h4>
<form method="post" action="${pageContext.request.contextPath}/admin/student-range">
    <label for="range-min">Min</label>
    <input id="range-min" name="range-min"/>
    <span width="5px"></span>
    <label for="range-max">Max</label>
    <input id="range-max" name="range-max"/>
    <input type="submit" value="submit">
</form>

</body>
</html>
