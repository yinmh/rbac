function post(url,param,calback) {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        calback(xhr);
    }
    xhr.open("post",url,true);
    xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    xhr.send(param);
}

function get(url,param,calback) {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        calback(xhr);
    }
    xhr.open("get",url+"?"+param,true);
    xhr.send();
}