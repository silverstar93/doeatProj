<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="mm" class="db_p.UserDTO" scope="session"/>
<!DOCTYPE html>
<script src="../js/jquery-3.3.1.min.js"></script>
<script>
function cnoChk(cnoin, memin){	

		$.ajax('../ajax_song/CnoChk?cno='+cnoin, {	
			success:function(dd){
				
				var msg = "신고하시겠습니까?"
				
				if(eval(dd)==false){
					msg = "이미 신고된 리뷰입니다\n내역을 확인하시겠습니까?"						
				}
				
				if(confirm(msg)){ 					
					if(msg=="신고하시겠습니까?"){
						location.href="ReviewReport?rid=${param.rid }&memNo="+memin+"&cno="+cnoin;
					}else{
						$('#uu').html('신고된 리뷰')
						location.href="../mypage/replist"
						
					}	                
	            }else{
	                
	            } 			
			},
			error:function(){
				alert("중복체크에 실패했습니다")
			}								
		}); // ajax
		}
</script>
<div class="steps">
<div>
<table width="1000px">
	<c:forEach var="rv" items="${review }" varStatus="no">
	<tr>
		<td id="reviewline" colspan="4"></td>
	</tr>
	<tr>
		<td width="780px" class="review">작성자:${rv.memNo }</td>
		<td width="220px" class="review" colspan="3" align="right">등록일(${rv.regdateStr })</td>
	</tr>
	<tr>
		<td id="reviewline2" colspan="4"></td>
	</tr>
	<tr>
		<td colspan="3">
			${rv.contextBr }
		</td>
		<td align="center">
		<c:choose>
			<c:when test="${rv.pic!='NA' }">
				<img src="../rvpic/${rv.pic }" width="200px"/>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
		</td>
	</tr>
	<tr>
		<td class="review" align="center">맛점수:${rv.tsrate } 분위기점수:${rv.mdrate } 서비스점수:${rv.svrate }</td>
		<td class="review2" colspan="3" align="right">
			<c:choose>
				<c:when test="${mm.memNo==null }">
				(좋아요: ${rv.likes } )
				</c:when>
				<c:when test="${mm.memNo== detail.memNo}">
					<a class="review2" id="uu" onclick="cnoChk(${rv.cno}, ${rv.memNo })">
					<img src="../icons/rvreport.png" width="30px"><br>리뷰신고
					</a>	
				</c:when>
				<c:when test="${mm.memNo!= rv.memNo and mm.cate!= 0 }">				
					<c:choose>
					<c:when test="${rv.who!=null }">
						<c:set var="l" value="1"/>
						
						<c:forTokens var="ee" items="${rv.who }" delims=",">
							<c:if test="${mm.memNo==ee }">
								<c:set var="l" value="${1+=1 }"/>
							</c:if>
						</c:forTokens>
						
						<c:if test="${l>1 }">
							<a class="review2" href="ReviewUnlike?rid=${param.rid }&memNo=${mm.memNo}&cno=${rv.cno}">
							<img src="../icons/rvdelete.png" width="30px"><br>좋아요해제</a>( ${rv.likes } )
						</c:if>
						<c:if test="${l==1 }">
							<a class="review2" href="ReviewLike?rid=${param.rid }&memNo=${mm.memNo}&cno=${rv.cno}">
							<img src="../icons/rvlike.png" width="30px"><br>좋아요</a>( ${rv.likes } )
						</c:if>
						
					</c:when>
					<c:when test="${rv.who==null }">
						<a class="review2" href="ReviewLike?rid=${param.rid }&memNo=${mm.memNo}&cno=${rv.cno}">
						<img src="../icons/rvlike.png" width="30px"><br>좋아요</a>( ${rv.likes } )
					</c:when>
					</c:choose>		
				</c:when>
				<c:when test="${rv.memNo== mm.memNo or mm.cate==0}">			
					<a class="review2" href="ReviewDelete?rid=${param.rid }&cno=${rv.cno }&memNo=${rv.memNo}&pic=${rv.pic}">
					<img src="../icons/rvdelete.png" width="30px"><br>리뷰삭제하기</a>
				</c:when>
			</c:choose>				
		</td>
	</tr>	
	</c:forEach>
	<tr>
		<td colspan="4" align="center" class="review">
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
</div>
</div>