<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	演示原理:<br/>
	<form action="${pageContext.request.contextPath}/upload/file" method="post"
		enctype="multipart/form-data">
		描述：<input type="text" name="desc" /> <br /> 
		艳照：<input type="file" name="image" /> <br /> 
			<input type="submit" value="上传" />
	</form>
	文件上传简单实现:<br/>
	<form action="${pageContext.request.contextPath}/upload/file/easy" method="post"
		enctype="multipart/form-data">
		描述：<input type="text" name="desc" /> <br /> 
		艳照：<input type="file" name="image" /> <br /> 
			<input type="submit" value="上传" />
	</form>
</body>
</html>