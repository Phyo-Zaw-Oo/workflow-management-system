<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="dist/css/adminlte.min.css">
<link rel="stylesheet"
	href="plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<link rel="stylesheet"
	href="plugins/daterangepicker/daterangepicker.css">
<link rel="stylesheet" href="plugins/summernote/summernote-bs4.min.css">
<link
	href="https://cdn.datatables.net/1.10.23/css/jquery.dataTables.min.css"
	rel="stylesheet">
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

	<div class="content-wrapper">
		<div class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1 class="m-0">Staff Overtime Record List</h1>
					</div>
				</div>
			</div>
		</div>
		<div class="search-form content"
			style="background: #fff; margin: 16px; border: 1px solid #ddd; border-radius: 4px; padding: 16px;">
			<label id="message" style="color: red;">${err}</label> 
			<form:form action="checkStaffOt" method="post"
				modelAttribute="overtimeForm">
				<div class="row">
					<div class="col-2">
						<div class="form-group">
							<label for="exampleInputEmail1">Name</label>
							<form:input class="form-control" placeholder="Enter Name"
								path="applicant" />
						</div>
					</div>
					<div class="col-2">
						<div class="form-group">
							<label for="exampleInputEmail1">Department</label>
							<form:select path="department" itemLabel="departmentName"
								class="form-control" itemValue="departmentId">
								<form:option value="">--Select--</form:option>
								<form:options items="${deptList}" itemLabel="departmentName"
									itemValue="departmentId" />
							</form:select>
						</div>
					</div>
					<div class="col-2">
						<div class="form-group">
							<label for="exampleInputEmail1">Overtime Type</label>
							<form:select id="expenseType" itemValue="overtimeTypeId"
								itemLabel="overtimeType" class="form-control"
								path="overtimeType">
								<form:option value="">---Select---</form:option>
								<form:options items="${overtimeTypeList}"
									itemValue="overtimeTypeId" itemLabel="overtimeType" />
							</form:select>
						</div>
					</div>
					<div class="col-3">
						<div class="form-group">
							<label>Time range:</label>
							<div class="input-group col-lg-12 col-md-12 pl-0">
								<div class="input-group-prepend">
									<span class="input-group-text"> <i
										class="far fa-calendar-alt"></i>
									</span>
								</div>
								<form:input type="text"
									class="form-control float-right date-time-range"
									path="timeRange" autocomplete="off" />
							</div>
						</div>
					</div>
					<div class="col-2">
						<div class="form-group">
							<button type="submit" class="form-control btn btn-primary"
								style="margin-top: 32px;">
								<i class="nav-icon fas fa-search pr-3"></i>Search
							</button>
						</div>
					</div>
				</div>
			</form:form>
		</div>
		<section class="content">
		<div class="container-fluid">
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">Overtime Record List</h3>
						</div>
						<div class="card-body">
							<table id="status-table" class="table table-bordered">
								<c:if test="${overtimeStatusList!=null}">
									<thead>
										<tr>
											<th width=10%>No.</th>
											<th width=25%>Request Form</th>
											<th width=25%>Applicant</th>
											<th width=25%>Department</th>
											<th width=25%>Overtime Type</th>
											<th width=25%>Start Time</th>
											<th width=25%>End Time</th>
											<th width=25%>Reason</th>
											<th width=25%>Status</th>
											<th width=25%>Created Date</th>
											<th width=25%></th>
										</tr>
									</thead>
								</c:if>
								<tbody>
									<c:forEach items="${overtimeStatusList}" var="list"
										varStatus="s">
										<tr>
											<td>${s.count}</td>
											<td>${list.overtimeForm.overtimeFormId}</td>
											<td>${list.overtimeForm.applicant.name}</td>
											<td>${list.overtimeForm.applicant.department.departmentName}</td>
											<td>${list.overtimeForm.overtimeType.overtimeType}</td>
											<td>${list.overtimeForm.startTime}</td>
											<td>${list.overtimeForm.endTime}</td>
											<td>${list.overtimeForm.reason}</td>
											<c:choose>
													<c:when test="${list.status=='Approved'}">
														<td><span class="badge bg-primary">${list.status}</span></td>
													</c:when>
													<c:when test="${list.status=='Rejected'}">
														<td><span class="badge bg-danger">${list.status}</span></td>
													</c:when>
													<c:when test="${list.status=='Sendback'}">
														<td><span class="badge bg-warning">${list.status}</span></td>
													</c:when>
													<c:otherwise>
														<td><span class="badge bg-success">${list.status}</span></td>
													</c:otherwise>
												</c:choose>
											<td>${list.createdDate}</td>
											<td><a
												href="checkAppOtDetail?id=${list.overtimeForm.overtimeFormId}"><u>Detail</u></a></td>
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
	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Change Password</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<div class="modal-body">
					<div class="form-group">
						<input type="text" class="form-control" id="exampleInputEmail1"
							placeholder="Current Password">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="exampleInputEmail1"
							placeholder="New Password">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="exampleInputEmail1"
							placeholder="Retype New Password">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-primary"
						data-dismiss="modal">Update Password</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				</div>
			</div>
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

	<script type="text/javascript">
		$('input[name="timeRange"]')
				.on(
						'apply.daterangepicker',
						function(ev, picker) {
							$(this)
									.val(
											picker.startDate
													.format('MM/DD/YYYY HH:mm')
													+ ' - '
													+ picker.endDate
															.format('MM/DD/YYYY HH:mm'));
						});

		$('input[name="timeRange"]').on('cancel.daterangepicker',
				function(ev, picker) {
					$(this).val('');
				});
	</script>
	
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#status-table').DataTable({
				"pagingType" : "full_numbers"
			});
		});
	</script>
</body>
</html>
