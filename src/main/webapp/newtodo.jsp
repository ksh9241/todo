<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<style>
body {
	background-color: rgb(150, 142, 142);
}

.container {
	background-color: white;
	position: fixed;
	top: 5px;
	left: 10%;
	width: 80%;
	height: 500px;
	overflow: auto;
}

.content {
	margin-left: 20%;
}

.content>div {
	margin-top: 30px;
}

button {
	width: 100px;
	height: 30px;
}

input {
	height: 30px;
	margin-top: 10px;
}

#back {
	background-color: white;
	font-weight: bold;
}

#add, #reset {
	background-color: skyblue;
	color: white;
	border: 0;
	outline: 0;
}
</style>
</head>
<body>
	<div class="container">
		<div style="text-align: center; margin-top: 20px; font-size: 25px;">
			<b>할일 등록</b>
		</div>

		<form id="nf" name="nf" action="newtodoEnd" onsubmit="return false"
			method="post">
			<div class="content">
				<div>
					<a>어떤 일인가요?</a><br> <input id="nextwork" name="content"
						type="text" placeholder="할일을 작성하세요(최대24자)" style="width: 70%;"
						maxlength="24">
				</div>

				<div>
					<a>누가 할 일인가요?</a><br> <input id="workman" name="name"
						type="text" placeholder="홍길동" style="width: 30%;">
				</div>
				<div>
					<a>우선순위를 선택하세요</a><br> <input type="radio" value="1"
						name="sequence">1순위 <input type="radio" value="2"
						name="sequence">2순위 <input type="radio" value="3"
						name="sequence">3순위

				</div>

				<div>
					<button id="back">< 이전</button>
					<button id="add" type=submit
						style="margin-left: 100px; margin-right: 20px;">제출</button>
					<button id="reset" type="reset">내용지우기</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script>
	document.getElementById('back').onclick = function() {
		location.href = "./main"
	}

	document.getElementById('add').onclick = function() {
		var work = document.getElementById("nextwork").value;
		var man = document.getElementById("workman").value;
		var numLen = document.getElementsByName("sequence").length;
		var num;
		for (var i = 0; i < numLen; i++) {
			if (document.getElementsByName("sequence")[i].checked == true) {
				num = document.getElementsByName("sequence")[i].value;
			}
		}
		if (!work) {
			alert("해야할 일을 입력하세요");
			document.getElementById("nextwork").focus();
			return;
		} else if (!man) {
			alert("해야할 사람을 입력하세요");
			document.getElementById("workman").focus();
			return;
		} else if (!num) {
			alert("우선순위를 입력하세요");
			return;
		} else {
			document.nf.submit();
			return true;
		}
	}
</script>
</html>