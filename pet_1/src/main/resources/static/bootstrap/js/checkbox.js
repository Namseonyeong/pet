// 체크박스 전체 클릭 및 해지 
function setCheckAll(name){  
	var chk_listArr = $("input[name='"+ name +"']"); 
  	const checkbox = document.getElementById('allCheck'); // 체크박스 id 기입
  	
	for (var i=0; i<chk_listArr.length; i++) {
		if (checkbox.checked) {
			chk_listArr[i].checked = true;
		} else {
			chk_listArr[i].checked = false;
		}
	
	}
};
		
// 체크박스 선택 삭제
function deleteValue(url, returnUrl, name) {
	var url = url; // Controller로 보내고자 하는 URL
	var valueArr = new Array();
	var list = $("input[name='"+ name +"']"); 
	
	for(var i = 0; i < list.length; i++){ 
		 if(list[i].checked){ // 선택되어 있으면 배열에 값을 저장함
		    valueArr.push(list[i].value);
		 }
	}
	
	if (valueArr.length == 0){
		 alert("선택된 게시글이 없습니다.");
		 return false;
	}
	
	if (confirm("정말 삭제하시겠습니까?")) {
		$.ajax({
			url : url,  // 전송 URL
			type : 'POST', // POST 방식
			traditional : true, 
			data : {
				valueArr : valueArr // 보내고자 하는 data 변수 설정
			},
			beforeSend: function (jqXHR, settings) {
                var header = $("meta[name='_csrf_header']").attr("content");
                var token = $("meta[name='_csrf']").attr("content");
                jqXHR.setRequestHeader(header, token);
            },
			success: function(data) {
				if(data = 1) {
			    	alert("삭제 완료"); 
			        location.replace(returnUrl) //list로 페이지 새로 고침
			    } else {
			       alert("삭제 실패");
			    }
			 }
		});
	}
}