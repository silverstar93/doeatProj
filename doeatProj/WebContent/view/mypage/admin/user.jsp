<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script src="../js/jquery-3.3.1.min.js"></script>

<script>
function use(){
	
	var namecc = /^([가-힣]+)$/;

	var chk = true;

	if (!namecc.test($('#nnn').val())) {
		chk = false;
	}
	if($('#nnn').val()==""){
		chk=true;
	}
	if (chk)
		usf.submit();
}

</script>


<h3 class="review">일반 회원 관리</h3>

<form name="usf" action="user" method="post">
<table width="100%" cellspacing="0">
	<tr align="center">
	<td class="review" colspan="7">
		이름 : <input type="text" id="nnn" name="name"/> <input type="button" value="검색" onclick="use()" />
	</td>
	</tr>
	<tr><td class="cover" colspan="7"></td></tr>
	<tr align="center">
		<th class="review">회원 분류</th>
		<th class="review">회원 등급</th>
		<th class="review">회원 번호</th>
		<th class="review">아이디</th>
		<th class="review">이름</th>
		<th class="review">휴대전화</th>
		<td></td>
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
	<c:when test="${i.grade==2 }">
		<td>뚜잇이(특별회원)</td>
	</c:when>
	<c:otherwise>
		<td>일반회원</td>
	</c:otherwise>
	</c:choose>
		<td>${i.memNo }</td>
		<td>${i.id }</td>
		<td>${i.name }</td>
		<td>${i.phone }</td>
		<td class="review"><a href="uDetail?mem=${i.memNo }&nowPage=${nowPage }"><input type="button" value="상세"/></a></td>
	</tr>
	<tr><td class="cover" colspan="7"></td></tr>
	</c:forEach>
	<tr>
		<td colspan="7" align="center" class="review">
		
		<c:if test="${startPage!=null }">
			<c:if test="${startPage>1 }">
				<a href="?page=${startPage-1 }">이전</a>
			</c:if>
			<c:forEach begin = "${startPage }" end="${endPage }" var="i">
			<c:choose>
				<c:when test="${i==nowPage }">
					${i }
				</c:when>
				<c:otherwise>
					<a href="?page=${i }">${i }</a>
				</c:otherwise>
			</c:choose>				
			</c:forEach>				
			<c:if test="${endPage<totalPage }">
				<a href="?page=${endPage+1 }">다음</a>
			</c:if>
			</c:if>
		</td>
	</tr>
</table>
</form>