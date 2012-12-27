<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        	<meta content="application/xhtml+xml; charset=utf-8" http-equiv="Content-Type"/>
	<meta content="The Perfect 2 Column Liquid Layout (left menu): No CSS hacks. SEO friendly. iPhone compatible." name="description"/>
	<meta content="The Perfect 2 Column Liquid Layout (left menu): No CSS hacks. SEO friendly. iPhone compatible." name="keywords"/>
	<meta content="index, follow" name="robots"/>

	<link type="image/x-icon" href="image/simpleblog.png" rel="shortcut icon"/>
	<link rel=StyleSheet type="text/css" href="style/homestyle.css">
	<link rel=StyleSheet type="text/css" href="style/comments.css">
	<link rel=StyleSheet type="text/css" href="style/messageboxcss/messageBoxes.css">
	
        <title>Login</title>
<style>
	.outerdiv{
		left: 200px; 
		top: 200px; 
		width: 500px; 
		position: relative; 
	}
	.contentA{
		padding:10px;
		width:100%;
		border-width: 0px; 
	}
	
	.row{
		background-color:none;
		display:block;
		height: 30px;
		border-width: 0px; 
	}
	.left{
		width:150px;
		float:left;
		padding:0px 0px 0px 30px;
		min-height:24px;
		border-width: 0px; 
	}
	
	.over{
		background-color:#7DFF52;
	}
	.out{
		background-color:none;
	}
}

</style>
<script type="text/javascript" src="js/jquery-1.2.6.js"></script>
<script type="text/javascript" src="js/validate/jquery.validate.js"></script>
		
<script>
		var loginForm;
		$(document).ready(function()
		{
			loginForm = $("#loginForm").validate();
			
			$('.contentA .left, .contentA input, .contentA textarea, .contentA select').focus(function(){
				$(this).parents('.row').addClass("over");
			}).blur(function(){
				$(this).parents('.row').removeClass("over");
			});
		});

	  		
</script>
</head>
    <body>
        <script type="text/javascript">
           function validate(){
				var r1 = loginForm.element("#userId")
				var r2 = loginForm.element("#password")
				if (r1 && r2 ){return true;}
				else{return false;}
            }
        </script>
        <div class="outerdiv">
			<h3 align="center">Simple Blog Login Page</h3>
            <form method="post" action="Login" id="loginForm" onsubmit="return validate();">
                <table class="contentA" cellspacing="0">
                    <tr class="row">
                        <td class="left">
                            <label for="userId" >UserName</label>
                        </td>
                        <td>
                            <input type="text" class="required" name="userId" id="userId"/>
                        </td>
                    </tr>
                    
                   
                    <tr class="row" >
                        <td class="left">
							 <label for="password1" >Password</label>
                        </td>
                        <td>
                            <input id="password1" class="required" type="password" name="password" />
                        </td>
                    </tr>
                    
					<tr class="row">
                        <td colspan="2" >
                           <div class="${message.style}">
							${message.content}
							</div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="Login" />&nbsp;&nbsp;<input type="reset" />
						&nbsp;&nbsp;<input type="button" onclick="window.open('register.jsp','_self')" value="Register">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>