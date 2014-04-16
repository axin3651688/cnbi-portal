<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath(), 
	basePath = request.getScheme()+ "://"+ request.getServerName()+ ":"+ request.getServerPort() + path + "/",
	resourcePath = basePath+"resource/";//上海烟草财务管理系统  favicon
%>
<c:set var="ctx" value="<%=basePath %>"/>
<c:set var="resourcePath" value="${ctx}resources/"/>
<c:set var="nav" value="${ctx}main.cnbi?scode="/>
<c:set var="bootstrapPath" value="${resourcePath}bootstrap3.0.3/"/>