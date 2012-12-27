package cn.heapstack.simpleblog.demodaoImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heapstack.simpleblog.dao.ITagDAO;
import cn.heapstack.simpleblog.domain.Tag;

public class TagDAODemo implements ITagDAO {

	Logger logger = LoggerFactory.getLogger(TagDAODemo.class);
	
	public static HashMap<String, Tag> TagMap = new HashMap<String, Tag>();
	static
	{
		Tag java = new Tag("Java");
		java.setTagID(10);
		TagMap.put(String.valueOf(10), java);
		
		for(int i=0;i<10;i++)
		{
			Tag c = new Tag("Tag"+i);
			c.setTagID(i);
			c.setRelatedPosts(i);
			TagMap.put(String.valueOf(i), c);
		}
	}
	

	public boolean addTag(Tag c)
	{
		//Normally this should be create Tag to DB, use the ID as hashMap key
		c.setTagID(getNextTagID());
		c.setRelatedPosts( c.getRelatedPosts() + 1 );
		
		TagMap.put(String.valueOf(c.getTagID()), c);
		return true;
	}
	

	public boolean deleteTag(String tagName)
	{
		TagMap.remove(String.valueOf(tagName));
		return true;
	}
	

	public boolean updateTag(Tag c)
	{
		TagMap.put(String.valueOf(c.getTagID()), c);
		return true;
	}
	

	public Tag getTag(int tagID)
	{
		return TagMap.get(String.valueOf(tagID));
	}
	
	public Tag getTag(String tagName) {
		
		Iterator<String> keys = TagMap.keySet().iterator();
		logger.info("getTag("+tagName + ") ,TagMap Size:" + TagMap.keySet().size());
		while(keys.hasNext())
		{
			Tag c = TagMap.get(keys.next());
			
			if(c.getTagName().equals(tagName))
				return c;
		}
		return null;
	}
	
	public boolean isTagExist(String tagName)
	{
		
		Iterator<String> keys = TagMap.keySet().iterator();
		logger.info("isTagExist("+tagName + ") ,TagMap Size:" + TagMap.keySet().size());
		while(keys.hasNext())
		{
			Tag c = TagMap.get(keys.next());
			
			if(c.getTagName().equals(tagName))
				return true;
		}
		return false;
	}
	
	@Override
	public boolean checkExist(String tagID) {
		return getTag(tagID) != null;
	}

	
	private int getNextTagID()
	{
		Iterator<String> keys = TagMap.keySet().iterator();
		int max = 0;
		while(keys.hasNext())
		{
			int id = Integer.parseInt( keys.next());
			if( id > max)
				max = id;
		}
		logger.info("Next Tag ID:"+max);
		return ++max;
	}

	@Override
	public int getTagID(String tagName) {
		Iterator<String> keys = TagMap.keySet().iterator();
		logger.info("isTagExist("+tagName + ") ,Size:" + TagMap.keySet().size());
		while(keys.hasNext())
		{
			Tag c = TagMap.get(keys.next());
			
			if(c.getTagName().equals(tagName))
				return c.getTagID();
		}
		return -1;
	}
	@Override
	public List<Tag> queryAll() {
		Collection<Tag> tmp = TagMap.values();
		List<Tag> result = new ArrayList<Tag>();
		for (Tag tag : tmp) {
			result.add(tag);
		}
		return result;
	}

	@Override
	public List<Tag> queryByName(String TagName) {
		// TODO Auto-generated method stub
		return null;
	}







}
