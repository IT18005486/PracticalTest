$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

// SAVE
$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation
	var status = validateAppointmentForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;

	}

	// upto this operation related processing

	// If valid-------------------------
	var type = ($("#hidAppointmentIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "AppointmentsAPI",
		type : type,
		data : $("#formAppointment").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onAppointmentSaveComplete(response.responseText, status);
		}
	});

});

function onAppointmentSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();

			$("#divAppointmentsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidAppointmentIDSave").val("");
	$("#formAppointment")[0].reset();

}

// UPDATE
$(document).on(
		"click",
		".btnUpdate",
		function(event) {
			$("#hidAppointmentIDSave")
					.val(
							$(this).closest("tr").find(
									'#hidAppointmentIDUpdate').val());
			$("#appointmentNumber").val(
					$(this).closest("tr").find('td:eq(0)').text());
			$("#appointmentType").val(
					$(this).closest("tr").find('td:eq(1)').text());
			$("#docID").val($(this).closest("tr").find('td:eq(2)').text());
			$("#docName").val($(this).closest("tr").find('td:eq(3)').text());
			$("#hospitalName").val(
					$(this).closest("tr").find('td:eq(4)').text());
			$("#Desc").val($(this).closest("tr").find('td:eq(5)').text());
		});

// REMOVE
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "AppointmentsAPI",
		type : "DELETE",
		data : "appointmentID=" + $(this).data("appointmentid"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});

function onAppointmentDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();

			$("#divAppointmentsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}

}

// CLIENT MODEL
function validateItemForm() {

	// Appointment Number
	if ($("#appointmentNumber").val().trim() == "") {
		return "Insert Appointment number.";
	}

	// Appointment Type
	if ($("#appointmentType").val().trim() == "") {
		return "Insert Appointment type.";
	}

	// Doctor ID
	if ($("#docID").val().trim() == "") {
		return "Insert Doctor ID.";
	}

	// Doctor Name
	if ($("#docName").val().trim() == "") {
		return "Insert Doctor Name.";
	}

	// Hospital Name
	if ($("#hospitalName").val().trim() == "") {
		return "Insert Hospital Name.";
	}

	// Description
	if ($("#Desc").val().trim() == "") {
		return "Insert Description.";
	}

	return true;
}
