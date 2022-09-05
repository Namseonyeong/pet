/**
 *	트레이너 예약 팝업창 호출.
 */
 function trainerResevPopup(trainerId) {
	document.domain = "localhost";
	var url = "/TrainerBooking?trainer="+ trainerId;
	window.open(url, "_blank", 'scrollbars=no, width=460, height=750');
}

/**
 *	펫시터 예약 팝업창 호출.
 */
 function sitterResevPopup() {
	document.domain = "localhost";
	var url = "/SitterBooking?sitter="+ sitterId;
	window.open(url, "_blank", 'scrollbars=no, width=460, height=750');
}

/**
 *	예약정보 입력확인 및 팝업닫으면서 submit 처리.
 */
function closeWithSubmit() {
	var f = document.forms.reservForm;
	document.domain = "localhost";
	opener.name = "TrainerProfile";
	f.target = opener.name;
	
	var rsDate = document.getElementById("rsDate");
	var rsTime = document.getElementById("rsTime");
	var rsTime2 = document.getElementById("rsTime2");
	var rsKind = document.getElementById("rsKind");
	//var theForm = document.getElementById("reservForm"); 
	
	if (rsDate.value == "") {
        alert("예약 날짜를 선택하세요.");
        rsDate.focus();
        return false;
    } else if (rsTime.value == "") {
        alert("예약 시간을 선택하세요.");
        rsTime.focus();
        return false;
    } else if (rsTime2.value == "") {
        alert("총 이용시간을 입력하세요.");
        rsTime2.focus();
        return false;
    } else if(rsKind.value == "") {
        alert("견종을 입력하세요.");
        rsKind.focus();
        return false;
    } else {
		alert ("예약 완료!");
		f.submit();
		self.close();
	}
	
}

