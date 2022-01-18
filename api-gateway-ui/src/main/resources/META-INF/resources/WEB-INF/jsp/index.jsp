<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="by_BY" scope="session"/>
<f:setBundle basename="local" var="local"/>

<html style="font-size: 16px;">
<head>
	<title>Home</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Home.css" media="screen">
	<meta property="og:title" content="Home">
	<c:import url="common/head.jsp"/>
</head>
<body data-home-page="Home.html" data-home-page-title="Home" class="u-body">
<c:import url="common/menu.jsp"/>
<section class="u-clearfix u-image u-section-1" id="carousel_b7ce" data-image-width="1280" data-image-height="839">
	<div class="u-align-left u-clearfix u-sheet u-sheet-1">
		<h4 class="u-custom-font u-font-georgia u-text u-text-body-alt-color u-text-default u-text-1">
			<f:message key="index.hello.guest.part1" bundle="${local}"/>
			<c:if test="${user!=null}">
				${user.getUserFirstName()}
			</c:if>
			<c:if test="${user==null}">
				<f:message key="index.guest" bundle="${local}"/>
			</c:if>
			<f:message key="index.hello.guest.part2" bundle="${local}"/>
			->${pageContext.request.contextPath}
		</h4>
		<h6 class="u-custom-font u-font-georgia u-text u-text-body-alt-color u-text-default u-text-2">
			<f:message key="index.request.message" bundle="${local}"/>
		</h6>
		<form action="command?name=request" method="post">
			<div class="u-clearfix u-custom-html u-custom-html-1">
				<select id="personsAmount" name="persons" class="custom-select" aria-label="Default select example">
					<option selected="" value=""></option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
				</select>
			</div>
			<p class="u-text u-text-body-alt-color u-text-default u-text-3"><f:message key="index.persons"
			                                                                           bundle="${local}"/></p>
			<div class="u-clearfix u-custom-html u-custom-html-2">
				<select id="roomClass" name="roomClass" class="custom-select" aria-label="Default select example">
					<option selected="" value=""></option>
					<option value="SINGLE">SINGLE</option>
					<option value="DOUBLE">DOUBLE</option>
					<option value="SUITE">SUITE</option>
					<option value="DELUXE">DELUXE</option>
				</select>
			</div>
			<p class="u-text u-text-body-alt-color u-text-default u-text-4"><f:message key="index.room.class"
			                                                                           bundle="${local}"/></p>
			<div class="u-clearfix u-custom-html u-custom-html-3">
				<input type="text" name="datefilter" value="">
			</div>
			<p class="u-text u-text-body-alt-color u-text-default u-text-4" style="text-align: right"><f:message
					key="index.date.range" bundle="${local}"/></p>
			<button type="submit"
			        class="u-border-2 u-border-white u-btn u-button-style u-hover-grey-50 u-none u-text-body-alt-color u-text-hover-white u-btn-1">
				<f:message key="index.check.availability" bundle="${local}"/>
			</button>
		</form>
	</div>
</section>
<c:import url="common/footer.jsp"/>
<%--<c:import url="common/cookies.jsp"/>--%>
</body>
</html>