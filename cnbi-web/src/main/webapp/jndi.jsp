<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.sql.*"%>
<%@ page language="java" import="java.math.*"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JNDI Test</title>
    </head>
    <body>

    <h1>JNDI Test</h1>
    
	<div align="center">
		<%
		String jndiName=request.getParameter("jndi");
		if(jndiName==null){
			jndiName="jndi";
		}
		String[] jndiPrefix = { "java:comp/env/", "java:comp/env/jdbc/", "jdbc/", "java:","" };
		javax.naming.InitialContext ctx = new javax.naming.InitialContext();
		for (int i=0;i<jndiPrefix.length;i++) {
			String prefix =jndiPrefix[i];
			out.print("<div>");
			String jndiStr = prefix + jndiName;
			out.print("jndi:"+jndiStr);
			try {
				javax.sql.DataSource ds = (javax.sql.DataSource)ctx.lookup(jndiStr);
				try {
					java.sql.Connection conn = ds.getConnection();
					conn.close();
					out.print("<span style='color:blue'>获取连接成功.</span>");
				} catch (java.lang.Exception e1) {
					out.print("<span style='color:red'>获取数据库连接失败!</span>");
				}
				out.print("<span style='color:blue'>已找到.</span>");
			} catch (java.lang.Exception e2) {
				out.print("<span style='color:red'>未找到!</span>");
			}
			out.print("</div>");
		}
		%>
		
	</div>
    
    </body>
</html>
    