<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Details</title>

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
<label><b><u>Student Details</u></b></label><br/><br/>
			<table>
					<tr><td align="left">Student Id: ${theStudent.id}</td> </tr>
					<tr><td align="left">Student Name: ${theStudent.firstName} ${theStudent.lastName} </td></tr>
					<tr><td align="left">Student dob: ${theStudent.dob} </td></tr>
					<tr><td align="left">Student joinDate: ${theStudent.joinDate} </td></tr>
					<tr><td align="left">Student	mobile: ${theStudent.mobile} </td></tr>
					<tr><td align="left">Student email: ${theStudent.email}</td></tr>
					<tr><td align="left">Student Address: ${theStudent.doorNo}, ${theStudent.street}, ${theStudent.city}, ${theStudent.province}, ${theStudent.postalCode}</td></tr>
					<tr><td align="left">Primary Contact Name: ${theStudent.primaryContact}</td></tr>
					<tr><td align="left">Secondary Contact Name: ${theStudent.secondaryContact}</td></tr>
			</table>
			<label>
				<a href="${pageContext.request.contextPath}/student/list" style="margin-top: 45px;width: 120px;float: left;">Back to list</a>
			</label>
			<label>		
				<a href="${pageContext.request.contextPath}/student/getStudentFinances" style="margin-top: 45px;width: 120px;float: left;">Finance Details</a>
			</label>
			<label>
				<a href="${pageContext.request.contextPath}/student/getStudentAttendance" style="margin-top: 45px;width: 120px;float: left;">Attendance Details</a>
			</label>		
				<%--<br/><br/><br/><label><b>Financial Details</b></label>
				<table>
				<tr>
					
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
				</tr>
				 <br/><label><b>Attendance Details</b></label>	
				<tr>
					<table>

							<tr>
								<th>Level</th>
								<th>Rank</th>
								<th>Level</th>
								<th>Time</th>
								<th>DateAttended</th>
							</tr>
							<c:forEach var="tmpAttendance" items="${theStudentAttendance}" >
							<tr>
								<td>${tmpAttendance.level}</td>
								<td>${tmpAttendance.rank}</td>
								<td>${tmpAttendance.day}</td>
								<td>${tmpAttendance.time}</td>
								<td>${tmpAttendance.dateAttended}</td>
							</tr>
							</c:forEach>
					</table>
				</tr> --%>
			</table>
			
		</div>
	</div>

</body>
</html>