<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String pushStatus = "";
	Object pushStatusObj = request.getAttribute("pushStatus");

	if (pushStatusObj != null) {
		pushStatus = pushStatusObj.toString();
	}
%>
<head>
<title>Google Cloud Messaging (GCM) Server in PHP</title>
</head>
<body>

	<h1>Google Cloud Messaging (GCM) Server in Java</h1>

	<form action="GCMNotification" method="post">

		<div>
			<textarea rows="2" name="note" cols="23"
				placeholder="Message to transmit via GCM"></textarea>
		</div>
		<div>
			<input type="submit" value="Send Push Notification via GCM" />
		</div>
	</form>
	
	<a href="onstartupthread">Link to sendmessage</a>
	<p>
		<h3>
			<%=pushStatus%>
		</h3>
	</p>
</body>
</html>
