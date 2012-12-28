package cn.heapstack.simpleblog.domain;

public class Tag {
	
	private String tagName;
	private int relatedPosts;
	
	public Tag()
	{
		
	}
	public Tag(String tagName) {
		this.tagName = tagName;
	}
	public Tag(String tagName, int relatedPosts) {
		this.tagName = tagName;
		this.setRelatedPosts(relatedPosts);
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	public void setRelatedPosts(int relatedPosts) {
		this.relatedPosts = relatedPosts;
	}
	
	public int getRelatedPosts() {
		return relatedPosts;
	}

}
