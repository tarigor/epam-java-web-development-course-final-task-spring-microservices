<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="html" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>

<f:setLocale value="${sessionScope.language}" scope="session"/>
<f:setBundle basename="local" var="local"/>

<html style="font-size: 16px;">
<head>
	<title>Log In</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Log-in.css" media="screen">
	<meta property="og:title" content="Log In">
	<c:import url="common/head.jsp"/>
</head>
<body class="u-body">
<c:import url="common/menu.jsp"/>
<section class="u-clearfix u-image u-section-1" id="sec-3a70" data-image-width="1280" data-image-height="839">
	<div class="u-clearfix u-sheet u-sheet-1">
		
		<div class="u-clearfix u-custom-html u-grey-60 u-opacity u-opacity-70 u-custom-html-1">
			<meta charset="utf-8">
			<title></title>
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<link rel="stylesheet" href="fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">
			<link rel="stylesheet" href="css/style.css">
			<br>
			<div class="wrapper" style="background-image: url('images/bg-registration-form-2.jpg');">
				<div class="inner">
					<form action="">
						<h3 class="text-center"><f:message key="account.page.name" bundle="${local}"/></h3>
						<div class="form-wrapper">
							<a class=" text-center"><f:message key="account.holder" bundle="${local}"/>:</a>
							<label for="" class="form-control text-center align-middle" style="text-align:center">
								<a>${sessionScope.user.getFirstName()}</a>
								<a> </a>
								<a>${sessionScope.user.getLastName()}</a>
							</label>
							<a class=" text-center"><f:message key="account.balance" bundle="${local}"/>:</a>
							<label for=""
							       class="form-control text-center align-middle">${String.format("%.2f",sessionScope.user.getAccount())}
								USD</label>
						</div>
						<div class="form-wrapper">
							<%--							<a href="<c:url value="${pageContext.request.contextPath}/command?name=top_up&user=${sessionScope.user.getUserID()}"/>"--%>
							<a href="<c:url value="${pageContext.request.contextPath}/page?name=chargeaccount"/>"
							   class="btn btn-success form-control text-center align-middle"
							   style="text-align: center;color: white"><f:message
									key="account.charge.button"
									bundle="${local}"/></a>
						</div>
						<c:if test="${charged}">
							<label style="text-align: center;font-size: medium;color: #62C584"><f:message
									key="account.charged"
									bundle="${local}"/></label>
							<a href="<c:url value="${pageContext.request.contextPath}/clientcabinet"/>"
							   class="btn btn-secondary form-control text-center align-middle"
							   style="text-align: center;color: black"><f:message
									key="account.back.to.client.cabinet"
									bundle="${local}"/></a>
						</c:if>
						<c:if test="${paid}">
							<label style="text-align: center;font-size: medium;color: #62C584 "><f:message
									key="account.paid"
									bundle="${local}"/></label>
							<a href="<c:url value="${pageContext.request.contextPath}/clientcabinet"/>"
							   class="btn btn-secondary form-control text-center align-middle"
							   style="text-align: center;color: black"><f:message
									key="account.back.to.client.cabinet"
									bundle="${local}"/></a>
						</c:if>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>
<c:import url="common/footer.jsp"/>
<%--<c:import url="common/cookies.jsp"/>--%>
</body>
</html>