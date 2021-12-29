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
 * Servlet implementation class listCon
 */
@WebServlet("/eatlist/*")
public class eatlistCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public eatlistCon() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		System.out.println("dhgaioioahgoih");
		String service = request.getRequestURI().substring("/doeatProj/eatlist/".length());
				
		try {
			
			request.setAttribute("mainUrl", "eatlist/"+service+".jsp");
			
			Executor exc = (Executor)Class.forName("eatlist_p."+service).newInstance();
			exc.execute(request, response);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/template.jsp");
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
