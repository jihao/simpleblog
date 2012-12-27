package cn.heapstack.simpleblog.utils.bean;

public class Message {

	private MessageStyle style;
	private String content;
	
	public Message()
	{
	}
	
	public Message(MessageStyle style, String content)
	{
		this.style = style;
		this.content = content;
	}
	
	public MessageStyle getStyle() {
		return style;
	}
	public void setStyle(MessageStyle style) {
		this.style = style;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}


