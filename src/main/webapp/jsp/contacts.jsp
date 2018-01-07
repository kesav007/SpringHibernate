<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="../css/bootstrap.min.css" />

</head>
<body>
	<div class="container">
		<div class="page-header">
			<h1>Contacts</h1>
		</div>
		<div id="list-example" class="list-group">
		<c:forEach items="${contacts}" var="contact">
				<a class="list-group-item list-group-item-action"  href="contact?view=${contact.id}">${contact.name}</a>
		</c:forEach>
		<a href="contact" class="btn btn-primary">Add Contacts</a>
		</div>
	</div>
</body>
</html>