<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <div id="wrap">
    	<h2>게시글 리스트</h2>
    	<table>
    		<tr>
    			<td colspan="5" style="border:white;text-align:right;"><a href="BoardServlet?command=board_write_form">글 작성하기</a></td>
    		</tr>
    		<tr>
    			<th>글제목</th>
    			<th>작성자</th>
    			<th>글내용</th>
    		</tr>
			<c:forEach var="list" items="${list }">
				<tr>
					<td>${item.title }</td>
					<td>${item.writer }</td>
					<td>${item.content }</td>
				</tr>
			</c:forEach>
    	</table>
    </div>
    <script type="text/javascript">

    	$.ajax({
    		type:"get",
    		url:"./boards",
    		dataType:"json",
    		success:function(data){

    			var s="";
    				s+="<table class='table table-info' style='width:800px;'>";
    				s+="<tr align='center' style='background-color: skyblue;'><th>번호</th><th>작성자</th><th>제목</th><th>내용</th></tr>";
    				let list = data.datas;
    				$(list).each(function(i,item){
    				s+="<tr>"
    				s+="<td align='center'>"+(i+1)+"</td>";
    				s+="<td>"+item.title+"</td>";
    				s+="<td>"+item.writer+"</td>";
    				s+="<td>"+item.content+"</td>";
    				s+="</tr>";
    			});
    				s+="</table>";
    			$("#board").html(s)
    		}
    	});

    </script>

</body>
</html>