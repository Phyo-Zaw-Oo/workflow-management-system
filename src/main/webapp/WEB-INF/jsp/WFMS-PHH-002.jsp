<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
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
							<h1 class="m-0">Approval Waiting</h1>
						</div>
					</div>
				</div>
			</div>
			<section class="content">
			<div class="container-fluid">
				<div class="row">
					<div class="col-12">
						<div class="card">
							<c:if test="${leaveApprovalWaitingDTOList!=null}">
								<div class="card-header">
									<h3 class="card-title">Leave Request</h3>
								</div>
								<div class="card-body">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>No.</th>
												<th>Request Form</th>
												<th>Applicant</th>
												<th>Start Date</th>
												<th>End Date</th>
												<th>Duration</th>
												<th>Assign To</th>
												<th>Reason</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="list" items="${leaveApprovalWaitingDTOList}"
												varStatus="a">
												<tr>
													<td>${a.count}</td>
													<td>${list.leaveFormId}</td>
													<td>${list.applicant}</td>
													<td>${list.startDate}</td>
													<td>${list.endDate}</td>
													<td>${list.duration}</td>
													<td>${list.assignTo}</td>
													<td>${list.reason}</td>
													<td>
														<button type="button" class="btn btn-sm btn-danger"
															data-toggle="modal" data-target="#rejectModal"
															data-id="${list.leaveFormId}">Reject</button>
														<button type="button" class="btn btn-sm btn-warning"
															data-toggle="modal" data-target="#sendBackModal"
															data-id="${list.leaveFormId}">Sendback</button>
														<button type="button" class="btn btn-sm btn-primary"
															data-toggle="modal" data-target="#submitModal"
															data-id="${list.leaveFormId}">Approve</button>
													</td>
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

			<section class="content">
			<div class="container-fluid">
				<div class="row">
					<div class="col-12">
						<div class="card">
							<c:if test="${overtimeApprovalWaitingDTOList!=null}">
								<div class="card-header">
									<h3 class="card-title">Overtime Request</h3>
								</div>
								<div class="card-body">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>No.</th>
												<th>Request Form</th>
												<th>Applicant</th>
												<th>Start Time</th>
												<th>End Time</th>
												<th>Duration</th>
												<th>Reason</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="list"
												items="${overtimeApprovalWaitingDTOList}" varStatus="a">
												<tr>
													<td>${a.count}</td>
													<td>${list.overtimeFormId}</td>
													<td>${list.applicant}</td>
													<td>${list.startTime}</td>
													<td>${list.endTime}</td>
													<td>${list.duration}</td>
													<td>${list.reason}</td>
													<td>
														<button type="button" class="btn btn-sm btn-danger"
															data-toggle="modal" data-target="#rejectModal"
															data-id="${list.overtimeFormId}">Reject</button>
														<button type="button" class="btn btn-sm btn-warning"
															data-toggle="modal" data-target="#sendBackModal"
															data-id="${list.overtimeFormId}">Sendback</button>
														<button type="button" class="btn btn-sm btn-primary"
															data-toggle="modal" data-target="#submitModal"
															data-id="${list.overtimeFormId}">Approve</button>
													</td>
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
	<div id="submitModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Approve</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div class="card">
						<form:form name="form" class="form" id="approvedComplete"
							modelAttribute="id">
							<div class="card-body">
								<div class="form-group row">
									<div class="col-lg-3 col-md-12 pl-0">
										<label for="exampleInputLeave1">Comment</label>
									</div>
									<div class="col-lg-9 col-md-12 pl-0">
										<textarea class="form-control" id="comment" name="comment"></textarea>
									</div>
								</div>

								<div class="form-group row">
									<div class="col-lg-3 col-md-12 pl-0">
										<label for="exampleInputLeave1">Submit To</label>
									</div>
									<div class="col-lg-9 col-md-12 pl-0">
										<select class="form-control" name="name" required="required">
											<option value="NONE">Select</option>
											<c:forEach var="list" items="${approverNameList}">
												<option value="${list.name}">${list.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</form:form>
					</div>
				</div>
				<div class="modal-footer">
					<button onclick="approvedComplete_form_submit()" class="btn btn-sm btn-success"
						data-dismiss="modal" data-toggle="modal"
						data-target="#approveSure">Complete</button>
					<button onclick="form_submit()" class="btn btn-sm btn-primary"
						data-dismiss="modal">Submit</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Sure Approve -->
	<div id="approveSure" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">

					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div class="modal-body">
						<p class="pt-2 pb-2" style="text-align: center; font-size: 24px">Approved
							Complete!!!</p>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-primary"
						data-dismiss="modal">Ok</button>

				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div id="sendBackModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Send Back</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div class="card">
						<form:form class="form" action="/sendBackForm" id="sendBack"
							modelAttribute="id">
							<div class="card-body">
								<div class="form-group row">
									<div class="col-lg-3 col-md-12 pl-0">
										<label for="exampleInputLeave1">Comment</label>
									</div>
									<div class="col-lg-9 col-md-12 pl-0">
										<textarea class="form-control" id="comment" name="comment"></textarea>
									</div>
								</div>
							</div>
						</form:form>
					</div>
				</div>
				<div class="modal-footer">
					<button onclick="sendback_form_submit()"
						class="btn btn-sm btn-primary" data-dismiss="modal">Submit</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div id="rejectModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Reject</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div class="card">
						<form:form class="form" action="/rejectForm" id="reject"
							modelAttribute="id">
							<div class="card-body">
								<div class="form-group row">
									<div class="col-lg-3 col-md-12 pl-0">
										<label for="exampleInputLeave1">Comment</label>
									</div>
									<div class="col-lg-9 col-md-12 pl-0">
										<textarea class="form-control" id="comment" name="comment"></textarea>
									</div>
								</div>
							</div>
						</form:form>
					</div>
				</div>
				<div class="modal-footer">
					<button onclick="reject_form_submit()"
						class="btn btn-sm btn-primary" data-dismiss="modal">Submit</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function reject_form_submit() {
			document.getElementById("reject").submit();
			/* comment = $("#comment").val();
			name = $("#name").val();
			alert(comment);
			alert(name); */
		}
	</script>
	<script>
		$(document).ready(function() {
			$('#rejectModal').on('shown.bs.modal', function(event) {
				var button = $(event.relatedTarget); // Button that triggered the modal
				var recipient = button.data('id');
				console.log(recipient + '>>>>>>>>>>>>>>>>>');

				$("input[type='hidden']").remove();
				var input = document.createElement("input");

				input.setAttribute("type", "hidden");

				input.setAttribute("name", "id");

				input.setAttribute("value", recipient);

				//append to form element that you want .
				$('#reject').append(input);

			});
		});
	</script>
	<script type="text/javascript">
		function sendback_form_submit() {
			document.getElementById("sendBack").submit();
			/* comment = $("#comment").val();
			alert(comment); */
		}
	</script>
	<script>
		$(document).ready(function() {
			$('#sendBackModal').on('shown.bs.modal', function(event) {
				var button = $(event.relatedTarget); // Button that triggered the modal
				var recipient = button.data('id');
				console.log(recipient + '>>>>>>>>>>>>>>>>>');

				$("input[type='hidden']").remove();
				var input = document.createElement("input");

				input.setAttribute("type", "hidden");

				input.setAttribute("name", "id");

				input.setAttribute("value", recipient);

				//append to form element that you want .
				$('#sendBack').append(input);

			});
		});
	</script>
	<!-- <script type="text/javascript">
		function approvedComplete_form_submit() {
			document.getElementById("approvedComplete").submit();
			comment = $("#comment").val();
			alert(comment);
		}
	</script> -->
	<script type="text/javascript">
		function approvedComplete_form_submit() {
			document.form.action = "/approveCompleteForm"
			    //document.Form1.target = "_blank";    // Open in a new window
			document.form.submit();             // Submit the page
			return true;
		}
	</script>
	<script type="text/javascript">
		function form_submit() {
			document.form.action = "/submitApproveCompleteForm"
			    //document.Form1.target = "_blank";    // Open in a new window
			document.form.submit();             // Submit the page
			return true;
		}
	</script>
	<script>
		$(document).ready(function() {
			$('#submitModal').on('shown.bs.modal', function(event) {
				var button = $(event.relatedTarget); // Button that triggered the modal
				var recipient = button.data('id');
				console.log(recipient + '>>>>>>>>>>>>>>>>>');

				$("input[type='hidden']").remove();
				var input = document.createElement("input");

				input.setAttribute("type", "hidden");

				input.setAttribute("name", "id");

				input.setAttribute("value", recipient);

				//append to form element that you want .
				$('#approvedComplete').append(input);

			});
		});
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