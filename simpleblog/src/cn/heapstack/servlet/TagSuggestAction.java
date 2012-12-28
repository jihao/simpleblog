package cn.heapstack.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;
import cn.heapstack.servlet.json.bean.AutoSuggestInfo;
import cn.heapstack.servlet.json.bean.Results;

/**
 * Servlet implementation class TagSuggestAction
 */
public class TagSuggestAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TagSuggestAction() {
        super();
    }

    private static ArrayList<String> array = new ArrayList<String>();
    static
    {
    	String [] source = new String[]{
    			"中文",
    			"中文1",
    			"中文2",
    			"中文3",
		"Altman, Alisha",
		"Archibald, Janna",
		"Auman, Cody",
		"Bagley, Sheree",
		"Ballou, Wilmot",
		"Bard, Cassian",
		"Bash, Latanya",
		"Beail, May",
		"Black, Lux",
		"Bloise, India",
		"Blyant, Nora",
		"Bollinger, Carter",
		"Burns, Jaycob",
		"Carden, Preston",
		"Carter, Merrilyn",
		"Christner, Addie",
		"Churchill, Mirabelle",
		"Conkle, Erin",
		"Countryman, Abner",
		"Courtney, Edgar",
		"Cowher, Antony",
		"Craig, Charlie",
		"Cram, Zacharias",
		"Cressman, Ted",
		"Crissman, Annie",
		"Davis, Palmer",
		"Downing, Casimir",
		"Earl, Missie",
		"Eckert, Janele",
		"Eisenman, Briar",
		"Fitzgerald, Love",
		"Fleming, Sidney",
		"Fuchs, Bridger",
		"Fulton, Rosalynne",
		"Fye, Webster",
		"Geyer, Rylan",
		"Greene, Charis",
		"Greif, Jem",
		"Guest, Sarahjeanne",
		"Harper, Phyllida",
		"Hildyard, Erskine",
		"Hoenshell, Eulalia",
		"Isaman, Lalo",
		"James, Diamond",
		"Jenkins, Merrill",
		"Jube, Bennett",
		"Kava, Marianne",
		"Kern, Linda",
		"Klockman, Jenifer",
		"Lacon, Quincy",
		"Laurenzi, Leland",
		"Leichter, Jeane",
		"Leslie, Kerrie",
		"Lester, Noah",
		"Llora, Roxana",
		"Lombardi, Polly",
		"Lowstetter, Louisa",
		"Mays, Emery",
		"Mccullough, Bernadine",
		"Mckinnon, Kristie",
		"Meyers, Hector",
		"Monahan, Penelope",
		"Mull, Kaelea",
		"Newbiggin, Osmond",
		"Nickolson, Alfreda",
		"Pawle, Jacki",
		"Paynter, Nerissa",
		"Pinney, Wilkie",
		"Pratt, Ricky",
		"Putnam, Stephanie",
		"Ream, Terrence",
		"Rumbaugh, Noelle",
		"Ryals, Titania",
		"Saylor, Lenora",
		"Schofield, Denice",
		"Schuck, John",
		"Scott, Clover",
		"Smith, Estella",
		"Smothers, Matthew",
		"Stainforth, Maurene",
		"Stephenson, Phillipa",
		"Stewart, Hyram",
		"Stough, Gussie",
		"Strickland, Temple",
		"Sullivan, Gertie",
		"Swink, Stefanie",
		"Tavoularis, Terance",
		"Taylor, Kizzy",
		"Thigpen, Alwyn",
		"Treeby, Jim",
		"Trevithick, Jayme",
		"Waldron, Ashley",
		"Wheeler, Bysshe",
		"Whishaw, Dodie",
		"Whitehead, Jericho",
		"Wilks, Debby",
		"Wire, Tallulah",
		"Woodworth, Alexandria",
		"Zaun, Jillie"};
    	
    	array.addAll(Arrays.asList(source));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
/*		Enumeration<String> params = request.getParameterNames();
		while(params.hasMoreElements())
		{
			String key = params.nextElement();
			String value = request.getParameter(key);
			System.out.println(key + ":" + value);
		}*/
		
/*		String myparam = request.getParameter("tagName");
		myparam = new String(myparam.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println(myparam);*/
		
		System.out.println( URLEncoder.encode("中", "ISO-8859-1") );//%3F
		System.out.println( URLEncoder.encode("中", "UTF-8") );//%E4%B8%AD
		
		System.out.println(request.getParameter("tags"));//%E4%B8%AD
		String chineseParamTagName = URLDecoder.decode(request.getParameter("tags"), "UTF-8");
		System.out.println(chineseParamTagName);
		System.out.println(URLDecoder.decode(chineseParamTagName, "UTF-8"));
		
		String[] tags = chineseParamTagName.split("[ |\\||,|;]+");
		
		chineseParamTagName=tags[tags.length-1];
		System.out.println(chineseParamTagName);
		
		List<AutoSuggestInfo> result = getSuggest(chineseParamTagName);
		Results results = new Results();
		results.setResults(result);
		String cache = JSONSerializer.toJSON(results).toString(2);
		System.out.println(cache);
		resp.setHeader( "Pragma", "no-cache" );
	    resp.addHeader( "Cache-Control", "must-revalidate" );
	    resp.addHeader( "Cache-Control", "no-cache" );
	    resp.addHeader( "Cache-Control", "no-store" );
	    resp.setDateHeader("Expires", 0);
		resp.setContentType("application/json");
			
		resp.setCharacterEncoding("UTF-8");
		resp.getOutputStream().write(cache.getBytes("UTF-8"));
		resp.flushBuffer();
	}

	private List<AutoSuggestInfo> getSuggest(String input) {
		List<AutoSuggestInfo> result = new ArrayList<AutoSuggestInfo>();
		for (String s : array) {
			if(s.startsWith(input))
				result.add(new AutoSuggestInfo(s));
		}
		return result;
	}

}


