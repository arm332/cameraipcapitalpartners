<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test</title>
</head>
<body>
<h1>Proxy PHP</h1>
<img src="http://iprj2.invprof.com.br/cameras-test/forward.php?url=http://cam11.invprof.com.br/image.jpg?cache=0&rnd=0"
	title="http://iprj2.invprof.com.br/cameras-test/forward.php?url=http://cam11.invprof.com.br/image.jpg?cache=0&rnd=0">

<h1>Proxy Java Mais Proxy PHP</h1>
<img src="http://cameraipcapitalpartners.appspot.com/forward?url=http://iprj2.invprof.com.br/cameras-test/forward.php?url=http://cam11.invprof.com.br/image.jpg?cache=0&rnd=0"
	title="http://cameraipcapitalpartners.appspot.com/forward?url=http://iprj2.invprof.com.br/cameras-test/forward.php?url=http://cam11.invprof.com.br/image.jpg?cache=0&rnd=0">

<h1>Proxy Java Com URL</h1>
<img src="http://cameraipcapitalpartners.appspot.com/forward?url=http://cam11.invprof.com.br/image.jpg?cache=0&rnd=0"
	title="http://cameraipcapitalpartners.appspot.com/forward?url=http://cam11.invprof.com.br/image.jpg?cache=0&rnd=0">

<h1>Proxy Java Com ID</h1>
<img src="http://cameraipcapitalpartners.appspot.com/forward?cam=cam11"
	title="http://cameraipcapitalpartners.appspot.com/forward?cam=cam11">

<h1>Acesso direto</h1>
<img src="http://cam11.invprof.com.br/image.jpg"
	title="http://cam11.invprof.com.br/image.jpg">

<h1>Casa Proxy Java Com URL</h1>
<img src="http://cameraipcapitalpartners.appspot.com/forward?url=http://189.122.162.212:8080/photo.jpg"
	title="http://cameraipcapitalpartners.appspot.com/forward?url=http://189.122.162.212:8080/photo.jpg">

<h1>Casa Proxy Java Com ID</h1>
<img src="http://cameraipcapitalpartners.appspot.com/forward?cam=casa"
	title="http://cameraipcapitalpartners.appspot.com/forward?cam=casa">

<h1>Casa direto</h1>
<img src="http://189.122.162.212:8080/photo.jpg"
	title="http://189.122.162.212:8080/photo.jpg">
</body>
</html>