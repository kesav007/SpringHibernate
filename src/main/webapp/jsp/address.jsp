<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="css/bootstrap.min.css" />

</head>
<body>
	<div class="container">				
		<table class="table table-hover table-striped">
			<thead>
				<tr>
					<th scope="col">Street</th>
					<th scope="col">City</th>
					<th scope="col">State</th>
					<th scope="col">Zip</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${addresses}" var="address">
					<tr>
						<td>${address.street}</td>
						<td>${address.city}</td>
						<td>${address.state.state}</td>
						<td>${address.zip}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>