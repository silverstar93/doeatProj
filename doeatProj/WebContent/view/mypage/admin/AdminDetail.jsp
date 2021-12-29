<%@page import="db_p.DealDTO"%>
<%@page import="db_p.DealDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3 class="review">EatDeal 신청 상세내역</h3>
<script src="../js/jquery-3.3.1.min.js"></script>
<div>
<form action="AdminDetailReg" method="post">
	<input type="hidden" name="cno" value="${dto.cno }">
	<input type="hidden" name="nowPage" value="${nowPage }"/>
	<table>
<!-- 		<tr>
			<td class="review">가게 이름</td>
			<td></td>
		</tr> -->
		<tr>
			<td class="review">식당 코드</td>
			<td>${dto.rid }</td>
		</tr>
		<tr><td colspan="2" class="cover"></td></tr>
		<tr>
			<td class="review">메뉴</td>
			<td>${dto.cmenu }</td>
		</tr>
		<tr><td colspan="2" class="cover"></td></tr>
		<tr>
			<td class="review">원가</td>
			<td>${dto.orgprice }</td>
		</tr>
		<tr><td colspan="2" class="cover"></td></tr>
		<tr>
			<td class="review">할인</td>
			<td>${dto.cdiscount }</td>
		</tr>
		<tr><td colspan="2" class="cover"></td></tr>
		<tr>
			<td class="review">상태</td>
			<td>${dto.ccon }</td>
		</tr>
		<tr><td colspan="2" class="cover"></td></tr>
		<tr>
			<td class="review">수량</td>
			<td>${dto.cvolume }</td>
		</tr>
		<tr><td colspan="2" class="cover"></td></tr>
		<tr>
			<td class="review">유효기간 시작일</td>
			<td>${dto.cstartStr }~ ${dto.cendStr }</td>
		</tr>
		<tr><td colspan="2" class="cover"></td></tr>
		<tr>
			<td class="review">유효기간 마감일</td>
			<td>${dto.cendStr }</td>
		</tr>
		<tr><td colspan="2" class="cover"></td></tr>
		<tr>
			<td class="review">작성일</td>
			<td>${dto.cpubStr }</td>
		</tr>
		<tr><td colspan="2" class="cover"></td></tr>
		<tr>
			<td class="review">내용</td>
			<c:choose>
			<c:when test="${dto.contentBr==NS }">
			<td>내용 없음</td>
			</c:when>
			<c:otherwise>
			<td>${dto.contentBr }</td>
			</c:otherwise>
			</c:choose>
		</tr>
		<tr><td colspan="2" class="cover"></td></tr>
		<tr>
			<c:choose>
			<c:when test="${dto.ccon==1 }">
			<td class="review" colspan="2" align="center">이미 승인된 딜입니다</td>
			</c:when>
			<c:when test="${dto.ccon==3 }">
			<td class="review" colspan="2" align="center">이미 거절된 딜입니다</td>
			</c:when>
			<c:otherwise>
			<td colspan="2" align="center">
			<input type="submit" value="승인" />
			<input type="button" value="거절" onclick="location.href='refuse?cno=${dto.cno }&page=${nowPage }'"/> <!-- 에러나면 cno제거 -->
			</td>
			</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<td colspan="2" align="right"><a class="review" href="deal?page=${nowPage }">목록으로</a></td>
		</tr>
	</table>
</form>
</div>
