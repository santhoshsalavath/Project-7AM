<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1 style="  text-align:center ">Social Security Number </h1>
<script src= "https://code.jquery.com/jquery-3.4.1.min.js">
</script>
<head>
<style type="text/css">
body {
	background-color: #f6f6ff;
	font-family: Calibri, Myriad;
}

#main {
	width: 780px;
	padding: 20px;
	margin: auto;
}

table.timecard {
	margin: auto;
	width: 1000px;
	border-collapse: collapse;
	border: 2px solid #fff; /*for older IE*/
	 border-style: hidden; 
}

table.timecard caption {
	background-color: #f79646;
	color: #fff;
	font-size: x-large;
	font-weight: bold;
	letter-spacing: .3em;
}

table.timecard thead th {
	padding: 8px;
	background-color: #fde9d9;
	font-size: large;
}

table.timecard thead th#thDay {
	width: 40%;	
}

table.timecard thead th#thRegular, table.timecard thead th#thOvertime, table.timecard thead th#thTotal {
	width: 20%;
}

table.timecard th, table.timecard td {
	padding: 3px;
	border-width: 1px;
	border-style: solid;
	border-color: #f79646 #ccc;
}

table.timecard td {
	text-align: right;
}

table.timecard tbody th {
	font-weight: bold;
	font-size: large;
	text-align: left;
}

table.timecard tfoot {
	font-weight: bold;
	font-size: large;
	background-color: #687886;
	color: #fff;
}

table.timecard tr.even {
font-weight: bold;
	font-size: large;
	text-align: left;
	background-color: #fde9d9;
}
</style>
<script>
$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>
</head>
<body>
<div id="main">
<h2 class="main">Type something in the field to search in the table</h2>
<input type="text" placeholder="search .." id="myInput"/><br><br>
<div id="main">
<table class="timecard">
<caption>All SSN Number</caption>
<thead>
<tr >
<th id="thDay">SSN NUMBER</th>
<th id="thRegular">FIRST NAME</th>
<th id="thOvertime">LAST NAME</th>
<th id="thTotal">DTAE OF BIRTH</th>
<th id="thDay">GENDER</th>
<th id="thRegular">PHONE NUMBER</th>
<th id="thTotal">STATE NAME</th>
<th id="thRegular">PHOTO</th>
</tr>
</thead>
<tbody id="myTable">
<c:if test="${!empty SSNDetails}">
<c:forEach items="${SSNDetails}" var="ssn">
<tr class="even">
<td>${ssn.ssnNumber}</td>
<td>${ssn.fname}</td>
<td>${ssn.lname}</td>
<td>${ssn.dob}</td>
<td>${ssn.gender}</td>
<td>${ssn.phone}</td>
<td>${ssn.state}</td>
<td><a href="displayImage?id=${ssn.ssnNumber}">Display Image</a>
</td>
</tr>
</c:forEach>
</c:if>
</tbody>
<tfoot>
		<tr>
			<th>Total weekly hours:</th>
			<td>40</td>
			<td>8.5</td>
			<td>48.5</td>
			<td>8.5</td>
			<td>48.5</td>
			<td>48.5</td>
			<td>8.5</td>
		</tr>
	</tfoot>
</table>
</div>
</body>
</html>