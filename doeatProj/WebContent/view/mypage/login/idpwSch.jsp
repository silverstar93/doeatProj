<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="../js/jquery-3.3.1.min.js"></script>
<script>
	function sch() {
		var idcc = /^[a-zA-Z0-9]{3,10}$/;
		var namecc = /^([가-힣]+)$/;
		var yearcc = /^\d{4}$/;
		var datecc = /^\d{1,2}$/;
		var mail1cc = /^([a-zA-Z0-9_\.-]+)$/;
		var mail2cc = /^(\w+)[.](\w+)$/;

		
		if("${param.kind}"=="1"){
			if (!namecc.test($('#name').val())
					|!yearcc.test($('#yy').val())|!datecc.test($('#dd').val())
					|!mail1cc.test($('#mail1').val())|!mail2cc.test($('#mail2').val())) {
				
				alert("아이디를 찾을 수 없습니다.");
				
			}else{
				idpwsch.submit();
			}
			
		}else{
			if (!idcc.test($('#id').val())||!namecc.test($('#name').val())
					|!yearcc.test($('#yy').val())|!datecc.test($('#dd').val())
					|!mail1cc.test($('#mail1').val())|!mail2cc.test($('#mail2').val())
					|!namecc.test($('#answer').val())) {
				
				alert("비밀번호를 찾을 수 없습니다.");
				
			}else{
				idpwsch.submit();
			}
		}
		
		
		
	

	}
</script>

<div align="center" style="height: 750px;">
	<table style="height: 100%; valign: middle;">
		<tr>
			<td>
				<form name="idpwsch" action="idpwReg" method="post">
					<input type="hidden" value="${param.kind }" name="kind" />
					<table cellpadding="5">
						<c:set var="kk" value="${param.kind }"></c:set>
						<c:if test="${kk==2 }">
							<tr>
								<td>아이디</td>
								<td><input type="text" name="id" id="id" /></td>
							</tr>
						</c:if>

						<tr>
							<td>이름</td>
							<td><input type="text" id="name" name="name" /></td>
						</tr>
						<tr>
							<td>생년월일</td>
							<td><input type="text" id="yy" name="byear" maxlength="4"
								size="5" />년 <select id="month" name="bmonth">
									<c:forEach begin="1" end="12" var="i">
										<option>${i }</option>
									</c:forEach>
							</select>월 <input type="text" id="dd" name="bdate" maxlength="2" size="3" />일</td>
						</tr>
						<tr>
							<td>이메일</td>
							<td><input type="text" id="mail1" name="mail1" /> @ <input
								type="text" name="mail2" id="mail2" /></td>
						</tr>

						<c:if test="${kk==2 }">
							<tr>
								<td align="center">비밀번호 찾기 질문</td>
								<td><select name="question">
										<c:forEach var="i" items="${que }">
											<option>${i }</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>

								<td align="center">비밀번호 찾기 답변</td>
								<td><input type="text" id="answer" name="answer" /></td>
							</tr>
						</c:if>
						<tr>
							<c:choose>
								<c:when test="${kk==1 }">
									<td align="right" colspan="2"><input type="button"
										onclick="sch()" value="아이디 찾기" /></td>
								</c:when>
								<c:when test="${kk==2 }">
									<td align="right" colspan="2"><input type="button"
										onclick="sch()" value="비밀번호 찾기" /></td>
								</c:when>

							</c:choose>


						</tr>
					</table>
				</form>
			</td>
		</tr>

	</table>
</div>

