$(function () {

    pageHelper();

});


function pageHelper() {
    // 初始化页码
    let curPage = 1;
    // 实现点击文章后返回时记住当前页码
    if (typeof (Storage) !== "undefined") {
        if (sessionStorage.getItem("op") != null) {
            curPage = sessionStorage.getItem("op");
        }
    }
    let url = "/api/admin/operations/";
    loadPage(url, curPage);

    $(".pagination").on("click", "a", function () {
        let p = $(this).attr("op");
        loadPage(url, p);
    });
}

/*
    构造展示数据的dom, 需要根据文档的结构来修改
    data : 以数组组合的对象
  */
function loadData(data) {
    let ele = '';
    /*
    <tr>
            <th class="tdleft">表</th>
            <th class="tdleft">方法</th>
            <th class="tdleft">时间</th>
            <th style="width: 50px;">目标</th>
        </tr>
     */
    for (let i = 0; i < data.length; i++) {
        ele += '<tr><td class="tdleft">' +
            '<a href="/admin/operation/' + data[i].id + '" target="_blank">' + data[i].table + '</a></td>' +
            '<td class="tdleft"><span class="gray">&nbsp;' + data[i].type + '</span></td>' +
            '<td class="tdleft">' + data[i].time + '</td>' +
            '</td></tr>' +
            '<tr>' +
            '<td colspan=4><div class="recon"  style="text-align: center">' +
            '<i style="color: red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RE:&nbsp;&nbsp;&nbsp;</i>' + data[i].message + '</div>' +
            '</td>' +
            '</tr>';

    }
    let articles = $("#operations");
    articles.html(ele);
}


/*
    构造显示分页效果的dom
    curPage ; 目标页码
    totalPage : 总页数
  */
function loadPageBar(curPage, totalPage) {
    let pageEle = $(".pagination");
    pageEle.empty();
    let ele = '';
    if (parseInt(curPage) === 1) {    // 如果当前页是第一页
        ele += '<li class="disabled"><span >&lsaquo;</span></li>';
    } else {
        ele += '<li class="page-item"><a href="javascript:void(0)" op="' + (parseInt(curPage) - 1) + '">&lsaquo;</a></li>';
    }

    let begin = 0;
    let end = 0;

    if (totalPage <= 5) {
        begin = 1;
        end = totalPage;
    } else {              // 如果总页数大于５
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
            ele += '<li class="active"><a href="javascript:void(0)" op="' + i + '">' + i + '</a></li>';
        } else {
            ele += '<li ><a href="javascript:void(0)" op="' + i + '">' + i + '</a></li>';
        }
    }

    if (parseInt(curPage) === parseInt(totalPage)) {    // 如果当前页是最后一页
        ele += '<li class="disabled"><span >&rsaquo;</span></li>';
    } else {
        ele += '<li><a href="javascript:void(0)" op="' + (parseInt(curPage) + 1) + '">&rsaquo;</a></li>';
    }
    pageEle.html(ele);
}

function loadPage(url, curPage) {
    $.ajax({
        url: url + curPage,
        type: "GET",
        dataType: "json",  //服务器返回的数据类型
        beforeSend: function (request) {
            addTokenHader(request);
        },
        success: function (result) {
            if (result.success) {
                let objects = result.data.objects;
                for (let i = 0; i < objects.length; i++) {
                    objects[i].time = formatDate(new Date(objects[i].time));
                }
                loadData(objects);
                loadPageBar(result.data.curPage, result.data.totalPage);
                // 把当前页码放入sessionStorage, 每次请求后更新
                sessionStorage.setItem("op", curPage);
            } else {
                if (result.status === 401) {
                    location.href = "/admin/sign-in";
                } else if (result.status === 450) {
                    sessionStorage.setItem("op", 1);
                    pageHelper();
                }
            }
        }
    });
}


