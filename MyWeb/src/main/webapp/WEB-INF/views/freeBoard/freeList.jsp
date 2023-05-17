<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

			<%@ include file="../include/header.jsp" %>

				<section>
					<div class="container-fluid">
						<div class="row">
							<!--lg에서 9그리드, xs에서 전체그리드-->
							<div class="col-lg-9 col-xs-12 board-table">
								<div class="titlebox">
									<p>자유게시판</p>
								</div>
								<hr>

								<!--form select를 가져온다 -->
								<form>
									<div class="search-wrap">
										<button type="button" class="btn btn-info search-btn">검색</button>
										<input type="text" class="form-control search-input"> <select
											class="form-control search-select">
											<option>제목</option>
											<option>내용</option>
											<option>작성자</option>
											<option>제목+내용</option>
										</select>
									</div>
								</form>

								<table class="table table-bordered">
									<thead>
										<tr>
											<th>번호</th>
											<th class="board-title">제목</th>
											<th>작성자</th>
											<th>등록일</th>
											<th>수정일</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="vo" items="${boardList}">
											<tr>
												<td>${vo.bno}</td>
												<td><a
														href="${pageContext.request.contextPath}/freeBoard/content/${vo.bno}">${vo.title}</a>
												</td>
												<td>${vo.writer}</td>
												<td>
													<fmt:parseDate value="${vo.regDate}" pattern="yyyy-MM-dd'T'HH:mm:ss"
														var="parsedDate" type="both" />
													<fmt:formatDate value="${parsedDate}"
														pattern="yyyy년 MM월 dd일 HH시 mm분" />
												</td>
												<td>
													<fmt:parseDate value="${vo.updateDate}"
														pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedUpdatetime"
														type="both" />
													<fmt:formatDate value="${parsedUpdatetime}"
														pattern="yyyy년 MM월 dd일 HH시 mm분" />
												</td>
											</tr>
										</c:forEach>
									</tbody>

								</table>


								<!--페이지 네이션을 가져옴-->
								<form action="${pageContext.request.contextPath}/freeBoard/freeList" name="pageForm">
									<div class="text-center">
										<hr>
										<ul id="pagination" class="pagination pagination-sm">
											<c:if test="${pc.prev}">
												<li><a href="#" data-pagenum="${pc.beginPage-1}">이전</a></li>
											</c:if>

											<c:forEach var="num" begin="${pc.beginPage}" end="${pc.endPage}">
												<li class="${pc.paging.pageNum == num ? 'active' : ''}">
													<a href="#" data-pagenum="${num}">${num}</a>
												</li>
											</c:forEach>

											<c:if test="${pc.next}">
												<li><a href="#" data-pagenum="${pc.endPage+1}">다음</a></li>
											</c:if>
										</ul>
										<button type="button" class="btn btn-info"
											onclick="location.href='${pageContext.request.contextPath}/freeBoard/freeRegister'">글쓰기</button>
									</div>
								</form>

							</div>
						</div>
					</div>
				</section>

				<%@ include file="../include/footer.jsp" %>

					<script>

						// 브라우저 창이 로딩이 완료된 후에 실행할 것을 보장하는 이벤트.
						window.onload = () => {

							// 사용자가 페이지 관련 버튼을 클릭했을 때, 기존에는 각각의 a태그의 href에 각각 다른 url을 일일이 작성해서 보내줬다면,
							// 이번에는 클릭한 그 버튼이 무엇인지를 확인해서 그 버튼에 맞는 페이지 정보를 자바스크립트로 끌고 와서 요청을 보내주겠습니다.

							document.getElementById('pagination').addEventListener(e => {
								if (!e.target.matches('a')) return;
							});

						}

					</script>