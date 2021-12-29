<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../scripts/topdochange.js"></script>
<c:choose>
<c:when test="${mainUrl=='eatdeal/List2.jsp' }">
<script>
	$(document).ready(function(){
		
		$('.dealgo').html('<font color="white">잇딜</font>')
		
	})
</script>
</c:when>
<c:when test="${mainUrl=='mypage/login/join.jsp' }">
<script>
	$(document).ready(function(){
		
		$('.joingo').html('<font color="white">회원가입</font>')
		
	})
</script>
</c:when>
<c:when test="${mainUrl=='mypage/login/index.jsp' }">
<script>
	$(document).ready(function(){
		
		$('.logingo').html('<font color="white">로그인</font>')
		
	})
</script>
</c:when>
<c:when test="${mainUrl=='mypage/user/index.jsp' }">
<script>
	$(document).ready(function(){
		
		$('.logingo').html('<font color="white">로그인</font>')
		
	})
</script>
</c:when>
<c:when test="${whosein!=null }">
<script>
	$(document).ready(function(){
		
		$('.mygo').html('<font color="white">마이페이지</font>')
		
	})
</script>
</c:when>


<c:when test="${mainUrl=='eatlist/Home.jsp' }">
<script>
	$(document).ready(function(){
		
		$('.searchgo').html('')
		
	})
</script>
</c:when>
</c:choose>
<table class="fixmain">
	<tr>
		<td width="100px" align="center" height="50px"><a href="http://localhost:8080/doeatProj/eatlist/Home">
			<img src="../icons/doitit.png" width="50px"></a>
		</td>		

		<td width="500px" align="center" class="searchgo"><jsp:include page="search.jsp"></jsp:include></td>
	<td width="100px" class="dealgo" align="center"><a class="intro" href="http://localhost:8080/doeatProj/eatdeal/List2">잇딜</a></td>	
		<td width="100px"></td>	
		<c:set value="${mm }" var="ss"></c:set>
	<c:choose>
	<c:when test="${ss==null }">
	
		<td width="100px" class="joingo" align="center"><a class="intro" href="http://localhost:8080/doeatProj/login/join">회원가입</a></td>
		<td width="100px" class="logingo" align="center"><a class="intro" href="http://localhost:8080/doeatProj/login/index">로그인</a></td>
	</c:when>
	<c:otherwise>
		<td width="100px" class="mygo" align="center"><a class="intro" href="../mypage/index">마이페이지</a></td>
		<td width="100px" align="center"><a class="intro" href="http://localhost:8080/doeatProj/login/logout">로그아웃</a></td>
	</c:otherwise>
	</c:choose>
	</tr>
</table>
