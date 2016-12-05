<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<!-- 자동완성 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/typeahead.js/0.11.1/typeahead.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/corejs-typeahead/0.11.1/bloodhound.js"></script>

<script>	 
$(document).ready(function(){
	var first = 0;
	var second = 0;
	/************* 기업검색 자동완성 *******************/ 
	var states = new Bloodhound({
		  datumTokenizer: Bloodhound.tokenizers.whitespace,
		  queryTokenizer: Bloodhound.tokenizers.whitespace,
		  // `states`는 The Basics으로 정의된 company 배열의 이름
		  local: states
		});
	var substringMatcher = function(strs) {
		  return function findMatches(q, cb) {
		    var matches, substringRegex;
		    matches = [];
		    // q가 문자열에 있는지 확인 
		    substrRegex = new RegExp(q, 'i');

		   // q를 포함한 문자열을 통해, matches의 배열에 추가
		    $.each(strs, function(i, str) {
		      if (substrRegex.test(str)) {
		        matches.push(str);
		      }
		    });
		    cb(matches);
		  };
		};
		
	//db에서 가져온 기업명 리스트
	var list = new Array();
	<c:forEach items="${companyList}" var="item">
	list.push("${item}");
	</c:forEach>
	var states =  list; 

	$('#the-basics .typeahead').typeahead({
		hint: true,
		highlight: true,
		minLength: 1
	}, {
	  name: 'states',
	  source: substringMatcher(states)
	}).on('typeahead:open', function() {
	}).on('typeahead:rendered', function(element, data) { // 검색 결과 화면 생성 이벤트
	}).on('typeahead:cursorchanged', function(element, data) { // 위 아래 커서 이동시 이벤트
	}).on('typeahead:selected', function(element, data) { // 선택 이벤트
	}).on('typeahead:autocompleted', function(element, data) { // 검색어 자동완성 이벤트
	});

	/************ 직무 대분류 - 중분류 *********** */

	$(document).on("change",".jobTopIndexCd",function(){ 
		var num = $('.jobTopIndexCd').index(this);
		$('.jobMidIndexCd').eq(num).empty();
		<c:forEach items="${jobMidIndex}" var ="jobMid">
			if($('.jobTopIndexCd').eq(num).val()=="${jobMid.jobTopIndexCd}"){
				$('.jobMidIndexCd').eq(num).append('<option value="${jobMid.jobMidIndexCd}">${jobMid.jobMidIndexName}</option>');
			}
		</c:forEach>	
	});
	
	/************ 직무추가 버튼 클릭  ************/
	$('#jobAddBtn').click(function(){
		var flag = false;
		 $('.recruitJobJobdetail').each(function(index, item){
            if($(this).val() == ''){
                flag = true;    
        	} 
		 }); 
		 $('.jobMidIndexCd').each(function(index, item){
            if($(this).val() == ''){
                flag = true;    
       		 } 
		 }); 
		 $('.cCletterArticle').each(function(index, item){
            if($(this).val() == ''){
            	flag = true;    
       		 } 
		 });
		 if(flag){
			$('#jobAddError').text('상세직무 모두입력후 추가가능합니다.');
		}else{
			second = 0;
			first++;
			
			var jobDetail = 
			'<label>각 직무별 채용입력</label>'+
			'<div id="job">'+
				'<table class="table table-striped">'+
							'<tr>'+
								'<td class="col-sm-3">직무선택</td>'+
								'<td class="col-sm-3">'+
									'<select name="jobTopIndexCd" class="jobTopIndexCd form-control">'+
										'<option value="">대분류</option>'+
									'	<c:forEach var="jobTopIndex" items="${jobTopIndex}">'+
									'		<option value="${jobTopIndex.jobTopIndexCd}">${jobTopIndex.jobTopIndexName}</option>'+
									'	</c:forEach>'+
									'</select>'+ 
								'</td>'+
								'<td class="col-sm-3">'+
									'<select name="recruitList['+first+'].jobMidIndexCd" class="jobMidIndexCd form-control">'+
										'<option value="">소분류</option>'+
									'</select>'+
								'</td>'+
							'<tr>'+
							'<tr>'+
								'<td class="col-sm-3">채용형태</td>'+
								'<td class="col-sm-3" colspan="2">'+
									'<select name="recruitList['+first+'].recruitJobWorkstatus"'+
									'class="recruitJobWorkstatus form-control">'+
									'<option value="신입">신입</option>'+
									'<option value="경력">경력</option>'+
									'<option value="인턴">인턴</option>'+
									'<option value="계약직">계약직</option>'+
								'</select>'+
								'</td>'+
							'<tr>'+
							'<tr>'+
								'<td class="col-sm-3">채용상세직무</td>'+
								'<td class="col-sm-3" colspan="2">'+
									'<input type="text" name="recruitList['+first+'].recruitJobJobdetail"'+
									'class="recruitJobJobdetail form-control" placeholder="채용상세직무 입력">'+
								'</td>'+
							'</tr>'+
							'<tr>'+
								'<td class="col-sm-3">학력</td>'+
								'<td class="col-sm-3" colspan="2">'+
									'<select name="recruitList['+first+'].recruitJobEducation" class="recruitJobEducation form-control">'+
										'<option value="학력무관">학력무관</option>'+
										'<option value="고졸">고졸</option>'+
										'<option value="전문대졸">전문대졸</option>'+
										'<option value="4년대졸">4년대졸</option>'+
									'</select>'+
								'</td>'+
							'</tr>'+
						'</table>'+
						'<div id="coverletter" class="form-group">'+
							'<div>'+
								'<table class="table table-striped">'+
									'<tr>'+
										'<td class="col-sm-3" colspan="3">'+
											'자기소개서 항목'+
											'<span id="coverletterWarn" style="color: gray"></span>'+
											'<span style="float: right;">'+
												'<input type="button"  class="coverletterAddBtn btn btn-primary" value="항목추가">'+
												'<input type="button" class="coverletterDelBtn btn btn-defualt" value="항목삭제">'+
											'</span>'+
										'</td>'+
									'</tr>'+
								'</table>'+
							'</div>'+
							'<div>'+
								'<table class="table table-striped">'+	
									'<tr>'+
										'<td class="col-sm-3" colspan="3">'+
											'<textarea class="cCletterArticle form-control" rows="2" name="recruitList['+first+'].cCletterArticle['+second+'].cCletterArticle" placeholder="자기소개서 항목 입력"></textarea>'+
										'</td>'+
									'</tr>'+
								'</table>'+
								'<div class="fieldCoverletter"></div>'+
							'</div>'+
						'</div>'+
					'</div>'+
				'</div>'
			'</div>'+
			'<br>';
			
			$('#jobDetail').append(jobDetail);
		}
	});
	
	/***********  직무 삭제 버튼***********/ 
	$('#jobRemoveBtn').click(function(){
		 $('#job').last().remove();
	});
	
	/*********** 자기소개서 항목 ***********/ 
	//항목 추가
	$(document).on('click','.coverletterAddBtn',function(){
		second++;
		var index = $('.coverletterAddBtn').index(this);
		var check = false;
		 $('.cCletterArticle').each(function(index, item){
	            if($(this).val() == ''){
	            	check = true;    
	       		 } 
			 }); 
		 if(check==true){
			 $('#coverletterWarn').text(' * 모든항목 입력후 추가할수있습니다.');
		 }else{
			console.log(second);
			$('.fieldCoverletter').eq(index).append(
					'<table class="table table-striped cCletterArticleTable">'
						+'<tr>'
							+'<td class="col-sm-3" colspan="3">'
								+'<textarea class="cCletterArticle form-control" rows="2" name="recruitList['+first+'].cCletterArticle['+second+'].cCletterArticle" placeholder="자기소개서 항목을 입력해주세요"></textarea>'
							+'</td>'
						+'</tr>'
					+'</table>');
		 }
	});
	//항목삭제
	$(document).on('click','.coverletterDelBtn',function(){
		//console.log(second);
		var index = $('.coverletterDelBtn').index(this);
		 //$('.cCletterArticle').eq(index).remove(); 
		 $('.cCletterArticleTable').last().remove(); 
	});
		
	/************ 등록버튼 클릭시 유효성검사 ************/
 	$('#recruitAdd').click(function(){	
		console.log($('.cCletterArticle').eq(3).val());
		
		if($('#companyName').val()==''){
			$('#error').text('기업명을 입력하세요');
			$('#companyName').focus();	
		}else if($('#recruitName').val()==''){
			$('#error').text('공고명을 입력하세요');
			$('#recruitName').focus();
		}else if($('#recruitBegindate').val()==''){
			$('#error').text('채용시작일을 입력하세요');
			$('#recruitBegindate').focus();
		}else if($('#recruitEnddate').val()==''){
			$('#error').text('채용종료일을 입력하세요');
			$('#recruitEnddate').focus();
		}else if($('#recruitJobFile').val()==''){
			$('#error').text('파일을 선택하세요');
			$('#recruitJobFile').focus();
		}else{
			var flag = false;
			 $('.recruitJobJobdetail').each(function(index, item){
	            if($(this).val() == ''){
	                flag = true;    
	        	} 
			 }); 
			 $('.jobMidIndexCd').each(function(index, item){
	            if($(this).val() == ''){
	                flag = true;    
	       		 } 
			 }); 
			 $('.cCletterArticle').each(function(index, item){
		            if($(this).val() == ''){
		                flag = true;    
		       		 } 
				 }); 
			 if(flag){
				$('#error').text('상세직무 모두입력후 추가가능합니다.');
			}else{
				$('#recruitInsertForm').submit();
			}
		}
		
	}); 
});
</script>
<title>채용수정</title>
</head>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/common/module/modHeader.jsp" />
<body>
	<div class="container">
		<div class="jumbotron">
			<h2>채용 정보 수정</h2>
			<p></p>
		</div>
		<form id="recruitInsertForm" action="/recruitInsert"  method="post" enctype="multipart/form-data">
			<h3>기업 채용정보 </h3>
			<div class="form-group" id="the-basics">
				<table class="table table-striped">
					<tr>
						<th class="col-sm-3">
							채용기업<br>
							<span style="color: gray">검색결과에 없는 기업이면 임시등록됩니다</span>
						</th>
						<th class="col-sm-3">채용공고명</th>
						<th class="col-sm-3">채용시작일</th>
						<th class="col-sm-3">채용마감일</th>
					</tr>
					<tr>
						<td class="col-sm-3">
							<input class="typeahead form-control" type="text" name="companyName" id="companyName" placeholder="기업명 입력"
							value="${updateList.recruitList[0].companyName}">
						</td>
						<td class="col-sm-3">
							<input type="text" name="recruitName" id="recruitName"
							class="form-control" value="${updateList.recruitList[0].recruitName}" placeholder="채용공고명 입력">
						</td>
						<td class="col-sm-3">
							<input type="date" name="recruitBegindate" id="recruitBegindate" class="form-control" value="${updateList.recruitList[0].recruitBegindate.substring(0,10)}">
						</td>
						<td class="col-sm-3">
							<input type="date" name="recruitEnddate" id="recruitEnddate" class="form-control" value="${updateList.recruitList[0].recruitEnddate.substring(0,10)}">
						</td>
					</tr>
					<tr>
						<td class="col-sm-3">
							공고문(사진파일 1장)
						</td>
						<td class="col-sm-3" colspan="3" >
				    			<input type="file" name="recruitJobFile" id="recruitJobFile" >
				    			<span>${updateList.recruitList[0].recruitImgName}</span>
				    			<!-- value= "C:\Users\202-10\git\topteam_pickme\pickme\src\main\webapp\upload\recruitimg\"+"${updateList.recruitList[0].recruitImgName}"> -->
				    			<!-- value= "C:/Users/202-10/git/topteam_pickme/pickme/src/main/webapp/upload/recruitimg/Tulips_1479187582197.jpg${updateList.recruitList[0].recruitImgName}"> -->
				    			<!-- value= "C:\Users\202-10\git\topteam_pickme\pickme\src\main\webapp\upload\recruitimg\Tulips_1479187582197.jpg"> -->
				    			
				    			<span style="color: red">${errorMsg}</span>
  							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<h3>직무별 채용</h3>
					
					
					<c:forEach items="${updateList.jobVo}" var="jobList" >
					
					<label>각 직무별 채용입력</label>
					<table class="table table-striped">
						<tr>
							<td class="col-sm-3">직무선택</td>
							<td class="col-sm-3">
								<select name="jobTopIndexCd" class="jobTopIndexCd form-control">
									<c:forEach var="jobTopIndex" items="${jobTopIndex}">
										<option value="${jobTopIndex.jobTopIndexCd}">${jobTopIndex.jobTopIndexName}</option>
									</c:forEach>
								</select> 
							</td>
							<td class="col-sm-3">
								<select name="recruitList[0].jobMidIndexCd" class="jobMidIndexCd form-control">
										<c:if test="${jobList.jobMidindexCd==jobList.jobMidindexCd}">
										<option value="${jobList.jobMidindexCd}" selected="selected">소분류</option>
										</c:if>
										<c:if test="${jobList.jobMidindexCd!=jobList.jobMidindexCd}">
										<option value="${jobList.jobMidindexCd}" >소분류</option>
										</c:if>
								</select>
							</td>
						<tr>
						<tr>
							<td class="col-sm-3">채용형태</td>
							<td class="col-sm-3" colspan="2">
								<select name="recruitList[0].recruitJobWorkstatus"
								class="recruitJobWorkstatus form-control">
								<option  value="신입">신입</option>
								<option value="경력">경력</option>
								<option value="인턴">인턴</option>
								<option value="계약직">계약직</option>
							</select>
							</td>
						<tr>
						<tr>
							<td class="col-sm-3">채용상세직무</td>
							<td class="col-sm-3" colspan="2">
								<input type="text" name="recruitList[0].recruitJobJobdetail"
								class="recruitJobJobdetail form-control" placeholder="채용상세직무 입력" value="${jobList.recruitJobJobdetail}">
							</td>
						</tr>
						<tr>
							<td class="col-sm-3">학력</td>
							<td class="col-sm-3" colspan="2">
								<select name="recruitList[0].recruitJobEducation" class="recruitJobEducation form-control">
									<option value="학력무관">학력무관</option>
									<option value="고졸">고졸</option>
									<option value="전문대졸">전문대졸</option>
									<option value="4년대졸">4년대졸</option>
								</select>
							</td>
						</tr>
					</table>
					<div id="coverletter" class="form-group">
						<div>
							<table class="table table-striped">
								<tr>
									<td class="col-sm-3" colspan="3">
										자기소개서 항목
										<span id="coverletterWarn" style="color: gray"></span>
										<span style="float: right;">
											<input type="button"  class="coverletterAddBtn btn btn-primary" value="항목추가">
											<input type="button" class="coverletterDelBtn btn btn-defualt" value="항목삭제">
										</span>
									</td>
								</tr>
							</table>
						</div>
						<div>
						<c:forEach items="${updateList.recruitList}" var="letter">
							<c:if test="${letter.cCletterRecruitJobCd==updateList.recruitJobCd}">
								<td class="col-sm-3" colspan="3">
									<textarea class="cCletterArticle form-control" rows="2" name="recruitList[0].cCletterArticle[0].cCletterArticle" placeholder="자기소개서 항목 입력">
									${letter.cCletterArticles}
									</textarea>
								</td>
							</c:if>
							<table class="table table-striped">	
								<tr>
									<td class="col-sm-3" colspan="3">
										<textarea class="cCletterArticle form-control" rows="2" name="recruitList[0].cCletterArticle[0].cCletterArticle" placeholder="자기소개서 항목 입력">
										${updateList.recruitJobJobdetail}
										</textarea>
									</td>
								</tr>
							</table>
						</c:forEach>
							<div class="fieldCoverletter"></div>
						</div>
					</div>
				</div>
			</div>
			<br>
			</c:forEach>
			
			
			<div id="jobDetail"></div>
			<div class="form-group">
				<input type="button"  class="coverletterAddBtn btn btn-primary" id="jobAddBtn" value="직무추가"> 
				<input type="button" class="coverletterAddBtn btn btn-primary" id="jobRemoveBtn" value="직무삭제">
				<span id="jobAddError" style="color: red"></span>
			</div>

			<div class="form-group">
				<input type="button" id="recruitAdd" class="btn btn-default" value="등록"> 
				<span id="error" style="color: red"></span>
			</div>
		</form>
	</div>
</body>
</html>