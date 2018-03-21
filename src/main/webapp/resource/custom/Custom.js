var baseUrl="http://localhost:8085/AutoCode/";


var MsgBox= function () {
    this.show= function (xhr,error) {
        var msg=xhr.responseText;
        msg=msg.replace(/\r/g,"")
        msg=msg.replace(/\n/g,"")

        if(typeof(msg)=="undefined" || msg==""){
            layer.alert(error,{icon:2});
        }
        else{
            layer.alert(msg,{icon:2});
        }
    }
}



Array.prototype.insert = function (index, item) {
    this.splice(index, 0, item);
};

//过滤信息，去除null，undefined等情况
function filterValue(text){
    if(text=="null" || text=="undefined" || typeof(text)=="undefined"){
        return "";
    }
    else{
        return text;
    }
}
//创建普通TD对象，不允许编辑
function CreateTD(text) {
    var mytd = document.createElement("td");
    mytd.setAttribute("nowrap", "true");
    mytd.setAttribute("align", "left");
    mytd.setAttribute("style", "cursor: hand");
    mytd.innerHTML = text;
    return mytd;
}
//创建普通TD对象，不允许编辑(隐藏)
function CreateTDHidden(text) {
    var td=CreateTD(text);
    td.setAttribute("style", "display:none");
    return td;
}

//创建下拉框对象
function CreateSelectTD(name,value,arrValues) {
    var mytd = document.createElement("td");
    mytd.setAttribute("align", "left");

    var mySelect = document.createElement("select");
    mySelect.id = name + "Select";
    for(var i=0;i<arrValues.length;i++){
        mySelect.options.add(new Option(arrValues[i][0], arrValues[i][1])); //这个兼容IE与firefox
    }
    mySelect.setAttribute("class", "form-control");
    mytd.appendChild(mySelect);
    mySelect.value = value;
    return mytd;
}

//创建输入框对象
function CreateInputTextTD(name, text) {
    var mytd = document.createElement("td");
    mytd.setAttribute("align", "left");
    var myInput = document.createElement("input");
    myInput.id = name + "input";
    myInput.setAttribute("class", "form-control");
    myInput.setAttribute("value", text);
    mytd.appendChild(myInput);
    return mytd;
}

/**
 * 创建按钮列
 * @constructor
 */
function CreateButtonTD(id) {
    var mytd = document.createElement("td");
    mytd.setAttribute("align", "left");
    mytd.setAttribute("nowrap", "nowrap");
    var innerHtml=[];
    innerHtml.push(" <button type=\"button\" class=\"btn green\" onclick=\"batchAutoCode('"+id+"');\"> " +
        "<i class=\"fa fa-save\"> 生成方案</i></button>")
    mytd.innerHTML =  innerHtml.join("");
    return mytd;
   /* */
}

//创建勾选框对象
function CreateCheckBox(name,ischecked,i){
    var mytd = document.createElement("td");
    mytd.setAttribute("align", "left");
    var myCheckBox = document.createElement("input");
    myCheckBox.type="checkbox";
    myCheckBox.id=name +"checkbox";
    myCheckBox.setAttribute("onchange","selectChange('"+i+"')");
    mytd.appendChild(myCheckBox);
    if(ischecked){
        myCheckBox.checked=true;
    }
    return mytd;
}

//获取指定的URL参数
function request(paras) {
    var url = location.href;
    var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
    var paraObj = {}
    for (i = 0; j = paraString[i]; i++) {
        paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=") + 1, j.length);
    }
    var returnValue = paraObj[paras.toLowerCase()];
    if (typeof (returnValue) == "undefined") {
        return "";
    } else {
        return returnValue;
    }
}




