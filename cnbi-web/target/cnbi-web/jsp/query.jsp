<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="common/common.jsp"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SQL录入取数测试</title>
<link href="${bootstrapPath}css/bootstrap.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
   $(function(){
	   $("#reset").click(function(){
		    $("#sql").text("");
	   });
   });
   function validate_form(thisform){
	   with (thisform){
		   var text = $("#sql").val().toLowerCase();
		  text = text.replace(/(^\s*)|(\s*$)/g, "");
		   if(text.indexOf("from") == -1 ||text.indexOf("select") == -1){
			   alert("不合法的SQL语句！"); 
			   return false;
		   }else{
			   return true;
		   }
	   }
   }
</script>
</head>
<body>
	<div class="container" style="margin-top: 20px;">
		<form class="form-horizontal" action="${ctx}query.cnbi" method="post" onsubmit="return validate_form(this);">
			<fieldset>
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
				<div id="legend" class="">
					<legend class="">SQL取数测试</legend>
				</div>
				 <div class="form-group">
					    <label for="company" class="col-sm-2 control-label">公司</label>
					    <div class="col-sm-10">
						     <select class="input-xlarge" name="company" id="company" style="width:100%">
								<option>总公司</option>
							</select>
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="year" class="col-sm-2 control-label">年份</label>
					    <div class="col-sm-10">
					      <select class="input-xlarge" name="year" id="year"  style="width:100%">
							<option value="2013">2013</option>
							<option value="2012">2012</option>
							<option value="2014">2014</option>
						</select>
					    </div>
					  </div>
					   <div class="form-group">
					    <label for="month" class="col-sm-2 control-label">月份</label>
					    <div class="col-sm-10">
					      <select class="input-xlarge" name="month" id="month"  style="width:100%">
							<option value="01">1</option>
							<option value="02">2</option>
							<option value="03">3</option>
							<option value="04">4</option>
							<option value="05">5</option>
							<option value="06">6</option>
							<option value="07">7</option>
							<option value="08">8</option>
							<option value="09">9</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
						</select>
					    </div>
					  </div>
					   <div class="form-group">
					    <label for="helpsql" class="col-sm-2 control-label">请输入正确的SQL语句：</label><!-- autofocus -->
					    <div class="col-sm-10">
					      <textarea name="helpsql" id="helpsql"style="margin: 0px; width: 100%; height: 150px;" placeholder="请输入正确语法的SQL语句!" required>
					        <c:if test="${sql != null}" var="visits">${sql}</c:if>
					      </textarea>
					    </div>
					  </div>
					  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <button type="submit" class="btn btn-primary">执行查询</button>
					      <button type="reset" id="reset" style="margin-left:30px;"class="btn btn-danger">重置清空</button>
					    </div>
					  </div>
			</fieldset>
		</form>
	</div>
	<!--  <table class="table table-striped table-bordered">
    		#createTableHeader($headerArr)
    		<tbody>
    			#foreach($bean in $modelList)
    			<tr>
    			     #foreach($itemIndex in [0..8])
    					#set($item = "index" + $itemIndex)
    					 <td >$bean.getValue("$item")</td>
    				 #end
    			  </tr>
    			#end
    		</tbody>
    </table> -->
    <c:set var="colnames" value="${datas.get(0).getBeanMap()}"/>
	<div class="container">
		<table class="table table-striped table-bordered">
			<thead>
				<tr> <%-- <td>${colnames[entry.key]}</td> --%>
					<c:forEach var="entry" items="${colnames}" varStatus="idx">
						<td>${entry.key}</td>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${datas}" varStatus="idx">
                              <tr> <%-- <td>${colnames[entry.key]}</td> --%>
								<c:forEach var="entry" items="${colnames}" varStatus="idxs">
									<td>${bean.getValue(entry.key)}</td>
								</c:forEach>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
<script src="${bootstrapPath}js/bootstrap.min.js"></script>

</html>