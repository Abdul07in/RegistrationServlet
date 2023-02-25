$(document).ready(function() {
	$("#myform").on('submit', function(event) {
		event.preventDefault();
		var formData = $(this).serialize();
		$(".loader").show();
		$("form").hide();
		$.ajax({
			type: "post",
			url: "register",
			data: formData,
			success: function(data, textStatus, jqXHR) {
				console.log('success');
				$(".loader").hide();
				$("form").show();
				if (data.trim() === 'Success') {
					$("#msg").css({
						"color": "green"
					});
					$("#msg").html("Successfully Registered 🙂");
				} else {
					$("#msg").css({
						"color": "red"
					});
					$("#msg").html("Error While Registration 😢");
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log('error' + textStatus);
				$(".loader").hide();
				$("form").show();
				$("#msg").css({
					"color": "red"
				});
				$("#msg").html("Error While Registration 😢");
			}
		});
	});
})
