<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="common/common.jsp"%> 
<%@ include file="common/header.jsp"%> 
<link href="${resourcePath}css/${pageBean.scode}.css" rel="stylesheet">
<div class="container" style="margin-top:75px">
    <jsp:include page="${pageBean.scode}.jsp"/>
</div>
<%@ include file="common/footer.jsp"%>
