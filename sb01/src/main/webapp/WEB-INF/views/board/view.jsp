<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
<h2>${error}</h2>
    <input type="hidden" value="${boardDTO.bno}" id="bno">
<table>
    <tr>
        <td>bno</td>
        <td>content</td>
        <td>title</td>
        <td>writer</td>
        <td>visitcount</td>
        <td>postdate</td>
    </tr>
    <tr>
        <td>${boardDTO.bno}</td>
        <td>${boardDTO.content}</td>
        <td>${boardDTO.title}</td>
        <td>${boardDTO.writer}</td>
        <td>${boardDTO.visitcount}</td>
        <td><fmt:formatDate value="${boardDTO.postdate}"/></td>
    </tr>
    <tr>
        <td colspan="6" align="right"><button type="button" onclick="location.href='/board/edit?bno=${boardDTO.bno}'">수정</button>
        <button type="button" onclick="location.href='/board/remove?bno=${boardDTO.bno}'">삭제</button>
        <button type="button" onclick="location.href='/board/list'">목록</button></td>
    </tr>
</table>
<div class="container mt-5">
    <div class = "form-group">
        <label for="reply">Reply:</label>
        <textarea class="form-control" name="reply" rows="3" id="reply"></textarea>
    </div>
    <div class = "form-group">
        <label for="replyer">Replyer:</label>
        <input type="text" class="form-control" name="replyer" value="${memberDTO.userName}" id="replyer" readonly>
    </div>
    <button type="button" class="btn btn-primary" id="replyBtn">댓글추가</button>
</div>
<div id="replyResult"></div>
<script>
    var init=function (){
        var bno = $("#bno").val();
        console.log(bno)
        $.ajax({
            type : "get",
            url : "/reply/list/"+bno,
            dataType : "json"
        }).done(function (resp){
            str="<table>"
            $.each(resp,function (key,val){
                str+="<tr>"
                str+="<td>"+val.reply+"</td>"
                str+="<td>"+val.replyer+"</td>"
                str+="<td>"+val.postdate+"</td>"
                str+="</tr>"
                <%--if (val.replyer ==${userID}) {--%>
                <%--    str+="<td><a href='javascript:rdel("+val.rno+")'>삭제</a></td>";--%>
                <%--}--%>
            })
            str+="</table>"
            $("#replyResult").html()
            $("#replyResult").html(str)
        })
    }
    init();
    $("#replyBtn").click(function (){
        var data = {"bno":$("#bno").val(),
        "reply":$("#reply").val(),
        "replyer":$("#replyer").val()}
        $.ajax({
            url : "/reply/new",
            data : JSON.stringify(data),
            type : "post",
            contentType:"application/json;charset=utf-8",
        }).done(function (resp){
            alert(resp)
            init()
        }).fail(function (){
            alert("실패")
        })
    })
</script>
</body>
</html>