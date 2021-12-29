<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script src="../js/jquery-3.3.1.min.js"></script>

<script>
function ptn(){
	
	var namecc = /^([가-힣]+)$/;

	var chk = true;

	if (!namecc.test($('#nnn').val())) {
		chk = false;
	}
	if($('#nnn').val()==""){
		chk=true;
	}
	if (chk)
		ptf.submit();
}

</script>

<c:set value="${param.tap }" var="pp"></c:set>

<c:choose>

<c:when test="${pp ==1}">

<h3 class="review">탈퇴신청 관리</h3>

<a id="accept" href="partner">파트너 관리</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font class="review"><b>탈퇴신청 관리</b></font>
<br><br>

<table>
	<tr>
		<th class="review">회원 번호</th>
		<th class="review">이름</th>
		<th class="review">휴대전화</th>
	</tr>
	<tr><td class="cover" colspan="3"></td></tr>
	<c:forEach var="i" items="${partner }">
		<tr>
			<td class="review"><a href="partnerDetail?memNo=${i.memNo }&nowPage=${nowPage}">${i.memNo }</a></td>
			<td>${i.name }</td>
			<td>${i.phone }</td>
		</tr>
		<tr><td class="cover" colspan="3"></td></tr>
	</c:forEach>
	<tr>
			<td colspan="3" align="center" class="review">
			<c:if test="${startPage>1 }">
				<a href="?rid=${param.rid }&page=${startPage-1 }">이전</a>
			</c:if>
			<c:forEach begin = "${startPage }" end="${endPage }" var="i">
			<c:choose>
				<c:when test="${i==nowPage }">
					${i }
				</c:when>
				<c:otherwise>
					<a href="?rid=${param.rid }&page=${i }">${i }</a>
				</c:otherwise>
			</c:choose>				
			</c:forEach>				
			<c:if test="${endPage<totalPage }">
				<a href="?rid=${param.rid }&page=${endPage+1 }">다음</a>
			</c:if>
		</td>
	</tr>
</table>

</c:when>

<c:otherwise>

<h3 class="review">파트너 관리</h3>

<font class="review"><b>파트너 관리</b></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="accept" href="partner?tap=1">탈퇴신청 관리</a>
<br><br>

<form name="ptf" action="partner" method="post">
<table width="100%">
<tr align="center">
	<td colspan="7">
		이름 : <input type="text" id="nnn" name="name"/> <input type="button" value="검색" onclick="ptn()" />
	</td>
	<tr><td class="cover" colspan="7"></td></tr>
</tr>
	<tr align="center">
		<th class="review">회원 분류</th>
		<th class="review">회원 등급</th>
		<th class="review">회원 번호</th>
		<th class="review">아이디</th>
		<th class="review">이름</th>
		<th class="review">휴대전화</th>
		<th></th>
	</tr>
	<tr><td class="cover" colspan="7"></td></tr>
 	<c:forEach var="i" items="${user }" >
	<tr align="center">
		<c:choose>
		<c:when test="${i.cate==0 }">	
		<td>관리자</td>
		</c:when>
		<c:when test="${i.cate==2 }">
		<td>파트너</td>
		</c:when>
		<c:otherwise>
		<td>일반</td>
		</c:otherwise>
		</c:choose>
		<c:choose>
		<c:when test="${i.grade==9 }">
		<td>미승인 파트너</td>
		</c:when>
		<c:when test="${i.grade==8 }">
		<td>탈퇴신청 파트너</td>
		</c:when>
		<c:otherwise>
		<td>일반 파트너 회원</td>
		</c:otherwise>
		</c:choose>
		<td>${i.memNo }</td>
		<td>${i.id }</td>
		<td>${i.name }</td>
		<td>${i.phone }</td>
		<td><a href="uDetail?mem=${i.memNo }&nowPage=${gennowPage}"><input type="button" value="상세"/></a></td>
	</tr>
	<tr><td class="cover" colspan="7"></td></tr>
	</c:forEach>
	<tr>
			<td colspan="7" align="center" class="review">
			<c:if test="${genstartPage!=null }">
			<c:if test="${genstartPage>1 }">
				<a href="?rid=${param.rid }&page=${genstartPage-1 }">이전</a>
			</c:if>
			<c:forEach begin = "${genstartPage }" end="${genendPage }" var="i">
			<c:choose>
				<c:when test="${i==gennowPage }">
					${i }
				</c:when>
				<c:otherwise>
					<a href="?rid=${param.rid }&page=${i }">${i }</a>
				</c:otherwise>
			</c:choose>				
			</c:forEach>				
			<c:if test="${genendPage<gentotalPage }">
				<a href="?rid=${param.rid }&page=${genendPage+1 }">다음</a>
			</c:if>
			</c:if>
		</td>
	</tr>
</table>
</form>

</c:otherwise>

</c:choose>