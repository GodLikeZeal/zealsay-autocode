<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/common.jsp" %>
<html>
<head>
    <title>代码自动生成</title>
</head>
<body>
<ul class="nav nav-tabs" id="myTab">
    <li class="active">
        <a href="#autoCode">代码自动生成</a>
    </li>
    <li><a href="#haveAutoCode">已生成列表</a></li>
</ul>

<div class="tab-content">
    <div class="tab-pane fade in active" id="autoCode">
        <iframe id="iframeAutoCode" style="width: 100%;height: 100%" src="autocode.jsp"></iframe>
    </div>
    <div class="tab-pane fade" id="haveAutoCode">
        <iframe id="iframehaveAutoCode" style="width: 100%;height: 100%" src="haveautocode.jsp"></iframe>
    </div>
</div>
<script type="application/javascript">
    $(function () {
        $('#myTab a').click(function (e) {
            e.preventDefault();//阻止a链接的跳转行为
            $(this).tab('show');//显示当前选中的链接及关联的content
        })
    })
</script>
</body>
</html>
