<%@page import="org.slf4j.Logger,org.slf4j.LoggerFactory"%>
<%@page import="com.gisquest.realestate.common.web.Servlets"%>
<%@page import="com.gisquest.realestate.common.utils.Exceptions"%>
<%@page import="com.gisquest.realestate.common.utils.StringUtils"%>
<%@page import="com.gisquest.realestate.manage.Utils.ServiceException" %>
<%@page contentType="text/html;charset=UTF-8" isErrorPage="true"%>

<%
	response.setStatus(500);
	// 获取异常类
	Throwable ex = Exceptions.getThrowable(request);
	if (ex != null) {
		LoggerFactory.getLogger("500.jsp").error(ex.getMessage(), ex);
	}

	// 编译错误信息
	String errorMsg="";
	if (ex != null) {
		errorMsg=ServiceException.ErrorMsg(ex);
		//sb.append(Exceptions.getStackTraceAsString(ex));
	} else {
		errorMsg="未知错误";
	}
	// 如果是异步请求或是手机端，则直接返回信息
	if (true) {
		out.print(errorMsg);
	}

	// 输出异常信息页面
	else {
%>
<!DOCTYPE html>
<html>
<head>
<title>500 - 系统内部错误</title>
</head>
<body>
	<div class="row">
		<div class="col-md-12 page-404">
			<div class="number">500</div>
			<hr>
			<div class="details" >
				<h1>系统内部错误</h1>
				<input type="button" class="btn red btn-block" value="返回上一页" onclick="history.go(-1);">
			</div>
			<hr>
			<div class="errorMessage" style="text-align:left">
				<%=StringUtils.toHtml(errorMsg)%>
			</div>
		</div>
	</div>
</body>
</html>
<%
	}
	out = pageContext.pushBody();
%>