$(function () {
    $("#datatablesSimple tbody tr").click(function () {
        let menuCode = $("td:nth-of-type(1)", this).text();
        location.href = "/admin/suit-box/menu/modify?menuCode=" + menuCode;
    })
})