<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- Right Panel start -->

<div class="col1">
	<div class="post">
		<form action="Posts" method="post">
			Do you really want to delete this post ( ${article.title} ) ?
			<br />
			<input name="articleID" type="hidden" value="${articleID}">
			<input name="confirmDelete" type="hidden" value="true">
			<input name="method" type="hidden" value="delete">
			<input type="submit" value="Confirm">
			<input type="button" value="Cancel" onclick="window.history.back()">
			<!-- window.location=&quot;Posts?method=list&quot; -->
		</form>

		<br />
		<div class="post">
				<div class="articlePostTitle">
					<p>To be deleted article preview : </p>
					<div><img src="image/title_post.gif" border="0" align="absmiddle" alt="">&nbsp;&nbsp;<f:formatDate pattern="yyyy年MM月dd日 " value="${article.postDate}"/></div> 
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
					|&nbsp;阅读(1145)&nbsp;
					|&nbsp;<a href="?method=view&articleID=${article.articleID}#comments">评论</a>(<label id="numberOfComments">${fn:length(article.commentsList)}</label>)&nbsp;
					|&nbsp;<a href="Posts?method=edit&articleID=${article.articleID}">编辑</a>&nbsp;
					|&nbsp;收藏&nbsp;
					|&nbsp;所属分类:&nbsp;
						<c:forEach var="category" items="${article.categoryList}">
						<a href="Posts?method=search&categoryName=${category.categoryName}">${category.categoryName}</a>&nbsp;
						</c:forEach>
					|&nbsp;Tags:&nbsp;<c:forEach items="${article.tagList}" var="tag" varStatus="status">
						<a href="Posts?method=search&tagName=${tag.tagName}">${tag.tagName}</a>&nbsp;
					</c:forEach> 
				</div>
			</div>
	</div>
</div>
<!-- Right Panel end -->