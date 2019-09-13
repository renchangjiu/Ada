/*
Pagination
Version 1.0
Author: su
 */

/**
 * 分页函数
 * @param url  返回json 的链接, 该json 需要具有一定的格式
 * @param flag  分页条的dom 标记, 注意, 同一应用程序中, flag 不可重复
 * @param loadDataFunc 构造每页数据的方法, 该方法的形参是url返回的json 数据
 */
function pageHelper(url, flag, loadDataFunc) {
    // 初始化页码
    let curPage = 1;
    // 点击文章后返回时记住当前页码,
    if (typeof (Storage) === "undefined") {
        alert("您的浏览器不支持Web 存储, 无法正常使用本站");
        return;
    }
    if (sessionStorage.getItem(flag) != null && sessionStorage.getItem(flag) !== undefined) {
        curPage = sessionStorage.getItem(flag);
    }
    loadPage(url, curPage, flag, loadDataFunc);

    $("#page-helper").on("click", "a", function () {
        let p = $(this).attr(flag);
        loadPage(url, p, flag, loadDataFunc);
    });
}

/**
 * 构造展示数据的dom, 需要根据文档的结构来修改
 * 该函数仅做示例用, 注释不要打开
 * @param data 以数组组合的对象
 */

/*function loadData(data) {
    console.log("is me");
    let ele = '';
    for (let i = 0; i < data.length; i++) {
        ele += '<div class="post-feed" style="min-height: 200px">' +
            '<article class="post-card post tag-php no-image">' +
            '<div class="post-card-content">' +
            '<a class="post-card-content-link" href="/article/' + data[i].id + '/">' +
            '<header class="post-card-header">' +
            '<p class="post-card-tags">' + data[i].editTime + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
            data[i].readNum + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + data[i].tags +
            '</p>' +
            '<h2 class="post-card-title">' + data[i].title + '</h2>' +
            '</header>' +
            '<section class="post-card-excerpt">' +
            '<p>' + data[i].summary + '</p>' +
            '</section></a></div></article>' +
            '</div>';
    }
    $("#content").html(ele);
}*/


/**
 * 构造分页条
 * @param curPage 目标页码
 * @param totalPage 服务器端返回的总页数
 * @param flag 分页条的dom 标记, 注意, 同一应用程序中, flag 不可重复
 */
function loadPageBar(curPage, totalPage, flag) {
    let pageEle = $("#page-helper");
    pageEle.css("text-align", "center");
    pageEle.empty();
    let ele = '<ul class="pagination">';

    // 如果当前页是第一页
    if (parseInt(curPage) === 1) {
        ele += '<li class="disabled"><span >&lsaquo;</span></li>';
    } else {
        ele += '<li class="page-item"><a href="javascript:void(0)" ' + flag + '="' + (parseInt(curPage) - 1) + '">&lsaquo;</a></li>';
    }

    let begin = 0;
    let end = 0;

    // 如果总页数大于５
    if (totalPage <= 5) {
        begin = 1;
        end = totalPage;
    } else {
        begin = parseInt(curPage) - 2;
        end = parseInt(curPage) + 2;
        if (begin < 1) {
            begin = 1;
            end = 5
        }
        if (end > totalPage) {
            begin = totalPage - 4;
            end = totalPage;
        }
    }

    for (let i = begin; i <= end; i++) {
        if (parseInt(curPage) === i) {
            ele += '<li class="active"><a href="javascript:void(0)" ' + flag + '="' + i + '">' + i + '</a></li>';
        } else {
            ele += '<li ><a href="javascript:void(0)" ' + flag + '="' + i + '">' + i + '</a></li>';
        }
    }

    // 如果当前页是最后一页
    if (parseInt(curPage) === parseInt(totalPage)) {
        ele += '<li class="disabled"><span >&rsaquo;</span></li>';
    } else {
        ele += '<li><a href="javascript:void(0)" ' + flag + '="' + (parseInt(curPage) + 1) + '">&rsaquo;</a></li>';
    }
    ele += '</ul>';
    pageEle.html(ele);
}

function loadPage(url, curPage, flag, loadDataFunc) {
    $.ajax({
        url: url + curPage,
        type: "GET",
        dataType: "json",
        success: function (result) {
            if (result.success) {
                if (result.data != null) {
                    loadDataFunc(result.data.objects);
                    loadPageBar(result.data.curPage, result.data.totalPage, flag);
                    // 把当前页码放入sessionStorage, 每次请求后更新
                    sessionStorage.setItem(flag, curPage);
                }
            } else {
                // 如果请求的页码超过最大页码
                if (result.status === 450) {
                    loadPage(url, 1, flag, loadDataFunc);
                }
            }
        }
    });
}


