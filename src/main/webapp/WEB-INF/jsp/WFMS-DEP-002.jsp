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
<link rel="stylesheet" href="dist/css/adminlte.min.css">
<link rel="stylesheet"
	href="plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<link rel="stylesheet"
	href="plugins/daterangepicker/daterangepicker.css">
<link rel="stylesheet" href="plugins/summernote/summernote-bs4.min.css">
<link
	href="https://cdn.datatables.net/1.10.23/css/jquery.dataTables.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>
<link
	href="https://cdn.datatables.net/1.10.23/css/jquery.dataTables.min.css"
	rel="stylesheet">
</head>
<body class="hold-transition sidebar-mini layout-fixed">

	<jsp:include page="WFMS-ASB-001.jsp" />

	<div class="content-wrapper">
		<div class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1 class="m-0">Department List</h1>
					</div>
				</div>
			</div>
		</div>
		<div class="search-form content"
			style="background: #fff; margin: 16px; border: 1px solid #ddd; border-radius: 4px; padding: 16px;">
			<label id="message" style="color: green;" margin="left">${msg}</label>
			<label id="message" style="color: red;" margin="left">${err}</label>
			<form:form action="deptList" modelAttribute="department"
				method="post">
				<div class="row">
					<div class="col-2">
						<div class="form-group">
							<label for="exampleInputEmail1">ID</label>
							<form:input type="text" class="form-control"
								id="exampleInputEmail1" placeholder="Enter ID"
								path="departmentId"></form:input>
						</div>
					</div>
					<div class="col-2">
						<div class="form-group">
							<label for="exampleInputEmail1">Name</label>
							<form:input type="text" class="form-control"
								id="exampleInputEmail1" placeholder="Enter Name"
								path="departmentName"></form:input>
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
					<c:if test="${deptList!=null}">
						<div class="card-header">
							<h3 class="card-title">Department List</h3>
						</div>
						<div class="card-body" id="list">
							<table  class="table table-bordered">
								
									<thead>
										<tr>
											<th>Department ID</th>
											<th>Department Name</th>
											<th style="width: 13%">Action</th>
										</tr>
									</thead>
								
								<tbody>
									<c:forEach items="${deptList}" var="list">
										<tr>
											<td>${list.departmentId}</td>
											<td>${list.departmentName}</td>
											<td><a href="updateDeptSetUp?id=${list.departmentId}">
													<input type="button" value="Edit"
													class="btn btn-sm btn-primary" id="deptUpdate" />
											</a> <a href="deleteDept?id=${list.departmentId}"> <input
													type="button" value="Delete" id="delete"
													class="btn btn-sm btn-danger" onclick="checkDelete()" />
											</a></td>

										</tr>
									</c:forEach>


								</tbody>
							</table>
						</div>
						</c:if>
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

	
</body>
</html>
