<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>Registration</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link rel="stylesheet" href="css/style.css">
		<link rel="icon" type="image/x-icon" href="https://img.icons8.com/fluency/256/petition.png">
	</head>

	<body>
		<div class="container">
			<div class="row">
				<div class="col m8 offset-m2">
					<div class="card">
						<div class="card-content">
							<h3 class="center-align">Registration Form</h3>
							<h5 id="msg" class="center-align"></h5>
							<div class="form center-align">
								<form action="register" method="post" id="myform">
									<div class="row">
										<div class="input-field col s12">
											<input id="username" name="user_name" type="text" class="validate"> <label
												for="username">Username</label>
										</div>
									</div>
									<div class="row">
										<div class="input-field col s12">
											<input id="password" name="user_password" type="password" class="validate">
											<label for="password">Password</label>
										</div>
									</div>
									<div class="row">
										<div class="input-field col s12">
											<input id="email" type="email" name="user_email" class="validate"> <label
												for="email">Email</label>
										</div>
									</div>

									<button class="btn waves-effect waves-light green" type="submit" name="action">
										Submit <i class="material-icons right">send</i>
									</button>

								</form>
							</div>
							<div class="loader center-align">
								<div class="preloader-wrapper big active">
									<div class="spinner-layer spinner-blue-only">
										<div class="circle-clipper left">
											<div class="circle"></div>
										</div>
										<div class="gap-patch">
											<div class="circle"></div>
										</div>
										<div class="circle-clipper right">
											<div class="circle"></div>
										</div>
									</div>
								</div>
								<h5>Please Wait...</h5>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery-3.6.3.min.js"
			integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
		<script type="text/javascript">
			$(function () {
				$("#myform").on('submit', function (event) {
					event.preventDefault();
					var f = $(this).serialize();
					console.log(f);
					$(".loader").show();
					$("form").hide();
					$.ajax({
						type: "post",
						url: "register",
						data: f,
						success: function (data, textStatus, jqXHR) {
							console.log(data);
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
						error: function (jqXHR, textStatus, errorThrown) {
							console.log(data);
							console.log('error');
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
		</script>
	</body>

	</html>