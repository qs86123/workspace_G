/**
 * Created by The_kid on 2017/2/17.
 */
$(function () {
   $("#btn1").click(function () {
       var userName=$("#txtName").val();
       var userPass=$("#txtPass").val();
       if(userName!=""&&userPass!=""){
           UserLogin(userName,userPass);
       }
       else{
           alert("username and passWord must not be null");
       }
   });
    function UserLogin(userName,userPass){
        $.ajax({
            type:"post",
            url:"userLogin",
            data:"action=Login&d="+new Date()+"&username="+userName+"&password="+userPass,
            success: function (data) {
                if(data=="true"){
                    window.location="ChatMain.html";
                }else{
                    alert("username or password is wrong");
                    return false;
                }
            }
        });
    }
});