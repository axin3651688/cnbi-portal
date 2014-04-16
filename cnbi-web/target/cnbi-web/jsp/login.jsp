<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath(),
    basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/",
    resourcePath = basePath+"resources/";
%>
<!doctype html>
<html lang="zh_CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统登录</title>
 <link rel="Shortcut Icon" href="<%=resourcePath%>images/sys/favicon.ico" type="image/x-icon"/>
<link href="<%=resourcePath%>bootstrap3.0.3/css/bootstrap.css" rel="stylesheet">
</head>
<body class="container" style="margin-top:30px;vertical-align: middle;">
     <form class="form-horizontal" role="form" action="<%=basePath%>login.cnbi" method="post">
     <c:if test="${errorMsg != null}" var="visits"> 
        <div class="form-group">
		    <label for="suser" class="col-sm-2 control-label"></label>
		    <div class="col-sm-10">
		      <div class="alert alert-danger fade in">
		        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
		       <h4>温馨提示</h4>
		        <p>${errorMsg}</p>
		        <p>
		        </p>
		      </div>
		    </div>
      </div>  
     </c:if>
  
  <div class="form-group">
    <label for="suser" class="col-sm-2 control-label">用户名称</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="suser" name="suser" placeholder="请输入用户名称" required autofocus>
    </div>
  </div>
  <div class="form-group">
    <label for="spassword" class="col-sm-2 control-label">用户密码</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="spassword"  name="spassword"  placeholder="请输入用户密码" required>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox"> 记住
        </label>
      </div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
     <!--  <button type="button" data-loading-text="正在加载..." class="btn btn-primary">
       Loading state
      </button> -->
      <button type="submit" data-loading-text="正在提交数据..." class="btn btn-primary">确 定</button>
    </div>
  </div>
</form>
</body>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=resourcePath%>bootstrap3.0.3/js/bootstrap.min.js"></script>
<script type="text/javascript">
   $(function(){
	   $().button('loading');
	  
   });
</script>
</html>