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
<form action="/board/edit" method="post">
    <input type="hidden" value="${boardDTO.bno}" name="bno">
    <div>
        <label>title</label>
        <input type="text" name="title" value="${boardDTO.title}">
    </div>
    <div>
        <label>content</label>
        <textarea name="content">${boardDTO.content}</textarea>
    </div>
    <div>
        <label>writer</label>
        <input type="text" name="writer" value="${boardDTO.writer}">
    </div>
    <div>
        <input type="submit" value="수정">
        <input type="button" onclick="location.href='/board/view?bno=${boardDTO.bno}'" value="목록">
    </div>

</form>
</body>
</html>