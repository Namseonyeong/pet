/**
 * 장바구니 체크박스
 */
// 체크박스 선택 삭제
function deleteCart(url, returnUrl, name) {
    var url = "/deleteCart";
    var valueArr = new Array();
    var list = $("input[name='" + name + "']");

    for (var i = 0; i < list.length; i++) {
        if (list[i].checked) {
            // 선택되어 있으면 배열에 값을 저장함
            valueArr.push(list[i].value);
        }
    }

    if (valueArr.length == 0) {
        alert("선택된 게시글이 없습니다.");
        return false;
    }

    if (confirm("정말 삭제하시겠습니까?")) {
        $.ajax({
            url: url, // 전송 URL
            type: "POST", // POST 방식
            traditional: true,
            data: {
                valueArr: valueArr, // 보내고자 하는 data 변수 설정
            },
            success: function (data) {
                if ((data = 1)) {
                    alert("삭제 완료");
                    location.replace(returnUrl); //list로 페이지 새로 고침
                } else {
                    alert("삭제 실패");
                }
            },
        });
    }
}

/*
장바구니에 담기


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
	
	
	
	var theForm = document.getElementById("theForm");
	
	var qty = document.getElementById(id).previousSibling.value;
	
	console.log("id=" + id + ", qty="+qty);
	
	theForm.action = "/insertCart?"+"pSeq=" + pSeq + "cartStrock=" + qty;
	
	
	var go_insertCart = document.getElementById("go_insertCart");
	
	var go_insertCart = cart_strock.previousSibling;
	
	console.log("go_insertCart=" + insertCart, "cart_strock=" + cart_strock)
	
	return cartList;
	
	}
*/

//장바구니에 담기
function go_insertCart(button, pSeq) {
    var $theForm = $("#theForm");

    var $parent = $(button).parent();
    var $grand_parent = $parent.parent();
    console.log("grand_parent=", $grand_parent);
    $prodQty = $parent.find("input");
    qty = $prodQty.val();
    //console.log("Qty=", $prodQty.val());

    //$("#theForm").attr("action", "/insertCart?pSeq=" + pSeq + "&cartStrock=" + qty).submit();

    //$grand_parent.attr("method", "get");
    //$grand_parent.attr("action", "/insertCart?pSeq=" + pSeq + "&cartStrock=" + qty);
    $grand_parent.submit();
}

// 체크박스 전체 클릭 및 해지
function setCheckAll(name) {
    var chk_listArr = $("input[name='" + name + "']");
    const checkbox = document.getElementById("allCheck"); // 체크박스 id 기입

    for (var i = 0; i < chk_listArr.length; i++) {
        if (checkbox.checked) {
            chk_listArr[i].checked = true;
        } else {
            chk_listArr[i].checked = false;
        }
    }
}
/*
function insertorder(){
//	 var csTitle = document.getElementById("csTitle");
 //    var csContent = document.getElementById("csContent");
     var f = document.forms.theForm;
     
     f.submit();
	}
 /*    if (csTitle.value == "") {
        alert("제목을 입력하세요.");
        csTitle.focus();
        return false;
     } else if (csContent.value == "") {
        alert("내용을 입력하세요.");
        csContent.focus();
        return false;
     } else {
      alert("글 등록 완료!")
         f.submit();
     }
 }
	//var $theForm = $("#theForm");
	
	//$theForm.attr("action", "/insertorder");	
	//$theForm.submit();

*/

function insertorderview(name) {
    var list = $("input[name='" + name + "']");
    var checkFlag = false;

    for (var i = 0; i < list.length; i++) {
        if (list[i].checked) {
            checkFlag = true;
        }
    }

    if (!checkFlag) {
        alert("선택된 상품이 없습니다.");
        return false;
    }

    var $theForm = $("#theForm");

    alert("구매페이지로 이동합니다.");
    $theForm.attr("action", "/insertorderview");
    $theForm.submit();
}

function payment() {
    var $theForm = $("#theForm");
    alert("배송신청이 완료되었습니다");
    $theForm.attr("action", "/payment");
    $theForm.submit();
}
