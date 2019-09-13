$(function () {
    console.log("艾达祝你身体健康");

    // 分页
    let url = "/api/articles/";
    let flag = "p";
    pageHelper(url, flag, loadData);

    search();
});


/**
 * 构造展示数据的dom, 需要根据文档的结构来修改
 * @param data 以数组组合的对象
 */
function loadData(data) {
    let ele = '';
    for (let i = 0; i < data.length; i++) {
        // 格式化日期
        data[i].editTime = formatDate(new Date(data[i].editTime));
        ele += '' +
            '<div class="posts-list js-posts">' +
            '<div class="post post-layout-list" data-aos="fade-up">' +
            '<div class="status_list_item icon_kyubo">' +
            '<div class="status_user" style="background-image: url(../images/article-list/' + getRandomInteger(1, 17) + '.jpg)">' +
            '<div class="status_section">' +
            '<p class="section_p" style="color: #8d908b;font-family: 楷体,serif">' +
            data[i].editTime + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
            data[i].readNum + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + data[i].tags +
            '</p>' +
            '<a href="' + '/article/' + data[i].id + '" class="status_btn">' + data[i].title + '</a>' +
            '<p class="section_p">' + data[i].summary + '</p>' +
            '</div></div></div></div>';
    }
    $("#content").html(ele);
}


// [min, max]
function getRandomInteger(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
}


