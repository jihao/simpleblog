<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
 * @version: $Id: sampleslist.jsp 2167 2008-07-03 21:33:15Z mosipov $
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>FCKeditor - Sample Selection</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="robots" content="noindex, nofollow" />
		<link href="sample.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			if (window.top == window)
				document.location = 'index.jsp' ;

			function OpenSample(sample) {
				if (sample.length > 0)
					window.open( sample, 'Sample' );
			}
		</script>
	</head>
	<body>
		<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>Please select the sample you want to view: <br />
				<select onchange="OpenSample(this.value);">
					<option value="jsp/sample01.jsp" selected="selected">
					Sample 01: Editor with all features generated via API</option>
					<option value="jsp/sample02.jsp">Sample 02: Editor with all 
					features generated with JSP Taglib</option>
					<option value="jsp/sample03.jsp">Sample 03:	Multi-language 
					support</option>
					<option value="jsp/sample04.jsp">Sample 04: Toolbar
					selection</option>
					<option value="jsp/sample05.jsp">Sample 05: Skins
					support</option>
					<option value="jsp/sample06.jsp">Sample 06: Plugins
					support</option>
					<option value="jsp/sample07.jsp">Sample 07: Full Page
					editing</option>
				</select></td>
			</tr>
		</table>
	</body>
</html>
