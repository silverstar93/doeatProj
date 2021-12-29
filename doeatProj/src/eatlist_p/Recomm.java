package eatlist_p;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import db_p.ClickDAO;
import db_p.HashtagDAO;
import db_p.RecommDAO;
import db_p.RecommDTO;
import db_p.ResInfDAO;
import db_p.ResInfDTO;
import db_p.ReviewDAO;
import db_p.UserDAO;
import db_p.UserDTO;

public class Recomm {
/* click review hashtag itdeal*/  
	String memNo;
	
	String test () {
		String test ="cpcpcpcpcpcpcpcpcppcpc";
		
		return test;
		
	}
	
	
	public ArrayList<RecommDTO> rcCal(UserDTO dto) {
		


		//북마크 목록 받아와서 가르고
		memNo = dto.getMemNo()+"";
		ArrayList<RecommDTO> hashR= (ArrayList<RecommDTO>) new HashtagDAO().hComm(dto);
		ArrayList<RecommDTO> reviewR= (ArrayList<RecommDTO>) new ReviewDAO().rComm(memNo);
		//ArrayList<RecommDTO> clcickR= (ArrayList<RecommDTO>) new ClickDAO().cComm(dto);
		ArrayList<RecommDTO> bookR =  (ArrayList<RecommDTO>)new RecommDAO().bComm(dto); 
		ArrayList<RecommDTO> realRComm =  (ArrayList<RecommDTO>)new RecommDAO().realRComm(dto) ;
		

		ArrayList<RecommDTO> res =  new ArrayList<>();
		/*for (int i = 0; i <hashR.size(); i++) {
			if(hashR.get(i)!=null) {
			res.add(hashR.get(i));}
			if(clcickR.get(i)!=null) {
			res.add(clcickR.get(i));}			
		}
		for (int i = 0; i <bookR.size(); i++) {
		if(bookR.get(i)!=null) {
			res.add(bookR.get(i));
		}
		}*/
		
		for (int i = 0; i <realRComm.size(); i++) {
			if(realRComm.get(i)!=null) {
			res.add(realRComm.get(i));}		
		}
		
		
		

		

		  
		return res;	
	}
}
