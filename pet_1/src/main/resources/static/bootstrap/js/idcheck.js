// 아이디 체크
	var deny = 't';
	$(document).ready(function() {
	$("#idcheck").on("click", function() {
		$.ajax({
			type : "get",
			url : "id_check",
			data : {
				"id" : $("#id").val()
			},
			dataType : "json",
			success : function(data) {
				if (data.Check === "success") {
					$("#id_span_fail").hide();
					$("#id_span").text("사용가능한 아이디입니다.");
					$("#id_span").show();

				} else if (data.Check === "fail") {
					$("#id_span").hide();
					$("#id_span_fail").text("아이디가 중복되었습니다.");
					$("#id_span_fail").show();
				}

				if (data.Check2 === "deny") {
					deny = 'f';
				}
			},
			error : function() {
				alert("에러발생");
			}
		});
	});
});
