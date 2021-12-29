package interface_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Executor {

	public void execute(HttpServletRequest request, HttpServletResponse response);
	
}
