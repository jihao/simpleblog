package cn.heapstack.simpleblog.jdbctemplatedaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cn.heapstack.simpleblog.dao.DAOFactory;
import cn.heapstack.simpleblog.dao.IArticleTagDAO;
import cn.heapstack.simpleblog.dao.ITagDAO;
import cn.heapstack.simpleblog.domain.Tag;

public class TagDaoJdbcTemplate extends JdbcDaoSupport implements ITagDAO {

	IArticleTagDAO articleTagDAO = DAOFactory.getDAOFactory().getArticleTagDAO();
	
	@Override
	public boolean addTag(Tag tag) {
		int i = getJdbcTemplate().update(
				"INSERT INTO tag(tagName,relatedPosts) VALUES (?,?)",
				new Object[] { tag.getTagName(), tag.getRelatedPosts() },
				new int[] { Types.VARCHAR, Types.INTEGER });
		return i == 1;
	}

	@Override
	public boolean deleteTag(String tagName) {
		int i = getJdbcTemplate().update("DELETE FROM tag WHERE tagName = ?",
				new Object[] { tagName }, 
				new int[] { Types.VARCHAR });
		return i == 1;

	}

	@Override
	public boolean updateTag(Tag tag) {
		int i = getJdbcTemplate().update(
						"update tag set relatedPosts = ? WHERE tagName = ?",
						new Object[] { tag.getRelatedPosts(), tag.getTagName() },
						new int[] {  Types.INTEGER, Types.VARCHAR });
		return i == 1;

	}



	@Override
	public Tag getTag(String tagName) {
		return (Tag) getJdbcTemplate().queryForObject(
				"select * from tag WHERE tagName = ?",
				new Object[] { tagName }, 
				new int[] { Types.VARCHAR },
				new TagRowMapper());
	}

	@Override
	public boolean isTagExist(String tagName) {
		int i = getJdbcTemplate().queryForInt("select count(*) from tag WHERE tagName = ?",
				new Object[] { tagName }, 
				new int[] { Types.VARCHAR });
		return (i > 0);
	}

	@Override
	public List<Tag> queryAll() {
		List<Tag> tags = getJdbcTemplate().query("SELECT * FROM tag", new TagRowMapper());
		for (Tag tag : tags) {
			int relatedPosts = articleTagDAO.getArticleCountsRelatedWithTagName(tag.getTagName());
			tag.setRelatedPosts(relatedPosts);
			updateTag(tag);
		}
		return tags;
	}

	@Override
	public List<Tag> queryByName(String tagName) {
		return getJdbcTemplate().query("SELECT * FROM tag WHERE tagName = ?",
				new Object[] { tagName },
				new int[] { Types.VARCHAR },
				new TagRowMapper());
	}


}

class TagRowMapper implements RowMapper {
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Tag t = new Tag();
		t.setTagName(rs.getString("tagName"));
		t.setRelatedPosts(rs.getInt("relatedPosts"));

		return t;
	}
}