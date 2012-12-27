package cn.heapstack.servlet.json.bean;
import java.util.List;


public class Results {

	private List<AutoSuggestInfo> results;

	public List<AutoSuggestInfo> getResults() {
		return results;
	}

	public void setResults(List<AutoSuggestInfo> results) {
		this.results = results;
	}
}
