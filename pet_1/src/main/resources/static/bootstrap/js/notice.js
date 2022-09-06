/**
 *	글 등록 스크립트
 */
function saveNotice() {
	var noTitle = document.getElementById("noTitle");
	var noContent = document.getElementById("noContent");
	var f = document.forms.noticeform;

	if (noTitle.value == "") {
		alert("제목을 입력하세요.");
		noTitle.focus();
		return false;
	} else if (noContent.value == "") {
		alert("내용을 입력하세요.");
		noContent.focus();
		return false;
	} else {
		alert("글 등록 완료!")
		f.submit();
	}
}


/**
 *	글 수정 스크립트
 */
function updateNotice() {
	var noTitle = document.getElementById("noTitle");
	var noContent = document.getElementById("noContent");
	var f = document.forms.noUpdateForm;

	if (noTitle.value == "") {
		alert("제목을 입력하세요.")
		noTitle.focus();
		return false;
	} else if (noContent.value == "") {
		alert("내용을 입력하세요.")
		noContent.focus();
		return false;
	} else {

		f.submit();
	}
}

 /*function removeComma(input) {
	return input.value.replace(/,/g, "");
}*/

/**
 *   상품 수정 페이지 이동
 */
/*function go_mod(pseq) {
	document.getElementById("detail_form").action = "worker_product_update_form?pseq=" + pseq;
	document.getElementById("detail_form").submit();
}*/

/**
 *   상품 수정 , 입력 확인
 */
/*function go_mod_save(pseq) {
	var kind = document.getElementById("kind");
	var name = document.getElementById("name");
	var price1 = document.getElementById("price1");
	var price2 = document.getElementById("price2");
	var price3 = document.getElementById("price3");
	var content = document.getElementById("content");
	  var useyn = document.getElementById("useyn");
   var bestyn = document.getElementById("bestyn");
	var theForm = document.getElementById("update_form");

	if (kind.value == "") {
		alert("상품 종류를 입력하세요.");
		kind.focus();
		return false;
	} else if (name.value == "") {
		alert("상품명을 입력하세요.");
		name.focus();
		return false;
	} else if (price1.value == "") {
		alert("상품 원가를 입력하세요.");
		price1.focus();
		return false;
	} else if (price2.value == "") {
		alert("판매가를 입력하세요.");
		price2.focus();
		return false;
	} else if (content.value == "") {
		alert("상품 설명을 입력하세요.");
		content.focus();
		return false;
	} else {
		   if (useyn.checked == true) {
		 useyn.value = 'y';
	  } else {
		 useyn.value = 'n';
	  }

	  if (bestyn.checked == true) {
		 bestyn.value = 'y';
	  } else {
		 bestyn.value = 'n';
	  } 
		price1.value = removeComma(price1);
		price2.value = removeComma(price2);
		price3.value = removeComma(price3);

		theForm.encoding = "multipart/form-data";
		theForm.action = "worker_product_update";
		theForm.submit();
	}*/
