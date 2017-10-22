<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<title>neue Todo</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	    		rel="stylesheet">
</head>
<body>
<div class="container">
    <form:form method="POST" commandName="todo">
    
    <fieldset class="form-group">
       <form:hidden path="id"/>
       <form:label path="desc">Description</form:label> 
       <form:input path="desc" type="text" class="form-control" required="required"/> 
       <form:errors path="desc" cssClass="text-Warning"></form:errors>

    <fieldset class="form-group">
       <form:label path="targetDate">Target Date</form:label> 
       <form:input path="targetDate" type="text" class="form-control" required="required"/> 
       <form:errors path="targetDate" cssClass="text-Warning"></form:errors>
     </fieldset>
     
     <input class="btn btn-success" type="submit" value="add" />
     
    </form:form>
    
</div>

 <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
 <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
</body>
</html>