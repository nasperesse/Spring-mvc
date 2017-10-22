<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<title>Todos for ${name}</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	    		rel="stylesheet">
</head>
<body>

<div class="container">
<H1>Your Todos</H1>



<p>Your Todo are:</p>

<table class="table table-striped">
<thead>
<tr>
<th>ID</th>
<th>Beschreibung</th>
<th>Name</th>
<th>Date</th>

</tr>

</thead>

<tbody>
<c:forEach items="${todos}" var="todo">
<tr>
<td> ${todo.id}</td>
<td> ${todo.desc}</td>
<td>${todo.user}</td>
<td><fmt:formatDate pattern="dd/MM/YYYY" value="${todo.targetDate}"/></td>
<td><a href="/delete-todo?id=${todo.id}" class="btn btn-danger">Delete</a> 
<a href="/update-todo?id=${todo.id}" class="btn btn-success">Update</a></td>


</tr>
</c:forEach>

</tbody>




</table>
<div>
 <a class="btn btn-success" href="/add-todo">Add</a>
</div>

 </div>
 <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
 <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
</body>
</html>