<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="mm" class="db_p.UserDTO" scope="session"/>
<jsp:setProperty property="*" name="mm"/>
    
    
    나의 포인트 : ${mm.point }