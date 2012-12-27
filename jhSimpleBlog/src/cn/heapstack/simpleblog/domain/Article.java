package cn.heapstack.simpleblog.domain;

import java.util.ArrayList;
import java.util.Date;

public class Article implements Comparable<Article>{
	private String articleID;
	private boolean asDraft;
	private String title;
	private String content;
	private ArrayList<Category> categoryList = new ArrayList<Category>();
	private ArrayList<Tag> tagList = new ArrayList<Tag>();
	private Date postDate = new Date();
	private ArrayList<Comments> commentsList = new ArrayList<Comments>();
	private User author;
	/**
	 * For further extension
	 */
	private boolean setTop;

	/**
	 * For further extension, make it easy to be searched by google
	 */
	private String staticLinkURL;

	public String getArticleID() {
		return articleID;
	}

	public void setArticleID(String articleID) {
		this.articleID = articleID;
	}

	public boolean isAsDraft() {
		return asDraft;
	}

	public void setAsDraft(boolean asDraft) {
		this.asDraft = asDraft;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArrayList<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(ArrayList<Category> categoryList) {
		this.categoryList = categoryList;
	}
	
	public ArrayList<Tag> getTagList() {
		return tagList;
	}

	public void setTagList(ArrayList<Tag> tagList) {
		this.tagList = tagList;
	}

	public ArrayList<Comments> getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(ArrayList<Comments> commentsList) {
		this.commentsList = commentsList;
	}
	public boolean isSetTop() {
		return setTop;
	}

	public void setSetTop(boolean setTop) {
		this.setTop = setTop;
	}

	public String getStaticLinkURL() {
		return staticLinkURL;
	}

	public void setStaticLinkURL(String staticLinkURL) {
		this.staticLinkURL = staticLinkURL;
	}
	
	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	@Override
	public int compareTo(Article o) {
		return postDate.compareTo(o.getPostDate());
	}

}
