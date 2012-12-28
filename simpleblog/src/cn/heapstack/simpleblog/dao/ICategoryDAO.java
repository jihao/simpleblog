package cn.heapstack.simpleblog.dao;

import java.util.List;

import cn.heapstack.simpleblog.domain.Category;

public interface ICategoryDAO {

	boolean addCategory(Category c);

	Category getCategory(String categoryName);

	boolean updateCategory(Category c);

	boolean deleteCategory(String categoryName);
	
	/**
	 * Check if the category exist by category ID
	 * 
	 * @param categoryName
	 * @return
	 */
	boolean isCategoryExist(String categoryName);
	

	List<Category> queryAll();
	
	List<Category> queryByName(String categoryName);
}