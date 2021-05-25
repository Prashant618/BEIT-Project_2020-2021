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

	

	<form action="Addbusinfo" method="post">
Bus Number
		<input type="text" name="BusNumber"  /><br></br>
		
		Bus Plate Number
		<input type="text" name="BusplateNumber"  /><br></br>
Source		
		<input type="text" name="Source"  /><br></br>
Destination	
		<input type="text" name="Destination"  /><br></br>
Start Timing		
		<input type="text" name="StartTiming"  /><br></br>
		
		DownTiming 		
		<input type="text" name="DownTiming"  /><br></br>
		<div>
			<input type="submit" value="Submit" />
		</div>
	</form>
	

	<p>
		<h3>
			<%=pushStatus%>
		</h3>
	</p>
</body>
</html>
