<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Right Panel start -->

<link rel=StyleSheet type="text/css" href="style/comments.css" />
<script type="text/javascript" src="js/jquery-1.2.6.js" ></script>
<script type="text/javascript">
    $(document).ready(function(){
		 $("input, textarea").addClass("idle");
		        $("input, textarea").focus(function(){
		            $(this).addClass("activeField").removeClass("idle");
		 }).blur(function(){
		            $(this).removeClass("activeField").addClass("idle");
		 });
    });
</script>

<div class="col1">
	<div class="categoryDiv">
		<div class="listCategoryDiv">
			<jsp:include page="listcategory.jsp"></jsp:include>
		</div>
		<form action="createCategory" method="post" id="myForm"> 
			<div id="inputArea">
				<h3 align="center">Add New Category</h3>
				<!-- 
				<input type="hidden" name="categoryID" value="${param.categoryID}">
				 -->
				Name<input type="text" name="categoryName" value="${categoryName }" />
				Comment<textarea rows="4" cols="30" name="categoryComments">${categoryComments}</textarea>
				<c:if test="${param.method eq 'edit'}"><input type="submit" value="Update"/></c:if>
				<c:if test="${param.method ne 'edit'}"><input type="submit" value="Add"/></c:if>
			</div>
		</form>
	</div>
</div>
<!-- Right Panel end -->