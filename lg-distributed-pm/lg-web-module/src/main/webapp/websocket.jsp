<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>ws</title>
<style type='text/css'>
#message {
	color: green;
	display: flex;
	justify-content: flex-start;
	flex-direction: column-reverse;
}

#text {
	background-color: #ooo;
	border: 2px solid pink;
}
</style>
</head>

<body>
	<h5>收购百度讨论组</h5>
	<br />
	<div id="str">
		name: ${requestScope.str}
	</div>
	<input type="text" id="text"
		style="border: 5px dotted blue; color: red"
		onfocus="borderColor(this);" onblur="clearTimeout(oTime);">
	<button onclick="send()">Send</button>
	<hr />
	<button onclick="closeWebSocket()">Close</button>
	<button onclick="clear11()">Clear</button>
	<div id="message"></div>
</body>

<script type="text/javascript">
	var websocket = null;

	//判断当前浏览器是否支持WebSocket
	if ('WebSocket' in window) {
		websocket = new WebSocket(
				"ws://10.0.0.43:7088/connect?paramKey=${requestScope.str}");
		/* websocket = new WebSocket("ws://192.168.31.28:28081/websocket/${requestScope.str}"); */
	} else {
		alert('Not support websocket');
	}

	//连接发生错误的回调方法
	websocket.onerror = function() {
		setMessageInnerHTML("error");
	};

	//连接成功建立的回调方法
	websocket.onopen = function(event) {
		setMessageInnerHTML("open");
	};

	//接收到消息的回调方法
	websocket.onmessage = function(event) {
		setMessageInnerHTML(event.data);
	};

	//连接关闭的回调方法
	websocket.onclose = function(event) {
		setMessageInnerHTML("close");
	};

	//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
	window.onbeforeunload = function() {
		websocket.close();
	};

	//将消息显示在网页上
	function setMessageInnerHTML(innerHTML) {
		/* alert(innerHTML); */
		document.getElementById('message').innerHTML += '<div>' + innerHTML
				+ '</div>';
	}

	//关闭连接
	function closeWebSocket() {
		websocket.close();
	}

	//发送消息
	function send() {
		var message = document.getElementById('text').value;
		websocket.send(message);
		document.getElementById('text').value = '';
	}

	//清屏
	function clear11() {
		document.getElementById('message').innerHTML = '';
	}

	function borderColor() {
		if (self['text'].style.borderColor == 'pink') {
			self['text'].style.borderColor = 'yellow';
		} else {
			self['text'].style.borderColor = 'pink';
		}
		oTime = setTimeout('borderColor()', 400);
	}
</script>
</html>
