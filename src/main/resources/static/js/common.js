function htmlEncode(str) {
    let s = "";
    if (str.length === 0) return "";
    s = str.replace(/&/g, "&gt;");
    s = s.replace(/</g, "&lt;");
    s = s.replace(/>/g, "&gt;");
    s = s.replace(/ /g, "&nbsp;");
    s = s.replace(/'/g, "&#39;");
    s = s.replace(/"/g, "&quot;");
    s = s.replace(/\n/g, "<br>");
    return s;
}

function htmlDecode(str) {
    let s = "";
    if (str.length === 0) return "";
    s = str.replace(/&gt;/g, "&");
    s = s.replace(/&lt;/g, "<");
    s = s.replace(/&gt;/g, ">");
    s = s.replace(/&nbsp;/g, " ");
    s = s.replace(/&#39;/g, "\'");
    s = s.replace(/&quot;/g, "\"");
    s = s.replace(/<br>/g, "\n");
    return s;
}


function storageAvailable() {
    return typeof (Storage) !== "undefined";
}

function addTokenHader(request) {
    let token = localStorage.getItem("token");
    request.setRequestHeader("token", token);
}

// 登出
function signOut() {
    let token = localStorage.getItem("token");
    if (token != null) {
        localStorage.removeItem("token");
        $.ajax({
            url: "/api/admin/sign-out",
            dataType: "json", // 服务器返回的数据类型
            beforeSend(request) {
                request.setRequestHeader("token", token);
            },
            success: function (result) {
                console.log(result);
            }
        });
    }
    location.href = "/admin/sign-in";
}


function writeJS(src) {
    document.write('<script src="' + src + '?t=' + new Date().getTime() + '" type="text/javascript"><\/script>');
}

/*
    yyyy-mm-dd HH:mm:ss
 */
function formatDate(date) {
    let str = "";
    str = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDay() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
    return str;
}


function search() {
    let searchInputEle = $("#search-input");
    let searchResultEle = $("#search_filtered");

    searchInputEle.keyup(function () {
        $.ajax({
            url: "/api/search",
            type: "POST",
            data: {"input": searchInputEle.val()},
            dataType: "json",  //服务器返回的数据类型
            success: function (result) {
                // console.log(result);
                searchResultEle.empty();
                let data = result.data;
                let len = data.length;
                let ele = '';
                for (let i = 0; i < len; i++) {
                    ele += '<li><a href="/article/' + data[i].id + '" target="_blank">' + data[i].title + '</a></li>';
                }
                searchResultEle.append(ele);
            }
        });
    });
}