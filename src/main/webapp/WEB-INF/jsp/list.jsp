<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��� ������</title>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
 
$( document ).ready(function() {

	var listTotal = document.getElementById("searchContent").getElementsByTagName("tr").length;
 	var totalList = listTotal-1;
 	$("#total").text(totalList+" �׸� ǥ��"); 
	
});

function restApiList(projectId){


	 var restApiPojectId = projectId;
	 alert("������ƮID::::"+restApiPojectId);
	 
	 var url= "http://localhost:85/list/"+restApiPojectId+"/detail";    //�˾�â ������ URL
	 var winWidth = 700;
	 var winHeight = 600;
	 var popupOption= "width="+winWidth+", height="+winHeight;    //�˾�â �ɼ�(optoin)
	 window.open(url,"",popupOption);

 }


</script>
</head>
<body>
<h1>�� REST API�� Ȱ���� ���� ���� ��� �Ѹ���</h1>
<br>
<h1>������Ʈ</h1>
	
	<table id="searchContent" border="1">
		<thead>
			<tr>
				<th>��ȣ</th>
				<th>�̸�</th>
				<th>����</th>
				<th>������ƮID</th>
				<th>�������̸�</th>
				<th>Ȱ��ȭ��</th>
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
												<td>��</td>
										</c:when>
										<c:otherwise>
												<td>�ƴϿ�</td>
										</c:otherwise>
									</c:choose>
							</tr>			
					</c:forEach>			
		</tbody>					
	</table>
</body>
</html>