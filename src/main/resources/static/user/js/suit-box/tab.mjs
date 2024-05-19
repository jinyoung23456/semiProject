$(function () {
    // tab
    let tabLength = $(".bl_tabBtn").length;
    let tabWD = 100 / tabLength;
    $(".bl_tabBtn").css("width", tabWD + "%");
    for (let i = 0; i < tabLength; i++) $(".bl_tabWrap:nth-of-type(" + (i + 1) + ") .bl_tabBtn").css("margin-left", (tabWD * i) + "%");

    $(".bl_tabBtn").click(function () {
        $(".bl_tabBtn").removeClass("hp_active")
        $(this).addClass("hp_active");
    });
})