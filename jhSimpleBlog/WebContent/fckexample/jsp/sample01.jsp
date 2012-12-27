<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="net.fckeditor.*" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%--
 * FCKeditor - The text editor for Internet - http://www.fckeditor.net
 * Copyright (C) 2003-2008 Frederico Caldeira Knabben
 *
 * == BEGIN LICENSE ==
 *
 * Licensed under the terms of any of the following licenses at your
 * choice:
 *
 *  - GNU General Public License Version 2 or later (the "GPL")
 *    http://www.gnu.org/licenses/gpl.html
 *
 *  - GNU Lesser General Public License Version 2.1 or later (the "LGPL")
 *    http://www.gnu.org/licenses/lgpl.html
 *
 *  - Mozilla Public License Version 1.1 or later (the "MPL")
 *    http://www.mozilla.org/MPL/MPL-1.1.html
 *
 * == END LICENSE ==
 * @version: $Id: sample01.jsp 2167 2008-07-03 21:33:15Z mosipov $
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>FCKeditor - JSP Sample</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="robots" content="noindex, nofollow" />
		<link href="../sample.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" href="../fckeditor.gif"
				type="image/x-icon" />
		<script type="text/javascript">
			function FCKeditor_OnComplete(editorInstance) {
				window.status = editorInstance.Description;
			}
		</script>
	</head>
	<%
		FCKeditor fckEditor = new FCKeditor(request, "EditorDefault");
	%>
	<body>
		<h1>FCKeditor - JSP - Sample 1</h1>
		<p>This sample displays a normal HTML form with an FCKeditor with
		full features enabled.</p>
		<p>Basic FCKeditor informations:</p>
		<ul>
			<li><FCK:check command="CompatibleBrowser" /></li>
			<li><FCK:check command="FileBrowsing" /></li>
			<li><FCK:check command="FileUpload" /></li>
		</ul>
		<hr />
		<form action="sampleposteddata.jsp" method="post" target="_blank">
		<%
			fckEditor.setValue("This is some <strong>sample text</strong>. You are using <a href=\"http://www.fckeditor.net\">FCKeditor</a>.");
			out.println(fckEditor);
		%>
		<br />
		<input type="submit" value="Submit" /></form>
	</body>
</html>