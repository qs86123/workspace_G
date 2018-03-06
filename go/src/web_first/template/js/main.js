function aaa() {
    alert("aaa")
}

function ajaxData() {
    $.ajax({
        url: "/data",
        type: "GET",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            alert(data.name)
        }
    });
}
function httpUpload() {
    $.ajax({
        url: "/uploadByHttp",
        type: "GET",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            alert(data.msg)
        }
    });
}
