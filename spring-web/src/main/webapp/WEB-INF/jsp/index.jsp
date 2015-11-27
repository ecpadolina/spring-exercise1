<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Spring Activity - Person Management</title>
</head>
<body>
	<jsp:include page="headers.jsp"/>
	<h1>Person Management</h1>
	<form method="POST">
	Sort By: <select name="column">
			<option value="id">Id</option>
			<option value="name.lastName">Last Name</option>
			<option value="gwa">Gwa</option>
		</select>
		<select name="order">
			<option value="1">Ascending</option>
			<option value="2">Descending</option>
		</select>
		<select name="role">
			<c:forEach var="r" items="${roleList}">
				<option value="${r.roleId}">${r.roleType}</option>
			</c:forEach>
		</select>
		<button type="submit">Search</button>
	</form>
	<br>
	<table>
		<thead>
			<tr>
	            <th>ID</th>
	            <th><spring:message code="label.name"/></th>
	            <th><spring:message code="label.birthday"/></th>
	            <th><spring:message code="label.grade"/></th>
	            <th><spring:message code="label.action"/></th>
			</tr>
		</thead>
		<tbody>
	         <c:forEach var="p" items="${personList}">
				<tr>
					<td><c:out value="${p.id}" /></td>
					<td>${p.firstName} ${p.lastName}</td>
					<td>${p.birthday}</td>
					<td>${p.gwa}</td>
					<td><button onclick="location.href='/deletePerson?id=${p.id}'">Delete</button><button onclick="location.href='/editPerson?id=${p.id}'">Edit</button></td>
				</tr>
			</c:forEach>     
		</tbody>
	</table>
</body>
</html>
