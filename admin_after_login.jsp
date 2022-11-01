<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body>
	<div class="container justify-content-center ">
		<h3 class="text-center">Please tell me to whom would you like to
			play with</h3>
		<br> <br>

		<div class="d-grid gap-2 col-6 mx-auto">
			<form action="admin_after_login" method="post">  


				<select name="choice">
					<option selected value="user">life of user</option>
					<option value="organizer">life of event organizer</option>
					<option value="event">Events</option>
					<option value="booking">Bookings</option>
					<option value="admin_profile">Why not play with your own
						life Now play with it</option>
				</select> <br> <br> <input type="submit"
					value="submit here aapka prastav">
			</form>
		</div>
	</div>
</body>
</html>