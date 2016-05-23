<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/main.css" rel="stylesheet">
<title>Briscas!</title>
</head>
<body>
<h3>WebApp?</h3>

<canvas id="briscasCanvas" height="850" width="1300">
	
	
</canvas>

	<div id="startDiv">
	<form action="/Briscas/rest/deal">
	<button id="startButton">Play!</button>
	</form>
	</div>
	
	<script src='./js/app.js'></script>
</body>


</html>