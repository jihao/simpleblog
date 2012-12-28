<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- This page was included by mynewcategoryeditor.jsp -->
<style type="text/css">
table{
	font-size: small;
}
table thead td
{
	border-bottom-style: solid; 
	border-bottom-width: 2px; 
	border-color: #000000;
}
table tfoot td
{
	border-top-style: solid; 
	border-top-width: 3px; 
	border-color: #000000;
}
.categoryComments {
	font-size: 80%;
}
</style>


<h3>Category List</h3>
<table width="100%" cellspacing="0px">
	<thead>
	<tr align="center">
		<td>Category</td>
		<td colspan="2">Actions</td>
	</tr>
	</thead>
	
	<c:forEach items="${categoryList}" var="category" varStatus="i">
	<c:if test="${i.count%2 == 0}">
		<c:set var="bgColor" value="#ffffff"> </c:set>
	</c:if>
	<c:if test="${i.count%2 != 0}">
		<c:set var="bgColor" value="#EBF2FA"> </c:set>
	</c:if>
	<tr bgcolor="${bgColor}">
		<td >
			<a href="listpost?categoryName=${category.categoryName}">${category.categoryName}</a>
			<br/>
			<label class="categoryComments">&nbsp;&nbsp;&nbsp;&nbsp;${category.categoryComments}</label>
		</td>
		<td><a href="?method=edit&categoryName=${category.categoryName}">Edit</a></td>
		<td><a href="?method=delete&categoryName=${category.categoryName}">Delete</a></td>	
	</tr>
	</c:forEach>
	<tfoot>
		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
	</tfoot>
</table>
