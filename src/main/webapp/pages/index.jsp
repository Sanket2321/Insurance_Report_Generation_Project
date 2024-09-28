<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Report Application</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div>
			<h3 class="pb-3 pt-3">Report Application</h3>
		</div>
		<form:form action="/search" modelAttribute="search" method="POST">
			<div class="row">
				<div class="col-md-4">
					<label for="PlanName">Plan Name:</label>
					<form:select path="PlanName" class="form-control">
						<form:option value="">-Select-</form:option>
						<form:options items="${names}" />
					</form:select>
				</div>
				<div class="col-md-4">
					<label for="PlanStatus">Plan Status:</label>
					<form:select path="PlanStatus" class="form-control">
						<form:option value="">-Select-</form:option>
						<form:options items="${status}" />
					</form:select>
				</div>
				<div class="col-md-4">
					<label for="Gender">Gender:</label>
					<form:select path="Gender" class="form-control">
						<form:option value="">-Select-</form:option>
						<form:option value="Male">Male</form:option>
						<form:option value="Female">Female</form:option>
					</form:select>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<label for="planStartDate">Start Date:</label>
					<form:input type="date" path="startDate" class="form-control" />
				</div>
				<div class="col-md-6">
					<label for="planEndDate">End Date:</label>
					<form:input type="date" path="endDate" class="form-control" />
				</div>
			</div>
			<div class="row pt-3">
				<div class="col-md-2">
					<a href="/" class="btn btn-secondary">Reset</a>
				</div>
				<div class="col-md-10">
					<input type="submit" value="Search" class="btn btn-primary" />
				</div>
			</div>
		</form:form>
		<hr />
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>Holder Name</th>
					<th>Gender</th>
					<th>Plan Names</th>
					<th>Plan Status</th>
					<th>Start Date</th>
					<th>End Date</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${plans}" var="plan" varStatus="index">
					<tr>
						<td>${index.count}</td>
						<td>${plan.citizenname}</td>
						<td>${plan.gender}</td>
						<td>${plan.planName}</td>
						<td>${plan.planStatus}</td>
						<td>${plan.planStartDate}</td>
						<td>${plan.planEndDate}</td>
					</tr>
				</c:forEach>
				<c:if test="${empty plans}">
					<td colspan="7" style="text-align: center">No Record found</td>
				</c:if>
			</tbody>
		</table>
		
		<hr />
		<div class="d-flex justify-content-start">
			<a href="excel" class="btn btn-success me-2">Export to Excel</a>
			<a href="pdf" class="btn btn-danger">Export to PDF</a>
		</div>
		
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>
