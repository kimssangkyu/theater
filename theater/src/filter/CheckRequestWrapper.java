package filter;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CheckRequestWrapper extends HttpServletRequestWrapper {
	
	
	private Map<String, String[]> parameterMap = null;
	
	
	
	
	public void checkNull(String[] parameterNames){
		
		
		for(String pName : parameterNames){
			if(!parameterMap.containsKey(pName)){
				
				String[] values = new String[] {""};
				
				parameterMap.put(pName, values);
			}
		}
	}
	
	public CheckRequestWrapper(HttpServletRequest request) {
		
		super(request);
		
		parameterMap = new HashMap<String,String[]>(request.getParameterMap());
	}

	@Override
	public String getParameter(String name) {
		
		String [] values = getParameterValues(name);
		
		if(values != null && values.length >0){
			return values[0];
		}
		return null;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		
		return parameterMap;
	}

	@Override
	public Enumeration<String> getParameterNames() {
		
		return Collections.enumeration(parameterMap.keySet());
	}

	@Override
	public String[] getParameterValues(String name) {
			
		return parameterMap.get(name);
	}
}