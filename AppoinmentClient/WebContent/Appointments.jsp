<%@page import="com.Appointment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointments Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Appointments.js"></script>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-8">
				<h1>Appointment Management</h1>

				<form id="formAppointment" name="formAppointment">
					Appointment Number: <input id="appointmentNumber"
						name="appointmentNumber" type="text"
						class="form-control form-control-sm"> <br>
					<br> Appointment Type: <input id="appointmentType"
						name="appointmentType" type="text"
						class="form-control form-control-sm"> <br>
					<br> Doctor ID: <input id="docID" name="docID" type="text"
						class="form-control form-control-sm"> <br>
					<br> Doctor Name: <input id="docName" name="docName"
						type="text" class="form-control form-control-sm"> <br>
					<br> Hospital Name: <input id="hospitalName"
						name="hospitalName" type="text"
						class="form-control form-control-sm"> <br>
					<br> Description: <input id="Desc" name="Desc" type="text"
						class="form-control form-control-sm"> <br>
					<br> <input id="btnSave" name="btnSave" type="button"
						value="Save" class="btn btn-primary"> <input type="hidden"
						id="hidAppointmentIDSave" name="hidAppointmentIDSave" value="">
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divAppointmentsGrid">

					<%
						Appointment appointmentObj = new Appointment();
					out.print(appointmentObj.readAppointments());
						
					%>

				</div>
			</div>
		</div>
</body>
</html>