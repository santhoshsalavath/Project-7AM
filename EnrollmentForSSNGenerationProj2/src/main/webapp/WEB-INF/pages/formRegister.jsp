<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form" %>
<html>
<head>
<style type="text/css">
.error {
	color: red;
}
</style>
<script src="https://cdn.jsdelivr.net/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
<script >
$(function() {
	  // Initialize form validation on the registration form.
	  // It has the name attribute "registration"
	  $("form[name='registration']").validate({
	    // Specify validation rules
	    rules: {
	      // The key name on the left side is the name attribute
	      // of an input field. Validation rules are defined
	      // on the right side
	      
	      
	      dob: "required",
	      phone: "required",
	      gender: "required",
	      fname: {
	        required: true,
	        minlength: 3
	      },
	  lname: {
	        required: true,
	        minlength: 3
	      }
	    },
	    // Specify validation error messages
	    messages: {
	    	fname: {
	    	        required:     "      Please enter your firstname",
	    	        minlength:    "      Your firstname must be at least 3 characters "
	    	      },
	      
	      lname:{
  	        required:     "       Please enter your lastname",
	        minlength:    "      Your last name must be at least 3 characters "
	      },
	      dob:    "         Please enter your Date Of Birth",
	     phone:     "      Please enter your phone number",
	     gender:    "     Please enter your gender"
	    },
	    // Make sure the form is submitted to the destination defined
	    // in the "action" attribute of the form when valid
	    submitHandler: function(form) {
	      form.submit();
	    }
	  });
	});
</script>
</head>
<body>
<h1 style="color:blue ; text-align:center">Enrollment For SSN (Social security Number)</h1>
<h2 style="color:green">${resultMsg }</h2>
<fieldSet>
<legend ><h2 style="color:blue ">Form Registration</h2></legend>
<form:form modelAttribute="model" action="saveUser" enctype="multipart/form-data" name="registration">
First Name ::<form:input path="fname" placeHolder="first name"/> &nbsp &nbsp <form:errors path="fname" cssClass="error" /><br><br>
Last Name :: <form:input path="lname"  placeHolder="last name"/> &nbsp &nbsp <form:errors path="lname" cssClass="error" /><br><br>
Date Of Birth ::<form:input type="date"  path="dob"/>  <br><br>
Gender :: <form:radiobuttons path="gender" items="${genders }"/> &nbsp &nbsp <form:errors path="gender" cssClass="error" /><br><br>
Phone Number :: <form:input path="phone"  placeHolder="phone number"/><br><br>
Select State ::<form:select path="state">
                         <form:option value="-- select state --"/>
                         <form:options items="${states}"/>
</form:select><form:errors path="state" cssClass="error" /><br><br>
Chose Photo ::<input type="file" name="photo"><br><br>
<input type="submit" value="register">
</form:form>
</fieldSet>
<h2><a href="displayAllSSN">Display all SSN </a></h2>
</body>
</html>