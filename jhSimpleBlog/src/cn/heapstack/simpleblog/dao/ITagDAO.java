package cn.heapstack.simpleblog.dao;

import java.util.List;

import cn.heapstack.simpleblog.domain.Tag;

public interface ITagDAO {

	boolean addTag(Tag tag);
	
	Tag getTag(String tagName);
	
	boolean updateTag(Tag tag);
	
	boolean deleteTag(String tagName);
	
	boolean isTagExist(String tagName);
	
	

	List<Tag> queryAll();
	List<Tag> queryByName(String tagName);
}
