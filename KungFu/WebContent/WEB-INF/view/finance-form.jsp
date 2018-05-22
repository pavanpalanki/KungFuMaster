<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Finance Form</title>

<link type="text/css"
	rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
	
</head>
<body>

<div id="wrappper">
		<div id="header">
			<h2>KungFu Master</h2>
		</div>
</div>

<div id="container">
		<div id="content">
		
		<div>
			<h2>Welcome ${student.firstName} ${student.lastName}</h2>
		</div>	

		
		<table>
				<tr>
					<th>Category</th>
					<th>Sub Category</th>
					<th>Fees</th>
					<th>Description</th>
					<th>Action</th>
				</tr>
			
				<c:forEach var="tmpService" items="${services}" >	
				
				<c:url var="addFinance" value="/student/addFinance">
					<c:param name="studentId" value="${student.id}"></c:param>
					<c:param name="serviceId" value="${tmpService.id}"></c:param>
				</c:url>		
				
				<tr> 
					<td>${tmpService.category}</td>
					<td>${tmpService.subCategory}</td>
					<td>${tmpService.fees}</td>
					<td>${tmpService.description}</td>
					<td>
						<a href="${addFinance}">Add</a> 
					</td>
				</tr>
				</c:forEach>					
			</table>
			<label>
				<a href="${pageContext.request.contextPath}/student/list" style="margin-top: 45px;width: 120px;float: left;">Back to list</a>
			</label>
		</div>
</div>

</body>
</html>