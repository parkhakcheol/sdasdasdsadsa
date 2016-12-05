<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8"/>
<title>면접후기 관리</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">	
<link rel="stylesheet" href="/css/company/companyCommon.css">
<link rel="stylesheet" href="/css/company/companyInterview.css">
<link rel="stylesheet" href="/css/company/companyinfo.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/admin/module/adminHeader.jsp"/>
	<section id="main" class="column">
		<div class="container">
			<div class="jumbotron text-center">
				<h1>면접후기 미승인 리스트</h1>
			</div>
			<div class="table-responsive">
				<table class="table table-bordered tablesorter">
					<thead>
						<tr>
							<th>번호</th>
							<th>기업이름</th>
							<th>작성자</th>
							<th>면접후기 간략</th>
							<th>등록일</th>
							<th>승인상태</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="companyInterviewList" items="${interviewUnreceivedMap.interviewListUnreceived}" varStatus="i">
							<tr class="tablehover" onClick="location.href='/interview/companyInterviewUnreceivedDetail?interviewCd=${companyInterviewList.interviewCd}'">
								<c:if test="${page > 1}">
									<td>${i.count + (page-1) * 5}</td>
								</c:if>
								<c:if test="${page <= 1}">
									<td>${i.count}</td>
								</c:if>
								<td>${companyInterviewList.companyName }</td>
								<td>${companyInterviewList.loginId }</td>
								<td>${fn:substring(companyInterviewList.interviewSummary, 0, 20) }...</td>
								<td>${companyInterviewList.interviewRegDate }</td>
								<td>미승인</td>
							</tr>				
						</c:forEach>
					</tbody>
				</table>	
				<!-- 페이징 -->
				<div class="text-center">
					<ul class="pager">
						<li class="previous"><a href="/interview/companyInterviewUnreceivedList?page=${page-1}">이전</a><li>
						<c:forEach var="i" begin="${interviewUnreceivedMap.startPage }" end="${interviewUnreceivedMap.endPage }">
							<c:if test="${page == i}">
								<li class="active"><a href="/interview/companyInterviewUnreceivedList?page=${i }">${i }</a><li>
							</c:if>
							<c:if test="${page != i}">
								<li><a href="/interview/companyInterviewUnreceivedList?page=${i }">${i }</a></li>
							</c:if>
						</c:forEach>
						<li class="next"><a href="/interview/companyInterviewUnreceivedList?page=${page+1}">다음</a></li>
					</ul>
				</div>
			</div>
		</div>
	</section>
</body>
</html>