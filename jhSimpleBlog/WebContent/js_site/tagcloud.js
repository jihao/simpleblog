var XMLHttpReq;
var maxTagPosts = 1; // 这个变量用来保存包含关联文章最多的那个Tag的文章数

// 创建XMLHttpRequest对象
function createXMLHttpRequest() {
	if (window.XMLHttpRequest) { // Mozilla 浏览器
		XMLHttpReq = new XMLHttpRequest();
	} else if (window.ActiveXObject) { // IE浏览器
		try {
			XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
}
// 发送请求函数
function sendRequest(url, callback) {
	createXMLHttpRequest();
	XMLHttpReq.open("GET", url, true);
	//XMLHttpReq.onreadystatechange = processResponse;// 指定响应函数
	XMLHttpReq.onreadystatechange = callback;
	XMLHttpReq.send(null); // 发送请求
}

// 处理返回信息函数
function processResponseStyle1() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
						
			var tagCloud = JSON.parse( XMLHttpReq.responseText );
			for(var i=0; i< tagCloud.length;i++)
			{
				if(tagCloud[i].relatedPosts>maxTagPosts)
				{
					maxTagPosts = tagCloud[i].relatedPosts;
				}
			}

			var tagCloudHTML = "";
			for(var j=0; j< tagCloud.length;j++)
			{
				var searchCondition = encodeURI(tagCloud[j].tagName);
				
				var classSize = getClass(tagCloud[j].relatedPosts);
				var aTag = "<span class='"+classSize+"'><a href=\"Posts?method=search&tagName="+tagCloud[j].tagName
				+"\" title=\"Posts: "+tagCloud[j].relatedPosts+"\">"+tagCloud[j].tagName+"</a></span>\n";
				
				tagCloudHTML += aTag;
			}
			//Posts?method=search&tagID=1
			document.getElementById("tagcloud").innerHTML = tagCloudHTML;
		} else { // 页面不正常
			window.alert("Page Exception!");
		}
	}
}

function processResponseStyle2() {
	if (XMLHttpReq.readyState == 4) {
		if (XMLHttpReq.status == 200) {
			var tagCloud = JSON.parse( XMLHttpReq.responseText );
			for(var i=0; i< tagCloud.length;i++)
			{
				if(tagCloud[i].relatedPosts>maxTagPosts)
				{
					maxTagPosts = tagCloud[i].relatedPosts;
				}
			}
			
			var tagCloudHTML = "";
			for(var j=0; j< tagCloud.length;j++)
			{
				var searchCondition = encodeURI( tagCloud[j].tagName );
				var classSize = getClass(tagCloud[j].relatedPosts);
				var aTag = "<span class='"+classSize+"'><a href='Posts?method=search&tagName="+tagCloud[j].tagName
					+ "' title='Posts: "+tagCloud[j].relatedPosts+"'"
					+ " style='color:" + getRandomColor() +"' >"
					+ tagCloud[j].tagName + "</a></span>\n";
			
				tagCloudHTML += aTag;
			}			
			document.getElementById("tagcloudwithstyle2").innerHTML = tagCloudHTML;
		} else {
			window.alert("Page Exception!");
		}
	}
}

function getRandomColor()
{
	var r=Math.floor((Math.random()*256)).toString(16);
	var g=Math.floor((Math.random()*256)).toString(16);
	var b=Math.floor((Math.random()*256)).toString(16);
	var colorString="#"+r+g+b;
	return colorString;
}
function getTagCloud() {
	sendRequest('getTagCloud',processResponseStyle1);
}
function getTagCloud2() {
	sendRequest('getTagCloud',processResponseStyle2);
}

function getClass(relatedPosts)
{
	var presentage = Math.floor((relatedPosts/maxTagPosts)*100);
	var classSize;
	if(presentage<20)
	{
		classSize = 'smallest';
	}
	else if(20<=presentage && presentage <40)
	{
		classSize = 'small';
	}
	else if(40<=presentage && presentage <60)
	{
		classSize = 'medium';
	}
	else if(60<=presentage && presentage <80)
	{
		classSize = 'large';
	}
	else if(80<=presentage)
	{
		classSize = 'largest';
	}
	return classSize;
}

window.onload = function(){
	createXMLHttpRequest();
	getTagCloud();
	//getTagCloud2();
}
