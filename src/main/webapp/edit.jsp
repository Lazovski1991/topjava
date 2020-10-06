<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 06.10.2020
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>AddMeals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<br>
<h1>Edit meal</h1>
<br>
<form action="/topjava/meals" method="post">
    <input type="hidden" name="id" value=<%=request.getParameter("id")%>>
    <div>
        <label for="dateDD">DateTime:</label>
        <input type="datetime-local" id="dateDD" name="date">
    </div>
    <br>
    <div>
        <label for="description">Description:</label>
        <input type="text" id="description" name="description" size="30px">
    </div>
    <br>
    <div>
        <label for="calories">Calories:</label>
        <input type="number" id="calories" name="calories" size="15px">
    </div>
    <br>
    <input type="submit"  value="Save">
    <form action="/topjava/meals">
        <input type="submit"  formmethod="get" value="Cancel">
    </form>
    <p></p>
</form>
</body>
</html>
</body>
</html>
