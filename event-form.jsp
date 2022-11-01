<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Event Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> Event Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Events</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${event != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${event == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${event != null}">
            			Edit event
            		</c:if>
						<c:if test="${event == null}">
            			Add New event
            		</c:if>
					</h2>
				</caption>

				<c:if test="${event != null}">
					<input type="hidden" name="event_id" value="<c:out value='${event.event_id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Event Name</label> <input type="text"
						value="<c:out value='${event.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Event Venue</label> <input type="text"
						value="<c:out value='${event.venue}' />" class="form-control"
						name="venue">
				</fieldset>

				<fieldset class="form-group">
					<label>event price</label> <input type="number"
						value="<c:out value='${event.price}' />" class="form-control"
						name="price">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Event category</label> <input type="text"
						value="<c:out value='${event.category}' />" class="form-control"
						name="category">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
