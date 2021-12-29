package eatlist_p;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.HashKeyDAO;
import db_p.HashtagDAO;
import db_p.HashtagDTO;
import db_p.RecommDAO;
import db_p.RecommDTO;
import db_p.ResDetailDAO;
import db_p.ResDetailDTO;
import db_p.ResInfDAO;
import db_p.UserDTO;
import interface_p.Executor;

public class Eatlist implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		try {
			
				request.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		HashtagDTO dto;
		String hcl = request.getParameter("rloc1");
		String hcm = request.getParameter("menu");
		String search = request.getParameter("search");
		String hash =request.getParameter("hashcode");
		//code 1 : 메뉴  2 : 위치  3: 해쉬태그 검색
		if(search=="") {
			search=null;
		}
		String str="";
		// 위치+음식 종류 ===>ok 위치 +검색 ==> ㄴㄴ
		
		if(hash!=null) {
			

			str = " rloc2 ='"+search+"' "; 
			
			if(hash.equals("1")) {
				str = " rmenu2 ='"+search+"'" ;
			}
			
	
			if(request.getSession().getAttribute("mm")!=null) {
				
				UserDTO udto = (UserDTO)request.getSession().getAttribute("mm");
				
				
				
				dto = new HashtagDTO();
				dto.setHashcode(Integer.parseInt(hash));
				dto.setMemNo(udto.getMemNo()+"");
				dto.setHcon(search);
				new HashtagDAO().insert(dto);
				
			}

			
		}else {
			
			/*rloc1 = '서울' and rmenu1 ='중식' or rmenu2 ='양꼬치' rname like '%양꼬치%'*/
			
		if(hcm==null||hcm.equals("")) {
			
			str = "rloc1 = '" + hcl+"' or rmenu1 ='"+search+"' or rmenu2 ='"+search+"' )tt where tt.rno = resInf.rno "+" or rname like '%" + search+"%'";
			
			hash="2";
			
		}else if(hcm!=null||!hcm.equals("")){
			System.out.println(hcm);
			//str = "and  rloc1 = '" + hcl+"' and rmenu1 ='"+hcm+"' or rname = '%" + search+"%'";
			
			str = " rloc1 = '" + hcl+"' and rmenu1 ='"+hcm+"' or rmenu2 ='"+search+"' or  rname like '%"+
					search+"%' or rmain1 = '"+search+"' or rloc2='"+search+"'";
			
			hash ="1";
		}
		
		}; 
		

		
/*		if(search!=null){
			str += " or rmenu1 ='"+search+"' or rloc1 = '"+search+"'";
		}; 나중에 데이터 키워드로 해서 거르기 
		ex) 검색창에 '강남' 검색 ==> rmenu에서도 거르고 rloc에서도 거르고 출력
			'강남 뚝불' 검색 ==> rmenu에서도 거르고 rloc에서도 거르고 겹치는거 출력*/
		
				 
		ResDetailDTO dto2 =new ResDetailDTO();
		
		ArrayList<String> srchName = new ArrayList<>();
		

		System.out.println(search);
		/*디비에서 해쉬코드 찾기 디에이오수정하기*/
		System.out.println(request.getParameter("rloc1"));		
		request.setAttribute("mainUrl", "eatlist/Eatlist.jsp");

		request.setAttribute("hash", new HashKeyDAO().recomm(search));
		request.setAttribute("first", new RecommDAO().search(hcl, hcm,search));	
		request.setAttribute("srchList", new ResDetailDAO().search(search,str));
		//request.setAttribute("srch",new ResInfDAO().srch(search)); 이따가
		

		System.out.println("잇리스트 서비스 진입");
	}

}
