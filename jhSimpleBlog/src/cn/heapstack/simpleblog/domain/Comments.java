package cn.heapstack.simpleblog.domain;

import java.util.Date;

public class Comments {

	private String commentsID;
	private String theCommentsArticleID;
	private String title;
	private String userName;
	private String homePageURL;
	private String userEmail;
	private String content;
	private Date postDate = new Date();
	
	public Comments()
	{
		
	}
	public Comments(String theCommentsArticleID, String title, String userName, String userEmail)
	{
		this.theCommentsArticleID = theCommentsArticleID;
		this.title = title;
		this.userName = userName;
		this.userEmail = userEmail;
	}
	
	public Comments(String theCommentsEntryID, String title, String userName, String userEmail, String homePageURL)
	{
		this.theCommentsArticleID = theCommentsEntryID;
		this.title = title;
		this.userName = userName;
		this.userEmail = userEmail;
		this.homePageURL = homePageURL;
	}
	
	public String getCommentsID() {
		return commentsID;
	}
	public void setCommentsID(String commentsID) {
		this.commentsID = commentsID;
	}
	
	public String getTheCommentsArticleID() {
		return theCommentsArticleID;
	}
	public void setTheCommentsArticleID(String theCommentsArticleID) {
		this.theCommentsArticleID = theCommentsArticleID;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHomePageURL() {
		return homePageURL;
	}

	public void setHomePageURL(String homePageURL) {
		this.homePageURL = homePageURL;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return content;
	}

}
