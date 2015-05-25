<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<div class=formView>

				<h2 align="center">
					<c:out value="${work.name}" default="Art Work"></c:out>
				</h2>

				<sf:form class="formWorksView" method="POST" action="submitaddcomment">
					<fieldset class="fieldsetView">

						<table class="tableWorkInfoView" border="0">
							<tr>
								<td class="fright" rowspan="6"><a
									href="${pageContext.request.contextPath}/images/Works/${work.path}"
									data-lightbox="demo" data-title="${work.name}"> <img
										class="artworkInfo"
										src="${pageContext.request.contextPath}/images/Works/${work.path}"
										title="work" alt="work" />
								</a></td>
								
								<td align="right">
									<span class="textView"><spring:message code="author"/>:</span>
								</td>
								<td class="fleft">
									<spring:url value="/userInfo/${work.user.login}" var="userInfo" htmlEscape="true"/>
            						<a  href="${userInfo}">
										<span class="textWorkNameView"><c:out value=" ${work.user.login}"></c:out></span>
									</a>
								</td>
							</tr>
							<tr> 
								<td align="right">
									<span class="textView"><spring:message code="work_type"/>:</span>
								</td>
								<td class="fleft">
									<span class="textWorkTypeView"><c:out value="${work.type.name}"></c:out></span>
								</td>
							 </tr>
							<tr> 
								<td align="right">
									<span class="textView"><spring:message code="load_date"/>:</span>
								</td>
								<td class="fleft">
									<fmt:formatDate value="${work.loadDate}" var="dateString" pattern="dd.MM.yyyy" />
									<span class="textView"><c:out value="${dateString}"></c:out></span>
								</td>
							 </tr>
							<tr>
								<td align="right">
									<span class="textView"><spring:message code="competition"/>:</span>
								</td>
								<td class="fleft"><span class="textCompetitionView"><c:out
											value="${work.competition.name}"></c:out></span> <c:if
										test="${empty work.competition}">
										<small><span class="textCompetitionView"><c:out
													value="no competition"></c:out> </span>
									</c:if></td>
							</tr>
							<tr>
								<td class="tdView"><span class="textView"><c:out
											value="${work.comments.size()}"></c:out></span> <img class="icon"
									src="${pageContext.request.contextPath}/resources/css/images/comm.png"
									title="icon" alt="icon" /></td>
							</tr>
							<tr>
								<td class="tdView">
									<c:if	test="${not empty work.competition}">
										<span class="textView"><c:out value="${work.voices.size()}"></c:out></span>
										<img class="icon"
											src="${pageContext.request.contextPath}/resources/css/images/star.png"	title="icon" alt="icon" />
											
											<c:if	test="${work.user.login!=sessionScope.login&&login != null&&!voitedUser}">
	 											<spring:url value="/addLike" var="addLike" htmlEscape="true"/>
												<a  href="${addLike}">
													<spring:message code="add_like"/>
												</a>
											</c:if>
											<c:if	test="${voitedUser}">
												<h4 align="center"><spring:message code="thank_you"/></h4>
											</c:if>
									</c:if>	
									</td>
							</tr>
						</table>
	
						<br /><br />
						<div align="left" class="textView"><spring:message code="comments"/>:</div>
						
						<c:if test="${empty work.comments}"> 
							<h4 align="center"><spring:message code="no_comments"/></h4>
						</c:if>
						
						<c:if test="${not empty login}"> 
							<c:if test="${!user.commentAble}"> 
								<h4 align="center"><spring:message code="you_can_not_comment"/></h4>
							</c:if>
						</c:if>
						
						<c:if test="${empty login}"> 
							<h4 align="center"><spring:message code="please_login_for_comment"/></h4>
						</c:if>
						<br />
						
						<c:forEach items="${work.comments}" var="comment">
							<table>
								<tr>
									<td rowspan="2">
										<span class="fleft">
											<img class="avatar_comment" src="${pageContext.request.contextPath}/images/Avatars/${comment.user.photoPath}" title ="avatar" alt="avatar"/>
											
										</span>		
									</td>
									<td >
										<span class="textWorkNameView"><c:out value="${comment.user.login} "></c:out></span>
										<fmt:formatDate value="${  comment.loadDate}" var="dateString" pattern="dd.MM.yyyy HH:mm" />
										<span class="textCommentTimeView"><c:out value=" ${dateString}"></c:out></span>
									</td>
								</tr>
								<tr>
									<td  class="sizeCommentView">
										<span class="textCommentView"><c:out value="${comment.commentText}" ></c:out></span>
									</td>
								</tr>
							</table>
						</c:forEach>
					
					<c:if test="${not empty sessionScope.login}"> 	
						<c:if test="${user.commentAble}"> 
						<br />
						<span class="textView"><c:out value="Add comment: "></c:out></span>
						<br /><br />
						<textarea name="commentText" rows="4" cols="80"  maxlength="1000" required="true" ></textarea>
						<div class="comment_buttons_width" width="50%">
						<button class="buttonCancel" type="reset" >Cancel</button>
						<button class="buttonApply" type="submit">Apply</button>
						</div>
						</c:if>
					</c:if>	
		<!-- 				<h4 align="center">
							<spring:url value="/first" var="firstUrl" htmlEscape="true" />
							<a class="pagin" href="${firstUrl}">First ...</a>

							<c:if test="${pageNumber!=1}">
								<spring:url value="/prev" var="prevUrl" htmlEscape="true" />
								<a class="pagin" href="${prevUrl}">Prev</a>
							</c:if>
							<c:if test="${pageNumber==1}">
								<span class="pagin_label"><c:out value="Prev"></c:out></span>
							</c:if>
							Page ${pageNumber} of ${pageCount}
							<c:if test="${pageNumber!=pageCount}">
								<spring:url value="/next" var="nextUrl" htmlEscape="true" />
								<a class="pagin" href="${nextUrl}">Next</a>
							</c:if>
							<c:if test="${pageNumber==pageCount}">
								<span class="pagin_label"><c:out value="Next"></c:out></span>
							</c:if>
							<spring:url value="/last" var="lastUrl" htmlEscape="true" />
							<a class="pagin" href="${lastUrl}">... Last</a>
						</h4>
 -->
					</fieldset>
				</sf:form>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>