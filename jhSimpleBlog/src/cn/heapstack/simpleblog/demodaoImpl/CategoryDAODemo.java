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
		
		categoryMap.put("Java", java);
		
		for(int i=0;i<10;i++)
		{
			Category c = new Category("Category"+i,"Comments for this category");
			categoryMap.put("Category"+i, c);
		}
	}
	

	public boolean addCategory(Category c)
	{
		categoryMap.put(String.valueOf(c.getCategoryName()), c);
		
		return true;
	}
	

	public boolean deleteCategory(String categoryID)
	{
		categoryMap.remove(String.valueOf(categoryID));
		
		return true;
	}
	

	public boolean updateCategory(Category c)
	{
		categoryMap.put(String.valueOf(c.getCategoryName()), c);
		
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
