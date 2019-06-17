<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>상세페이지</title>
</head>
<body>

<div>
	<h1>${name}</h1>
	- 프로젝트 이름 : ${name}<br>
	- 프로젝트 ID : ${id}<br>
	- 활성화됨 : <c:choose>
						<c:when test="${enabled eq true}">
								예
						</c:when>
						<c:otherwise>
								아니요
						</c:otherwise>
					</c:choose><br>
	- 설명 : ${description}<br>
</div>
</body>
</html>