package filter;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CheckFilter implements Filter{
	
	private String[] parameterNames = null;
	
	@Override
	public void destroy() {
		
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest)req;
		HttpSession session = httpRequest.getSession();
		
		CheckRequestWrapper requestWrapper = new CheckRequestWrapper((HttpServletRequest)req);
		
		requestWrapper.checkNull(parameterNames);
		
		chain.doFilter(requestWrapper, resp);
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		
		String names = config.getInitParameter("paramterNames");
		
		StringTokenizer st = new StringTokenizer(names, ",");
		
		parameterNames = new String[st.countTokens()]; 
		
		
		for(int i=0;st.hasMoreElements();i++){
			parameterNames[i] = st.nextToken(); 
		}
	}
	
}
