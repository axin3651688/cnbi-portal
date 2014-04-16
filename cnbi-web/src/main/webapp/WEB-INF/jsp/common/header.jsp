<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="Shortcut Icon" href="${resourcePath}images/sys/${pageBean.favicon}.ico"type="image/x-icon" />
<title>${pageBean.sname}</title>
<link href="${bootstrapPath}css/bootstrap.css" rel="stylesheet">
<link href="${resourcePath}css/nav.css" rel="stylesheet">
<!--[if IE]>
<script type="text/javascript" src="${resourcePath}js/html5.js"></script>
<![endif]-->
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
</head>
<body>
	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">中国经邦</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">
				<!--  上海烟草集团智能财务管控系统 -->
				   <img alt="" src="${resourcePath}images/sys/logo.png" width=282 height=30>
				</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<c:forEach var="item" items="${list}" varStatus="idx">
					    <c:if test="${item.spcode == '1' }">
						<li class="${item.active}" scode="${item.scode}"><a href="${nav}${item.scode}" target="_self">${item.sname}</a></li>
					    </c:if>
					</c:forEach>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">后台管理 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<c:forEach var="item" items="${list}" varStatus="idx">
					           <c:if test="${item.spcode == '2' }">
						         <li scode="${item.scode}"><a href="${nav}${item.scode}" target="_self">${item.sname}</a></li>
					           </c:if>
					         </c:forEach>
					<!--	<li class="divider"></li>
							<li class="dropdown-header">Nav header</li>
							<li><a href="#">Separated link</a></li>
							<li><a href="#">One more separated link</a></li>-->
						</ul>
					</li>
				</ul>
				
			</div>
		</div>
	</div>
	<!--  <div class="container" style="margin-top:68px;">
	  <ul class="breadcrumb" style=";">
			    <li class="global-filter" >当前位置：</li> 
				 <li style="margin-left:-40px;">
					<a href="#">系统</a> <span class="divider">/</span>
				</li>
				<li class="active">
					首页
				</li>
	     </ul>
	</div> -->
	<%-- <div id="iframeAll" class="container">
		 <iframe id="iframemain" class="myFrame" name="iframemain" scrolling="auto" vspace="0" hspace="0" height=1200 width=100% frameborder="0" src="<%=basePath%>index/default">
		</iframe>
    </div>
	 --%>

 




<!-- <div class="filter" id="filter_div_id">
	当前位置：
		
	<span>&gt;</span>			
</div> -->
<!-- <div class="core-top"></div> -->
<!-- JiaThis Button BEGIN -->
 <script type="text/javascript" src="http://v3.jiathis.com/code/jiathis_r.js?uid=1356084986632651&move=0" charset="utf-8"></script>
<!-- JiaThis Button END -->
