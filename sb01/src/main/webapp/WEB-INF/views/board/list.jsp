<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <table>
        <tr>
            <td>bno</td>
            <td>title</td>
            <td>writer</td>
            <td>visitcount</td>
            <td>postdate</td>
        </tr>
        <c:forEach var="dto" items="${boardList}">
            <tr>
                <td>${dto.bno}</td>
                <td><a href="/board/view?bno=${dto.bno}">${dto.title}</a></td>
                <td>${dto.writer}</td>
                <td>${dto.visitcount}</td>
                <td><fmt:formatDate value="${dto.postdate}"/></td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="5" align="right"><button type="button" onclick="location.href='/board/register'">글쓰기</button></td>
        </tr>
    </table>
</body>
</html>