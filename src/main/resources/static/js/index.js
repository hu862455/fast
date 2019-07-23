var index = function () {

    function chooseTab(choosed) {
        $(".contentInfo").css("display","none");
        $("#"+choosed).css("display","block");
        var html = "<div id=\"createMember\" class=\"col-md-12 contentInfo\" th:replace=\"/member/createMember::createMember\" ></div>"
    }

    return{
        chooseTab:chooseTab
    }
}();