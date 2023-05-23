﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

			<%@ include file="../include/header.jsp" %>

				<section>
					<div class="container">
						<div class="row">
							<div class="col-xs-12 col-md-9 write-wrap">
								<div class="titlebox">
									<p>상세보기</p>
								</div>

								<form action="${pageContext.request.contextPath}/freeBoard/freeModify" method="post">
									<div>
										<label>DATE</label>
										<c:choose>
											<c:when test="${content.updateDate == null}">
												<c:set var="value" value="${content.regDate}" />
											</c:when>
											<c:otherwise>
												<c:set var="value" value="${content.updateDate}" />
											</c:otherwise>
										</c:choose>
										<p>
											<fmt:parseDate value="${value}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDate" type="both" />
											<fmt:formatDate value="${parsedDate}" pattern="yyyy년 MM월 dd일 HH시 mm분" />
										</p>
									</div>

									<div class="form-group">
										<label>번호</label> <input class="form-control" name='bno' value="${content.bno}" readonly>
									</div>
									<div class="form-group">
										<label>작성자</label> <input class="form-control" name='writer' value="${content.writer}" readonly>
									</div>
									<div class="form-group">
										<label>제목</label> <input class="form-control" name='title' value="${content.title}" readonly>
									</div>

									<div class="form-group">
										<label>내용</label>
										<textarea class="form-control" rows="10" name='content' readonly>${content.content}</textarea>
									</div>

									<button type="submit" class="btn btn-primary" onclick="return confirm('변경 페이지로 이동합니다.')">변경</button>
									<button type="button" class="btn btn-dark"
										onclick="location.href='${pageContext.request.contextPath}/freeBoard/freeList?pageNum=${p.pageNum}&cpp=${p.cpp}&keyword=${p.keyword}&condition=${p.condition}'">목록</button>
								</form>
							</div>
						</div>
					</div>
				</section>

				<!-- 댓글 영역 시작부분 -->
				<section style="margin-top: 80px;">
					<div class="container">
						<div class="row">
							<div class="col-xs-12 col-md-9 write-wrap">
								<form class="reply-wrap">
									<div class="reply-image">
										<img src="${pageContext.request.contextPath}/img/profile.png">
									</div>
									<!--form-control은 부트스트랩의 클래스입니다-->
									<div class="reply-content">
										<textarea class="form-control" rows="3" id="reply"></textarea>
										<div class="reply-group">
											<div class="reply-input">
												<input type="text" class="form-control" id="replyId" placeholder="이름">
												<input type="password" class="form-control" id="replyPw" placeholder="비밀번호">
											</div>

											<button type="button" id="replyRegister" class="right btn btn-info">등록하기</button>
										</div>

									</div>
								</form>

								<!--여기에접근 반복-->
								<div id="replyList">
									<!--  자바스크립트 단에서 반복문을 이요해서 댓글의 개수만큼 반복 표현.										
										<div class='reply-wrap'>
											<div class='reply-image'>
												<img src='${pageContext.request.contextPath}/img/profile.png'>
											</div>
											<div class='reply-content'>
												<div class='reply-group'>
													<strong class='left'>honggildong</strong> <small class='left'>2019/12/10</small>
													<a href='#' class='right'><span class='glyphicon glyphicon-pencil'></span>수정</a> <a href='#'
														class='right'><span class='glyphicon glyphicon-remove'></span>삭제</a>
												</div>
												<p class='clearfix'>여기는 댓글영역</p>
											</div>
										</div> 
									</div> -->
								</div>

								<button type="button" class="form-control" id="moreList">더보기</button>

							</div>
						</div>
				</section>

				<!-- 모달 -->
				<div class="modal fade" id="replyModal" role="dialog">
					<div class="modal-dialog modal-md">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="btn btn-default pull-right" data-dismiss="modal">닫기</button>
								<h4 class="modal-title">댓글 수정</h4>
							</div>
							<div class="modal-body">
								<!-- 수정폼 id값을 확인하세요-->
								<div class="reply-content">
									<textarea class="form-control" rows="4" id="modalReply" placeholder="수정할 내용을 입력해주세요. :)"></textarea>
									<div class="reply-group">
										<div class="reply-input">
											<input type="hidden" id="modalRno"> <input type="password" class="form-control"
												placeholder="비밀번호를 입력해주세요. :)" id="modalPw">
										</div>
										<button class="right btn btn-info" id="modalModBtn">수정하기</button>
										<button class="right btn btn-info" id="modalDelBtn">삭제하기</button>
									</div>
								</div>
								<!-- 수정폼끝 -->
							</div>
						</div>
					</div>
				</div>

				<%@ include file="../include/footer.jsp" %>

					<script>
						window.onload = () => {
							document.getElementById('replyRegister').onclick = () => {
								console.log('댓글 등록 버튼이 눌림!');
								const bno = '${content.bno}'; // 현재 게시글 번호
								const reply = document.getElementById('reply').value;
								const replyId = document.getElementById('replyId').value;
								const replyPw = document.getElementById('replyPw').value;

								if (reply === '' || replyId === '' || replyPw === '') {
									alert('이름, 비밀번호, 내용을 모두 입력해주셔야 합니다! :(');
									return;
								}

								// 요청에 관련된 정보 객체
								const reqobj = {
									method: 'POST',
									headers: {
										'Content-Type': 'application/json'
									},
									body: JSON.stringify({
										'bno': bno,
										'reply': reply,
										'replyId': replyId,
										'replyPw': replyPw
									})
								}

								fetch('${pageContext.request.contextPath}/reply/register', reqobj)
									.then(res => res.text())
									.then(data => {
										console.log('통신 성공! (' + data + ')');
										document.getElementById('reply').value = '';
										document.getElementById('replyId').value = '';
										document.getElementById('replyPw').value = '';

										// 등록 완료 후 댓글 목록 함수를 호출해서 비동기식으로 목록 표현.
										getList(1, true); // false가 오는 경우는 사용자가 '더보기' 버튼을 눌렀을 때다.
									});
							} // 댓글 등록 이벤트 끝

							let page = 1;			// 전역 의미로 사용할 페이지 번호
							let strAdd = '';	// 화면에 그려넣을 태그를 문자열의 형태로 추가할 변수

							// 게시글 상세보기 화면에 처음 진입했을 시 댓글 리스트를 한 번 불러오자.
							getList(1, true);

							// 댓글 목록을 가져올 함수
							// getList의 매개값으로 뭘 줄 거냐?
							// 요청된 페이지 번호와 화면을 리셋할 것인지의 여부를 bool 타입의 reset으로 받겠습니다.
							// (페이지가 그대로 머물면서 댓글이 밑에 계속 쌓이기 때문에
							// 상황에 따라서 페이지를 리셋해서 새롭게 그려낼 것인지, 누적해서 쌓을 것인지의 여부를 판단)
							function getList(pageNum, reset) {
								strAdd = '';
								const bno = '${content.bno}'; // 게시글 번호

								// get 방식으로 댓글 목록을 요청(비동기)
								fetch('${pageContext.request.contextPath}/reply/getList/' + bno + '/' + pageNum)
									.then(res => res.json())
									.then(data => {
										// console.log(data);

										let total = data.total;			// 총 댓글 수
										let replyList = data.list;	// 댓글 리스트
										let position = 'beforeend';
										const $replyList = document.getElementById('replyList');

										// 응답 데이터의 길이가 0과 같거나 더 작으면 함수를 종료.
										if (replyList.length <= 0) return;

										// insert, update, delete 작업 후에는
										// 댓글 내용 태그를 누적하고 있는 strAdd 변수를 초기화해서 마치 화면이 리셋된 것처럼 보여줘야 합니다.
										if (reset) {
											while ($replyList.firstChild) {
												$replyList.removeChild($replyList.firstChild);
											}
											page = 1;
											position = 'afterbegin'
										}

										// 페이지 번호 * 이번 요청으로 받은 댓글 수보다 전체 댓글 수가 작다면 더보기 버튼은 없어도 된다.
										if (total <= page * 5) {
											document.getElementById('moreList').style.display = 'none';
										} else {
											document.getElementById('moreList').style.display = 'block';
										}

										// replyList의 개수만큼 태그를 문자열 형태로 직접 그림.
										// 중간에 들어갈 글쓴이, 날짜, 댓글 내용은 목록에서 꺼내서 표현.
										for (let i = 0; i < replyList.length; i++) {
											strAdd +=
												`<div class='reply-wrap'>
													<div class='reply-image'>
														<img src='${pageContext.request.contextPath}/img/profile.png'>
													</div>
													<div class='reply-content'>
														<div class='reply-group'>
															<strong class='left'>` + replyList[i].replyId + `</strong>
															<small class='left'>` + replyList[i].replyDate + `</small>
															<a href='` + replyList[i].rno + `' class='right replyDelete'><span class='glyphicon glyphicon-remove'></span>삭제</a>
															<a href='` + replyList[i].rno + `' class='right replyModify'><span class='glyphicon glyphicon-pencil'></span>수정</a> &nbsp;
														</div>
														<p class='clearfix'>` + replyList[i].reply + `</p>
													</div>
												</div>`;
										}

										// id가 replyList라는 div 영역에 문자열 형식으로 모든 댓글을 추가.
										document.getElementById('replyList').insertAdjacentHTML(position, strAdd);
									});
							} // end getList();

							// 더보기 버튼 처리(클릭 시 전역 변수 page에 +1한 값을 요청)
							document.getElementById('moreList').onclick = () => {
								/*
									왜 false를 줄까요?
									더보기이기 때문에 댓글을 누적해서 보여줘야 하지 않을까요?
									1페이지의 댓글 내용 밑에다가 2페이지를 누적해서 깔아야 합니다.
									1페이지 내용을 없애고 2페이지를 보여주는 것이 아니기 때문입니다.
								*/
								getList(++page, false);
							}

							// 수정, 삭제
							/*
								document.querySelector('.replyModify').onclick = e => {
									e.preventDefault();
									console.log('수정 이벤트 발생!!!');
								};

								.replyModify 요소는 실제 존재하는 요소가 아니라 비동기 통신을 통해 생성되는 요소입니다.
								그러다보니 이벤트가 등록되는 시점보다 fetch 함수의 실행이 먼저 끝날 것이라는 보장이 없기 때문에 해당 방식은 이벤트 등록이 불가능합니다.
								이 때는, 이미 실제로 존재하는 #replyList에 등록하고, 이벤트를 자식에게 위임하여 사용하는 addEventListener를 통해 처리를 해야 합니다.
							*/
							document.getElementById('replyList').addEventListener('click', e => {
								e.preventDefault(); // 태그의 고유 기능 중지

								// 1. 이벤트가 발생한 target이 a 태그가 아니라면 이벤트 종료.
								if (!e.target.matches('a')) return;

								// 2. a 태그가 두 개(수정, 삭제)이므로 어떤 링크인지를 확인.
								// 댓글이 여러 개 -> 수정, 삭제가 발생하는 댓글이 몇 번인지도 확인.
								const rno = e.target.getAttribute('href');
								console.log('댓글 번호 : ' + rno);
								// 모달 내부에 숨겨진 input 태그에 댓글 번호를 담아주자.
								document.getElementById('modalRno').value = rno;

								const content = e.target.parentNode.nextElementSibling.textContent;
								console.log('댓글 내용 : ' + content);

								// 3. 모달 창 하나를 이용해서 상황에 따라 수정/ 삭제 모달을 구분하기 위해 조건문을 작성.
								// (모달 하나로 수정, 삭제를 같이 처리. 그러기 위해 디자인 조정.)
								if (e.target.classList.contains('replyModify')) {
									document.getElementById('modalDelBtn').style.display = 'none';
									// 수정 버튼을 눌렀으므로 수정 모달 형식을 꾸며주겠다.
									document.querySelector('.modal-title').textContent = '댓글 수정';
									document.getElementById('modalReply').style.display = 'inline'; // 댓글창
									document.getElementById('modalReply').value = content;
									document.getElementById('modalModBtn').style.display = 'inline';
									document.getElementById('modalDelBtn').style.display = 'none';

									// 제이쿼리를 이용해서 bootstrap 모달을 여는 방법.
									$('#replyModal').modal('show');
								} else {
									// 삭제 버튼을 눌렀으므로 삭제 모달 형식으로 꾸며줌.
									document.querySelector('.modal-title').textContent = '댓글 삭제';
									document.getElementById('modalReply').style.display = 'none'; // 댓글창
									document.getElementById('modalModBtn').style.display = 'none';
									document.getElementById('modalDelBtn').style.display = 'inline';

									$('#replyModal').modal('show');
								}
							}); // 수정 or 삭제 버튼 클릭 이벤트 끝.

							// 수정 처리 함수. (수정 모달을 열어서 수정 내용을 작성 후 수정 버튼을 클릭했을 때)
							document.getElementById('modalModBtn').onclick = () => {
								const reply = document.getElementById('modalReply').value;
								const rno = document.getElementById('modalRno').value;
								const replyPw = document.getElementById('modalPw').value;

								if (reply === '' || replyPw === '') {
									alert('내용과 비밀번호는 필수 입력사항입니다! :(');
									return;
								}

								// 요청에 관련된 정보 객체
								const reqobj = {
									method: 'PUT',
									headers: {
										'Content-Type': 'application/json'
									},
									body: JSON.stringify({
										'reply': reply,
										'replyPw': replyPw
									})
								}

								fetch('${pageContext.request.contextPath}/reply/' + rno, reqobj)
									.then(res => res.text())
									.then(data => {
										if (data === 'Fail') {
											alert('비밀번호를 확인해주세요! :(');
											document.getElementById('modalPw').value = '';
											document.getElementById('modalPw').style.borderColor = 'red';
											document.getElementById('modalPw').focus();
										} else {
											alert('수정이 완료되었습니다! :)');
											document.getElementById('modalReply').value = '';
											document.getElementById('modalPw').value = '';
											document.getElementById('modalPw').style.borderColor = 'black';
											$('#replyModal').modal('hide');
											getList(1, true);
										}
									});
							}
						} // window.onload
					</script>