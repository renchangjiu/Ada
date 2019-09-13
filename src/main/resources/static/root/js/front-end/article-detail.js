$(function () {
    search();
    loadArticle();
    submit();
});


function loadArticle() {
    let url = location.href;
    let articleId = url.slice(url.lastIndexOf("/") + 1);

    $.ajax({
        url: "/api/article/" + articleId,
        dataType: "json",
        success: function (result) {
            if (result.success) {
                // console.log(result);
                let data = result.data;
                $("title").html(data.title);
                $("#title").html(data.title);
                let editTime = data.editTime.slice(0, 10);
                $("#editTime").text(editTime);
                $("#content").html(data.content);
                loadTags(data.tags);
            } else {
                if (result.status === 450) {
                    location.href = "/404"
                }
            }
        }
    });
}


function subSummary(summary) {
    if (summary.length > 138) {
        return summary.substr(0, 138) + "...";
    }
    return summary;
}

function loadTags(tags) {
    let tagsEle = $("#tags");
    let tagsArr = tags.split(" ");
    for (let tag of tagsArr) {
        let aEle = "<a href='javascript:void(0)'>" + tag + "&nbsp;&nbsp;</a>";
        tagsEle.append(aEle);
    }
}


// 提交评论
function submit() {
    $("#submit").click(function () {
        // 从url里获取当前文章的id
        let artId = location.href.slice(location.href.lastIndexOf("/") + 1);
        let submitBtn = $("#submit");

        if (!validateTextarea()) {
            $("#errorMsg").text("评论内容不能为空哦");
            return;
        }
        let name = $("#name").val();
        let content = $("#comment-content").val();
        submitBtn.attr("disabled", true);
        submitBtn.val("发送中...");
        $.ajax({
            url: "/api/comment/send",
            type: "POST",
            data: {"article.id": artId, "name": name, "content": content},
            dataType: "json",
            success: function (result) {
                submitBtn.attr("disabled", false);
                submitBtn.val("发送");
                if (result.success) {
                    submitBtn.attr("disabled", true);
                    let step = 5;
                    let interval = setInterval(function () {
                        submitBtn.val(step);
                        step--;
                        if (step === -1) {
                            clearInterval(interval);
                            submitBtn.attr("disabled", false);
                            submitBtn.val("发送");
                        }
                    }, 1000);
                    $("#comment-content").val("");
                    $("#errorMsg").empty();
                    localStorage.setItem("comment-name", name);
                    // 页面滚动到 #panel
                    $('html, body').animate({
                        scrollTop: $("#panel").offset().top
                    }, 500);
                    page();     // 重新分页
                } else {
                    alert(result.message);
                }
            }
        });
    });

}

function validateTextarea() {
    let result = false;
    let text = $("#comment-content").val();
    if (text !== "") {
        result = true;
    }
    return result;
}



















