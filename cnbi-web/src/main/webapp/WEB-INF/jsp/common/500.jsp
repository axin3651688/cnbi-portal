<%@ page session="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Exception!500页面</title>
</head>
<body>
<% Exception e = (Exception)request.getAttribute("exception"); %>
<H2>错误异常: <%= e.getClass().getSimpleName()%></H2>
<hr />
<P>错误描述：</P>
<%= e.getMessage()%>
<P>错误信息：</P>
<% e.printStackTrace(new java.io.PrintWriter(out)); %>
</body>
</html>