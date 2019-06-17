<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>목록 페이지</title>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
 
$( document ).ready(function() {

	var listTotal = document.getElementById("searchContent").getElementsByTagName("tr").length;
 	var totalList = listTotal-1;
 	$("#total").text(totalList+" 항목 표시"); 
	
});

function restApiList(projectId){


	 var restApiPojectId = projectId;
	 alert("프로젝트ID::::"+restApiPojectId);
	 
	 var url= "http://localhost:85/list/"+restApiPojectId+"/detail";    //팝업창 페이지 URL
	 var winWidth = 700;
	 var winHeight = 600;
	 var popupOption= "width="+winWidth+", height="+winHeight;    //팝업창 옵션(optoin)
	 window.open(url,"",popupOption);

 }


</script>
</head>
<body>
<h1>※ REST API를 활용한 오픈 스택 목록 뿌리기</h1>
<br>
<h1>프로젝트</h1>
	
	<table id="searchContent" border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>설명</th>
				<th>프로젝트ID</th>
				<th>도메인이름</th>
				<th>활성화됨</th>
			</tr>
		</thead>
		<tbody>		
					<c:forEach var="restApiVo" items="${arrRestList }"  varStatus="status" >
						<p id="total"></p>
							  <tr>
									<td>${status.count} </td>
									<td><a href="#" id="restApiName${status.count}"  onclick="return restApiList('${restApiVo.id}');">${restApiVo.name }</a></td>
									<td>${restApiVo.description }</td>
									<td>${restApiVo.id }</td>
									<td>${restApiVo.domain_id }</td>
									
									<c:choose>
										<c:when test="${restApiVo.enabled eq true}" >
												<td>예</td>
										</c:when>
										<c:otherwise>
												<td>아니요</td>
										</c:otherwise>
									</c:choose>
							</tr>			
					</c:forEach>			
		</tbody>					
	</table>
</body>
</html>