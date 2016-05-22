<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>A Page</title>
</head>
<body>
<h3>This is the deck</h3>

	<form action="/Briscas/rest/main" >
		<input type="text" name="name" /> 
		<input type="submit" />
	</form>
	
	<div id="dealerHand">
	
	</div>
	<div id="dealerPile">
	
	</div>
	
	<div id="playerHand">
	
	</div>
	
	<div id="playerPile">
	</div>
	
	<div id="start">
	<form action="/Briscas/rest/deal">
	<button id="startButton">Play!</button>
	</form>
	
	</div>
	
	<script src='./js/app.js'></script>
</body>


</html>