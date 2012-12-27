<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<%@page import="cn.heapstack.simpleblog.domain.Article"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cn.heapstack.simpleblog.domain.Tag"%>


<div class="col1">
	<script type="text/javascript">
	function save_as_draft()
	{
		var postAsDraft = document.getElementById("postAsDraft");
		postAsDraft.value = "true";
		document.getElementById("myForm").submit();
	}
	function FCKeditor_OnComplete(editorInstance) {
		window.status = editorInstance.Description;
	}
	</script>

	<div class="post">
		<form action="Posts" method="post" id="myForm"> 
			<strong>Title: </strong><br/>
			<input type="text" style="width:95%" maxlength="255" name="title" width="98%" value="${article.title}"><br/>
			
			<strong> Content: </strong> <br/>
			<FCK:editor instanceName="EditorDefault" height="500px" width="95%">
				<jsp:attribute name="value">
					<c:choose>
						<c:when test="${article ne null}">
							${article.content}
						</c:when>
						<c:otherwise>
							This is some <strong>sample text</strong>.
							You are using <a href="http://www.fckeditor.net">FCKeditor</a>.
							中文 测试 测试
						</c:otherwise>
					</c:choose>
			    </jsp:attribute>
			
				<jsp:body>
					<FCK:config SkinPath="skins/office2003/" AutoDetectLanguage="true" />
				</jsp:body>
			
			</FCK:editor>
			
			<strong>Category:</strong><br/>
			<div style="width:95%">
				<c:choose>
					<c:when test="${article.categoryList == null}">
						<c:forEach var="category" items="${categoryList}">
							<input type="checkbox" name="categoryNames" value="${category.categoryName}"/>${category.categoryName} &nbsp;&nbsp;
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach var="category" items="${categoryList}">
							<c:forEach var="checkCatrgory" items="${article.categoryList}">
								<c:if test="${checkCatrgory.categoryName eq category.categoryName}">
									<c:set var="checked" value="checked=&quot;checked&quot;"></c:set>
								</c:if>
							</c:forEach>
							<input type="checkbox" name="categoryNames" value="${category.categoryName}" ${checked}/> ${category.categoryName} &nbsp;&nbsp;
							<c:set var="checked" value=""></c:set>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
			<%
				Article a = (Article)request.getAttribute("article");
				String tags = "";
				if(a!=null)
				{
					ArrayList<Tag> tagList = a.getTagList();
					
					for(Tag t : tagList)
					{
						tags += t.getTagName();
						tags += ";";
					}
				}
			%>
			<strong>Tags:</strong><br/>
			<input type="text" width="95% " size="100" id="tags" name="tags" value="<%=tags%>"> <br/>
	
			<!-- <c:set var="theUpdate" value="update"/>${method} -->
			<c:choose>
				<c:when test="${method eq theUpdate}">
					<input id="method" type="hidden" name="method" value="update">
					<input type="hidden" name="updateArticleID" value="${article.articleID}">
				</c:when>
				<c:otherwise>
					<input id="method" type="hidden" name="method" value="save">
				</c:otherwise>
			</c:choose>

			<input type="submit" value="Post"/>
			<input type="hidden" name="postAsDraft" id="postAsDraft" value="false">
			<input type="button" value="Save as draft" onclick="save_as_draft()">
			<input type="button" value="Cancel" onclick="window.history.back()">
		</form>
	</div>
</div>

<script type="text/javascript">
    var save = "";
	var options = {
		script: function () {
			var raw = document.getElementById('tags').value;
			var param = encodeURI(document.getElementById('tags').value);
			//alert("raw input:"+raw+"; after encodeURL:"+param);
			var requestURL = encodeURI("./TagSuggestAction?tags="+param);
			//alert("actual send URL:"+requestURL);
			return requestURL;
		},
		varname:'tags',
		json:true,
		shownoresults:false,
		timeout:25000,
		maxresults:6,
		callback: function (obj){
			//alert(obj.id);
			//alert(obj.value);
			//alert(obj.info);
			save += document.getElementById('tags').value;
			save += " ";
			document.getElementById('tags').value = save;
		}
	};
	var as_json = new bsn.AutoSuggest('tags', options);
</script>