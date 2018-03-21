<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/common.jsp" %>
<html>
<head>
    <title>已生成的代码配置</title>
    <link href="../../resource/bootstrap-paginator/src/bootstrap-paginator.css?v=11" rel="stylesheet">

</head>
<body>
<div class="span2" id="scroll_table" style="overflow:auto;width: 100%">
    <table id="tableColumn" class="table table-hover  table-bordered" width="100%">
        <tr style="background-color: darkgrey">
            <td style="width: 70px" nowrap="nowrap">顺序号</td>
            <td style="width: 200px;" nowrap="nowrap">操作列</td>
            <td style="width: 150px;" nowrap="nowrap">表名</td>
            <td style="width: 100%;" nowrap="nowrap">模块名</td>
            <td style="width: 150px;" nowrap="nowrap">类名</td>
            <td style="width: 150px;" nowrap="nowrap">创建者</td>
            <td style="width: 150px;" nowrap="nowrap">创建时间</td>

        </tr>
    </table>
</div>
<div class="row-fluid">
    <div class="col-md-12" style="overflow: auto; margin-left:10px">

        <div class="pagination  pagination-mini" id="pagination">
            <ul></ul>
            <ul style="bordered-width: 0px;">
                <li>
                    <a style="bordered-width: 0px;"></a>
                </li>
            </ul>
        </div>
    </div>
</div>

<script type="application/javascript">

    var currentIndex=1;
    var paginSize=15;

    $(function(){
        loadTableList(currentIndex,true);
    })

    function loadTableList(curIndex,isCreatePaginbar){
        var start=(curIndex-1) * paginSize;
        showLoading();
        urlPath = baseUrl + "autocode/getAllGenTable?start="+start+"&size="+paginSize+"&order=name&sort=desc&islike=1";
        $.ajax({
            url: urlPath,
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            processData: false,
            success: function (result, status, xhr) {

                var totals=result.total;
                var table = document.getElementById("tableColumn"); //获取table对象
                var rowNum = table.rows.length; //获取table中所有行
                for (i = 1; i < rowNum; i++) //第一行是标题列，不删除
                {
                    table.deleteRow(i);
                    rowNum = rowNum - 1;
                    i = i - 1;
                }
                var dataResult = result.data;
                for (var i = 0; i < dataResult.length; i++) {
                    var id=dataResult[i].id;
                    var tableName = dataResult[i].name;
                    var modelName = dataResult[i].comments;
                    var className = dataResult[i].className;
                    var createBy = dataResult[i].createBy;
                    var createDate = dataResult[i].createDate;
                    var tr = table.insertRow();
                    tr.appendChild(CreateTD(filterValue((start+(i+1)))));

                    tr.appendChild(CreateButtonTD(id));
                    tr.appendChild(CreateTD(filterValue(tableName)));
                    tr.appendChild(CreateTD(filterValue(modelName)));
                    tr.appendChild(CreateTD(filterValue(className)));
                    tr.appendChild(CreateTD(filterValue(createBy)));
                    tr.appendChild(CreateTD(filterValue(createDate)));
                }
                if(isCreatePaginbar){
                    createPagingBarn(currentIndex, totals);
                }
                hideLoading();

            },
            error: function (xhr, status, err) {
                //var msg=new MsgBox();
                msg.show(xhr, err);
                hideLoading();
            }
        });
    }

    function batchAutoCode(id) {
        showLoading();
        var urlPath = baseUrl + "autocode/saveBatchClassById?id="+id;
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

    function createPagingBarn(cpage, _totalCount){
        var _re = /^[0-9]+.?[0-9]*$/;
        var pageCount;
        if (!_re.test(_totalCount)) {
            pageCount = 100;
            _totalCount = "...";
        } else {
            pageCount = Math.ceil(_totalCount / parseInt(paginSize))
        }
        var options = {
            currentPage: cpage,
            totalPages: pageCount,
            numberOfPages: 5,
            itemTexts: function(type, page, current) {
                switch (type) {
                    case "first":
                        return "首页";
                    case "prev":
                        return "上一页";
                    case "next":
                        return "下一页";
                    case "last":
                        return "末页";
                    case "page":
                        return page;
                }
            },
            onPageClicked: function(event, originalEvent, type, page) {
                if (currentIndex == page) {
                    return;
                }
                currentIndex = page;
                loadTableList(currentIndex);
            }
        };


        if (_totalCount == 0)
            $('#pagination').find('ul:first').empty();
        else
            $('#pagination').bootstrapPaginator(options);

        $('#pagination').find('a:last').text("共"+_totalCount+"条");
    }
</script>
</body>
</html>
