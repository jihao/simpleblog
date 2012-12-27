package cn.heapstack.simpleblog.jdbctemplatedaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cn.heapstack.simpleblog.dao.DAOFactory;
import cn.heapstack.simpleblog.dao.IArticleCategoryDAO;
import cn.heapstack.simpleblog.dao.ICategoryDAO;
import cn.heapstack.simpleblog.domain.Category;

public class CategoryDaoJdbcTemplate extends JdbcDaoSupport implements
		ICategoryDAO {

	IArticleCategoryDAO articleCategoryDAO = DAOFactory.getDAOFactory().getArticleCategoryDAO();
	
	@Override
	public boolean addCategory(Category c) {
		int i = getJdbcTemplate().update(
				"insert into category(categoryName, relatedPosts, categoryComments) values(?,?,?)",
				new Object[] { c.getCategoryName(), c.getRelatedPosts(), c.getCategoryComments() },
				new int[] { Types.VARCHAR, Types.INTEGER, Types.VARCHAR });
		return i == 1;
	}

	@Override
	public boolean deleteCategory(String categoryName) {
		int i = getJdbcTemplate().update(
				"delete from category where categoryName = ?",
				new Object[] { categoryName },
				new int[] { Types.VARCHAR });
		return i == 1;

	}

	@Override
	public boolean updateCategory(Category c) {
		int i = getJdbcTemplate().update(
				"update category set categoryComments = ?, relatedPosts = ? where categoryName = ?",
				new Object[] { c.getCategoryComments(), c.getRelatedPosts(), c.getCategoryName() },
				new int[] { Types.VARCHAR, Types.INTEGER, Types.VARCHAR });
		return i == 1;
	}
	
	@Override
	public Category getCategory(String categoryName) {
		return (Category) getJdbcTemplate().queryForObject("select * from category where categoryName = ?", 
				new Object[] { categoryName },
				new int[] { Types.VARCHAR },
				new CategoryRowMapper());
	}
	
	@Override
	public boolean isCategoryExist(String categoryName) {
		int i = getJdbcTemplate().queryForInt("select count(*) from category where categoryName = ?", 
				new Object[] { categoryName },
				new int[] { Types.VARCHAR });
		return i==1;
	}


	@Override
	public List<Category> queryAll() {
		return getJdbcTemplate().query("select * from category", 
				new CategoryRowMapper());
	}

	@Override
	public List<Category> queryByName(String categoryName) {
		List<Category> list = getJdbcTemplate().query("select * from category where categoryName = ?", 
				new Object[] { categoryName },
				new int[] { Types.VARCHAR },
				new CategoryRowMapper());
		for (Category category : list) {
			int relatedPosts = articleCategoryDAO.getArticleCountsRelatedWithCategoryName(category.getCategoryName());
			category.setRelatedPosts(relatedPosts);
			updateCategory(category);
		}
		
		return list;
	}



}
class CategoryRowMapper implements RowMapper
{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Category c = new Category();
		c.setRelatedPosts(rs.getInt("relatedPosts"));
		c.setCategoryName(rs.getString("categoryName"));
		c.setCategoryComments(rs.getString("categoryComments"));
		
		return c;
	}
	
}