<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h2>${error}</h2>
<form action="/board/register" method="post">
    <div>
        <label>title</label>
        <input type="text" name="title">
    </div>
    <div>
        <label>content</label>
        <textarea name="content"></textarea>
    </div>
    <div>
        <label>writer</label>
        <input type="text" name="writer">
    </div>
    <div>
        <input type="submit" value="전송">
        <input type="button" onclick="location.href='/board/list'" value="목록">
    </div>

</form>
</body>
</html>