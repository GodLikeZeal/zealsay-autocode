/*



<%--<h5>登录 是一种态度</h5>--%>



 <div class="theme-popover">
 <div class="theme-poptit">
 <span id="spanTitle"></span><a href="javascript:;" title="关闭" class="close">×</a>
 <%--<h5>登录 是一种态度</h5>--%>
 </div>
 <div>
 <iframe id="srcFrame" src="" width="100%" height="100%"></iframe>
 </div>
 </div>
 <div class="theme-popover-mask"></div>

    */
document.write("<div class=\"theme-popover\">");
document.write("<div class=\"theme-poptit\">");
document.write("<span class=\"spanTitle\" id=\"spanTitle\"></span><a href=\"javascript:;\" title=\"关闭\" class=\"close\">×</a>");
document.write("</div>");
document.write("<div>");
document.write("<iframe id=\"srcFrame\" width=\"100%\" height=\"100%\"></iframe>");
document.write("</div>");
document.write("</div>");
document.write("<div class=\"theme-popover-mask\"></div>");

$(document).ready(function ($) {
    $('.theme-poptit .close').click(function () {
        closePop();
    })
})
var closePop=function(){
    $('.theme-popover-mask').fadeOut(100);
    $('.theme-popover').slideUp(200);
}
function SetPopover(width, height) {
    /* var left=(document.body.offsetWidth-width)/2;
     var top=(document.body.offsetHeight-height)/2;*/
    var left = (window.innerWidth - width) / 2;
    var top = (window.innerHeight - height) / 2;

    $('.theme-popover').css("width", width + "px");
    $('.theme-popover').css("height", height + "px");
    $('.theme-popover').css("top", top + "px");
    $('.theme-popover').css("left", left + "px");
}
