<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��������</title>
</head>
<body>

<div>
	<h1>${name}</h1>
	- ������Ʈ �̸� : ${name}<br>
	- ������Ʈ ID : ${id}<br>
	- Ȱ��ȭ�� : <c:choose>
						<c:when test="${enabled eq true}">
								��
						</c:when>
						<c:otherwise>
								�ƴϿ�
						</c:otherwise>
					</c:choose><br>
	- ���� : ${description}<br>
</div>
</body>
</html>