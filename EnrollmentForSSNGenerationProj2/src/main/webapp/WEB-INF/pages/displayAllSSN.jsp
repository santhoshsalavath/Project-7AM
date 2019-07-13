<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1 style="  text-align:center ;background-color: cyan">Social Security Number </h1>
<script src= "https://code.jquery.com/jquery-3.4.1.min.js">
</script>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
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
<h2 style="background-color: cyan"">Type something in the field to search in the table</h2>
<input type="text" placeholder="search .." id="myInput"/><br><br>
<table>
<tr >
<th>SSN NUMBER</th>
<th>FIRST NAME</th>
<th>LAST NAME</th>
<th>DTAE OF BIRTH</th>
<th>GENDER</th>
<th>PHONE NUMBER</th>
<th>STATE NAME</th>
<th>PHOTO</th>
</tr>
<tbody id="myTable">
<c:if test="${!empty SSNDetails}">
<c:forEach items="${SSNDetails}" var="ssn">
<tr style="font-size: medium;">
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
</table>
