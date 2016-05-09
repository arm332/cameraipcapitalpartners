<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test</title>
<style>
body {
	font-family: arial, helvetica, sans-serif;
	margin: 0;
}
div {
	width: 100%;
}
div>img {
	width: 100%
}
div>p {
	word-wrap: break-word;
}
@media (min-width: 480px)
	and (max-width: 767px) {
	div { float: left; width: 50% }
	div:nth-child(3n) { clear: left }
}
@media (min-width: 768px)
	and (max-width: 1023px) {
	div { float: left; width: 33% }
	div:nth-child(4n) { clear: left }
}
@media (min-width: 1024px) {
	div { float: left; width: 25% }
	div:nth-child(5n) { clear: left }
}
</style>
</head>
<body>
<div>
	<h1>Proxy PHP</h1>
	<img src="http://iprj2.invprof.com.br/cameras-test/forward.php?url=http://cam11.invprof.com.br/image.jpg?cache=0&rnd=0">
	<p>http://iprj2.invprof.com.br/cameras-test/forward.php?url=http://cam11.invprof.com.br/image.jpg?cache=0&rnd=0</p>
</div>
<div>
	<h1>Proxy Java com URL</h1>
	<img src="http://cameraipcapitalpartners.appspot.com/forward?url=http://cam11.invprof.com.br/image.jpg?cache=0&rnd=0">
	<p>http://cameraipcapitalpartners.appspot.com/forward?url=http://cam11.invprof.com.br/image.jpg?cache=0&rnd=0</p>
</div>
<div>
	<h1>Proxy Java com ID</h1>
	<img src="http://cameraipcapitalpartners.appspot.com/forward?cam=cam11&cache=0&rnd=0">
	<p>http://cameraipcapitalpartners.appspot.com/forward?cam=cam11&cache=0&rnd=0</p>
</div>
<div>
	<h1>Acesso direto</h1>
	<img src="http://cam11.invprof.com.br/image.jpg">
	<p>http://cam11.invprof.com.br/image.jpg</p>
</div>
</html>