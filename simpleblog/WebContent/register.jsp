<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Version 1</title>
        <link rel=StyleSheet type="text/css" href="style/homestyle.css">
<style>
	.outerdiv{
		left: 200px; 
		top: 200px; 
		width: 400px; 
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
		height: 40px;
		border-width: 0px; 
	}
	.left{
		width:150px;
		float:left;
		padding:10px 0px 0px 10px;
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
<script>
		$(document).ready(function()
		{
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
            var errorMessage = "";
			var errorFields = new Array();
			
			Require = /.+/;
			Email = /^\w+([-+.]\w+)*@\w+([-.]\\w+)*\.\w+([-.]\w+)*$/;

            function verifyUserId(){
                var userName = document.getElementById("userId");
				var re = new RegExp(Require);
				
                if (!re.test(userName.value)) {
					errorFields.concat("userId");
					errorMessage += "UserName can't be empty. <br />"
                    return false;
                }
				if(userName.length < 5)
				{
					errorFields.concat("userId");
					errorMessage += "UserName length can't be less than 5. <br />";
                    return false;
				}
				return true;
            }
            
            function verifyEmail(){
				var email = document.getElementById("email");
				var re = new RegExp(Email);
				if(!re.test(email.value))
				{
					errorFields.concat("email");
					errorMessage += "Email formart is not correct. <br />"
					return false;
				}
				return true;
            }
            
            function verifyPassword(){
                var pwd_1 = document.getElementById("password1");
                var pwd_2 = document.getElementById("password2");
                var re = new RegExp(Require);
				
                if (!re.test(pwd_1.value)) {
					errorFields.concat("password1");
                	errorMessage += "password can not be empty!<br />";
					return false;
                }
                else if(!re.test(pwd_2.value)) {
                	errorFields.concat("password2");
                	errorMessage += "password can not be empty!<br />";
					return false;
                }
				else if (pwd_1.value != pwd_2.value) {
                    errorFields.concat("password1");
					errorFields.concat("password2");
					errorMessage += "Two passwords are not the same!<br />";
                    pwd_1.value = "";
                    pwd_2.value = "";
					return false;
                }
				
				return true;
            }
            
            function validateInput(){
				var result = true;
				v1 = verifyUserId();
				v2 = verifyEmail(); 
				v3 = verifyPassword();
				result = (v1 && v2) && v3;
				if(!result)
				{
					alert(errorMessage);
					var errorField = document.getElementById("errorField");
					
					errorField.innerHTML = errorMessage.fontcolor("red");
					alert(errorField.innerHTML);
					
					errorMessage = ""
				}
				//alert(result);
				return result;
            }
        </script>
        <div class="outerdiv">
        	<h3 align="center">Simple Blog Register Page</h3>
            <form method="post" action="Register" id="form" onsubmit="return validateInput();">
                <table class="contentA" cellspacing="0">
                    <tr class="row">
                        <td class="left">
                            UserName:
                        </td>
                        <td>
                            <input type="text" name="userId" id="userId"/>
                        </td>
                    </tr>
                    <tr class="row">
                        <td class="left">
                            Email:
                        </td>
                        <td>
                            <input type="text" name="email" />
                        </td>
                    </tr>
                    <tr class="row">
                        <td class="left">
                            Nick Name:
                        </td>
                        <td>
                            <input type="text" name="nickName" />
                        </td>
                    </tr>
                    <tr class="row">
                        <td class="left">
                            Password:
                        </td>
                        <td>
                            <input id="password1" type="password" name="password" />
                        </td>
                    </tr>
                    <tr class="row">
                        <td class="left">
                            Confirm Password:
                        </td>
                        <td>
                            <input id="password2" type="password" name="cpassword"/>
                        </td>
                    </tr>
					<tr class="row">
                        <td colspan="2">
                            <div class="errorMessage" id="errorField" align="center"></div>
                        </td>
                    </tr>
                    <tr >
                        <td colspan="2" align="center">
                            <input type="submit" value="Register" />&nbsp;&nbsp;<input type="reset" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>