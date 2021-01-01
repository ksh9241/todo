<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>

<style>
body {
	background-color: white;
}

#container {
	padding: 10px;
	border: 1px solid black;
	overflow: auto;
}

#newTodo {
	background-color: skyblue;
	color: white;
	width: 150px;
	height: 40px;
	margin: 40px 10px;
}

#header {
	float: right;
}

#todotable {
	float: left;
	margin-top: 5%;
	margin-left: 10%;
	display: inline-flex;
}

#todo {
	position: absolute;
	color: rgb(22, 89, 167);
	font-size: 20px;
	font-weight: bold;
	transform: rotate(-30deg);
	margin-top: 30px;
}

.th {
	background-color: rgb(22, 89, 167);
	color: white;
	width: 250px;
	height: 40px;
	text-align: left;
	padding-left: 30px;
}

.td {
	background-color: skyblue;
	color: black;
	width: 250px;
	height: 70px;
	text-align: left;
	padding: 10px 10px 10px 30px;
}

img {
	width: 10px;
	height: 10px;
}

.date {
	font-size: 12px;
}

button {
	border: 0;
	outline: 0;
}
</style>
</head>
<body>
	<div id="container">
		<div id="todo">나의 해야할 일들</div>
		<div id="header">
			<button id="newTodo" onclick="newTodo()">새로운 TODO 등록</button>
		</div>

		<div id="todotable">
			<table>
				<tr>
					<th class="th">TODO</th>
				</tr>

				<!-- 데이터가 없을 경우 -->
				<c:if test="${todo <= 0}">
					<tr>
						<td class="td"><b class="content">해당 일정이 없습니다.</b></td>
					</tr>
				</c:if>

				<c:forEach items="${list}" var="list">
					<!-- 데이터가 있을 경우 -->
					<c:if test="${list.type eq 'todo'}">
						<tr>
							<td class="td"><b class="content">${list.content}</b><br>
								<a class="date">등록날짜 : ${list.regDate}, ${list.name},
									${list.sequence}
									<button type="button" id="todo${list.idx}"
										name="todo${list.idx}" onclick="update(${list.idx})"
										style="margin-left: 15px;">
										<img src="./images/화살표.png" alt="">
									</button>
							</a></td>
						</tr>
					</c:if>
				</c:forEach>
			</table>

			<table>
				<tr>
					<th class="th">DOING</th>
				</tr>

				<!-- 데이터가 없을 경우 -->
				<c:if test="${doing <= 0}">
					<tr>
						<td class="td"><b class="content">해당 일정이 없습니다.</b></td>
					</tr>
				</c:if>
				
				<c:forEach items="${list}" var="list">
					<!-- 데이터가 있을 경우 -->
					<c:if test="${list.type eq 'doing'}">
						<tr>
							<td class="td"><b class="content">${list.content}</b><br>
								<a class="date">등록날짜 : ${list.regDate}, ${list.name},
									${list.sequence}
									<button type="button" id="todo${list.idx}"
										name="todo${list.idx}" onclick="update(${list.idx})"
										style="margin-left: 15px;">
										<img src="./images/화살표.png" alt="">
									</button>
							</a></td>
						</tr>
					</c:if>
				</c:forEach>

			</table>
			<table>
				<tr>
					<th class="th">DONE</th>
				</tr>
				
				<!-- 데이터가 없을 경우 -->
				<c:if test="${done <= 0}">
					<tr>
						<td class="td"><b class="content">해당 일정이 없습니다.</b></td>
					</tr>
				</c:if>

				<c:forEach items="${list}" var="list">
					<!-- 데이터가 있을 경우 -->
					<c:if test="${list.type eq 'done'}">
						<tr>
							<td class="td"><b class="content">${list.content}</b><br>
								<a class="date">등록날짜 : ${list.regDate}, ${list.name},
									${list.sequence} </a></td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
<script>
	function newTodo() {
		location.href = "./newtodo"
	}
	
	function update(id){
		var xhr=new XMLHttpRequest();
		var formData={'idx':id};
		xhr.onreadystatechange=function(){
			if(xhr.readyState===xhr.DONE){
				if(xhr.status===200||xhr.status===201){
					document.getElementById("container").innerHTML=xhr.responseText;
				}else{
					console.error(xhr.responseText);
				}
			}
		};
		xhr.open('POST','/todo/update');
		xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
		xhr.send(JSON.stringify(formData));
		
	}
	
</script>

</html>