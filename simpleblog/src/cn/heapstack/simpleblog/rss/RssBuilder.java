package cn.heapstack.simpleblog.rss;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heapstack.rss.EmailAddress;
import cn.heapstack.rss.Guid;
import cn.heapstack.rss.Rss;
import cn.heapstack.rss.RssChannel;
import cn.heapstack.rss.RssItem;
import cn.heapstack.rss.SimpleRssGenerator;
import cn.heapstack.simpleblog.dao.DAOFactory;
import cn.heapstack.simpleblog.dao.IArticleDAO;
import cn.heapstack.simpleblog.domain.Article;
import cn.heapstack.simpleblog.domain.Category;
import cn.heapstack.simpleblog.domain.Comments;

public class RssBuilder extends TimerTask {

	Logger logger = LoggerFactory.getLogger(RssBuilder.class);
	
	int size;
	String file;
	public RssBuilder(int size, String file)
	{
		this.size = size;
		this.file = file;
	}
	
	@Override
	public void run() {
		generateRss(this.size, this.file);
		logger.info("RSS FILE GENERATED, file location: {}", this.file);
	}

	public void generateRss(int size, String file) {
		DAOFactory daoFactory = DAOFactory.getDAOFactory();
		IArticleDAO articleDAO = daoFactory.getArticleDAO();

		List<Article> articleList = articleDAO.queryByPage(1, size);
		Rss rss = new Rss();
		RssChannel channel = new RssChannel();
		channel.setGenerator("rss_generator_v0.1");
		channel.setPubDate(new Date());
		channel.setCopyright("copyright");
		channel.setLastBuildDate(new Date());
		channel.setLink("http://localhost:8080/jhSimpleBlog/rss.xml");
		EmailAddress emailAddress = new EmailAddress();
		emailAddress.setEmail("jihao@heapstack.cn");
		channel.setManagingEditor(emailAddress);
		channel.setWebMaster(emailAddress);
		
		for (int i = 0; i < articleList.size(); i++) {
			Article a = articleList.get(i);
			RssItem item = new RssItem();
			item.setDescription( a.getContent() );
			item.setTitle(a.getTitle());
			item.setPubDate(a.getPostDate());
			Guid guid = new Guid();
			guid.setGuid(a.getArticleID());
			item.setGuid(guid);
			
			String categoryNames = "";
			for (Category c : a.getCategoryList()) {
				categoryNames += c.getCategoryName();
				categoryNames += " ";
			} 
			cn.heapstack.rss.Category cc = new cn.heapstack.rss.Category();
			cc.setCategory(categoryNames);
			cc.setDomain("");
			item.setCategory(cc);
			
			String commentsListStr = "";
			for (Comments comments : a.getCommentsList()) {
				commentsListStr += comments.getUserName()+" : "+comments.getContent()+"<br/>";
			}
			item.setComments(commentsListStr);
			item.setLink("http://localhost:8080/jhSimpleBlog/Posts?method=view&articleID="+a.getArticleID());
			
			channel.getItemList().addFirst(item);
		}
		rss.setChannel(channel);
		
		try {
			SimpleRssGenerator.generate(rss, new FileOutputStream(file));
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			if(logger.isDebugEnabled())
			{
				e.printStackTrace();
			}
		} 
	}
}
