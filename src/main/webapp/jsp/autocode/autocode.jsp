<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/common.jsp" %>
<html>

<head>
    <title>自动生成代码</title>
</head>
<body>

<div class="portlet box blue">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-stack-exchange"> 代码自动生成</i>
        </div>
    </div>
    <div class="portlet-body">
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span2" id="divContent">
                    <table width="100%">
                        <tr style="height:10px"></tr>
                        <tr>
                            <td nowrap="true" width="80px">
                                表名：
                            </td>
                            <td width="50%" vertical-align="middle" colspan="3">
                                <select id="selectTable" onchange="getColumns();" class="form-control text-danger"/>
                            </td>
                            <td colspan="1" style="width:8px">&nbsp;</td>
                            <td nowrap="true"  width="80px">
                                生成路径：
                            </td>
                            <td width="50%" colspan="3">
                                <input type="text" id="txtPath" value="E:\AutoCode\CustomService" class="form-control text-info">
                            </td>
                        </tr>
                        <tr style="height: 10px;"></tr>
                        <tr>
                            <td nowrap="true"  width="80px">
                                模块名：
                            </td>
                            <td width="50%" colspan="3">
                                <input type="text" id="txtModuleName" value="" class="form-control text-success">
                            </td>
                            <td colspan="1" style="width:8px">&nbsp;</td>
                            <td nowrap="true"  width="80px">
                                类名：
                            </td>
                            <td width="50%" vertical-align="middle" colspan="3">
                                <input type="text" id="txtClassName" value="" class="form-control text-success" >
                            </td>

                        </tr>
                        <tr style="height: 10px;"></tr>
                        <tr>
                            <td nowrap="true">
                                创建者：
                            </td>
                            <td width="50%" colspan="3"  vertical-align="middle">
                                <input type="text" id="txtCreateBy" value="" class="form-control text-danger"/>
                            </td>
                            <td colspan="1" style="width:8px">&nbsp;</td>
                            <td nowrap="true">
                                数据库：
                            </td>
                            <td  colspan="3" width="50%" vertical-align="middle">
                                <select  id="dataBaseName" onchange="getTables();" value="" class="form-control text-primary"/>
                            </td>
                            <td colspan="1" style="width:8px">&nbsp;</td>
                            <td nowrap="true">
                                &nbsp;
                            </td>
                            <td width="25%" vertical-align="middle" colspan="3">
                                <table width="100%">
                                    <tr>

                                        <td>
                                            <button type="button" id="btnCreate" class="btn green" onclick="GetAllSetValue(false);">
                                                <i class="fa fa-save"> 保存</i>
                                            </button>
                                        </td>
                                        <td width="10px">&nbsp;</td>
                                        <td>
                                            <button type="button" id="btnAutoCode" class="btn blue-dark"
                                                    onclick="return GetAllSetValue(true);">
                                                <i class="fa fa-pencil"> 保存并生成代码</i>
                                            </button>
                                        </td>
                                        <%--<td style="width:10px">&nbsp;</td>
                                        <td>
                                            <button type="button" id="btnBatchAutoCode" class="btn yellow-gold"
                                                    onclick="batchAutoCode();">
                                                <i class="fa fa-sitemap"> 批量生成代码</i>
                                            </button>
                                        </td>--%>
                                        <td width="100%">
                                            &nbsp;
                                        </td>
                                    </tr>
                                </table>
                            </td>

                        </tr>
                    </table>
                </div>
                <div style="height: 10px;">
                </div>

                <div style="height: 10px;"></div>
                <div class="span2" id="scroll_table" style="overflow:auto">
                    <table id="tableColumn" class="table table-hover  table-bordered" width="100%">
                        <tr style="background-color: darkgrey">
                            <td>顺序号</td>
                            <td>字段名称</td>
                            <td>实体类属性</td>
                            <td>字段解释</td>
                            <td>MyBatis类型</td>
                            <td>实体类型</td>
                            <td>是否主键</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<input type="hidden" id="hidComments"/>
<input type="hidden" id="hidClassName"/>
<script type="application/javascript">
    var msg;

    function batchAutoCode() {
        var urlPath = baseUrl + "autocode/saveBatchClass";
        $.ajax({
            url: urlPath,
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            processData: false,
            success: function (result, status, xhr) {
                layer.alert("生成成功！", {icon: 1});
            },
            error: function (xhr, status, err) {
                msg.show(xhr, err);
            }
        });
    }
    function AutoCode() {
        showLoading();
        var urlPath = baseUrl + "autocode/saveClass";
        var name = $('#selectTable').val();
        var moduleName = $('#txtModuleName').val();
        var className = $('#txtClassName').val();
        var createBy =$('#txtCreateBy').val();
        urlPath += "?name=" + name + "&moduleName=" + moduleName + "&className=" + className+"&createBy="+encodeURIComponent(createBy);
        $.ajax({
            url: urlPath,
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            processData: false,
            success: function (result, status, xhr) {

                layer.alert("生成成功！", {icon: 1});
                hideLoading();
            },
            error: function (xhr, status, err) {
                msg.show(xhr, err);
                hideLoading();
            }
        });
    }
