package cn.heapstack.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heapstack.utils.ServletUtils;

/**
 * Servlet Filter implementation class ViewPostsFilter
 */
public class ViewPostsFilter implements Filter {

	private static Logger logger = LoggerFactory
			.getLogger(ViewPostsFilter.class);

	/**
	 * Default constructor.
	 */
	public ViewPostsFilter() {

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		logger.info("ViewPostsFilter destory");
	}



	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String queryStr = ((HttpServletRequest)request).getQueryString();
		
		if (queryStr!=null && queryStr.contains("method=view"))
		{
			
			String clientIP = ServletUtils.getIpAddr((HttpServletRequest)request);
			logger.debug("___TODO: update the read count___ ; This one from IP" + clientIP);
		}
			
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		logger.info("ViewPostsFilter init");
	}

}
