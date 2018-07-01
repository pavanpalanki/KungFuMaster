<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html style="margin-left:0 ;margin-right:0">
<head>
<title>List Students</title>

<style>
        
		.result {color:red}
		body{margin-bottom:100px !important;background:#000}
</style>
	
	
<link type="text/css"
	rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
	
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
</head>
<body style="background-color:#000;display:table;width:100%">
<img src="https://png.pngtree.com/thumb_back/fh260/back_pic/03/57/22/6157a051c9146a3.jpg" style="margin-bottom:50px;width: 100%;height: 260px;">
<div class="table-bkg">
	<div id="wrappper" style="position:relative">
		<div id="header" style="width:35%;background:transparent !important;padding:15px !important;position: absolute;top: -233px;left: 460px;">
			<h2 style="color:rgb(253,233,30);font-size:50px">KungFu Academy</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
		<div class="col-md-10 col-md-offset-1">
			<input type="button" value="Add Student"
				onClick="window.location.href='addStudent'; return false;"
				class="add-button pull-left"
			/>
				
			<!--  add a search box -->
			<form:form action="searchStudent" method="POST" class="pull-right">
				<span style="color:#fff">Search Student:</span> <input type="text" name="theSearchString" style="padding: 3px;border-radius: 5px;border: 1px solid #000;margin-right: 5px;"/>
				
				<input type="submit" value="Search" class="add-button"  />
			</form:form>
			</div>
			<div>
			
			<%
				if(request.getParameter("result")!=null){
					System.out.println(request.getParameter("result"));
					out.print("<i><label Class=\"result col-md-10 col-md-offset-1\">"+request.getParameter("result")+"</label></i>");
				}
			%>

			
			</div>
			
			<table class="col-md-10 col-md-offset-1">
				<tr>
					<th style="background: none repeat scroll 0 0 #333;text-align:center">ID</th>
					<th style="background: none repeat scroll 0 0 #333;text-align:center">First Name</th>
					<th style="background: none repeat scroll 0 0 #333;text-align:center">Last Name</th>
					<th style="background: none repeat scroll 0 0 #333;text-align:center">Email</th>
					<!-- <th>DOB</th>
					<th>Address</th> -->
					<th style="background: none repeat scroll 0 0 #333">Action</th>
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
	</div>

</body>
</html>