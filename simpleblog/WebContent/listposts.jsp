<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- This page was included by manage.jsp -->

<div class="col1">
	<div class="post">
		<!--	=== Debug Info ===
				page Size : ${pageSize}		<br />
				Total Posts: ${totalPosts}		<br />
				Total Pages: ${totalPages}		<br />
				Current Page: ${pageNumber}		<hr />
		-->		
		<table>
			<thead>
				<tr align="center">
					<td width="60%">Article Title</td>
					<td width="20%">Post Date</td>
					<td colspan="3">Actions</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${articleList}" var="article">
					<tr>
						<td><a href="">${article.title}</a></td>
						<td>
							<fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${article.postDate}"/>
						</td>
						<td><a href="Posts?method=view&articleID=${article.articleID}">View</a></td>
						<td><a href="Posts?method=edit&articleID=${article.articleID}">Edit</a></td>
						<td><a href="Posts?method=delete&articleID=${article.articleID}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr align="center">
					<td colspan="5">
						<jsp:include page="listposts_paging_footer.jsp"></jsp:include>
					</td>
				</tr>
			</tfoot>
		</table>
		
		<hr/>

	</div>
</div>