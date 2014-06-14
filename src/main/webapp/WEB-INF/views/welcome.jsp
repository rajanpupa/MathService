<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Welcome</title>
</head>
<body>
	<div id="wrapper">
		Welcome <br /> Time : ${serverTime}<br />

		<form id="expression_form" method="get" action="evaluate">
			<input name="expression" /><br/>
			<input type="submit" value="submit" />
		</form>
		<div id="response"></div>
	</div>
	<script src="resources/script/jquery.js" ></script>
	<script>
		$('#expression_form').on('submit', function(e) {
			e.preventDefault();
			var url = 'ajax/evaluate';
			
			$.post(url, $('#expression_form').serialize(), function(response) {
				$("#response").html(response);
			})
		});
	</script>
</body>
</html>