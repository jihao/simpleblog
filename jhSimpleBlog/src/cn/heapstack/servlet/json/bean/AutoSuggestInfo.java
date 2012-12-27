package cn.heapstack.servlet.json.bean;


public class AutoSuggestInfo {

	private int id;
	private String value;
	private String info;
	
	public AutoSuggestInfo()
	{
		
	}
	static int i = 0;
	public AutoSuggestInfo(String val)
	{
		this.id = i++;
		this.value = val;
		this.info = "info for "+val;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	

}
