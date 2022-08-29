/**
 * 장바구니 체크박스
 */
 // 체크박스 선택 삭제
function deleteCart(url, returnUrl, name) {
	var url = "/deleteCart";
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


/*
*장바구니에 담기
*/

function go_insertCart(id, pSeq){
	/*
	var qty = document.getElementById("cart_strock").value;
	if(qty == ""){
		alert("수량을 입력해주세요")
		document.getElementById("cart_strock").focus();
		return false;
	} else if (qty > 50){
		alert("수량이 너무 큽니다");
		document.getElementById("cart_strock").focus();
		return false;
	} else {
		document.getElementById("theForm").action = "/insertCart"; //장바구니 저장 요청 URL
		document.getElementById("theForm").submit();
	}
}
	*/
	
	
	var theForm = document.getElementById("theForm");
	
	var qty = document.getElementById(id).previousSibling.value;
	
	console.log("id=" + id + ", qty="+qty);
	
	theForm.action = "/insertCart?"+"pSeq=" + pSeq + "cartStrock=" + qty;
	
	
	var go_insertCart = document.getElementById("go_insertCart");
	
	var go_insertCart = cart_strock.previousSibling;
	
	console.log("go_insertCart=" + insertCart, "cart_strock=" + cart_strock)
	
	return cartList;
	
	}