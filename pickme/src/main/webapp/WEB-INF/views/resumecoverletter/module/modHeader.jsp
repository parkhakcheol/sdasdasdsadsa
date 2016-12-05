<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/style.css">
<script>
$(document).ready(function(){
	$btnLogout = $('.btn-logout');
	
	$btnLogout.click(function(){
		
		location.href = "/memberGeneralLogout";
	});
});
</script>
</head>
<body>
	<div class="container">
		<section class="mainmenu">
			<nav class="navbar navbar-inverse navbar-fixed-top">
  				<div class="container-fluid">
    				<div class="navbar-header">
	    				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
					        <span class="icon-bar"></span>
					        <span class="icon-bar"></span>
					        <span class="icon-bar"></span>
					    </button>
	   					<a class="navbar-brand" href="/"><img src="/img/main/pickme_logo_small.png" width="120px;"></a>
    					<c:if test="${sessionScope.generalLevel == 1}">
	   						<div class="adminPage">
    							<a href="/admin">관리자 PAGE</a>
    						</div>
   						</c:if>
    				</div>
    				<div class="collapse navbar-collapse" id="myNavbar">
	    				<ul class="nav navbar-nav navbar-right">
	    					<li><a href="/diary">채용공고</a></li>
	      					<li><a class="dropdown-toggle" data-toggle="dropdown" href="/resumeCoverletterIndex">이력서 & 자기소개서 <span class="caret"></span></a>
	      						<ul class="dropdown-menu">
	      							<li><a href="/resumeCoverletterInfo">나의 정보</a></li>
						            <li><a href="/resumeList">나의 이력서 리스트</a></li>
						            <li><a href="/resumeInsert">이력서 새로 쓰기</a></li>
						            <li><a href="/memberCoverletterInsert?recruitJobCd=recruit_company_job_0001">새 자소서 쓰기</a></li>
						            <li><a href="/memberCoverletterList">내가 쓴 자소서</a></li>
						            <li><a href="/companyJobCoverletterList">채용기업 리스트</a></li>
						        </ul>
						    </li>
						    <li><a href="/freeboardList">자유게시판</a></li>
	      					<li><a class="dropdown-toggle" data-toggle="dropdown" href="#">기업정보 <span class="caret"></span></a>
	      						<ul class="dropdown-menu">
	      							<li><a href="/company/companyInfoList">기업정보</a></li>
						            <li><a href="/review/companyReviewListAllow">기업리뷰</a></li>
						            <li><a href="/salary/companySalaryListAllow">연봉정보</a></li>
						            <li><a href="/interview/companyInterviewListAllow">면접후기</a></li>
						        </ul>
	      					</li>	
	      					<li class="hiddenNavWrap">
					<c:choose>
      			    	<c:when test="${empty sessionScope.generalId}">
      						<li><a href="/memberGeneralInsert">회원가입</a>
      						<li><a href="/memberGeneralLogin">로그인</a>
      					</c:when>	
     		 			<c:otherwise>
      						<li><a href="/general/memberGeneralUpdate?generalId=${sessionScope.generalId}">내 정보</a></li>
      						<li><a href="#" data-toggle="modal" data-target="#logoutModal">로그아웃</a></li>
      					</c:otherwise>   				
      				</c:choose>
	      				</ul>
  					</div>
  				</div>
			</nav>
		</section>
		<div class="modal fade" id="logoutModal" role="dialog">
			<div class="modal-dialog modal-sm">
	 			<div class="modal-content">
	   		 		<div class="modal-header">
	      				<button type="button" class="close" data-dismiss="modal">&times;</button>
	      				<h4 class="modal-title">로그아웃</h4>
	    			</div>
	   				<div class="modal-body">
	      				<p>${sessionScope.generalId }님 로그아웃 되었습니다.</p>
	    			</div>
	    			<div class="modal-footer">
	      				<button type="button" class="btn btn-logout" data-dismiss="modal">Close</button>
	    			</div>
	  			</div>
			</div>
		</div>
		<div class="modal fade" id="loginModal" role="dialog">
			<div class="modal-dialog modal-sm">
	 			<div class="modal-content">
	   		 		<div class="modal-header">
	      				<button type="button" class="close" data-dismiss="modal">&times;</button>
	      				<h4 class="modal-title">로그인</h4>
	    			</div>
	   				<div class="modal-body">
	      				<p>로그인이 필요한 정보입니다. 로그인 페이지로 이동합니다.</p>
	    			</div>
	    			<div class="modal-footer">
	      				<button type="button" class="btn btn-logout" data-dismiss="modal">확인</button>
	    			</div>
	  			</div>
			</div>
		</div>
	</div>
</body>
</html>