<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 02.10.2020
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html>
<style type="text/css">

    TABLE {
        border-collapse: collapse;
    }
    TH, TD{
        border: 2px solid black;
    }
    TD {
        height: 30px;
    }
    .head {
        text-align: center;
        font-weight: bold;
    }
</style>

<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h1>Meals</h1>
<div><a href="${pageContext.request.contextPath}/addMeals.html">Add Meal</a></div>
<br>
<table>
    <tr class="head">
        <td width="150px ">Date</td>
        <td width="300px ">Description</td>
        <td width="100px ">Calories</td>
        <td width="100px "></td>
        <td width="100px "></td>
    </tr>

    <c:forEach var="meal" items="${mealToList}">
    <tr <c:choose>
        <c:when test="${meal.isExcess()==true}">
            style="color: red" ;
        </c:when>
        <c:otherwise>
            style="color: green" ;
        </c:otherwise>
    </c:choose>
        >
        <javatime:format value="${meal.dateTime}" pattern="yyyy-MM-dd HH:mm" var="cleanDate"/>
        <td text align="center">${cleanDate}</td>
        <td>${meal.getDescription()}</td>
        <td>${meal.getCalories()}</td>
        <td><a href="${pageContext.request.contextPath}/edit.jsp?id=${meal.getId()}">Update</a></td>
        <td><a href="${pageContext.request.contextPath}/meals?id=${meal.getId()}">Delete</a></td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
