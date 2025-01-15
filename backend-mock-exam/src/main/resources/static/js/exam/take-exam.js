$(document).ready(function() {
    $(".slide-box>ul").each(function() {
        $(this).find("input:checkbox").on("change", function() {
            // 한 문제 안에서 답변은 하나만 선택 가능
            const $checkboxes = $(this).closest("ul").find("input:checkbox");
            $checkboxes.not(this).prop("checked", false);

            // 한 문제 안에서 하나라도 답변이 선택되어 있으면 건너뛰기버튼 숨기고 다음버튼
            if ($checkboxes.filter(":checked").length > 0) {
                $(this).closest("ul").siblings(".btn_skip").css("display", "none");
                $(this).closest("ul").siblings(".btn_next").css("display", "initial");
            } else {
                $(this).closest("ul").siblings(".btn_next").css("display", "none");
                $(this).closest("ul").siblings(".btn_skip").css("display", "initial");
            }
        });
    });

    // "건너뛰기" 눌렀을 때 0값을 보냄
    $(".btn_skip").click(function() {
        if ($(this).text() === "건너뛰기") {
            const $ul = $(this).siblings("ul");
            $ul.find('input[class="zero"]').remove();

            const $input = document.createElement("input");
            $input.type = "checkbox";
            $input.name = "userAnswers";
            $input.classList.add("zero");
            $input.value = 0;
            $input.checked = true;
            $input.style = "display: none";

            $ul.append($input);
        }
    });
});
