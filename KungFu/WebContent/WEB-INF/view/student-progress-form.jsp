<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Achievement Form</title>

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
		
						<table>							
							<tr>
								<th>Rank</th>
								<th>level</th>
								<th>Date Awarded</th>
								<th>Award Belt</th>
							</tr>
							<c:forEach var="tmpProgressSummary" items="${theStudentProgress}" >
							<tr>
								<td>${tmpProgressSummary.rank}</td>
								<td>${tmpProgressSummary.level}</td>
								<td>${tmpProgressSummary.dateAwarded}</td>
								<td>
								<c:if test="${tmpProgressSummary.dateAwarded==null}">
									<form:form action="awardBelt" method="POST">
										<input type="hidden" name="studentId" value=${tmpProgressSummary.studentId} >
										<input type="hidden" name="progressId" value=${tmpProgressSummary.progressId} >										
										<input type="submit" value="Award" class="add-button" />
									</form:form>
								</c:if>
								</td>
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