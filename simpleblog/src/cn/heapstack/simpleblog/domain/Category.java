package cn.heapstack.simpleblog.domain;


public class Category {

	private String categoryName;
	private String categoryComments;
	private int relatedPosts;
	
	public Category()
	{
		
	}
	public Category(String categoryName) {
		this.categoryName = categoryName;
	}
	public Category(String categoryName, String categoryComments) {
		this.categoryName = categoryName;
		this.categoryComments = categoryComments;
	}
	public Category(String categoryName, String categoryComments, int relatedPosts) {
		this.categoryName = categoryName;
		this.categoryComments = categoryComments;
		this.setRelatedPosts(relatedPosts);
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryComments() {
		return categoryComments;
	}
	public void setCategoryComments(String categoryComments) {
		this.categoryComments = categoryComments;
	}
	public void setRelatedPosts(int relatedPosts) {
		this.relatedPosts = relatedPosts;
	}
	public int getRelatedPosts() {
		return relatedPosts;
	}

}
