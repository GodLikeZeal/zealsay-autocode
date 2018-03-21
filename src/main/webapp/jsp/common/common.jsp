<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="../../resource/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" id="bootscss">
<%----%>
<script type="application/javascript" src="../../resource/jquery/jquery-1.9.1.js"></script>
<script type="application/javascript" src="../../resource/json/json2.js"></script>
<script type="application/javascript" src="../../resource/custom/Custom.js?v=1000"></script>
<link href="../../resource/custom/Custom.css?V=999" rel="stylesheet">

<link href="../../resource/bootstrap/querystatistics.css" rel="stylesheet" type="text/css">
<link href="../../resource/bootstrap/components.css?v=6"  rel="stylesheet" type="text/css"/>
<link href="../../resource/bootstrap/plugins.css" rel="stylesheet" type="text/css"/>
<script src="../../resource/layer/layer/layer.js"></script>
<link href="../../resource/layer/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="../../resource/layer/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<script type="application/javascript" src="../../resource/bootstrap/js/bootstrap.min.js"></script>
<script src="../../resource/bootstrap-paginator/src/bootstrap-paginator.js"/>
<script type="application/javascript">
    var getNowDateMounth=function(monthMinus){
        var date=new Date();
        date.setMonth(date.getMonth()-monthMinus);
        var year=date.getFullYear();
        var month=date.getMonth()+1;
        if(month<10)
            month="0"+month;
        var day=date.getDate();
        if(day<10){
            day="0"+day;
        }
        var hour=date.getHours();
        if(hour<10){
            hour="0"+hour;
        }
        var min=date.getMinutes();

        if(min<10){
            min="0"+min;
        }
        var sec=date.getSeconds();
        if(sec<0){
            sec="0"+sec;
        }
        return year+"-"+month+"-"+day+"T"+hour+":"+min+":"+sec;
    }
    var getNowDate=function(){
        return getNowDateMounth(0);
    }
</script>
<script type="application/javascript">
    var currentIndex;
    /**
     * 显示进度条
     */
    var showLoading=function(){
        currentIndex = layer.load(1, {
            shade: [0.5, '#fff'] //0.1透明度的白色背景
        });
    }
    /**
     * 关闭进度条
     */
    var hideLoading=function(){
        layer.close(currentIndex);
    }
</script>