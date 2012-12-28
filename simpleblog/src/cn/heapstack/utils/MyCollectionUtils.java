package cn.heapstack.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class MyCollectionUtils {

	/**
	 * 
	 * 取Collection和Remove的差集 
	 * <br/>
	 * - 功能和CollectionUtils.removeAll的文档描述一样，但是CollectionUtils的removeAll有个Bug
	 * 
	 * @param collection
	 *            - the collection from which items are removed (in the returned
	 *            collection)
	 * @param remove
	 *            - the items to be removed from the returned collection
	 * @return a Collection containing all the elements of collection except any
	 *         elements that also occur in remove.
	 */
	public static Collection<String> removeAll(
			Collection<String> collection, Collection<String> remove) {
		Collection<String> result = new ArrayList<String>();
		Iterator<String> e = collection.iterator();
		while (e.hasNext()) {
			String oneInCollection = e.next();
			if(!remove.contains(oneInCollection))
			{
				result.add(oneInCollection);
			}
		}
		return result;
	}
	
	/**
	 * 移除collection里面的重复元素
	 * 
	 * @param collection
	 *  - the collection which may contain duplicated items
	 * @return
	 * a Collection containing all the unduplicated elements of collection
	 */
	public static Collection<String> removeDuplicates(Collection<String> collection)
	{
		Collection<String> result = new ArrayList<String>();
		Iterator<String> e = collection.iterator();
		while (e.hasNext()) {
			String oneInCollection = e.next();
			if(!result.contains(oneInCollection))
			{
				result.add(oneInCollection);
			}
		}
		return result;
	}
}
