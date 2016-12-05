<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>나의 정보</title>
<!-- Bootstrap Core CSS -->
<link href="resumecoverlettersetting/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- MetisMenu CSS -->
<link href="resumecoverlettersetting/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="resumecoverlettersetting/dist/css/sb-admin-2.css" rel="stylesheet">
<!-- Morris Charts CSS -->
<link href="resumecoverlettersetting/vendor/morrisjs/morris.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="resumecoverlettersetting/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<!-- jQuery -->
<script src="resumecoverlettersetting/vendor/jquery/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="resumecoverlettersetting/vendor/bootstrap/js/bootstrap.min.js"></script>
<!-- Metis Menu Plugin JavaScript -->
<script src="resumecoverlettersetting/vendor/metisMenu/metisMenu.min.js"></script>
<!-- Custom Theme JavaScript -->
<script src="resumecoverlettersetting/dist/js/sb-admin-2.js"></script>
<script>
	$(document).ready(function(){	
		
		$('#myRecruitBookmarkDetail').click(function(){
			var d = new Date();
			var ddayYear = d.getFullYear(); 
			var ddayMonth = d.getMonth();
			console.log(ddayYear);
			console.log(ddayMonth);
			location.href="/diary?ddayYear="+ddayYear+"&ddayMonth="+ddayMonth+"&ddayOption=search&bookmark=true";
		});
	});
