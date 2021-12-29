package ctrl_p;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.UserDTO;
import interface_p.Executor;

/**
 * Servlet implementation class mypageCon
 */
@WebServlet("/mypage/*")
public class mypageCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mypageCon() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String service = request.getRequestURI().substring("/doeatProj/mypage/".length());
				
		try {
			
			request.setAttribute("mainUrl", "mypage/mypage.jsp");
			request.setAttribute("imin", service);
			
			String main = service;
			String cate = "user";			
		
			
			LinkedHashMap<String, String> map = new LinkedHashMap<String,String>();
			LinkedHashMap<String, String> map1 = new LinkedHashMap<String,String>();
			LinkedHashMap<String, String> map2 = new LinkedHashMap<String,String>();


			map.put("user", "회원 관리");
			map.put("partner", "파트너 관리");
			map.put("res", "식당 관리");
			map.put("review", "리뷰 관리");
			map.put("deal", "잇딜 관리");
			map.put("coup", "이용권 관리");
			map.put("PDCal", "잇딜 정산");
			
			map1.put("index", "내 정보");
			map1.put("point", "포인트 조회");
			map1.put("myCoupon", "나의 이용권");
			map1.put("myReview", "나의 리뷰");
			map1.put("eatMap", "맛집 지도");

			
			map2.put("index", "내 정보");
			map2.put("myRes", "나의 식당");
			map2.put("salesRep", "판매 내역");
			map2.put("eatDealReq", "잇딜 신청");
			map2.put("eatDealList", "잇딜 신청 내역");
			map2.put("MyDealCal", "잇딜 정산");
			
			
			
			UserDTO cc = (UserDTO)request.getSession().getAttribute("mm");
			if(cc.getCate()==1){
				request.setAttribute("menuList", map1);	
			}
			else if(cc.getCate()==2){
				
				cate="user2";	
				if(cc.getGrade()==9){
					main="pre";
					request.setAttribute("menuList",new LinkedHashMap<String,String>());
				} else{
					request.setAttribute("menuList", map2);
				}
			}
			else if(cc.getCate()==0){
				request.setAttribute("menuList", map);
				cate="admin";
			}
		
				
			
			
				main =  cate+"/"+main+".jsp";
				
				request.setAttribute("main", main);
				request.setAttribute("whosein", cate);
			
			
				Executor exc = (Executor)Class.forName("mypage_p."+service).newInstance();
				exc.execute(request, response);
			
			
			
			
			
			
			///////////////////////////////////////////////////////////////////
			
			
			
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