</script>
<script type="application/javascript">
    function GetSelcetOptions() {
        var arrValue = [];
        arrValue.push(["", ""]);
        arrValue.push(["String", "String"]);
        arrValue.push(["Long", "Long"]);
        arrValue.push(["Double", "Double"]);
        arrValue.push(["byte[]", "byte[]"]);
        arrValue.push(["java.util.Date", "java.util.Date"]);
        arrValue.push(["Timestamp", "Timestamp"]);
        arrValue.push(["Integer", "Integer"]);
        arrValue.push(["BigDecimal", "BigDecimal"]);
        return arrValue;
    }
    function GetAllSetValue(isCreateCode) {
        showLoading();
        var selectTableValue = $('#selectTable').val();
        var trs = $('#tableColumn').find('tr');
        //去除第一行的信息
        var arrTable = {};
        var tableName = $('#selectTable').val();
        var modlueName = $('#txtModuleName').val();
        var className = $('#txtClassName').val();
        var createBy=$("#txtCreateBy").val();
        var path = $('#txtPath').val();
        if (modlueName == "" || className == "" || tableName=="" || createBy=="") {
            layer.alert("请输入完整信息！");
            hideLoading();
            return;
        }
        var isHavePrimaryKey=false;
        for (var i = 1; i < trs.length; i++) {
            var isPk = (trs[i].children[6].children[0].checked) ? "1" : "0";
            if(isPk=="1"){
                isHavePrimaryKey=true;
            }
        }
        if(!isHavePrimaryKey){
            layer.alert("请选择主键！");
            hideLoading();
            return;
        }
        arrTable.name = tableName
        arrTable.className = className;
        arrTable.comments = modlueName;
        arrTable.bz = path;
        arrTable.createBy=createBy;

        var arrColumns = [];

        for (var i = 1; i < trs.length; i++) {
            var columnsJson = {};
            var sort = trs[i].children[0].innerText;
            var columnName = trs[i].children[1].innerText;
            var proertyName = trs[i].children[2].innerText;
            var proerptyChnName = trs[i].children[3].children[0].value;
            var columnType = trs[i].children[4].innerText;
            var propertyType = trs[i].children[5].children[0].value;
            var isPk = (trs[i].children[6].children[0].checked) ? "1" : "0";
            columnsJson.name = columnName;
            columnsJson.comments = proerptyChnName;
            columnsJson.javaType = propertyType;
            columnsJson.jdbcType = columnType;
            columnsJson.javaField = proertyName;
            columnsJson.sort = sort;
            columnsJson.isPk = isPk;
            arrColumns.push(columnsJson);
        }
        arrTable.columnList = arrColumns
        //alert(JSON.stringify(arrTable));

        urlPath = baseUrl + "autocode/save"
        $.ajax({
            url: urlPath,
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(arrTable),
            processData: false,
            success: function (result, status, xhr) {
                getTables(true, selectTableValue,isCreateCode);
                //hideLoading();
            },
            error: function (xhr, status, err) {
                msg.show(xhr, err);
                hideLoading();
            }
        });
    }
</script>
<script type="application/javascript">
    function GetSelectModleValue() {
        for (var i = 0; i < totalJson.length; i++) {
            var name = totalJson[i].name.toUpperCase();
            var selectName = $('#selectTable').val();
            if (typeof(selectName) != "undefined")
                selectName = selectName.toUpperCase();
            if (name == selectName) {
                var moduleName = totalJson[i].comments;
                var className = totalJson[i].className;
                $('#txtModuleName').val(moduleName);
                $('#txtClassName').val(className);
                return;
            }
            else {
                $('#txtModuleName').val(moduleName);
                $('#txtClassName').val(className);
            }
        }
    }
    window.onload = function () {
        showLoading();
        msg = new MsgBox();
        //getTables(false,"",false);
        getDatabase();
        $("#scroll_table").height((window.innerHeight - 120 - $("#divContent").height()) + "px");
    }
</script>


