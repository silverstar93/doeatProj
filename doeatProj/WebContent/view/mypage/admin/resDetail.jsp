<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../scripts/rno.js"></script>
<link rel="stylesheet" href="../css/resacp.css" type="text/css">
<form name="acfrm" action="resAccept?nowPage=${param.nowPage }" method="post">
<table width="100%">
	<tr>
		<td class="review">회원번호</td>
		<td colspan="2">
			${waithandle.memNo }
			<input type="hidden" name="memNo" value="${waithandle.memNo }"/>
		</td>
	</tr>
	<tr><td class="cover" colspan="3"></td></tr>
	<tr>
		<td class="review">사업자등록번호</td>
		<td id="test">
			<input type="text" id="rno" name="rno" maxlength="9" value="${waithandle.rno }" readOnly="readOnly"/>			
		</td>
		<td><input type="button" id="dck" value="중복체크" onclick="doubleChk()"/></td>
	</tr>
	<tr><td class="cover" colspan="3"></td></tr>
	<tr>
		<td class="review">사업자</td>
		<td colspan="2">
			${waithandle.rowner }			
			<input type="hidden" name="rowner" value="${waithandle.rowner }"/>
		</td>
	</tr>
	<tr><td class="cover" colspan="3"></td></tr>
	<tr>
		<td class="review">상호명</td>
		<td colspan="2">
			${waithandle.rname }
			<input type="hidden" name="rname" value="${waithandle.rname }"/>
		</td>
	</tr>
	<tr><td class="cover" colspan="3"></td></tr>
	<tr>
		<td class="review">주소</td>
		<td colspan="2">
			${waithandle.raddr }
			<input type="hidden" name="raddr" value="${waithandle.raddr }"/>
		</td>
	</tr>
	<tr><td class="cover" colspan="3"></td></tr>
	<tr>
		<td class="review">전화번호</td>
		<td colspan="2">
			${waithandle.rtel }
			<input type="hidden" name="rtel" value="${waithandle.rtel }"/>
		</td>
	</tr>
	<tr><td class="cover" colspan="3"></td></tr>
	<tr>
		<td class="review">#위치(관리자직접입력)</td>
		<td>
			광역:${bigaddr }
			<input type="hidden" name="rloc1" value="${bigaddr }"/>
		</td>
		<td>
			<font color="red"><b>소규모</b></font><input type="text" name="rloc2"/>
		</td>
	</tr>
	<tr><td class="cover" colspan="3"></td></tr>
	<tr>
		<td class="review">영업시간</td>
		<td colspan="2">
			<input type="hidden" name="rhour" value="${waithandle.rhour }"/>${waithandle.rhour }
		</td>
	</tr>
	<tr><td class="cover" colspan="3"></td></tr>
	<tr>
		<td class="review">메뉴</td>
		<td>
			대분류:<input type="hidden" name="rmenu1" value="${waithandle.rmenu1 }"/>${waithandle.rmenu1 }
		</td>
		<td>
			소분류:<input type="hidden" name="rmenu2" value="${waithandle.rmenu2 }"/>${waithandle.rmenu2 }
		</td>
	</tr>
	<tr><td class="cover" colspan="3"></td></tr>
	<tr>
		<td class="review">가격대</td>
		<td colspan="2">
			<input type="hidden" name="rprice1" value="${waithandle.rprice1 }"/>${waithandle.rprice1 }-
			<input type="hidden" name="rprice2"value="${waithandle.rprice2 }"/>${waithandle.rprice2 }
		</td>
		</tr>
		<tr><td class="cover" colspan="3"></td></tr>
		<tr>
			<td class="review">주요메뉴</td>
			<td colspan="2">
				<input type="hidden" name="rmain1" value="${waithandle.rmain1 }"/>${waithandle.rmain1 }
				<input type="hidden" name="rmain2" value="${waithandle.rmain2 }"/>${waithandle.rmain2 }
				<input type="hidden" name="rmain3" value="${waithandle.rmain3 }"/>${waithandle.rmain3 }
			</td>
		</tr>
		<tr><td class="cover" colspan="3"></td></tr>
		<tr>
			<td class="review">등록사진<br><font size="2">마우스를 올려 사진 확대</font></td>
			<td rowspan="2" align="center">
			<c:if test="${pic!=null and pic!='NA' }">
				<img id="regpic" src="../view/img/${pic }" width="200px">
				<input type="hidden" name="pic" value="${pic }"/>
			</c:if>
			</td>
			<td rowspan="2">
			<c:if test="${pic2!=null and pic!='NA' }">
				<img id="regpic2" src="../view/img/${pic2 }" width="200px">
			</c:if>
			</td>
		</tr>
		<tr>
			<td class="review"></td>
		</tr>
		<tr><td class="cover" colspan="3"></td></tr>
		<tr>
			<td>
			<td align="center"><a class="review" href="resAcp?page=${param.nowPage }">식당 관리 페이지로 이동</a></td>
			<td align="center">
				<input type="hidden" name="nowPage" value="${param.nowPage }"/>
				<input type="radio" name="result" value="accept">식당등록
				<input type="radio" name="result" value="reject">등록거절
				<input type="button" value="요청처리" onclick="reqchecked()"/>
			</td>
		</tr>
</table>
</form>
<div class="regpic">
<c:if test="${pic!=null }">
<img class="regpic" src="../view/img/${pic }">
</c:if>
</div>
<div class="regpic2">
<c:if test="${pic2!=null }">
<img class="regpic2" src="../view/img/${pic2 }" width="200px">
</c:if>
</div> 
