/**
 * Created by The_kid on 2017/2/17.
 */
$(function(){
    //全局绑定ajaxStart事件
    $("#divMsg").ajaxStart(function(){
        $(this).show();
    });
    //全局绑定ajaxStart事件
    $("divMsg").ajaxStop(function () {
        $(this).html("completed").hide();
    });
    InitFace();
    GetMessageList();
    GetOnlineList();
    $("#btn1").click(function () {
        var $content=$("#txtContent");
        if($content.val()!=""){
            SendContent($content.val());
        }else{
            alert("send content cannot be null");
            $content.focus();
            return false;
        }
    });
    //表情图标单击事件
    $("table tr td img").click(function () {
        var strContent = $("#txtContent").val()
        +"<:"+this.id+":>";
        $("#txtContent").val("strContent");
    });
    //***************************************************
    //自定义发送聊天内容函数
    //参数content为聊天内容
    //***************************************************
    function SendContent(content){
        $.ajax({
            type:"post",
            url:"sendContent",
            data:"action=SendContent&d="+new Date()+"&content="+content,
            success: function (data) {
                if(data == "true"){
                	GetMessageList();
                	$("txtContent").val("");
                }else{
                    alert("send fail");
                    return false;
                }
            }
        });
    }
    //***************************************************
    //自定义返回聊天内容函数
    //参数data为返回聊天内容数据
    //***************************************************
    function GetMessageList(){
        $.ajax({
            type:"post",
            url:"getMessageList",
            data:"action-ChatList&d="+new Date(),
            success: function (data) {
                $("#divContent").html(data);
            }
        });
        AutoUpdContent();
    }
    //***************************************************
    //自定义返回在线人员函数
    //参数data为返回在线人员数据
    //***************************************************
    function GetOnlineList(){
        $.ajax({
            type:"post",
            url:"getOnlineList",
            data:"action-ChatList&d="+new Date(),
            success: function (data) {
                $("#divOnline").html(data);
            }
        });
    }
    //***************************************************
    //自定义设置表情图标函数
    //***************************************************
    function InitFace(){
        var strHTML="";
        for(var i=1;i<=10;i++){
            strHTML+="<img src='Face/'"+i+".gif' id='"+i+"'/>";
        }
        $("#divFace").html(strHTML);
    }
    //***************************************************
    //自定义定时之行返回聊天内容与在线人员函数
    //***************************************************
    function AutoUpdContent(){
        setTimeout(GetMessageList,5000);
        setTimeout(GetOnlineList,6000);
    }
});