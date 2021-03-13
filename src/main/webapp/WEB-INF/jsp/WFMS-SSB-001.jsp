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
	<div class="wrapper">
		<nav
			class="main-header navbar navbar-expand navbar-white navbar-light">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
				href="#" role="button" style="font-size: 32px;"><i
					class="fas fa-bars" style="font-size: 24px;"></i><b class="pl-3">Workflow
						Management System</b></a></li>
		</ul>
		<ul class="navbar-nav ml-auto">
			<li class="nav-item pt-2"><a href="#" class="ml-auto pr-3"
				style="color: rgba(0, 0, 0, .5);">${sessionScope.sesUser.name}<i
					class="fas fa-user mt-1 pl-1"></i></a></li>

			<li class="nav-item pt-1 mr-3">
		          <a href="#" class="dropdown-item dropdown-footer" data-toggle="modal" data-target="#myModal"><i class="fas fa-key mr-2"></i>Change Password</a>
				
		    </li>
		</ul>
		</nav>
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
		<a href="checkStatus" class="brand-link"> <img
			src="dist/img/logo.png" alt="AdminLTE Logo"
			class="brand-image img-circle elevation-3" style="opacity: .8">
			<span class="brand-text font-weight-light">Savage Team</span>
		</a>
		<div class="sidebar">
			<nav class="mt-2">
			<ul class="nav nav-pills nav-sidebar flex-column"
				data-widget="treeview" role="menu" data-accordion="false">
				<li class="nav-item"><a href="checkStatus"
					class="nav-link"> <i class="nav-icon fa fa-home"></i>
						<p class="text">Home</p>
				</a></li>
				<li class="nav-item"><a href="leaveFormSetUp"
					class="nav-link"> <i class="nav-icon far fa-edit"></i>
						<p class="text">Apply Leave</p>
				</a></li>
				<li class="nav-item"><a href="otFormSetUp"
					class="nav-link"> <i class="nav-icon far fa-edit"></i>
						<p class="text">Apply Overtime</p>
				</a></li>
				<li class="nav-item has-treeview"><a href=""
					class="nav-link"> <i class="nav-icon far fa-edit"></i>
						<p>
							Application History <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="checkAppLeaveSetup"
							class="nav-link"> <i class="nav-icon far fa-plus-square"></i>
								<p>Leave</p>
						</a></li>
						<li class="nav-item"><a href="checkAppOtSetup"
							class="nav-link"> <i class="nav-icon far fa-plus-square"></i>
								<p>Overtime</p>
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="logout"
					class="nav-link"> <i class="nav-icon fas fa-sign-out-alt"></i>
						<p class="text">Logout</p>
				</a></li>
			</ul>
			</nav>
		</div>
		</aside>
	</div>
	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<form:form action="/changePassword" id="update_password">
					<div class="modal-header">
						<h4 class="modal-title">Change Password</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<div class="modal-body">
						<div class="form-group">
							<input name="password" type="password" class="form-control" id="exampleInputEmail1"
								placeholder="Current Password"/>
						</div>
						<div class="form-group">
							<input name="newPassword" type="password" class="form-control" id="exampleInputEmail1"
								placeholder="New Password"/>
						</div>
						<div class="form-group">
							<input name="reTypePassword" type="password" class="form-control" id="exampleInputEmail1"
								placeholder="Retype New Password"/>
						</div>
					</div>
					<div class="modal-footer">
						<button onclick="form_submit()" class="btn btn-sm btn-primary"
							data-dismiss="modal">Update Password</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function form_submit() {
			document.getElementById("update_password").submit();
		}
	</script>
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