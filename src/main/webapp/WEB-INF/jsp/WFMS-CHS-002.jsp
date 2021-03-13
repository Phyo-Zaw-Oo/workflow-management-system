<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Workflow Management System</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet"
	href="plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<link rel="stylesheet"
	href="plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<link rel="stylesheet" href="plugins/jqvmap/jqvmap.min.css">
<link rel="stylesheet" href="dist/css/adminlte.min.css">
<link rel="stylesheet"
	href="plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<link rel="stylesheet"
	href="plugins/daterangepicker/daterangepicker.css">
<link rel="stylesheet" href="plugins/summernote/summernote-bs4.min.css">

</head>
<body class="hold-transition sidebar-mini layout-fixed">

	<c:choose>
		<c:when test="${sessionScope.sesUser.role.roleName=='Admin'}">
			<jsp:include page="WFMS-ASB-001.jsp" />
		</c:when>
		<c:when test="${sessionScope.sesUser.role.roleName=='Staff'}">
			<jsp:include page="WFMS-SSB-001.jsp" />
		</c:when>
		<c:when
			test="${sessionScope.sesUser.role.roleName=='Project Manager'}">
			<jsp:include page="WFMS-PSB-001.jsp" />
		</c:when>
		<c:when
			test="${sessionScope.sesUser.role.roleName=='Head of Department'}">
			<jsp:include page="WFMS-PSB-001.jsp" />
		</c:when>
		<c:when test="${sessionScope.sesUser.role.roleName=='HR'}">
			<jsp:include page="WFMS-HSB-001.jsp" />
		</c:when>
	</c:choose>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<div class="content-wrapper">
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">Leave Status</h1>
						</div>
					</div>
				</div>
			</div>
			<section class="content">
			<div class="container-fluid">
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<table id="status-table" class="table table-bordered">
									<c:if test="${leaveStatusList!=null}">
										<thead>
											<tr>
												<th class="text-center align-middle">No.</th>
												<th class="text-center align-middle">Request Form</th>
												<th>Assign To</th>
												<th>Approver Name</th>
												<th>Approver Role</th>
												<th>Leave Type</th>
												<th>Status</th>
												<th>Start Date</th>
												<th>End Date</th>
												<th style="width: 15%">Comment</th>
												<th style="width: 10%">Created Date</th>
											</tr>
										</thead>
									</c:if>

									<tbody>
										<c:forEach var="list" items="${leaveStatusList}" varStatus="s">
											<tr>
												<td>${s.count}</td>
												<td>${list.leaveForm.leaveFormId}</td>
												<td>${list.leaveForm.assignTo.name}</td>
												<td>${list.approver.name}</td>
												<td>${list.approver.role.roleName}</td>
												<td>${list.leaveForm.leaveType.leaveType}</td>
												<c:choose>
														<c:when test="${list.status=='Approved'}">
															<td><span class="badge bg-primary">${list.status}</span></td>
														</c:when>
														<c:when test="${list.status=='Rejected'}">
															<td><span class="badge bg-danger">${list.status}</span></td>
														</c:when>
														<c:when test="${list.status=='Sendback'}">
															<td><span class="badge bg-warning"><a href="sendBackLeaveFormSetUp?id=${list.leaveForm.leaveFormId}"><u>${list.status}</u></a></span></td>
														</c:when>
														<c:when test="${list.status=='Pending'}">
															<td><span class="badge bg-warning">${list.status}</span></td>
														</c:when>
														<c:otherwise>
															<td><span class="badge bg-success">${list.status}</span></td>
														</c:otherwise>
													</c:choose>
												<td>${list.leaveForm.startDate}</td>
												<td>${list.leaveForm.endDate}</td>
												<td>${list.comment}</td>
												<td>${list.createdDate}</td>
											</tr>

										</c:forEach>

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			</section>

			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">Overtime Status</h1>
						</div>
					</div>
				</div>
			</div>
			<section class="content">
			<div class="container-fluid">
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<table class="table table-bordered">
									<c:if test="${otStatusList!=null}">
										<thead>
											<tr>
												<th class="text-center align-middle">No.</th>
												<th class="text-center align-middle">Request Form</th>
												<th>Approver Name</th>
												<th>Approver Role</th>
												<th>Overtime Type</th>
												<th>Status</th>
												<th>Start Time</th>
												<th>End Time</th>
												<th style="width: 15%">Comment</th>
												<th style="width: 10%">Created Date</th>
											</tr>
										</thead>
									</c:if>
									<tbody>
										<c:forEach var="list" items="${otStatusList}" varStatus="s">
											<tr>
												<td>${s.count}</td>
												<td>${list.overtimeForm.overtimeFormId}</td>
												<td>${list.approver.name}</td>
												<td>${list.approver.role.roleName}</td>
												<td>${list.overtimeForm.overtimeType.overtimeType}</td>
												<c:choose>
														<c:when test="${list.status=='Approved'}">
															<td><span class="badge bg-primary">${list.status}</span></td>
														</c:when>
														<c:when test="${list.status=='Rejected'}">
															<td><span class="badge bg-danger">${list.status}</span></td>
														</c:when>
														<c:when test="${list.status=='Sendback'}">
															<td><span class="badge bg-warning"><a href="sendBackOTFormSetUp?id=${list.overtimeForm.overtimeFormId}"><u>${list.status}</u></a></span></td>
														</c:when>
														<c:when test="${list.status=='Pending'}">
															<td><span class="badge bg-warning">${list.status}</span></td>
														</c:when>
														<c:otherwise>
															<td><span class="badge bg-success">${list.status}</span></td>
														</c:otherwise>
													</c:choose>
												<td>${list.overtimeForm.startTime}</td>
												<td>${list.overtimeForm.endTime}</td>
												<td>${list.comment}</td>
												<td>${list.createdDate}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			</section>
		</div>
	</div>
	<script src="plugins/jquery/jquery.min.js"></script>
	<script src="plugins/jquery-ui/jquery-ui.min.js"></script>
	<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="plugins/chart.js/Chart.min.js"></script>
	<script src="plugins/sparklines/sparkline.js"></script>
	<script src="plugins/jqvmap/jquery.vmap.min.js"></script>
	<script src="plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
	<script src="plugins/jquery-knob/jquery.knob.min.js"></script>
	<script src="plugins/moment/moment.min.js"></script>
	<script src="plugins/daterangepicker/daterangepicker.js"></script>
	<script
		src="plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
	<script src="plugins/summernote/summernote-bs4.min.js"></script>
	<script
		src="plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
	<script src="dist/js/adminlte.js"></script>
	<script src="dist/js/common.js"></script>
	<script src="dist/js/pages/dashboard.js"></script>
	<script src="dist/js/demo.js"></script>
	
</body>
</html>