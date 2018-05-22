<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>List Students</title>

<style>
		.result {color:red}
</style>
	
	
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
		
			<input type="button" value="Add Student"
				onClick="window.location.href='addStudent'; return false;"
				class="add-button"
			/>
				
			<!--  add a search box -->
			<form:form action="searchStudent" method="POST">
				Search Student: <input type="text" name="theSearchString" />
				
				<input type="submit" value="Search" class="add-button" />
			</form:form>
			
			<div>
			
			<%
				if(request.getParameter("result")!=null){
					System.out.println(request.getParameter("result"));
					out.print("<i><label Class=\"result\">"+request.getParameter("result")+"</label></i>");
				}
			%>

			
			</div>
			
			<table>
				<tr>
					<th>ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<!-- <th>DOB</th>
					<th>Address</th> -->
					<th>Action</th>
				</tr>
			
				<c:forEach var="tmpStudent" items="${students}" > 
				
				<c:url var="studentDetails" value="/student/showStudentDetails">
					<c:param name="studentId" value="${tmpStudent.id}"></c:param>
				</c:url>				

				<%-- <c:url var="updateLink" value="/student/showFormForUpdate">
					<c:param name="studentId" value="${tmpStudent.id}"></c:param>
				</c:url>
				
				<c:url var="deleteLink" value="/student/delete">
					<c:param name="studentId" value="${tmpStudent.id}"></c:param>
				</c:url> --%>
				
				<c:url var="recordAttendance" value="/student/recordAttendance">
					<c:param name="studentId" value="${tmpStudent.id}"></c:param>
				</c:url>
				
				<c:url var="showFinanceForm" value="/student/showFinanceForm">
					<c:param name="studentId" value="${tmpStudent.id}"></c:param>
				</c:url>
				<c:url var="showAchievementForm" value="/student/showAchievementForm">
					<c:param name="studentId" value="${tmpStudent.id}"></c:param>
				</c:url>
				
				<tr> 
					<td><a href="${studentDetails}">${tmpStudent.id}</a></td>
					<td>${tmpStudent.firstName}</td>
					<td>${tmpStudent.lastName}</td>
					<td>${tmpStudent.email}</td>
					<%-- <td>${tmpStudent.dob}</td>
					<td>${tmpStudent.doorNo},${tmpStudent.street},${tmpStudent.city},${tmpStudent.province},${tmpStudent.postalCode}</td> --%>
					<td>
						<a href="${recordAttendance}"
							onClick="return confirm('Are you sure you want to record attendance for this student?');">
							Record Attendance</a>
						|
						<a href="${showFinanceForm}">Add Finance</a>
						|
						<a href="${showAchievementForm}">Show/Add Achievement</a>
					</td>
				</tr>
				</c:forEach>
			</table>
			
		</div>
	</div>

</body>
</html>