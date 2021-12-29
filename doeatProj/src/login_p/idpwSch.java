package login_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import interface_p.Executor;

public class idpwSch implements Executor {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String [] que = {
				"기억에 남는 추억의 장소는?","자신의 보물 제1호는?","가장 기억에 남는 선생님 성함은?","유년시절 가장 생각나는 친구 이름은?",
				"인상깊게 읽은 책 이름은?","가장 존경하는 인물은?"
		};

		String [] tel = {
				"010","011","016","017","018","019"
		};

		String [] email = {
			"직접입력","naver.com","gmail.com","hanmail.net","nate.com","hotmail.com"	
		};
		
		

		request.setAttribute("que", que);
		request.setAttribute("tel", tel);
		request.setAttribute("email", email);

	}

}
