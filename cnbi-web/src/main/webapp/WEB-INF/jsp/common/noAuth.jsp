<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath(), 
	basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/",
	resourcePath = basePath + "resources/";
%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>有关授权</title>
<link rel="Shortcut Icon" href="<%=resourcePath%>images/sys/favicon.ico" type="image/x-icon" />
<link href="<%=resourcePath%>bootstrap3.0.3/css/bootstrap.css" rel="stylesheet">
<style type="text/css">
.container{
    width:1170px;
}
</style>
</head>
<body class="container" style="margin-top:30px;vertical-align: middle;">
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
</body>
</html>
