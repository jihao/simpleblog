<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
function logout()
{
	document.getElementById("logoutform").submit();
}
</script>
<div class="col2">
		<!-- Column 2 start -->
		
		<c:if test="${user.nickName != null}">
			<h3>Welcome, ${user.nickName}</h3>
			<form id="logoutform" action="Logout" method="post">
				<ul>
					<li><a href="#" onclick="logout();">Logout</a></li>        			
					<li><a href="login.jsp" onclick="logout();">Switch User</a></li>
				</ul>
			</form> 
		</c:if>
		<c:if test="${user.nickName == null}">
			<h3>Welcome, Guest</h3>
			<ul>
       			<li><a href="login.jsp">Login</a></li>					
			</ul>
		</c:if>

        <div id="actionMenu">
        	<h3>Actions</h3>
        	<ul>
        		<li><a href="newpost.jsp" >New Post</a></li>
				<li><a href="Posts?method=list" >List Post</a></li>
        		<li><a href="listCategory" >New Category</a></li>
				<li><a href="listCategory" >List Category</a></li>
        		<li><a href="" >User Setting</a></li>
        		<li><a href="" >Misc Setting</a></li>
        	</ul>
        </div>
        <div id="categoryMenu">
			<h3>Categories</h3>
			<ul>
				<li>All Categories</li>
				<li>DWR</li>
				<li>Eclipse</li>
				<li>Groovy &amp; Grails</li>
				<li>GWT</li>
				<li>Hibernate</li>
				<li>J2EE</li>
				<li>J2ME</li>
				<li>J2SE</li>
				<li>OSGI</li>
				<li>Pattern</li>
				<li>Spring</li>
				<li>Struts1</li>
				<li>Struts2</li>
				<li>Swing Tips</li>
				<li>数独程序</li>
			</ul>
		</div>
		
		<p id="justforfun">
		${header.host}
		${header.referer}
        </p>
		<!-- Column 2 end -->
</div>