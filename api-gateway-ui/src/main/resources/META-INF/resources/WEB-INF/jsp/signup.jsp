<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<f:setLocale value="${sessionScope.language}" scope="session"/>
<f:setBundle basename="local" var="local"/>

<html style="font-size: 16px;">
<head>
	<title>Sign Up</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Sign-up.css" media="screen">
	<meta property="og:title" content="Sign Up">
	<c:import url="common/head.jsp"/>
</head>
<body class="u-body">
<c:import url="common/menu.jsp"/>
<section class="u-clearfix u-image u-section-1" id="sec-3a70" data-image-width="1280" data-image-height="839">
	<div class="u-clearfix u-sheet u-sheet-1">
		<div class="u-clearfix u-custom-html u-grey-60 u-opacity u-opacity-70 u-custom-html-1">
			<meta charset="utf-8">
			<title></title>
			<meta name="viewport" content="width=device-width, initial-scale=1.0"><!-- MATERIAL DESIGN ICONIC FONT -->
			<link rel="stylesheet" href="fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">
			<!-- STYLE CSS -->
			<link rel="stylesheet" href="css/style.css">
			<div class="wrapper">
				<div class="inner">
					<form:form action="${pageContext.request.contextPath}/command?name=registration" modelAttribute="registration">
						<h3>Registration Form</h3>
						<div class="form-group">
							<div class="form-wrapper">
								<label>First Name</label>
								<form:input type="text" class="form-control" path="firstName"
								            oninput="validateName(this)"/>
								<c:if test="${fieldErrorList.contains('firstName')}">
									<label class="text-warning"
									       style="font-size: xx-small"><f:message key="validator.wrong.user.name.input"
									                                              bundle="${local}"/></label>
								</c:if>
							</div>
							<div class="form-wrapper">
								<label>Last Name</label>
								<form:input type="text" class="form-control" path="lastName"
								            oninput="validateName(this)"/>
								<c:if test="${fieldErrorList.contains('lastName')}">
									<label class="text-warning"
									       style="font-size: xx-small"><f:message key="validator.wrong.user.name.input"
									                                              bundle="${local}"/></label>
								</c:if>
							</div>
						</div>
						<div class="form-wrapper">
							<label>Email</label>
							<form:input type="text" class="form-control" path="email"
							            oninput="validateEmail(this)"/>
							<c:if test="${fieldErrorList.contains('email')}">
								<label class="text-warning"
								       style="font-size: xx-small"><f:message key="validator.wrong.user.email.input"
								                                              bundle="${local}"/></label>
							</c:if>
						</div>
						<div class="form-wrapper">
							<label>User Type</label>
							<form:select id="userRole" path="userType" class="custom-select"
							             aria-label="Default select example">
								<option selected value="CLIENT"></option>
								<option value="ADMIN">ADMINISTRATOR</option>
								<option value="CLIENT">CLIENT</option>
							</form:select>
						</div>
						<div class="form-wrapper">
							<label>Password</label>
							<form:input type="password" class="form-control" path="password"
							            oninput="validatePassword(this)"/>
						</div>
						<div class="form-wrapper">
							<label>Confirm Password</label>
							<form:input type="password" class="form-control" path="repeatedPassword"
							            oninput="checkPassword(this)"/>
						</div>
						<c:if test="${fieldErrorList.contains('passwordDoubleCheck')}">
							<label class="text-warning"
							       style="font-size: xx-small"><f:message
									key="validator.wrong.user.password.double.check.input" bundle="${local}"/></label>
						</c:if>
						<c:if test="${fieldErrorList.contains('password')}">
							<label class="text-warning"
							       style="font-size: xx-small"><f:message key="validator.wrong.user.password.input"
							                                              bundle="${local}"/></label>
						</c:if>
						<br>
						<button type="submit" value="Submit">Register Now</button>
						<br>
						<br>
						<c:if test="${newUserFault}">
							<label class="text-warning" style="text-align: center;font-size: medium">
								<f:message key="signup.user.fault" bundle="${local}"/></label>
						</c:if>
					
					</form:form>
				</div>
			</div><!-- This templates was made by Colorlib (https://colorlib.com) -->
		</div>
	</div>
</section>

<script src=<c:url value="../../js/validation.js"/>>
</script>

<c:import url="common/footer.jsp"/>
<%--<c:import url="common/cookies.jsp"/>--%>
</body>
</html>