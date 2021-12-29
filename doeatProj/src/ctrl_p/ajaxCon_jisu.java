package ctrl_p;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import interface_p.Executor;

/**
 * Servlet implementation class ajaxCon
 */
@WebServlet("/ajax_jisu/*")
public class ajaxCon_jisu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ajaxCon_jisu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String service = request.getRequestURI().substring("/doeatProj/ajax_jisu/".length());
		
		try {
			
			
			Executor exc = (Executor)Class.forName("ajax_jisu."+service).newInstance();
			exc.execute(request, response);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/ajax_jisu/"+service+".jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
