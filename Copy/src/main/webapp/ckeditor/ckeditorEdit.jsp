<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%-- <%@ include file="/include/top.jsp" %> --%>
<jsp:include page="/include/top.jsp"  />

<style>
  table {width:900px;}  
  .td1 {
    height: 30px; /* td의 높이를 이미지의 높이에 맞게 조정 */    
  }  
  .td2 {
    height: 30px; /* td의 높이를 이미지의 높이에 맞게 조정 */    
    text-align : center;
  }
  #img1{
   display:block; margin:auto;
   height: 50px;  width:50px;
  }
  
</style>

<section>
<br>
  <div align="center"> 
  <h2>  SummerEdit ( EL / JSTL )    </h2>
	<form method="post" action="${path}/CkeditorController">
		<input type=hidden name=sw value=U>
		<table  border=1 >
			<tr align="center"> 
				<td>idx</td>  
				<td>title</td> 
				<td>name</td>
				<td>today</td> 
			</tr>
			<tr align="center"> 
				<td><input type=text name="idx" value="${m.idx}" readonly></td> 
				<td><input type=text name="title" value="${m.title}"></td> 
				<td><input type=text name="name" value="${m.name}"></td> 	
				<td><input type=text name="today" value="${m.today}" readonly></td> 
			</tr>
			<tr align="center"> 
				<td colspan=4>
					<textarea id="editor" name="etc">${m.etc}</textarea>
				</td> 
			</tr>
			<tr>
				<td colspan=4 align="center" > 
					<input  type=button onClick="newK()" value="새글작성" style="height:70%;"> &emsp;
			    <input  type=submit value="수정하기" style="height:70%;"> &emsp;
			    <input  type=button onClick="listK()" value="목록보기" style="height:70%;"> &emsp;
			    <input  type=button onClick="delK(${m.idx})" value="삭제하기" style="height:70%;">   
				</td>
			</tr>
		</table>
	</form>
	<form  method="post" action="${path}/CkeditorController">
		<input type=hidden name=sw value=CI >
		<input type=hidden name=idx value="${m.idx}" >
		<table border=1 align=center>
			<tr>
				<td>작성자</td>
				<td>제목</td>
				<td>저장/삭제</td>
			</tr>
			<tr>
				<td><input type=text name=name ></td>
				<td><input type=text name=title ></td>
				<td rowspan=2><input type=submit value=저장 ></td>
			</tr>
			<tr>
				<td colspan=2><textarea name=content ></textarea></td>
			</tr>
		</table>
	</form>
	<table border=1 align=center>
		<tr>
			<td>cidx</td>
			<td>idx</td>
			<td>작성자</td>
			<td>제목</td>
			<td>내용</td>
			<td>날짜</td>
			<td>삭제</td>
		</tr>
		<c:if test="${li == null}">
			<tr align="center">   
		    <td colspan=5> 레코드가 존재하지 않습니다. </td>  
			</tr>
		</c:if>
		<c:if test="${li != null}">
			<!-- 반복문 시작 -->
			<c:forEach  var="m" items="${li}">
				<tr align="center">
					<td>${m.cidx}</td> 
					<td>${m.idx}</td> 
					<td> ${m.name}</td> 	
					<td> ${m.title}</td> 
					<td> ${m.content}</td> 
					<td> ${m.today}</td> 
					<c:url value="/CkeditorController?sw=CD&idx=${m.idx}&cidx=${m.cidx}"  var ="deleteUrl">
					</c:url>
					<td><a href="${deleteUrl}">삭제</a></td>
				</tr>
			</c:forEach>
		<!-- 반복문 끝 -->
		</c:if>
	</table>
</div> 
<br> 
</section>

<script>
function  delK(idx){
	alert("삭제:" + idx)
	location.href="${path}/CkeditorController?sw=D&idx="+idx
}

function  listK(){
	location.href="${path}/CkeditorController?sw=S"
}

function  newK(){
	location.href="${path}/CkeditorController?sw=F"
}
</script>


<script src="https://cdn.ckeditor.com/ckeditor5/41.3.1/super-build/ckeditor.js"></script>
<script>
    CKEDITOR.ClassicEditor.create(document.getElementById("editor"), {

        toolbar: {
            items: [
                'exportPDF','exportWord', '|',
                'findAndReplace', 'selectAll', '|',
                'heading', '|',
                'bold', 'italic', 'strikethrough', 'underline', 'code', 'removeFormat', '|',
                'bulletedList', 'numberedList', 'todoList', '|',
                'outdent', 'indent', '|',
                'undo', 'redo',
                '-',
                'fontSize', 'fontFamily', 'fontColor', 'fontBackgroundColor', 'highlight', '|',
                'alignment', '|',
                'link', 'uploadImage', 'blockQuote', 'insertTable', 'mediaEmbed', 'codeBlock', 'htmlEmbed', '|',
                'specialCharacters', 'horizontalLine', 'pageBreak', '|',
                'textPartLanguage', '|',
                'sourceEditing'
            ],
            shouldNotGroupWhenFull: true
        },

        list: {
            properties: {
                styles: true,
                startIndex: true,
                reversed: true
            }
        },

        heading: {
            options: [
                { model: 'paragraph', title: 'Paragraph', class: 'ck-heading_paragraph' },
                { model: 'heading1', view: 'h1', title: 'Heading 1', class: 'ck-heading_heading1' },
                { model: 'heading2', view: 'h2', title: 'Heading 2', class: 'ck-heading_heading2' },
                { model: 'heading3', view: 'h3', title: 'Heading 3', class: 'ck-heading_heading3' },
                { model: 'heading4', view: 'h4', title: 'Heading 4', class: 'ck-heading_heading4' },
            ]
        },
        placeholder: 'Welcome to CKEditor 5!',
        fontFamily: {
            options: [
                'default',
                'Arial, Helvetica, sans-serif',
                'Courier New, Courier, monospace',
                'Trebuchet MS, Helvetica, sans-serif',
                'Verdana, Geneva, sans-serif'
            ],
            supportAllValues: true
        },
        fontSize: {
            options: [ 10, 12, 14, 'default', 18, 20, 22 ],
            supportAllValues: true
        },
        htmlSupport: {
            allow: [
                {
                    name: /.*/,
                    attributes: true,
                    classes: true,
                    styles: true
                }
            ]
        },
        htmlEmbed: {
            showPreviews: true
        },
         link: {
            decorators: {
                addTargetToExternalLinks: true,
                defaultProtocol: 'https://',
                toggleDownloadable: {
                    mode: 'manual',
                    label: 'Downloadable',
                    attributes: {
                        download: 'file'
                    }
                }
            }
        },
        mention: {
            feeds: [
                {
                    marker: '@',
                    feed: [
                        '@apple', '@bears', '@brownie', '@cake', '@cake', '@candy', '@canes', '@chocolate',
                        '@sugar', '@sweet', '@topping', '@wafer'
                    ],
                    minimumCharacters: 1
                }
            ]
        },
        removePlugins: [
            'AIAssistant',  'CKBox',   'CKFinder',    'EasyImage',   'MultiLevelList',
            'RealTimeCollaborativeComments',   'RealTimeCollaborativeTrackChanges',
            'RealTimeCollaborativeRevisionHistory',      'PresenceList',
            'Comments',    'TrackChanges',    'TrackChangesData',  'RevisionHistory',
            'Pagination',  'WProofreader',   'MathType',   'SlashCommand',
            'Template',    'DocumentOutline', 'FormatPainter', 'TableOfContents',
            'PasteFromOfficeEnhanced', 'CaseChange'
        ]
    });
</script>

<jsp:include page="/include/bottom.jsp"  />