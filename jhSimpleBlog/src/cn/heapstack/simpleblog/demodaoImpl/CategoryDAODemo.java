package cn.heapstack.simpleblog.demodaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heapstack.simpleblog.dao.ICategoryDAO;
import cn.heapstack.simpleblog.domain.Category;

public class CategoryDAODemo implements ICategoryDAO {

	Logger logger = LoggerFactory.getLogger(CategoryDAODemo.class);
	
	public static HashMap<String, Category> categoryMap = new HashMap<String, Category>();
	static
	{
		Category java = new Category("Java","Comments for Java");
		java.setCategoryID(10);
		categoryMap.put(String.valueOf(10), java);
		
		for(int i=0;i<10;i++)
		{
			Category c = new Category("Category"+i,"Comments for this category");
			c.setCategoryID(i);
			categoryMap.put(String.valueOf(i), c);
		}
	}
	

	public boolean addCategory(Category c)
	{
		//Normally this should be create category to DB, use the ID as hashMap key
		c.setCategoryID(getNextCategoryID());
		
		categoryMap.put(String.valueOf(c.getCategoryID()), c);
		
		return true;
	}
	

	public boolean deleteCategory(String categoryID)
	{
		categoryMap.remove(String.valueOf(categoryID));
		
		return true;
	}
	

	public boolean updateCategory(Category c)
	{
		categoryMap.put(String.valueOf(c.getCategoryID()), c);
		
		return true;
	}
	

	public Category getCategory(String categoryID)
	{
		return categoryMap.get(String.valueOf(categoryID));
	}
	
	public boolean isCategoryNameExist(String categoryName)
	{
		Iterator<String> keys = categoryMap.keySet().iterator();
		logger.info("isCategoryExist("+categoryName + ") ,Size:" + categoryMap.keySet().size());
		while(keys.hasNext())
		{
			Category c = categoryMap.get(keys.next());
			
			if(c.getCategoryName().equals(categoryName))
			{
				return true;
			}
		}
		return false;
	}
	
	private int getNextCategoryID()
	{
		Iterator<String> keys = categoryMap.keySet().iterator();
		int max = 0;
		while(keys.hasNext())
		{
			int id = Integer.parseInt( keys.next());
			if( id > max)
				max = id;
		}
		logger.info("Next Category ID:"+max);
		return ++max;
	}

	@Override
	public int getCategoryID(String categoryName) {
		Iterator<String> keys = categoryMap.keySet().iterator();
		logger.info("isCategoryExist("+categoryName + ") ,Size:" + categoryMap.keySet().size());
		while(keys.hasNext())
		{
			Category c = categoryMap.get(keys.next());
			
			if(c.getCategoryName().equals(categoryName))
				return c.getCategoryID();
		}
		return -1;
	}
	
	@Override
	public List<Category> queryAll() {
		List<Category> categoryList = new ArrayList<Category>();
		Iterator<String> keys = categoryMap.keySet().iterator();
		
		while(keys.hasNext())
		{
			Category c = categoryMap.get(keys.next());
			categoryList.add(c);
		}
		return categoryList;
	}

	@Override
	public List<Category> queryByName(String categoryName) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isCategoryExist(String categoryName) {
		return categoryMap.keySet().contains(String.valueOf(categoryName));
	}



}
