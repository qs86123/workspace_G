/**
 * Created by Me1kro on 16/11/11.
 */
function post(url, params) {
    return $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(params)
    })
}
function get(url, params) {
    var params = params || '';
    return $.ajax({
        type: 'get',
        url: url
    })

}
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}