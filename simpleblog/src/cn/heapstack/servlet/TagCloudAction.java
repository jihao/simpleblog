package cn.heapstack.servlet;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heapstack.simpleblog.dao.DAOFactory;
import cn.heapstack.simpleblog.dao.ITagDAO;
import cn.heapstack.simpleblog.domain.Tag;


public class TagCloudAction extends HttpServlet {


	private static String cache = "";
	/**
	 * 
	 */
	private static final long serialVersionUID = -7031695721764039045L;
	
	private DAOFactory daoFactory = DAOFactory.getDAOFactory();

	
	Logger logger = LoggerFactory.getLogger(TagCloudAction.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setHeader( "Pragma", "no-cache" );
	    resp.addHeader( "Cache-Control", "must-revalidate" );
	    resp.addHeader( "Cache-Control", "no-cache" );
	    resp.addHeader( "Cache-Control", "no-store" );
	    resp.setDateHeader("Expires", 0);
		resp.setContentType("text/xml");
		resp.setCharacterEncoding("UTF-8");
//		if(cache.isEmpty())
//		{
			cache = getTagCloudJSONString();
//		}
		resp.getOutputStream().write(cache.getBytes("UTF-8"));
		resp.flushBuffer();
	}
	
	private String getTagCloudJSONString()
	{
		ITagDAO tagDAO = daoFactory.getTagDAO();
/*		Category c = new Category("Name","This is comments",10);
		c.setCategoryID(10);
		//logger.info( JSONSerializer.toJSON(c).toString() );
		//logger.info.println( JSONSerializer.toJSON(c).toString(2) );
		
		ArrayList<Category> categoriesList = new ArrayList<Category>();
		
		Random r = new Random();
		String[] tags = new String[]{
				"JAVA","Groovy","Servlet","J2EE","JSP","J2SE"
				,"JSON","AJAX","CaiClient",".NET","C#","Perl",
				"Python","Rails","Ruby","Boss","Nokia","GPhone","iPhone"
				,"HiPhone","Ericsson","Semens","Novels","春天","夏天","秋天","冬天","节日快乐",
				"破釜沉舟","瑞星杀毒","事故"
				,"奥运会","Grails","Google","Baidu","XiaoNei",
				"开心网","校内网","海内网","都是","萨达姆","PK","网摘"
		};
		int len = tags.length-1;
		for (int i = 0; i < 100; i++) {
			Category item = new Category(tags[r.nextInt(len)],"This is comments for "+i,r.nextInt(100));
			item.setCategoryID(i);
			categoriesList.add(item);
		}*/
		
		List<Tag> tagsList = tagDAO.queryAll();
		
		System.err.println( JSONSerializer.toJSON(tagsList).toString() );
		//System.err.println( JSONSerializer.toJSON(categoriesList).toString(2) );
		
		return JSONSerializer.toJSON(tagsList).toString(2);
	}

}
