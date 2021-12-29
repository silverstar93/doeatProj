<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String type ="rloc1"; %>

<div>


<c:forEach var="i" items="${hash }" varStatus="no">
<!-- 이동 주소와 사진은 res에서 받아온다 -->
<c:if test="${i.hashcode==1 }"><% type= "menu"; %></c:if>
 <a href="Eatlist?hashcode=${i.hashcode }&<%=type %>=${i.hcontent}&search=${i.hcontent}"># ${i.hcontent} </a>
 <!-- // 다시 잇리스트로 보내서 검색 결과 출력 -->
</c:forEach> 

</div>

<div style="background-color: #EAEAEA">

<font size="5"><i>두잇 추천</i></font><br>
<c:forEach var="i" items="${first}" >

 <a href="SearchReg?rid=${i.rid }">
 
 <img src="../view/img/${i.pic}" width="200px"><br>
${i.rname} [ ${i.score} ]<br>

 </a>

</c:forEach>

</div>

<div>

<c:forEach var="i" items='${srchList}' varStatus="no">
<!-- 이동 주소와 사진은 res에서 받아온다 -->
 <a href="SearchReg?rid=${i.rid }">
 <img src="../view/img/${i.pic}" width="200px"><br>
${i.rname} [ ${i.score} ]<br> 
 </a>
 

 
</c:forEach>

</div>


