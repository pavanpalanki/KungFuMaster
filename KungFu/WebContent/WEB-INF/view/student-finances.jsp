<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Finances</title>

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
		<c:forEach var="tmpAccountSummary" items="${theAccountSummary}" >
			<c:set var = "studentId" scope = "session" value = "${tmpAccountSummary.studentId}"/>
		</c:forEach>
			<form:form action="searchFinanceRange" method="POST">
			<input type="hidden" name="studentId" value=${studentId} >
				From: <input type="date" name="fromDate" />
				To: <input type="date" name="toDate" />				
				<input type="submit" value="Search Finances" class="add-button" />
			</form:form>
		
						<table>							
							<tr>
								<th>Category</th>
								<th>Sub Category</th>
								<th>fees</th>
								<th>Date Paid</th>
							</tr>
							<c:forEach var="tmpAccountSummary" items="${theAccountSummary}" >
							<tr>
								<td>${tmpAccountSummary.category}</td>
								<td>${tmpAccountSummary.subCategory}</td>
								<td>${tmpAccountSummary.fees}$</td>
								<td>${tmpAccountSummary.datePaid}</td>
							</tr>
							</c:forEach>
						</table>	
						
						<label> <a
					href="${pageContext.request.contextPath}/student/list"
					style="margin-top: 45px; width: 120px; float: left;">Back to
						list</a>
				</label>
		
		</div>
	</div>

</body>
</html>