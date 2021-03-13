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

	<div class="wrapper">
		<div class="content-wrapper">
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">Apply Overtime</h1>
						</div>
					</div>
				</div>
			</div>
			<section class="content">
			<div class="container-fluid">
				<div class="row">
					<div class="col-12">
						<div class="card">
							<form:form class="apply-form" action="sendBackAddOTForm"
								method="post" modelAttribute="otForm">
								<div class="card-body">
									<div class="form-group">
										<label for="exampleInputLeave1">Overtime Type</label>
										<div class="col-lg-6 col-md-12 pl-0">
										<form:input path="overtimeForm" type="hidden" value="${otForm.overtimeForm}"/>
											<form:select path="overtimeType" class="form-control"
												id="exampleInputLeave1" itemValue="otTypeId"
												itemLabel="otType" required="required">
												<form:option value="">--Select--</form:option>
												<form:options items="${otTypeList}"
													itemValue="overtimeTypeId" itemLabel="overtimeType" />
											</form:select>
										</div>
									</div>
									<div class="form-group">
										<label>Time Range:</label>
										<div class="input-group col-lg-6 col-md-12 pl-0">
											<div class="input-group-prepend">
												<span class="input-group-text"> <i
													class="far fa-calendar-alt"></i>
												</span>
											</div>
											<form:input type="text"
												class="form-control float-right date-time-range"
											 path="timeRange" autocomplete="off" required="required"></form:input>
										</div>
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1">Reason</label>
										<div class="col-lg-6 col-md-12 pl-0">
											<form:textarea class="form-control" path="reason"></form:textarea>
										</div>
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1">Submit To:</label>
										<div class="col-lg-6 col-md-12 pl-0">
											<form:select class="form-control" path="submitTo"
												items="${userList}" itemValue="userId" itemLabel="name" required="required">
												<form:option value="NONE">----Select---</form:option>
												<form:options items="${userList}" itemValue="userId"
													itemLabel="name" />

											</form:select>
										</div>
									</div>
								</div>
								<div class="card-footer">
									<button type="submit" class="btn btn-primary" style="margin-left: 450px;">Apply</button>
								</div>
							</form:form>
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
	
</body>
</html>