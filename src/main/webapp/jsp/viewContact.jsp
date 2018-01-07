<!DOCTYPE form PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" >
$(document).ready(function(){
	$("#update").click(function () {
		$("#updateType").val("update");
		$("#form").submit();
	});	
	$("#delete").click(function () {
		 if(confirm("Are you sure you want to delete this?")){
			 $("#updateType").val("delete");	
	     		$("#form").submit();
		 }
		 else {
			 return false;
		 }
	});
});
</script>
</head>
<body>
	<div class="container">
		<form id="form" action="contact" method="post">
			<div class="page-header">
			<c:if test="${empty contact.id}" >
			<h1>Add Contact</h1>
			</c:if>
			<c:if test="${not empty contact.id}" >
				<h1>View / Update Contact</h1>
			</c:if>
			</div>
			<input type="text" class="form-control" name="contactId" value="${contact.id}"  readonly="readonly">
			<input type="text" class="form-control" id="updateType"  name="updateType" readonly="readonly">
			<div class="form-row">
				<div class="form-group col-md-12">
					<label for="fname">First Name</label>
					<input type="text" class="form-control" id="fname" placeholder="First Name" name="name" value="${contact.name}">
				</div>
			</div>
			<div class="form-group">
				<label for="inputAddress">Address</label> 
				<input type="text" class="form-control" id="inputAddress" placeholder="1234 Main St" name="street" value="${address.street }">
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="inputCity">City</label> 
					<input type="text"  class="form-control" id="inputCity" name="city" value="${address.city}">
				</div>
				<div class="form-group col-md-4">
					<label for="inputState">State</label>
					<input type="text" class="form-control" id="inputState" name="state" value="${address.state}">
				</div>
				<div class="form-group col-md-2">
					<label for="inputZip">Zip</label>
					<input type="text" class="form-control" id="inputZip" name="zip" value="${address.zip}">
				</div>
			</div>
			<c:if test="${empty contact.id}" >
				<button type="submit" class="btn btn-primary" >Add</button>
			</c:if>
			<c:if test="${not empty contact.id}" >
			
				<button class="btn btn-primary"  id="update">Update</button>
				<button class="btn btn-primary"  id="delete">Delete</button>
			</c:if>
			<a href="contacts" class="btn btn-primary">Back to Contacts</a>
		</form>
	</div>
</body>
</html>