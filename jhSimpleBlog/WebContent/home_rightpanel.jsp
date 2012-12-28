<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- Right Panel start -->

<div class="col1">
	<!--	=== Debug Info ===
			page Size : ${pageSize}		<br />
			Total Posts: ${totalPosts}		<br />
			Total Pages: ${totalPages}		<br />
			Current Page: ${pageNumber}		<hr />
	-->
	<c:forEach items="${articleList}" var="article">
		<div class="post">
			<div class="articlePostTitle">
				<div><img src="image/title_post.gif" border="0" align="absmiddle" alt="">&nbsp;&nbsp;<f:formatDate pattern="yyyy-MM-dd " value="${article.postDate}"/></div> 
				<h2><a href="Posts?method=view&articleID=${article.articleID}">${article.title}</a></h2>
			</div>
			<div class="content">
				<!-- === Debug Info ===
				ArticleID: ${article.articleID}<br/>
				AsDraft: ${article.asDraft}<br/>
				 -->
				${article.content}
			</div>
			
			<div class="articlePostDesc">
				posted on <f:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${article.postDate}"/> by ${article.author.nickName}
				|&nbsp;Read(1145)&nbsp;
				|&nbsp;<a href="?method=view&articleID=${article.articleID}#comments">Comments</a>(<label id="numberOfComments">${fn:length(article.commentsList)}</label>)&nbsp;
				|&nbsp;<a href="Posts?method=edit&articleID=${article.articleID}">Edit</a>&nbsp;
				|&nbsp;Star&nbsp;
				|&nbsp;Category:&nbsp;
					<c:forEach var="category" items="${article.categoryList}">
					<a href="Posts?method=search&categoryName=${category.categoryName}">${category.categoryName}</a>&nbsp;
					</c:forEach>
				|&nbsp;Tags:&nbsp;<c:forEach items="${article.tagList}" var="tag" varStatus="status">
					<a href="Posts?method=search&tagName=${tag.tagName}">${tag.tagName}</a>&nbsp;
				</c:forEach> 
			</div>
		</div>
		<br/>
	</c:forEach>

	<div align="right">
		<jsp:include page="home_rightpanel_paging_footer.jsp"></jsp:include>
	</div>
</div>
<!-- Right Panel end -->