<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/common/module/modHeader.jsp"/>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/companyinfo/module/companyInfoHeader.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="/css/company/companyinfo.css">
<link rel="stylesheet" href="/css/company/companyCommon.css">
<link rel="stylesheet" href="/css/company/companyInterview.css">
<script>
	$(document).ready(function(){
		//면접경험, 면접레벨 값얻어와 해당 하는 태그에 css적용
		const $interviewExperience = $(".interviewExperience");
		const $interviewLeve = $(".interviewLevel");
		$interviewExperience.eq('${companyInterviewDetail.interviewExperience - 1}').addClass("active");
		$interviewLeve.eq('${companyInterviewDetail.interviewLevel - 1}').addClass("active");	
		
	});
</script>
<title>면접후기 상세보기(사용자)</title>
</head>
<body>
	<div class="container">
		<div class="panel form">
			<div class="panel-heading"><h3><strong>면접후기</strong></h3></div>
			<div class="panel-body interviewDetail">
				<div class="row">
					<div class="col-xs-2">
						<h4><label for="companyName">기업명 : </label></h4>
					</div>
					<div class="col-xs-3">
						<h4>${companyInterviewDetail.companyName}</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-2">
						<h4><label for="companyName">지원직무 : </label></h4>
					</div>
					<div class="col-xs-3">
						<h4>${companyInterviewDetail.jobTopIndexName}</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-2">
						<h4><label>면접경험 : </label></h4>
					</div>
					<div class="col-xs-5">
						<ol class="levelbar">
							<li class="interviewExperience" data-step="1">긍정</li>
						  	<li class="interviewExperience" data-step="2">보통</li>
						  	<li class="interviewExperience" data-step="3">부정적</li>
						</ol>
					</div>
				</div>
				<div class="row">
					<div class="form-grop">
						<div class="col-xs-8">
							<h4><label>면접/채용 과정요약 : </label></h4>
							<p><strong>${companyInterviewDetail.interviewSummary}</strong><p>
						</div>
					</div>
					<div class="form-grop">
						<div class="col-xs-8">
							<h4><label>면접질문 : </label></h4>
							<p><strong>${companyInterviewDetail.interviewSummary}</strong><p>
						</div>
					</div>
					<div class="form-grop">
						<div class="col-xs-8">
							<h4><label>면접답변 : </label></h4>
							<p><strong>${companyInterviewDetail.interviewSummary}</strong><p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-grop">
						<div class="col-xs-10">
							<h4><label>면접 난이도 :</label></h4>
							<ol class="levelbar">
								<li class="interviewLevel" data-step="1">매우 쉬움</li>
							  	<li class="interviewLevel" data-step="2">쉬움</li>
							  	<li class="interviewLevel" data-step="3">보통</li>
							  	<li class="interviewLevel" data-step="4">어려움</li>
							  	<li class="interviewLevel" data-step="5">매우어려움</li>
							</ol>
						</div>
					</div>
				</div>		
			</div>
		</div>
	</div>	
</body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/common/module/modFooter.jsp"/>
</html>