</script>
</head>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/resumecoverletter/module/modHeader.jsp"/>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/resumecoverletter/module/modSideCommon.jsp"/>
<body>
	<div id="wrapper">
		<div id="page-wrapper">
	    		<div class="row">
	            	<div class="col-lg-10">
	                    <h2 class="page-header">${sessionScope.generalNick}님의 INFO DESK</h2>
						<div class="jumbotron" style="background-image: url('img/coverletter/resume_coverletter1.jpg'); background-size: 100%;">
							<h1 align="right" style="color: white;">나의 이력서와 자소서 </h1>
						</div>
	                </div>
	                <!-- /.col-lg-12 -->
	            </div>
	            <!-- /.row -->
	            <div class="row">
	                <div class="col-lg-2 col-md-6">
	                    <div class="panel panel-primary">
	                        <div class="panel-heading">
	                            <div class="row">
	                                <div class="col-xs-3">
	                                    <i class="fa fa-pencil fa-5x"></i>
	                                </div>
	                                <div class="col-xs-9 text-right">
	                                    <div class="huge">${myCoverletterListCount.myCoverletterCount}</div>
	                                    <div>나의 자소서</div>
	                                </div>
	                            </div>
	                        </div>
	                        <a href="/memberCoverletterList">
	                            <div class="panel-footer">
	                                <span class="pull-left">상세보기</span>
	                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
	                                <div class="clearfix"></div>
	                            </div>
	                        </a>
	                    </div>
	                </div>
	                <div class="col-lg-2 col-md-6">
	                    <div class="panel panel-green">
	                        <div class="panel-heading">
	                            <div class="row">
	                                <div class="col-xs-3">
	                                    <i class="fa fa-list-alt fa-5x"></i>
	                                </div>
	                                <div class="col-xs-9 text-right">
	                                    <div class="huge">${myResumeListCount.myResumeCount}</div>
	                                    <div>나의 이력서</div>
	                                </div>
	                            </div>
	                        </div>
	                        <a href="/resumeList">
	                            <div class="panel-footer">
	                                <span class="pull-left"><strong>상세보기</strong></span>
	                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
	                                <div class="clearfix"></div>
	                            </div>
	                        </a>
	                    </div>
	                </div>
	                <div class="col-lg-2 col-md-6">
	                    <div class="panel panel-yellow">
	                        <div class="panel-heading">
	                            <div class="row">
	                                <div class="col-xs-3">
	                                    <i class="fa fa-bookmark-o fa-5x"></i>
	                                </div>
	                                <div class="col-xs-9 text-right">
	                                    <div class="huge">${myRecruitListCount.myRecruitBookmarkCount}</div>
	                                    <div>나의 관심 채용</div>
	                                </div>
	                            </div>
	                        </div>
	                        <a href="javascript:void(0);">
	                            <div class="panel-footer" id="myRecruitBookmarkDetail">
	                                <span class="pull-left"><strong>상세보기</strong></span>
	                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
	                                <div class="clearfix"></div>
	                            </div>
	                        </a>
	                    </div>
	                </div>
	                <div class="col-lg-2 col-md-6">
	                    <div class="panel panel-red">
	                        <div class="panel-heading">
	                            <div class="row">
	                                <div class="col-xs-3">
	                                    <i class="fa fa-comments fa-5x"></i>
	                                </div>
	                                <div class="col-xs-9 text-right">
	                                    <div class="huge">${myFreeboardWriteCount.myFreeboardWriteCount}</div>
	                                    <div>나의 게시글</div>
	                                </div>
	                            </div>
	                        </div>
	                        <a href="#">
	                            <div class="panel-footer">
	                                <span class="pull-left"><strong>상세보기</strong></span>
	                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
	                                <div class="clearfix"></div>
	                            </div>
	                        </a>
	                    </div>
	                </div>
	                <div class="col-lg-2 col-md-6">
	                    <div class="panel panel-red">
	                        <div class="panel-heading">
	                            <div class="row">
	                                <div class="col-xs-3">
	                                    <i class="fa fa-comments fa-5x"></i>
	                                </div>
	                                <div class="col-xs-9 text-right">
	                                    <div class="huge">${myFreeboardBookmarkCount.myFreeboardBookmarkCount}</div>
	                                    <div>나의 관심글</div>
	                                </div>
	                            </div>
	                        </div>
	                        <a href="#">
	                            <div class="panel-footer">
	                                <span class="pull-left"><strong>상세보기</strong></span>
	                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
	                                <div class="clearfix"></div>
	                            </div>
	                        </a>
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	            	<div class="row">
		                <div class="col-sm-5">
		                    <div class="panel panel-primary">
		                        <div class="panel-heading">
		                            <i class="fa fa-bell fa-fw"></i> <strong>전체 채용 마감 임박</strong>
		                        </div>
		                        <!-- /.panel-heading -->
		                        <div class="panel-body">
		                        	<div>
		                            <table class="table table-striped table-bordered table-hover table-condensed" >
			                        	<thead>
			                                <tr>
			                                    <th class="small">번호</th>
												<th class="small">마감일</th>
												<th class="small">채용명</th>
												<th class="small">상세직무</th>
											</tr>
			                            </thead>
			                            <tbody>
			                               	<c:forEach var="companyJobCoverletterListForInfo" items="${companyJobCoverletterListForInfo}" varStatus="i">
											<tr>
												<td class="small">${i.count}</td>
												<td class="small">${companyJobCoverletterListForInfo.recruitEnddate}</td>
												<td class="small"><a href="/memberCoverletterInsert?recruitJobCd=${companyJobCoverletterListForInfo.recruitJobCd}">${companyJobCoverletterListForInfo.recruitName}</a></td>
												<td class="small">${companyJobCoverletterListForInfo.recruitJobJobdetail}</td>
											</tr>
											</c:forEach>
										</tbody>
									</table>
									</div>
		                            
		                            <a href="/companyJobCoverletterList" class="btn btn-primary btn-block">모든 채용 공고 보기</a>
		                        </div>
		                    </div>
		                </div>
		                <div class="col-sm-5">
		                    <div class="panel panel-yellow">
		                        <div class="panel-heading">
		                            <i class="fa fa-bell fa-fw"></i> <strong>나의 관심채용 마감 임박</strong>
		                        </div>
		                        <div class="panel-body">
		                        	<div>
		                            <table class="table table-striped table-bordered table-hover table-condensed" >
			                        	<thead>
			                                <tr>
			                                    <th class="small">번호</th>
												<th class="small">마감일</th>
												<th class="small">채용명</th>
												<th class="small">상세직무</th>
											</tr>
			                            </thead>
			                            <tbody>
			                               	<c:forEach var="myRecruitBookmarkListForInfo" items="${myRecruitBookmarkListForInfo}" varStatus="i">
											<tr>
												<td class="small">${i.count}</td>
												<td class="small">${myRecruitBookmarkListForInfo.recruitEnddate}</td>
												<td class="small"><a href="/memberCoverletterInsert?recruitJobCd=${myRecruitBookmarkListForInfo.recruitJobCd}">${myRecruitBookmarkListForInfo.recruitName}</a></td>
												<td class="small">${myRecruitBookmarkListForInfo.recruitJobJobdetail}</td>
											</tr>
											</c:forEach>
										</tbody>
									</table>
									</div>
		                            <a href="/companyJobCoverletterList" class="btn btn-warning btn-block">나의 관심 채용 전체 보기</a>
		                        </div>
		                    </div>
		                </div>
		            </div>
		            <div class="row">   
		                <div class="col-sm-5">
		                    <div class="panel panel-red">
		                        <div class="panel-heading">
		                            <i class="fa fa-bell fa-fw"></i> <strong>나의 게시물</strong>
		                        </div>
		                        <div class="panel-body">
		                        	<div>
		                            <table class="table table-striped table-bordered table-hover table-condensed" >
			                        	<thead>
			                                <tr>
			                                    <th class="small">번호</th>
												<th class="small">제목</th>
												<th class="small">등록일자</th>
											</tr>
			                            </thead>
			                            <tbody>
			                               	<c:forEach var="myFreeboardWriteListForInfo" items="${myFreeboardWriteListForInfo}" varStatus="i">
											<tr>
												<td class="small">${i.count}</td>
												<td class="small">${myFreeboardWriteListForInfo.freeboardTitle}</td>
												<td class="small">${myFreeboardWriteListForInfo.freeboardRegdate}</td>
											</tr>
											</c:forEach>
										</tbody>
									</table>
									</div>
		                            <a href="/companyJobCoverletterList" class="btn btn-danger btn-block">나의 게시물 전체 보기</a>
		                        </div>
		                    </div>
		            	</div>
		            	<div class="col-sm-5">
		                    <div class="panel panel-green">
		                        <div class="panel-heading">
		                            <i class="fa fa-bell fa-fw"></i> <strong>나의 관심글</strong>
		                        </div>
		                        <div class="panel-body">
		                        	<div>
		                            <table class="table table-striped table-bordered table-hover table-condensed" >
			                        	<thead>
			                                <tr>
			                                    <th class="small">번호</th>
												<th class="small">제목</th>
												<th class="small">글 등록일자</th>
												<th class="small">북마크 등록일자</th>
											</tr>
			                            </thead>
			                            <tbody>
			                               	<c:forEach var="myFreeboardBookmarkListForInfo" items="${myFreeboardBookmarkListForInfo}" varStatus="i">
											<tr>
												<td class="small">${i.count}</td>
												<td class="small">${myFreeboardBookmarkListForInfo.freeboardTitle}</td>
												<td class="small">${myFreeboardBookmarkListForInfo.freeboardRegdate}</td>
												<td class="small">${myFreeboardBookmarkListForInfo.freeboardBookmarkRegdate}</td>
											</tr>
											</c:forEach>
										</tbody>
									</table>
									</div>
		                            <a href=/freeboardList?bookmark=bookmark class="btn btn-success btn-block">나의 관심글 전체 보기</a>
		                        </div>
		                    </div>
		            	</div>
	           		</div>
	        	</div>
		</div>
	</div>	
</body>
</html>
