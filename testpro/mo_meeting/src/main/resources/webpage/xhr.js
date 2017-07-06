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