<script type="application/javascript">
    var totalJson;
    function getColumns() {
        showLoading();
        GetSelectModleValue();
        var tableName = $("#selectTable").val();
        var dataBaseName=$('#dataBaseName').val();
        urlPath = baseUrl + "autocode/getAllColumn?tablename=" + tableName+"&dataBaseName="+dataBaseName;
        $.ajax({
            url: urlPath,
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            processData: false,
            success: function (result, status, xhr) {
                result.sort(function (a, b) {
                    return parseInt(a.sort) > parseInt(b.sort) ? 1 : -1;
                })
                var table = document.getElementById("tableColumn"); //获取table对象
                var rowNum = table.rows.length; //获取table中所有行
                for (i = 1; i < rowNum; i++) //第一行是标题列，不删除
                {
                    table.deleteRow(i);
                    rowNum = rowNum - 1;
                    i = i - 1;
                }

                for (var i = 0; i < result.length; i++) {
                    var columnName = result[i].name;
                    var classPropertyName = result[i].javaField;
                    var comments = result[i].comments;
                    var sort = result[i].sort;
                    var jdbcType = result[i].jdbcType;
                    var entityType = result[i].javaType;
                    var isPk = result[i].isPk;
                    columnName = columnName.toUpperCase();
                    var tr = table.insertRow();
                    tr.appendChild(CreateTD(filterValue(sort)));
                    tr.appendChild(CreateTD(filterValue(columnName)));
                    tr.appendChild(CreateTD(filterValue(classPropertyName)));
                    tr.appendChild(CreateInputTextTD("comments", filterValue(comments)));
                    tr.appendChild(CreateTD(filterValue(jdbcType)));
                    tr.appendChild(CreateSelectTD("entityType", filterValue(entityType), GetSelcetOptions()));
                    tr.appendChild(CreateCheckBox("isPk", (isPk == "1" ? true : false), i));
                }
                hideLoading();
            },
            error: function (xhr, status, err) {
                msg.show(xhr, err);
                hideLoading();
            }
        });
    }
    function getTables(notloadcomments, selectValue,isCreateCode) {
        var dataBaseName=$('#dataBaseName').val();
        if(dataBaseName!=undefined) {
            urlPath = baseUrl + "autocode/getAllTable?dataBaseName="+dataBaseName;
            $.ajax({
                url: urlPath,
                type: 'GET',
                contentType: "application/json; charset=utf-8",
                processData: false,
                success: function (result, status, xhr) {
                    $("#selectTable").empty();
                    totalJson = result;
                    $("#selectTable").append("<option value=''></option>")
                    for (var i = 0; i < result.length; i++) {
                        var tablename = result[i].name.toUpperCase();
                        var moduleName = result[i].comments;
                        var className = result[i].className;
                        var zt = result[i].zt;
                        $("#selectTable").append("<option value='" + tablename + "'>" + tablename + ((zt == "未生成") ? "" : "(" + zt + ")") + "</option>"); //为Select追加一个Option(下拉项)
                        if (notloadcomments) {

                        }
                        else {
                            /*if (i == 0) {
                            $("#selectTable").val(tablename);
                            $('#txtModuleName').val(moduleName);
                            $('#txtClassName').val(className);
                            getColumns();
                        }*/
                    }
                }

                if

                (notloadcomments) {
                    $("#selectTable").val(selectValue);
                }
                if (isCreateCode){
                    AutoCode();
                }
                else{
                    if(notloadcomments){
                        layer.alert("保存成功！", {icon: 1});
                    }
                    hideLoading();
                }


            }
                ,
            error: function (xhr,
                             status, err) {
                //var msg=new MsgBox();
                msg.show(xhr, err);
                hideLoading();
            }
        });
        }
    }
    function getDatabase() {
        urlPath = baseUrl + "autocode/getDatabaseTableName"
        $.ajax({
            url: urlPath,
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            processData: false,
            success: function (result, status, xhr) {
                $("#dataBaseName").empty();
                totalJson = result;
                $("#dataBaseName").append("<option value=''></option>")
                for (var i = 0; i < result.length; i++) {
                    var dataBaseName = result[i].toUpperCase();
                    $("#dataBaseName").append("<option value='" + dataBaseName + "'>" + dataBaseName +  "</option>"); //为Select追加一个Option(下拉项)
                }
                hideLoading();
            }
        });

    }
</script>
<script type="application/javascript">

</script>
<script type="application/javascript">
    function selectChange(index) {
        var obj = $('#tableColumn').find('tr');
        var totalValue = 0;
        for (var i = 1; i < obj.length; i++) {
            if ((parseInt(index) + 1) != i) {
                obj[i].children[6].children[0].checked = false;
            }
        }

    }

</script>
</body>
</html>
