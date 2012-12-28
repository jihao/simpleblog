<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- Right Panel start -->

<div class="col1">
    <script type='text/javascript' src='dwr/engine.js'></script>
    <script type='text/javascript' src='dwr/interface/CommentsAction.js'></script>
    <script type='text/javascript' src='dwr/util.js'></script>
  	<script type="text/javascript" src="js/jquery-1.2.6.js"></script>
	<script type="text/javascript" src="js/validate/jquery.validate.js"></script>

	<script type="text/javascript">
	
	var comments = {
		commentsID:"",
		theCommentsArticleID:"",
		title:"",
		userName:"",
		homePageURL:"",
		userEmail:"",
		content:""
	}
	var commentForm;
	
	function addComments()
	{
		var r1 = commentForm.element("#title")
		var r2 = commentForm.element("#userName")
		var r3 = commentForm.element("#homePageURL")
		var r4 = commentForm.element("#userEmail")
		var r5 = commentForm.element("#content")
		if (r1 && r2 && r3 && r4 && r5)
		{
			DWRUtil.getValues(comments);
			
	        var reply = DWRUtil.toDescriptiveString(comments, 2);
				reply = reply.replace(/\n/g, "<br/>");
	        alert(reply);
	        
	        CommentsAction.addComments(
	                comments,
	                function(c)
			        {
	    		        alert(DWRUtil.toDescriptiveString(c, 2))
	    		        var commentsDiv = document.getElementById("commentsDiv");
	    		        var d = new Date(c.postDate);
	    		        var innerHTML = "<div class=\"post\">";
	    		        	innerHTML+= "	<a title=\""+c.title+"\" href=\"#"+c.commentsID+"\">#</a>&nbsp;<a name=\""+c.commentsID+"\"></a>";
	    		        	innerHTML+= 	c.title + " (" + d.getYear()+"-"+d.getMonth()+"-"+d.getDay()+" "+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds()+")";
	    		        	innerHTML+= " | <a href=\""+ c.homePageURL+"\" target=\"_blank\">"+c.userName +"</a><br/>";
	    		        	innerHTML+= "	<div align=left>" + c.content + "&nbsp;&nbsp;";
	    		        	innerHTML+= "		<a onclick=\"return setReplyAuthor(&quot;"+c.userName+"&quot;)\" href=\"#commentInput\">回复</a>";
	    		        	innerHTML+= "	</div>";
	    		        	innerHTML+= "</div><br/>";
	    		        commentsDiv.innerHTML += innerHTML;
	
	    		        updateNumberOfComments();
	    		        
	    		        DWRUtil.setValues(
	    		        		{
	    		        			userName:"",
	    		        			homePageURL:"",
	    		        			userEmail:"",
	    		        			content:""
	    		        		});
	    		        
			        });
		}
	}
	function setReplyAuthor(author)
	{
		alert(author);
		DWRUtil.setValue("content","@"+author+"\n");
	}
	function updateNumberOfComments()
	{
		 var numberOfComments = document.getElementById("numberOfComments");
	     var i = new Number(numberOfComments.innerHTML)+1;
	     numberOfComments.innerHTML = i;
	}
	 // Initialization that is called when window is loaded
    function init() {
        DWRUtil.useLoadingMessage();
    }
    
    // Call init function when window is loaded
    window.onload = init;

    
    $(document).ready(function() {
    	commentForm = $("#commentForm").validate();
    });
    
	</script>
	<div class="post">
		<div class="articlePostTitle"><a href="Posts?method=view&articleID=${article.articleID}">${article.title}</a></div>
		<div class="content">
			<!-- 
			ArticleID: ${article.articleID}<br/>
			AsDraft: ${article.asDraft}<br/>
			 -->
			${article.content}
		</div>
		
		<div class="articlePostDesc">
			posted on <f:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${article.postDate}"/> by ${article.author.nickName}
			|&nbsp;Read(1145)&nbsp;
			|&nbsp;<a href="#comments">评论</a>(<label id="numberOfComments">${fn:length(article.commentsList)}</label>)&nbsp;
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

	<div class="blog_comment">
		<h5 id="comments">Comments</h5>
	</div>

	<div>
		<div id="commentsDiv">
			<!-- AJAX Comments -->
			<c:forEach var="comments" items="${article.commentsList}">
				<div class="post">
    		        <a title="${comments.title}" href="#${comments.commentsID}">#</a>
					&nbsp;<a name="${comments.commentsID}"></a>
    		        ${comments.title}(<f:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${comments.postDate}"/>) 
    		        | <a href="${comments.homePageURL}" target="_blank">${comments.userName}</a><br/>
    		        	<div align=left>${comments.content}&nbsp;&nbsp;
    		        		<a onclick="return setReplyAuthor(&quot;${comments.userName}&quot;)" href="#commentInput">回复</a>
    		        	</div>
    		    </div><br/>
			</c:forEach>
		</div>

		<div class="blog_comment">
			<h5 id="commentInput">Post Your Comment</h5>
			<p>留言请注意遵守<a href="" target="_blank">本站发贴规则</a></p>
			<input type="hidden" id="theCommentsArticleID" value="${article.articleID}"/>			
			<p>
			<form id="commentForm" action="" method="get" >
				<table>
					<thead>
						<tr>
							<td width="50"></td>
							<td></td>
						</tr>
					</thead>
					<tbody>
					<tr>
						<td><label for="title" >Title</label></td>
						<td><input type="text" id="title" class="required" style="width:320" value="re:${article.title}"></td>
					</tr>
					<tr>
						<td><label for="userName">Name</label></td>
						<td><input type="text" id="userName" class="required" style="width:320"></td>
					</tr>
					<tr>
						<td><label for="homePageURL">URL</label></td>
						<td><input type="text" id="homePageURL" class="required url" style="width:320"></td>
					</tr>	
					<tr>
						<td><label for="userEmail">Email</label></td>
						<td><input type="text" id="userEmail" class="required email" style="width:320"></td>
					</tr>	
					<tr>
						<td><label for="content">Content</label></td>
						<td><textarea id = "content" rows="4" cols="30" class="required" style="width:320"></textarea></td>
					</tr>
					<tr>
						<td colspan="2"><input type="button" onclick="addComments();" value="Submit"> </td>
					</tr>				
					</tbody>
				</table>
			</form>
		</div>
	</div>
	
</div>
<!-- Right Panel end -->